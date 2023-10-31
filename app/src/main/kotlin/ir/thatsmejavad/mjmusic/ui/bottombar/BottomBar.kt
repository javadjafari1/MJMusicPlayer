package ir.thatsmejavad.mjmusic.ui.bottombar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.thatsmejavad.mjmusic.ui.theme.MJMusicTheme

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    screens: List<BottomBarItem>,
    currentDestination: String?,
    onItemClick: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .heightIn(min = 80.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically
    ) {
        screens.forEach { screen ->
            val selected = currentDestination == screen.route
            AddBottomBarItem(
                icon = if (selected) screen.focusedIcon else screen.icon,
                title = screen.title,
                selected = selected,
                background = if (selected) {
                    MaterialTheme.colorScheme.secondaryContainer
                } else {
                    Color.Transparent
                },
                onItemClick = {
                    onItemClick(screen.route)
                }
            )
        }
    }
}

@Composable
private fun RowScope.AddBottomBarItem(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    @StringRes title: Int,
    selected: Boolean,
    background: Color,
    onItemClick: () -> Unit
) {
    val weight = remember { Animatable(if (selected) 1f else WEIGHT_7) }
    val animSpec: TweenSpec<Float> = tween(
        durationMillis = 300,
        easing = LinearOutSlowInEasing
    )

    LaunchedEffect(selected) {
        if (selected) {
            weight.animateTo(1f, animSpec)
        } else {
            weight.animateTo(WEIGHT_7, animSpec)
        }
    }

    Row(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 18.dp)
            .heightIn(max = 44.dp)
            .weight(weight.value)
            .clip(MaterialTheme.shapes.small)
            .background(background)
            .clickable(onClick = onItemClick)
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            space = 6.dp,
            alignment = Alignment.CenterHorizontally
        )
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = null
        )
        AnimatedVisibility(visible = selected) {
            Text(
                text = stringResource(title),
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

@Preview
@Composable
private fun BottomBarPreview() {
    MJMusicTheme {
        BottomBar(
            screens = BottomBarItem.screens,
            currentDestination = "home",
            onItemClick = {}
        )
    }
}

private const val WEIGHT_7 = .7f
