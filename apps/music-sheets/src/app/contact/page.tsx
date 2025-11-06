'use client'

import { useState } from 'react'
import { Mail, Phone, MapPin, Send } from 'lucide-react'

export default function ContactPage() {
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    subject: '',
    message: '',
  })
  const [submitted, setSubmitted] = useState(false)

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault()
    // Simulate form submission
    console.log('Form submitted:', formData)
    setSubmitted(true)
    setTimeout(() => {
      setSubmitted(false)
      setFormData({ name: '', email: '', subject: '', message: '' })
    }, 3000)
  }

  return (
    <div className="container mx-auto px-4 py-8">
      <div className="max-w-4xl mx-auto">
        <h1 className="text-5xl font-bold mb-6">Contactez-nous</h1>

        <p className="text-xl text-gray-600 mb-12">
          Une question ? Un problème ? Notre équipe est là pour vous aider.
        </p>

        <div className="grid md:grid-cols-2 gap-8 mb-12">
          {/* Contact Form */}
          <div className="card p-6">
            <h2 className="text-2xl font-bold mb-6">Envoyez-nous un message</h2>

            {submitted ? (
              <div className="bg-green-50 border border-green-200 rounded-lg p-4 text-center">
                <p className="text-green-800 font-semibold">
                  Message envoyé avec succès !
                </p>
                <p className="text-green-600 text-sm mt-1">
                  Nous vous répondrons dans les plus brefs délais.
                </p>
              </div>
            ) : (
              <form onSubmit={handleSubmit} className="space-y-4">
                <div>
                  <label className="block text-sm font-medium text-gray-700 mb-2">
                    Nom
                  </label>
                  <input
                    type="text"
                    required
                    className="input-field"
                    value={formData.name}
                    onChange={(e) => setFormData({ ...formData, name: e.target.value })}
                  />
                </div>

                <div>
                  <label className="block text-sm font-medium text-gray-700 mb-2">
                    Email
                  </label>
                  <input
                    type="email"
                    required
                    className="input-field"
                    value={formData.email}
                    onChange={(e) => setFormData({ ...formData, email: e.target.value })}
                  />
                </div>

                <div>
                  <label className="block text-sm font-medium text-gray-700 mb-2">
                    Sujet
                  </label>
                  <input
                    type="text"
                    required
                    className="input-field"
                    value={formData.subject}
                    onChange={(e) => setFormData({ ...formData, subject: e.target.value })}
                  />
                </div>

                <div>
                  <label className="block text-sm font-medium text-gray-700 mb-2">
                    Message
                  </label>
                  <textarea
                    required
                    rows={5}
                    className="input-field"
                    value={formData.message}
                    onChange={(e) => setFormData({ ...formData, message: e.target.value })}
                  />
                </div>

                <button type="submit" className="btn-primary w-full flex items-center justify-center">
                  <Send size={20} className="mr-2" />
                  Envoyer
                </button>
              </form>
            )}
          </div>

          {/* Contact Info */}
          <div>
            <div className="card p-6 mb-6">
              <h2 className="text-2xl font-bold mb-6">Informations de contact</h2>

              <div className="space-y-6">
                <div className="flex items-start">
                  <Mail className="text-primary-600 mr-4 mt-1" size={24} />
                  <div>
                    <h3 className="font-semibold mb-1">Email</h3>
                    <a
                      href="mailto:contact@musicsheets.com"
                      className="text-gray-600 hover:text-primary-600"
                    >
                      contact@musicsheets.com
                    </a>
                  </div>
                </div>

                <div className="flex items-start">
                  <Phone className="text-primary-600 mr-4 mt-1" size={24} />
                  <div>
                    <h3 className="font-semibold mb-1">Téléphone</h3>
                    <a
                      href="tel:+33123456789"
                      className="text-gray-600 hover:text-primary-600"
                    >
                      +33 1 23 45 67 89
                    </a>
                  </div>
                </div>

                <div className="flex items-start">
                  <MapPin className="text-primary-600 mr-4 mt-1" size={24} />
                  <div>
                    <h3 className="font-semibold mb-1">Adresse</h3>
                    <p className="text-gray-600">
                      123 Rue de la Musique<br />
                      75001 Paris, France
                    </p>
                  </div>
                </div>
              </div>
            </div>

            <div className="card p-6">
              <h3 className="font-bold mb-4">Horaires d'ouverture</h3>
              <div className="space-y-2 text-sm">
                <div className="flex justify-between">
                  <span className="text-gray-600">Lundi - Vendredi:</span>
                  <span className="font-semibold">9h00 - 18h00</span>
                </div>
                <div className="flex justify-between">
                  <span className="text-gray-600">Samedi:</span>
                  <span className="font-semibold">10h00 - 16h00</span>
                </div>
                <div className="flex justify-between">
                  <span className="text-gray-600">Dimanche:</span>
                  <span className="font-semibold">Fermé</span>
                </div>
              </div>
            </div>
          </div>
        </div>

        <div className="bg-blue-50 border border-blue-200 rounded-lg p-6">
          <h3 className="font-bold mb-2">Questions fréquentes</h3>
          <p className="text-gray-600 mb-4">
            Consultez notre FAQ pour obtenir des réponses rapides aux questions les plus courantes.
          </p>
          <a href="#" className="text-primary-600 hover:underline font-semibold">
            Voir la FAQ →
          </a>
        </div>
      </div>
    </div>
  )
}
