package fr.umontpellier.iut.encheres;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProduitTest {

    private Produit produit;
    private Compte compte;
    private OffreEnchere o;

    @BeforeEach
    public void initialisation() {
        produit = new Produit(0, "", 0, 152);
        compte = new Compte("toto", 59);
        o = new OffreEnchere(15, 20, produit, compte);
        produit.setPasEnchere(2);
        produit.demarrerEnchere();
    }

    @Disabled
    @Test
    void test_demmarrer_enchere(){
        assertTrue(produit.estDisponible());
    }

    @Disabled
    @Test
    void test_arreter_enchere(){
        produit.arreterEnchere();
        assertFalse(produit.estDisponible());
    }

    @Disabled
    @Test
    void test_verifier_offre_enchere_non_demarree(){
        Produit p = new Produit(0, "", 0, 2);
        OffreEnchere o = new OffreEnchere(10, 14, p, compte );
        assertFalse(p.verifierOffre(o));
    }

    @Disabled
    @Test
    void test_verifier_offre_quand_pas_d_offre_et_pas_enchere_non_respecte(){
        assertTrue(produit.verifierOffre(o));
    }

    @Disabled
    @Test
    void test_verifier_offre_quand_il_y_a_offre_et_pas_enchere_non_respecte(){
        produit.ajouterOffre(o);
        assertFalse(produit.verifierOffre(new OffreEnchere(16,900, produit,compte)));
    }

    @Disabled
    @Test
    void test_ajouterOffre_quand_c_est_la_premiere_offre() {
        produit.ajouterOffre(o);
        assertSame(produit.getOffreGagnante(), o);
    }

    @Disabled
    @Test
    public void test_verifierOffre_quand_il_y_a_offre_pas_enchere_non_respecte() {
        produit.ajouterOffre(o);
        OffreEnchere o2 = new OffreEnchere(16, 20, produit, compte);
        assertFalse(produit.verifierOffre(o2));
    }

}