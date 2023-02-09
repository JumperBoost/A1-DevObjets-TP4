package fr.umontpellier.iut.encheres;

import java.util.ArrayList;

public class Compte {
    private String pseudo;
    private int solde;
    private ArrayList<OffreEnchere> mesEncheres;
    private ArrayList<Produit> produitsAchetés;

    public Compte(String pseudo, int solde) {
        this.pseudo = pseudo;
        this.solde = solde;
        mesEncheres = new ArrayList<>();
        produitsAchetés = new ArrayList<>();
    }

    public int getSolde() {
        return solde;
    }

    /**
     * Renvoie une offre pour le produit passé en paramètres avec un prix de base et
     * un prix maximal à ne pas dépasser. Si la création a pu avoir lieu, l'offre est
     * ajoutée à la liste des enchères du compte et le solde est mis à jour.
     *
     * @return l'offre d'enchère correspondante ou null si la création n'a pas pu avoir lieu
     */
    public OffreEnchere creerOffre(Produit produit, int prix, int prixMax) {
        throw new RuntimeException("Méthode non implémentée ! Effacez cette ligne et écrivez le code nécessaire");
    }
}
