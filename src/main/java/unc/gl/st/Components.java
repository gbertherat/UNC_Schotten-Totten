package unc.gl.st;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;

public class Components {
    public static Header buildHeader(){
        Header header = new Header();
        header.setClassName("header");
        
        Image logo = new Image("/img/logo.png", "Logo Schotten Totten");
        logo.setWidth(240, Unit.PIXELS);
        logo.setHeight(128, Unit.PIXELS);

        header.add(logo);
        return header;
    }

    public static Footer buildFooter(){
        Footer footer = new Footer();
        footer.setClassName("footer");
        Label madeBy = new Label("Créé par Bertherat Guillaume, Boyer--Seytres Arthur, Moinet Mathéo. 2021");
        footer.add(madeBy);
        return footer;
    }
}
