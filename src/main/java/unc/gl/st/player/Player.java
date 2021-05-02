package unc.gl.st.player;

public class Player {

    private Hand hand;
    private String name;

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public String getName() {
        return name;
    }

    public Player(String name) {
        this.name = name;
        this.hand = new Hand();
    }
}
