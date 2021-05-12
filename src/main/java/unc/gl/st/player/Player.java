package unc.gl.st.player;

public class Player {
    private int id;
    private Hand hand;
    private String name;

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

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
        this.id = 0;
        this.name = name;
        this.hand = new Hand();
    }
}
