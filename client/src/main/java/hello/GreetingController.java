package hello;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

@Controller
public class GreetingController {

    Lista lista = new Lista();
    RestTemplate template = new RestTemplate();

    private Channel channel;
    private Connection connection;
    private String queueName = "players";

    private void openConnection() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("admin");
        factory.setPassword("admin");
        factory.setHost("10.30.1.140");

        connection = factory.newConnection();
        channel = connection.createChannel();
    }

    private void sendMessage(String message) throws IOException {
        channel.queueDeclare(queueName, false, false, false, null);
        channel.basicPublish("", queueName, null, message.getBytes());
        System.out.println("send: " + message);
    }

    private void closeConnection() throws IOException, TimeoutException {
        channel.close();
        connection.close();
    }

    @GetMapping("/greeting")
    public String greetingForm(Model model) throws IOException, TimeoutException {
        model.addAttribute("greeting", new Greeting());
        model.addAttribute("list", lista);

        openConnection();

        return "greeting";
    }

    @PostMapping("/greeting")
    public String greetingSubmit(@ModelAttribute Greeting greeting, HttpServletRequest request) throws IOException, TimeoutException {

        sendMessage("Jarek");
        sendMessage("Przemek");

        closeConnection();

        System.out.println(request.getRemoteAddr());

        return "greeting";
    }
}
