package com.ccc.todolistvr.firstScreen.room.daoissues

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ccc.todolistvr.firstScreen.room.entities.IssuesEntities
import com.ccc.todolistvr.firstScreen.room.entities.IssuesList
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoIssues {
    @Query("SELECT * FROM Issues")
    fun getAll(): Flow<List<IssuesEntities>>

    @Query("SELECT * FROM IssuesList")
    fun getAllList(): Flow<List<IssuesList>>

    @Query("SELECT * FROM Issues WHERE idListIssue IN (:idListIssue)")
    fun loadListById(idListIssue: Int): List<IssuesEntities>

    @Query("UPDATE Issues SET Checked = :newValue WHERE idIssue = :entityId")
    suspend fun updateChecked(entityId: Int, newValue: Boolean)

    @Query("DELETE FROM Issues")
    suspend fun deleteAllIssues()

//------------------------------------Insert---------------------------------------------
    @Insert
    suspend fun insertIssue(issue: IssuesEntities)

    @Insert
    suspend fun insertIssueList(issueList: IssuesList)
//------------------------------------Update---------------------------------------------
    @Update
    suspend fun updateIssue(issue: IssuesEntities)

    @Update
    suspend fun updateIssueList(issueList: IssuesList)
//------------------------------------Delete---------------------------------------------

    @Delete
    suspend fun deleteIssue(issue: IssuesEntities)

    @Delete
    suspend fun deleteIssueList(issueList: IssuesList)



}