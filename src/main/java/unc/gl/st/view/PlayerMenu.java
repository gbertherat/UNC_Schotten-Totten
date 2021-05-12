package unc.gl.st.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.textfield.TextField;

import unc.gl.st.game.Game;
import unc.gl.st.game.GameOptions;
import unc.gl.st.game.GameRegistry;
import unc.gl.st.player.Player;

public class PlayerMenu {
    public static VerticalLayout build(VerticalLayout mainLayout) {
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

            Game game = GameRegistry.getInstance().createNewGame(new GameOptions(playerOneName+"&"+playerTwoName));
            
            game.addPlayer(new Player(playerOneName.getValue()));
            game.addPlayer(new Player(playerTwoName.getValue()));

            mainLayout.replace(formLayout, GameMenu.build(game));
        });

        formLayout.add(valider);
        return formLayout;
    }
}
