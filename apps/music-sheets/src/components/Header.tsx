'use client'

import Link from 'next/link'
import { Music, ShoppingCart, Search, Menu, X } from 'lucide-react'
import { useState } from 'react'
import { useCart } from '@/lib/store'

export default function Header() {
  const [mobileMenuOpen, setMobileMenuOpen] = useState(false)
  const cartItems = useCart((state) => state.items)

  const cartItemCount = cartItems.reduce((sum, item) => sum + item.quantity, 0)

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

          {/* Cart and Mobile Menu */}
          <div className="flex items-center space-x-4">
            <Link href="/cart" className="relative">
              <ShoppingCart className="text-gray-700 hover:text-primary-600 transition-colors" size={24} />
              {cartItemCount > 0 && (
                <span className="absolute -top-2 -right-2 bg-primary-600 text-white text-xs rounded-full w-5 h-5 flex items-center justify-center">
                  {cartItemCount}
                </span>
              )}
            </Link>

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
            </div>
          </div>
        )}
      </nav>
    </header>
  )
}
