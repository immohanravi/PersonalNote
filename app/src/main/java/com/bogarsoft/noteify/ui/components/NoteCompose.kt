package com.bogarsoft.noteify.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import com.bogarsoft.noteify.models.Note
import com.bogarsoft.noteify.utils.Helper


@Composable
fun NoteComponent(
    note: Note,
    onDelete: (Note) -> Unit,
    onClick: (Note) -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp),
        shape = RoundedCornerShape(8.dp)


    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = note.title, style = TextStyle(fontSize = 18.sp), fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,modifier = Modifier.fillMaxWidth().heightIn(max = 50.dp))
            Text(text = note.content, modifier = Modifier.heightIn(max = 100.dp))
            Row(modifier = Modifier.fillMaxWidth().padding(top = 8.dp), verticalAlignment = Alignment.Bottom) {
                Text(text = Helper.dateToString(note.date),
                    color = androidx.compose.ui.graphics.Color.Gray,
                    style = TextStyle(
                        fontSize = 12.sp,
                    ), modifier = Modifier.fillMaxHeight().padding(bottom = 8.dp))

                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = {onDelete(note)}) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                    )

                }
            }
        }
    }

}