package com.ccc.todolistvr.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ccc.todolistvr.firstScreen.room.daoissues.DaoIssues

@Suppress("UNCHECKED_CAST")
class IssueViewModelFactory(private val dao: DaoIssues):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T{
        return IssuesViewModel(dao) as T
    }
}