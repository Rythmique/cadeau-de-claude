import { NextRequest, NextResponse } from 'next/server'
import { getDb } from '@/lib/db'

export const dynamic = 'force-dynamic'

export async function POST(request: NextRequest) {
  try {
    const body = await request.json()
    const { customerEmail, customerName, items, total } = body

    if (!customerEmail || !items || items.length === 0) {
      return NextResponse.json({ error: 'Invalid request' }, { status: 400 })
    }

    const db = getDb()

    // Create order
    const orderResult = db.prepare(`
      INSERT INTO orders (customer_email, total, status)
      VALUES (?, ?, ?)
    `).run(customerEmail, total, 'completed')

    const orderId = orderResult.lastInsertRowid

    // Create order items
    const insertOrderItem = db.prepare(`
      INSERT INTO order_items (order_id, sheet_id, quantity, price)
      VALUES (?, ?, ?, ?)
    `)

    for (const item of items) {
      insertOrderItem.run(orderId, item.sheetId, item.quantity, item.price)
    }

    return NextResponse.json({
      id: orderId,
      customerEmail,
      total,
      status: 'completed',
    })
  } catch (error) {
    console.error('Error creating order:', error)
    return NextResponse.json({ error: 'Failed to create order' }, { status: 500 })
  }
}
