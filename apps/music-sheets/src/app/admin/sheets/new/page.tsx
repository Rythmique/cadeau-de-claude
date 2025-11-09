'use client'

import { useState } from 'react'
import { useRouter } from 'next/navigation'
import { ArrowLeft } from 'lucide-react'
import Link from 'next/link'

export default function NewSheetPage() {
  const router = useRouter()
  const [formData, setFormData] = useState({
    title: '',
    composer: '',
    arranger: '',
    genre: 'classical',
    difficulty: 'intermediate',
    instrument: 'piano',
    price: '',
    description: '',
    pages: '',
    pdfFilePath: '',
    coverImageUrl: ''
  })
  const [saving, setSaving] = useState(false)

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault()
    setSaving(true)

    try {
      const response = await fetch('/api/admin/sheets', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          ...formData,
          price: parseFloat(formData.price),
          pages: parseInt(formData.pages)
        })
      })

      if (response.ok) {
        router.push('/admin/sheets')
      } else {
        alert('Erreur lors de la création')
      }
    } catch (error) {
      alert('Erreur lors de la création')
    } finally {
      setSaving(false)
    }
  }

  return (
    <div>
      <Link href="/admin/sheets" className="flex items-center text-gray-600 hover:text-gray-900 mb-6">
        <ArrowLeft size={20} className="mr-2" />
        Retour
      </Link>

      <h1 className="text-3xl font-bold mb-8">Nouvelle partition</h1>

      <div className="card p-8">
        <form onSubmit={handleSubmit} className="space-y-6">
          <div className="grid md:grid-cols-2 gap-6">
            <div>
              <label className="block text-sm font-medium mb-2">Titre *</label>
              <input
                type="text"
                required
                className="input-field"
                value={formData.title}
                onChange={(e) => setFormData({ ...formData, title: e.target.value })}
              />
            </div>

            <div>
              <label className="block text-sm font-medium mb-2">Compositeur *</label>
              <input
                type="text"
                required
                className="input-field"
                value={formData.composer}
                onChange={(e) => setFormData({ ...formData, composer: e.target.value })}
              />
            </div>

            <div>
              <label className="block text-sm font-medium mb-2">Arrangeur</label>
              <input
                type="text"
                className="input-field"
                value={formData.arranger}
                onChange={(e) => setFormData({ ...formData, arranger: e.target.value })}
              />
            </div>

            <div>
              <label className="block text-sm font-medium mb-2">Genre *</label>
              <select
                className="input-field"
                value={formData.genre}
                onChange={(e) => setFormData({ ...formData, genre: e.target.value })}
              >
                <option value="classical">Classique</option>
                <option value="jazz">Jazz</option>
                <option value="pop">Pop</option>
                <option value="rock">Rock</option>
                <option value="blues">Blues</option>
              </select>
            </div>

            <div>
              <label className="block text-sm font-medium mb-2">Difficulté *</label>
              <select
                className="input-field"
                value={formData.difficulty}
                onChange={(e) => setFormData({ ...formData, difficulty: e.target.value })}
              >
                <option value="beginner">Débutant</option>
                <option value="intermediate">Intermédiaire</option>
                <option value="advanced">Avancé</option>
                <option value="expert">Expert</option>
              </select>
            </div>

            <div>
              <label className="block text-sm font-medium mb-2">Instrument *</label>
              <input
                type="text"
                required
                className="input-field"
                value={formData.instrument}
                onChange={(e) => setFormData({ ...formData, instrument: e.target.value })}
                placeholder="piano, guitare, violon..."
              />
            </div>

            <div>
              <label className="block text-sm font-medium mb-2">Prix (€) *</label>
              <input
                type="number"
                step="0.01"
                required
                className="input-field"
                value={formData.price}
                onChange={(e) => setFormData({ ...formData, price: e.target.value })}
              />
            </div>

            <div>
              <label className="block text-sm font-medium mb-2">Nombre de pages *</label>
              <input
                type="number"
                required
                className="input-field"
                value={formData.pages}
                onChange={(e) => setFormData({ ...formData, pages: e.target.value })}
              />
            </div>
          </div>

          <div>
            <label className="block text-sm font-medium mb-2">Description *</label>
            <textarea
              required
              rows={4}
              className="input-field"
              value={formData.description}
              onChange={(e) => setFormData({ ...formData, description: e.target.value })}
            />
          </div>

          <div>
            <label className="block text-sm font-medium mb-2">Chemin du fichier PDF</label>
            <input
              type="text"
              className="input-field"
              value={formData.pdfFilePath}
              onChange={(e) => setFormData({ ...formData, pdfFilePath: e.target.value })}
              placeholder="/uploads/sheets/partition.pdf"
            />
          </div>

          <div>
            <label className="block text-sm font-medium mb-2">URL image de couverture</label>
            <input
              type="url"
              className="input-field"
              value={formData.coverImageUrl}
              onChange={(e) => setFormData({ ...formData, coverImageUrl: e.target.value })}
              placeholder="https://..."
            />
          </div>

          <div className="flex space-x-4">
            <button
              type="submit"
              disabled={saving}
              className="btn-primary disabled:opacity-50"
            >
              {saving ? 'Enregistrement...' : 'Créer la partition'}
            </button>
            <Link href="/admin/sheets" className="btn-secondary">
              Annuler
            </Link>
          </div>
        </form>
      </div>
    </div>
  )
}
