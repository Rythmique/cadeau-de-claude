import { Music, Users, Award, Globe } from 'lucide-react'

export default function AboutPage() {
  return (
    <div className="container mx-auto px-4 py-8">
      <div className="max-w-4xl mx-auto">
        <h1 className="text-5xl font-bold mb-6">À propos de MusicSheets</h1>

        <div className="prose prose-lg mb-12">
          <p className="text-xl text-gray-600">
            MusicSheets est votre destination de confiance pour des partitions musicales
            de qualité professionnelle. Nous nous engageons à rendre la musique accessible
            à tous les musiciens, quel que soit leur niveau.
          </p>
        </div>

        <div className="grid md:grid-cols-2 gap-8 mb-12">
          <div className="card p-6">
            <Music size={40} className="text-primary-600 mb-4" />
            <h3 className="text-xl font-bold mb-2">Notre Mission</h3>
            <p className="text-gray-600">
              Offrir un accès instantané à des milliers de partitions de qualité professionnelle,
              soigneusement éditées et vérifiées par des musiciens experts.
            </p>
          </div>

          <div className="card p-6">
            <Users size={40} className="text-primary-600 mb-4" />
            <h3 className="text-xl font-bold mb-2">Notre Communauté</h3>
            <p className="text-gray-600">
              Plus de 50 000 musiciens nous font confiance pour leur apprentissage
              et leur développement musical quotidien.
            </p>
          </div>

          <div className="card p-6">
            <Award size={40} className="text-primary-600 mb-4" />
            <h3 className="text-xl font-bold mb-2">Qualité Garantie</h3>
            <p className="text-gray-600">
              Chaque partition est soigneusement éditée, vérifiée et optimisée
              pour une lecture facile et une impression de qualité.
            </p>
          </div>

          <div className="card p-6">
            <Globe size={40} className="text-primary-600 mb-4" />
            <h3 className="text-xl font-bold mb-2">Disponibilité Mondiale</h3>
            <p className="text-gray-600">
              Téléchargement instantané disponible partout dans le monde,
              24h/24 et 7j/7.
            </p>
          </div>
        </div>

        <div className="bg-gradient-to-r from-primary-600 to-primary-800 text-white rounded-lg p-8 mb-12">
          <h2 className="text-3xl font-bold mb-4">Pourquoi choisir MusicSheets ?</h2>
          <ul className="space-y-3">
            <li className="flex items-start">
              <span className="mr-2">✓</span>
              <span>Catalogue varié couvrant tous les genres musicaux</span>
            </li>
            <li className="flex items-start">
              <span className="mr-2">✓</span>
              <span>Partitions pour tous les niveaux, du débutant à l'expert</span>
            </li>
            <li className="flex items-start">
              <span className="mr-2">✓</span>
              <span>Téléchargement instantané en format PDF haute qualité</span>
            </li>
            <li className="flex items-start">
              <span className="mr-2">✓</span>
              <span>Prix abordables et transparents</span>
            </li>
            <li className="flex items-start">
              <span className="mr-2">✓</span>
              <span>Support client réactif et disponible</span>
            </li>
          </ul>
        </div>

        <div className="text-center">
          <h2 className="text-3xl font-bold mb-4">Prêt à commencer ?</h2>
          <p className="text-gray-600 mb-6">
            Rejoignez des milliers de musiciens satisfaits et découvrez notre catalogue
          </p>
          <a href="/catalog" className="btn-primary inline-block px-8 py-3 text-lg">
            Explorer le catalogue
          </a>
        </div>
      </div>
    </div>
  )
}
