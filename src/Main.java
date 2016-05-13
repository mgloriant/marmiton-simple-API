import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Saisir un terme de recherche : ");
        Scanner sc = new Scanner(System.in);
        String recherche = sc.nextLine();


        recherche.replaceAll(" ","-");


        Document doc = null;
        try {

            ArrayList<RemoteRecette>recettes = new ArrayList<>();

            doc = Jsoup.connect("http://www.marmiton.org/recettes/recherche.aspx?aqt="+recherche).get();

            Element listeResultats = doc.getElementsByClass("m_resultats_liste_recherche").first();
            Elements resultats = listeResultats.getElementsByClass("recette_classique");



            for (Element e:resultats) {
                Elements courant = e.getElementsByAttribute("title");
                String titre = courant.first().attr("title");
                String lien = "http://www.marmiton.org"+courant.first().attr("href");
                //System.out.println(titre+"\n"+lien+"\n\n");

                recettes.add(new RemoteRecette(titre, lien));

            }

            int i = 0;
            for (RemoteRecette r : recettes) {
                System.out.println(i+") "+r.getLibelle());
                i++;
            }

            System.out.println("Choisir une recette : ");
            Scanner sc2 = new Scanner(System.in);
            int indice = sc.nextInt();


                RemoteRecette recetteChoisie = recettes.get(indice);

            System.out.println(recetteChoisie.getLibelle());
            recetteChoisie.chargerInfosRecette();
            System.out.println(recetteChoisie);


            //System.out.println("\n"+doc.title());
            //System.out.println(recettes);


        } catch (IOException e) {
            System.out.println("Probleme lors de la recherhche de recettes !");
        }






    }
}
