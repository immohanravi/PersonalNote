package com.bogarsoft.noteify

import android.app.Application
import android.util.Log
import com.bogarsoft.noteify.database.DBHandler
import com.bogarsoft.noteify.models.Note
import org.dizitart.no2.sync.DataGateClient
import org.dizitart.no2.sync.DataGateSyncTemplate
import org.dizitart.no2.sync.ReplicationType
import org.dizitart.no2.sync.Replicator
import org.dizitart.no2.sync.SyncEventData
import org.dizitart.no2.sync.SyncEventListener
import org.dizitart.no2.sync.TimeSpan.timeSpan
import java.util.concurrent.TimeUnit


class App:Application() {

    override fun onCreate() {
        super.onCreate()
    }

    companion object {
        private const val TAG = "App"
    }
}