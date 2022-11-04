package com.gerotac.auth.requirement.presentation.ui.homerequirement.search

sealed class SearchEvent {
    data class EnteredCharacter(val value: String): SearchEvent()
}
