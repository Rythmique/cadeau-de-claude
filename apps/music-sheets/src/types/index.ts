export interface MusicSheet {
  id: number
  title: string
  composer: string
  arranger?: string
  genre: string
  difficulty: 'beginner' | 'intermediate' | 'advanced' | 'expert'
  instrument: string
  price: number
  description: string
  pages: number
  pdfFilePath?: string
  previewUrl?: string
  coverImageUrl?: string
  rating: number
  reviewCount: number
  createdAt: string
}

export interface CartItem {
  sheetId: number
  quantity: number
  sheet: MusicSheet
}

export interface User {
  id: number
  email: string
  name: string
  role: 'user' | 'admin'
  createdAt: string
}

export interface Session {
  id: string
  userId: number
  expiresAt: string
  createdAt: string
}

export interface Order {
  id: number
  userId?: number
  items: CartItem[]
  total: number
  customerEmail: string
  customerName?: string
  status: 'pending' | 'completed' | 'cancelled'
  paymentMethod?: string
  paymentLink?: string
  createdAt: string
}

export interface Review {
  id: number
  userId: number
  sheetId: number
  rating: number
  comment?: string
  userName?: string
  createdAt: string
}

export interface Purchase {
  id: number
  userId: number
  sheetId: number
  orderId: number
  purchasedAt: string
}
