package unc.gl.st;

import com.vaadin.flow.component.HtmlContainer;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.router.Route;

@Route
@Tag("st-main")
@CssImport("./styles/style.css")
public class MainView extends HtmlContainer {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public MainView(){
        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setClassName("main");
        mainLayout.setAlignItems(Alignment.CENTER);

        mainLayout.setSizeFull();

        // HEADER //
        Header header = new Header();
        header.setClassName("header");
        
        Image logo = new Image("/img/logo.png", "Logo Schotten Totten");
        logo.setWidth(240, Unit.PIXELS);
        logo.setHeight(128, Unit.PIXELS);

        header.add(logo);
        mainLayout.add(header);

        // BODY //
        VerticalLayout body = new VerticalLayout();
        body.setSizeFull();
        body.setAlignItems(Alignment.CENTER);
        body.setJustifyContentMode(JustifyContentMode.CENTER);
        Button jouer = new Button("JOUER"); // Bouton JOUER
        jouer.setClassName("mainButton");
        body.add(jouer);

        Button howToPlay = new Button("COMMENT JOUER?"); // BOUTON COMMENT JOUER?
        howToPlay.setClassName("mainButton");
        body.add(howToPlay);

        mainLayout.add(body);

        // FOOTER //
        Footer footer = new Footer();
        footer.setClassName("footer");
        Label madeBy = new Label("Créé par Bertherat Guillaume, Boyer--Seytres Arthur, Moinet Mathéo. 2021");
        footer.add(madeBy);

        mainLayout.add(footer);
        
        this.add(mainLayout);
    }
}
