package fr.umontpellier.iut.encheres;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class IBaille {

    public static void main(String[] args) {
        if(args.length > 0)
            return;

        Produit.setPasEnchere(500);
        Produit iPhone1 = new Produit(1, "Le 1er iPhone créé, délivré et signé par Steve Jobs lui-même, sous blister", 30000, 300);
        Compte tim = new Compte("Tim", 840750);
        Compte stevenn = new Compte("Stevenn", 347500);
        Compte paul = new Compte("Paul", 616999);
        ArrayList<Compte> encherisseurs = new ArrayList<>(Arrays.asList(tim, stevenn, paul));

        iPhone1.demarrerEnchere();
        ArrayList<Compte> r_encherisseurs = new ArrayList<>();
        do {
            r_encherisseurs.clear();
            for (Compte encherisseur : encherisseurs) {
                System.out.println("Tour du compte '" + encherisseur.getPseudo() + "' :");
                if (encherisseur.getSolde() >= iPhone1.getPrixActuel() + iPhone1.getCoutParticipation() + Produit.getPasEnchere()) {
                    int maxSolde = encherisseur.getSolde() - iPhone1.getCoutParticipation();
                    int minSolde = iPhone1.getPrixActuel() + Produit.getPasEnchere();
                    Scanner scanner = new Scanner(System.in);

                    System.out.println("Le prix actuel du produit est de " + iPhone1.getPrixActuel() + ".");
                    System.out.println("Vous possedez un solde de " + encherisseur.getSolde() + ". Vous pouvez encherir au maximum " + maxSolde + " (min: " + minSolde + ").");
                    System.out.print("Souhaitez-vous encherir maintenant ? (O pour oui, N pour non, A pour abandonner): ");
                    char response = scanner.next().charAt(0);
                    if (response == 'A') {
                        r_encherisseurs.add(encherisseur);
                        System.out.println("Vous n'encherissez plus ce produit.");
                    } else if (response == 'O') {
                        int solde;
                        int soldeSeuil;
                        OffreEnchere offre;
                        do {
                            System.out.print("Veuillez saisir un montant maximum (seuil) valide à encherir: ");
                            soldeSeuil = scanner.nextInt();
                            System.out.print("Veuillez saisir un montant valide à encherir: ");
                            solde = scanner.nextInt();
                            offre = encherisseur.creerOffre(iPhone1, solde, soldeSeuil);
                        } while (offre == null);
                        System.out.println("Vous venez d'encherir " + solde + " sur ce produit.");
                        iPhone1.ajouterOffre(offre);
                    }
                } else {
                    System.out.println("Vous n'avez pas assez pour encherir.");
                    r_encherisseurs.add(encherisseur);
                }
            }
            encherisseurs.removeAll(r_encherisseurs);
        } while (encherisseurs.size() > 1);
        iPhone1.arreterEnchere();
        System.out.println(iPhone1);
    }
}
