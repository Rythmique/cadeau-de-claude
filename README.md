# 🎨 Coloriage Enfants - Application Android

Application de coloriage éducative pour enfants sur Android, permettant de colorier les lettres de l'alphabet et différents animaux avec une palette de couleurs variée.

## 📱 Fonctionnalités

- **Alphabet Interactif**: Les enfants peuvent sélectionner et colorier les 26 lettres de l'alphabet (A-Z)
- **Galerie d'Animaux**: 20 animaux différents à colorier (chat, chien, lion, éléphant, etc.)
- **Palette de Couleurs Riche**: 13 couleurs vibrantes à choisir
- **Interface Intuitive**: Design simple et coloré adapté aux jeunes enfants
- **Navigation Facile**: Écrans clairs avec navigation fluide

## 🎨 Catégories

### Alphabet
Toutes les lettres de A à Z présentées dans une grille interactive

### Animaux
- 🐱 Chat
- 🐶 Chien
- 🦁 Lion
- 🐯 Tigre
- 🐻 Ours
- 🐼 Panda
- 🐰 Lapin
- 🦊 Renard
- 🐘 Éléphant
- 🦒 Girafe
- 🦓 Zèbre
- 🐵 Singe
- 🐠 Poisson
- 🐬 Dauphin
- 🐋 Baleine
- 🐦 Oiseau
- 🦋 Papillon
- 🐝 Abeille
- 🐸 Grenouille
- 🐢 Tortue

## 🛠️ Technologies Utilisées

- **Kotlin**: Langage de programmation principal
- **Jetpack Compose**: Framework UI moderne d'Android
- **Material Design 3**: Design system pour une interface cohérente
- **Navigation Component**: Gestion de la navigation entre écrans
- **Canvas API**: Pour le système de dessin et coloriage

## 📋 Prérequis

- Android Studio (version 2023.1 ou supérieure)
- Android SDK 24 (Android 7.0) ou supérieur
- JDK 8 ou supérieur
- Gradle 8.0

## 🚀 Installation

1. Clonez le repository:
```bash
git clone https://github.com/votre-username/cadeau-de-claude.git
```

2. Ouvrez le projet dans Android Studio

3. Synchronisez le projet avec Gradle (Android Studio le fera automatiquement)

4. Connectez un appareil Android ou lancez un émulateur

5. Cliquez sur "Run" ou utilisez la commande:
```bash
./gradlew installDebug
```

## 📱 Configuration Minimale

- **API minimale**: Android 7.0 (API 24)
- **API cible**: Android 14 (API 34)
- **Orientation**: Portrait uniquement

## 🎯 Utilisation

1. **Écran d'accueil**: Choisissez entre "Alphabet" ou "Animaux"
2. **Sélection**: Appuyez sur une lettre ou un animal
3. **Coloriage**:
   - Sélectionnez une couleur dans la palette
   - Touchez les zones à colorier
   - Utilisez le bouton "Effacer" pour recommencer

## 🏗️ Structure du Projet

```
app/src/main/java/com/example/coloringkids/
├── MainActivity.kt              # Point d'entrée de l'application
├── Navigation.kt                # Configuration de la navigation
├── screens/
│   ├── HomeScreen.kt           # Écran d'accueil
│   ├── AlphabetScreen.kt       # Grille des lettres
│   ├── AnimalScreen.kt         # Grille des animaux
│   └── ColoringScreen.kt       # Écran de coloriage
├── components/
│   └── ColoringCanvas.kt       # Composant de dessin
└── ui/theme/
    ├── Color.kt                # Définition des couleurs
    ├── Theme.kt                # Thème de l'application
    └── Type.kt                 # Typographie
```

## 🎨 Palette de Couleurs

L'application inclut 13 couleurs:
- Rouge, Bleu, Vert, Jaune
- Orange, Violet, Rose, Marron
- Bleu clair, Vert clair
- Marron foncé, Gris, Noir

## 📄 Licence

Ce projet a été créé dans le cadre d'un test de développement.

## 👨‍💻 Développement

Développé avec ❤️ en utilisant Claude AI pour créer une application éducative et amusante pour les enfants.
