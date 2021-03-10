package unc.gl.st.combination;

import unc.gl.st.card.ClanCard;

import java.util.List;

public class Combination {
    private List<ClanCard> cards;
    private int sum;

    public Combination(List<ClanCard> cards){
        this.cards = cards;
        this.sum = 0;

        for (ClanCard clanCard : this.cards) {
            this.sum += clanCard.getStrength();
        }
    }

    public List<ClanCard> getCards(){
        return this.cards;
    }

    public void setCards(List<ClanCard> cards){
        this.cards = cards;
    }

    public int getSum(){
        return this.sum;
    }

    public void setSum(int sum){
        this.sum = sum;
    }

    /**
     * This method allows for the comparison between two combinations
     * @param combination the combination to compare to
     * @return <p>1 if win <hr> 0 if tie <hr> -1 if lose</p>
     */
    public int compareTo(Combination combination){
        if(this.sum > combination.getSum()){
            return 1;
        } 
        else if(this.sum == combination.getSum()){
            return 0;
        }
        return -1;
    }
}
