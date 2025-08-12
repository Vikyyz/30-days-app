package com.example.a30daysapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a30daysapp.data.DataSource
import com.example.a30daysapp.model.Tip
import com.example.a30daysapp.ui.theme._30DaysAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _30DaysAppTheme {
                Scaffold (
                    content =  {
                        TipsList(
                            tipsList = DataSource().loadTips(),
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
fun TipsCard(tip: Tip, modifier: Modifier = Modifier) {
    var completed by remember { mutableStateOf(false) }
    Card(modifier = modifier.padding(8.dp), elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)) {
        val dismissState = rememberSwipeToDismissBoxState(
            confirmValueChange = { value ->
                if (value == SwipeToDismissBoxValue.EndToStart) {
                    completed = true
                    false
                } else {
                    true
                }
            }
        )
        SwipeToDismissBox (
            state = dismissState,
            enableDismissFromEndToStart = true,
            backgroundContent = {
                Box {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete tip"
                    )
                }
            }
        ) {
            if (completed) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(194.dp)
                        .padding(16.dp),
                    contentAlignment = androidx.compose.ui.Alignment.Center
                ) {
                    Text(text = "You have completed this day's task")
                }
            } else {
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
}

@Preview
@Composable
private fun TipsCardPreview () {
    TipsCard(Tip(R.string.Tips1, R.drawable.image1))
}

@Composable
private fun TipsList(tipsList: List<Tip>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(220.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        item (span = { GridItemSpan(this.maxLineSpan) }) {
            ArtBlockDetailsHeaderItem()
        }
        items(tipsList) { tip ->
            TipsCard(tip)
        }
    }
}
@Composable
fun ArtBlockDetailsHeaderItem(modifier: Modifier = Modifier) {
    Card(modifier = Modifier.padding(8.dp)) {
        Column {
            Text (
                text = "30 Days to combat Art Block",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = "Art Block",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    _30DaysAppTheme {
        Greeting("Android")
    }
}