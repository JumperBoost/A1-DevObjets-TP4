package fr.umontpellier.iut.encheres;

public class OffreEnchere {
    private int prixEnCours;
    private int prixMax;
    private final Produit produit;
    private boolean etatGagnant;
    private final Compte monCompte;

    public OffreEnchere(int prixEnCours, int prixMax, Produit produit, Compte monCompte) {
        this.prixEnCours = prixEnCours;
        this.prixMax = prixMax;
        this.produit = produit;
        etatGagnant = false;
        this.monCompte = monCompte;
    }

    public int getPrixEnCours() {
        return prixEnCours;
    }

    public void setPrixEnCours(int prix) {
        prixEnCours = prix;
    }

    public int getPrixMax() {
        return prixMax;
    }

    public void setPrixMax(int prix) {
        monCompte.crediter(prixMax - prix);
        prixMax = prix;
    }

    public Produit getProduit() {
        return produit;
    }

    public boolean hasMemeProprietaire(OffreEnchere offreEnchere) {
        return monCompte.getPseudo().equals(offreEnchere.monCompte.getPseudo());
    }

    public boolean getEtatGagnant() {
        return etatGagnant;
    }

    public void setEtatGagnant(boolean etat) {
        if(!etat)
            monCompte.crediter(prixMax);
        etatGagnant = etat;
    }

    public void finaliser() {
        if(etatGagnant) {
            monCompte.crediter(prixMax - prixEnCours);
            monCompte.ajouterProduit(produit);
        }
        monCompte.supprimerOffre(this);
    }

    @Override
    public String toString() {
        return "OffreEnchere{" +
                "prixEnCours=" + prixEnCours +
                ", prixMax=" + prixMax +
                ", produit=" + produit.getNumero() +
                ", encherisseur=" + monCompte.getPseudo() +
                ", est gagnante  ? -> " + etatGagnant +
                '}';
    }
}
