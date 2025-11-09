import { NextRequest, NextResponse } from 'next/server'
import { getDb } from '@/lib/db'
import { getSession } from '@/lib/session'

export const dynamic = 'force-dynamic'

export async function GET(
  request: NextRequest,
  { params }: { params: { sheetId: string } }
) {
  try {
    const db = getDb()
    const reviews = db.prepare(`
      SELECT r.*, u.name as userName
      FROM reviews r
      JOIN users u ON r.user_id = u.id
      WHERE r.sheet_id = ?
      ORDER BY r.created_at DESC
    `).all(params.sheetId)

    return NextResponse.json(reviews)
  } catch (error) {
    console.error('Error fetching reviews:', error)
    return NextResponse.json({ error: 'Server error' }, { status: 500 })
  }
}

export async function POST(
  request: NextRequest,
  { params }: { params: { sheetId: string } }
) {
  try {
    const session = await getSession()
    if (!session) {
      return NextResponse.json({ error: 'Unauthorized' }, { status: 401 })
    }

    const body = await request.json()
    const db = getDb()

    // Check if user has purchased this sheet
    const purchase = db.prepare(`
      SELECT id FROM purchases
      WHERE user_id = ? AND sheet_id = ?
    `).get(session.user.id, params.sheetId)

    if (!purchase) {
      return NextResponse.json(
        { error: 'Vous devez acheter cette partition pour laisser un avis' },
        { status: 403 }
      )
    }

    // Insert or update review
    db.prepare(`
      INSERT INTO reviews (user_id, sheet_id, rating, comment)
      VALUES (?, ?, ?, ?)
      ON CONFLICT(user_id, sheet_id) DO UPDATE SET
        rating = excluded.rating,
        comment = excluded.comment,
        created_at = CURRENT_TIMESTAMP
    `).run(session.user.id, params.sheetId, body.rating, body.comment || null)

    // Update sheet rating
    const avgRating = db.prepare(`
      SELECT AVG(rating) as avg, COUNT(*) as count
      FROM reviews
      WHERE sheet_id = ?
    `).get(params.sheetId) as { avg: number; count: number }

    db.prepare(`
      UPDATE music_sheets
      SET rating = ?, review_count = ?
      WHERE id = ?
    `).run(avgRating.avg, avgRating.count, params.sheetId)

    return NextResponse.json({ success: true })
  } catch (error) {
    console.error('Error creating review:', error)
    return NextResponse.json({ error: 'Server error' }, { status: 500 })
  }
}
