import { cookies } from 'next/headers'
import { getSessionById, getUserById } from './auth'
import type { User } from '@/types'

const SESSION_COOKIE_NAME = 'session_id'

export async function getSession(): Promise<{ user: User } | null> {
  const cookieStore = await cookies()
  const sessionId = cookieStore.get(SESSION_COOKIE_NAME)?.value

  if (!sessionId) {
    return null
  }

  const session = getSessionById(sessionId)
  if (!session) {
    return null
  }

  const user = getUserById(session.userId)
  if (!user) {
    return null
  }

  return { user }
}

export async function setSessionCookie(sessionId: string) {
  const cookieStore = await cookies()
  cookieStore.set(SESSION_COOKIE_NAME, sessionId, {
    httpOnly: true,
    secure: process.env.NODE_ENV === 'production',
    sameSite: 'lax',
    maxAge: 60 * 60 * 24 * 30, // 30 days
    path: '/'
  })
}

export async function clearSessionCookie() {
  const cookieStore = await cookies()
  cookieStore.delete(SESSION_COOKIE_NAME)
}
