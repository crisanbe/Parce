package com.gerotac.auth.requirement.data.remote.getdetailrequirement

data class Relations(
    val files: List<File>,
    val interventions: List<Any>,
    val users: List<User>
)