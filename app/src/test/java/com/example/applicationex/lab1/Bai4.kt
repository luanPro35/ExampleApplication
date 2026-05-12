package com.example.applicationex.lab1

import kotlinx.coroutines.*

fun main() = runBlocking {
    println("--- BÀI 4: COROUTINES & ENUMS ---")

    println("Bắt đầu tải dữ liệu sinh viên...")
    val job = launch {
        delay(1000)
        println("Đã tải xong danh sách sinh viên sau 1 giây!")
    }
    job.join()

    println("\n--- Kiểm tra Enum ---")
    val xepLoai = XepLoaiLab1.GIOI
    println("Xếp loại: ${xepLoai.moTa}, Điểm tối thiểu: ${xepLoai.diemToiThieu}")

    println("\n--- Kiểm tra Exception ---")
    try {
        val diems = listOf<Double>()
        if (diems.isEmpty()) throw IllegalArgumentException("Danh sách điểm không được rỗng")
    } catch (e: Exception) {
        println("Bắt được lỗi: ${e.message}")
    }

    println("\nKết thúc chương trình Lab 1.")
}

enum class XepLoaiLab1(val diemToiThieu: Double, val moTa: String) {
    XUAT_SAC(9.0, "Xuất sắc"),
    GIOI(8.0, "Giỏi"),
    KHA(7.0, "Khá"),
    TRUNG_BINH(5.0, "Trung bình"),
    YEU(0.0, "Yếu")
}
