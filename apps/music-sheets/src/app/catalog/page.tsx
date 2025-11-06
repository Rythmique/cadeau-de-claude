'use client'

import { useState, useEffect } from 'react'
import { Search, Filter } from 'lucide-react'
import SheetCard from '@/components/SheetCard'
import type { MusicSheet } from '@/types'

export default function CatalogPage() {
  const [sheets, setSheets] = useState<MusicSheet[]>([])
  const [searchQuery, setSearchQuery] = useState('')
  const [selectedGenre, setSelectedGenre] = useState('')
  const [selectedDifficulty, setSelectedDifficulty] = useState('')
  const [selectedInstrument, setSelectedInstrument] = useState('')
  const [showFilters, setShowFilters] = useState(false)
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    fetchSheets()
  }, [searchQuery, selectedGenre, selectedDifficulty, selectedInstrument])

  const fetchSheets = async () => {
    setLoading(true)
    try {
      const params = new URLSearchParams()
      if (searchQuery) params.append('q', searchQuery)
      if (selectedGenre) params.append('genre', selectedGenre)
      if (selectedDifficulty) params.append('difficulty', selectedDifficulty)
      if (selectedInstrument) params.append('instrument', selectedInstrument)

      const response = await fetch(`/api/sheets?${params.toString()}`)
      const data = await response.json()
      setSheets(data)
    } catch (error) {
      console.error('Error fetching sheets:', error)
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className="container mx-auto px-4 py-8">
      <h1 className="text-4xl font-bold mb-8">Catalogue de partitions</h1>

      {/* Search and Filter Bar */}
      <div className="bg-white p-4 rounded-lg shadow-md mb-8">
        <div className="flex flex-col md:flex-row gap-4">
          {/* Search */}
          <div className="flex-grow relative">
            <Search className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400" size={20} />
            <input
              type="text"
              placeholder="Rechercher une partition, un compositeur..."
              className="input-field pl-10"
              value={searchQuery}
              onChange={(e) => setSearchQuery(e.target.value)}
            />
          </div>

          {/* Filter Toggle */}
          <button
            onClick={() => setShowFilters(!showFilters)}
            className="btn-secondary flex items-center justify-center"
          >
            <Filter size={20} className="mr-2" />
            Filtres
          </button>
        </div>

        {/* Filters */}
        {showFilters && (
          <div className="grid md:grid-cols-3 gap-4 mt-4 pt-4 border-t">
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-2">Genre</label>
              <select
                className="input-field"
                value={selectedGenre}
                onChange={(e) => setSelectedGenre(e.target.value)}
              >
                <option value="">Tous les genres</option>
                <option value="classical">Classique</option>
                <option value="jazz">Jazz</option>
                <option value="pop">Pop</option>
                <option value="rock">Rock</option>
                <option value="blues">Blues</option>
              </select>
            </div>

            <div>
              <label className="block text-sm font-medium text-gray-700 mb-2">Difficulté</label>
              <select
                className="input-field"
                value={selectedDifficulty}
                onChange={(e) => setSelectedDifficulty(e.target.value)}
              >
                <option value="">Tous les niveaux</option>
                <option value="beginner">Débutant</option>
                <option value="intermediate">Intermédiaire</option>
                <option value="advanced">Avancé</option>
                <option value="expert">Expert</option>
              </select>
            </div>

            <div>
              <label className="block text-sm font-medium text-gray-700 mb-2">Instrument</label>
              <select
                className="input-field"
                value={selectedInstrument}
                onChange={(e) => setSelectedInstrument(e.target.value)}
              >
                <option value="">Tous les instruments</option>
                <option value="piano">Piano</option>
                <option value="guitar">Guitare</option>
                <option value="violin">Violon</option>
                <option value="saxophone">Saxophone</option>
                <option value="flute">Flûte</option>
              </select>
            </div>
          </div>
        )}
      </div>

      {/* Results */}
      <div className="mb-4 text-gray-600">
        {loading ? 'Chargement...' : `${sheets.length} partition${sheets.length > 1 ? 's' : ''} trouvée${sheets.length > 1 ? 's' : ''}`}
      </div>

      {/* Sheets Grid */}
      {loading ? (
        <div className="text-center py-12">
          <p className="text-gray-500">Chargement des partitions...</p>
        </div>
      ) : sheets.length === 0 ? (
        <div className="text-center py-12">
          <p className="text-gray-500">Aucune partition trouvée. Essayez de modifier vos critères de recherche.</p>
        </div>
      ) : (
        <div className="grid md:grid-cols-2 lg:grid-cols-4 gap-6">
          {sheets.map((sheet) => (
            <SheetCard key={sheet.id} sheet={sheet} />
          ))}
        </div>
      )}
    </div>
  )
}
