package fr.umontpellier.iut.encheres;

import java.util.ArrayList;
import java.util.Objects;

public class Produit {
    private final int numero;
    private String description;
    private final int prixInitial;
    private static int pasEnchere;
    private int coutParticipation;

    private boolean disponible;

    private final ArrayList<OffreEnchere> offresEncheres;
    private OffreEnchere offreGagnante;

    public Produit(int numero, String description, int prixInitial, int coutParticipation) {
        this.description = description;
        this.prixInitial = prixInitial;
        this.coutParticipation = coutParticipation;
        this.numero = numero;
        disponible = false;
        offresEncheres = new ArrayList<>();
    }

    public int getNumero() {
        return numero;
    }

    // question 2
    public static int getPasEnchere() {
        return pasEnchere;
    }

    // question 2
    public static void setPasEnchere(int pas) {
        pasEnchere = pas;
    }

    public void demarrerEnchere() {
        disponible = true;
    }

    public void arreterEnchere() {
        disponible = false;
        for(OffreEnchere offre : offresEncheres) {
            offre.finaliser();
        }
    }

    // question 5
    public int getPrixActuel(){
        return offreGagnante != null ? offreGagnante.getPrixEnCours() : prixInitial;
    }

    // pré-requis : l'offre passée en paramètre est valide
    public void ajouterOffre(OffreEnchere o) {
        offresEncheres.add(o);
        if(offreGagnante != null) {
            if(!offreGagnante.hasMemeProprietaire(o)) {
                if(offreGagnante.getPrixMax() >= o.getPrixMax()) {
                    offreGagnante.setPrixEnCours(o.getPrixMax());
                    o.setEtatGagnant(false);
                } else {
                    o.setPrixEnCours(Math.max(offreGagnante.getPrixMax(), o.getPrixEnCours()));
                    offreGagnante.setEtatGagnant(false);
                    o.setEtatGagnant(true);
                    offreGagnante = o;
                }
            } else {
                if(offreGagnante.getPrixMax() >= o.getPrixMax()) {
                    offreGagnante.setPrixMax(o.getPrixMax());
                    offreGagnante.setPrixEnCours(o.getPrixEnCours());
                    o.setEtatGagnant(false);
                } else {
                    offreGagnante.setEtatGagnant(false);
                    o.setEtatGagnant(true);
                    offreGagnante = o;
                }
            }
        } else {
            o.setEtatGagnant(true);
            offreGagnante = o;
        }
    }

    public int getCoutParticipation() {
        return coutParticipation;
    }

    public OffreEnchere getOffreGagnante() {
        return offreGagnante;
    }

    public boolean estDisponible() {
        return disponible;
    }

    // vérifie si l'offre est correcte
    public boolean verifierOffre(OffreEnchere offre) {
        return disponible && equals(offre.getProduit()) && offre.getPrixMax() >= offre.getPrixEnCours()
                && (offreGagnante == null ? offre.getPrixEnCours() >= prixInitial
                    : offre.getPrixEnCours() >= offreGagnante.getPrixEnCours() + pasEnchere);
    }


    // fonction permettant la comparaison des Produits
    // cette fonction doit rester intacte
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produit produit)) return false;
        return getNumero() == produit.getNumero();
    }

    public String toString() {
        return "Produit{num=" + numero + ", desc='" + description + "', prixInitial=" + prixInitial + ", coutParticip=" +
                coutParticipation + ", dispo=" + disponible + (offreGagnante != null ? ", gagnantInfo=" + offreGagnante.toString() : "") + "}";
    }

    // fonction auxiliaire définissant le hashCode des objets de type Produit en respectant le contrat de equals(Object o)
    // cette fonction doit rester intacte
    @Override
    public int hashCode() {
        return Objects.hash(getNumero());
    }
}
