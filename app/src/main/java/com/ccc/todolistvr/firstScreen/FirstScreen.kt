package com.ccc.todolistvr.firstScreen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ccc.todolistvr.firstScreen.room.daoissues.DaoIssues
import com.ccc.todolistvr.ui.theme.Pink40
import com.ccc.todolistvr.ui.theme.Purple40
import com.ccc.todolistvr.ui.theme.ToDoListVrTheme
import com.ccc.todolistvr.viewmodel.IssuesViewModel


@Composable
fun FirstScreen(modifier: Modifier = Modifier,viewmodel:IssuesViewModel) {
    val issues = remember { mutableStateListOf("") }
    var checkedIssue by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { TopBarFirstScreen() },
        bottomBar = { BottomBarFirstScreen() },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { issues.add("hola") },
                content = { Icon(imageVector = Icons.Default.Add, contentDescription = "agregar") })
        }, floatingActionButtonPosition = FabPosition.End
    )
    { innnerpadding ->
        Surface(Modifier.padding(innnerpadding)) {
            LazyColumn {
                items(issues) { issue ->
                    if (issue.isNotBlank()) {
                        ElevatedCard(
                            modifier = modifier
                                .fillParentMaxWidth()
                                .padding(5.dp)
                                .heightIn(min = 20.dp),
                            colors = CardDefaults.elevatedCardColors(containerColor = Pink40),
                            shape = RoundedCornerShape(5)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically){
                                Checkbox(
                                    checked = checkedIssue,
                                    onCheckedChange = { checkedIssue = it })
                                Text(
                                    text = issue,
                                    modifier = modifier.padding(5.dp)
                                )
                            }

                        }
                    }
                }
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
fun TopBarFirstScreen() {
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
        colors = topAppBarColors(containerColor = Purple40)
    )
}

@Preview
@Composable
fun PreviewFirstScreen() {



}