# Open Classrooms Projet12 Badj (association)
 Voici un système de gestion de dêpots-ventes d'articles (version 1.0.1):
 
### Techniques utilisés pour ce projet:
_Ce projet se compose de deux applications et d'une base de donnée:_
* Application Web crée avec le framework Angular en version 8 (typeScript).
    * Le site est responsive (pensé pour être utilisé facilement sur un petit écran tactile)
* API web en REST (microserviceBdd) (java, jdk8) connectée à la base de données
Mysql.
    * Les dépendances sont gérés avec Maven (version 4.0.0).
    * L'architecture est multi-modules (business, dao, model et web).
    * L'API web est couverte à 85% par des tests d'intégrations ou unitaires.
    * Intégration continue (Travis CI et SonarCloud) liée au github du projet.
* Base de donnée mySql (version 8), dans un conteneur Docker.
 
## Trois types de ventes(Bourses):
_Deux bourses de printemps:_
* Vêtements enfants
* Vêtements adultes

_Deux bourses d'Automne:_
* Vêtements enfants
* Vêtements adultes

_Une bourse de noël:_
* Jouets Livres Cadeaux bijoux

_Pour chaque prochaine bourse:_
### Les fonctions (client vendeur):
* Le client vendeur une fois inscrit dans le système peut pré-enregistrer des articles dans
les prochaines bourses. (Le nombre d'articles est limité)
* Il dispose d'un espace personnel avec tous les articles qu'il a pré-enregistré.
* Dans cette espace il peut supprimer un article qu'il ne veut plus vendre.
* Il peut modifier ses données personnelles (nom, prenom, email, password...etc)

### Les Fonctions pour les benévoles de l'association:
Les bénévoles de l'association ont les mêmes fonctions qu'un client vendeur plus:<br>

**1ere Etape - La Validation et la création d'articles-**<br>
_Le premier jour de la bourse le client vendeur amène ses articles (pré-enregistré ou non)._
* Le bénévole doit vérifier que l'article correspond bien à la description du pré-enregistrement 
et valider avec le client le tarif de vente.
* Si le client vendeur n'a pas pré-enregistré l'article, le bénevole crée un compte utilisateur au besoin et peut avec son
compte enregistrer des articles pour une autre personne(système de recherche facilité en rentrant les premieres lettres de l'email).
* Le bénévole peut modifier les données de l'article et le valider. (une fois l'article validé, le client vendeur
dans son espace personnel voit la mention 'Validé', il ne peut donc plus supprimer l'article).
* Le systéme donne un numéro d'article unique pour cette bourse, le bénévole peut donc l'inscrire avec le prix sur l'étiquette 
de l'article.

**2eme Etape - L'encaissement -**<br>
_Deuxième et troisième jour de la bourse les clients acheteurs consultent les articles._
Le bénévole encaisse un client:
* Ajout des numéros d'articles et vérification que l'article correspond bien.
* Le système donne la somme à payer (avec les 10% ajouté pour l'association arrondi au dixième de centime 
en faveur de l'association).
* Le bénévole peut rentrer dans le système le montant donné par l'acheteur.
* Le système calcule automatiquement la somme à rembourser.
* Le bénévole valide la transaction.

**3eme Etape - Le rembourssement -**<br>
_Quatrième jour de la bourse les clients vendeur viennent récupérer les gains ou les articles invendus._
* Le bénévole doit écrire l'email du client vendeur (système de recherche facilité en rentrant les premieres lettres de l'email) afin d'afficher
les infos.
* Le bénévole doit pour chaque article déclaré "l'article rendu au propriétaire" ou "article volé".
* Le systéme lorsque tous les articles ne sont plus de la résponsibilité de l'association donne le montant
des articles que l'association à vendu. (- 10% arrondi au dixième en faveur de l'association)

### Les fonctions pour la responsable:
La responsable de l'association à les mêmes fonctions qu'un bénévole plus:<br>
* La responsable peut supprimer une bourse sauf si elle a commencé ou si elle est déja passé.
Dans son espace personnel:
* La responsable peut gérer les accés utilisateurs:
    * Donner ou reprendre l'accés bénévole.
    * Donner ou reprendre l'accés responsable.
* La responsable peut crée des nouvelles bourses.
* La responsable peut voir les résultats de toutes les bourses en temps réel (système de recherche par date).
    * Nombre total d'articles dans la bourse.
    * Nombre total d'articles vendus (+ pourcentage).
    * Nombre total d'articles volés (+ pourcentage).
    * Nombre total d'articles retournés au propriétaire (+ pourcentage).
    * Total des gains de la bourse pour l'association sur les articles vendus.
    
## Déploiement:
**Pour l'application web(front):**<br>
* Executer la commande suivante afin de mettre en place les dépendances du projet angular:
```
    npm install
```
* Executer la commande suivante afin de crée le dossier dist/applicationWebAngular:
```
    ng build --prod
```
Copier/coller le contenu de ce dossier dans un serveur static web de type apache httpd ou nginx.

**Pour l'API web (microserviceBDD -> back):**<br>
* Copier/coller le fichier "token-conf.properties" à l'emplacement de votre choix.
- Dans ce fichier , vous pouvez changer la variable "secret", elle contient le mot secret pour le token.
* Afin de lancer la base de donnée mySql: copier/coller le dossier docker et executer la commande suivante afin de lancer un conteneur
docker qui contient la base de donnée mySql:
```
    docker-compose up -d
```
* Afin de lancer l'API web microserviceBdd: créez une variable d'environnement temporaire avec l'emplacement du fichier "token-conf.properties"
, exemple sur windows:
```
    set CONF_DIR=C:\Users\nicob\Documents\GitHub\Projet12_Badj\microserviceBdd\microservice-web\target
```
* Dans le dossier microserviceBdd executez la commande:
```
    mvn package
```
* Le fichier microservice-web-1.0.1.jar devrait être crée dans le dossier target du module web.
* Executer la commande suivante afin de lancer l'API:
```
    Java -jar microservice-web-1.0.1.jar
```
* Lancer votre serveur Tomcat et rendez-vous à l'adresse :
```
   http://localhost:4200/
``` 

## Contribution
[Github du projet](https://github.com/nicowtt/Projet12_Badj)

1: clone repository

2: Créer une nouvelle branche

2: Faite vos évolutions / changements (git checkout -b my-new-feature)

3: "Commit" les évolutions / changements (git commit -am "add some feature")

4: "Push" la nouvelle branche (git push origin my-new-feature)

5: Créer une nouvelle "pull request"

## Aperçu du site

![alt text](https://github.com/nicowtt/Projet12_Badj/blob/master/ViewSite.jpg)

## Auteur
Nicow

Voir mes autres projets :
[ICI](https://github.com/nicowtt?tab=repositories)


*twitter: nicow@nicowtt*
