package hello;

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
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Controller
public class GreetingController {

    Lista lista = new Lista();
    RestTemplate template = new RestTemplate();

    @GetMapping("/greeting")
    public String greetingForm(Model model) {
        model.addAttribute("greeting", new Greeting());
        model.addAttribute("list", lista);
        return "greeting";
    }

    @PostMapping("/greeting")
    public String greetingSubmit(@ModelAttribute Greeting greeting, HttpServletRequest request) {
        lista.addToList(greeting.getContent());
        HttpHeaders headers = new HttpHeaders();
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("name", "Bajtek");
        map.add("IP", "1.2.3.4");
//        BackendDTO response = template.getForEntity("http://localhost:9090/", BackendDTO.class);
//        ResponseEntity<String> response = template.postForEntity("http://localhost:9090/register", headers, String.class, map);
        String response = template.postForObject("http://localhost:9090/register", map, String.class);
        System.out.println(response);

        System.out.println(request.getRemoteAddr());

        return "greeting";
    }
}
