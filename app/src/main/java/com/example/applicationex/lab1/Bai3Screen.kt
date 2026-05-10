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
fun Bai3Screen() {
    val concepts = listOf(
        KotlinConcept(
            "Tập hợp (Sets)",
            "Làm việc với Set (không chứa phần tử trùng lặp) và các phép toán tập hợp",
            "val numbers = listOf(0, 3, 8, 4, 0, 5, 5)\nval set = numbers.toSet() // {0, 3, 8, 4, 5}\n\nval set1 = setOf(1, 2, 3)\nval set2 = setOf(3, 4, 5)\nprintln(set1.intersect(set2)) // {3}\nprintln(set1.union(set2))     // {1, 2, 3, 4, 5}"
        ),
        KotlinConcept(
            "Bản đồ (Maps)",
            "Lưu trữ dữ liệu dưới dạng Key-Value",
            "val ages = mutableMapOf(\"Fred\" to 30, \"Ann\" to 23)\nages.put(\"Barbara\", 42)\nages[\"Joe\"] = 51\n\nages.forEach { print(\"\u0024{it.key} is \u0024{it.value}\") }"
        ),
        KotlinConcept(
            "Xử lý tập hợp (Collection Ops)",
            "Các hàm tiện ích như filter, map, sorted, take",
            "val filtered = map.filter { it.key.length < 4 }\nval names = words.filter { it.startsWith(\"b\") }\n    .shuffled()\n    .take(2)\n    .sorted()"
        ),
        KotlinConcept(
            "Hàm phạm vi (let, apply)",
            "Giúp code gọn gàng hơn khi làm việc với object",
            "arguments?.let {\n    letterId = it.getString(\"LETTER\")\n}\n\nbinding?.apply {\n    flavorFragment = this@FlavorFragment\n}"
        ),
        KotlinConcept(
            "Lambda & Companion Object",
            "Hàm ẩn danh và đối tượng tĩnh trong lớp",
            "val triple: (Int) -> Int = { a -> a * 3 }\nprintln(triple(5)) // 15\n\ncompanion object {\n    const val LETTER = \"letter\"\n}"
        ),
        KotlinConcept(
            "Property Delegation & lateinit",
            "Cách khởi tạo biến linh hoạt",
            "private val viewModel: GameViewModel by viewModels()\n\nprivate lateinit var currentWord: String\n// Cần gán giá trị trước khi sử dụng"
        ),
        KotlinConcept(
            "An toàn Null & Elvis Operator",
            "Xử lý giá trị null một cách an toàn",
            "val letterId = intent?.extras?.getString(\"letter\")\n\nvar quantity: Int? = null\nval result = quantity ?: 0 // Trả về 0 nếu null"
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
                text = "Kotlin Collections & Advanced Syntax",
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
