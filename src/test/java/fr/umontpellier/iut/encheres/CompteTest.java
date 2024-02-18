package fr.umontpellier.iut.encheres;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompteTest {

    private Compte compte;
    private Produit produit;

    @BeforeEach
    public void init(){
        compte = new Compte("toto", 100);
        produit = new Produit(0, "", 0, 152);
        Produit.setPasEnchere(20);
        produit.demarrerEnchere();
    }

    @Test
    public void test_creer_offre_prix_superieur_au_prix_max(){
        OffreEnchere o = compte.creerOffre(produit,50,40);
        assertNull(o);
    }

    @Test
    public void test_creer_offre_solde_insuffisant_pour_prix_max_plus_cout_participation(){
        OffreEnchere o = compte.creerOffre(produit,50,100);
        assertNull(o);
    }

    // complétez ici avec vos tests
    @Test
    public void test_afficher_compte() {
        compte.crediter(300);
        OffreEnchere o = compte.creerOffre(produit, 100, 200);
        assertNotNull(o);
        produit.ajouterOffre(o);
        produit.arreterEnchere();
        assertEquals(o.toString(), "OffreEnchere{prixEnCours=100, prixMax=200, produit=0, encherisseur=toto, est gagnante  ? -> true}");
    }
}