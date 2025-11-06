import { NextRequest, NextResponse } from 'next/server'
import { getAllSheets, searchSheets } from '@/lib/db'

export const dynamic = 'force-dynamic'

export async function GET(request: NextRequest) {
  try {
    const searchParams = request.nextUrl.searchParams
    const query = searchParams.get('q') || ''
    const genre = searchParams.get('genre') || undefined
    const difficulty = searchParams.get('difficulty') || undefined
    const instrument = searchParams.get('instrument') || undefined

    let sheets

    if (query || genre || difficulty || instrument) {
      sheets = searchSheets(query, {
        genre,
        difficulty,
        instrument,
      })
    } else {
      sheets = getAllSheets()
    }

    return NextResponse.json(sheets)
  } catch (error) {
    console.error('Error fetching sheets:', error)
    return NextResponse.json({ error: 'Failed to fetch sheets' }, { status: 500 })
  }
}
