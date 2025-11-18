'use client'

import { useState } from 'react'
import { useRouter } from 'next/navigation'
import { useCart } from '@/lib/store'
import { CreditCard, Mail, User } from 'lucide-react'

export default function CheckoutPage() {
  const router = useRouter()
  const { items, getTotalPrice, clearCart } = useCart()
  const [email, setEmail] = useState('')
  const [name, setName] = useState('')
  const [paymentMethod, setPaymentMethod] = useState('card')
  const [processing, setProcessing] = useState(false)

  const total = getTotalPrice()
  const totalWithTax = total * 1.2

  if (typeof window !== 'undefined' && items.length === 0) {
    router.push('/cart')
    return null
  }

  if (items.length === 0) {
    return null
  }

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault()
    setProcessing(true)

    try {
      const response = await fetch('/api/orders', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          customerEmail: email,
          customerName: name,
          paymentMethod,
          items: items.map((item) => ({
            sheetId: item.sheetId,
            quantity: item.quantity,
            price: item.sheet.price,
          })),
          total: totalWithTax,
        }),
      })

      if (response.ok) {
        const order = await response.json()
        clearCart()
        router.push(`/order-confirmation/${order.id}`)
      } else {
        alert('Erreur lors de la commande. Veuillez réessayer.')
      }
    } catch (error) {
      console.error('Error creating order:', error)
      alert('Erreur lors de la commande. Veuillez réessayer.')
    } finally {
      setProcessing(false)
    }
  }

  return (
    <div className="container mx-auto px-4 py-8">
      <h1 className="text-4xl font-bold mb-8">Finaliser la commande</h1>

      <div className="grid lg:grid-cols-3 gap-8">
        {/* Checkout Form */}
        <div className="lg:col-span-2">
          <form onSubmit={handleSubmit} className="space-y-6">
            {/* Customer Info */}
            <div className="card p-6">
              <h2 className="text-2xl font-bold mb-4">Informations client</h2>

              <div className="space-y-4">
                <div>
                  <label className="block text-sm font-medium text-gray-700 mb-2">
                    <User className="inline mr-2" size={16} />
                    Nom complet
                  </label>
                  <input
                    type="text"
                    required
                    className="input-field"
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                    placeholder="Jean Dupont"
                  />
                </div>

                <div>
                  <label className="block text-sm font-medium text-gray-700 mb-2">
                    <Mail className="inline mr-2" size={16} />
                    Email
                  </label>
                  <input
                    type="email"
                    required
                    className="input-field"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    placeholder="jean.dupont@example.com"
                  />
                  <p className="text-sm text-gray-500 mt-1">
                    Vos partitions seront envoyées à cette adresse
                  </p>
                </div>
              </div>
            </div>

            {/* Payment Info */}
            <div className="card p-6">
              <h2 className="text-2xl font-bold mb-4">
                <CreditCard className="inline mr-2" size={24} />
                Paiement
              </h2>

              <div className="bg-yellow-50 border border-yellow-200 rounded-lg p-4 mb-4">
                <p className="text-sm text-yellow-800">
                  <strong>Mode démo:</strong> Aucun paiement réel ne sera effectué.
                  Cliquez sur "Confirmer la commande" pour simuler l'achat.
                </p>
              </div>

              <div className="space-y-4">
                <div>
                  <label className="block text-sm font-medium text-gray-700 mb-2">
                    Numéro de carte
                  </label>
                  <input
                    type="text"
                    className="input-field"
                    placeholder="4242 4242 4242 4242"
                    value="4242 4242 4242 4242"
                    disabled
                  />
                </div>

                <div className="grid grid-cols-2 gap-4">
                  <div>
                    <label className="block text-sm font-medium text-gray-700 mb-2">
                      Date d'expiration
                    </label>
                    <input
                      type="text"
                      className="input-field"
                      placeholder="MM/YY"
                      value="12/25"
                      disabled
                    />
                  </div>
                  <div>
                    <label className="block text-sm font-medium text-gray-700 mb-2">
                      CVV
                    </label>
                    <input
                      type="text"
                      className="input-field"
                      placeholder="123"
                      value="123"
                      disabled
                    />
                  </div>
                </div>
              </div>
            </div>

            <button
              type="submit"
              disabled={processing}
              className="btn-primary w-full py-3 text-lg disabled:opacity-50 disabled:cursor-not-allowed"
            >
              {processing ? 'Traitement en cours...' : 'Confirmer la commande'}
            </button>
          </form>
        </div>

        {/* Order Summary */}
        <div className="lg:col-span-1">
          <div className="card p-6 sticky top-24">
            <h2 className="text-2xl font-bold mb-6">Récapitulatif</h2>

            <div className="space-y-4 mb-6">
              {items.map((item) => (
                <div key={item.sheetId} className="flex justify-between text-sm">
                  <div>
                    <p className="font-semibold">{item.sheet.title}</p>
                    <p className="text-gray-600">Qté: {item.quantity}</p>
                  </div>
                  <p className="font-semibold">
                    {(item.sheet.price * item.quantity).toFixed(2)} €
                  </p>
                </div>
              ))}
            </div>

            <div className="border-t pt-4 space-y-2">
              <div className="flex justify-between">
                <span className="text-gray-600">Sous-total</span>
                <span className="font-semibold">{total.toFixed(2)} €</span>
              </div>
              <div className="flex justify-between">
                <span className="text-gray-600">TVA (20%)</span>
                <span className="font-semibold">{(total * 0.2).toFixed(2)} €</span>
              </div>
              <div className="border-t pt-2">
                <div className="flex justify-between text-lg">
                  <span className="font-bold">Total</span>
                  <span className="font-bold text-primary-600">
                    {totalWithTax.toFixed(2)} €
                  </span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}
