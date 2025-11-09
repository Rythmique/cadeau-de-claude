'use client'

import { useState, useEffect } from 'react'
import { Star } from 'lucide-react'
import type { Review } from '@/types'

interface ReviewSectionProps {
  sheetId: number
}

export default function ReviewSection({ sheetId }: ReviewSectionProps) {
  const [reviews, setReviews] = useState<Review[]>([])
  const [canReview, setCanReview] = useState(false)
  const [userReview, setUserReview] = useState({ rating: 5, comment: '' })
  const [loading, setLoading] = useState(true)
  const [submitting, setSubmitting] = useState(false)

  useEffect(() => {
    fetchReviews()
    checkCanReview()
  }, [sheetId])

  const fetchReviews = async () => {
    try {
      const response = await fetch(`/api/reviews/${sheetId}`)
      if (response.ok) {
        const data = await response.json()
        setReviews(data)
      }
    } catch (error) {
      console.error('Error fetching reviews:', error)
    } finally {
      setLoading(false)
    }
  }

  const checkCanReview = async () => {
    try {
      const response = await fetch('/api/auth/me')
      if (response.ok) {
        setCanReview(true)
      }
    } catch (error) {
      // Not logged in
    }
  }

  const handleSubmitReview = async (e: React.FormEvent) => {
    e.preventDefault()
    setSubmitting(true)

    try {
      const response = await fetch(`/api/reviews/${sheetId}`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(userReview)
      })

      if (response.ok) {
        fetchReviews()
        setUserReview({ rating: 5, comment: '' })
      } else {
        const data = await response.json()
        alert(data.error || 'Erreur lors de l\'envoi de l\'avis')
      }
    } catch (error) {
      alert('Erreur lors de l\'envoi de l\'avis')
    } finally {
      setSubmitting(false)
    }
  }

  return (
    <div id="review" className="mt-12">
      <h2 className="text-2xl font-bold mb-6">Avis des clients</h2>

      {/* Review form */}
      {canReview && (
        <div className="card p-6 mb-6">
          <h3 className="text-xl font-semibold mb-4">Laisser un avis</h3>
          <form onSubmit={handleSubmitReview} className="space-y-4">
            <div>
              <label className="block text-sm font-medium mb-2">Note</label>
              <div className="flex space-x-2">
                {[1, 2, 3, 4, 5].map((star) => (
                  <button
                    key={star}
                    type="button"
                    onClick={() => setUserReview({ ...userReview, rating: star })}
                    className="focus:outline-none"
                  >
                    <Star
                      size={32}
                      className={star <= userReview.rating ? 'fill-yellow-400 text-yellow-400' : 'text-gray-300'}
                    />
                  </button>
                ))}
              </div>
            </div>

            <div>
              <label className="block text-sm font-medium mb-2">Commentaire</label>
              <textarea
                className="input-field"
                rows={4}
                value={userReview.comment}
                onChange={(e) => setUserReview({ ...userReview, comment: e.target.value })}
                placeholder="Partagez votre expérience avec cette partition..."
              />
            </div>

            <button
              type="submit"
              disabled={submitting}
              className="btn-primary disabled:opacity-50"
            >
              {submitting ? 'Envoi...' : 'Publier l\'avis'}
            </button>
          </form>
        </div>
      )}

      {/* Reviews list */}
      <div className="space-y-4">
        {loading ? (
          <p>Chargement des avis...</p>
        ) : reviews.length === 0 ? (
          <p className="text-gray-500">Aucun avis pour le moment. Soyez le premier à donner votre avis!</p>
        ) : (
          reviews.map((review) => (
            <div key={review.id} className="card p-6">
              <div className="flex items-start justify-between mb-2">
                <div>
                  <p className="font-semibold">{review.userName}</p>
                  <div className="flex items-center mt-1">
                    {[...Array(5)].map((_, i) => (
                      <Star
                        key={i}
                        size={16}
                        className={i < review.rating ? 'fill-yellow-400 text-yellow-400' : 'text-gray-300'}
                      />
                    ))}
                  </div>
                </div>
                <span className="text-sm text-gray-500">
                  {new Date(review.createdAt).toLocaleDateString('fr-FR')}
                </span>
              </div>
              {review.comment && (
                <p className="text-gray-700 mt-2">{review.comment}</p>
              )}
            </div>
          ))
        )}
      </div>
    </div>
  )
}
