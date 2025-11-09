import { NextRequest, NextResponse } from 'next/server'
import { getSession } from '@/lib/session'
import { getDb } from '@/lib/db'
import fs from 'fs'
import path from 'path'

export const dynamic = 'force-dynamic'

export async function GET(
  request: NextRequest,
  { params }: { params: { sheetId: string } }
) {
  try {
    const session = await getSession()
    if (!session) {
      return NextResponse.json({ error: 'Unauthorized' }, { status: 401 })
    }

    const db = getDb()

    // Check if user has purchased this sheet
    const purchase = db.prepare(`
      SELECT id FROM purchases
      WHERE user_id = ? AND sheet_id = ?
    `).get(session.user.id, params.sheetId)

    if (!purchase) {
      return NextResponse.json(
        { error: 'Vous devez acheter cette partition pour la télécharger' },
        { status: 403 }
      )
    }

    // Get sheet info
    const sheet = db.prepare(`
      SELECT pdf_file_path, title FROM music_sheets WHERE id = ?
    `).get(params.sheetId) as { pdf_file_path: string | null; title: string } | undefined

    if (!sheet || !sheet.pdf_file_path) {
      return NextResponse.json(
        { error: 'Fichier PDF non disponible' },
        { status: 404 }
      )
    }

    // In production, serve the actual PDF file
    // For demo, return a download link
    return NextResponse.json({
      success: true,
      message: 'PDF download would start here',
      filename: `${sheet.title}.pdf`,
      note: 'En production, le téléchargement du PDF démarrerait automatiquement'
    })
  } catch (error) {
    console.error('Error downloading sheet:', error)
    return NextResponse.json({ error: 'Server error' }, { status: 500 })
  }
}
