Il existe 2 manières de lancer le programme:


	1-POUR LANCER LE PROGRAMME VIA LE .JAR:
	Il faut se situer dans le répertoire du projet.
	Une fois qu'on y est, on écrit : java -jar Programme.jar dans le terminal et cela lance le logiciel.


	2-POUR LANCER LE PROGRAMME VIA UN IDE :

	Pour pouvoir afficher les statistiques il faut installer rJava ainsi que JAVAGD.
	Pour ce faire, il faut lancer R et écrire les commandes suivantes dans la console: 
	- install.packages("rJava")
	- install.packages("JavaGD")
	IL se peut qu'un conflit se crée et que les librairies ne s'ajoutent pas. Dans ce cas là, il faut ouvrir le terminal de linux et écrire la commande suivante : $ R CMD javareconf après s'être authentifier en administrateur (sudo). Après cela, relancez R et écrivez de nouveau les deux install packages, cela devrait fonctionner.

	Une fois que cela est fait, il faut ensuite configurer l'IDE:
	Avec Eclipse :
	- Clique droit sur le projet Questionnaire -> Build Path -> Add External Archives et ici sélectionner les 4.jar présent dans le dossier librairie (dans le dossier source du projet)
	- Il faut ensuite configurer les options de lancements, pour cela 2 choses à faire :
	- Cliquez sur l'onglet Run -> Run Configurations. Dans la fenetre qui vient de s'ouvrir, aller dans l'onglet Arguments et y inscrire la ligne suivante dans VM Arguments 
	- "-Djava.library.path=.:/usr/lib/R/site-library/rJava/jri/"  (sans les guillemets)
	- Ici, nous donnons le chemin d'accès pour JRI, il est donc à noter que le chemin pour y accéder peut varier d'un ordinateur à un autre
	- Pour finir, toujours dans cette même fenetre, aller dans l'onglet Environment et Cliquer sur New: Inscrivez R_HOME dans le Name et dans Value le chemin pour y accéder (par défaut /usr/lib/R).

	Vous êtes maintenant prêt à lancer le programme pour cela compiler et lancer le fichier Menu.java. 
