@file:OptIn(ExperimentalMaterial3Api::class)

package com.ccc.todolistvr.firstScreen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ccc.todolistvr.date.DatePickerModal
import com.ccc.todolistvr.date.convertMillisToDate
import com.ccc.todolistvr.date.getActualDate
import com.ccc.todolistvr.firstScreen.room.entities.IssuesEntities
import com.ccc.todolistvr.ui.theme.BackgroundButtonColor
import com.ccc.todolistvr.ui.theme.ButtonBLue
import com.ccc.todolistvr.ui.theme.ErrorLetters
import com.ccc.todolistvr.ui.theme.Pink40
import com.ccc.todolistvr.ui.theme.Purple40
import com.ccc.todolistvr.ui.theme.White
import com.ccc.todolistvr.viewmodel.IssuesViewModel


@Composable
fun FirstScreen(modifier: Modifier = Modifier, viewmodel: IssuesViewModel = viewModel()) {

    val issues by viewmodel.stateIssue.collectAsState()
    var showBottomSheet by remember { mutableStateOf(false) }



    Scaffold(
        topBar = { TopBarFirstScreen() },
        bottomBar = { BottomBarFirstScreen() },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showBottomSheet = true },
                content = { Icon(imageVector = Icons.Default.Add, contentDescription = "agregar") })
        }, floatingActionButtonPosition = FabPosition.End
    )
    { innnerpadding ->
        Surface(Modifier.padding(innnerpadding)) {
            LazyColumn {
                items(issues) { issue ->
                    CardList(issue)

                }
            }
        }
    }
    AddIssue(showBottomSheet = showBottomSheet) { showBottomSheet = false }


}


@Composable
fun AddIssue(
    modifier: Modifier = Modifier,
    viewmodel: IssuesViewModel = viewModel(),
    showBottomSheet: Boolean,
    closeButtonSheet: () -> Unit

) {
    var nameIssue by remember { mutableStateOf("") }
    var errorNameIssue by remember { mutableStateOf("!!Debe colocar una tarea!!") }
    var errorNameState by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )
    var goalDateIssue by remember { mutableStateOf<Long?>(null) }
    var showCalendar by remember { mutableStateOf(false) }
    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { closeButtonSheet() },
            sheetState = sheetState


        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                if (errorNameState) Text(errorNameIssue, color = ErrorLetters)
                Row {
                    Spacer(modifier.weight(1f))
                    OutlinedTextField(
                        value = nameIssue,
                        onValueChange = { nameIssue = it },

                        )
                    Spacer(modifier.weight(1f))
                }

                Row {
                    //-------------------------------goal date button------------------------------

                    TextButton(
                        onClick = { showCalendar = true },
                        colors = ButtonDefaults.elevatedButtonColors(containerColor = BackgroundButtonColor),
                        modifier = modifier.padding(10.dp)
                    ) {
                        Text(
                            text = "Goal",
                            fontSize = 15.sp
                        )
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Date Range",
                            modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier.weight(1f))
                    //--------------------------------save button----------------------------------
                    TextButton(
                        onClick = {
                            if (nameIssue.isNotBlank()) {
                                viewmodel.addIssue(
                                    IssuesEntities(
                                        nameIssue = nameIssue,
                                        dateCreateIssue = getActualDate(),//get actual date
                                        dateFinishIssue = goalDateIssue?.let {//take finish date from calendar if he needed if not get actual date
                                            convertMillisToDate(
                                                it
                                            )
                                        } ?: getActualDate(),
                                        idListIssue = 1
                                    )
                                )
                                nameIssue = ""
                                errorNameState = false
                                closeButtonSheet()
                            } else {errorNameState = true}
                        },
                        colors = ButtonDefaults.elevatedButtonColors(
                            containerColor = ButtonBLue,
                            contentColor = White
                        ),
                        modifier = modifier.padding(10.dp)
                    ) {
                        Text(
                            text = "Save",
                            fontSize = 15.sp
                        )
                        Icon(
                            imageVector = Icons.Default.Done,
                            contentDescription = "Save",
                            modifier.size(25.dp)
                        )
                    }
                }
            }
        }
    }
    if (showCalendar) {
        DatePickerModal(
            onDateSelected = { date ->
                goalDateIssue = date
                showCalendar = false

            },
            onDismiss = { showCalendar = false }
        )
    }
}


@Composable
fun CardList(
    issue: IssuesEntities,
    modifier: Modifier = Modifier,
    viewmodel: IssuesViewModel = viewModel()
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp)
            .heightIn(min = 20.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = BackgroundButtonColor),
        shape = RoundedCornerShape(5)
    ) {
        var checked by remember { mutableStateOf(issue.Checked) }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = checked,
                onCheckedChange = {
                    checked = it
                    issue.idIssue?.let { id ->
                        viewmodel.updateChecked(id)
                    }
                })
            if (checked) {//change style of text
                Text(
                    text = "${issue.nameIssue}",
                    textDecoration = TextDecoration.LineThrough,
                    modifier = modifier.padding(5.dp)
                )
            } else {
                Text(
                    text = "${issue.nameIssue}",
                    modifier = modifier.padding(5.dp)
                )
            }
        }

    }
}

@Composable
fun BottomBarFirstScreen() {
    BottomAppBar(containerColor = Purple40) { }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarFirstScreen(viewmodel: IssuesViewModel = viewModel()) {
    TopAppBar(
        title = { Text("ToDoList") },
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = (Icons.Default.Menu),
                    contentDescription = "navegacion"
                )
            }
        },
        actions = {
            IconButton(onClick = { viewmodel.deleteAllIssue() }) {
                Icon(
                    imageVector = (Icons.Default.Delete),
                    contentDescription = "borrar issues"
                )
            }
        },
        colors = topAppBarColors(containerColor = Purple40)
    )
}



