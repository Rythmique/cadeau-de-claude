import Link from 'next/link'
import { Music, Download, Star, ShoppingCart } from 'lucide-react'
import FeaturedSheets from '@/components/FeaturedSheets'

export default function Home() {
  return (
    <div>
      {/* Hero Section */}
      <section className="bg-gradient-to-r from-primary-600 to-primary-800 text-white py-20">
        <div className="container mx-auto px-4">
          <div className="max-w-3xl mx-auto text-center">
            <h1 className="text-5xl font-bold mb-6">
              Votre bibliothèque de partitions musicales
            </h1>
            <p className="text-xl mb-8">
              Découvrez des milliers de partitions de qualité professionnelle.
              Téléchargement instantané, tous niveaux, tous genres.
            </p>
            <Link href="/catalog" className="btn-primary inline-block text-lg px-8 py-3">
              Explorer le catalogue
            </Link>
          </div>
        </div>
      </section>

      {/* Features Section */}
      <section className="py-16">
        <div className="container mx-auto px-4">
          <div className="grid md:grid-cols-3 gap-8">
            <div className="text-center">
              <div className="inline-flex items-center justify-center w-16 h-16 bg-primary-100 text-primary-600 rounded-full mb-4">
                <Download size={32} />
              </div>
              <h3 className="text-xl font-semibold mb-2">Téléchargement instantané</h3>
              <p className="text-gray-600">
                Accédez immédiatement à vos partitions après l'achat
              </p>
            </div>

            <div className="text-center">
              <div className="inline-flex items-center justify-center w-16 h-16 bg-primary-100 text-primary-600 rounded-full mb-4">
                <Star size={32} />
              </div>
              <h3 className="text-xl font-semibold mb-2">Qualité professionnelle</h3>
              <p className="text-gray-600">
                Partitions vérifiées et éditées par des musiciens professionnels
              </p>
            </div>

            <div className="text-center">
              <div className="inline-flex items-center justify-center w-16 h-16 bg-primary-100 text-primary-600 rounded-full mb-4">
                <Music size={32} />
              </div>
              <h3 className="text-xl font-semibold mb-2">Tous les genres</h3>
              <p className="text-gray-600">
                Classique, jazz, pop, rock et bien plus encore
              </p>
            </div>
          </div>
        </div>
      </section>

      {/* Featured Sheets */}
      <section className="py-16 bg-white">
        <div className="container mx-auto px-4">
          <h2 className="text-3xl font-bold text-center mb-12">Partitions populaires</h2>
          <FeaturedSheets />
        </div>
      </section>

      {/* CTA Section */}
      <section className="py-16 bg-gray-100">
        <div className="container mx-auto px-4 text-center">
          <h2 className="text-3xl font-bold mb-4">Prêt à commencer ?</h2>
          <p className="text-xl text-gray-600 mb-8">
            Rejoignez des milliers de musiciens qui font confiance à MusicSheets
          </p>
          <Link href="/catalog" className="btn-primary inline-block text-lg px-8 py-3">
            Voir toutes les partitions
          </Link>
        </div>
      </section>
    </div>
  )
}
