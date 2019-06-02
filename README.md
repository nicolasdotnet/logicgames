# logicgames

logicgames est une aplication de jeux logique : Recherche +/- et Mastermind.  

Chaque jeu peut être jouer sous 3 modes :   

-> Challenger :  
-> Défenseur :   
-> Duel :   

Un bouton option sur la fenêtre d'acceuille permet de modigier les paramétres des jeux :  


# Prérequis

- java SE 8
- maven

# Etapes de déploiement

git clone https://github.com/nicolasdotnet/logicgames.git  
cd logicgames  
git branch -a  
git checkout realise_02  
mvn clean install  
cd target  
java -jar LogicGames-1.0-SNAPSHOT.jar  

Le fichier log est disponible sur le chemin logicgames/target/resources/logs


