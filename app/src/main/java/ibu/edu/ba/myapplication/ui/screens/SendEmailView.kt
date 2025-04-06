package ibu.edu.ba.myapplication.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp


@Composable
fun SendEmailView() {
    // State variables for user input
    val recipientState = remember { mutableStateOf("") }
    val subjectState = remember { mutableStateOf("") }
    val bodyState = remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ){
            IconButton(
                onClick = {
                    sendEmail(context, recipientState.value, subjectState.value, bodyState.value)
                },
            ) {
                Icon(Icons.Default.Send, contentDescription = "", tint = Color.Blue)
            }
            IconButton(
                onClick = {},
            ) {
                Icon(Icons.Default.Add, contentDescription = "")
            }
            IconButton(
                onClick = {},
            ) {
                Icon(Icons.Default.Menu, contentDescription = "")
            }
        }
        TextField(
            value = recipientState.value,
            onValueChange = { recipientState.value = it },
            label = { Text("To") },
            placeholder = { Text("Enter recipient's email") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.bodyMedium,
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, modifier = Modifier.size(20.dp)) },
            trailingIcon = { Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Choose recipient") },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent
            )
        )

        TextField(
            value = subjectState.value,
            onValueChange = { subjectState.value = it },
            label = { Text("Subject") },
            placeholder = { Text("Enter subject here") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.bodySmall,
            leadingIcon = { Icon(Icons.Default.Info, contentDescription = null, modifier = Modifier.size(20.dp)) },
            trailingIcon = { Icon(Icons.Default.Lock, contentDescription = "Email subject", modifier = Modifier.size(20.dp)) },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent
            )
        )

        TextField(
            value = bodyState.value,
            onValueChange = { bodyState.value = it },
            label = { Text("Body") },
            placeholder = { Text("Write your message...") },
            modifier = Modifier.fillMaxWidth()
                .fillMaxHeight().weight(1f),
            textStyle = MaterialTheme.typography.bodyMedium,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
            )
        )
    }
}


fun sendEmail(context: Context, recipient: String, subject: String, body: String) {
    Log.d("Mail status: ", "${recipient}, $subject, $body")
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "message/rfc822"
        putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, body)
        setPackage("com.google.android.gm")
    }
    try {
        context.startActivity(intent)
    } catch(e: android.content.ActivityNotFoundException) {
        Log.e("Mail status: ", "No email client installed.")
        Toast.makeText(context, "An error occured", Toast.LENGTH_SHORT).show()
    }
}