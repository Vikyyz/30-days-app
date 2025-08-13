package com.example.a30daysapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a30daysapp.model.Tip
import com.example.a30daysapp.model.TipsViewModel
import com.example.a30daysapp.ui.theme._30DaysAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _30DaysAppTheme {
                val tipsViewModel: TipsViewModel = viewModel()
                Scaffold (
                    content =  {
                        TipsList(
                            tipsList = tipsViewModel.tips,
                            onDelete = { tip ->
                                tipsViewModel.removeTip(tip)
                            },
                            modifier = Modifier.padding(it)
                        )
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TipsCard(
    tip: Tip,
    dismissState: SwipeToDismissBoxState,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.padding(8.dp), elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)) {
        Text (
            text = "Day ${tip.day + 1}",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(10.dp)
        )
        SwipeToDismissBox (
            state = dismissState,
            enableDismissFromEndToStart = true,
            enableDismissFromStartToEnd = false,
            backgroundContent = {
                Box {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete tip",
                    )
                }
            }
        ) {
            Column {
                Image(
                    painter = painterResource(tip.imageResourceId),
                    contentDescription = stringResource(tip.stringResourceId),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(194.dp),
                    contentScale = ContentScale.Crop
                )
                Text (
                    text = stringResource(tip.stringResourceId),
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }
    }
}

@Preview
@Composable
private fun TipsCardPreview () {
    TipsCard(Tip(R.string.Tips1, R.drawable.image1, 0), dismissState = rememberSwipeToDismissBoxState())
}

@Composable
private fun TipsList(tipsList: List<Tip>, onDelete: (Tip) -> Unit, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(220.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        item (span = { GridItemSpan(this.maxLineSpan) }) {
            ArtBlockDetailsHeaderItem()
        }

        items(tipsList, key = { it.stringResourceId }) { tip ->
            // Aceasta este într-un context @Composable, deci remember funcționează
            val dismissState = rememberSwipeToDismissBoxState(
                confirmValueChange = { value ->
                    if (value == SwipeToDismissBoxValue.EndToStart) {
                        onDelete(tip)
                        false
                    } else {
                        true
                    }
                }
            )

            TipsCard(
                tip = tip,
                dismissState = dismissState,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}
@Composable
fun ArtBlockDetailsHeaderItem(modifier: Modifier = Modifier) {
//    Header - Entire header
    Column(
        modifier = modifier.padding(5.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    )
//    Header - Image and title
    {
        Row(
            modifier = modifier
                .padding(5.dp)
                .padding(top = 15.dp),
            verticalAlignment = Alignment.Top
        ) {
            Image(
                painter = painterResource(id = R.drawable.header1),
                contentDescription = null,
                modifier = Modifier
                    .size(110.dp),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(start = 8.dp)) {
                Text(
                    text = "30 Days to combat Art Block",
                    style = MaterialTheme.typography.titleLarge,
                )
                LinearProgressIndicator(
                    progress = {
                        0.33f
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    color = MaterialTheme.colorScheme.primary,
                    trackColor = ProgressIndicatorDefaults.linearTrackColor,
                    strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
                )
            }
        }
//        Header - Description
        var expanded by remember { mutableStateOf(false) }
        Column {
            Text(
                text = stringResource(R.string.Description),
                style = MaterialTheme.typography.bodyMedium,
                maxLines = if (expanded) Int.MAX_VALUE else 3,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = if (expanded) "See less" else "See more",
                style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.primary),
                modifier = Modifier
                    .animateContentSize()
                    .clickable { expanded = !expanded }
            )
        }
    }
}
