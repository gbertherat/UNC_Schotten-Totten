package unc.gl.st.view.component;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import unc.gl.st.border.Stone;
import unc.gl.st.border.StoneArea;
import unc.gl.st.game.Game;
import unc.gl.st.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class BorderLayout extends HorizontalLayout {

    Game game;
    List<StoneLayout> stoneLayouts;

    public BorderLayout(Game game) {
        super();
        this.game = game;
        this.setSizeFull();
        PlayerSideLayout playerSidesLayout = new PlayerSideLayout(game.getPlayers());
        this.add(playerSidesLayout);

        stoneLayouts = new ArrayList<>();
        for (Stone stone : game.getBoard().getBorder().getStones()) {
            StoneLayout stoneLayout = new StoneLayout(stone, this);
            stoneLayouts.add(stoneLayout);
            this.add(stoneLayout);
        }
    }

    public List<StoneLayout> getStoneLayouts() {
        return stoneLayouts;
    }

    private void addClickListenerOnStoneImages(ComponentEventListener<ClickEvent<Image>> listener) {
        for (StoneLayout stoneLayout : stoneLayouts) {
            stoneLayout.addClickListenerOnStoneImage(listener);
        }
    }

    public void updateStones() {
        stoneLayouts.forEach(StoneLayout::update);
    }

    public void updateStone(Stone stone) {
        stoneLayouts.stream().filter(stoneLayout -> stoneLayout.stone == stone).forEach(StoneLayout::update);
    }

    private class PlayerSideLayout extends VerticalLayout {

        public PlayerSideLayout(List<Player> players) {
            super();
            this.setSizeFull();
            this.setJustifyContentMode(JustifyContentMode.CENTER);
            this.add(
                    new Label(players.get(0).getName()),
                    new Label(players.get(1).getName())
            );
        }
    }

    public class StoneLayout extends VerticalLayout {

        private StoneImage stoneImage;
        private Stone stone;
        private BorderLayout borderLayout;

        public StoneLayout(Stone stone, BorderLayout borderLayout) {
            super();
            this.stone = stone;
            this.setClassName("noGap");
            this.setPadding(false);
            this.setAlignItems(Alignment.CENTER);
            this.setJustifyContentMode(JustifyContentMode.CENTER);

            stoneImage = new StoneImage();
            this.add(stoneImage);

        }

        public Stone getStone() {
            return stone;
        }

        public void addClickListenerOnStoneImage(ComponentEventListener<ClickEvent<Image>> listener) {
            stoneImage.addClickListener(listener);
        }

        public void update() {
            this.removeAll();
            this.add(stoneImage);
            stone.getAreas().forEach((player, stoneArea) -> {
                if (player.getId() == 0) {
                    getSmallImageForCard(stoneArea, player).forEach(this::addComponentAsFirst);
                } else {
                    getSmallImageForCard(stoneArea, player).forEach(this::add);
                }
            });
        }

        public Stream<ClanCardImage> getSmallImageForCard(StoneArea stoneArea, Player player) {
            if (player.equals(stone.getOwnBy())) {
                return stoneArea.getCards().stream()
                        .map(card -> {
                            ClanCardImage clanCardImage = new ClanCardImage(card, true, player);
                            clanCardImage.setWinningArea();
                            return clanCardImage;
                        });
            } else {
                return stoneArea.getCards().stream()
                        .map(card -> new ClanCardImage(card, true, player));
            }
        }


    }


}
