import { getDb } from './db'
import type { User, Session } from '@/types'

// Simple hash function - in production use bcrypt
function hashPassword(password: string): string {
  return Buffer.from(password).toString('base64')
}

function verifyPassword(password: string, hash: string): boolean {
  return hashPassword(password) === hash
}

export function createUser(email: string, password: string, name: string, role: string = 'user'): User | null {
  try {
    const db = getDb()
    const passwordHash = hashPassword(password)

    const result = db.prepare(`
      INSERT INTO users (email, password_hash, name, role)
      VALUES (?, ?, ?, ?)
    `).run(email, passwordHash, name, role)

    return {
      id: Number(result.lastInsertRowid),
      email,
      name,
      role,
      createdAt: new Date().toISOString()
    }
  } catch (error) {
    console.error('Error creating user:', error)
    return null
  }
}

export function getUserByEmail(email: string): (User & { passwordHash: string }) | null {
  try {
    const db = getDb()
    const user = db.prepare(`
      SELECT id, email, password_hash as passwordHash, name, role, created_at as createdAt
      FROM users
      WHERE email = ?
    `).get(email)

    return user as (User & { passwordHash: string }) | null
  } catch (error) {
    console.error('Error getting user:', error)
    return null
  }
}

export function getUserById(id: number): User | null {
  try {
    const db = getDb()
    const user = db.prepare(`
      SELECT id, email, name, role, created_at as createdAt
      FROM users
      WHERE id = ?
    `).get(id)

    return user as User | null
  } catch (error) {
    console.error('Error getting user:', error)
    return null
  }
}

export function authenticateUser(email: string, password: string): User | null {
  const user = getUserByEmail(email)
  if (!user) return null

  if (!verifyPassword(password, user.passwordHash)) {
    return null
  }

  // Return user without password hash
  const { passwordHash, ...userWithoutPassword } = user
  return userWithoutPassword
}

export function createSession(userId: number): Session | null {
  try {
    const db = getDb()
    const sessionId = crypto.randomUUID()
    const expiresAt = new Date()
    expiresAt.setDate(expiresAt.getDate() + 30) // 30 days

    db.prepare(`
      INSERT INTO sessions (id, user_id, expires_at)
      VALUES (?, ?, ?)
    `).run(sessionId, userId, expiresAt.toISOString())

    return {
      id: sessionId,
      userId,
      expiresAt: expiresAt.toISOString(),
      createdAt: new Date().toISOString()
    }
  } catch (error) {
    console.error('Error creating session:', error)
    return null
  }
}

export function getSessionById(sessionId: string): Session | null {
  try {
    const db = getDb()
    const session = db.prepare(`
      SELECT id, user_id as userId, expires_at as expiresAt, created_at as createdAt
      FROM sessions
      WHERE id = ? AND expires_at > datetime('now')
    `).get(sessionId)

    return session as Session | null
  } catch (error) {
    console.error('Error getting session:', error)
    return null
  }
}

export function deleteSession(sessionId: string): boolean {
  try {
    const db = getDb()
    db.prepare(`DELETE FROM sessions WHERE id = ?`).run(sessionId)
    return true
  } catch (error) {
    console.error('Error deleting session:', error)
    return false
  }
}

export function cleanExpiredSessions(): void {
  try {
    const db = getDb()
    db.prepare(`DELETE FROM sessions WHERE expires_at < datetime('now')`).run()
  } catch (error) {
    console.error('Error cleaning sessions:', error)
  }
}
