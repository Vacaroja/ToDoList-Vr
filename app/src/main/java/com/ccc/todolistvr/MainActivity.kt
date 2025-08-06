package com.ccc.todolistvr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import com.ccc.todolistvr.firstScreen.FirstScreen
import com.ccc.todolistvr.firstScreen.room.databaseissues.AppDatabase
import com.ccc.todolistvr.ui.theme.ToDoListVrTheme
import com.ccc.todolistvr.viewmodel.IssueViewModelFactory
import com.ccc.todolistvr.viewmodel.IssuesViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "IssuesDatabase"
        ).build()
        val dao = db.DaoIssues()
        val factory = IssueViewModelFactory(dao)
        val viewmodel:IssuesViewModel by viewModels { factory }
        enableEdgeToEdge()
        setContent {
            ToDoListVrTheme {
                FirstScreen(viewmodel= viewmodel)
            }
        }
    }
}



