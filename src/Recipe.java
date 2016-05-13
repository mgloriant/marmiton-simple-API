import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

/**
 * Created by paull on 12/02/2016.
 */
public class Recipe {
    private String name;
    private String urlLink;
    private Document page;
    private Element pageContent;

    private String prepTime;
    private String cookingTime;
    private String details;
    private String ingredients;


    public Recipe(String name, String urlLink)  {

        this.name = name;
        this.urlLink = urlLink;


    }

    public void loadInformations()throws IOException{
        page = Jsoup.connect(this.getUrlLink()).get();
        pageContent = page.getElementsByClass("m_content_recette_main").first();

        //System.out.println(pageContent);

        prepTime = (pageContent.getElementsByClass("preptime").first().getElementsByClass("value-title").first().attr("title")).substring(2);
        cookingTime = (pageContent.getElementsByClass("cooktime").first().getElementsByClass("value-title").first().attr("title")).substring(2);
        details = pageContent.getElementsByClass("m_content_recette_todo").first().text();
        ingredients = pageContent.getElementsByClass("m_content_recette_ingredients").first().text();

    }

    public String getName() {
        return name;
    }

    public String getUrlLink() {
        return urlLink;
    }

    public String getPrepTime() {
        return prepTime;
    }

    public String getCookingTime() {
        return cookingTime;
    }


    @Override
    public String toString() {
        return "Recipe{" +"\n"+
                "name='" + name + '\'' +"\n"+
                ", urlLink='" + urlLink + '\'' +"\n"+
                ", prepTime='" + prepTime + '\'' +"\n"+
                ", cookingTime='" + cookingTime + '\'' +"\n"+
                ", details='" + details + '\'' +"\n"+
                ", ingredients='" + ingredients + '\'' +"\n"+
                '}';
    }


}
