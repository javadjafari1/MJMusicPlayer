package ir.thatsmejavad.mjmusic.ui.bottombar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.thatsmejavad.mjmusic.ui.theme.MJMusicTheme

@Composable
fun MJMusicBottomBar(
    items: List<BottomBarItem>,
    currentBottomBarItem: BottomBarItem?,
    modifier: Modifier = Modifier,
    onItemClick: (BottomBarItem) -> Unit
) {
    Row(
        modifier = Modifier
            .heightIn(min = 80.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        items.forEach { item ->
            val selected = currentBottomBarItem == item
            BottomBarItem(
                icon = item.icon,
                focusedIcon = item.focusedIcon,
                title = item.title,
                selected = selected,
                background = if (selected) {
                    MaterialTheme.colorScheme.secondaryContainer
                } else {
                    Color.Transparent
                },
                onItemClick = {
                    onItemClick(item)
                }
            )
        }
    }
}

@Composable
private fun BottomBarItem(
    @DrawableRes icon: Int,
    @DrawableRes focusedIcon: Int,
    @StringRes title: Int,
    selected: Boolean,
    background: Color,
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 18.dp)
            .clip(MaterialTheme.shapes.small)
            .background(background)
            .animateContentSize()
            .clickable(onClick = onItemClick)
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            )
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            space = 6.dp,
            alignment = Alignment.CenterHorizontally
        )
    ) {
        Crossfade(
            targetState = selected,
            label = "Selected Item Animation"
        ) { isSelected ->
            val currentIcon = if (isSelected) focusedIcon else icon
            Icon(
                painter = painterResource(currentIcon),
                contentDescription = null
            )
        }
        AnimatedVisibility(
            visible = selected,
            enter = fadeIn(tween(delayMillis = 100)) + expandHorizontally(),
            exit = fadeOut(tween()) + shrinkHorizontally(),
        ) {
            Text(
                text = stringResource(title),
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                style = MaterialTheme.typography.labelMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview
@Composable
private fun BottomBarPreview() {
    MJMusicTheme {
        MJMusicBottomBar(
            items = BottomBarItem.items,
            currentBottomBarItem = BottomBarItem.Home,
            onItemClick = {}
        )
    }
}
