package unc.gl.st.combination;

import unc.gl.st.card.Card;

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
     * @param combination the combination to compare to
     * @return <p>1 if win <hr> 0 if tie <hr> -1 if lose <hr> -2 if an error occured</p> 
     */
    @Override
    public int compareTo(Combination combination) {
        if(this.cards.size() != 3 || combination.cards.size() != 3){
            return -2;
        }
        CombinationType combinationType1 = CombinationType.findFor(this);
        CombinationType combinationType2 = CombinationType.findFor(combination);

        if(combinationType1.ordinal() > combinationType2.ordinal()){
            return -1;
        } else if(combinationType1.ordinal() == combinationType2.ordinal()){
            return Integer.compare(this.getSum(), combination.getSum());
        } else {
            return 1;
        }
    }
}
