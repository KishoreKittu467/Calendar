package com.kkapps.bubbles.features.contact

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ContactRoot(
    viewModel: ContactViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ContactScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun ContactScreen(
    state: ContactState,
    onAction: (ContactAction) -> Unit,
) {

}

@Preview
@Composable
private fun Preview() {
    MaterialTheme {
        ContactScreen(
            state = ContactState(),
            onAction = {}
        )
    }
}