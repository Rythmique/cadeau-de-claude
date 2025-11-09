import { NextRequest, NextResponse } from 'next/server'
import { getSession } from '@/lib/session'
import { getAllSheets, getDb } from '@/lib/db'

export const dynamic = 'force-dynamic'

export async function GET() {
  try {
    const session = await getSession()
    if (!session || session.user.role !== 'admin') {
      return NextResponse.json({ error: 'Unauthorized' }, { status: 401 })
    }

    const sheets = getAllSheets()
    return NextResponse.json(sheets)
  } catch (error) {
    return NextResponse.json({ error: 'Server error' }, { status: 500 })
  }
}

export async function POST(request: NextRequest) {
  try {
    const session = await getSession()
    if (!session || session.user.role !== 'admin') {
      return NextResponse.json({ error: 'Unauthorized' }, { status: 401 })
    }

    const body = await request.json()
    const db = getDb()

    const result = db.prepare(`
      INSERT INTO music_sheets (
        title, composer, arranger, genre, difficulty, instrument,
        price, description, pages, pdf_file_path, cover_image_url
      ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
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
      body.coverImageUrl || null
    )

    return NextResponse.json({ id: result.lastInsertRowid })
  } catch (error) {
    console.error('Error creating sheet:', error)
    return NextResponse.json({ error: 'Server error' }, { status: 500 })
  }
}
