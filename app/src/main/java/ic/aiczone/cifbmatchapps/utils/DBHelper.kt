package ic.aiczone.cifbmatchapps.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import ic.aiczone.cifbmatchapps.entities.Favorite
import org.jetbrains.anko.db.*

class DBHelper(ctx: Context): ManagedSQLiteOpenHelper(ctx, "Favorite.db", null, 1 ) {
    companion object {
        private var instance: DBHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DBHelper{
            if (instance==null){
                instance = DBHelper(ctx.applicationContext)
            }
            return  instance as DBHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(Favorite.TABLE_FAVORITE, true,
                Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                Favorite.EVENT_ID to TEXT + UNIQUE,
                Favorite.EVENT_NAME to TEXT,
                Favorite.EVENT_DATE to TEXT,
                Favorite.HOME_TEAM_ID to TEXT + UNIQUE,
                Favorite.HOME_TEAM_NAME to TEXT,
                Favorite.HOME_TEAM_SCORE to TEXT,
                Favorite.AWAY_TEAM_ID to TEXT + UNIQUE,
                Favorite.AWAY_TEAM_NAME to TEXT,
                Favorite.AWAY_TEAM_SCORE to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(Favorite.TABLE_FAVORITE, true)
    }
}
val Context.database: DBHelper
    get() = DBHelper.getInstance(applicationContext)