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

    public String getPseudo() {
        return pseudo;
    }

    public int getSolde() {
        return solde;
    }

    public void crediter(int montant) {
        solde += montant;
    }

    /**
     * Renvoie une offre pour le produit passé en paramètres avec un prix de base et
     * un prix maximal à ne pas dépasser. Si la création a pu avoir lieu, l'offre est
     * ajoutée à la liste des enchères du compte et le solde est mis à jour.
     *
     * @return l'offre d'enchère correspondante ou null si la création n'a pas pu avoir lieu
     */
    public OffreEnchere creerOffre(Produit produit, int prix, int prixMax) {
        OffreEnchere offre = new OffreEnchere(prix, prixMax, produit, this);
        if((produit.getOffreGagnante() != null && produit.getOffreGagnante().getCompte().pseudo != pseudo) ? solde >= prixMax + produit.getCoutParticipation()
                : solde + produit.getOffreGagnante().getPrixMax() >= prixMax + produit.getCoutParticipation()
            && prixMax >= prix && produit.verifierOffre(offre)) {
            solde -= prixMax + produit.getCoutParticipation();
            mesEncheres.add(offre);
            return offre;
        }
        return null;
    }
}
