package ir.thatsmejavad.mjmusic.screens.permissions

import GradientBackground
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import ir.thatsmejavad.mjmusic.R
import ir.thatsmejavad.mjmusic.core.ApplicationScreens
import ir.thatsmejavad.mjmusic.enums.PermissionTypes
import ir.thatsmejavad.mjmusic.enums.getDescription
import ir.thatsmejavad.mjmusic.enums.getPermissionsList
import ir.thatsmejavad.mjmusic.enums.getTitle

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionsScreen(
    navController: NavController
) {
    PermissionsScreen(
        navController = navController,
        permissionsState = rememberMultiplePermissionsState(getPermissionsList())
    )
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
internal fun PermissionsScreen(
    navController: NavController,
    permissionsState: MultiplePermissionsState
) {
    LaunchedEffect(permissionsState.allPermissionsGranted) {
        val areAllPermissionsGranted = permissionsState.permissions.all {
            it.status.isGranted
        }

        if (areAllPermissionsGranted) {
            navController.navigate(ApplicationScreens.Home.route) {
                popUpTo(ApplicationScreens.Permissions.route) {
                    inclusive = true
                }
                launchSingleTop = true
            }
        } else {
            //TODO We can show a snackBar and open settings on callback of the snackBar.
        }
    }

    PermissionsScreen {
        if (!permissionsState.allPermissionsGranted) {
            permissionsState.launchMultiplePermissionRequest()
        }
    }
}

@Composable
internal fun PermissionsScreen(
    onAllow: () -> Unit
) {
    val density = LocalDensity.current
    var transparentImageHeightInDp by remember {
        mutableStateOf(0.dp)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .verticalScroll(rememberScrollState())
    ) {
        Box {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .onGloballyPositioned {
                        transparentImageHeightInDp = with(density) {
                            it.size.height.toDp()
                        }
                    },
                painter = painterResource(id = R.drawable.img_permission_background),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )

            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = transparentImageHeightInDp / 5),
                painter = painterResource(id = R.drawable.img_permission),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )

            GradientBackground(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(transparentImageHeightInDp),
                startColor = Color.Transparent,
                endColor = MaterialTheme.colorScheme.surface
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(top = transparentImageHeightInDp - transparentImageHeightInDp / 3),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Text(
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .padding(bottom = 24.dp)
                    .align(CenterHorizontally),
                text = stringResource(R.string.label_permissions),
                style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Medium),
                color = MaterialTheme.colorScheme.onSurface
            )

            PermissionTypes.entries.forEach {
                TitleAndDescriptionText(
                    titleTextId = it.getTitle(),
                    descriptionTextId = it.getDescription()
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            TextButton(
                modifier = Modifier
                    .padding(bottom = 48.dp)
                    .clip(MaterialTheme.shapes.small)
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = MaterialTheme.shapes.small
                    )
                    .align(CenterHorizontally)
                    .navigationBarsPadding(),
                onClick = { onAllow() },
                contentPadding = PaddingValues(
                    horizontal = 72.dp,
                    vertical = 8.dp
                )
            ) {
                Text(
                    text = stringResource(R.string.label_allow),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Composable
private fun TitleAndDescriptionText(titleTextId: Int, descriptionTextId: Int) {
    Column {
        Text(
            text = stringResource(titleTextId),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = stringResource(descriptionTextId),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
