'use client'

import Link from 'next/link'
import { Music, ShoppingCart, Menu, X, User, LogOut, Settings } from 'lucide-react'
import { useState, useEffect } from 'react'
import { useRouter } from 'next/navigation'
import { useCart } from '@/lib/store'
import type { User as UserType } from '@/types'

export default function Header() {
  const router = useRouter()
  const [mobileMenuOpen, setMobileMenuOpen] = useState(false)
  const [userMenuOpen, setUserMenuOpen] = useState(false)
  const [user, setUser] = useState<UserType | null>(null)
  const cartItems = useCart((state) => state.items)

  const cartItemCount = cartItems.reduce((sum, item) => sum + item.quantity, 0)

  useEffect(() => {
    checkAuth()
  }, [])

  const checkAuth = async () => {
    try {
      const response = await fetch('/api/auth/me')
      if (response.ok) {
        const data = await response.json()
        setUser(data.user)
      }
    } catch (error) {
      // Not authenticated
    }
  }

  const handleLogout = async () => {
    try {
      await fetch('/api/auth/logout', { method: 'POST' })
      setUser(null)
      router.push('/')
    } catch (error) {
      console.error('Logout error:', error)
    }
  }

  return (
    <header className="bg-white shadow-sm sticky top-0 z-50">
      <nav className="container mx-auto px-4 py-4">
        <div className="flex items-center justify-between">
          {/* Logo */}
          <Link href="/" className="flex items-center space-x-2">
            <Music className="text-primary-600" size={32} />
            <span className="text-2xl font-bold text-gray-900">MusicSheets</span>
          </Link>

          {/* Desktop Navigation */}
          <div className="hidden md:flex items-center space-x-8">
            <Link href="/" className="text-gray-700 hover:text-primary-600 transition-colors">
              Accueil
            </Link>
            <Link href="/catalog" className="text-gray-700 hover:text-primary-600 transition-colors">
              Catalogue
            </Link>
            <Link href="/about" className="text-gray-700 hover:text-primary-600 transition-colors">
              À propos
            </Link>
            <Link href="/contact" className="text-gray-700 hover:text-primary-600 transition-colors">
              Contact
            </Link>
          </div>

          {/* Cart, User Menu and Mobile Menu */}
          <div className="flex items-center space-x-4">
            <Link href="/cart" className="relative">
              <ShoppingCart className="text-gray-700 hover:text-primary-600 transition-colors" size={24} />
              {cartItemCount > 0 && (
                <span className="absolute -top-2 -right-2 bg-primary-600 text-white text-xs rounded-full w-5 h-5 flex items-center justify-center">
                  {cartItemCount}
                </span>
              )}
            </Link>

            {/* User Menu */}
            {user ? (
              <div className="relative hidden md:block">
                <button
                  onClick={() => setUserMenuOpen(!userMenuOpen)}
                  className="flex items-center space-x-2 text-gray-700 hover:text-primary-600 transition-colors"
                >
                  <User size={24} />
                  <span className="text-sm">{user.name}</span>
                </button>

                {userMenuOpen && (
                  <div className="absolute right-0 mt-2 w-48 bg-white rounded-lg shadow-lg border py-2">
                    <Link
                      href="/profile"
                      className="block px-4 py-2 text-gray-700 hover:bg-gray-100"
                      onClick={() => setUserMenuOpen(false)}
                    >
                      <User className="inline mr-2" size={16} />
                      Mon profil
                    </Link>
                    {user.role === 'admin' && (
                      <Link
                        href="/admin"
                        className="block px-4 py-2 text-gray-700 hover:bg-gray-100"
                        onClick={() => setUserMenuOpen(false)}
                      >
                        <Settings className="inline mr-2" size={16} />
                        Administration
                      </Link>
                    )}
                    <button
                      onClick={() => {
                        setUserMenuOpen(false)
                        handleLogout()
                      }}
                      className="block w-full text-left px-4 py-2 text-gray-700 hover:bg-gray-100"
                    >
                      <LogOut className="inline mr-2" size={16} />
                      Déconnexion
                    </button>
                  </div>
                )}
              </div>
            ) : (
              <div className="hidden md:flex items-center space-x-2">
                <Link href="/login" className="btn-secondary px-4 py-2">
                  Connexion
                </Link>
                <Link href="/register" className="btn-primary px-4 py-2">
                  Inscription
                </Link>
              </div>
            )}

            {/* Mobile Menu Button */}
            <button
              className="md:hidden"
              onClick={() => setMobileMenuOpen(!mobileMenuOpen)}
            >
              {mobileMenuOpen ? <X size={24} /> : <Menu size={24} />}
            </button>
          </div>
        </div>

        {/* Mobile Menu */}
        {mobileMenuOpen && (
          <div className="md:hidden mt-4 pb-4 border-t pt-4">
            <div className="flex flex-col space-y-4">
              <Link href="/" className="text-gray-700 hover:text-primary-600 transition-colors">
                Accueil
              </Link>
              <Link href="/catalog" className="text-gray-700 hover:text-primary-600 transition-colors">
                Catalogue
              </Link>
              <Link href="/about" className="text-gray-700 hover:text-primary-600 transition-colors">
                À propos
              </Link>
              <Link href="/contact" className="text-gray-700 hover:text-primary-600 transition-colors">
                Contact
              </Link>

              {user ? (
                <>
                  <Link href="/profile" className="text-gray-700 hover:text-primary-600 transition-colors">
                    <User className="inline mr-2" size={16} />
                    Mon profil
                  </Link>
                  {user.role === 'admin' && (
                    <Link href="/admin" className="text-gray-700 hover:text-primary-600 transition-colors">
                      <Settings className="inline mr-2" size={16} />
                      Administration
                    </Link>
                  )}
                  <button
                    onClick={handleLogout}
                    className="text-left text-gray-700 hover:text-primary-600 transition-colors"
                  >
                    <LogOut className="inline mr-2" size={16} />
                    Déconnexion
                  </button>
                </>
              ) : (
                <>
                  <Link href="/login" className="btn-secondary px-4 py-2 text-center">
                    Connexion
                  </Link>
                  <Link href="/register" className="btn-primary px-4 py-2 text-center">
                    Inscription
                  </Link>
                </>
              )}
            </div>
          </div>
        )}
      </nav>
    </header>
  )
}
