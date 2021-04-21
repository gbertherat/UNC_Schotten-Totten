package unc.gl.st;

import com.vaadin.flow.component.HtmlContainer;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.login.LoginI18n.Form;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.router.Route;

import org.jsoup.nodes.FormElement;

import unc.gl.st.player.Player;

@Route("start")
@Tag("st-main")
@CssImport("./styles/style.css")
public class StartView extends HtmlContainer{

    public VerticalLayout buildBody(){
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

        Element formElement = new Element("form");
        formElement.setAttribute("method", "post");
        formElement.setAttribute("action", "game");
        formElement.appendChild(formLayout.getElement());

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
        });

        formLayout.add(valider);
        return formLayout;
    }
    
    public StartView(){
        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setClassName("main");
        mainLayout.setAlignItems(Alignment.CENTER);

        mainLayout.setSizeFull();

        // HEADER //
        mainLayout.add(Components.buildHeader());

        // BODY //
        VerticalLayout body = buildBody();
        mainLayout.add(body);

        // FOOTER //
        mainLayout.add(Components.buildFooter());
        
        this.add(mainLayout);
    }
}