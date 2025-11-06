import Database from 'better-sqlite3'
import path from 'path'
import type { MusicSheet } from '@/types'

const dbPath = path.join(process.cwd(), 'data', 'music-sheets.db')
let db: Database.Database | null = null

export function getDb() {
  if (!db) {
    db = new Database(dbPath)
    initDb()
  }
  return db
}

function initDb() {
  if (!db) return

  // Create tables
  db.exec(`
    CREATE TABLE IF NOT EXISTS music_sheets (
      id INTEGER PRIMARY KEY AUTOINCREMENT,
      title TEXT NOT NULL,
      composer TEXT NOT NULL,
      arranger TEXT,
      genre TEXT NOT NULL,
      difficulty TEXT NOT NULL,
      instrument TEXT NOT NULL,
      price REAL NOT NULL,
      description TEXT NOT NULL,
      pages INTEGER NOT NULL,
      preview_url TEXT,
      cover_image_url TEXT,
      rating REAL DEFAULT 0,
      review_count INTEGER DEFAULT 0,
      created_at DATETIME DEFAULT CURRENT_TIMESTAMP
    )
  `)

  db.exec(`
    CREATE TABLE IF NOT EXISTS orders (
      id INTEGER PRIMARY KEY AUTOINCREMENT,
      customer_email TEXT NOT NULL,
      total REAL NOT NULL,
      status TEXT NOT NULL,
      created_at DATETIME DEFAULT CURRENT_TIMESTAMP
    )
  `)

  db.exec(`
    CREATE TABLE IF NOT EXISTS order_items (
      id INTEGER PRIMARY KEY AUTOINCREMENT,
      order_id INTEGER NOT NULL,
      sheet_id INTEGER NOT NULL,
      quantity INTEGER NOT NULL,
      price REAL NOT NULL,
      FOREIGN KEY (order_id) REFERENCES orders(id),
      FOREIGN KEY (sheet_id) REFERENCES music_sheets(id)
    )
  `)

  // Seed some data if empty
  const count = db.prepare('SELECT COUNT(*) as count FROM music_sheets').get() as { count: number }

  if (count.count === 0) {
    seedData()
  }
}

function seedData() {
  if (!db) return

  const sampleSheets = [
    {
      title: 'Clair de Lune',
      composer: 'Claude Debussy',
      arranger: null,
      genre: 'classical',
      difficulty: 'advanced',
      instrument: 'piano',
      price: 9.99,
      description: 'Le troisième mouvement de la Suite bergamasque. Une des œuvres les plus célèbres de Debussy.',
      pages: 5,
      preview_url: null,
      cover_image_url: 'https://images.unsplash.com/photo-1520523839897-bd0b52f945a0?w=400&h=600&fit=crop',
      rating: 4.8,
      review_count: 127
    },
    {
      title: 'Für Elise',
      composer: 'Ludwig van Beethoven',
      arranger: null,
      genre: 'classical',
      difficulty: 'intermediate',
      instrument: 'piano',
      price: 7.99,
      description: 'Bagatelle No. 25 en la mineur. Une des pièces pour piano les plus populaires de Beethoven.',
      pages: 3,
      preview_url: null,
      cover_image_url: 'https://images.unsplash.com/photo-1511192336575-5a79af67a629?w=400&h=600&fit=crop',
      rating: 4.9,
      review_count: 245
    },
    {
      title: 'Take Five',
      composer: 'Paul Desmond',
      arranger: 'John Smith',
      genre: 'jazz',
      difficulty: 'intermediate',
      instrument: 'saxophone',
      price: 12.99,
      description: 'Standard de jazz composé par Paul Desmond, arrangement pour saxophone alto.',
      pages: 4,
      preview_url: null,
      cover_image_url: 'https://images.unsplash.com/photo-1511379938547-c1f69419868d?w=400&h=600&fit=crop',
      rating: 4.7,
      review_count: 89
    },
    {
      title: 'Bohemian Rhapsody',
      composer: 'Queen',
      arranger: 'Emily Johnson',
      genre: 'rock',
      difficulty: 'advanced',
      instrument: 'piano',
      price: 14.99,
      description: 'Arrangement complet pour piano de ce chef-d\'œuvre du rock.',
      pages: 12,
      preview_url: null,
      cover_image_url: 'https://images.unsplash.com/photo-1493225457124-a3eb161ffa5f?w=400&h=600&fit=crop',
      rating: 4.9,
      review_count: 312
    },
    {
      title: 'La Vie en Rose',
      composer: 'Édith Piaf',
      arranger: 'Pierre Martin',
      genre: 'pop',
      difficulty: 'beginner',
      instrument: 'guitar',
      price: 5.99,
      description: 'Arrangement simple pour guitare de ce classique français.',
      pages: 2,
      preview_url: null,
      cover_image_url: 'https://images.unsplash.com/photo-1510915361894-db8b60106cb1?w=400&h=600&fit=crop',
      rating: 4.6,
      review_count: 156
    },
    {
      title: 'Autumn Leaves',
      composer: 'Joseph Kosma',
      arranger: 'Bill Evans',
      genre: 'jazz',
      difficulty: 'intermediate',
      instrument: 'piano',
      price: 10.99,
      description: 'Standard de jazz avec harmonies enrichies, arrangement style Bill Evans.',
      pages: 6,
      preview_url: null,
      cover_image_url: 'https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=400&h=600&fit=crop',
      rating: 4.8,
      review_count: 178
    },
    {
      title: 'Canon in D',
      composer: 'Johann Pachelbel',
      arranger: null,
      genre: 'classical',
      difficulty: 'intermediate',
      instrument: 'violin',
      price: 8.99,
      description: 'Partition pour violon du célèbre Canon de Pachelbel.',
      pages: 4,
      preview_url: null,
      cover_image_url: 'https://images.unsplash.com/photo-1452626038306-9aae5e071dd3?w=400&h=600&fit=crop',
      rating: 4.7,
      review_count: 203
    },
    {
      title: 'Imagine',
      composer: 'John Lennon',
      arranger: null,
      genre: 'pop',
      difficulty: 'beginner',
      instrument: 'piano',
      price: 6.99,
      description: 'Arrangement simplifié de ce classique de John Lennon.',
      pages: 3,
      preview_url: null,
      cover_image_url: 'https://images.unsplash.com/photo-1516450360452-9312f5e86fc7?w=400&h=600&fit=crop',
      rating: 4.9,
      review_count: 421
    }
  ]

  const insert = db.prepare(`
    INSERT INTO music_sheets (
      title, composer, arranger, genre, difficulty, instrument,
      price, description, pages, preview_url, cover_image_url,
      rating, review_count
    ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
  `)

  for (const sheet of sampleSheets) {
    insert.run(
      sheet.title,
      sheet.composer,
      sheet.arranger,
      sheet.genre,
      sheet.difficulty,
      sheet.instrument,
      sheet.price,
      sheet.description,
      sheet.pages,
      sheet.preview_url,
      sheet.cover_image_url,
      sheet.rating,
      sheet.review_count
    )
  }
}

export function getAllSheets(): MusicSheet[] {
  const db = getDb()
  const sheets = db.prepare(`
    SELECT
      id, title, composer, arranger, genre, difficulty, instrument,
      price, description, pages, preview_url as previewUrl,
      cover_image_url as coverImageUrl, rating, review_count as reviewCount,
      created_at as createdAt
    FROM music_sheets
    ORDER BY created_at DESC
  `).all()

  return sheets as MusicSheet[]
}

export function getSheetById(id: number): MusicSheet | undefined {
  const db = getDb()
  const sheet = db.prepare(`
    SELECT
      id, title, composer, arranger, genre, difficulty, instrument,
      price, description, pages, preview_url as previewUrl,
      cover_image_url as coverImageUrl, rating, review_count as reviewCount,
      created_at as createdAt
    FROM music_sheets
    WHERE id = ?
  `).get(id)

  return sheet as MusicSheet | undefined
}

export function searchSheets(query: string, filters?: {
  genre?: string
  difficulty?: string
  instrument?: string
  minPrice?: number
  maxPrice?: number
}): MusicSheet[] {
  const db = getDb()

  let sql = `
    SELECT
      id, title, composer, arranger, genre, difficulty, instrument,
      price, description, pages, preview_url as previewUrl,
      cover_image_url as coverImageUrl, rating, review_count as reviewCount,
      created_at as createdAt
    FROM music_sheets
    WHERE 1=1
  `

  const params: any[] = []

  if (query) {
    sql += ' AND (title LIKE ? OR composer LIKE ? OR description LIKE ?)'
    params.push(`%${query}%`, `%${query}%`, `%${query}%`)
  }

  if (filters?.genre) {
    sql += ' AND genre = ?'
    params.push(filters.genre)
  }

  if (filters?.difficulty) {
    sql += ' AND difficulty = ?'
    params.push(filters.difficulty)
  }

  if (filters?.instrument) {
    sql += ' AND instrument = ?'
    params.push(filters.instrument)
  }

  if (filters?.minPrice !== undefined) {
    sql += ' AND price >= ?'
    params.push(filters.minPrice)
  }

  if (filters?.maxPrice !== undefined) {
    sql += ' AND price <= ?'
    params.push(filters.maxPrice)
  }

  sql += ' ORDER BY rating DESC, review_count DESC'

  const sheets = db.prepare(sql).all(...params)
  return sheets as MusicSheet[]
}
