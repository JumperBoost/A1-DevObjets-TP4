# ![](ressources/logo.jpeg)

# Développement Orienté Objets

### IUT Montpellier-Sète – Département Informatique

* [**Support de cours**](https://gitlabinfo.iutmontp.univ-montp2.fr/dev-objets/Ressources)
* **Enseignants:**
  [Malo Gasquet](mailto:malo.gasquet@umontpellier.fr),
  [Sophie Nabitz](mailto:sophie.nabitz@univ-avignon.fr),
  [Cyrille Nadal](mailto:cyrille.nadal@umontpellier.fr),
  [Victor Poupet](mailto:victor.poupet@umontpellier.fr),
  [Gilles Trombettoni](mailto:gilles.trombettoni@umontpellier.fr),
  [Petru Valicov](mailto:petru.valicov@umontpellier.fr)
* Le [forum Piazza](https://piazza.com/class/lrahb0patze3u4) de ce cours pour poser vos questions
* [Email](mailto:petru.valicov@umontpellier.fr) pour toute question concernant le cours.
* Le [sujet du TP](https://www.lirmm.fr/~pvalicov/Cours/dev-objets/TP4.pdf) en format .pdf téléchargeable et imprimable.

<!--Avant de démarrer le TP, vérifiez que vous n'avez pas atteint votre quota d'espace de stockage autorisé :

* placez-vous dans votre `$HOME` et utilisez les commandes suivantes :
    * `du -sh` pour voir combien d'espace vous avez déjà utilisé
    * `du -sh *` pour voir combien d'espace vous avez déjà utilisé pour chaque fichier (sans fichiers cachés)
    * `du -sch .[!.]* *` pour voir combien d'espace vous avez déjà utilisé pour chaque fichier, y compris les fichiers cachés
* Supprimez les fichiers inutiles.
* Pour éviter des problèmes durant vos TPs d'informatique, vous devriez toujours **garder 300-400 Mo d'espace libre**.
-->

### Consignes
- Sauf indication contraire, **tous** les attributs que vous allez déclarer dans ce TP (et dans les TPs qui suivent) doivent être privés (`private`).
- A priori, la plupart des méthodes devraient être déclarées publiques (`public`). Vous pouvez tout de même déclarer et utiliser des méthodes `private` du moment qu'elles vous sont utiles et que votre programme fonctionne correctement.
- Essayez de respecter les conventions de nommage *Java* (voir la fin des transparents du [cours](https://www.lirmm.fr/~pvalicov//Cours/dev-objets/Generalites_x4.pdf) ou disponibles sur le site d'Oracle).
- **Sauf indication contraire, vous ne devrez pas modifier la signature des méthodes et des attributs des classes qui vous sont proposées.**
- Quelques tests unitaires vous sont déjà données (ils sont annotés `@Disabled`). Cependant, ils ne suffisent pas pour tester l'intégralité des cas de figure du sujet. **Pensez à écrire suffisamment de tests unitaires pour les différentes méthodes implémentées**
- Date limite de rendu de votre code sur le dépôt GitLab : **dimanche 18 février à 23 h 00**.


## TP 4 : Systèmes d'enchères
#### _Thème : encapsulation, cycle de vie d'une application orientée objet_

Vous êtes chargé de développer le système d'enchères **iBaille**. Pour avoir une idée globale du système, voici le principe général de fonctionnement :

* des produits sont mis en vente avec un prix initial (le prix de base) ;
* des utilisateurs peuvent enchérir sur les produits jusqu'à ce que l'enchère soit arrêtée ;
* pour pouvoir participer les utilisateurs doivent payer un coût de participation (différent pour chaque produit) ; ce montant ne sera jamais remboursé -- bénéfice du site ;
* à la fin de la vente, l'utilisateur ayant proposé le prix le plus élevé, remporte le produit ;
* pour éviter des enchères inutiles (de 1 centime par exemple), le même pas d’enchère minimal est défini pour tous les produits vendus via **iBaille** ;
* afin d'éviter aux utilisateurs de devoir enchérir de nombreuses fois (lors d'un "duel" entre deux utilisateurs par exemple), lorsqu'un utilisateur propose un prix pour un produit, il propose également un prix maximal qu'il est prêt à débourser en cas d'enchère concurrente. Le détail de la détermination du gagnant et du prix final du produit seront donnés ci-dessous.

Un squelette du code vous est fourni. Ci-dessous le diagramme de classes correspondant :

<img style="max-width: 600px; height: auto; " src="//www.plantuml.com/plantuml/png/ZLDBRjmm3Dth5CAifXy32RehkaYG3eeksWwG4oWsPyB4eWIA3W8CUg2UenUhRDFmnpMGMHZ2UtplKQJc9ebZQNnjqZDITEoyfL11WcW_NLrDK7RqtABN6JLbxLAoHmvLWs8lnkPVOQdnmC5-jaYYK7Og6QDWe8pV2oETbOgChzy91LqzaqIN3bG-0iCCBaCZHyU29KQdVfCSJ34GFjIjw2646XmPqv4VSXFUCGEhxVvThda9M2dTShB9da6EkTFRKXgeCwcf0tUtR6jTyhCRom9Fa6LfEtCa5qBtb1E1VvrEcUg-mxJ2mkK564-uNQfZ6ZExac_kJ8waZvsAj0wIV1s63VkrZtTFQg4Zs-ZH_1wUqQpWCdaT1yhJvQJN7_4VxdM6WJZHp4axy_sfrtexnzvmlPx3wBfNpoxfFpUu2pxAfFS4JHMMxps5kW8D6UFlMtGHNZ8CMzE_sCVCzf_rLWP9XGAyHneRd3qOTLB7iIXVOhQ0dOUavsJVxmh6ywCKkzZBxFNEgeDvcz07ql5aQNyoHYtiO9jkiqx-_cc5NufYgBi1gXfV_mC0"  width=800 alt="diagramme de classes">

Vous pouvez également consulter le code source PlantUML dans le répertoire `ressources` de ce dépôt.

Remarquez que la classe principale `IBaille` (celle qui contient la méthode `main(String args[])`) n'apparaît pas dans le diagramme de classes qui vous est fourni. En effet, en pratique, on ne représente pas (ou on le fait très brièvement) la classe principale dans un diagramme de classes, car toute application orientée objet doit avoir une telle classe, qui constitue le point d'entrée du programme. De même les fonctions "_standard_" sont également omises dans le diagramme de classes. Par exemple `toString()`, le constructeur etc.

Prenez le temps de lire le diagramme et le squelette de code, car vous aurez à le compléter en y ajoutant des méthodes et des attributs qui vous paraissent nécessaires.

Également, quelques classes de tests unitaires vous sont fournies pour pouvoir démarrer.

1. Implémentez la méthode `void demarrerEnchere()` de `Produit` pour qu'elle rende l'objet disponible.

1. Quelque chose a été oublié dans la classe `Produit` : comme indiqué précédemment, le pas d'enchère doit être systématiquement le même pour tous les produits, mais modifiable par le client. Changez la déclaration de cet attribut afin de satisfaire cette contrainte. Doit-on modifier également les méthodes `void setPasEnchere()` et `int getPasEnchere()` ? Justifiez.

   **Remarque** : ne pas confondre la notion de _client du site_ (non-informaticien) et le client de l'application (informaticien) qui va se servir de votre code pour poursuivre son développement, pour le maintenir, le débugger, etc. Dans ce cours le client, c'est l'informaticien.

1. Complétez la classe `Compte` en y ajoutant une méthode qui permet de créditer le compte avec une somme donnée. Cette somme pourra éventuellement être négative, ce qui permettra alors de retirer de l'argent du compte.

1. La classe `OffreEnchere` représentera une enchère proposée par un utilisateur pour un produit donné. Certains de ses attributs et méthodes vous sont donnés. Ajoutez dans la classe `OffreEnchere` une méthode modifieur (_setter_) pour le prix en cours.

   **Remarque :** observez que dans le constructeur de cette classe, aucune vérification concernant la cohérence des attributs de l'offre créée avec ceux du produit n'a été faite (ce n'est pas la responsabilité de l'objet `OffreEnchere`).

   **Remarque :** observez également que par défaut l'offre est désignée comme perdante à travers un booléen.

1. Dans la classe `Produit`, ajoutez des attributs permettant respectivement de

    * stocker toutes les offres émises sur ce produit -- on vous conseille d'utiliser une structure de données de type liste prédéfinie en _Java_, comme `java.util.ArrayList` ou `java.util.LinkedList`, mais vous êtes libres d'utiliser d'autres solutions ;
    * stocker l'offre gagnante actuelle.

   Complétez la méthode `getOffreGagnante()`.

1. Au départ, lorsque le produit n'a reçu aucune offre d'enchère, son prix actuel correspond à son prix initial. Ensuite, le prix actuel va correspondre au prix en cours de l'enchère gagnante actuelle. Implémentez la méthode `int getPrixActuel()` de la classe `Produit`.

Étant donné un produit (ayant un prix en cours **c**) recevant une offre **o** (de prix courant **p<sub>o</sub>**, peu importe son prix maximal), on dit que **o** est **correcte** pour le produit si toutes les conditions suivantes sont respectées :

* le produit est ouvert aux enchères
* le produit de l'offre **o** est bien le même que le produit courant qui reçoit **o**
* si **o** est la première offre, alors il faut que **p<sub>o</sub>**  &ge; **c**
* si **o** n'est pas la première offre (dans ce cas **c** correspond au prix courant de l'offre actuellement gagnante), alors il faut que **p<sub>o</sub>  &ge; c + &delta;**, où **&delta;** est le pas d'enchère.

7. Implémentez la méthode `boolean verifierOffre(OffreEnchere offre)` de la classe `Produit`, qui vérifie si une offre est correcte.

Soient un compte **toto** (ayant un solde **s**), proposant une offre **o** (de prix courant **p<sub>o</sub>** et maximum **M<sub>o</sub>**) pour un produit (de coût de participation **c<sub>p</sub>**). On dit que **o** est **valide** si toutes les conditions suivantes sont respectées :

* **s &ge; M<sub>o</sub> + c<sub>p</sub>** si **toto** n'est pas le gagnant actuel
* **s + M<sub>g</sub>  &ge; M<sub>o</sub> + c<sub>p</sub>** si **toto** est le gagnant actuel et **M<sub>g</sub>** est le prix maximal de l'offre gagnante actuelle (voir la remarque ci-dessous)
* **M<sub>o</sub> &ge; p<sub>o</sub>**
* **o** est une offre correcte pour le produit

  <a name="remGagnantActuel"></a> **Remarque :**
  Il se peut qu'un utilisateur détenant l'offre gagnante actuelle du produit, souhaite enchérir une nouvelle fois. Par exemple, cela peut arriver sur un coup de stress : la crainte de perdre devient trop forte ! Ce qui explique la présence de la seconde inégalité ci-dessus.

8. Écrivez le code de la méthode `public OffreEnchere creerOffre(Produit produit, int prix, int prixMax)` de la classe `Compte` qui, à partir de ses paramètres, instancie et retourne une offre si celle-ci est **valide**. Également, si l'offre est valide, la méthode devra débiter le compte de `prixMax` + le coût de participation du produit. Enfin, l'offre valide devra être stockée dans la liste des enchères du compte. La méthode doit retourner `null` si l'offre n'est pas valide.


Passons maintenant à la gestion des coûts liés à la création d'offres. Comme vous l'avez remarqué dans la question précédente, dès qu'un compte
crée une offre valide, alors le compte est directement débité de **M<sub>o</sub>**+**c<sub>p</sub>**. L'idée derrière ce débit immédiat est de s'assurer qu'un compte
qui proposerait des offres sur de nombreux produits en parallèle aurait de quoi toutes les payer s'il était vainqueur sur tous ces produits.
Par conséquent, si une offre s'avère perdante, alors il faut rembourser **M<sub>o</sub>** sur le compte correspondant (sinon, c'est du vol !). En revanche, si l'offre est déclarée gagnante, alors il faut rembourser la différence entre **M<sub>o</sub>** et le prix du produit au moment de la clôture de l'enchère.

9. Implémentez la méthode `setEtatGagnant(boolean etat)` de la classe `OffreEnchere`. On utilisera cette méthode pour faire basculer une enchère à un état (gagnante ou perdante). Elle effectuera le bon remboursement du compte en cas de passage à l'état perdant.

Nous allons maintenant implémenter la méthode la plus importante, qui va gérer la concurrence entre plusieurs offres valides pour un produit fixé.
Voici les règles permettant de déterminer si une nouvelle offre valide est gagnante ou non, et de fixer la nouvelle valeur du prix courant.

Considérons un produit pour lequel arrive une nouvelle offre **o2** (supposée valide), de prix courant **p<sub>o2</sub>** et maximum **M<sub>o2</sub>**. 

* Si aucune enchère n'a encore été déposée sur ce produit, alors la nouvelle offre est désignée comme gagnante.
* Sinon, notons respectivement **p<sub>o1</sub>** et **M<sub>o1</sub>** le prix courant et le maximum de l'offre gagnante actuelle. On distingue deux cas :
  * Si le compte du gagnant actuel (détenant l'offre **o1**) est différent de celui proposant l'offre **o2** :
     *  si **M<sub>o1</sub> &ge; M<sub>o2</sub>**, alors le gagnant ne change pas et la valeur **p<sub>o1</sub>** est actualisée à **M<sub>o2</sub>** ;
     * si **M<sub>o1</sub> < M<sub>o2</sub>**, alors la nouvelle enchère sera désignée comme gagnante,  et la valeur **p<sub>o2</sub>** est actualisée à **max(M<sub>o1</sub>, p<sub>o2</sub>)**.
  * Si le compte du gagnant actuel (détenant l'offre **o1**) est le même que celui proposant l'offre **o2** :
     * si **M<sub>o1</sub> &ge; M<sub>o2</sub>**, alors l'offre gagnante reste la même, mais son maximum **M<sub>o1</sub>** est actualisée à **M<sub>o2</sub>** et la valeur **p<sub>o1</sub>** est actualisée à **p<sub>o2</sub>** ;
     * si **M<sub>o1</sub> < M<sub>o2</sub>**, alors la nouvelle offre d'enchère sera désignée comme gagnante et **p<sub>o2</sub>** et **M<sub>o2</sub>** resteront inchangés.
     
     En quelque sorte, en surenchérissant sur soi-même le gagnant actuel peut ajuster à la fois le prix courant et le prix maximal qu'il est prêt à payer (mais chaque ajustement lui coûte le montant de la participation tout de même..).


<!-- On rappelle qu'un utilisateur peut déposer une nouvelle offre d'enchère sur le même produit sur lequel il a déjà déposé une offre d'enchère. Par exemple, il pourra le faire si son offre a été "battue" par un autre enchérisseur. Ou encore s'il est dans le cas de la situation décrite dans la [Remarque précédant la question 8](#remGagnantActuel). -->

10. Supposons que pour un produit fixé, on ait une première offre **o1=(10,20)** (la notation signifie que **p<sub>o1</sub>=10** et **M<sub>o1</sub>=20**), avec un pas d'enchère de **2**. Imaginons que les offres ci-dessous sont ensuite, faites pour ce produit :
    * **o2=(11,25)**, avec enchérisseur différent de celui de **o1**
    * **o3=(12,15)**, avec enchérisseur différent de celui de **o2**
    * **o4=(16,30)**, avec enchérisseur différent de celui de **o3**
    * **o5=(25,30)**, avec enchérisseur différent de celui de **o4**
    * **o6=(27,35)**, avec enchérisseur différent de celui de **o5**
    * **o7=(32,74)**, avec le même enchérisseur que celui de **o6**

    Indiquez (sur papier) après chaque offre, quelle est l'offre actuellement gagnante, ainsi que le prix courant de l'objet.

11. Implémentez la méthode `void ajouterOffre(OffreEnchere o)` de la classe `Produit` qui, étant donné une nouvelle offre **o** (supposée valide, et pour le même produit), effectue les actions suivantes :
    * ajoute **o** à la liste d'offres d'enchères du produit ;
    * met à jour l'offre gagnante actuelle sur le produit (en déterminant si **o** est gagnante ou non, selon les règles ci-dessus) ;
    * change correctement l'état des offres en concurrence en "gagnante" ou "perdante", tout en déclenchant le remboursement du compte perdant (utiliser la méthode `setEtatGagnant(boolean etat)`).

**Remarque :** nul besoin de vérifier ici si l'offre est valide, à l'utilisation de la méthode `void ajouterOffre(OffreEnchere o)` on suppose l'offre  **o** comme étant valide.

**Remarque :** vous pouvez ajouter des méthodes auxiliaires qui vous paraissent nécessaires.

12. Implémentez la méthode `void arreterEnchere()`, qui rendra l'objet indisponible et invoquera le remboursement du compte lié à l'offre gagnante **o** de **M<sub>o</sub>** - **c**, où **c** est le prix courant de l'objet (qui correspond donc au prix auquel l'objet va partir au moment de la clôture). À la fin de cette fonction, le compte de l'offre gagnante devra avoir le bon solde et le produit remporté dans sa liste `produitsAchetés`.

    **Remarque** : pour réaliser cette fonction, vous serez certainement amenés à ajouter des nouvelles méthodes (ou attributs) dans certaines classes ; c'est à vous de décider ce qui est le mieux pour votre application. Vous pouvez en discuter avec votre enseignant.

Les enchères seront ouvertes et clôturées sur appel explicite de `demarrerEnchere()` et `arreterEnchere()`. On supposera qu'une fois clôturée, une enchère ne sera jamais réouverte.

13. Écrivez la méthode `toString()` appropriée dans la classe `Compte`. Libre à vous de décider les informations à retourner, mais en ce qui concerne les offres du compte, seules les offres gagnantes actuelles du compte devraient être affichées.


14. Écrivez la méthode `toString()` appropriée dans la classe `Produit`. Parmi les différentes offres déposées, seule l'offre gagnante actuelle devrait être affichée.


15. Simulez votre application dans le programme principal (la classe `IBaille`). Pour cela, vous instancierez un produit et plusieurs comptes (3 au minimum). Pour chacun des comptes vous proposerez à l'utilisateur du logiciel (non-informaticien donc) de déposer des enchères pour ce produit en affichant les informations sur le produit et l'offre gagnante en cours.

    Pour récupérer les données saisies par l'utilisateur à la console, vous pouvez vous servir de la classe `java.util.Scanner` qui permet de "parser" de manière intelligente une chaîne de caractères. Voici un petit exemple de ce que vous pouvez faire avec :

    ```java

    import java.util.Scanner;

    class IBaille {

        public static void main(String[] args) {

            // on attache un objet Scanner au flux d'entrée associée à la console
            Scanner saisie = new Scanner(System.in);
            System.out.println("Veuillez écrire quelque chose :");

            // récupération de la chaîne de caractères saisie par l'utilisateur
            String réponse = saisie.next();

            // récupération de la chaîne de caractères saisie par l'utilisateur sous forme d'un nombre entier
            int entier = saisie.nextInt();

        }

    }
    ```
    Pour plus de détails sur cette classe, voir l'API : https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Scanner.html


16. En fonction de votre réalisation, raffinez le modèle du diagramme de classes qui vous a été donné au début du sujet en y apportant les modifications nécessaires.

    **Rappel :** le code source PlantUML est dans le répertoire `ressources` de votre dépôt.
