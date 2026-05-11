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
            "Định nghĩa lớp cơ sở không thể khởi tạo trực tiếp",
            "// Lớp trừu tượng Person\nabstract class Person(\n    val hoTen: String,\n    val namSinh: Int\n) {\n    abstract fun tinhTuoi(): Int\n    abstract fun gioiThieu(): String\n}\n\n// Lớp SinhVien kế thừa từ Person\nclass SinhVien(\n    hoTen: String,\n    namSinh: Int,\n    val mssv: String,\n    val lop: String\n) : Person(hoTen, namSinh) {\n    override fun tinhTuoi(): Int {\n        return 2024 - namSinh\n    }\n    \n    override fun gioiThieu(): String {\n        return \"Toi la sinh viên \$hoTen, lop \$lop\"\n    }\n}\n\n// Lớp GiangVien kế thừa từ Person\nclass GiangVien(\n    hoTen: String,\n    namSinh: Int,\n    val maGV: String,\n    val khoa: String\n) : Person(hoTen, namSinh) {\n    override fun tinhTuoi(): Int {\n        return 2024 - namSinh\n    }\n    \n    override fun gioiThieu(): String {\n        return \"Toi la giang vien \$hoTen, khoa \$khoa\"\n    }\n}\n\n// Sử dụng\nval sv = SinhVien(\"Nguyen Van A\", 2002, \"20211234\", \"CTK41\")\nval gv = GiangVien(\"Tran Thi B\", 1985, \"GV001\", \"CNTT\")\nprintln(sv.gioiThieu())\nprintln(gv.gioiThieu())"
        ),
        KotlinConcept(
            "Kế thừa (Inheritance)",
            "Lớp con kế thừa thuộc tính và phương thức từ lớp cha",
            "// Lớp cơ sở NhanVien\nopen class NhanVien(\n    val hoTen: String,\n    val luongCoBan: Double\n) {\n    open fun tinhLuong(): Double {\n        return luongCoBan\n    }\n    \n    open fun inThongTin() {\n        println(\"Nhan vien: \$hoTen, Luong: \${tinhLuong()}\")\n    }\n}\n\n// Lớp LapTrinhVien kế thừa từ NhanVien\nclass LapTrinhVien(\n    hoTen: String,\n    luongCoBan: Double,\n    val ngonNgu: String\n) : NhanVien(hoTen, luongCoBan) {\n    override fun tinhLuong(): Double {\n        return super.tinhLuong() * 1.2 // Phụ cấp lập trình\n    }\n    \n    fun lapTrinh() {\n        println(\"\$hoTen dang lap trinh bang \$ngonNgu\")\n    }\n}\n\n// Lớp QuanLy kế thừa từ NhanVien\nclass QuanLy(\n    hoTen: String,\n    luongCoBan: Double,\n    val soNhanVien: Int\n) : NhanVien(hoTen, luongCoBan) {\n    override fun tinhLuong(): Double {\n        return super.tinhLuong() + soNhanVien * 500\n    }\n}\n\n// Sử dụng\nval lt = LapTrinhVien(\"Le Van C\", 8000000.0, \"Kotlin\")\nval ql = QuanLy(\"Pham Thi D\", 12000000.0, 5)\nlt.inThongTin()\nql.inThongTin()\nlt.lapTrinh()"
        ),
        KotlinConcept(
            "Danh sách (Lists)",
            "Tạo và thao tác với danh sách các đối tượng",
            "// Danh sách sinh viên\nval danhSachSV = listOf(\n    \"Nguyen Van A\",\n    \"Tran Thi B\", \n    \"Le Van C\",\n    \"Pham Thi D\"\n)\n\n// In danh sách\nprintln(\"Danh sach sinh vien:\")\ndanhSachSV.forEachIndexed { index, ten ->\n    println(\"\${index + 1}. \$ten\")\n}\n\n// Danh sách điểm\nval diems = listOf(8.5, 7.0, 9.0, 6.5, 8.0)\nprintln(\"So luong diem: \${diems.size}\")\nprintln(\"Diem cao nhat: \${diems.max()}\")\nprintln(\"Diem thap nhat: \${diems.min()}\")\nprintln(\"Diem trung binh: \${diems.average()}\")\n\n// Danh sách có thể thay đổi\nval diemMutable = mutableListOf(7.0, 8.0)\ndiemMutable.add(9.0)\ndiemMutable.remove(7.0)\nprintln(\"Danh sach diem sau khi sua: \$diemMutable\")"
        ),
        KotlinConcept(
            "Vòng lặp (Loops)",
            "Lặp qua danh sách và thực hiện các thao tác",
            "// Danh sách môn học\nval monHocs = listOf(\"Toan\", \"Ly\", \"Hoa\", \"Anh\", \"Tin\")\n\n// Vòng lặp for\nprintln(\"Danh sach mon hoc:\")\nfor (i in monHocs.indices) {\n    println(\"Mon \${i + 1}: \${monHocs[i]}\")\n}\n\n// Vòng lặp for-each\nprintln(\"\\nDanh sach mon hoc (for-each):\")\nmonHocs.forEach { mon ->\n    println(\"- \$mon\")\n}\n\n// Vòng lặp while\nvar dem = 0\nprintln(\"\\nDem nguoc:\")\nwhile (dem < 5) {\n    println(\"\${5 - dem}\")\n    dem++\n}\n\n// Vòng lặp do-while\nvar so = 1\nprintln(\"\\nNhan doi den qua 10:\")\ndo {\n    println(\"\$so\")\n    so *= 2\n} while (so <= 10)"
        ),
        KotlinConcept(
            "Chuỗi & Biểu thức (Strings)",
            "Sử dụng biểu thức trong mẫu chuỗi",
            "// Thông tin sinh viên\nval hoTen = \"Le Thi Mai\"\nval mssv = \"20211234\"\nval lop = \"CTK41\"\nprintln(\"Ten: \$hoTen\")\nprintln(\"Do dai ten: \${hoTen.length}\")\nprintln(\"MSSV: \$mssv\")\nprintln(\"Lop: \$lop\")\n\n// Tính toán trong chuỗi\nval diemToan = 8.5\nval diemLy = 7.0\nval diemHoa = 9.0\nval soMon = 3\nval diemTB = (diemToan + diemLy + diemHoa) / soMon\nprintln(\"Diem trung binh \$soMon mon: \$diemTB\")\n\n// Ghép chuỗi phức tạp\nprintln(\"Sinh vien \$hoTen (\$mssv) lop \$lop co DTB: \$diemTB\")\nprintln(\"Tong diem: \${diemToan + diemLy + diemHoa}\")"
        ),
        KotlinConcept(
            "Hàm phạm vi (with)",
            "Sử dụng 'with' để truy cập trực tiếp các thuộc tính của đối tượng",
            "// Lớp SinhVien\ndata class SinhVien(\n    val hoTen: String,\n    val mssv: String,\n    val lop: String,\n    val diems: List<Double>\n) {\n    fun tinhDTB(): Double = diems.average()\n    fun xepLoai(): String {\n        val dtb = tinhDTB()\n        return when {\n            dtb >= 8.5 -> \"Gioi\"\n            dtb >= 7.0 -> \"Kha\"\n            dtb >= 5.5 -> \"Trung binh\"\n            else -> \"Yeu\"\n        }\n    }\n}\n\n// Sử dụng with để truy cập thuộc tính\nval sv = SinhVien(\"Nguyen Van A\", \"20215678\", \"CTK42\", listOf(8.0, 7.5, 9.0))\n\nwith(sv) {\n    println(\"--- THONG TIN SINH VIEN ---\")\n    println(\"Ho ten: \$hoTen\")\n    println(\"MSSV: \$mssv\")\n    println(\"Lop: \$lop\")\n    println(\"Diem: \$diems\")\n    println(\"Diem TB: \${tinhDTB()}\")\n    println(\"Xep loai: \${xepLoai()}\")\n    println(\"----------------------------\")\n}"
        ),
        KotlinConcept(
            "Khác (Misc)",
            "Toán tử gán, import và tham số vararg",
            "// Toán tử gán ghép\nvar diem = 7.0\ndiem += 1.5  // diem = diem + 1.5\ndiem *= 2    // diem = diem * 2\nprintln(\"Diem sau khi cap nhat: \$diem\")\n\n// Sử dụng hàm toán học (cần import kotlin.math.max, kotlin.math.min)\nval diemCaoNhat = max(8.0, 7.5)\nval diemThapNhat = min(6.5, 9.0)\nprintln(\"Diem cao nhat: \$diemCaoNhat, thap nhat: \$diemThapNhat\")\n\n// Tham số vararg\nfun inMonHoc(vararg monHoc: String) {\n    println(\"Danh sach mon hoc:\")\n    monHoc.forEachIndexed { index, mon ->\n        println(\"\${index + 1}. \$mon\")\n    }\n}\n\ninMonHoc(\"Toan\", \"Ly\", \"Hoa\", \"Anh Van\")\ninMonHoc(\"Lap trinh\", \"Co so du lieu\", \"Mang may tinh\")"
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
                text = "Kotlin Advanced Concepts - Lab 1",
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
