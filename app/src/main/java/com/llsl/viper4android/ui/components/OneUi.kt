package com.llsl.viper4android.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.llsl.viper4android.ui.theme.oneui_seekbar_disabled_dark
import com.llsl.viper4android.ui.theme.oneui_seekbar_disabled_light
import com.llsl.viper4android.ui.theme.oneui_seekbar_track_dark
import com.llsl.viper4android.ui.theme.oneui_seekbar_track_light
import com.llsl.viper4android.ui.theme.oneui_switch_thumb
import com.llsl.viper4android.ui.theme.oneui_switch_thumb_off_stroke_dark
import com.llsl.viper4android.ui.theme.oneui_switch_thumb_off_stroke_light
import com.llsl.viper4android.ui.theme.oneui_switch_track_off_dark
import com.llsl.viper4android.ui.theme.oneui_switch_track_off_light

/** True when the active colour scheme is dark (works for any theme, not only the system setting). */
@Composable
fun isOneUiDark(): Boolean = MaterialTheme.colorScheme.background.luminance() < 0.5f

private val SwitchTrackWidth = 40.dp
private val SwitchTrackHeight = 22.dp
private val SwitchThumbDiameter = 18.dp
private val SwitchThumbStroke = 1.5.dp

/**
 * One UI style switch: slim rounded track, white thumb, outlined thumb when off.
 * Same contract as material3 Switch for the parameters this app uses.
 */
@Composable
fun OneUiSwitch(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val dark = isOneUiDark()
    val progress by animateFloatAsState(
        targetValue = if (checked) 1f else 0f,
        animationSpec = tween(durationMillis = 200),
        label = "oneUiSwitch",
    )
    val primary = MaterialTheme.colorScheme.primary
    val trackOff = if (dark) oneui_switch_track_off_dark else oneui_switch_track_off_light
    val strokeOff = if (dark) oneui_switch_thumb_off_stroke_dark else oneui_switch_thumb_off_stroke_light
    val trackColor = lerp(trackOff, primary, progress)

    Box(
        modifier =
            modifier
                .then(
                    if (onCheckedChange != null) {
                        Modifier.toggleable(
                            value = checked,
                            enabled = enabled,
                            role = Role.Switch,
                            onValueChange = onCheckedChange,
                        )
                    } else {
                        Modifier
                    },
                ).minimumInteractiveComponentSize()
                .alpha(if (enabled) 1f else 0.4f),
        contentAlignment = Alignment.Center,
    ) {
        Canvas(modifier = Modifier.size(width = SwitchTrackWidth, height = SwitchTrackHeight)) {
            val radius = size.height / 2f
            drawRoundRect(
                color = trackColor,
                size = Size(size.width, size.height),
                cornerRadius = CornerRadius(radius, radius),
            )
            val thumbRadius = SwitchThumbDiameter.toPx() / 2f
            val travel = size.width - 2f * radius
            val center = Offset(radius + travel * progress, size.height / 2f)
            drawCircle(color = oneui_switch_thumb, radius = thumbRadius, center = center)
            if (progress < 1f) {
                val stroke = SwitchThumbStroke.toPx()
                drawCircle(
                    color = strokeOff.copy(alpha = 1f - progress),
                    radius = thumbRadius - stroke / 2f,
                    center = center,
                    style = Stroke(width = stroke),
                )
            }
        }
    }
}

private val SeekbarTrackHeight = 3.dp
private val SeekbarThumbSlot = 24.dp
private val SeekbarThumbRadius = 6.5.dp
private val SeekbarThumbStroke = 2.dp
private val SeekbarTickRadius = 1.5.dp

/**
 * One UI style seekbar: 3dp track, hollow ring thumb in the accent colour.
 * Ticks are drawn only for small, discrete step counts (1..9), like the previous Slider usage.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OneUiSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    valueRange: ClosedFloatingPointRange<Float>,
    modifier: Modifier = Modifier,
    steps: Int = 0,
    enabled: Boolean = true,
) {
    val dark = isOneUiDark()
    val primary = MaterialTheme.colorScheme.primary
    val trackInactive = if (dark) oneui_seekbar_track_dark else oneui_seekbar_track_light
    val disabledActive = if (dark) oneui_seekbar_disabled_dark else oneui_seekbar_disabled_light
    val thumbFill = if (dark) Color(0xFF010102) else Color.White
    val drawTicks = steps in 1..9

    Slider(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        valueRange = valueRange,
        steps = steps,
        thumb = {
            Canvas(modifier = Modifier.size(SeekbarThumbSlot)) {
                val center = Offset(size.width / 2f, size.height / 2f)
                val radius = SeekbarThumbRadius.toPx()
                val stroke = SeekbarThumbStroke.toPx()
                val ring = if (enabled) primary else disabledActive
                drawCircle(color = thumbFill, radius = radius, center = center)
                drawCircle(
                    color = ring,
                    radius = radius - stroke / 2f,
                    center = center,
                    style = Stroke(width = stroke),
                )
            }
        },
        track = { state ->
            val span = state.valueRange.endInclusive - state.valueRange.start
            val fraction =
                if (span > 0f) {
                    ((state.value - state.valueRange.start) / span).coerceIn(0f, 1f)
                } else {
                    0f
                }
            val activeColor = if (enabled) primary else disabledActive
            Canvas(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(SeekbarTrackHeight),
            ) {
                val radius = size.height / 2f
                drawRoundRect(
                    color = trackInactive,
                    size = Size(size.width, size.height),
                    cornerRadius = CornerRadius(radius, radius),
                )
                // Slider already insets the track by half the thumb width, so the thumb centre
                // sits exactly at fraction * width of this canvas.
                val activeEnd = fraction * size.width
                drawRoundRect(
                    color = activeColor,
                    size = Size(activeEnd.coerceAtLeast(0f), size.height),
                    cornerRadius = CornerRadius(radius, radius),
                )
                if (drawTicks) {
                    val tickRadius = SeekbarTickRadius.toPx()
                    for (i in 0..steps + 1) {
                        val x = (i / (steps + 1).toFloat()) * size.width
                        val onActive = x <= activeEnd
                        drawCircle(
                            color = if (onActive) thumbFill.copy(alpha = 0.9f) else activeColor.copy(alpha = 0.45f),
                            radius = tickRadius,
                            center = Offset(x, size.height / 2f),
                        )
                    }
                }
            }
        },
    )
}

/**
 * One UI dialog container: 26dp corners, floating-surface colour, no elevation tint.
 */
@Composable
fun OneUiDialog(
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit,
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            shape = RoundedCornerShape(UiDimens.DialogCornerRadius),
            color = MaterialTheme.colorScheme.surfaceContainerHigh,
            tonalElevation = UiDimens.None,
        ) {
            content()
        }
    }
}

/** Centered dialog title, as used by One UI alert dialogs. */
@Composable
fun OneUiDialogTitle(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onSurface,
        textAlign = TextAlign.Center,
        modifier = modifier.fillMaxWidth(),
    )
}

/**
 * One UI dialog button bar: equal-width text buttons split by a thin divider.
 * The confirm label is accent coloured, or error coloured when [destructive].
 */
@Composable
fun OneUiDialogButtons(
    dismissLabel: String,
    onDismiss: () -> Unit,
    confirmLabel: String,
    onConfirm: () -> Unit,
    modifier: Modifier = Modifier,
    confirmEnabled: Boolean = true,
    destructive: Boolean = false,
) {
    val confirmColor = if (destructive) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
    Row(
        modifier =
            modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .padding(horizontal = UiDimens.Medium)
                .padding(bottom = UiDimens.Large),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextButton(
            onClick = onDismiss,
            modifier = Modifier.weight(1f).height(UiDimens.SwitchSlotHeight),
        ) {
            Text(
                text = dismissLabel,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
        VerticalDivider(
            modifier = Modifier.height(UiDimens.IconLarge),
            color = MaterialTheme.colorScheme.outlineVariant,
        )
        TextButton(
            onClick = onConfirm,
            enabled = confirmEnabled,
            modifier = Modifier.weight(1f).height(UiDimens.SwitchSlotHeight),
        ) {
            Text(
                text = confirmLabel,
                style = MaterialTheme.typography.bodyLarge,
                color = if (confirmEnabled) confirmColor else MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

/** One UI "underline" text field colours: transparent container, accent underline. */
@Composable
fun oneUiTextFieldColors(): TextFieldColors =
    TextFieldDefaults.colors(
        focusedContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,
        disabledContainerColor = Color.Transparent,
        errorContainerColor = Color.Transparent,
        focusedIndicatorColor = MaterialTheme.colorScheme.primary,
        unfocusedIndicatorColor = MaterialTheme.colorScheme.outline,
    )

/** Column wrapper that gives dialog bodies the standard One UI 24dp side padding. */
@Composable
fun OneUiDialogBody(content: @Composable () -> Unit) {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(start = UiDimens.IconLarge, end = UiDimens.IconLarge, top = UiDimens.IconLarge),
    ) {
        content()
    }
}
