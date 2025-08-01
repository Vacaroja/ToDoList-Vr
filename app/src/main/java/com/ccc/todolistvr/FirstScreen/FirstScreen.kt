package com.ccc.todolistvr.FirstScreen


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Label
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ccc.todolistvr.ui.theme.Pink40
import com.ccc.todolistvr.ui.theme.Purple40


@Composable
fun FirstScreen(modifier:Modifier = Modifier) {
    Scaffold(
        topBar = { TopBarFirstScreen() },
        bottomBar = { BottomBarFirstScreen() },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                content = { Icon(imageVector = Icons.Default.Add, contentDescription = "agregar") })
        }, floatingActionButtonPosition = FabPosition.End
    )
    { innnerpadding ->
        Surface(Modifier.padding(innnerpadding)) {
            LazyColumn() {
                item {
                    ElevatedCard(modifier = modifier.size(100.dp),colors = CardDefaults.elevatedCardColors(containerColor = Pink40)) {
                        Text(
                            text = "Compra"
                        )
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
fun TopBarFirstScreen(modifier: Modifier = Modifier) {
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
    FirstScreen()


}