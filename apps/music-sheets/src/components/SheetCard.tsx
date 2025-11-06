'use client'

import Link from 'next/link'
import Image from 'next/image'
import { Star, Music } from 'lucide-react'
import type { MusicSheet } from '@/types'
import { useCart } from '@/lib/store'

interface SheetCardProps {
  sheet: MusicSheet
}

const difficultyColors = {
  beginner: 'bg-green-100 text-green-800',
  intermediate: 'bg-blue-100 text-blue-800',
  advanced: 'bg-orange-100 text-orange-800',
  expert: 'bg-red-100 text-red-800',
}

const difficultyLabels = {
  beginner: 'Débutant',
  intermediate: 'Intermédiaire',
  advanced: 'Avancé',
  expert: 'Expert',
}

export default function SheetCard({ sheet }: SheetCardProps) {
  const addItem = useCart((state) => state.addItem)

  const handleAddToCart = (e: React.MouseEvent) => {
    e.preventDefault()
    addItem(sheet)
  }

  return (
    <Link href={`/sheet/${sheet.id}`} className="card overflow-hidden block">
      <div className="relative h-64 bg-gray-200">
        {sheet.coverImageUrl ? (
          <Image
            src={sheet.coverImageUrl}
            alt={sheet.title}
            fill
            className="object-cover"
          />
        ) : (
          <div className="flex items-center justify-center h-full">
            <Music size={64} className="text-gray-400" />
          </div>
        )}
        <div className="absolute top-2 right-2">
          <span className={`px-2 py-1 rounded text-xs font-semibold ${difficultyColors[sheet.difficulty]}`}>
            {difficultyLabels[sheet.difficulty]}
          </span>
        </div>
      </div>

      <div className="p-4">
        <h3 className="font-semibold text-lg mb-1 line-clamp-1">{sheet.title}</h3>
        <p className="text-gray-600 text-sm mb-2">{sheet.composer}</p>

        <div className="flex items-center mb-3">
          <Star size={16} className="text-yellow-400 fill-yellow-400" />
          <span className="ml-1 text-sm font-medium">{sheet.rating.toFixed(1)}</span>
          <span className="ml-1 text-sm text-gray-500">({sheet.reviewCount})</span>
        </div>

        <div className="flex items-center justify-between">
          <span className="text-2xl font-bold text-primary-600">{sheet.price.toFixed(2)} €</span>
          <button
            onClick={handleAddToCart}
            className="btn-primary text-sm px-4 py-2"
          >
            Ajouter
          </button>
        </div>

        <div className="mt-3 text-xs text-gray-500">
          {sheet.instrument} • {sheet.pages} pages
        </div>
      </div>
    </Link>
  )
}
