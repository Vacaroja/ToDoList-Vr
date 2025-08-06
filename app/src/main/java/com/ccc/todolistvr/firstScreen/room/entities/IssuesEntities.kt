package com.ccc.todolistvr.firstScreen.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    tableName = "Issues", foreignKeys = [ForeignKey(
        entity = IssuesList::class,
        parentColumns = arrayOf("idListIssue"),
        childColumns = arrayOf("idListIssue"),
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    )]
)
data class IssuesEntities
    (
    @PrimaryKey(autoGenerate = true) val idIssue: Int = 1,
    @ColumnInfo(name = "NameIssue") var nameIssue: String?,
    @ColumnInfo(name = "DateCreated") val dateCreateIssue: String?,
    @ColumnInfo(name = "DateFinish") val dateFinishIssue: String?,
    @ColumnInfo(name = "Checked") var Checked: Boolean = false,
    @ColumnInfo(name = "idListIssue") val idListIssue: Int?,

    )

@Entity(tableName = "IssuesList")
data class IssuesList(
    @PrimaryKey(autoGenerate = true) val idListIssue: Int = 1,
    @ColumnInfo(name = "Name") var nameIssue: String?,

    )


