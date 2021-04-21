package unc.gl.st;

import com.vaadin.flow.component.HtmlContainer;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.router.Route;

import unc.gl.st.player.Player;

@Route("game")
@Tag("st-main")
@CssImport("./styles/style.css")
public class GameView extends HtmlContainer {
    
    public GameView(){
        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setClassName("main");
        mainLayout.setAlignItems(Alignment.CENTER);

        mainLayout.setSizeFull();

        // HEADER //
        mainLayout.add(Components.buildHeader());

        // BODY //

        // FOOTER //
        mainLayout.add(Components.buildFooter());
        
        this.add(mainLayout);
    }
}
