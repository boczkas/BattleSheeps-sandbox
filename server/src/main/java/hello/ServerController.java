package hello;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.GetResponse;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ServerController {

    private Channel channel;
    private Connection connection;
    private Players players = new Players();
    private String queueName = "players";

    ServerController() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("admin");
        factory.setPassword("admin");
        factory.setHost("10.30.1.140");

        connection = factory.newConnection();
        channel = connection.createChannel();

        channel.queueDeclare(queueName, false, false,  false, null);
    }

    void run() throws IOException, TimeoutException {
        GetResponse response;
        while (!players.arePresent()) {
            response = channel.basicGet(queueName, true);
            if (response != null) {
                registerPlayer(new Player(new String(response.getBody(), "UTF-8"), "127.0.0.1"));
//                System.out.println(new String(response.getBody(), "UTF-8"));
            }
        }
        closeConnection();
    }

    private void closeConnection() throws IOException, TimeoutException {
        channel.close();
        connection.close();
    }

    private void registerPlayer(Player player) {
//        System.out.println(player.toString());
        try {
            players.addPlayer(player);
        } catch (TooManyPlayersException e) {
            e.printStackTrace();
        }
        players.printPlayers();
    }
}
