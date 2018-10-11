import lombok.ToString;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.annotation.Documented;


//TODO Create class that represents coordinates
public class Coordinates {
    //private String lat;
    //private String lng;
    //private String[] coors;

    public static String getCoor(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements coors = doc.select(".geo");
        String allCoor = "";

        for(Element i:coors){
            allCoor = i.text();
            if( allCoor != null) {
                return allCoor;
            }
            return null;
        }
        if( allCoor != null) {
            return allCoor;
        }
        return null;
    }
}
