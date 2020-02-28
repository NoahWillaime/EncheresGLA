# EncheresGLA
Pour l'application principale :
  Ouvrir les projets :

  - Sous NetBeans faire Ouvrir projet
  - Choisir le projet EncheresGLA
  - Ajoutez à la librairie du projet War le jar "pretyFaces" 

  Création de la base de donnée :

  - Créer une base de donnée sous Service -> Database -> Java DB -> Create database
  - Appelez la base de donnée GLA avec comme utilisateur root et mot de passe root
  - Créez une pool "jdbc/encheres"
  - Nommez la persistence-unit : "Encheres-PU"
  
  Création des JMS-Queues : 
 
  - Créez 4 JMS queues : jms/FactureQueue jms/LivraisonQueue jms/RecepLivraisonQueue jms/RecepFactureQueue
  
  Déployer le projet :
  
  - Faites Clean and Build sur le projet GLA_Enchere si l'application note une erreur à cause de pretyFaces ce n'est pas grave
  - Pour lancer le projet faite Run Project

Pour l'application des commandes :
  Ouvrir le projet [GLAFacturation](https://github.com/NoahWillaime/GLAFacturation)
  Ouvrir le projet [GLALivraison](https://github.com/NoahWillaime/GLALivraison)

  - Sous NetBeans faire Ouvrir projet
  - Choisir les projets

  Déployer le projet :

  - Faites Clean and Build sur le prjet GLA_Enchere
  - Pour lancer le projet faite Run Project
