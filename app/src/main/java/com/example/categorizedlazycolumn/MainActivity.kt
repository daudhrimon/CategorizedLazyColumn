package com.example.categorizedlazycolumn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.categorizedlazycolumn.data.Category
import com.example.categorizedlazycolumn.data.nameList
import com.example.categorizedlazycolumn.ui.theme.CategorizedLazyColumnTheme

class MainActivity : ComponentActivity() {
    private val categories: List<Category>? by lazy {
        nameList.map {
            Category(
                category = it.key.toString(),
                nameList = it.value
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CategorizedLazyColumnTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CategorizedLazyColumn(categories = categories)
                }
            }
        }
    }
}

@Composable
private fun CategoryHeader(
    text: String, modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Gray)
            .padding(16.dp)
    )
}

@Composable
private fun CategoryItem(
    text: String, modifier: Modifier = Modifier
) {
    Button(
        onClick = {

        },
        modifier = modifier.padding(horizontal = 8.dp, vertical = 2.dp)
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = modifier
                .fillMaxWidth()
                .padding(4.dp)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun CategorizedLazyColumn(
    categories: List<Category>?, modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        categories?.forEach { categoryItem->
            stickyHeader {
                CategoryHeader(text = categoryItem.category)
            }
            items(categoryItem.nameList) {
                CategoryItem(text = it)
            }
        }
    }
}