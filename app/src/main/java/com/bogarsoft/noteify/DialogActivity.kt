package com.bogarsoft.noteify


import android.content.Intent
import android.os.Bundle
import android.text.Layout.Alignment
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.style.TextAlign
import com.bogarsoft.noteify.data.NoteRepository
import com.bogarsoft.noteify.database.DBHandler
import com.bogarsoft.noteify.interfaces.OnOpenGraphResponse
import com.bogarsoft.noteify.models.Note
import com.bogarsoft.noteify.ui.screens.HomeViewModel
import com.bogarsoft.noteify.utils.Helper
import com.bogarsoft.noteify.utils.findActivity
import com.kedia.ogparser.OpenGraphCacheProvider
import com.kedia.ogparser.OpenGraphCallback
import com.kedia.ogparser.OpenGraphParser
import com.kedia.ogparser.OpenGraphResult
import kotlin.system.exitProcess

class DialogActivity : ComponentActivity(){



    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activity = findActivity()

        val db = DBHandler.getDBInstance(applicationContext)
        val repo = db.getRepository(Note::class.java)
        val respository = NoteRepository(repo)
        val viewModel = HomeViewModel(respository)
        setContent {
            var title by remember {
                mutableStateOf("")
            }
            var show by remember {
                mutableStateOf(false)
            }
            var value by remember {
                mutableStateOf("")
            }
            activity?.intent?.let { intent ->
                if (intent.action == Intent.ACTION_SEND) {
                    intent.getStringExtra(Intent.EXTRA_TEXT)?.let { text ->
                        value = text
                        val link = Helper.extractLink(text)
                        link?.let {
                            title = text.substring(0, text.indexOf(link))
                            Helper.getOpenGraph(link, LocalContext.current,object : OnOpenGraphResponse{
                                override fun response(openGraphResult: OpenGraphResult) {
                                    openGraphResult.title?.let {
                                        Log.d(TAG, "onPostResponse: ${it}")
                                        title = if (it.length > 30) {
                                            it.substring(0, 30)+"..."
                                        } else {
                                            it
                                        }
                                        show = true

                                    }
                                }

                                override fun error(error: String) {
                                    show = true
                                }

                            })
                        }?:run{
                            if (text.length > 30) {
                                title = text.substring(0, 30)+"..."
                                show = true
                            } else {
                                title = text
                                show = true
                            }
                        }
                    }

                }
            }


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
                }else {
                    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                        //Text(text = "Dialog Activity", modifier = Modifier.fillMaxSize())
                        Text(text ="processing...",color = Color.White,modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
                    }

                }


            }

        }
    }

    companion object {
        private const val TAG = "DialogActivity"
    }


}