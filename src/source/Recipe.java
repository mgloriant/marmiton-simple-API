package source;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The class source.Recipe describe a recipe.
 */
public class Recipe {
    /**
     * Name of the source.Recipe
     */
    private String name;
    /**
     * URL link of the source.Recipe extracted from <a href="http://marmiton.org">http://marmiton.org</a>
     */
    private String urlLink;
    /**
     * HTML page of the source.Recipe
     */
    private Document page;
    /**
     * HTML code that contains informations about the source.Recipe
     */
    private Element pageContent;

    /**
     * Preparation time of the source.Recipe
     */
    private String prepTime;
    /**
     * Cooking time of the source.Recipe
     */
    private String cookingTime;
    /**
     * Details about the source.Recipe : instructions about how to make this source.Recipe
     */
    private String details;
    /**
     * Ingredients needed to cook the source.Recipe
     */
    private String ingredients;


    /**
     * Simple constructor of source.Recipe.
     *
     * @param name    Name of the source.Recipe.
     * @param urlLink Url link of the source.Recipe.
     */
    public Recipe(String name, String urlLink) {

        this.name = name;
        this.urlLink = urlLink;


    }

    /**
     * Method that send a query to marmiton.org and load the informations about the source.Recipe.
     *
     * @throws IOException
     */
    public void loadInformations() throws IOException {
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
        return "source.Recipe{" + "\n" +
                "name='" + name + '\'' + "\n" +
                ", urlLink='" + urlLink + '\'' + "\n" +
                ", prepTime='" + prepTime + '\'' + "\n" +
                ", cookingTime='" + cookingTime + '\'' + "\n" +
                ", details='" + details + '\'' + "\n" +
                ", ingredients='" + ingredients + '\'' + "\n" +
                '}';
    }


    /**
     * Static method which search Recipes by name and returns an ArrayList of these Recipes.
     *
     * @param keyword Title of the source.Recipe to search.
     * @return ArrayList of Recipes. Informations about these Recipes are not available due to performance reasons, you have to call {@link #loadInformations()} loadInformations} on the source.Recipe you want to have the informations.
     * @throws IOException
     */
    public static ArrayList<Recipe> search(String keyword) throws IOException {
        keyword.replaceAll(" ", "-");

        ArrayList<Recipe> resultRecipes = new ArrayList<>();

        Document document = Jsoup.connect("http://www.marmiton.org/recettes/recherche.aspx?aqt=" + keyword).get();

        Element elementResultsList = document.getElementsByClass("m_resultats_liste_recherche").first();
        Elements resultsElements = elementResultsList.getElementsByClass("recette_classique");

        for (Element e : resultsElements) {
            Elements currentRecipeElement = e.getElementsByAttribute("title");
            String title = currentRecipeElement.first().attr("title");
            String urlLink = "http://www.marmiton.org" + currentRecipeElement.first().attr("href");
            //System.out.println(titre+"\n"+lien+"\n\n");

            resultRecipes.add(new Recipe(title, urlLink));
        }

        return resultRecipes;
    }


}
