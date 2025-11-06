'use client'

import { useParams } from 'next/navigation'
import Link from 'next/link'
import { CheckCircle, Download, Home } from 'lucide-react'

export default function OrderConfirmationPage() {
  const params = useParams()

  return (
    <div className="container mx-auto px-4 py-16">
      <div className="max-w-2xl mx-auto text-center">
        <CheckCircle size={80} className="mx-auto text-green-500 mb-6" />

        <h1 className="text-4xl font-bold mb-4">Commande confirmée !</h1>

        <p className="text-xl text-gray-600 mb-8">
          Merci pour votre achat. Votre commande #{params.id} a été traitée avec succès.
        </p>

        <div className="card p-8 mb-8 text-left">
          <h2 className="text-2xl font-bold mb-4">Prochaines étapes</h2>

          <div className="space-y-4">
            <div className="flex items-start">
              <div className="flex-shrink-0 w-8 h-8 bg-primary-100 text-primary-600 rounded-full flex items-center justify-center font-bold mr-4">
                1
              </div>
              <div>
                <h3 className="font-semibold mb-1">Email de confirmation envoyé</h3>
                <p className="text-gray-600">
                  Consultez votre boîte mail pour le récapitulatif de votre commande
                </p>
              </div>
            </div>

            <div className="flex items-start">
              <div className="flex-shrink-0 w-8 h-8 bg-primary-100 text-primary-600 rounded-full flex items-center justify-center font-bold mr-4">
                2
              </div>
              <div>
                <h3 className="font-semibold mb-1">Téléchargez vos partitions</h3>
                <p className="text-gray-600">
                  Vos partitions sont disponibles immédiatement au format PDF
                </p>
              </div>
            </div>

            <div className="flex items-start">
              <div className="flex-shrink-0 w-8 h-8 bg-primary-100 text-primary-600 rounded-full flex items-center justify-center font-bold mr-4">
                3
              </div>
              <div>
                <h3 className="font-semibold mb-1">Commencez à jouer !</h3>
                <p className="text-gray-600">
                  Imprimez vos partitions et profitez de votre musique
                </p>
              </div>
            </div>
          </div>
        </div>

        <div className="flex flex-col sm:flex-row gap-4 justify-center">
          <button className="btn-primary flex items-center justify-center px-6 py-3">
            <Download size={20} className="mr-2" />
            Télécharger mes partitions
          </button>

          <Link href="/" className="btn-secondary flex items-center justify-center px-6 py-3">
            <Home size={20} className="mr-2" />
            Retour à l'accueil
          </Link>
        </div>

        <div className="mt-8 text-sm text-gray-600">
          <p>
            Besoin d'aide ? Contactez-nous à{' '}
            <a href="mailto:support@musicsheets.com" className="text-primary-600 hover:underline">
              support@musicsheets.com
            </a>
          </p>
        </div>
      </div>
    </div>
  )
}
