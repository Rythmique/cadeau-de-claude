import { NextRequest, NextResponse } from 'next/server'
import { getSession } from '@/lib/session'
import { getDb } from '@/lib/db'

export const dynamic = 'force-dynamic'

export async function PUT(
  request: NextRequest,
  { params }: { params: { id: string } }
) {
  try {
    const session = await getSession()
    if (!session || session.user.role !== 'admin') {
      return NextResponse.json({ error: 'Unauthorized' }, { status: 401 })
    }

    const body = await request.json()
    const db = getDb()

    db.prepare(`
      UPDATE music_sheets
      SET title = ?, composer = ?, arranger = ?, genre = ?, difficulty = ?,
          instrument = ?, price = ?, description = ?, pages = ?,
          pdf_file_path = ?, cover_image_url = ?
      WHERE id = ?
    `).run(
      body.title,
      body.composer,
      body.arranger || null,
      body.genre,
      body.difficulty,
      body.instrument,
      body.price,
      body.description,
      body.pages,
      body.pdfFilePath || null,
      body.coverImageUrl || null,
      params.id
    )

    return NextResponse.json({ success: true })
  } catch (error) {
    console.error('Error updating sheet:', error)
    return NextResponse.json({ error: 'Server error' }, { status: 500 })
  }
}

export async function DELETE(
  request: NextRequest,
  { params }: { params: { id: string } }
) {
  try {
    const session = await getSession()
    if (!session || session.user.role !== 'admin') {
      return NextResponse.json({ error: 'Unauthorized' }, { status: 401 })
    }

    const db = getDb()
    db.prepare('DELETE FROM music_sheets WHERE id = ?').run(params.id)

    return NextResponse.json({ success: true })
  } catch (error) {
    console.error('Error deleting sheet:', error)
    return NextResponse.json({ error: 'Server error' }, { status: 500 })
  }
}
