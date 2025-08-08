package com.ccc.todolistvr.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ccc.todolistvr.room.daoissues.DaoIssues
import com.ccc.todolistvr.room.entities.IssuesEntities
import com.ccc.todolistvr.room.entities.IssuesList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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
            dao.getAllList().collect { issueList -> _stateIssueList.value = issueList }

        }
        viewModelScope.launch {
            dao.getAll().collect { issueList -> _stateIssue.value = issueList }
        }
        ActualIssueList(1)
    }

    //-----------------------------funtions--------------------------------------
    fun ActualIssueList(id:Int?){
        viewModelScope.launch {
            dao.loadListById(id).collect { issue -> _stateIssueActual.value = issue }
        }
    }

    //-----------------------------CRUD----------------------------------
    fun readIssues():List<IssuesEntities>{
        return stateIssue.value
    }
//----------------------------------add------------------------
    fun addIssue(issue: IssuesEntities) {
        viewModelScope.launch { dao.insertIssue(issue) }
    }

    fun addIssueList(issue: IssuesList) {
        viewModelScope.launch { dao.insertIssueList(issue) }
    }

    //-------------------------------update-----------------------
    fun updateChecked(id: Int) {//cambio de checked revisas gemini si no me acuerdo
        println("este $id")
        viewModelScope.launch {
            var currentIssue = _stateIssue.value.firstOrNull { it.idIssue == id }
            println("estes $id")
            if (currentIssue != null) {
                println("esteses $id")
                currentIssue.Checked = !currentIssue.Checked
                dao.updateIssue(currentIssue)
            }
            else println("no se guardo compai")

        }

    }

    fun updateIssue(issue: IssuesEntities) {
        viewModelScope.launch { dao.updateIssue(issue) }

    }

    fun updateIssueList(issue: IssuesList) {
        viewModelScope.launch { dao.updateIssueList(issue) }

    }

    //-------------------------------delete-----------------------
    fun deleteIssue(issue: IssuesEntities) {
        viewModelScope.launch { dao.deleteIssue(issue) }

    }

    fun deleteIssueList(issue: IssuesList) {
        viewModelScope.launch { dao.deleteIssueList(issue) }

    }

    fun deleteAllIssue(){
        viewModelScope.launch { dao.deleteAllIssues() }
    }

}
