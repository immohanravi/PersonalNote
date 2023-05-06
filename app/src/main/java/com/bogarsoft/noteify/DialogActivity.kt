package com.bogarsoft.noteify


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.bogarsoft.noteify.ui.components.CustomDialog
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.bogarsoft.noteify.data.NoteRepository
import com.bogarsoft.noteify.database.DBHandler
import com.bogarsoft.noteify.models.Note
import com.bogarsoft.noteify.ui.screens.HomeViewModel
import com.bogarsoft.noteify.utils.Helper
import com.bogarsoft.noteify.utils.findActivity
import com.kedia.ogparser.OpenGraphCacheProvider
import com.kedia.ogparser.OpenGraphCallback
import com.kedia.ogparser.OpenGraphParser
import com.kedia.ogparser.OpenGraphResult
import kotlin.system.exitProcess

class DialogActivity : ComponentActivity(), OpenGraphCallback {

    private val openGraphParser by lazy {
        OpenGraphParser(
            listener = this,
            showNullOnEmpty = true,
            cacheProvider = OpenGraphCacheProvider(this)
        )
    }
    var title by mutableStateOf("")
    var show by mutableStateOf(true)
    var value by  mutableStateOf("")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activity = findActivity()
        activity?.intent?.let { intent ->
            if (intent.action == Intent.ACTION_SEND) {
                intent.getStringExtra(Intent.EXTRA_TEXT)?.let { text ->
                    value = text
                    val link = Helper.extractLink(text)
                    link?.let {
                        title = text.substring(0, text.indexOf(link))
                        openGraphParser.parse(it)
                    }?:run{
                        if (text.length > 30) {
                            title = text.substring(0, 30)+"..."
                        } else {
                            title = text
                        }
                    }
                }

            }
        }
        val db = DBHandler.getDBInstance(applicationContext)
        val repo = db.getRepository(Note::class.java)
        val respository = NoteRepository(repo)
        val viewModel = HomeViewModel(respository)
        setContent {

            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.Transparent,
                onClick = {
                    finish()
                }
            ) {

                /*AlertDialog(
                    onDismissRequest = {
                        // Dismiss the dialog when the user clicks outside the dialog or on the back
                        // button. If you want to disable that functionality, simply use an empty
                        // onCloseRequest.
                    },
                    title = {
                        Text(text = "Dialog Title")
                    },
                    text = {
                        Text("Here is a text ")
                    },
                    confirmButton = {
                        Button(

                            onClick = {
                                finish()
                            }) {
                            Text("This is the Confirm Button")
                        }
                    },
                    dismissButton = {
                        Button(

                            onClick = {
                                finish()
                            }) {
                            Text("This is the dismiss Button")
                        }
                    }
                )*/

                if(show){
                    CustomDialog(title=title,value = value, setShowDialog = {
                       finish()
                    },onSave = {
                        viewModel.addNote(it)
                        finish()
                    })
                }


            }

        }
    }

    companion object {
        private const val TAG = "DialogActivity"
    }

    override fun onError(error: String) {

    }

    override fun onPostResponse(openGraphResult: OpenGraphResult) {
        openGraphResult.let { graphResult ->
            graphResult.title?.let {
                Log.d(TAG, "onPostResponse: ${it.length}")
                title = if (it.length > 30) {
                    it.substring(0, 30)+"..."
                } else {
                    it
                }

            }
        }

    }
}