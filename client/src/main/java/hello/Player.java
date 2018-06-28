package hello;

public class Player {
    String name;
    String IP;

    public Player(String name, String IP) {
        this.name = name;
        this.IP = IP;
    }

    @Override
    public String toString() {
        return name + " " + IP;
    }
}
