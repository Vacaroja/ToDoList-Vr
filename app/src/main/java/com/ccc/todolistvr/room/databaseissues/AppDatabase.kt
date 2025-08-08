package com.ccc.todolistvr.room.databaseissues

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ccc.todolistvr.room.daoissues.DaoIssues
import com.ccc.todolistvr.room.entities.IssuesEntities
import com.ccc.todolistvr.room.entities.IssuesList

@Database(entities = [IssuesEntities::class, IssuesList::class], version = 3)
abstract class AppDatabase: RoomDatabase() {
    abstract fun DaoIssues(): DaoIssues
}