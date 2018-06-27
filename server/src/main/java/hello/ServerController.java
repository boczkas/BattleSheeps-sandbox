package hello;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ServerController {

    private static int num = 0;
    private Players players = new Players();

    @RequestMapping(value = "/",
            method = RequestMethod.POST,
            produces = "text/plain")
    public ResponseEntity<String> greetingForm(HttpServletRequest request) {
        String response;

        num++;

        if (num % 2 == 0) {
            response = "even";
        } else {
            response = "odd";
        }

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>("{\"msg\": \"" + response + "\"}", httpHeaders, HttpStatus.OK);

    }

    @RequestMapping(value = "/register",
            method = RequestMethod.POST,
            produces = "text/plain")
    public @ResponseBody
    Player registerPlayer(@RequestBody Player player) {
        System.out.println(player.toString());
        try {
            players.addPlayer(player);
        } catch (TooManyPlayersException e) {
            e.printStackTrace();
        }

        players.printPlayers();

        return player;
    }
}
