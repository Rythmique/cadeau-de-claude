# Cadeau de Claude

Monorepo contenant plusieurs applications développées avec Next.js et TypeScript.

## 🎵 Applications

### MusicSheets - Plateforme de vente de partitions musicales

Une application e-commerce complète pour la vente de partitions de musique en ligne.

**Fonctionnalités:**
- 🏠 Page d'accueil avec partitions en vedette
- 📚 Catalogue avec recherche et filtres avancés (genre, difficulté, instrument, prix)
- 📄 Pages détaillées pour chaque partition avec images et descriptions
- 🛒 Panier d'achat avec gestion des quantités
- 💳 Processus de checkout simplifié (mode démo)
- ✅ Page de confirmation de commande
- 📱 Design responsive et moderne avec Tailwind CSS
- 🗄️ Base de données SQLite avec données d'exemple

**Technologies:**
- Next.js 14 (App Router)
- TypeScript
- Tailwind CSS
- Zustand (gestion d'état)
- Better-SQLite3
- Lucide Icons

## 🚀 Installation

### Prérequis
- Node.js >= 18
- pnpm >= 8

### Installation des dépendances

```bash
# Installer pnpm si nécessaire
npm install -g pnpm

# Installer toutes les dépendances
pnpm install
```

## 💻 Développement

### Lancer l'application MusicSheets

```bash
# Démarrer le serveur de développement
pnpm dev

# Ou directement depuis le dossier de l'app
cd apps/music-sheets
pnpm dev
```

L'application sera accessible sur http://localhost:3000

### Structure du projet

```
cadeau-de-claude/
├── apps/
│   └── music-sheets/          # Application de vente de partitions
│       ├── src/
│       │   ├── app/           # Pages Next.js (App Router)
│       │   ├── components/    # Composants React
│       │   ├── lib/           # Utilitaires et config
│       │   └── types/         # Types TypeScript
│       ├── data/              # Base de données SQLite
│       └── package.json
├── packages/
│   ├── ui/                    # Composants partagés (futur)
│   └── utils/                 # Utilitaires partagés (futur)
├── package.json
└── pnpm-workspace.yaml
```

## 📦 Build

```bash
# Build toutes les applications
pnpm build

# Build une application spécifique
pnpm --filter music-sheets build
```

## 🎨 Fonctionnalités de MusicSheets

### Navigation
- Page d'accueil avec présentation et partitions populaires
- Catalogue complet avec filtres
- Détails des partitions
- Panier
- Checkout
- Confirmation de commande
- À propos
- Contact

### Filtres de recherche
- Par texte (titre, compositeur, description)
- Par genre (Classique, Jazz, Pop, Rock, Blues)
- Par difficulté (Débutant, Intermédiaire, Avancé, Expert)
- Par instrument (Piano, Guitare, Violon, Saxophone, Flûte)

### Base de données
La base de données SQLite est automatiquement créée et initialisée avec des données d'exemple au premier lancement.

Contenu par défaut:
- 8 partitions variées
- Différents genres, instruments et niveaux
- Images de couverture
- Notes et avis

## 🔧 Scripts disponibles

```bash
pnpm dev          # Démarrer le serveur de développement
pnpm build        # Build toutes les applications
pnpm lint         # Linter le code
pnpm clean        # Nettoyer les builds
```

## 📝 Notes

- L'application est en mode démo, aucun paiement réel n'est effectué
- Les images de couverture utilisent des URLs Unsplash
- La base de données est locale (SQLite)
- Le panier est persisté dans le localStorage du navigateur

## 🚀 Prochaines étapes

Fonctionnalités à ajouter:
- [ ] Système d'authentification utilisateur
- [ ] Dashboard administrateur pour gérer les partitions
- [ ] Téléchargement réel de fichiers PDF
- [ ] Aperçu des partitions
- [ ] Système de notation et d'avis
- [ ] Intégration d'un vrai système de paiement (Stripe)
- [ ] Historique des commandes
- [ ] Gestion des favoris
- [ ] Newsletter

## 📄 Licence

Projet de démonstration - 250 dollars de Claude Dev
