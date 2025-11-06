'use client'

import Link from 'next/link'
import Image from 'next/image'
import { Trash2, ShoppingCart, Music } from 'lucide-react'
import { useCart } from '@/lib/store'

export default function CartPage() {
  const { items, removeItem, updateQuantity, getTotalPrice } = useCart()

  const total = getTotalPrice()

  if (items.length === 0) {
    return (
      <div className="container mx-auto px-4 py-16">
        <div className="text-center max-w-md mx-auto">
          <ShoppingCart size={64} className="mx-auto text-gray-400 mb-4" />
          <h1 className="text-3xl font-bold mb-4">Votre panier est vide</h1>
          <p className="text-gray-600 mb-8">
            Découvrez notre catalogue de partitions et trouvez votre prochaine pièce à jouer !
          </p>
          <Link href="/catalog" className="btn-primary inline-block">
            Parcourir le catalogue
          </Link>
        </div>
      </div>
    )
  }

  return (
    <div className="container mx-auto px-4 py-8">
      <h1 className="text-4xl font-bold mb-8">Panier</h1>

      <div className="grid lg:grid-cols-3 gap-8">
        {/* Cart Items */}
        <div className="lg:col-span-2">
          <div className="space-y-4">
            {items.map((item) => (
              <div key={item.sheetId} className="card p-4">
                <div className="flex gap-4">
                  {/* Image */}
                  <div className="relative w-24 h-32 flex-shrink-0 bg-gray-200 rounded">
                    {item.sheet.coverImageUrl ? (
                      <Image
                        src={item.sheet.coverImageUrl}
                        alt={item.sheet.title}
                        fill
                        className="object-cover rounded"
                      />
                    ) : (
                      <div className="flex items-center justify-center h-full">
                        <Music size={32} className="text-gray-400" />
                      </div>
                    )}
                  </div>

                  {/* Details */}
                  <div className="flex-grow">
                    <h3 className="font-semibold text-lg mb-1">{item.sheet.title}</h3>
                    <p className="text-gray-600 text-sm mb-2">{item.sheet.composer}</p>
                    <p className="text-primary-600 font-bold">{item.sheet.price.toFixed(2)} €</p>
                  </div>

                  {/* Quantity and Remove */}
                  <div className="flex flex-col items-end justify-between">
                    <button
                      onClick={() => removeItem(item.sheetId)}
                      className="text-red-500 hover:text-red-700"
                    >
                      <Trash2 size={20} />
                    </button>

                    <div className="flex items-center gap-2">
                      <button
                        onClick={() => updateQuantity(item.sheetId, item.quantity - 1)}
                        className="w-8 h-8 rounded border border-gray-300 hover:bg-gray-100"
                      >
                        -
                      </button>
                      <span className="w-8 text-center font-semibold">{item.quantity}</span>
                      <button
                        onClick={() => updateQuantity(item.sheetId, item.quantity + 1)}
                        className="w-8 h-8 rounded border border-gray-300 hover:bg-gray-100"
                      >
                        +
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            ))}
          </div>
        </div>

        {/* Order Summary */}
        <div className="lg:col-span-1">
          <div className="card p-6 sticky top-24">
            <h2 className="text-2xl font-bold mb-6">Résumé de la commande</h2>

            <div className="space-y-3 mb-6">
              <div className="flex justify-between">
                <span className="text-gray-600">Sous-total</span>
                <span className="font-semibold">{total.toFixed(2)} €</span>
              </div>
              <div className="flex justify-between">
                <span className="text-gray-600">Taxe (TVA 20%)</span>
                <span className="font-semibold">{(total * 0.2).toFixed(2)} €</span>
              </div>
              <div className="border-t pt-3">
                <div className="flex justify-between text-lg">
                  <span className="font-bold">Total</span>
                  <span className="font-bold text-primary-600">
                    {(total * 1.2).toFixed(2)} €
                  </span>
                </div>
              </div>
            </div>

            <Link href="/checkout" className="btn-primary w-full block text-center py-3">
              Passer la commande
            </Link>

            <Link
              href="/catalog"
              className="btn-secondary w-full block text-center py-3 mt-3"
            >
              Continuer mes achats
            </Link>

            <div className="mt-6 text-sm text-gray-600">
              <p className="mb-2">✓ Téléchargement instantané</p>
              <p className="mb-2">✓ Paiement sécurisé</p>
              <p>✓ Accès illimité à vos partitions</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}
