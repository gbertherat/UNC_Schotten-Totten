package unc.gl.st;

import com.vaadin.flow.component.HtmlContainer;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.router.Route;

@Route
@Tag("st-main")
public class MainView extends HtmlContainer {
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public MainView() {
        this.add("Hello World");
    }
}
