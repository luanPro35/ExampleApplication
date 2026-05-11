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
            "Set (Tập hợp)",
            "Tập hợp các phần tử không trùng lặp",
            "// Set các môn học đã đăng ký\nval monHocDaDangKy = setOf(\n    \"Toan cao cap\",\n    \"Lap trinh Kotlin\",\n    \"CSDL quan hệ\",\n    \"Lap trinh Kotlin\"  // Trùng lặp sẽ bị bỏ qua\n)\n\nprintln(\"So luong mon hoc: \${monHocDaDangKy.size}\")\nprintln(\"Danh sach mon hoc: \$monHocDaDangKy\")\n\n// Set có thể thay đổi\nval monHocMutable = mutableSetOf<String>()\nmonHocMutable.add(\"Toan\")\nmonHocMutable.add(\"Ly\")\nmonHocMutable.add(\"Hoa\")\nmonHocMutable.add(\"Toan\")  // Không thêm vì đã tồn tại\n\nprintln(\"Set mutable: \$monHocMutable\")\n\n// Kiểm tra tồn tại\nval coToan = \"Toan\" in monHocMutable\nprintln(\"Co mon Toan khong: \$coToan\")\n\n// Set các sinh viên trong lớp\nval sinhVienLop = setOf(\n    \"SV001 - Nguyen Van A\",\n    \"SV002 - Tran Thi B\",\n    \"SV003 - Le Van C\",\n    \"SV004 - Pham Thi D\"\n)\n\nprintln(\"\\nSinh vien trong lop:\")\nsinhVienLop.forEach { sv ->\n    println(\"- \$sv\")\n}"
        ),
        KotlinConcept(
            "Map (Ánh xạ)",
            "Lưu trữ cặp key-value",
            "// Map điểm sinh viên\nval diemSinhVien = mapOf(\n    \"SV001\" to 8.5,\n    \"SV002\" to 7.0,\n    \"SV003\" to 9.0,\n    \"SV004\" to 6.5\n)\n\n// Lấy điểm theo MSSV\nval diemSV001 = diemSinhVien[\"SV001\"]\nprintln(\"Diem SV001: \$diemSV001\")\n\n// Kiểm tra tồn tại\nval coSV005 = \"SV005\" in diemSinhVien\nprintln(\"Co SV005 khong: \$coSV005\")\n\n// Duyệt qua map\nprintln(\"\\nBang diem:\")\ndiemSinhVien.forEach { (mssv, diem) ->\n    println(\"\$mssv: \$diem\")\n}\n\n// Map có thể thay đổi\nval diemMutable = mutableMapOf(\n    \"SV001\" to 8.5,\n    \"SV002\" to 7.0\n)\n\ndiemMutable[\"SV003\"] = 9.0  // Thêm mới\ndiemMutable[\"SV001\"] = 9.2  // Cập nhật\n\nprintln(\"\\nDiem sau khi cap nhat:\")\ndiemMutable.forEach { (mssv, diem) ->\n    println(\"\$mssv: \$diem\")\n}\n\n// Map thông tin lớp học\nval thongTinLop = mapOf(\n    \"tenLop\" to \"CTK41\",\n    \"khoa\" to \"Cong nghe thong tin\",\n    \"soSinhVien\" to 35,\n    \"giangVien\" to \"Nguyen Van X\"\n)\n\nprintln(\"\\nThong tin lop:\")\nthongTinLop.forEach { (key, value) ->\n    println(\"\$key: \$value\")\n}"
        ),
        KotlinConcept(
            "Collection Operations",
            "Các thao tác trên tập hợp dữ liệu",
            "// Danh sách sinh viên và điểm\nval sinhVien = listOf(\n    \"Nguyen Van A\",\n    \"Tran Thi B\",\n    \"Le Van C\",\n    \"Pham Thi D\",\n    \"Ho Van E\"\n)\n\nval diems = listOf(8.5, 7.0, 9.0, 6.5, 8.0)\n\n// Filter - Lọc sinh viên có điểm >= 8.0\nval sinhVienGioi = diems.filter { it >= 8.0 }\nprintln(\"Sinh vien gioi (diem >= 8.0): \$sinhVienGioi\")\n\n// Map - Chuyển đổi điểm sang xếp loại\nval xepLoai = diems.map { diem ->\n    when {\n        diem >= 8.5 -> \"Gioi\"\n        diem >= 7.0 -> \"Kha\"\n        diem >= 5.5 -> \"Trung binh\"\n        else -> \"Yeu\"\n    }\n}\nprintln(\"Xep loai: \$xepLoai\")\n\n// Sum - Tổng điểm\nval tongDiem = diems.sum()\nprintln(\"Tong diem: \$tongDiem\")\n\n// Average - Điểm trung bình\nval diemTB = diems.average()\nprintln(\"Diem trung binh: \$diemTB\")\n\n// Count - Đếm sinh viên đạt\nval soDat = diems.count { it >= 5.0 }\nprintln(\"So sinh vien dat: \$soDat\")\n\n// Sorted - Sắp xếp điểm\nval diemSapXep = diems.sorted()\nprintln(\"Diem sap xep: \$diemSapXep\")\n\n// Group by - Nhóm theo xếp loại\nval nhomDiem = diems.groupBy { diem ->\n    when {\n        diem >= 8.5 -> \"Gioi\"\n        diem >= 7.0 -> \"Kha\"\n        diem >= 5.5 -> \"Trung binh\"\n        else -> \"Yeu\"\n    }\n}\nprintln(\"\\nNhom theo xep loai:\")\nnhomDiem.forEach { (loai, danhSach) ->\n    println(\"\$loai: \$danhSach\")\n}"
        ),
        KotlinConcept(
            "Scope Functions (let, apply)",
            "Hàm phạm vi để xử lý đối tượng",
            "// Sử dụng let để xử lý null safety\nvar tenSinhVien: String? = \"Nguyen Van A\"\n\ntenSinhVien?.let { ten ->\n    println(\"Xin chao sinh vien: \$ten\")\n    println(\"Do dai ten: \${ten.length}\")\n}\n\n// Sử dụng let để chuỗi xử lý\nval chuoiHoa = \"Hello World\".let {\n    it.uppercase()\n}.let {\n    it.replace(\" \", \"_\")\n}\nprintln(\"Chuoi sau khi xu ly: \$chuoiHoa\")\n\n// Sử dụng apply để thiết lập đối tượng\ndata class SinhVien(\n    var hoTen: String = \"\",\n    var mssv: String = \"\",\n    var lop: String = \"\",\n    var diems: MutableList<Double> = mutableListOf()\n)\n\nval sv = SinhVien().apply {\n    hoTen = \"Tran Thi B\"\n    mssv = \"20215678\"\n    lop = \"CTK42\"\n    diems.addAll(listOf(8.0, 7.5, 9.0))\n}\n\nprintln(\"Thong tin sinh vien:\")\nprintln(\"Ho ten: \${sv.hoTen}\")\nprintln(\"MSSV: \${sv.mssv}\")\nprintln(\"Lop: \${sv.lop}\")\nprintln(\"Diems: \${sv.diems}\")\n\n// Sử dụng apply cho Map\nval thongTin = mutableMapOf<String, Any>().apply {\n    put(\"ten\", \"Le Van C\")\n    put(\"tuoi\", 20)\n    put(\"lop\", \"CTK41\")\n    put(\"diemTB\", 8.2)\n}\n\nprintln(\"\\nThong tin tu Map:\")\nthongTin.forEach { (key, value) ->\n    println(\"\$key: \$value\")\n}"
        ),
        KotlinConcept(
            "Lambda & Companion Object",
            "Hàm ẩn danh và đối tượng tĩnh trong lớp",
            "// Lambda để tính điểm trung bình\nval tinhDTB: (List<Double>) -> Double = { diems ->\n    if (diems.isNotEmpty()) diems.average() else 0.0\n}\n\n// Lambda để xếp loại\nval xepLoai: (Double) -> String = { diem ->\n    when {\n        diem >= 8.5 -> \"Gioi\"\n        diem >= 7.0 -> \"Kha\"\n        diem >= 5.5 -> \"Trung binh\"\n        else -> \"Yeu\"\n    }\n}\n\n// Sử dụng lambda\nval diems = listOf(8.0, 7.5, 9.0)\nval dtb = tinhDTB(diems)\nval loai = xepLoai(dtb)\nprintln(\"Diem TB: \$dtb - Xep loai: \$loai\")\n\n// Companion object trong lớp SinhVien\nclass SinhVien(val hoTen: String, val diems: List<Double>) {\n    companion object {\n        const val TRUONG = \"Dai hoc ABC\"\n        const val KHOA = \"Cong nghe thong tin\"\n        val DIEM_CHUAN = 5.0\n        \n        fun kiemTraTotNghiep(dtb: Double): Boolean {\n            return dtb >= DIEM_CHUAN\n        }\n    }\n    \n    fun tinhDTB(): Double = diems.average()\n}\n\nprintln(\"Truong: \${SinhVien.TRUONG}\")\nprintln(\"Dieu kien tot nghiep: \${SinhVien.DIEM_CHUAN}\")\nval sv = SinhVien(\"Nguyen A\", listOf(8.0, 7.5, 9.0))\nprintln(\"Du tot nghiep: \${SinhVien.kiemTraTotNghiep(sv.tinhDTB())}\")"
        ),
        KotlinConcept(
            "Property Delegation & lateinit",
            "Cách khởi tạo biến linh hoạt",
            "// Sử dụng lazy để khởi tạo khi cần\nclass QuanLySinhVien {\n    val danhSachSinhVien: List<String> by lazy {\n        // Đọc từ database hoặc file chỉ khi cần\n        listOf(\"SV001\", \"SV002\", \"SV003\", \"SV004\", \"SV005\")\n    }\n    \n    val thongTinLop: Map<String, String> by lazy {\n        mapOf(\n            \"CTK41\" to \"Lop CNTT khoa 41\",\n            \"CTK42\" to \"Lop CNTT khoa 42\",\n            \"CTK43\" to \"Lop CNTT khoa 43\"\n        )\n    }\n}\n\n// Sử dụng lateinit cho biến được khởi tạo sau\nclass BangDiem {\n    private lateinit var tenSinhVien: String\n    private lateinit var maSinhVien: String\n    private var diems: List<Double>? = null\n    \n    fun thongTinSinhVien(ten: String, maSo: String) {\n        tenSinhVien = ten\n        maSinhVien = maSo\n        println(\"Da thiet lap thong tin cho: \$tenSinhVien\")\n    }\n    \n    fun hienThiThongTin() {\n        if (::tenSinhVien.isInitialized) {\n            println(\"Sinh vien: \$tenSinhVien - \$maSinhVien\")\n        } else {\n            println(\"Chua co thong tin sinh vien\")\n        }\n    }\n}\n\nval qlsv = QuanLySinhVien()\nprintln(\"So sinh vien: \${qlsv.danhSachSinhVien.size}\")\n\nval bangDiem = BangDiem()\nbangDiem.thongTinSinhVien(\"Nguyen Van A\", \"20215678\")\nbangDiem.hienThiThongTin()"
        ),
        KotlinConcept(
            "An toàn Null & Elvis Operator",
            "Xử lý giá trị null một cách an toàn",
            "// Xử lý điểm có thể null\nvar diemCuoiKy: Double? = null\n\n// Safe call operator (?)\nprintln(\"Diem: \${diemCuoiKy ?: \"Chua co\"}\")\n\n// Elvis operator (?:)\nval diemHienTai = diemCuoiKy ?: 0.0\nprintln(\"Diem hien tai: \$diemHienTai\")\n\n// Chuỗi safe call\nfun layThongTinSinhVien(mssv: String?): String? {\n    return mssv?.let { \"SV\$it\" }\n}\n\nval thongTin = layThongTinSinhVien(null)\nprintln(\"Thong tin: \${thongTin ?: \"Khong tim thay\"}\")\n\n// Sử dụng với collections\nval diems: List<Double>? = listOf(8.0, 7.5, 9.0)\nval diemTB = diems?.average() ?: 0.0\nprintln(\"Diem TB: \$diemTB\")\n\n// Let với null safety\ndiemCuoiKy?.let { diem ->\n    if (diem >= 5.0) {\n        println(\"Dau mon hoc voi diem: \$diem\")\n    } else {\n        println(\"Rot mon hoc voi diem: \$diem\")\n    }\n} ?: println(\"Chua co diem cuoi ky\")\n\n// Gán giá trị mới\ndiemCuoiKy = 8.5\nval xepLoai = when {\n    (diemCuoiKy ?: 0.0) >= 8.5 -> \"Gioi\"\n    (diemCuoiKy ?: 0.0) >= 7.0 -> \"Kha\"\n    (diemCuoiKy ?: 0.0) >= 5.5 -> \"Trung binh\"\n    else -> \"Yeu\"\n}\nprintln(\"Xep loai: \$xepLoai\")"
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
                text = "Kotlin Collections & Advanced Syntax - Lab 1",
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
