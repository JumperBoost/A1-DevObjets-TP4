package fr.umontpellier.iut.encheres;

import java.util.Objects;

public class Produit {
    private final int numero;
    private String description;
    private final int prixInitial;
    private int pasEnchere;
    private int coutParticipation;

    private boolean disponible;

    public Produit(int numero, String description, int prixInitial, int coutParticipation) {
        this.description = description;
        this.prixInitial = prixInitial;
        this.coutParticipation = coutParticipation;
        this.numero = numero;
        disponible = false;
    }

    public int getNumero() {
        return numero;
    }

    // question 1
    public int getPasEnchere() {
        return pasEnchere;
    }

    // question 1
    public void setPasEnchere(int pas) {
        pasEnchere = pas;
    }

    public void demarrerEnchere() {
        throw new RuntimeException("Méthode non implémentée ! Effacez cette ligne et écrivez le code nécessaire");
    }

    public void arreterEnchere() {
        throw new RuntimeException("Méthode non implémentée ! Effacez cette ligne et écrivez le code nécessaire");
    }

    // question 5
    public int getPrixActuel(){
        throw new RuntimeException("Méthode non implémentée ! Effacez cette ligne et écrivez le code nécessaire");
    }

    // pré-requis : l'offre passée en paramètre est valide
    public void ajouterOffre(OffreEnchere o) {
        throw new RuntimeException("Méthode non implémentée ! Effacez cette ligne et écrivez le code nécessaire");
    }

    public int getCoutParticipation() {
        return coutParticipation;
    }

    public OffreEnchere getOffreGagnante() {
        throw new RuntimeException("Méthode non implémentée ! Effacez cette ligne et écrivez le code nécessaire");
    }

    public boolean estDisponible() {
        return disponible;
    }

    // vérifie si l'offre est correcte
    public boolean verifierOffre(OffreEnchere offre) {
        throw new RuntimeException("Méthode non implémentée ! Effacez cette ligne et écrivez le code nécessaire");
    }


    // fonction permettant la comparaison des Produits
    // cette fonction doit rester intacte
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produit produit)) return false;
        return getNumero() == produit.getNumero();
    }

    // fonction auxiliaire définissant le hashCode des objets de type Produit en respectant le contrat de equals(Object o)
    // cette fonction doit rester intacte
    @Override
    public int hashCode() {
        return Objects.hash(getNumero());
    }
}
