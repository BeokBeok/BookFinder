package com.beok.common.event

data class ViewModelToViewEvent(
    val showLoading: (Boolean) -> Unit,
    val showErrorMessage: (String) -> Unit
)
