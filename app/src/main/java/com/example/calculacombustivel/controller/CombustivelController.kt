import android.content.ContentValues
import android.content.Context
import com.example.calculacombustivel.HomeFragment
import com.example.calculacombustivel.database.CalculaCombustivelSQLiteHelper

class CombustivelController(context: Context) {

    private val dbHelper: CalculaCombustivelSQLiteHelper = CalculaCombustivelSQLiteHelper(context)
    private val db = dbHelper.writableDatabase

    fun inserirRegistro(etanol: Double, gasolina: Double, data: String) {

        val valores = ContentValues().apply {
            put("valor_etanol", etanol)
            put("valor_gasolina", gasolina)
            put("data_registro", data)
        }

        db.insert("combustivel", null, valores)
    }
}
