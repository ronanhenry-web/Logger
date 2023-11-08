
# TP Java avancé


## Organisation

Les groupes de travail sont conseillés mais ne doivent se composer au plus de 3 personnes.

Les sources et le fichier credits.md du TP seront à déposer sous pepal à 17h05 dernier délai sous la forme d'un fichier zip.

Un seul compte-rendu est attendu par groupe.

**Remarque : Vous devez évaluer le module avant que les notes ne vous soient communiquées.**


## Création du projet (1pt)

Créer un projet Java (avec un JDK-17 ou plus récent).
L'ensemble des sources devra se trouver dans le package sdv.java.m1.tp

Ajouter à la racine de votre projet un fichier credits.txt qui contiendra une ligne par collaborateur en indiquant son nom et son prénom.


## Les services

### Ecriture des logs (3pts)

Créer une classe sdv.java.m1.tp.service.Traceur munie d'une méthode publique **tracer** qui permet d'ajouter à la fin du fichier traces.txt (à la racine du projet) les différentes actions.

La méthode devra prendre en paramètre :
   - un niveau de criticité 
   - la chaine de caractères qui correspond à la description (chaine de caractères).

Elle tracera ces informations ainsi que la date et l'heure courante en UTC et en respectant le format "jour/mois/année heures:minutes:secondes".

Utiliser la classe DateTimeFormatter pour gérer le formattage de la date+heure.

La criticité est symbolisée par les valeurs suivantes et constitueront l'énumération sdv.java.m1.tp.enums.CriticiteAction :
 - ERREUR
 - DANGER
 - INFO
 - TRACE

Exemple de ligne attendue :  
`ERREUR 07/11/2023 12:30:00 Fermeture inatendue de l'application`

**N'oublier pas le saut de ligne.**

**Le fichier s'écrit avec l'encodage UTF-8 et utiliser le fin de ligne UNIX (\n).**


### Lecture des logs (1pts)

Ajouter à la classe sdv.java.m1.tp.service.Traceur une méthode publique **lire** qui permet de retourner la liste de lignes contenues dans le fichier traces.txt.
Cette méthode ne prend pas de paramètre.

En cas d'erreur, le retour de la méthode doit être une liste vide. 


### Filrage (2pts)

Créer une classe Java Bean sdv.java.m1.tp.bean.CritereFiltrage qui permettra de stocker les critères de filtrage des lignes issues du fichier traces.txt
Ces critères sont les suivants :
- criticites : la liste des criticités autorisées (un niveau TRACE ne permet pas de remonter les lignes de niveau ERREUR),
- sousChaine: la chaine de caractères que doit contenir une ligne (ce critère ne doit pas être sensible à la bascule minuscule/majuscule).

Créer le service de filtrage sdv.java.m1.tp.service.Filtrage qui définit la méthode 


## L'interface graphique (3pt)

Créer une classe sdv.java.m1.tp.ihm.Fenetre qui étend la classe JFrame.
Cette classe correspond à une fenêtre de dimension 800x600 pxiels.
Elle se ferme et arrête l'application lorsque l'on clique sur la croix rouge.
(1pt)


Ajouter à cette fenêtre les éléments suivants :
 - Un bouton **Actualiser** : ce bouton permettra la lecture du fichier traces.txt en utilisant la méthode lire du service Traceur
 - Un JTextArea élément central qui permet d'afficher le résultat de la lecture du fichier suite au clic sur le bouton Actualiser
 - Un JTextField qui permet de saisir une chaine de caractères (à rechercher dans chaque ligne)
 - Un bouton **Filtrer** qui permet d'appliquer le filtre de la sous-chaîne en utilisant la méthode filtrer du service Filtrage.

Le JTextArea doit être encapsulé dans un JScrollPane pour permettre l'affichage du texte quelque soit le nombre de lignes qu'il contient. 

Ajout des éléments (1pt).


Pour les boutons, l'usage des expressions lambdas ou des références de méthodes est fortement conseillé.
Les boutons **Filter** et **Actualiser** doivent mettre à jour le contenu du JTextArea.

Câblage des éléments (1pt).


## Bonus

Ces points permettent de réhausser la note du TP voire celle du QCM.

## Bonus - Swing (+1 pt)

Mettre en place le mécanisme de filtrage pour la criticité.
La criticité peut être représentée par des cases à cocher ou une JList (plusieurs niveaux peuvent être simultanément choisis).


## Bonus - Parallélisation (+1 pt)

Proposer une écriture du fichier traces.txt en utilisant un mécanisme de file d'attente de l'écriture.
Les messages seront stockés et dépilés depuis cette même file (LinkedList par exemple).
Seul un Thread sera responsable de l'écriture dans le fifchier.
