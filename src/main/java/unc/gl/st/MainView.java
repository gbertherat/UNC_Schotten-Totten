package unc.gl.st;

import com.vaadin.flow.component.HtmlContainer;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.router.Route;

@Route
@Tag("st-main")
public class MainView extends HtmlContainer {
    
    public MainView() {
        this.add("Hello World");
    }
}
