package co.edu.udea.compumovil.gr06_20241.lab1;

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class ContactDataActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "ContactDataActivity"
        setContent {
            MaterialTheme {
                Surface {
                    ContactDataScreen(context=this, activity=this)
                }
            }
        }
    }
}

@Composable
fun ContactDataScreen(context: Context, activity: ComponentActivity) {
    var phone by remember { mutableStateOf(TextFieldValue()) }
    var address by remember { mutableStateOf(TextFieldValue()) }
    var email by remember { mutableStateOf(TextFieldValue()) }
    var country by remember { mutableStateOf(TextFieldValue()) }
    var city by remember { mutableStateOf(TextFieldValue()) }
    var expanded = false;
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Top,
    ) {
        Column(
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Contact Data",
                style = TextStyle(
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Light
                )
            )
            Divider(
                color = Color.Black,
                thickness = 1.dp,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Column {
            Row(
                horizontalArrangement = Arrangement.Start
            ){
                Text(
                    text = "Phone:",
                    style = TextStyle(fontWeight = FontWeight.Bold),
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "*",
                    style = TextStyle(fontWeight = FontWeight.Bold, color = Color.Red)
                )
            }
            TextField(
                value=phone,
                onValueChange={phone = it},
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.fillMaxWidth()
            )
            if (phone.text.isEmpty() || phone.text == "" || phone.text.trim() == ""){
                Text(
                    text = "This field is mandatory",
                    color = Color.Red
                )
            } else {
                Data.phone = phone.text
            }
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Address:",
                style = TextStyle(fontWeight = FontWeight.Bold),
            )
            TextField(
                value=address,
                onValueChange={address = it},
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Email:",
                    style = TextStyle(fontWeight = FontWeight.Bold),
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "*",
                    style = TextStyle(fontWeight = FontWeight.Bold, color = Color.Red)
                )
            }
            TextField(
                value=email,
                onValueChange={email = it},
                //keyboardOptions = keyboardType?.let { KeyboardOptions.Default.copy(keyboardType = it) },
                modifier = Modifier.fillMaxWidth()
            )
            if (email.text.isEmpty() || email.text == "" || email.text.trim() == ""){
                Text(
                    text = "This field is mandatory",
                    color = Color.Red
                )
            } else {
                Data.email = email.text
            }
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Country:",
                    style = TextStyle(fontWeight = FontWeight.Bold),
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "*",
                    style = TextStyle(fontWeight = FontWeight.Bold, color = Color.Red)
                )
            }
            TextField(
                value=country,
                onValueChange={country = it},
                //keyboardOptions = keyboardType?.let { KeyboardOptions.Default.copy(keyboardType = it) },
                modifier = Modifier.fillMaxWidth()
            )
            /*
            DropdownMenu(
                expanded = expanded && country.text.isNotEmpty(),
                onDismissRequest = { expanded = false }
            ) {
                countryList.filter {
                    it.contains(text, ignoreCase = true)
                }.forEach { country ->
                    DropdownMenuItem(onClick = {
                        text = country
                        expanded = false
                    }) {
                        Text(country)
                    }
                }
            }
            */
            if (country.text.isEmpty() || country.text == "" || country.text.trim() == ""){
                Text(
                    text = "This field is mandatory",
                    color = Color.Red
                )
            } else {
                Data.country = country.text
            }
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "City:",
                style = TextStyle(fontWeight = FontWeight.Bold),
            )
            TextField(
                value=city,
                onValueChange={city = it},
                //keyboardOptions = keyboardType?.let { KeyboardOptions.Default.copy(keyboardType = it) },
                modifier = Modifier.fillMaxWidth()
            )
        }
        //AutoCompleteTextViewWithLabel("Country", countries)
        Spacer(modifier = Modifier.height(30.dp))
        // buttons back - submit
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.
            fillMaxWidth()
        ) {
            Button(
                onClick = {
                    activity.finish()
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Come back")
            }
            Spacer(modifier = Modifier.width(5.dp))
            Button(
                onClick = {
                    if (phone.text.isEmpty() || phone.text == "" || phone.text.trim() == "" || email.text.isEmpty() || email.text == "" || email.text.trim() == "" || country.text.isEmpty() || country.text == "" || country.text.trim() == "") {
                        Log.d("State: ", "Fill mandatory fields")
                    } else {
                        Log.d("Name: ", Data.name)
                        Log.d("Last Name: ", Data.lastname)
                        Log.d("Sex: ", Data.sex.toString())
                        Log.d("Birth Date: ", Data.birthdate)
                        Log.d("Major: ", Data.major)
                        Log.d("Phone: ", Data.phone)
                        Log.d("Address: ", Data.address)
                        Log.d("Email: ", Data.email)
                        Log.d("Country: ", Data.country)
                        Log.d("City: ", Data.city)
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Submit")
            }
        }
    }

}

@Composable
fun EditTextWithLabel(label: String, keyboardType: KeyboardType? = null) {
    var text by remember { mutableStateOf("") }
    Column {
        Text(
            text = label,
            style = TextStyle(fontWeight = FontWeight.Bold),
        )
        TextField(
            value=text,
            onValueChange={text = it},
            placeholder = {Text(label)},
            //keyboardOptions = keyboardType?.let { KeyboardOptions.Default.copy(keyboardType = it) },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun AutoCompleteTextViewWithLabel(label: String, suggestions: List<String>) {
    var text by remember { mutableStateOf(TextFieldValue()) }
    Column {
        Text(
            text = label,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}

@Composable
fun AutocompleteTextView(value: Any, onValueChange: () -> Unit, suggestions: List<String>, modifier: Modifier) {

}

// Dummy data for Autocomplete
val countries = listOf(
    "Argentina", "Bolivia", "Brazil", "Chile", "Colombia",
    "Costa Rica", "Cuba", "Dominican Republic", "Ecuador", "El Salvador",
    "Guatemala", "Honduras", "Mexico", "Nicaragua", "Panama",
    "Paraguay", "Peru", "Uruguay", "Venezuela"
)