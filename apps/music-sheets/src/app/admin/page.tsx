import { getDb } from '@/lib/db'
import { Music, ShoppingBag, Users, DollarSign } from 'lucide-react'

export const dynamic = 'force-dynamic'

export default function AdminDashboard() {
  const db = getDb()

  // Get statistics
  const stats = {
    totalSheets: db.prepare('SELECT COUNT(*) as count FROM music_sheets').get() as { count: number },
    totalOrders: db.prepare('SELECT COUNT(*) as count FROM orders').get() as { count: number },
    totalUsers: db.prepare('SELECT COUNT(*) as count FROM users').get() as { count: number },
    totalRevenue: db.prepare('SELECT COALESCE(SUM(total), 0) as total FROM orders WHERE status = "completed"').get() as { total: number }
  }

  // Get recent orders
  const recentOrders = db.prepare(`
    SELECT
      id, customer_email, total, status, created_at
    FROM orders
    ORDER BY created_at DESC
    LIMIT 5
  `).all() as Array<{
    id: number
    customer_email: string
    total: number
    status: string
    created_at: string
  }>

  return (
    <div>
      <h1 className="text-3xl font-bold mb-8">Tableau de bord</h1>

      {/* Stats Cards */}
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
        <div className="card p-6">
          <div className="flex items-center justify-between">
            <div>
              <p className="text-gray-600 text-sm">Partitions</p>
              <p className="text-3xl font-bold mt-2">{stats.totalSheets.count}</p>
            </div>
            <div className="w-12 h-12 bg-blue-100 rounded-full flex items-center justify-center">
              <Music className="text-blue-600" size={24} />
            </div>
          </div>
        </div>

        <div className="card p-6">
          <div className="flex items-center justify-between">
            <div>
              <p className="text-gray-600 text-sm">Commandes</p>
              <p className="text-3xl font-bold mt-2">{stats.totalOrders.count}</p>
            </div>
            <div className="w-12 h-12 bg-green-100 rounded-full flex items-center justify-center">
              <ShoppingBag className="text-green-600" size={24} />
            </div>
          </div>
        </div>

        <div className="card p-6">
          <div className="flex items-center justify-between">
            <div>
              <p className="text-gray-600 text-sm">Utilisateurs</p>
              <p className="text-3xl font-bold mt-2">{stats.totalUsers.count}</p>
            </div>
            <div className="w-12 h-12 bg-purple-100 rounded-full flex items-center justify-center">
              <Users className="text-purple-600" size={24} />
            </div>
          </div>
        </div>

        <div className="card p-6">
          <div className="flex items-center justify-between">
            <div>
              <p className="text-gray-600 text-sm">Revenus</p>
              <p className="text-3xl font-bold mt-2">{stats.totalRevenue.total.toFixed(2)} €</p>
            </div>
            <div className="w-12 h-12 bg-yellow-100 rounded-full flex items-center justify-center">
              <DollarSign className="text-yellow-600" size={24} />
            </div>
          </div>
        </div>
      </div>

      {/* Recent Orders */}
      <div className="card p-6">
        <h2 className="text-xl font-bold mb-4">Commandes récentes</h2>

        {recentOrders.length === 0 ? (
          <p className="text-gray-500 text-center py-8">Aucune commande pour le moment</p>
        ) : (
          <div className="overflow-x-auto">
            <table className="w-full">
              <thead>
                <tr className="border-b">
                  <th className="text-left py-3 px-4">ID</th>
                  <th className="text-left py-3 px-4">Client</th>
                  <th className="text-left py-3 px-4">Total</th>
                  <th className="text-left py-3 px-4">Statut</th>
                  <th className="text-left py-3 px-4">Date</th>
                </tr>
              </thead>
              <tbody>
                {recentOrders.map((order) => (
                  <tr key={order.id} className="border-b hover:bg-gray-50">
                    <td className="py-3 px-4">#{order.id}</td>
                    <td className="py-3 px-4">{order.customer_email}</td>
                    <td className="py-3 px-4">{order.total.toFixed(2)} €</td>
                    <td className="py-3 px-4">
                      <span className={`px-2 py-1 rounded text-xs font-semibold ${
                        order.status === 'completed'
                          ? 'bg-green-100 text-green-800'
                          : order.status === 'pending'
                          ? 'bg-yellow-100 text-yellow-800'
                          : 'bg-red-100 text-red-800'
                      }`}>
                        {order.status}
                      </span>
                    </td>
                    <td className="py-3 px-4">{new Date(order.created_at).toLocaleDateString('fr-FR')}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </div>
    </div>
  )
}
