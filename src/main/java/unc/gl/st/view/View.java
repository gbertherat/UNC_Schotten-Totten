package unc.gl.st.view;

import com.vaadin.flow.component.HtmlContainer;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.router.Route;

@Route("/")
@Tag("st-main")
@CssImport("./styles/style.css")
public class View extends HtmlContainer{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public View(){
        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setClassName("main");
        mainLayout.setAlignItems(Alignment.CENTER);

        mainLayout.setSizeFull();

        // HEADER //
        mainLayout.add(Components.header());

        // BODY //
        mainLayout.add(MainMenu.build(mainLayout));

        // FOOTER //
        mainLayout.add(Components.footer());
        
        this.add(mainLayout);
    }

    

    
}
