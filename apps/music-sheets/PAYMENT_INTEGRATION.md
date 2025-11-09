# Intégration du système de paiement

Ce document explique comment intégrer vos liens de paiement réels dans l'application MusicSheets.

## Où ajouter les liens de paiement

### 1. API Route de création de commande

**Fichier:** `src/app/api/orders/route.ts`

**Ligne 20:**
```typescript
// Generate payment link placeholder (you'll replace this with actual payment links later)
const paymentLink = `https://payment-link-placeholder.com/pay/${Date.now()}`
```

**À remplacer par:**
```typescript
// Générer le vrai lien de paiement avec votre système
// Exemple basé sur le système Chiasma Android
const paymentLink = await generatePaymentLink({
  amount: total,
  orderId: orderId,
  customerEmail: customerEmail,
  items: items
})
```

### 2. Page de confirmation de commande

**Fichier:** `src/app/order-confirmation/[id]/page.tsx`

**Ligne 11:**
```typescript
const paymentLink = `https://payment-link-placeholder.com/pay/${params.id}`
```

**À remplacer par:**
```typescript
// Récupérer le vrai lien de paiement depuis la base de données
const [paymentLink, setPaymentLink] = useState('')

useEffect(() => {
  fetch(`/api/orders/${params.id}/payment-link`)
    .then(res => res.json())
    .then(data => setPaymentLink(data.paymentLink))
}, [params.id])
```

## Structure recommandée pour l'intégration

### Créer un service de paiement

**Fichier à créer:** `src/lib/payment.ts`

```typescript
export async function generatePaymentLink(options: {
  amount: number
  orderId: number
  customerEmail: string
  items: any[]
}): Promise<string> {
  // Votre logique de génération de lien de paiement
  // Similaire à celle utilisée dans l'app Chiasma Android

  // Exemple:
  const response = await fetch('VOTRE_API_PAIEMENT', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({
      amount: options.amount,
      reference: `ORDER-${options.orderId}`,
      customer_email: options.customerEmail,
      items: options.items,
      callback_url: `${process.env.NEXT_PUBLIC_APP_URL}/api/payment/callback`,
      success_url: `${process.env.NEXT_PUBLIC_APP_URL}/order-confirmation/${options.orderId}`,
      cancel_url: `${process.env.NEXT_PUBLIC_APP_URL}/checkout`
    })
  })

  const data = await response.json()
  return data.payment_url
}
```

### Variables d'environnement nécessaires

Créer un fichier `.env.local`:

```bash
# URL de base de l'application
NEXT_PUBLIC_APP_URL=http://localhost:3000

# Configuration du système de paiement
PAYMENT_API_URL=https://votre-api-paiement.com
PAYMENT_API_KEY=votre-clé-api
PAYMENT_MERCHANT_ID=votre-merchant-id
```

## Méthodes de paiement supportées

Le système est configuré pour supporter plusieurs méthodes de paiement:

1. **Carte bancaire** (par défaut)
2. **Mobile Money**
3. **Virement bancaire**
4. **Autres** (selon votre système de paiement)

### Ajouter des méthodes de paiement dans le checkout

**Fichier:** `src/app/checkout/page.tsx`

Ajoutez des boutons ou sélecteurs pour les différentes méthodes:

```typescript
<select
  className="input-field"
  value={paymentMethod}
  onChange={(e) => setPaymentMethod(e.target.value)}
>
  <option value="card">Carte bancaire</option>
  <option value="mobile_money">Mobile Money</option>
  <option value="bank_transfer">Virement bancaire</option>
</select>
```

## Callbacks et webhooks

### Créer une route de callback

**Fichier à créer:** `src/app/api/payment/callback/route.ts`

```typescript
import { NextRequest, NextResponse } from 'next/server'
import { getDb } from '@/lib/db'

export async function POST(request: NextRequest) {
  const body = await request.json()

  // Vérifier la signature du webhook
  // Mettre à jour le statut de la commande
  // Envoyer l'email de confirmation

  const db = getDb()
  db.prepare(`
    UPDATE orders
    SET status = ?, payment_status = ?
    WHERE id = ?
  `).run('completed', body.status, body.order_id)

  return NextResponse.json({ success: true })
}
```

## Sécurité

1. **Toujours vérifier** les signatures des webhooks
2. **Ne jamais exposer** les clés API côté client
3. **Utiliser HTTPS** en production
4. **Valider** tous les montants côté serveur
5. **Logger** toutes les transactions

## Testing

Pour tester l'intégration:

1. Utilisez les credentials de test de votre fournisseur de paiement
2. Testez tous les scénarios: succès, échec, annulation
3. Vérifiez que les commandes sont correctement enregistrées
4. Testez les webhooks avec des outils comme ngrok

## Support

Pour plus d'informations, consultez la documentation de votre fournisseur de paiement ou contactez leur support technique.
