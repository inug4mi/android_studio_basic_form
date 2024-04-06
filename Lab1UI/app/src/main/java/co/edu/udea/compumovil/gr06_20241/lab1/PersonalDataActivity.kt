package co.edu.udea.compumovil.gr06_20241.lab1;

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Calendar
import android.app.DatePickerDialog
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import java.text.SimpleDateFormat
import java.util.Locale

class PersonalDataActivity : ComponentActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "PersonalDataActivity"
        setContent {
            MaterialTheme {
                Surface {
                    personalData(context=this)
                }
            }
        }
    }
}

@Composable
fun personalData(context: Context){

    var name by remember { mutableStateOf(TextFieldValue()) }
    var lastname by remember { mutableStateOf(TextFieldValue())}

    var selectedSexOption by remember { mutableStateOf(Sex.Man)}
    var birthDate by remember { mutableStateOf(Calendar.getInstance()) }

    var selectedMajor by remember { mutableStateOf((Major.None))}
    val majors = Major.entries
    var expanded_ by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.SpaceBetween,
    ){
        Column(
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Personal Data",
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
        Column(
            //verticalArrangement = Arrangement.Top
        ) {
            //Spacer(modifier = Modifier.height(25.dp))
            Row(
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "First Name:",
                    style = TextStyle(fontWeight = FontWeight.Bold),
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "*",
                    style = TextStyle(fontWeight = FontWeight.Bold, color = Color.Red)
                )
            }
            TextField(
                value = name,
                onValueChange = { name = it },
                modifier = Modifier.fillMaxWidth()
            )
            if (name.text.isEmpty() || name.text == "" || name.text.trim() == ""){
                Text(
                    text = "This field is mandatory",
                    color = Color.Red
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Last Name:",
                    style = TextStyle(fontWeight = FontWeight.Bold),
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "*",
                    style = TextStyle(fontWeight = FontWeight.Bold, color = Color.Red)
                )
            }
            TextField(
                value = lastname,
                onValueChange = { lastname = it },
                modifier = Modifier.fillMaxWidth()
            )
            if (lastname.text.isEmpty() || lastname.text == "" || lastname.text.trim() == ""){
                Text(
                    text = "This field is mandatory",
                    color = Color.Red
                )
            }
            Spacer(modifier = Modifier.height(30.dp))

            // sex selection
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        "Sex: ",
                        style = TextStyle(fontWeight = FontWeight.Bold),
                    )

                }
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.weight(1f)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        RadioButton(
                            selected = selectedSexOption == Sex.Man,
                            onClick = { selectedSexOption = Sex.Man }
                        )
                        Text(
                            "Male",
                        )
                    }
                    //Spacer(modifier = Modifier.width(12.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        RadioButton(
                            selected = selectedSexOption == Sex.Woman,
                            onClick = { selectedSexOption = Sex.Woman }

                        )
                        Text(
                            "Female",
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            // Birth date selection
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.align(Alignment.Start)
            ) {
                Text(
                    "Birth Date:",
                    style = TextStyle(fontWeight = FontWeight.Bold),
                )
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.weight(1f)
                )
                {
                    Button(
                        onClick = {
                            showDatePickerDialog(context, birthDate) { newDate ->
                                birthDate = newDate
                            }
                        },
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        Text("Select")
                    }
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            // major
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.align(Alignment.Start)
            ) {
                Text(
                    "Major:",
                    style = TextStyle(fontWeight = FontWeight.Bold),
                )
                Spacer(modifier = Modifier.width(25.dp))
                Column(
                    modifier = Modifier
                        .clickable {
                            expanded_ = !expanded_ // Toggle the dropdown when clicked
                        }
                )
                {
                    Text(
                        text = selectedMajor.name,

                        )
                    Divider(
                        color = Color.Black,
                        thickness = 2.dp,
                        modifier = Modifier.fillMaxWidth()
                    )
                    DropdownMenu(
                        expanded = expanded_,
                        onDismissRequest = { expanded_ = false },
                        modifier = Modifier
                            .width(200.dp)
                            .background(Color.LightGray, MaterialTheme.shapes.small)
                            .border(2.dp, Color.LightGray, MaterialTheme.shapes.small)
                    ) {
                        majors.forEach { major ->
                            DropdownMenuItem(
                                text = {
                                    Text(major.name.toString())
                                },
                                onClick = {
                                    selectedMajor = major
                                    expanded_ = false
                                }
                            )
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = {
                if (name.text.isEmpty() || name.text == "" || name.text.trim() == "" || lastname.text.isEmpty() || lastname.text == "" || lastname.text.trim() == ""){
                    Log.d("State: ", "Fill mandatory fields")
                } else {
                    Data.name = name.text
                    Data.lastname = lastname.text
                    if (selectedSexOption == Sex.Man){
                        Data.sex = 'H'
                    } else {
                        Data.sex = 'M'
                    }
                    val dateformat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                    Data.birthdate = dateformat.format(birthDate.time)
                    Data.major = selectedMajor.name.toString()
                    val intent = Intent(context, ContactDataActivity::class.java)
                    context.startActivity(intent)
                }

            },
            modifier = Modifier
                .padding(8.dp) // Adjust the padding as needed
                .fillMaxWidth()
        ) {
            Text("Next")
        }

    }
}

fun showDatePickerDialog(context: Context, initialDate: Calendar, onDateSelected: (Calendar)->Unit){
    val calendar = Calendar.getInstance().apply{
        timeInMillis = initialDate.timeInMillis
    }
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    DatePickerDialog(
        context,
        {
                _,selectedYear, selectedMonth, selectedDay->
            calendar.set(selectedYear, selectedMonth, selectedDay)
            onDateSelected(calendar)
        },
        year,
        month,
        day
    ).show()
}

enum class Major{
    None,
    Elementary,
    Middle,
    High,
    School_Degree,
    College_Degree
}
enum class Sex{
    Man,
    Woman
}