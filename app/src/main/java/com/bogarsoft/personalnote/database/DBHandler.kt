package com.bogarsoft.personalnote.database

import android.content.Context
import android.os.Environment
import com.bogarsoft.e_farming.utils.Constants
import org.dizitart.no2.Nitrite
import com.bogarsoft.personalnote.BuildConfig

class DBHandler {

    companion object {

        @Volatile private var INSTANCE: Nitrite? = null

        fun getDBInstance(context: Context): Nitrite {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildNitriteDB(context).also { INSTANCE = it }
            }
        }

        private fun buildNitriteDB(context: Context): Nitrite {
            return Nitrite.builder()
                .compressed()
                .filePath(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)?.absolutePath+"/${Constants.APP_DATABASE}")
                .openOrCreate(BuildConfig.DB_USER, BuildConfig.DB_PASSWORD)
        }
    }
}