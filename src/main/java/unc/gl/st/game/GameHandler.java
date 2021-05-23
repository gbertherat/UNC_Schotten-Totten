package unc.gl.st.game;

import com.vaadin.flow.component.Component;
import unc.gl.st.border.Stone;
import unc.gl.st.card.Card;
import unc.gl.st.player.Player;
import unc.gl.st.view.component.ClanCardImage;

public class GameHandler {

    public enum ResponseCode {
        NO_ACTION,
        OK,
        ERROR,
        GAME_FINISHED {
            public boolean win;
        }
    }

    final Game game;

    public GameHandler(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public ResponseCode placeCardOnStone(Player currentPLayer, Card card, Stone stone){
        if (card == null || stone == null || stone.isFullFor(currentPLayer)) {
            return ResponseCode.NO_ACTION;
        }

        stone.addCardFor(currentPLayer, card);
        currentPLayer.getHand().removeCard(card);

        final Player stoneOwner = stone.getOwnBy();
        if (stoneOwner == null) {
            return ResponseCode.OK;
        }

        stoneOwner.setScore(stoneOwner.getScore() + 1);

        int nbAdjacentOwned = game.getBoard().getBorder().getNbrAdjacentStones(stoneOwner);

        if (nbAdjacentOwned >= 3 || stoneOwner.getScore() >= 5) {
            return ResponseCode.GAME_FINISHED;
        } else {
            return ResponseCode.OK;
        }
    }

}
