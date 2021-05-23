package unc.gl.st.combination;

import unc.gl.st.card.Card;
import unc.gl.st.card.Color;

import java.util.Comparator;
import java.util.List;

public enum CombinationType implements Type {
    COLOR_RUN {
        @Override
        public boolean supports(List<Card> cards) {
            return isColorRun(cards);
        }
    },
    THREE_OF_KIND {
        @Override
        public boolean supports(List<Card> cards) {
            return isThreeOfKind(cards);
        }
    },
    COLOR {
        @Override
        public boolean supports(List<Card> cards) {
            return isColor(cards);
        }
    },
    RUN {
        @Override
        public boolean supports(List<Card> cards) {
            return isRun(cards);
        }
    },
    SUM {
        @Override
        public boolean supports(List<Card> cards) {
            return true;
        }
    };

    public abstract boolean supports(List<Card> cards);

    public static CombinationType findFor(Combination combination) {
        List<Card> cards = combination.getCards();

        for (CombinationType type : CombinationType.values()) {
            if (type.supports(cards)) {
                return type;
            }
        }
        return CombinationType.SUM;
    }

    private static boolean isColorRun(List<Card> cards) {
        cards.sort(Comparator.comparingInt(Card::getStrength));

        Card ref = cards.get(0);
        Color colorRef = ref.getColor();
        int strengthRef = ref.getStrength();

        for (int i = 1; i < cards.size(); i++) {
            if (cards.get(i).getColor() != colorRef
                    || cards.get(i).getStrength() - i != strengthRef) {
                return false;
            }
        }
        return true;
    }

    private static boolean isThreeOfKind(List<Card> cards) {
        int ref = cards.get(0).getStrength();

        for (int i = 1; i < cards.size(); i++) {
            if (cards.get(i).getStrength() != ref) {
                return false;
            }
        }
        return true;
    }

    private static boolean isColor(List<Card> cards) {
        Color ref = cards.get(0).getColor();

        for (int i = 1; i < cards.size(); i++) {
            if (cards.get(i).getColor() != ref) {
                return false;
            }
        }
        return true;
    }

    private static boolean isRun(List<Card> cards) {
        cards.sort(Comparator.comparingInt(Card::getStrength));
        int ref = cards.get(0).getStrength();

        for (int i = 1; i < cards.size(); i++) {
            if (cards.get(i).getStrength() - i != ref) {
                return false;
            }
        }
        return true;
    }
}
