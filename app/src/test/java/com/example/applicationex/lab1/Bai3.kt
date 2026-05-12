package com.example.applicationex.lab1

fun main() {
    println("--- BÀI 3: ADVANCED SYNTAX ---")

    val monHocSet = setOf("Toán", "Lý", "Toán")
    println("Số lượng môn học trong Set: ${monHocSet.size}")

    val diemSinhVien = mutableMapOf("SV001" to 8.5, "SV002" to 7.0)
    diemSinhVien["SV003"] = 9.0
    println("\nBảng điểm Map:")
    diemSinhVien.forEach { (mssv, diem) -> println("$mssv: $diem") }

    val diems = listOf(8.5, 7.0, 9.0, 4.5, 8.0)
    val sinhVienGioi = diems.filter { it >= 8.0 }
    println("\nĐiểm các sinh viên giỏi: $sinhVienGioi")
    println("Điểm trung bình: ${diems.average()}")

    println("\n--- Kiểm tra Null Safety ---")
    var ten: String? = null
    println("Tên sinh viên: ${ten ?: "Chưa xác định"}")

    ten = "Nguyen Van B"
    ten?.let {
        println("Xin chào $it. Độ dài tên: ${it.length}")
    }

    val tinhDTB: (List<Double>) -> Double = { it.average() }
    println("\nDTB từ Lambda: ${tinhDTB(diems)}")
}
