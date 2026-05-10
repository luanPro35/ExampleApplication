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
fun Bai1Screen() {
    val concepts = listOf(
        KotlinConcept(
            "Chương trình Kotlin",
            "Là chương trình main() nhỏ nhất để in văn bản",
            "fun main() {\n    println(\"Hello, world!\")\n}"
        ),
        KotlinConcept(
            "In một dòng văn bản",
            "Sử dụng println() để in nội dung ra màn hình",
            "println(\"This is the text to print!\")"
        ),
        KotlinConcept(
            "Nhận xét (Comments)",
            "Sử dụng // cho nhận xét trên cùng dòng",
            "// This is a comment line.\n// This is another comment."
        ),
        KotlinConcept(
            "Biến (Variables)",
            "val cho hằng số (không đổi), var cho biến (có thể đổi)",
            "// Assign once, cannot change.\nval age = \"5\"\nval name = \"Rover\"\n\n// Assign and change as needed.\nvar roll = 6\nvar rolledValue: Int = 4"
        ),
        KotlinConcept(
            "Mẫu chuỗi (String Templates)",
            "Sử dụng \u0024 để chèn giá trị biến vào chuỗi",
            "println(\"You are already \u0024{age}!\")\nprintln(\"You are already \u0024{age} days old, \u0024{name}!\")"
        ),
        KotlinConcept(
            "Loại dữ liệu (Data Types)",
            "Các kiểu dữ liệu cơ bản trong Kotlin",
            "Int      // Số nguyên\nString   // Chuỗi văn bản\nIntRange // Một khoảng số (ví dụ: 1..6)\nBoolean  // Đúng hoặc Sai (true/false)"
        ),
        KotlinConcept(
            "Hàm (Functions)",
            "Định nghĩa và gọi hàm",
            "// Hàm không có đối số\nfun printHello() {\n    println(\"Hello Kotlin\")\n}\n\n// Hàm có đối số\nfun printBorder(border: String, count: Int) {\n    repeat(count) { print(border) }\n    println()\n}"
        ),
        KotlinConcept(
            "Toán tử & Logic",
            "Các phép toán và so sánh cơ bản",
            "// Toán tử: +, -, *, /\n// So sánh: >, <, ==, >=, <=, !=\n\n// Tạo số ngẫu nhiên\nval randomNumber = (1..6).random()"
        ),
        KotlinConcept(
            "Kiểm soát luồng (Flow Control)",
            "Sử dụng if/else và when",
            "if (num > 4) {\n    println(\"Greater than 4\")\n} else {\n    println(\"4 or less\")\n}\n\nwhen (rollResult) {\n    6 -> println(\"You won!\")\n    else -> println(\"Try again!\")\n}"
        ),
        KotlinConcept(
            "Lớp (Classes)",
            "Định nghĩa lớp đơn giản",
            "class Dice(val numSides: Int) {\n    fun roll(): Int {\n        return (1..numSides).random()\n    }\n}\n\nval myFirstDice = Dice(6)"
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
                text = "Kotlin Programming Basics",
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
