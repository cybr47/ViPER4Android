package com.llsl.viper4android.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign

@Composable
fun ConfirmDialog(
    title: String,
    body: String,
    confirmLabel: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    dismissLabel: String = stringResource(android.R.string.cancel),
    destructive: Boolean = false,
) {
    OneUiDialog(onDismissRequest = onDismiss) {
        Column {
            OneUiDialogBody {
                OneUiDialogTitle(text = title)
                Text(
                    text = body,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center,
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(top = UiDimens.Large, bottom = UiDimens.IconLarge),
                )
            }
            OneUiDialogButtons(
                dismissLabel = dismissLabel,
                onDismiss = onDismiss,
                confirmLabel = confirmLabel,
                onConfirm = onConfirm,
                destructive = destructive,
            )
        }
    }
}
