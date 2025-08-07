package com.ccc.todolistvr.firstScreen.room.databaseissues

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ccc.todolistvr.firstScreen.room.daoissues.DaoIssues
import com.ccc.todolistvr.firstScreen.room.entities.IssuesEntities
import com.ccc.todolistvr.firstScreen.room.entities.IssuesList

@Database(entities = [IssuesEntities::class, IssuesList::class], version = 2)
abstract class AppDatabase: RoomDatabase() {
    abstract fun DaoIssues(): DaoIssues
}