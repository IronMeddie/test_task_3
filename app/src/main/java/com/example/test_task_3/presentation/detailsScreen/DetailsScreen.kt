package com.example.test_task_3.presentation.detailsScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.test_task_3.R
import com.example.test_task_3.data.remote.DataResource
import com.example.test_task_3.domain.constance.Constance

@Composable
fun DetailsScreen(navController: NavController,viewModel: DetailsViewModel = hiltViewModel()) {
    val item = viewModel.item.collectAsState().value
    Scaffold(topBar = {
        TopBarDetails(){
            navController.navigateUp()
        }
    }) {
        when (item) {
            is DataResource.Success -> {
                Column(
                    modifier = Modifier
                        .shadow(4.dp, MaterialTheme.shapes.medium)
                        .clip(MaterialTheme.shapes.medium)
                        .background(MaterialTheme.colors.secondary)
                        .fillMaxSize()
                        .padding(it)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                            .padding(20.dp)
                    ) {
                        AsyncImage(
                            model = Constance.BASE_URL + item.data.categories.icon,
                            contentDescription = "star",
                            modifier = Modifier
                                .padding(start = 10.dp, top = 20.dp)
                                .size(32.dp)
                        )
                        AsyncImage(
                            model = Constance.BASE_URL + item.data.image,
                            contentDescription = "image",
                            modifier = Modifier
                                .padding(14.dp)
                                .clip(MaterialTheme.shapes.medium)

                        )
                        Image(
                            painter = painterResource(id = R.drawable.favorite),
                            contentDescription = "favorite",
                            modifier = Modifier
                                .padding(end = 10.dp, top = 20.dp)
                                .size(32.dp)
                        )

                    }

                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = item.data.name,
                        style = MaterialTheme.typography.h1,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = item.data.description,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier

                            .fillMaxWidth()
                            .height(40.dp)
                            .padding(horizontal = 16.dp)
                            .clip(MaterialTheme.shapes.medium)
                            .border(
                                (0.5).dp,
                                MaterialTheme.colors.primaryVariant,
                                MaterialTheme.shapes.medium
                            ),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "location icon",
                            tint = MaterialTheme.colors.primary
                        )
                        Text(
                            text = stringResource(R.string.where_buy),
                            style = MaterialTheme.typography.h1
                        )
                    }

                }
            }
            is DataResource.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            is DataResource.Failure -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = item.errorBody.toString())
                }

            }
        }


    }
}

@Composable
private fun TopBarDetails(onBack: () -> Unit){
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(40.dp)
        .background(MaterialTheme.colors.primary), contentAlignment = Alignment.CenterStart) {
        IconButton(onClick = onBack){
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back", tint = Color.White)
        }

    }
}
