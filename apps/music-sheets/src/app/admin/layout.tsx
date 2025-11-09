import { redirect } from 'next/navigation'
import { getSession } from '@/lib/session'
import Link from 'next/link'
import { LayoutDashboard, Music, ShoppingBag, Users, LogOut } from 'lucide-react'

export default async function AdminLayout({
  children,
}: {
  children: React.ReactNode
}) {
  const session = await getSession()

  if (!session || session.user.role !== 'admin') {
    redirect('/login')
  }

  return (
    <div className="flex min-h-screen bg-gray-100">
      {/* Sidebar */}
      <aside className="w-64 bg-white shadow-md">
        <div className="p-6">
          <h2 className="text-2xl font-bold text-gray-900">Administration</h2>
          <p className="text-sm text-gray-600 mt-1">MusicSheets</p>
        </div>

        <nav className="px-4 space-y-2">
          <Link
            href="/admin"
            className="flex items-center space-x-3 px-4 py-3 text-gray-700 hover:bg-primary-50 hover:text-primary-600 rounded-lg transition-colors"
          >
            <LayoutDashboard size={20} />
            <span>Tableau de bord</span>
          </Link>

          <Link
            href="/admin/sheets"
            className="flex items-center space-x-3 px-4 py-3 text-gray-700 hover:bg-primary-50 hover:text-primary-600 rounded-lg transition-colors"
          >
            <Music size={20} />
            <span>Partitions</span>
          </Link>

          <Link
            href="/admin/orders"
            className="flex items-center space-x-3 px-4 py-3 text-gray-700 hover:bg-primary-50 hover:text-primary-600 rounded-lg transition-colors"
          >
            <ShoppingBag size={20} />
            <span>Commandes</span>
          </Link>

          <Link
            href="/admin/users"
            className="flex items-center space-x-3 px-4 py-3 text-gray-700 hover:bg-primary-50 hover:text-primary-600 rounded-lg transition-colors"
          >
            <Users size={20} />
            <span>Utilisateurs</span>
          </Link>
        </nav>

        <div className="absolute bottom-0 w-64 p-4 border-t">
          <Link
            href="/"
            className="flex items-center space-x-3 px-4 py-3 text-gray-700 hover:bg-gray-100 rounded-lg transition-colors"
          >
            <LogOut size={20} />
            <span>Retour au site</span>
          </Link>
        </div>
      </aside>

      {/* Main content */}
      <main className="flex-1 p-8">
        {children}
      </main>
    </div>
  )
}
