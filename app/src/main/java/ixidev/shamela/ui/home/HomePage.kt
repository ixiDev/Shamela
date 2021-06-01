package ixidev.shamela.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ixidev.shamela.data.entities.BookInfo
import ixidev.shamela.data.models.CategoryItemInfo
import ixidev.shamela.ui.theme.getCategoryColor

/**
 * Created by Abdelmajid ID ALI, on 30/05/2021
 * Github : [https://github.com/ixiDev]
 */


@Composable
fun HomePageView(viewModel: HomeViewModel, onBookClicked: (bookId: Int) -> Unit = {}) {
    val categories by viewModel.categories.collectAsState(initial = emptyList())
    LazyColumn {
        items(categories) { category ->
            Surface(
                modifier = Modifier
                    .padding(
                        top = 5.dp, bottom = 5.dp,
                        start = 8.dp, end = 8.dp
                    )
                    .fillMaxWidth()
                    .height(250.dp),
                elevation = 4.dp,
                color = Color.White,
                shape = RoundedCornerShape(5.dp)
            ) {
                CategoryRow(
                    category = category,
                    viewModel = viewModel,
                    onBookClicked = onBookClicked
                )
            }
        }
    }
}


@Composable
fun CategoryRow(
    modifier: Modifier = Modifier,
    category: CategoryItemInfo,
    viewModel: HomeViewModel,
    onBookClicked: (bookId: Int) -> Unit = {}
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.End
    ) {

        CategoryHeaderView(category)

        Divider(
            modifier = Modifier
                .fillMaxWidth(),
            color = MaterialTheme.colors.primary,
            thickness = 2.dp
        )

        val books by viewModel.getCategoryBooks(category.category.id)
            .collectAsState(initial = emptyList())

        if (books.isEmpty()) {
            Text(
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.CenterHorizontally),
                text = "ليس هناك اي كتب في هذا القسم",
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                fontWeight = FontWeight.Light
            )
        } else
            LazyRow(
                modifier = Modifier
                    .padding(10.dp),
                reverseLayout = true,

                ) {
                items(books) { book ->
                    BookVerticalRow(
                        modifier = Modifier.clickable {
                            onBookClicked.invoke(book.id)
                        },
                        book = book
                    )
                }
            }
    }

}

@Composable
private fun CategoryHeaderView(category: CategoryItemInfo) {
    Row(
        modifier = Modifier
            .background(getCategoryColor(category.category.id))
            .padding(top = 8.dp, end = 10.dp, start = 10.dp)
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterVertically),
            text = "عدد الكتب (${category.count})",
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black
        )
        Text(
            modifier = Modifier
                .weight(1f),
            text = "${category.category.name}",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.End,
            color = Color.Black
        )
    }
}

@Composable
fun BookVerticalRow(modifier: Modifier = Modifier, book: BookInfo) {
    Surface(
        modifier = modifier
            .padding(4.dp)
            .fillMaxHeight()
            .width(120.dp),
        color = MaterialTheme.colors.primary,
        shape = RoundedCornerShape(
            topStart = 15.dp,
            topEnd = 0.dp,
            bottomStart = 0.dp,
            bottomEnd = 0.dp
        ),
        elevation = 4.dp
    ) {
        Text(
            modifier = Modifier
                .padding(8.dp),
            text = "${book.name}",
            textAlign = TextAlign.End,
            fontSize = 14.sp,
        )
    }
}
