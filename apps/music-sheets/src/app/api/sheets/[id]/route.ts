import { NextRequest, NextResponse } from 'next/server'
import { getSheetById } from '@/lib/db'

export const dynamic = 'force-dynamic'

export async function GET(
  request: NextRequest,
  { params }: { params: { id: string } }
) {
  try {
    const id = parseInt(params.id)

    if (isNaN(id)) {
      return NextResponse.json({ error: 'Invalid ID' }, { status: 400 })
    }

    const sheet = getSheetById(id)

    if (!sheet) {
      return NextResponse.json({ error: 'Sheet not found' }, { status: 404 })
    }

    return NextResponse.json(sheet)
  } catch (error) {
    console.error('Error fetching sheet:', error)
    return NextResponse.json({ error: 'Failed to fetch sheet' }, { status: 500 })
  }
}
