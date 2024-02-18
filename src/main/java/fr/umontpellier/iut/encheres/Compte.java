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
        if((produit.getOffreGagnante() == null || (!produit.getOffreGagnante().hasMemeProprietaire(offre) ? solde >= prixMax + produit.getCoutParticipation()
                : prixMax == produit.getOffreGagnante().getPrixMax() && solde + produit.getOffreGagnante().getPrixMax() >= prixMax + produit.getCoutParticipation()))
            && solde >= prixMax + produit.getCoutParticipation() && prixMax >= prix && produit.verifierOffre(offre)) {
            solde -= prixMax + produit.getCoutParticipation();
            mesEncheres.add(offre);
            return offre;
        }
        return null;
    }

    public void supprimerOffre(OffreEnchere offre) {
        mesEncheres.remove(offre);
    }

    public void ajoutProduitAcheté(Produit produit) {
        produitsAchetés.add(produit);
    }

    public String toString() {
        String offres = "";
        for(OffreEnchere offre : mesEncheres)
            if(offre.getEtatGagnant())
                offres += "\n" + offre.toString();
        return "Compte '" + pseudo + "' avec un solde de " + solde + " possède " + produitsAchetés.size() + " produit(s)" + offres;
    }
}
