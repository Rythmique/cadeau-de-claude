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
- 💳 Processus de checkout avec intégration de paiement
- ✅ Page de confirmation de commande avec lien de paiement
- 📱 Design responsive et moderne avec Tailwind CSS
- 🗄️ Base de données SQLite avec données d'exemple

**Système d'authentification:**
- 🔐 Inscription et connexion utilisateur
- 👤 Profil utilisateur
- 🔑 Gestion des sessions sécurisées
- 🛡️ Protection des routes sensibles

**Dashboard administrateur:**
- 📊 Tableau de bord avec statistiques
- ➕ Création de nouvelles partitions
- ✏️ Modification des partitions existantes
- 🗑️ Suppression de partitions
- 📋 Gestion des commandes
- 👥 Vue des utilisateurs

**Fonctionnalités avancées:**
- 📥 Téléchargement de PDF après achat
- ⭐ Système de notation et d'avis
- 📖 Historique des achats
- 💰 Intégration de liens de paiement (prêt pour système réel)
- 🎯 Achats liés aux comptes utilisateurs

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

### Comptes de test

**Compte administrateur:**
- Email: `admin@musicsheets.com`
- Mot de passe: `admin123`

Ce compte permet d'accéder au dashboard admin (`/admin`) pour gérer:
- Les partitions (CRUD complet)
- Les commandes
- Les statistiques

**Compte utilisateur:**
Créez un nouveau compte via `/register` pour tester les fonctionnalités utilisateur:
- Profil personnel
- Historique d'achats
- Téléchargement de partitions
- Système d'avis

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

## 💳 Intégration du système de paiement

L'application est configurée pour intégrer facilement vos liens de paiement personnalisés.

### Configuration actuelle

- Liens de paiement placeholder générés à chaque commande
- Structure prête pour intégration avec n'importe quel système de paiement
- Support pour plusieurs méthodes de paiement

### Pour intégrer vos liens de paiement

Consultez le fichier `apps/music-sheets/PAYMENT_INTEGRATION.md` pour:
- Instructions détaillées d'intégration
- Emplacements exacts des fichiers à modifier
- Exemples de code
- Configuration des webhooks
- Variables d'environnement nécessaires

**Fichiers principaux à modifier:**
1. `src/app/api/orders/route.ts` - Ligne 20 (génération du lien)
2. `src/app/order-confirmation/[id]/page.tsx` - Ligne 11 (affichage du lien)

## 🎯 Fonctionnalités implémentées

✅ **Complété:**
- [x] Système d'authentification utilisateur complet
- [x] Dashboard administrateur pour gérer les partitions
- [x] CRUD complet des partitions (admin)
- [x] Téléchargement de fichiers PDF après achat
- [x] Système de notation et d'avis
- [x] Historique des commandes
- [x] Profil utilisateur avec achats
- [x] Intégration de paiement (structure prête)
- [x] Gestion des sessions sécurisées
- [x] Base de données complète

## 🚀 Améliorations futures possibles

- [ ] Upload de fichiers PDF via interface admin
- [ ] Aperçu des premières pages de partitions
- [ ] Système de favoris
- [ ] Newsletter
- [ ] Notifications email
- [ ] Recherche avancée avec filtres multiples
- [ ] Export des statistiques admin
- [ ] Gestion des promotions et codes promo

## 📄 Licence

Projet de démonstration - 250 dollars de Claude Dev
