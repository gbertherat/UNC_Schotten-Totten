package unc.gl.st;

import com.vaadin.flow.component.HtmlContainer;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
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

    public VerticalLayout buildMainMenu(){
        VerticalLayout body = new VerticalLayout();
        body.setSizeFull();
        body.setAlignItems(Alignment.CENTER);
        body.setJustifyContentMode(JustifyContentMode.CENTER);
        Button jouer = new Button("JOUER"); // Bouton JOUER
        jouer.setClassName("mainButton");
        jouer.addClickListener(ev -> {
            getUI().get().getPage().setLocation("start");
        });

        body.add(jouer);

        Button howToPlay = new Button("COMMENT JOUER?"); // BOUTON COMMENT JOUER?
        howToPlay.setClassName("mainButton");
        body.add(howToPlay);
        return body;
    }

    public MainView(){
        VerticalLayout mainLayout = new VerticalLayout();
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
