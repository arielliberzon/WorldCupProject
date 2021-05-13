import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

public class JsoupTester {
    public static void main(String[] args) throws IOException {

        String url = "https://www.fifa.com/fifa-world-ranking/ranking-table/men/#UEFA";
        Document document = Jsoup.connect(url).get();
        //System.out.println(document.body().getElementsByClass("fi-t__i"));
        Elements teamRank = document.body().select("td.fi-table__td.fi-table__rank");
        Elements teamNames = document.body().select("div.fi-t__n");
        Elements teamConf = document.body().select("td.fi-table__td.fi-table__confederation.hidden");
        Elements teamPoints = document.body().select("td.fi-table__td.fi-table__points");

        for (int i = 0; i < teamNames.size(); i++) {
            System.out.println(teamRank.get(i).text());
            System.out.println(teamNames.get(i).child(0).text());
            System.out.println(teamNames.get(i).child(1).text());
            String confederation = teamConf.get(i).text();
            confederation = confederation.substring(1, confederation.length() - 1);
            System.out.println(confederation);
            System.out.println(teamPoints.get(i).text()+"\n");
        }




    }
    //<div class="fi-t__i ">
    /**
     * String html = "<div class=\"content-text right-align bold-font\">foo</div>";
     * Document document = Jsoup.parse(html);
     * Elements elements = document.select("div.content-text.right-align.bold-font");
     * System.out.println(elements.text()); // foo
     */
}