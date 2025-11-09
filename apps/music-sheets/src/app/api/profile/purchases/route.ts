import { NextResponse } from 'next/server'
import { getSession } from '@/lib/session'
import { getDb } from '@/lib/db'

export const dynamic = 'force-dynamic'

export async function GET() {
  try {
    const session = await getSession()
    if (!session) {
      return NextResponse.json({ error: 'Unauthorized' }, { status: 401 })
    }

    const db = getDb()
    const purchases = db.prepare(`
      SELECT
        p.id,
        p.sheet_id as sheetId,
        p.order_id as orderId,
        p.purchased_at as purchasedAt,
        s.title,
        s.composer,
        s.price,
        s.pdf_file_path as pdfFilePath
      FROM purchases p
      JOIN music_sheets s ON p.sheet_id = s.id
      WHERE p.user_id = ?
      ORDER BY p.purchased_at DESC
    `).all(session.user.id)

    // Transform data to match expected format
    const result = (purchases as any[]).map((p) => ({
      id: p.id,
      sheetId: p.sheetId,
      orderId: p.orderId,
      purchasedAt: p.purchasedAt,
      sheet: {
        id: p.sheetId,
        title: p.title,
        composer: p.composer,
        price: p.price,
        pdfFilePath: p.pdfFilePath
      }
    }))

    return NextResponse.json(result)
  } catch (error) {
    console.error('Error fetching purchases:', error)
    return NextResponse.json({ error: 'Server error' }, { status: 500 })
  }
}
