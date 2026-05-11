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
            "fun main() {\n    println(\"Chào mừng đến với Kotlin!\")\n}"
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
            "// Thông tin cá nhân (không đổi)\nval ten = \"Nguyen Van A\"\nval tuoi = 20\nval lop = \"CTK41\"\n\n// Điểm số (có thể thay đổi)\nvar diemToan = 8.5\nvar diemLy = 7.0\nvar diemHoa = 9.0\n\n// Cập nhật điểm mới\ndiemToan = 9.0  // ✅ Được phép\n// ten = \"Tran Thi B\"  // ❌ Lỗi vì val không thể thay đổi"
        ),
        KotlinConcept(
            "Mẫu chuỗi (String Templates)",
            "Sử dụng \$ để chèn giá trị biến vào chuỗi",
            "// Thông tin sinh viên\nval hoTen = \"Le Thi Mai\"\nval mssv = \"20211234\"\nval diemTB = 8.2\n\n// In thông báo\nprintln(\"Sinh vien: \$hoTen\")\nprintln(\"Ma so: \$mssv\")\nprintln(\"Diem trung binh: \$diemTB\")\n\n// Ghép chuỗi phức tạp\nprintln(\"Sinh vien \$hoTen (\$mssv) co diem TB la \$diemTB\")\n\n// Tính toán trong chuỗi\nval diem1 = 8.0\nval diem2 = 7.5\nprintln(\"Diem trung binh: \${(diem1 + diem2) / 2}\")"
        ),
        KotlinConcept(
            "Loại dữ liệu (Data Types)",
            "Các kiểu dữ liệu cơ bản trong Kotlin",
            "Int      // Số nguyên\nString   // Chuỗi văn bản\nIntRange // Một khoảng số (ví dụ: 1..6)\nBoolean  // Đúng hoặc Sai (true/false)"
        ),
        KotlinConcept(
            "Hàm (Functions)",
            "Định nghĩa và gọi hàm",
            "// Hàm tính điểm trung bình\nfun tinhDiemTB(toan: Double, ly: Double, hoa: Double): Double {\n    return (toan + ly + hoa) / 3\n}\n\n// Hàm xếp loại\nfun xepLoai(diem: Double): String {\n    return when {\n        diem >= 8.5 -> \"Gioi\"\n        diem >= 7.0 -> \"Kha\"\n        diem >= 5.5 -> \"Trung binh\"\n        else -> \"Yeu\"\n    }\n}\n\n// Hàm in bảng điểm\nfun inBangDiem(hoTen: String, diemTB: Double, xepLoai: String) {\n    println(\"--- BANG DIEM ---\")\n    println(\"Ho ten: \$hoTen\")\n    println(\"Diem TB: \$diemTB\")\n    println(\"Xep loai: \$xepLoai\")\n    println(\"-----------------\")\n}\n\n// Sử dụng hàm\nval dtb = tinhDiemTB(8.0, 7.5, 9.0)\nval loai = xepLoai(dtb)\ninBangDiem(\"Nguyen Van A\", dtb, loai)"
        ),
        KotlinConcept(
            "Toán tử & Logic",
            "Các phép toán và so sánh cơ bản",
            "// Toán tử: +, -, *, /\n// So sánh: >, <, ==, >=, <=, !=\n\n// Tạo số ngẫu nhiên\nval randomNumber = (1..6).random()"
        ),
        KotlinConcept(
            "Kiểm soát luồng (Flow Control)",
            "Sử dụng if/else và when cho điều kiện thực tế",
            "// Kiểm tra tuổi để xác định đối tượng\nval tuoi = 17\n\nif (tuoi >= 18) {\n    println(\"Du tuoi bau cu\")\n} else {\n    println(\"Chua du tuoi bau cu\")\n}\n\n// Xếp loại học lực\nval diem = 7.8\nval xepLoai = when {\n    diem >= 9.0 -> \"Xuat sac\"\n    diem >= 8.0 -> \"Gioi\"\n    diem >= 7.0 -> \"Kha\"\n    diem >= 5.0 -> \"Trung binh\"\n    else -> \"Yeu\"\n}\nprintln(\"Xep loai: \$xepLoai\")\n\n// Kiểm tra ngày trong tuần\nval ngay = 3\nval tenNgay = when (ngay) {\n    1 -> \"Thu Hai\"\n    2 -> \"Thu Ba\"\n    3 -> \"Thu Tu\"\n    4 -> \"Thu Nam\"\n    5 -> \"Thu Sau\"\n    6, 7 -> \"Cuoi tuan\"\n    else -> \"Khong hop le\"\n}\nprintln(\"Hom nay la: \$tenNgay\")"
        ),
        KotlinConcept(
            "Lớp (Classes)",
            "Định nghĩa lớp và đối tượng",
            "// Lớp SinhVien\nclass SinhVien(\n    val hoTen: String,\n    val mssv: String,\n    var lop: String\n) {\n    private val diem = mutableListOf<Double>()\n    \n    fun themDiem(diemMoi: Double) {\n        diem.add(diemMoi)\n    }\n    \n    fun tinhDiemTB(): Double {\n        return if (diem.isNotEmpty()) diem.average() else 0.0\n    }\n    \n    fun inThongTin() {\n        println(\"Sinh vien: \$hoTen\")\n        println(\"MSSV: \$mssv\")\n        println(\"Lop: \$lop\")\n        println(\"Diem TB: \${tinhDiemTB()}\")\n    }\n}\n\n// Tạo đối tượng và sử dụng\nval sv1 = SinhVien(\"Tran Van B\", \"20215678\", \"CTK42\")\nsv1.themDiem(8.0)\nsv1.themDiem(7.5)\nsv1.themDiem(9.0)\nsv1.inThongTin()"
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
                text = "Kotlin Fundamentals - Lab 1",
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
