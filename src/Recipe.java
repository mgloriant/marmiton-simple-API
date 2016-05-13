import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

/**
 * The class Recipe describe a recipe.
 */
public class Recipe {
    /**
     * Name of the Recipe
     */
    private String name;
    /**
     * URL link of the Recipe extracted from <a href="http://marmiton.org">http://marmiton.org</a>
     */
    private String urlLink;
    /**
     * HTML page of the Recipe
     */
    private Document page;
    /**
     * HTML code that contains informations about the Recipe
     */
    private Element pageContent;

    /**
     * Preparation time of the Recipe
     */
    private String prepTime;
    /**
     * Cooking time of the Recipe
     */
    private String cookingTime;
    /**
     * Details about the Recipe : instructions about how to make this Recipe
     */
    private String details;
    /**
     * Ingredients needed to cook the Recipe
     */
    private String ingredients;


    /**
     * Simple constructor of Recipe.
     * @param name Name of the Recipe.
     * @param urlLink Url link of the Recipe.
     */
    public Recipe(String name, String urlLink)  {

        this.name = name;
        this.urlLink = urlLink;


    }

    /**
     * Method that send a query to marmiton.org and load the informations about the Recipe.
     * @throws IOException
     */
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
