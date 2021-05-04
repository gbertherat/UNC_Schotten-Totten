package unc.gl.st;

import java.io.File;
import java.util.List;
import java.util.Random;

import com.vaadin.flow.component.HtmlContainer;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import unc.gl.st.border.Border;
import unc.gl.st.card.Card;
import unc.gl.st.exception.EmptyStockException;
import unc.gl.st.exception.FullHandException;
import unc.gl.st.game.Game;
import unc.gl.st.game.GameOptions;
import unc.gl.st.game.GameRegistry;
import unc.gl.st.game.GameStatus;
import unc.gl.st.player.Hand;
import unc.gl.st.player.Player;
import unc.gl.st.stock.Stock;
import unc.gl.st.stock.StockFactories;

@Route
@Tag("st-main")
@CssImport("./styles/style.css")
public class MainView extends HtmlContainer{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private VerticalLayout mainLayout;
    private Game game;

    public MainView(){
        mainLayout = new VerticalLayout();
        mainLayout.setClassName("main");
        mainLayout.setAlignItems(Alignment.CENTER);

        mainLayout.setSizeFull();

        // HEADER //
        mainLayout.add(Components.header());

        // BODY //
        mainLayout.add(buildMainMenu());

        // FOOTER //
        mainLayout.add(Components.footer());
        
        this.add(mainLayout);
    }

    public VerticalLayout buildMainMenu(){
        VerticalLayout body = new VerticalLayout();
        body.setSizeFull();
        body.setAlignItems(Alignment.CENTER);
        body.setJustifyContentMode(JustifyContentMode.CENTER);
        Button jouer = new Button("JOUER"); // Bouton JOUER
        jouer.setClassName("button");
        jouer.addClickListener(ev -> {
            mainLayout.replace(body, buildPlayerMenu());
        });

        body.add(jouer);

        Button howToPlay = new Button("COMMENT JOUER?"); // BOUTON COMMENT JOUER?
        howToPlay.setClassName("button");
        body.add(howToPlay);
        return body;
    }

    public VerticalLayout buildPlayerMenu() {
        VerticalLayout formLayout = new VerticalLayout();
        formLayout.setSizeFull();
        formLayout.setAlignItems(Alignment.CENTER);
        formLayout.setJustifyContentMode(JustifyContentMode.CENTER);
    
        TextField playerOneName = new TextField("Nom du joueur 1:");
        playerOneName.setPrefixComponent(new Icon(VaadinIcon.USER));
        formLayout.add(playerOneName);

        TextField playerTwoName = new TextField("Nom du joueur 2:");
        playerTwoName.setPrefixComponent(new Icon(VaadinIcon.USER));
        formLayout.add(playerTwoName);

        Button valider = new Button("Valider");
        valider.setId("submitButton");

        valider.addClickListener(ev -> {
            if(playerOneName.getValue().length() == 0){
                Notification.show("Erreur: Veuillez remplir le champ 'Nom du joueur 1'", 5000, Position.TOP_CENTER);
                return;
            }

            if(playerTwoName.getValue().length() == 0){
                Notification.show("Erreur: Veuillez remplir le champ 'Nom du joueur 2'", 5000, Position.TOP_CENTER);
                return;
            }

            if(playerOneName.getValue().equals(playerTwoName.getValue())){
                Notification.show("Erreur: Veuillez choisir deux noms différents", 5000, Position.TOP_CENTER);
                return;
            }

            game = GameRegistry.getInstance().createNewGame(new GameOptions(playerOneName+"&"+playerTwoName));
            
            game.addPlayer(new Player(playerOneName.getValue()));
            game.addPlayer(new Player(playerTwoName.getValue()));

            mainLayout.replace(formLayout, buildGame(game));
        });

        formLayout.add(valider);
        return formLayout;
    }

    public VerticalLayout buildGame(Game game) {
        VerticalLayout gameLayout = new VerticalLayout();
        gameLayout.setSizeFull();
        gameLayout.setAlignItems(Alignment.CENTER);
        gameLayout.setJustifyContentMode(JustifyContentMode.CENTER);

        game.start();

        Random rand = new Random();

        /* PLAYER */
        HorizontalLayout playerLayout = new HorizontalLayout();
        playerLayout.setSizeFull();
        playerLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        List<Player> players = game.getPlayers();
        Player activePlayer = players.get(rand.nextInt(players.size()));

        Label whoseTurnLabel = new Label("Tour de: " + activePlayer.getName());
        whoseTurnLabel.setClassName("activePlayer");
        playerLayout.add(whoseTurnLabel);
        gameLayout.add(playerLayout);

        /* BORDER */
        HorizontalLayout topBorderLayout = new HorizontalLayout();
        topBorderLayout.setSizeFull();

        HorizontalLayout borderLayout = new HorizontalLayout();

        HorizontalLayout bottomBorderLayout = new HorizontalLayout();
        bottomBorderLayout.setSizeFull();

        Border border = new Border();

        File dir = new File("src/main/webapp/img/tuile_borne");
        File[] files = dir.listFiles();
        
        for(int i = 0; i < border.getNumStones(); i++){
            String borderCardPath = files[rand.nextInt(files.length)].getPath();
            borderCardPath = borderCardPath.replace("src\\main\\webapp", "");
            Image borderCardImage = new Image(borderCardPath, "Carte Frontière");
            borderCardImage.setClassName("border");

            borderLayout.add(borderCardImage);
        }        
        gameLayout.add(borderLayout);

        /* HAND */
        HorizontalLayout cardLayout = new HorizontalLayout();
        cardLayout.setSizeFull();

        Hand hand = activePlayer.getHand();
        Stock stock = StockFactories.createClanStock();

        for(int i = 0; i < Hand.HAND_SIZE; i++){
            try {
                Card randCard = stock.draw();
                hand.addCard(randCard);
                
                Image cardImage = new Image("/img/cartes_clan/" + randCard.getId().toLowerCase() + ".png", "Carte " + randCard.getId());
                cardImage.setClassName("carte");
                cardImage.setVisible(false);
                cardLayout.add(cardImage);
            } catch (FullHandException e) {
                e.printStackTrace();
            } catch (EmptyStockException e) {
                e.printStackTrace();
            }
        }

        Button showCards = new Button("Montrer les cartes");
        showCards.setClassName("button");
        showCards.addClickListener(ev -> {
            for (int i = 0; i < cardLayout.getComponentCount(); i++) {
                cardLayout.getComponentAt(i).setVisible(true);
                cardLayout.remove(showCards);
            }
        });
        cardLayout.add(showCards);

        Thread handThread = new Thread(){
            public void run(){
                while(game.getStatus() == GameStatus.STARTED){
                    System.out.println("test");
                    break;
                }
            }
        };
        handThread.start();
        gameLayout.add(cardLayout);

        /* STOCK */
        HorizontalLayout stockLayout = new HorizontalLayout();
        stockLayout.setSizeFull();
        stockLayout.setAlignItems(Alignment.END);
        stockLayout.setJustifyContentMode(JustifyContentMode.END);

        Image stockImage = new Image("/img/cartes_clan/back.png", "Carte Pioche");
        stockImage.setClassName("carte");
        stockLayout.add(stockImage);

        cardLayout.add(stockLayout);

        return gameLayout;
    }
}
