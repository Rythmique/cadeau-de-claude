import { create } from 'zustand'
import { persist } from 'zustand/middleware'
import type { CartItem, MusicSheet } from '@/types'

interface CartState {
  items: CartItem[]
  addItem: (sheet: MusicSheet) => void
  removeItem: (sheetId: number) => void
  updateQuantity: (sheetId: number, quantity: number) => void
  clearCart: () => void
  getTotalPrice: () => number
}

export const useCart = create<CartState>()(
  persist(
    (set, get) => ({
      items: [],

      addItem: (sheet) => {
        set((state) => {
          const existingItem = state.items.find((item) => item.sheetId === sheet.id)

          if (existingItem) {
            return {
              items: state.items.map((item) =>
                item.sheetId === sheet.id
                  ? { ...item, quantity: item.quantity + 1 }
                  : item
              ),
            }
          }

          return {
            items: [...state.items, { sheetId: sheet.id, quantity: 1, sheet }],
          }
        })
      },

      removeItem: (sheetId) => {
        set((state) => ({
          items: state.items.filter((item) => item.sheetId !== sheetId),
        }))
      },

      updateQuantity: (sheetId, quantity) => {
        if (quantity <= 0) {
          get().removeItem(sheetId)
          return
        }

        set((state) => ({
          items: state.items.map((item) =>
            item.sheetId === sheetId ? { ...item, quantity } : item
          ),
        }))
      },

      clearCart: () => {
        set({ items: [] })
      },

      getTotalPrice: () => {
        return get().items.reduce((total, item) => {
          return total + item.sheet.price * item.quantity
        }, 0)
      },
    }),
    {
      name: 'cart-storage',
    }
  )
)
