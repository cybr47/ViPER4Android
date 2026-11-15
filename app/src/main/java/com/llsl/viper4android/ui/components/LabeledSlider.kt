package com.llsl.viper4android.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.llsl.viper4android.R
import java.util.Locale
import kotlin.math.roundToInt

data class SliderEdit(
    val displayValue: Double,
    val displayRange: ClosedFloatingPointRange<Double>,
    val decimals: Int,
    val onCommit: (Double) -> Unit,
    val unit: String = "",
)

@Composable
fun LabeledSlider(
    label: String,
    value: Float,
    onValueChange: (Float) -> Unit,
    valueRange: ClosedFloatingPointRange<Float>,
    modifier: Modifier = Modifier,
    steps: Int = 0,
    enabled: Boolean = true,
    valueLabel: String? = null,
    edit: SliderEdit? = null,
) {
    var showEditDialog by remember { mutableStateOf(false) }
    val canEdit = enabled && edit != null

    Column(modifier = modifier.fillMaxWidth().padding(vertical = UiDimens.XSmall)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.weight(1f),
            )
            val valueModifier =
                if (canEdit) {
                    Modifier.clickable { showEditDialog = true }
                } else {
                    Modifier
                }.padding(horizontal = UiDimens.Medium, vertical = UiDimens.XSmall)
            Text(
                text = valueLabel ?: value.roundToInt().toString(),
                style = MaterialTheme.typography.bodyMedium,
                color =
                    if (canEdit) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    },
                modifier = valueModifier,
            )
        }
        OneUiSlider(
            value = value,
            onValueChange = onValueChange,
            valueRange = valueRange,
            steps = steps,
            enabled = enabled,
            modifier = Modifier.fillMaxWidth(),
        )
    }

    if (showEditDialog && edit != null) {
        NumberInputDialog(
            label = label,
            edit = edit,
            onDismiss = { showEditDialog = false },
        )
    }
}

@Composable
fun NumberInputDialog(
    label: String,
    edit: SliderEdit,
    onDismiss: () -> Unit,
) {
    val min = edit.displayRange.start
    val max = edit.displayRange.endInclusive

    fun fmt(v: Double): String =
        if (edit.decimals <= 0) {
            v.roundToInt().toString()
        } else {
            String.format(Locale.US, "%.${edit.decimals}f", v)
        }

    var text by remember { mutableStateOf(fmt(edit.displayValue)) }
    val parsed = text.trim().toDoubleOrNull()
    val isValid = parsed != null

    val rangeHint =
        if (edit.unit.isNotEmpty()) {
            stringResource(
                R.string.dialog_value_range_unit,
                fmt(min),
                fmt(max),
                edit.unit,
            )
        } else {
            stringResource(R.string.dialog_value_range, fmt(min), fmt(max))
        }

    OneUiDialog(onDismissRequest = onDismiss) {
        Column {
            OneUiDialogBody {
                OneUiDialogTitle(text = label)
                TextField(
                    value = text,
                    onValueChange = { text = it },
                    singleLine = true,
                    isError = !isValid,
                    suffix = if (edit.unit.isNotEmpty()) ({ Text(edit.unit) }) else null,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    supportingText = { Text(rangeHint) },
                    colors = oneUiTextFieldColors(),
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(top = UiDimens.Standard, bottom = UiDimens.Standard),
                )
            }
            OneUiDialogButtons(
                dismissLabel = stringResource(android.R.string.cancel),
                onDismiss = onDismiss,
                confirmLabel = stringResource(android.R.string.ok),
                onConfirm = {
                    val clamped = parsed!!.coerceIn(min, max)
                    edit.onCommit(clamped)
                    onDismiss()
                },
                confirmEnabled = isValid,
            )
        }
    }
}
