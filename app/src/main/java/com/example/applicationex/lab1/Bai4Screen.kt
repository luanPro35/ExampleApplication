package com.example.applicationex.lab1

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Bai4Screen() {
    val concepts = listOf(
        KotlinConcept(
            "Coroutine (Hàm tạm ngưng)",
            "Sử dụng suspend để định nghĩa các hàm có thể tạm dừng mà không làm treo UI",
            "suspend fun getValue(): Double {\n    // Công việc tốn thời gian hoặc gọi suspend function khác\n    return 10.0\n}\n\nsuspend fun processValue() {\n    val value = getValue() // Gọi từ một suspend function khác\n}"
        ),
        KotlinConcept(
            "Chạy Coroutine (Launch & Cancel)",
            "Sử dụng GlobalScope để chạy và quản lý vòng đời Coroutine",
            "val job = GlobalScope.launch {\n    val output = getValue()\n}\n\n// Hủy tác vụ khi không cần thiết\njob.cancel()"
        ),
        KotlinConcept(
            "Chặn luồng (runBlocking & async)",
            "Đợi Coroutine hoàn thành hoặc chạy không đồng bộ",
            "runBlocking {\n    val output = getValue() // Chặn luồng hiện tại cho đến khi xong\n}\n\nval output = async { getValue() }\nprintln(\"Output is \u0024{output.await()}\")"
        ),
        KotlinConcept(
            "Đối tượng (Singleton Objects)",
            "Khai báo một object duy nhất (Singleton) cho toàn ứng dụng",
            "object DataProviderManager {\n    // Dữ liệu hoặc phương thức dùng chung\n}"
        ),
        KotlinConcept(
            "Xử lý ngoại lệ (Exceptions)",
            "Sử dụng try-catch để bắt các lỗi runtime",
            "try {\n    // Code có thể gây lỗi\n} catch (exception: Exception) {\n    // Xử lý lỗi tại đây\n}"
        ),
        KotlinConcept(
            "Lớp liệt kê (Enum Classes)",
            "Định nghĩa tập hợp các hằng số cố định",
            "enum class Direction {\n    NORTH, SOUTH, WEST, EAST\n}\n\nval direction = Direction.NORTH\n\nwhen (direction) {\n    Direction.NORTH -> println(\"Going North\")\n    // ...\n}"
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "Kotlin Coroutines & Design Patterns",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
        items(concepts) { concept ->
            ConceptCard(concept)
        }
    }
}
