'use client'

import { useState, useEffect } from 'react'
import { useParams, useRouter } from 'next/navigation'
import Image from 'next/image'
import { Star, Music, ShoppingCart, ArrowLeft } from 'lucide-react'
import type { MusicSheet } from '@/types'
import { useCart } from '@/lib/store'
import ReviewSection from '@/components/ReviewSection'

const difficultyLabels = {
  beginner: 'Débutant',
  intermediate: 'Intermédiaire',
  advanced: 'Avancé',
  expert: 'Expert',
}

const genreLabels: Record<string, string> = {
  classical: 'Classique',
  jazz: 'Jazz',
  pop: 'Pop',
  rock: 'Rock',
  blues: 'Blues',
}

export default function SheetDetailPage() {
  const params = useParams()
  const router = useRouter()
  const [sheet, setSheet] = useState<MusicSheet | null>(null)
  const [loading, setLoading] = useState(true)
  const addItem = useCart((state) => state.addItem)

  useEffect(() => {
    fetchSheet()
  }, [params.id])

  const fetchSheet = async () => {
    try {
      const response = await fetch(`/api/sheets/${params.id}`)
      if (response.ok) {
        const data = await response.json()
        setSheet(data)
      }
    } catch (error) {
      console.error('Error fetching sheet:', error)
    } finally {
      setLoading(false)
    }
  }

  const handleAddToCart = () => {
    if (sheet) {
      addItem(sheet)
      router.push('/cart')
    }
  }

  if (loading) {
    return (
      <div className="container mx-auto px-4 py-8">
        <p className="text-center">Chargement...</p>
      </div>
    )
  }

  if (!sheet) {
    return (
      <div className="container mx-auto px-4 py-8">
        <p className="text-center">Partition non trouvée</p>
      </div>
    )
  }

  return (
    <div className="container mx-auto px-4 py-8">
      <button
        onClick={() => router.back()}
        className="flex items-center text-gray-600 hover:text-gray-900 mb-6"
      >
        <ArrowLeft size={20} className="mr-2" />
        Retour
      </button>

      <div className="grid md:grid-cols-2 gap-8">
        {/* Image */}
        <div>
          <div className="card overflow-hidden">
            <div className="relative h-96 bg-gray-200">
              {sheet.coverImageUrl ? (
                <Image
                  src={sheet.coverImageUrl}
                  alt={sheet.title}
                  fill
                  className="object-cover"
                />
              ) : (
                <div className="flex items-center justify-center h-full">
                  <Music size={96} className="text-gray-400" />
                </div>
              )}
            </div>
          </div>
        </div>

        {/* Details */}
        <div>
          <h1 className="text-4xl font-bold mb-2">{sheet.title}</h1>
          <p className="text-xl text-gray-600 mb-4">
            {sheet.composer}
            {sheet.arranger && ` • Arr. ${sheet.arranger}`}
          </p>

          <div className="flex items-center mb-6">
            <div className="flex items-center">
              {[...Array(5)].map((_, i) => (
                <Star
                  key={i}
                  size={20}
                  className={
                    i < Math.floor(sheet.rating)
                      ? 'text-yellow-400 fill-yellow-400'
                      : 'text-gray-300'
                  }
                />
              ))}
            </div>
            <span className="ml-2 text-gray-600">
              {sheet.rating.toFixed(1)} ({sheet.reviewCount} avis)
            </span>
          </div>

          <div className="mb-6">
            <div className="text-4xl font-bold text-primary-600 mb-4">
              {sheet.price.toFixed(2)} €
            </div>

            <button
              onClick={handleAddToCart}
              className="btn-primary w-full py-3 text-lg flex items-center justify-center"
            >
              <ShoppingCart className="mr-2" size={20} />
              Ajouter au panier
            </button>
          </div>

          <div className="bg-gray-50 rounded-lg p-6 space-y-3">
            <div className="flex justify-between">
              <span className="font-semibold">Genre:</span>
              <span>{genreLabels[sheet.genre] || sheet.genre}</span>
            </div>
            <div className="flex justify-between">
              <span className="font-semibold">Instrument:</span>
              <span className="capitalize">{sheet.instrument}</span>
            </div>
            <div className="flex justify-between">
              <span className="font-semibold">Niveau:</span>
              <span>{difficultyLabels[sheet.difficulty]}</span>
            </div>
            <div className="flex justify-between">
              <span className="font-semibold">Nombre de pages:</span>
              <span>{sheet.pages}</span>
            </div>
          </div>

          <div className="mt-6">
            <h2 className="text-xl font-semibold mb-2">Description</h2>
            <p className="text-gray-700">{sheet.description}</p>
          </div>
        </div>
      </div>

      {/* Reviews Section */}
      <ReviewSection sheetId={sheet.id} />
    </div>
  )
}
