package com.ccc.todolistvr.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ccc.todolistvr.firstScreen.room.daoissues.DaoIssues
import com.ccc.todolistvr.firstScreen.room.entities.IssuesEntities
import com.ccc.todolistvr.firstScreen.room.entities.IssuesList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class IssuesViewModel(private val dao: DaoIssues) : ViewModel() {
    //StateFlow for list of issues
    private val _stateIssueList = MutableStateFlow<List<IssuesList>>(emptyList())
    val stateIssueList: StateFlow<List<IssuesList>> = _stateIssueList.asStateFlow()
    //StateFlow for all issues
    private val _stateIssue = MutableStateFlow<List<IssuesEntities>>(emptyList())
    val stateIssue: StateFlow<List<IssuesEntities>> = _stateIssue.asStateFlow()
    //StateFlow for list of issues
    private val _stateIssueActual = MutableStateFlow<List<IssuesEntities>>(emptyList())
    val stateIssueActual: StateFlow<List<IssuesEntities>> = _stateIssueActual.asStateFlow()

    init {
        viewModelScope.launch {
            dao.getAll().collectLatest { issueList -> _stateIssue.value = issueList }
        }
    }

    //-----------------------------funtions--------------------------------------



    //-----------------------------CRUD----------------------------------
//----------------------------------add------------------------
    fun addIssue(Issue: IssuesEntities) {
        viewModelScope.launch { dao.insertIssue(Issue) }
    }

    fun addIssueList(Issue: IssuesList) {
        viewModelScope.launch { dao.insertIssueList(Issue) }
    }

    //-------------------------------update-----------------------
    fun updateChecked(id:Int){//cambio de checked revisas gemini si no me acuerdo
        viewModelScope.launch {
            var currentIssue= _stateIssue.value.firstOrNull{it.idIssue == id}

        }

    }

    fun updateIssue(Issue: IssuesEntities) {
        viewModelScope.launch { dao.updateIssue(Issue) }

    }
    fun updateIssueList(Issue: IssuesList) {
        viewModelScope.launch { dao.updateIssueList(Issue) }

    }
    //-------------------------------delete-----------------------
    fun deleteIssue(Issue: IssuesEntities) {
        viewModelScope.launch { dao.deleteIssue(Issue) }

    }
    fun deleteIssueList(Issue: IssuesList) {
        viewModelScope.launch { dao.deleteIssueList(Issue) }

    }

}