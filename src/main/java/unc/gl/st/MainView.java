package unc.gl.st;

import com.vaadin.flow.component.HtmlContainer;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import unc.gl.st.game.Game;
import unc.gl.st.game.GameOptions;
import unc.gl.st.game.GameRegistry;
import unc.gl.st.player.Player;

@Route
@Tag("st-main")
@CssImport("./styles/style.css")
public class MainView extends HtmlContainer {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private VerticalLayout mainLayout;

    public VerticalLayout buildMainMenu(){
        VerticalLayout body = new VerticalLayout();
        body.setSizeFull();
        body.setAlignItems(Alignment.CENTER);
        body.setJustifyContentMode(JustifyContentMode.CENTER);
        Button jouer = new Button("JOUER"); // Bouton JOUER
        jouer.setClassName("mainButton");
        jouer.addClickListener(ev -> {
            mainLayout.replace(body, buildPlayerMenu());
        });

        body.add(jouer);

        Button howToPlay = new Button("COMMENT JOUER?"); // BOUTON COMMENT JOUER?
        howToPlay.setClassName("mainButton");
        body.add(howToPlay);
        return body;
    }

    public VerticalLayout buildPlayerMenu(){
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
            }
            Game game = GameRegistry.getInstance().createNewGame(new GameOptions(playerOneName+"&"+playerTwoName));
            
            game.addPlayer(new Player(playerOneName.getValue()));
            game.addPlayer(new Player(playerTwoName.getValue()));

            mainLayout.replace(formLayout, buildGame(game));
        });

        formLayout.add(valider);
        return formLayout;
    }

    public VerticalLayout buildGame(Game game){
        VerticalLayout gameLayout = new VerticalLayout();
        
        return gameLayout;
    }

    public MainView(){
        mainLayout = new VerticalLayout();
        mainLayout.setClassName("main");
        mainLayout.setAlignItems(Alignment.CENTER);

        mainLayout.setSizeFull();

        // HEADER //
        mainLayout.add(Components.buildHeader());

        // BODY //
        mainLayout.add(buildMainMenu());

        // FOOTER //
        mainLayout.add(Components.buildFooter());
        
        this.add(mainLayout);
    }
}
