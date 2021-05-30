package ixidev.shamela.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ixidev.shamela.ui.theme.SelectionColor


/**
 * Created by Abdelmajid ID ALI, on 30/05/2021
 * Github : [https://github.com/ixiDev]
 */

sealed class BottomBarItem(
    val title: String
) {
    object AllBooks : BottomBarItem("جميع الكتب")
    object MyBooks : BottomBarItem("مكتبتي")
}

@Composable
fun ShamelaBottomBar(
    modifier: Modifier = Modifier,
    onItemSelected: (item: BottomBarItem) -> Unit = {}
) {
    Row(
        modifier = modifier
            .height(75.dp)
            .fillMaxWidth()
    ) {

        var selectedItem: BottomBarItem by remember { mutableStateOf(BottomBarItem.AllBooks) }

        val onItemClicked = { item: BottomBarItem ->
            selectedItem = item
            onItemSelected.invoke(item)
        }
        BottomBarItemView(
            modifier = Modifier.weight(1f),
            bottomBarItem = BottomBarItem.MyBooks,
            selected = selectedItem == BottomBarItem.MyBooks,
            onClick = onItemClicked
        )
        BottomBarItemView(
            modifier = Modifier.weight(1f),
            bottomBarItem = BottomBarItem.AllBooks,
            selected = selectedItem == BottomBarItem.AllBooks,
            onClick = onItemClicked
        )
    }
}

@Composable
fun BottomBarItemView(
    modifier: Modifier = Modifier,
    bottomBarItem: BottomBarItem,
    selected: Boolean = false,
    onClick: (self: BottomBarItem) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .clickable(enabled = true) {
                onClick.invoke(bottomBarItem)
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        val color = if (selected) SelectionColor else Color.Unspecified
        Divider(color = color, thickness = 2.dp)
        Text(
            text = bottomBarItem.title,
            textAlign = TextAlign.Center,
            color = color
        )
    }
}