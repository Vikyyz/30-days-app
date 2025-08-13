package com.example.a30daysapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                    containerColor = MaterialTheme.colorScheme.background,
                    content =  {
                        Box(modifier = Modifier.fillMaxSize()) {
                            TipsList(
                                tipsList = tipsViewModel.tips,
                                onDelete = { tip -> tipsViewModel.removeTip(tip) },
                                modifier = Modifier.padding(it)
                            )
                        }
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

        SwipeToDismissBox (
            state = dismissState,
            enableDismissFromEndToStart = true,
            enableDismissFromStartToEnd = false,
            backgroundContent = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(end = 16.dp),
//                        .background(MaterialTheme.colorScheme.error.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.CenterEnd,

                )
                {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete tip",
                        tint = MaterialTheme.colorScheme.error,
                        modifier = Modifier.size(64.dp)
                    )
                }
            }
        ) {
            Card(
                modifier = modifier
                    .padding(8.dp)
                    .padding(top = 8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                shape = RectangleShape,
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text (
                    text = "Day ${tip.day + 1}",
                    color = MaterialTheme.colorScheme.background,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(10.dp),
                )
            Column (
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(tip.imageResourceId),
                    contentDescription = stringResource(tip.stringResourceId),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .width( 200.dp)
                        .padding(start = 10.dp)
                        .padding(end = 10.dp),
                    contentScale = ContentScale.Crop,
                )
                Text (
                    text = stringResource(tip.stringResourceId),
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .padding(bottom = 5.dp)
                        .padding(start = 10.dp)
                        .padding(end = 10.dp),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
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
            ArtBlockDetailsHeaderItem(
                totalTips = 10,
                remainingTips = tipsList.size,
            )
        }

        items(tipsList, key = { it.stringResourceId }) { tip ->
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
fun ArtBlockDetailsHeaderItem(
    totalTips: Int,
    remainingTips: Int,
    modifier: Modifier = Modifier
) {
    TopOvalBackground()

    val progress = 1f - (remainingTips.toFloat() / totalTips.toFloat())
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

            Column(
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy((-9).dp)
                ) {
                    Text(
                        text = "30 DAYS",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(start = 4.dp),
                        color = MaterialTheme.colorScheme.background
                        )

                    Text(
                        text = "ANTI ART BLOCK",
                        style = MaterialTheme.typography.titleLarge,
                        fontSize = 26.sp,
                        modifier = Modifier.padding(start = 4.dp),
                    )
                }
                Column(
                ) {
                    Text(
                        text = "Days remaining: $remainingTips",
                        style = MaterialTheme.typography.labelSmall
                    )
                    LinearProgressIndicator(
                        progress = { progress },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        color = MaterialTheme.colorScheme.onSurface,
                        trackColor = MaterialTheme.colorScheme.background,
                        strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
                    )
                }
            }
        }
//        Header - Description
        var expanded by remember { mutableStateOf(false) }
        Column {
            Text(
                text = stringResource(R.string.Description),
                maxLines = if (expanded) Int.MAX_VALUE else 3,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.labelSmall
            )

            Text(
                text = if (expanded) "See less" else "See more",
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier
                    .animateContentSize()
                    .clickable { expanded = !expanded }
            )
        }
    }
}

@Composable
fun TopOvalBackground(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val ovalWidth = size.width * 1.5f
            val ovalHeight = size.height
            val topLeft = androidx.compose.ui.geometry.Offset(
                x = (size.width - ovalWidth) / 2f,
                y = -ovalHeight * 0.3f
            )
            drawOval(
                color = androidx.compose.ui.graphics.Color(0xFFFFA726),
                topLeft = topLeft,
                size = androidx.compose.ui.geometry.Size(ovalWidth, ovalHeight)
            )
        }
    }
}


