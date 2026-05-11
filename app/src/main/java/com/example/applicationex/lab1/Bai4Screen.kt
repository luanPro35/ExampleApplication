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
import kotlinx.coroutines.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Bai4Screen() {
    val concepts = listOf(
        KotlinConcept(
            "Coroutine (Hàm tạm ngưng)",
            "Sử dụng suspend để định nghĩa các hàm có thể tạm dừng mà không làm treo UI",
            "// Tải dữ liệu sinh viên từ database/API\nsuspend fun taiDanhSachSinhVien(): List<String> {\n    delay(1000) // Giả lập thời gian tải\n    return listOf(\"SV001\", \"SV002\", \"SV003\", \"SV004\", \"SV005\")\n}\n\n// Tải điểm của sinh viên\nsuspend fun taiDiemSinhVien(mssv: String): Double {\n    delay(500) // Giả lập thời gian tải\n    return when (mssv) {\n        \"SV001\" -> 8.5\n        \"SV002\" -> 7.0\n        \"SV003\" -> 9.0\n        \"SV004\" -> 6.5\n        \"SV005\" -> 8.0\n        else -> 0.0\n    }\n}\n\n// Xử lý dữ liệu sinh viên\nsuspend fun xuLySinhVien(mssv: String) {\n    println(\"Dang tai thong tin sinh vien \$mssv...\")\n    val diem = taiDiemSinhVien(mssv)\n    val xepLoai = when {\n        diem >= 8.5 -> \"Gioi\"\n        diem >= 7.0 -> \"Kha\"\n        diem >= 5.5 -> \"Trung binh\"\n        else -> \"Yeu\"\n    }\n    println(\"Sinh vien \$mssv: Diem \$diem - Xep loai: \$xepLoai\")\n}"
        ),
        KotlinConcept(
            "Chạy Coroutine (Launch & Cancel)",
            "Sử dụng GlobalScope để chạy và quản lý vòng đời Coroutine",
            "// Tải danh sách sinh viên không đồng bộ\nval taiSinhVienJob = GlobalScope.launch {\n    try {\n        println(\"Bat dau tai danh sach sinh vien...\")\n        val danhSach = taiDanhSachSinhVien()\n        println(\"Da tai \${danhSach.size} sinh vien\")\n        \n        // Tải điểm của từng sinh viên\n        danhSach.forEach { mssv ->\n            val diem = taiDiemSinhVien(mssv)\n            println(\"\$mssv: \$diem\")\n        }\n        println(\"Hoan thanh tai du lieu!\")\n    } catch (e: Exception) {\n        println(\"Loi khi tai du lieu: \${e.message}\")\n    }\n}\n\n// Tải thông tin chi tiết một sinh viên\nval taiChiTietJob = GlobalScope.launch {\n    println(\"Dang tai thong tin chi tiet SV001...\")\n    xuLySinhVien(\"SV001\")\n}\n\n// Hủy tác vụ nếu cần (ví dụ: người dùng chuyển màn hình)\n// taiSinhVienJob.cancel() // Hủy tải danh sách\n// taiChiTietJob.cancel()  // Hủy tải chi tiết"
        ),
        KotlinConcept(
            "Chặn luồng (runBlocking & async)",
            "Đợi Coroutine hoàn thành hoặc chạy không đồng bộ",
            "// Sử dụng runBlocking để chờ hoàn thành (thường dùng trong testing)\nrunBlocking {\n    println(\"Bat du lieu dong bo...\")\n    val danhSach = taiDanhSachSinhVien()\n    println(\"Da tai xong danh sach: \${danhSach.size} sinh vien\")\n    \n    // Tải điểm của sinh viên đầu tiên\n    val diem = taiDiemSinhVien(danhSach.first())\n    println(\"Diem cua \${danhSach.first()}: \$diem\")\n}\n\n// Sử dụng async để tải dữ liệu song song\nsuspend fun taiDuLieuSongSong() {\n    println(\"Bat du lieu song song...\")\n    \n    val danhSachDeferred = async { taiDanhSachSinhVien() }\n    val diemSV001Deferred = async { taiDiemSinhVien(\"SV001\") }\n    val diemSV002Deferred = async { taiDiemSinhVien(\"SV002\") }\n    \n    // Đợi tất cả hoàn thành\n    val danhSach = danhSachDeferred.await()\n    val diem1 = diemSV001Deferred.await()\n    val diem2 = diemSV002Deferred.await()\n    \n    println(\"Da tai song song:\")\n    println(\"- Danh sach: \${danhSach.size} sinh vien\")\n    println(\"- SV001: \$diem1\")\n    println(\"- SV002: \$diem2\")\n}"
        ),
        KotlinConcept(
            "Đối tượng (Singleton Objects)",
            "Khai báo một object duy nhất (Singleton) cho toàn ứng dụng",
            "// Quản lý dữ liệu sinh viên toàn ứng dụng\nobject QuanLySinhVienGlobal {\n    private val danhSachSinhVien = mutableMapOf<String, SinhVien>()\n    private var nextId = 1\n    \n    // Thêm sinh viên mới\n    fun themSinhVien(hoTen: String, lop: String): String {\n        val mssv = \"SV\${String.format(\"%03d\", nextId++)}\"\n        val sv = SinhVien(hoTen, mssv, lop, mutableListOf())\n        danhSachSinhVien[mssv] = sv\n        return mssv\n    }\n    \n    // Lấy thông tin sinh viên\n    fun laySinhVien(mssv: String): SinhVien? {\n        return danhSachSinhVien[mssv]\n    }\n    \n    // Cập nhật điểm\n    fun capNhatDiem(mssv: String, diems: List<Double>): Boolean {\n        return danhSachSinhVien[mssv]?.let { sv ->\n            sv.diems.clear()\n            sv.diems.addAll(diems)\n            true\n        } ?: false\n    }\n    \n    // Lấy tất cả sinh viên\n    fun layTatCaSinhVien(): List<SinhVien> {\n        return danhSachSinhVien.values.toList()\n    }\n    \n    // Thống kê\n    fun thongKe(): Map<String, Int> {\n        val sv = layTatCaSinhVien()\n        return mapOf(\n            \"Tong so\" to sv.size,\n            \"Gioi\" to sv.count { it.tinhDTB() >= 8.5 },\n            \"Kha\" to sv.count { it.tinhDTB() in 7.0..8.49 },\n            \"Trung binh\" to sv.count { it.tinhDTB() in 5.5..6.99 }\n        )\n    }\n}\n\ndata class SinhVien(\n    val hoTen: String,\n    val mssv: String,\n    val lop: String,\n    val diems: MutableList<Double>\n) {\n    fun tinhDTB(): Double = if (diems.isNotEmpty()) diems.average() else 0.0\n}\n\n// Sử dụng singleton\nval mssv1 = QuanLySinhVienGlobal.themSinhVien(\"Nguyen Van A\", \"CTK41\")\nval mssv2 = QuanLySinhVienGlobal.themSinhVien(\"Tran Thi B\", \"CTK42\")\nQuanLySinhVienGlobal.capNhatDiem(mssv1, listOf(8.0, 7.5, 9.0))\nprintln(\"Thong ke: \${QuanLySinhVienGlobal.thongKe()}\")"
        ),
        KotlinConcept(
            "Xử lý ngoại lệ (Exceptions)",
            "Sử dụng try-catch để bắt các lỗi runtime",
            "// Xử lý lỗi khi tải dữ liệu sinh viên\nsuspend fun taiThongTinSinhVien(mssv: String) {\n    try {\n        println(\"Dang tai thong tin sinh vien \$mssv...\")\n        val diem = taiDiemSinhVien(mssv)\n        \n        if (diem == 0.0) {\n            throw IllegalArgumentException(\"Khong tim thay sinh vien \$mssv\")\n        }\n        \n        println(\"Tai thanh cong: \$mssv - Diem: \$diem\")\n        \n    } catch (e: IllegalArgumentException) {\n        println(\"Loi du lieu: \${e.message}\")\n    } catch (e: Exception) {\n        println(\"Loi khong xac dinh: \${e.message}\")\n    }\n}\n\n// Xử lý lỗi khi tính điểm trung bình\nfun tinhDiemTrungBinh(diems: List<Double>): Double {\n    try {\n        require(diems.isNotEmpty()) { \"Danh sach diem khong duoc rong\" }\n        require(diems.all { it >= 0.0 && it <= 10.0 }) { \"Diem phai trong khoang 0-10\" }\n        \n        return diems.average()\n        \n    } catch (e: IllegalArgumentException) {\n        println(\"Loi tinh diem: \${e.message}\")\n        return 0.0\n    }\n}\n\n// Xử lý lỗi khi lưu dữ liệu\nfun luuThongTinSinhVien(sv: SinhVien): Boolean {\n    return try {\n        require(sv.hoTen.isNotBlank()) { \"Ten sinh vien khong duoc rong\" }\n        require(sv.mssv.matches(Regex(\"SV\\\\d{3}\"))) { \"MSSV khong dung dinh dang\" }\n        \n        // Giả lập lưu vào database\n        println(\"Da luu thong tin sinh vien: \${sv.hoTen}\")\n        true\n        \n    } catch (e: IllegalArgumentException) {\n        println(\"Loi validation: \${e.message}\")\n        false\n    } catch (e: Exception) {\n        println(\"Loi he thong: \${e.message}\")\n        false\n    }\n}"
        ),
        KotlinConcept(
            "Lớp liệt kê (Enum Classes)",
            "Định nghĩa tập hợp các hằng số cố định",
            "// Enum cho xếp loại học tập\nenum class XepLoai(val diemToiThieu: Double, val moTa: String) {\n    XUAT_SAC(9.0, \"Xuat sac\"),\n    GIOI(8.5, \"Gioi\"),\n    KHA(7.0, \"Kha\"),\n    TRUNG_BINH(5.5, \"Trung binh\"),\n    YEU(0.0, \"Yeu\");\n    \n    companion object {\n        fun tuDiem(diem: Double): XepLoai {\n            return values().reversed().first { diem >= it.diemToiThieu }\n        }\n    }\n}\n\n// Enum cho trạng thái sinh viên\nenum class TrangThaiSinhVien {\n    DANG_HOC,\n    TAM_NGHI,\n    DA_TOT_NGHIEP,\n    BUOC_THOI;\n    \n    fun layMoTa(): String {\n        return when (this) {\n            DANG_HOC -> \"Dang hoc\"\n            TAM_NGHI -> \"Tam nghi\"\n            DA_TOT_NGHIEP -> \"Da tot nghiep\"\n            BUOC_THOI -> \"Buoc thoai\"\n        }\n    }\n}\n\n// Enum cho các khoa\nenum class Khoa(val maKhoa: String, val tenKhoa: String) {\n    CNTT(\"CT\", \"Cong nghe thong tin\"),\n    TOAN(\"TO\", \"Toan hoc\"),\n    LY(\"LY\", \"Vat ly\"),\n    HOA(\"HO\", \"Hoa hoc\");\n    \n    override fun toString(): String = \"\$maKhoa - \$tenKhoa\"\n}\n\n// Sử dụng enum\nval diem = 8.2\nval xepLoai = XepLoai.tuDiem(diem)\nprintln(\"Diem \$diem -> Xep loai: \${xepLoai.moTa}\")\n\nval trangThai = TrangThaiSinhVien.DANG_HOC\nprintln(\"Trang thai: \${trangThai.layMoTa()}\")\n\n// Sử dụng trong when\nwhen (trangThai) {\n    TrangThaiSinhVien.DANG_HOC -> println(\"Can dang ky mon hoc\")\n    TrangThaiSinhVien.TAM_NGHI -> println(\"Can lam don xin hoc lai\")\n    TrangThaiSinhVien.DA_TOT_NGHIEP -> println(\"Can lam thu tuc bang cap\")\n    TrangThaiSinhVien.BUOC_THOI -> println(\"Khong the dang ky mon hoc\")\n}\n\n// In tất cả các khoa\nprintln(\"Danh sach khoa:\")\nKhoa.values().forEach { println(\"- \$it\") }"
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
                text = "Kotlin Coroutines & Design Patterns - Lab 1",
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
