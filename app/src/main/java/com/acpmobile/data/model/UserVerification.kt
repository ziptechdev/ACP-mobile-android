package com.acpmobile.data.model

data class UserVerification(
    val timestamp: String,
    val account: Account,
    val workflowExecution: WorkflowExecution

)