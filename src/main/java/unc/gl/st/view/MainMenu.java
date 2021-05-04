package unc.gl.st.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;


public class MainMenu{
    public static VerticalLayout build(VerticalLayout mainLayout){
        VerticalLayout body = new VerticalLayout();
        body.setSizeFull();
        body.setAlignItems(Alignment.CENTER);
        body.setJustifyContentMode(JustifyContentMode.CENTER);
        Button jouer = new Button("JOUER"); // Bouton JOUER
        jouer.setClassName("button");
        jouer.addClickListener(ev -> {
            mainLayout.replace(body, PlayerMenu.build(mainLayout));
        });

        body.add(jouer);

        Button howToPlay = new Button("COMMENT JOUER?"); // BOUTON COMMENT JOUER?
        howToPlay.setClassName("button");
        body.add(howToPlay);
        return body;
    }
}
