import SheetCard from './SheetCard'
import { getAllSheets } from '@/lib/db'

export default function FeaturedSheets() {
  const sheets = getAllSheets().slice(0, 4)

  return (
    <div className="grid md:grid-cols-2 lg:grid-cols-4 gap-6">
      {sheets.map((sheet) => (
        <SheetCard key={sheet.id} sheet={sheet} />
      ))}
    </div>
  )
}
