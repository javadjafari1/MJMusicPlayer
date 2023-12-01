package ir.thatsmejavad.mjmusic.screens.permissions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.tooling.preview.Preview
import ir.thatsmejavad.mjmusic.ui.theme.MJMusicTheme

@Preview(showBackground = true)
@Composable
fun PermissionsScreenLightPreview() {
    MJMusicTheme(darkTheme = false) {
        CompositionLocalProvider {
            PermissionsScreen {}
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PermissionsScreenDarkPreview() {
    MJMusicTheme(darkTheme = true) {
        CompositionLocalProvider {
            PermissionsScreen {}
        }
    }
}
