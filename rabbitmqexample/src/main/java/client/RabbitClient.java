package client;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitClient {


  private Channel channel;
  private Connection connection;

  public void openConnection() throws IOException, TimeoutException {

    ConnectionFactory factory = new ConnectionFactory();
    factory.setUsername("guest");
    factory.setPassword("guest");
    factory.setHost("localhost");
    factory.setPort(5672);

    connection = factory.newConnection();
    channel = connection.createChannel();

  }

  public void sentMessage(String message) throws IOException {
    channel.queueDeclare("test", false, false, false, null);
    channel.basicPublish("", "test", null, message.getBytes());
    System.out.println("send: " + message);
  }

  public void closeConnection() throws IOException, TimeoutException {
    channel.close();
    connection.close();
  }

  public static void main(String[] args) throws IOException, TimeoutException {
    RabbitClient client = new RabbitClient();
    client.openConnection();

    client.sentMessage("Jarek Przemek Bartek");

    client.closeConnection();
  }
}
