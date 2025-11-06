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

export interface Order {
  id: number
  items: CartItem[]
  total: number
  customerEmail: string
  status: 'pending' | 'completed' | 'cancelled'
  createdAt: string
}
