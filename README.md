_Gestion d’une bibliothèque_
--------

Cette application a pour objectif le suivi et la gestion des prêts des ouvrages pour les usagers, de ce fait le lecteur peut:

* s’inscrire et s’authentifier pour certaines actions,

* rechercher un ouvrage et voir le nombre d’exemplaire disponible,

* suivre les prêts en cours,

* prolonger un prêt,

* voir le détail des ouvrages,

_Technologies utilisées :_
-----

* Application web réalisé en **Java/JEE (JDK 8)**

* Serveur **GlassFish 5.0**

* Base de données **PostGreSQL 9.2**

* Packagée (WAR) avec **Maven**

_Outils_
----

* **Eclipse**
* **Papyrus**
* **SQL Workbench**
* **SQL Power Architect**

_Intallation des outils_
----

**Pour SQL Workbench il faut:**

* installer **SQL Workbench** : télécharger et lancer le fichier SQLWorkbench.exe.

* une fois l’interface lancée : aller à **file** puis **connect window**, une fenêtre s’ouvre renseigner le driver(org.postgresql.Driver.jar), l’url (jdbc:postgresql://192.168.99.100:32768/bibliotheque), le userName (khady) et enfin le mot de passe(0302) et la base de sonnée est maintenant disponible 

**Pour Java 8 (JDK 8) il faut:**

* télécharger et installer le kit de développement Java 8 (JDK 8) dans le site d’oracle : une fois votre jdk téléchargé et installé, il faut ajouter ce répertoire __C:\Program Files\Java\jdk1.8.0_101\bin__ dans votre variable d’environnement pour que toutes les commandes de java puisque être reconnu, enfin pour vérifier l’installation ouvrir votre invite de commande et taper **javac** ou  **java version** ou  **java**

**Pour Eclipse il faut:**

*  installer un environnement de développement (**IDE**) comme **Eclipse** 

**Pour GlassFish il faut:**

* installer ** GlassFish ** dans votre machine: télécharger et dézipper GlassFish dans un répertoire de votre machine puis se placer dans le dossier **bin** et lancer la commande  **asadmin start-domain domain1** et dans le navigateur taper **http://localhost:4848/**

* configurer **GlassFish** dans votre **IDE** : depuis l’interface de votre IDE faire **new** puis **other** et choisir **server**, **next** choisir la version installée dans votre machine **finish** et pour le démarrer **windows**, **show view** et choisir **server** et il apparaitra dans l’ide ou vous pouvez le démarrer l’arrêter, le supprimer…

**Pour Maven il faut:**

* installer **Maven** dans votre machine: télécharger et dézipper maven dans un répertoire de votre machine, ajouter la variable d’environnement utilisateur avec pour valeur le chemin du répertoire et lancer une nouvelle commande MS-DOS et exécuter ** mvn version** pour vérifier l’installation.
 
**Pour le déploiement du projet il faut**

**Par téléchargement**

* télécharger le projet depuis **Github** à l’url https://github.com/diarraSokhna/Biblio

* dézipper le projet qui contient deux dossiers : **bibliotheque** et **documentation**

**Par clonage**

* se placer sur un dossier et faire git clone https://github.com/diarraSokhna/Biblio

**Avec un IDE**

* dans votre IDE , faire importer ‘un projet maven existant’ puis sélectionner le dossier **bibliotheque** et suivre les instructions. 

* cliquer droit sur le projet **Run as** puis **maven package**

* lancer le module **webAppliBiblio** sur le serveur puis entrer dans le navigateur l'url _ http://localhost:8080/webAppliBiblio/_ et enjoy!!!

**Sans IDE**

* se positionner dans le dossier bibliotheque et exécuter la commande **mvn package** : un fichier war sera généré 

* aller à **http://localhost:4848/** sur **Applications** puis **Deploy** une nouvelle fénêtre s’ouvre  choisir le **war généré** et **OK**, pour lncer l’application cliquer sur **launch** puis sur **le premier lien** and Injoy !!!!

