package ir.thatsmejavad.mjmusic.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ir.thatsmejavad.mjmusic.R
import ir.thatsmejavad.mjmusic.core.ApplicationScreens

@Composable
internal fun PermissionsScreen(navController: NavController) {
    val density = LocalDensity.current
    var transparentImageHeightInDp by remember {
        mutableStateOf(0.dp)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.img_permission_background),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .onGloballyPositioned {
                        transparentImageHeightInDp = with(density) {
                            it.size.height.toDp()
                        }
                    },
                contentScale = ContentScale.FillWidth
            )

            Image(
                painter = painterResource(id = R.drawable.img_permission),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = transparentImageHeightInDp / 5),
                contentScale = ContentScale.FillWidth
            )

            Image(
                painter = painterResource(R.drawable.img_gradient_rectangle),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(transparentImageHeightInDp),
                contentScale = ContentScale.FillWidth
            )

            Text(
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .padding(top = transparentImageHeightInDp / 3)
                    .align(Center),
                text = stringResource(R.string.label_permissions),
                style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Medium),
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(top = (transparentImageHeightInDp - transparentImageHeightInDp / 5))
        ) {
            TitleAndDescriptionText(
                titleTextId = R.string.label_storage_access,
                descriptionTextId = R.string.label_storage_access_description
            )

            Spacer(modifier = Modifier.height(18.dp))

            TitleAndDescriptionText(
                titleTextId = R.string.label_media_library_access,
                descriptionTextId = R.string.label_media_library_access_description
            )

            Spacer(modifier = Modifier.height(18.dp))

            TitleAndDescriptionText(
                titleTextId = R.string.label_notification_access,
                descriptionTextId = R.string.label_notification_access_description
            )

            Spacer(modifier = Modifier.height(18.dp))

            TitleAndDescriptionText(
                titleTextId = R.string.label_phone_status_and_identity,
                descriptionTextId = R.string.label_phone_status_description
            )

            Spacer(modifier = Modifier.height(18.dp))

            TitleAndDescriptionText(
                titleTextId = R.string.label_bluetooth_access,
                descriptionTextId = R.string.label_bluetooth_access_description
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                modifier = Modifier
                    .clickable {
                        navController.navigate(ApplicationScreens.Home.route) {
                            popUpTo(ApplicationScreens.Permissions.route) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    }
                    .clip(MaterialTheme.shapes.small)
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = MaterialTheme.shapes.small
                    )
                    .padding(horizontal = 72.dp, vertical = 8.dp)
                    .align(CenterHorizontally),
                text = stringResource(R.string.label_allow),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Composable
fun TitleAndDescriptionText(titleTextId: Int, descriptionTextId: Int) {
    Text(
        text = stringResource(titleTextId),
        style = MaterialTheme.typography.titleSmall,
        color = MaterialTheme.colorScheme.onSurface
    )

    Spacer(modifier = Modifier.height(6.dp))

    Text(
        text = stringResource(id = descriptionTextId),
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}
