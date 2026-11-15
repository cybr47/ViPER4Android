package com.llsl.viper4android.ui.theme

import androidx.compose.ui.graphics.Color

// One UI palette. Values come from the sesl-androidx "basic_token_*" colour tokens:
//   gray_01 #FCFCFF, gray_05 #F3F3F5, gray_10 #E9E9EC, gray_15 #E4E4E7, gray_20 #CCCCCE,
//   gray_40 #A3A3A7, gray_50 #848487, gray_60 #636368, gray_80 #3A3A3D, gray_90 #252528,
//   gray_95 #17171A, gray_100 #010102, primary_40 #598FFF, primary_50 #387AFF, primary_60 #2E65D4,
//   red_50 #D93E36, orange_50 #E65B17.

val md_theme_light_primary = Color(0xFF387AFF)
val md_theme_light_onPrimary = Color(0xFFFFFFFF)
val md_theme_light_primaryContainer = Color(0xFFDCE7FF)
val md_theme_light_onPrimaryContainer = Color(0xFF12306F)
val md_theme_light_secondary = Color(0xFF636368)
val md_theme_light_onSecondary = Color(0xFFFCFCFF)
val md_theme_light_secondaryContainer = Color(0xFFE4E4E7)
val md_theme_light_onSecondaryContainer = Color(0xFF252528)
val md_theme_light_tertiary = Color(0xFF2E65D4)
val md_theme_light_onTertiary = Color(0xFFFFFFFF)
val md_theme_light_tertiaryContainer = Color(0xFFDCE7FF)
val md_theme_light_onTertiaryContainer = Color(0xFF12306F)
val md_theme_light_error = Color(0xFFD93E36)
val md_theme_light_onError = Color(0xFFFFFFFF)
val md_theme_light_errorContainer = Color(0xFFFBE3E1)
val md_theme_light_onErrorContainer = Color(0xFF7A1712)
val md_theme_light_background = Color(0xFFF3F3F5)
val md_theme_light_onBackground = Color(0xFF010102)
val md_theme_light_surface = Color(0xFFFCFCFF)
val md_theme_light_onSurface = Color(0xFF010102)
val md_theme_light_surfaceVariant = Color(0xFFE4E4E7)
val md_theme_light_onSurfaceVariant = Color(0xFF636368)
val md_theme_light_outline = Color(0xFFA3A3A7)
val md_theme_light_outlineVariant = Color(0xFFE4E4E7)

val md_theme_light_surfaceContainerLowest = Color(0xFFFCFCFF)
val md_theme_light_surfaceContainerLow = Color(0xFFFCFCFF)
val md_theme_light_surfaceContainer = Color(0xFFF3F3F5)
val md_theme_light_surfaceContainerHigh = Color(0xFFFCFCFF)
val md_theme_light_surfaceContainerHighest = Color(0xFFE9E9EC)

val md_theme_dark_primary = Color(0xFF598FFF)
val md_theme_dark_onPrimary = Color(0xFFFCFCFF)
val md_theme_dark_primaryContainer = Color(0xFF1F3D80)
val md_theme_dark_onPrimaryContainer = Color(0xFFDCE7FF)
val md_theme_dark_secondary = Color(0xFFA3A3A7)
val md_theme_dark_onSecondary = Color(0xFF010102)
val md_theme_dark_secondaryContainer = Color(0xFF3A3A3D)
val md_theme_dark_onSecondaryContainer = Color(0xFFFCFCFF)
val md_theme_dark_tertiary = Color(0xFF598FFF)
val md_theme_dark_onTertiary = Color(0xFF010102)
val md_theme_dark_tertiaryContainer = Color(0xFF1F3D80)
val md_theme_dark_onTertiaryContainer = Color(0xFFDCE7FF)
val md_theme_dark_error = Color(0xFFFF6B62)
val md_theme_dark_onError = Color(0xFF010102)
val md_theme_dark_errorContainer = Color(0xFF5C1B17)
val md_theme_dark_onErrorContainer = Color(0xFFFBE3E1)
val md_theme_dark_background = Color(0xFF010102)
val md_theme_dark_onBackground = Color(0xFFFCFCFF)
val md_theme_dark_surface = Color(0xFF17171A)
val md_theme_dark_onSurface = Color(0xFFFCFCFF)
val md_theme_dark_surfaceVariant = Color(0xFF3A3A3D)
val md_theme_dark_onSurfaceVariant = Color(0xFFA3A3A7)
val md_theme_dark_outline = Color(0xFF636368)
val md_theme_dark_outlineVariant = Color(0xFF3A3A3D)

val md_theme_dark_surfaceContainerLowest = Color(0xFF010102)
val md_theme_dark_surfaceContainerLow = Color(0xFF17171A)
val md_theme_dark_surfaceContainer = Color(0xFF252528)
val md_theme_dark_surfaceContainerHigh = Color(0xFF252528)
val md_theme_dark_surfaceContainerHighest = Color(0xFF3A3A3D)

// Master power button: "on" uses the One UI blue, "off" falls back to the error container.
val master_on_container_light = Color(0xFF387AFF)
val master_on_onContainer_light = Color(0xFFFFFFFF)
val master_on_container_dark = Color(0xFF387AFF)
val master_on_onContainer_dark = Color(0xFFFCFCFF)

// One UI widget tokens (switch / seekbar).
val oneui_switch_track_off_light = Color(0xFFA3A3A7)
val oneui_switch_track_off_dark = Color(0xFF636368)
val oneui_switch_thumb = Color(0xFFFCFCFF)
val oneui_switch_thumb_off_stroke_light = Color(0xFF8C8C8C)
val oneui_switch_thumb_off_stroke_dark = Color(0xFF8F8F8F)
val oneui_seekbar_track_light = Color(0x1A17171A)
val oneui_seekbar_track_dark = Color(0x26FCFCFF)
val oneui_seekbar_disabled_light = Color(0xFFDEDEE1)
val oneui_seekbar_disabled_dark = Color(0xFF77777A)

val status_active_green = Color(0xFF4CAF50)
val log_source_app = Color(0xFF66BB6A)
val log_source_driver = Color(0xFFAB47BC)
val log_level_info = Color(0xFF42A5F5)
val log_level_warn = Color(0xFFFFA726)
val log_level_debug = Color.Gray
val log_level_error = Color(0xFFEF5350)
val log_level_unspecified = Color.Unspecified

val md_alert_note = Color(0xFF539BF5)
val md_alert_tip = Color(0xFF57AB5A)
val md_alert_important = Color(0xFF986EE2)
val md_alert_warning = Color(0xFFC69026)
val md_alert_caution = Color(0xFFE5534B)
