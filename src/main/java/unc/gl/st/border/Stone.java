package unc.gl.st.border;

import java.util.HashMap;
import java.util.Map;

import unc.gl.st.card.Card;
import unc.gl.st.card.ClanCard;
import unc.gl.st.combination.Combination;
import unc.gl.st.player.Player;

public class Stone {
    private int id;
    private Map<Player, StoneArea> areas;
    private Player ownBy;
    private Player firstPlayerToPlace3Cards;

    public Stone(int id){
        this.id = id;
        this.ownBy = null;
        this.areas = new HashMap<>();
        this.firstPlayerToPlace3Cards = null;
    }

    public int getId() {
        return this.id;
    }

    public Map<Player,StoneArea> getAreas() {
        return this.areas;
    }

    public Player getOwnBy(){
        return this.ownBy;
    }

    public void setOwnBy(Player player){
        this.ownBy = player;
    }

    public StoneArea getAreaFor(Player player){
        return areas.get(player);
    } 

    public boolean isFullFor(Player player){
        StoneArea area = getAreaFor(player);
        return area.isFull();
    }

    public void addCardFor(Player player, Card card){
        StoneArea area = getAreaFor(player);
        if(area == null){
            area = new StoneArea();
            areas.put(player, area);
        }

        if(!area.isFull()){
            area.addCard(card);
        }

        if(area.isFull() && firstPlayerToPlace3Cards == null){
            firstPlayerToPlace3Cards = player;
        }

        if(area.isFull()){
            fightForStone();
        }
    }

    public void fightForStone(){
        if(areas.keySet().size() != 2){
            return;
        }
        Player player1 = (Player) areas.keySet().toArray()[0];
        Player player2 = (Player) areas.keySet().toArray()[1];

        StoneArea area1 = areas.get(player1);
        StoneArea area2 = areas.get(player2);

        if(area1.isFull() && area2.isFull()){
            Combination combination1 = new Combination(area1.getCards());
            Combination combination2 = new Combination(area2.getCards());

            int result = combination1.compareTo(combination2);

            if(result == -1){
                this.setOwnBy(player2);
            } else if(result == 0){
                this.setOwnBy(this.firstPlayerToPlace3Cards);
            } else {
                this.setOwnBy(player1);
            }
        }
    }
}
