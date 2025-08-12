package com.example.a30daysapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
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

@Composable
fun TipsCard(tip: Tip, modifier: Modifier = Modifier) {
    Card(modifier = modifier.padding(8.dp), elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)) {
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
                style = MaterialTheme.typography.headlineSmall // aici trebuie sa fie h6
            )
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
    LazyColumn {
        items(tipsList) { tip ->
            TipsCard(tip)
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