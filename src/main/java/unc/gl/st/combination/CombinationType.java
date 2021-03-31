package unc.gl.st.combination;

import unc.gl.st.card.ClanCard;
import unc.gl.st.card.Color;

import java.util.List;

public class CombinationType{
    public enum Type {
        COLOR_RUN,
        THREE_OF_KIND,
        COLOR,
        RUN,
        SUM
    }
    
    public static Type findFor(Combination combination){
        List<ClanCard> cards = combination.getCards();

        if(isColorRun(cards)){
            return Type.COLOR_RUN;
        } 
        else if(isThreeOfKind(cards)){
            return Type.THREE_OF_KIND;
        } 
        else if(isColor(cards)){
            return Type.COLOR;
        } 
        else if(isRun(cards)){
            return Type.RUN;
        }
        return Type.SUM;
    }

    private static boolean isColorRun(List<ClanCard> cards){
        ClanCard ref = cards.get(0);
        Color colorRef = ref.getColor();
        int strengthRef = ref.getStrength();

        for(int i = 1; i < cards.size(); i++){
            if(cards.get(i).getColor() != colorRef
            || cards.get(i).getStrength()-i != strengthRef){
                return false;
            }
        }
        return true;
    }

    private static boolean isThreeOfKind(List<ClanCard> cards){
        int ref = cards.get(0).getStrength();

        for(int i = 1; i < cards.size(); i++){
            if(cards.get(i).getStrength() != ref){
                return false;
            }
        }
        return true;
    }

    private static boolean isColor(List<ClanCard> cards){
        Color ref = cards.get(0).getColor();

        for (int i = 1; i < cards.size(); i++){
            if(cards.get(i).getColor() != ref){
                return false;
            }
        }
        return true;
    }

    private static boolean isRun(List<ClanCard> cards){
        int ref = cards.get(0).getStrength();

        for(int i = 1; i < cards.size(); i++){
            if(cards.get(i).getStrength()-i != ref){
                return false;
            }
        }
        return true;
    }
}
