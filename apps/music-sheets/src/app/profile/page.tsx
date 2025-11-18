'use client'

import { useState, useEffect } from 'react'
import { redirect } from 'next/navigation'
import Link from 'next/link'
import { User, Download, ShoppingBag, Star } from 'lucide-react'
import type { User as UserType, Purchase, MusicSheet } from '@/types'

export default function ProfilePage() {
  const [user, setUser] = useState<UserType | null>(null)
  const [purchases, setPurchases] = useState<Array<Purchase & { sheet: MusicSheet }>>([])
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    checkAuth()
    fetchPurchases()
  }, [])

  const checkAuth = async () => {
    try {
      const response = await fetch('/api/auth/me')
      if (response.ok) {
        const data = await response.json()
        setUser(data.user)
      } else {
        if (typeof window !== 'undefined') {
          window.location.href = '/login'
        }
      }
    } catch (error) {
      if (typeof window !== 'undefined') {
        window.location.href = '/login'
      }
    } finally {
      setLoading(false)
    }
  }

  const fetchPurchases = async () => {
    try {
      const response = await fetch('/api/profile/purchases')
      if (response.ok) {
        const data = await response.json()
        setPurchases(data)
      }
    } catch (error) {
      console.error('Error fetching purchases:', error)
    }
  }

  if (loading) {
    return <div className="container mx-auto px-4 py-16">Chargement...</div>
  }

  return (
    <div className="container mx-auto px-4 py-8">
      <div className="max-w-4xl mx-auto">
        {/* User Info */}
        <div className="card p-8 mb-8">
          <div className="flex items-center space-x-4 mb-6">
            <div className="w-20 h-20 bg-primary-100 rounded-full flex items-center justify-center">
              <User size={40} className="text-primary-600" />
            </div>
            <div>
              <h1 className="text-3xl font-bold">{user?.name}</h1>
              <p className="text-gray-600">{user?.email}</p>
            </div>
          </div>
        </div>

        {/* Purchases */}
        <div className="card p-8">
          <h2 className="text-2xl font-bold mb-6 flex items-center">
            <ShoppingBag className="mr-2" size={24} />
            Mes achats ({purchases.length})
          </h2>

          {purchases.length === 0 ? (
            <div className="text-center py-12">
              <p className="text-gray-500 mb-4">Vous n'avez pas encore acheté de partitions</p>
              <Link href="/catalog" className="btn-primary">
                Parcourir le catalogue
              </Link>
            </div>
          ) : (
            <div className="space-y-4">
              {purchases.map((purchase) => (
                <div
                  key={purchase.id}
                  className="border rounded-lg p-4 hover:bg-gray-50 transition-colors"
                >
                  <div className="flex justify-between items-start">
                    <div className="flex-1">
                      <h3 className="font-semibold text-lg">{purchase.sheet.title}</h3>
                      <p className="text-gray-600">{purchase.sheet.composer}</p>
                      <p className="text-sm text-gray-500 mt-1">
                        Acheté le {new Date(purchase.purchasedAt).toLocaleDateString('fr-FR')}
                      </p>
                    </div>
                    <div className="flex flex-col space-y-2">
                      <a
                        href={`/api/download/${purchase.sheetId}`}
                        className="btn-primary flex items-center px-4 py-2"
                      >
                        <Download size={16} className="mr-2" />
                        Télécharger
                      </a>
                      <Link
                        href={`/sheet/${purchase.sheetId}#review`}
                        className="btn-secondary flex items-center px-4 py-2"
                      >
                        <Star size={16} className="mr-2" />
                        Laisser un avis
                      </Link>
                    </div>
                  </div>
                </div>
              ))}
            </div>
          )}
        </div>
      </div>
    </div>
  )
}
