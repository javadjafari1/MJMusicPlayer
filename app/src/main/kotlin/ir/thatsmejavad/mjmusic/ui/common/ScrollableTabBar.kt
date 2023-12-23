package ir.thatsmejavad.mjmusic.ui.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun ScrollableTabBar(
    modifier: Modifier = Modifier,
    labels: List<String>,
    selectedTabIndex: Int,
    onTabClicked: (index: Int) -> Unit
) {
    ScrollableTabRow(
        modifier = modifier.padding(8.dp),
        selectedTabIndex = selectedTabIndex,
        edgePadding = 0.dp,
        divider = {},
        indicator = { tabPositions ->
            TabRowDefaults.PrimaryIndicator(
                modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
            )
        },
    ) {
        labels.forEachIndexed { index, title ->
            val selectedTab = selectedTabIndex == index
            Tab(
                modifier = Modifier.clip(MaterialTheme.shapes.small),
                selected = selectedTab,
                onClick = {
                    onTabClicked(index)
                },
                text = {
                    Text(
                        text = title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = if (selectedTab) {
                            MaterialTheme.colorScheme.surfaceTint
                        } else {
                            MaterialTheme.colorScheme.onBackground
                        },
                    )
                }
            )
        }
    }
}
