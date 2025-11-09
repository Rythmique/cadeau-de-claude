import { NextRequest, NextResponse } from 'next/server'
import { getDb } from '@/lib/db'
import { getSession } from '@/lib/session'

export const dynamic = 'force-dynamic'

export async function POST(request: NextRequest) {
  try {
    const body = await request.json()
    const { customerEmail, customerName, items, total, paymentMethod } = body

    if (!customerEmail || !items || items.length === 0) {
      return NextResponse.json({ error: 'Invalid request' }, { status: 400 })
    }

    const db = getDb()
    const session = await getSession()

    // Generate payment link placeholder (you'll replace this with actual payment links later)
    const paymentLink = `https://payment-link-placeholder.com/pay/${Date.now()}`

    // Create order
    const orderResult = db.prepare(`
      INSERT INTO orders (user_id, customer_email, customer_name, total, status, payment_method, payment_link)
      VALUES (?, ?, ?, ?, ?, ?, ?)
    `).run(session?.user.id || null, customerEmail, customerName, total, 'completed', paymentMethod, paymentLink)

    const orderId = orderResult.lastInsertRowid

    // Create order items
    const insertOrderItem = db.prepare(`
      INSERT INTO order_items (order_id, sheet_id, quantity, price)
      VALUES (?, ?, ?, ?)
    `)

    for (const item of items) {
      insertOrderItem.run(orderId, item.sheetId, item.quantity, item.price)
    }

    // If user is logged in, create purchases
    if (session) {
      const insertPurchase = db.prepare(`
        INSERT INTO purchases (user_id, sheet_id, order_id)
        VALUES (?, ?, ?)
      `)

      for (const item of items) {
        insertPurchase.run(session.user.id, item.sheetId, orderId)
      }
    }

    return NextResponse.json({
      id: orderId,
      customerEmail,
      total,
      status: 'completed',
      paymentLink,
    })
  } catch (error) {
    console.error('Error creating order:', error)
    return NextResponse.json({ error: 'Failed to create order' }, { status: 500 })
  }
}
