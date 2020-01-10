import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


class WebScrape {

    String url;

    WebScrape(String username){
        url = "https://steamcommunity.com/id/" + username + "/games/?tab=all";
        try {
            Document doc = Jsoup.connect(url).get();
            Elements ids = doc.getElementsByTag("games_list_rows");
            System.out.println(ids.next());
            /*for (Element row: doc.select("div.games_list_rows div")){

            }*/
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
