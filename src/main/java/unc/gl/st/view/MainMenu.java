package unc.gl.st.view;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.server.InputStreamFactory;
import com.vaadin.flow.server.StreamResource;

import javax.xml.transform.stream.StreamSource;
import java.awt.*;
import java.io.*;
import java.net.URL;


public class MainMenu {
    public static VerticalLayout build(VerticalLayout mainLayout) {
        VerticalLayout body = new VerticalLayout();
        body.setSizeFull();
        body.setAlignItems(Alignment.CENTER);
        body.setJustifyContentMode(JustifyContentMode.CENTER);
        Button jouer = new Button("JOUER", new Icon(VaadinIcon.PLAY)); // Bouton JOUER
        jouer.setClassName("button gap pointer");
        jouer.addClickListener(ev -> mainLayout.replace(body, PlayerMenu.build(mainLayout)));

        body.add(jouer);

        Anchor download = new Anchor(new StreamResource("Regles_Schotten_Totten.pdf",
                (InputStreamFactory) () -> MainMenu.class.getClassLoader().getResourceAsStream("Regles_Schotten_Totten.pdf")), "PDF");
        download.setTarget("_blank");
        download.removeAll();
        download.setClassName("noPointer");

        Button downloadButton = new Button("COMMENT JOUER?", new Icon(VaadinIcon.DOWNLOAD)); // Bouton COMMENT JOUER
        downloadButton.setClassName("button pointer");

        download.add(downloadButton);
        body.add(download);

        return body;
    }
}