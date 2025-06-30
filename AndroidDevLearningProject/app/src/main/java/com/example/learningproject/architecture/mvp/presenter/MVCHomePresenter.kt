package com.example.learningproject.architecture.mvp.presenter

import com.example.learningproject.architecture.mvp.model.MVPAPIService
import com.example.learningproject.architecture.mvp.model.UserModel

interface MVCViewUpdatable {
    fun updateViewWithUser(user: UserModel)
    fun updateViewWithFullName(fullName: String)
}

class MVCHomePresenter(
    val view: MVCViewUpdatable
) {
    
    private lateinit var user: UserModel
    
    fun getUser() {
        user = MVPAPIService.shared.getUser()
        view.updateViewWithUser(user = user)
    }
    
    fun computeFullName() {
        view.updateViewWithFullName(
            fullName = user.computeFullName()
        )
    }
}