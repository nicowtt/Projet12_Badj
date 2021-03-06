# Open Classrooms Projet12 Badj (association)
 Voici un système de gestion de dêpots-ventes d'articles (version 1.0.2):
 
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
* Base de donnée mySql (version 8).
* Toutes les applications sont dockérizé. (docker-compose dans le dossier docker)
* Une variable d'environnement "token-secret" contient le mot secret pour la sécurité lié aux tokens.

 
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
Placez-vous dans le dossier docker et executez la commande suivante:
```
    docker-compose up
```
## modification microserviceBdd(back):
- Effectuez la modification
- executer la commande:
```
    mvn package
```
- Déplacez le .jar obtenu du dossier: microservice-web/target/[version du microserviceBdd.jar]
vers le dossier: microserviceBdd/executable
- Modifier le nom du nouveau jar dans le fichier Dockerfile

## Documentation API microserviceBdd:
http://localhost:9001/swagger-ui.html

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

## Licence
    Copyright (C) 2020  BODELLE Nicolas

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>..

Voir mes autres projets :
[ICI](https://github.com/nicowtt?tab=repositories)


*twitter: nicow@nicowtt*
