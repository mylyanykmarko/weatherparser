import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Victor on 03.10.2018.
 */
public class Main {


    @SneakyThrows
    public static void main(String[] args) {
        String url = "https://uk.wikipedia.org/wiki/%D0%9C%D1%96%D1%81%D1%82%D0%B0_%D0%A3%D0%BA%D1%80%D0%B0%D1%97%D0%BD%D0%B8_(%D0%B7%D0%B0_%D0%B0%D0%BB%D1%84%D0%B0%D0%B2%D1%96%D1%82%D0%BE%D0%BC)";
        Document doc = Jsoup.connect(url).get();
        //System.out.println(doc.title());
        Elements cities = doc.select("table tr");
        City[] parsedCities = new City[cities.size()]; // You can use List`s or other java Collections
        String[] cors = new String[cities.size()];
        Map<String, String> pairs = new HashMap<String, String>();


        int counter = 0;
        int counter1 = 0;
        for (Element city : cities) {
            City myCity = City.parse(city);
            if (myCity != null) {
                //System.out.println(myCity.getUrl());
                String url1 = myCity.getUrl();
                String str = Coordinates.getCoor(url1);

                if(Coordinates.getCoor(url1) != null){
                    cors[counter1] = Coordinates.getCoor(url1);
                    //System.out.println(cors[counter1]);
                    pairs.put(myCity.getName(),cors[counter1]);
                    counter1++;
                }
                //System.out.println(Coordinates.getCoor(url1));
                parsedCities[counter] = myCity;
                //System.out.println(city.className());
                counter++;
            }
        }

        Map<String, String> reversedMap = new TreeMap<String, String>(pairs);

        for (Map.Entry entry : reversedMap.entrySet()) {
            System.out.println(entry.getKey() + ", " + entry.getValue());
        }

    }

}
