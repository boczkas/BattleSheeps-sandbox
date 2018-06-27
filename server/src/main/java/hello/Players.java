package hello;

public class Players {

    private Player firstPlayer;
    private Player secondPlayer;

    public void addPlayer(Player player) throws TooManyPlayersException {

        if (firstPlayer == null) {
            firstPlayer = player;
            return;
        }

        if (secondPlayer == null) {
            secondPlayer = player;
            return;
        }

        throw new TooManyPlayersException();
    }

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public void setFirstPlayer(Player firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }

    public void setSecondPlayer(Player secondPlayer) {
        this.secondPlayer = secondPlayer;
    }

    public void printPlayers() {

        if (firstPlayer != null)
            System.out.println(firstPlayer.toString());
        if (secondPlayer != null)
            System.out.println(secondPlayer.toString());
    }
}
