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
fun Bai2Screen() {
    val concepts = listOf(
        KotlinConcept(
            "Lớp trừu tượng (Abstract Classes)",
            "Định nghĩa lớp và thuộc tính trừu tượng để các lớp con kế thừa",
            "abstract class Dwelling() {\n    abstract val buildingMaterial: String\n    abstract fun floorArea(): Double\n}"
        ),
        KotlinConcept(
            "Kế thừa & Ghi đè (Inheritance)",
            "Sử dụng 'open' để cho phép kế thừa và 'override' để ghi đè phương thức",
            "open class RoundHut(residents: Int) { ... }\n\nclass SquareCabin : Dwelling() {\n    override fun floorArea(): Double {\n        return super.floorArea() * floors\n    }\n}"
        ),
        KotlinConcept(
            "Danh sách (Lists)",
            "Làm việc với danh sách chỉ đọc (listOf) và danh sách có thể thay đổi (mutableListOf)",
            "val numbers = listOf(1, 2, 3)\nval size = numbers.size\nval first = numbers[0]\n\nval entrees = mutableListOf<String>()\nentrees.add(\"spaghetti\")\nentrees[0] = \"lasagna\"\nentrees.remove(\"lasagna\")"
        ),
        KotlinConcept(
            "Vòng lặp (Loops)",
            "Sử dụng for và while để lặp qua danh sách",
            "for (element in myList) {\n    println(element)\n}\n\nvar index = 0\nwhile (index < myList.size) {\n    println(myList[index])\n    index++\n}"
        ),
        KotlinConcept(
            "Chuỗi & Biểu thức (Strings)",
            "Sử dụng biểu thức trong mẫu chuỗi",
            "val name = \"Android\"\nprintln(name.length)\n\nval number = 10\nval groups = 5\nprintln(\"\u0024{number * groups} people\")"
        ),
        KotlinConcept(
            "Hàm phạm vi (with)",
            "Sử dụng 'with' để truy cập trực tiếp các thuộc tính của đối tượng",
            "with(squareCabin) {\n    println(\"Capacity: \u0024{capacity}\")\n    println(\"Material: \u0024{buildingMaterial}\")\n}"
        ),
        KotlinConcept(
            "Khác (Misc)",
            "Toán tử gán, import và tham số vararg",
            "a += b // a = a + b\n\nimport kotlin.math.PI\nval area = PI * radius * radius\n\nfun addToppings(vararg toppings: String) {\n    // ...\n}"
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
                text = "Advanced Kotlin Concepts",
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
