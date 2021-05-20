package unc.gl.st.combination;

import unc.gl.st.card.Card;
import unc.gl.st.combination.CombinationType.Type;

import java.util.List;

public class Combination implements Comparable<Combination>{
    private final List<Card> cards;
    private int sum;

    public Combination(List<Card> cards){
        this.cards = cards;
        this.sum = 0;

        for (Card clanCard : this.cards) {
            this.sum += clanCard.getStrength();
        }
    }

    public List<Card> getCards(){
        return this.cards;
    }

    public int getSum(){
        return this.sum;
    }

    /**
     * This method allows for the comparison between two combinations
     * @param o the combination to compare to
     * @return <p>1 if win <hr> 0 if tie <hr> -1 if lose <hr> -2 if an error occured</p> 
     */
    @Override
    public int compareTo(Combination o) {
        if(this.cards.size() != 3 || o.cards.size() != 3){
            return -2;
        }
        Type type1 = CombinationType.findFor(this);
        Type type2 = CombinationType.findFor(o);

        if(type1.ordinal() > type2.ordinal()){
            return -1;
        } else if(type1.ordinal() == type2.ordinal()){
            return Integer.compare(this.getSum(), o.getSum());
        } else {
            return 1;
        }
    }
}
