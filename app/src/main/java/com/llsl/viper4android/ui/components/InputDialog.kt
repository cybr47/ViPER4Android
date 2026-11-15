package com.llsl.viper4android.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign

@Composable
fun InputDialog(
    title: String,
    initialValue: String,
    confirmLabel: String,
    onConfirm: (String) -> Unit,
    onDismiss: () -> Unit,
    dismissLabel: String = stringResource(android.R.string.cancel),
    body: String? = null,
    placeholder: String = "",
) {
    var value by remember { mutableStateOf(initialValue) }

    OneUiDialog(onDismissRequest = onDismiss) {
        Column {
            OneUiDialogBody {
                OneUiDialogTitle(text = title)
                body?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center,
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(top = UiDimens.Large),
                    )
                }
                TextField(
                    value = value,
                    onValueChange = { value = it },
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(top = UiDimens.Standard, bottom = UiDimens.Standard),
                    placeholder = { Text(placeholder) },
                    singleLine = true,
                    colors = oneUiTextFieldColors(),
                )
            }
            OneUiDialogButtons(
                dismissLabel = dismissLabel,
                onDismiss = onDismiss,
                confirmLabel = confirmLabel,
                onConfirm = { if (value.isNotBlank()) onConfirm(value.trim()) },
                confirmEnabled = value.isNotBlank(),
            )
        }
    }
}
