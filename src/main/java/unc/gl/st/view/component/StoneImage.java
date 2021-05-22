package unc.gl.st.view.component;

import com.vaadin.flow.component.html.Image;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class StoneImage extends Image {

    private static final Random rand = new Random();
    private static final List<String> stoneImageNames = getStoneImageNames();

    private static List<String> getStoneImageNames() {
        URL url = Image.class.getClassLoader().getResource("META-INF/resources/img/tuile_borne");

        File[] files = null;
        if (url != null) {
            File dir;
            try {
                dir = new File(URLDecoder.decode(url.getFile(), "UTF-8"));
                files = dir.listFiles();
            } catch (UnsupportedEncodingException e) {
                System.out.println("Could not load files because of encoding exception");
                e.printStackTrace();
            }
        }

        return Arrays.stream(files).map(File::getName).collect(Collectors.toList());
    }

    private static String getRandomStoneName(){
        return stoneImageNames.get(rand.nextInt(stoneImageNames.size()));
    }

    public StoneImage() {
        super("img/tuile_borne/" + getRandomStoneName(), "Carte Frontière");
        this.setClassName("border noGap");
    }
}
