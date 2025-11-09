'use client'

import { useState, useEffect } from 'react'
import Link from 'next/link'
import { Plus, Edit, Trash2 } from 'lucide-react'
import type { MusicSheet } from '@/types'

export default function AdminSheetsPage() {
  const [sheets, setSheets] = useState<MusicSheet[]>([])
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    fetchSheets()
  }, [])

  const fetchSheets = async () => {
    try {
      const response = await fetch('/api/admin/sheets')
      const data = await response.json()
      setSheets(data)
    } catch (error) {
      console.error('Error fetching sheets:', error)
    } finally {
      setLoading(false)
    }
  }

  const handleDelete = async (id: number) => {
    if (!confirm('Êtes-vous sûr de vouloir supprimer cette partition ?')) return

    try {
      const response = await fetch(`/api/admin/sheets/${id}`, {
        method: 'DELETE'
      })

      if (response.ok) {
        fetchSheets()
      }
    } catch (error) {
      console.error('Error deleting sheet:', error)
    }
  }

  return (
    <div>
      <div className="flex justify-between items-center mb-8">
        <h1 className="text-3xl font-bold">Gestion des partitions</h1>
        <Link href="/admin/sheets/new" className="btn-primary flex items-center">
          <Plus size={20} className="mr-2" />
          Nouvelle partition
        </Link>
      </div>

      {loading ? (
        <p>Chargement...</p>
      ) : (
        <div className="card overflow-hidden">
          <table className="w-full">
            <thead className="bg-gray-50">
              <tr>
                <th className="text-left py-3 px-4">ID</th>
                <th className="text-left py-3 px-4">Titre</th>
                <th className="text-left py-3 px-4">Compositeur</th>
                <th className="text-left py-3 px-4">Genre</th>
                <th className="text-left py-3 px-4">Prix</th>
                <th className="text-left py-3 px-4">Note</th>
                <th className="text-right py-3 px-4">Actions</th>
              </tr>
            </thead>
            <tbody>
              {sheets.map((sheet) => (
                <tr key={sheet.id} className="border-t hover:bg-gray-50">
                  <td className="py-3 px-4">{sheet.id}</td>
                  <td className="py-3 px-4 font-semibold">{sheet.title}</td>
                  <td className="py-3 px-4">{sheet.composer}</td>
                  <td className="py-3 px-4">{sheet.genre}</td>
                  <td className="py-3 px-4">{sheet.price.toFixed(2)} €</td>
                  <td className="py-3 px-4">{sheet.rating.toFixed(1)} ⭐</td>
                  <td className="py-3 px-4">
                    <div className="flex justify-end space-x-2">
                      <Link
                        href={`/admin/sheets/${sheet.id}/edit`}
                        className="text-blue-600 hover:text-blue-800"
                      >
                        <Edit size={18} />
                      </Link>
                      <button
                        onClick={() => handleDelete(sheet.id)}
                        className="text-red-600 hover:text-red-800"
                      >
                        <Trash2 size={18} />
                      </button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  )
}
