package unc.gl.st.combination;

import unc.gl.st.card.ClanCard;
import unc.gl.st.combination.CombinationType.Type;

import java.util.List;

public class Combination implements Comparable<Combination>{
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

    public int getSum(){
        return this.sum;
    }

    /**
     * This method allows for the comparison between two combinations
     * @param combination the combination to compare to
     * @return <p>1 if win <hr> 0 if tie <hr> -1 if lose <hr> -2 if an error occured</p> 
     */
    @Override
    public int compareTo(Combination o) {
        if(this.cards.size() != 3 || o.cards.size() != 3){
            return -2;
        }
        CombinationType combType = new CombinationType();
        Type type1 = combType.findFor(this);
        Type type2 = combType.findFor(o);

        if(type1.ordinal() > type2.ordinal()){
            return -1;
        } else if(type1.ordinal() == type2.ordinal()){
            if(this.getSum() < o.getSum()){
                return -1;
            } else if(this.getSum() == o.getSum()){
                return 0;
            } else {
                return 1;
            }
        } else {
            return 1;
        }
    }
}
