import Link from 'next/link'
import { Music, Mail, Facebook, Twitter, Instagram } from 'lucide-react'

export default function Footer() {
  return (
    <footer className="bg-gray-900 text-gray-300">
      <div className="container mx-auto px-4 py-12">
        <div className="grid md:grid-cols-4 gap-8">
          {/* Brand */}
          <div>
            <Link href="/" className="flex items-center space-x-2 mb-4">
              <Music className="text-primary-400" size={28} />
              <span className="text-xl font-bold text-white">MusicSheets</span>
            </Link>
            <p className="text-sm">
              Votre destination pour des partitions musicales de qualité professionnelle.
            </p>
          </div>

          {/* Quick Links */}
          <div>
            <h3 className="text-white font-semibold mb-4">Liens rapides</h3>
            <ul className="space-y-2">
              <li>
                <Link href="/catalog" className="hover:text-white transition-colors">
                  Catalogue
                </Link>
              </li>
              <li>
                <Link href="/about" className="hover:text-white transition-colors">
                  À propos
                </Link>
              </li>
              <li>
                <Link href="/contact" className="hover:text-white transition-colors">
                  Contact
                </Link>
              </li>
            </ul>
          </div>

          {/* Categories */}
          <div>
            <h3 className="text-white font-semibold mb-4">Catégories</h3>
            <ul className="space-y-2">
              <li>
                <Link href="/catalog?genre=classical" className="hover:text-white transition-colors">
                  Classique
                </Link>
              </li>
              <li>
                <Link href="/catalog?genre=jazz" className="hover:text-white transition-colors">
                  Jazz
                </Link>
              </li>
              <li>
                <Link href="/catalog?genre=pop" className="hover:text-white transition-colors">
                  Pop
                </Link>
              </li>
              <li>
                <Link href="/catalog?genre=rock" className="hover:text-white transition-colors">
                  Rock
                </Link>
              </li>
            </ul>
          </div>

          {/* Contact & Social */}
          <div>
            <h3 className="text-white font-semibold mb-4">Contact</h3>
            <div className="space-y-2 mb-4">
              <p className="flex items-center">
                <Mail size={16} className="mr-2" />
                contact@musicsheets.com
              </p>
            </div>
            <div className="flex space-x-4">
              <a href="#" className="hover:text-white transition-colors">
                <Facebook size={20} />
              </a>
              <a href="#" className="hover:text-white transition-colors">
                <Twitter size={20} />
              </a>
              <a href="#" className="hover:text-white transition-colors">
                <Instagram size={20} />
              </a>
            </div>
          </div>
        </div>

        <div className="border-t border-gray-800 mt-8 pt-8 text-center text-sm">
          <p>&copy; {new Date().getFullYear()} MusicSheets. Tous droits réservés.</p>
        </div>
      </div>
    </footer>
  )
}
