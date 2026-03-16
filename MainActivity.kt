package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// 1. Khai báo data class ĐẦY ĐỦ các thuộc tính
data class Dog(
    val name: String,
    val age: Int,
    val image: Int
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Sử dụng MaterialTheme để màu sắc và font chữ trông chuyên nghiệp hơn
            MaterialTheme {
                WoofApp()
            }
        }
    }
}

@Composable
fun WoofApp() {
    // 2. Danh sách dữ liệu (Đảm bảo các file dog1, dog2... đã có trong thư mục drawable)
    val dogs = listOf(
        Dog("Koda", 2, R.drawable.dog1),
        Dog("Lola", 16, R.drawable.dog2),
        Dog("Frankie", 2, R.drawable.dog3),
        Dog("Nox", 8, R.drawable.dog4),
        Dog("Faye", 8, R.drawable.dog5),
        Dog("Bella", 14, R.drawable.dog6),
        Dog("Moana", 2, R.drawable.dog7),
        Dog("Tzeitel", 7, R.drawable.dog8)
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF0F0F0) // Màu nền xám nhạt
    ) {
        Column {
            Text(
                text = "🐾 Woof",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(16.dp)
            )

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                items(dogs) { dog ->
                    DogItem(dog = dog)
                }
            }
        }
    }
}

@Composable
fun DogItem(dog: Dog) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable { expanded = !expanded },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(dog.image),
                    contentDescription = null,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(50)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = dog.name,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = "${dog.age} years old",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                }

                Icon(
                    imageVector = Icons.Default.ExpandMore,
                    contentDescription = "Expand",
                    // Xoay icon nếu đang mở
                    modifier = Modifier.padding(8.dp)
                )
            }

            // Nội dung hiển thị thêm khi nhấn vào card
            if (expanded) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "About:",
                    style = MaterialTheme.typography.labelLarge
                )
                Text(
                    text = "This is a friendly dog named ${dog.name}. They love playing and are very loyal to their family!",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}