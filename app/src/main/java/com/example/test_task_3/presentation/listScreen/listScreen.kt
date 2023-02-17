package com.example.test_task_3.presentation.listScreen

import androidx.activity.OnBackPressedCallback
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalTextInputService
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.test_task_3.R
import com.example.test_task_3.domain.constance.Constance
import com.example.test_task_3.domain.models.MyItem
import com.example.test_task_3.presentation.detailsScreen.toDetails

@Composable
fun ListScreen(navController: NavController, viewModel: ListViewModel = hiltViewModel()) {
    val data = viewModel.state.collectAsState().value
    val search = viewModel.search.collectAsState().value

    var isSearchVisible by remember {
        mutableStateOf(!search.isEmpty())
    }



    Scaffold(topBar = {
        TopBar(
            search,
            isSearchVisible,
            viewModel::onSearch,
            onClikSearch = { isSearchVisible = !isSearchVisible },
        )
    }) { paddings ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 24.dp),
            modifier = Modifier.padding(paddings),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),

            ) {
            itemsIndexed(data.items) { i, item->
                GridItem(item) {
                    navController.toDetails(item.id)
                }
                if (i >= data.items.size - 1 && !data.endReached && !data.isLoading) {
                    viewModel.loadNextItems()
                }
            }
            if (data.isLoading) item(span = { GridItemSpan(maxLineSpan) }) { LoadingItem() }
        }
    }
}

@Composable
fun TopBar(
    value: String,
    isSearchVisible: Boolean = false,
    onValueChange: (String) -> Unit,
    onClikSearch: () -> Unit,
) {

    val focusRequester = remember { FocusRequester() }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(MaterialTheme.colors.primary),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        IconButton(onClick = { }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back icon",
                tint = MaterialTheme.colors.secondary
            )
        }



        if (isSearchVisible) {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                textStyle = MaterialTheme.typography.subtitle1,
                cursorBrush = SolidColor(
                    Color.White
                ),
                modifier = Modifier
                    .focusRequester(focusRequester)
            )

            LaunchedEffect(Unit) {
                focusRequester.requestFocus()
            }
        } else Text(
            text = stringResource(R.string.list),
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.width(200.dp)
        )

        IconButton(onClick = {
            onClikSearch()
        }) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Back icon",
                tint = MaterialTheme.colors.secondary
            )
        }


    }
}


@Composable
fun GridItem(item: MyItem, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .shadow(4.dp, MaterialTheme.shapes.medium)
            .clip(MaterialTheme.shapes.medium)
            .height(297.dp)
            .background(MaterialTheme.colors.secondary)
            .clickable { onClick() }
    ) {
        AsyncImage(
            model = Constance.BASE_URL + item.image,
            contentDescription = "image",
            modifier = Modifier
                .height(78.dp)
                .padding(14.dp)
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = item.name,
            style = MaterialTheme.typography.h1,
            modifier = Modifier.padding(horizontal = 12.dp)
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(text = item.description, modifier = Modifier.padding(horizontal = 12.dp))

    }
}

@Composable
fun LoadingItem() {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}