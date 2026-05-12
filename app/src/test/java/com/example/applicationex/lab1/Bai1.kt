package com.example.applicationex.lab1

fun main() {

    println("Chào mừng đến với Kotlin!")

    val ten = "Nguyen Van A"
    var tuoi = 20
    tuoi = 21
    println("Sinh viên: $ten, Tuổi: $tuoi")

    val mssv = "20211234"
    val diemTB = 8.2
    println("Thông tin: $ten ($mssv) có điểm TB là $diemTB")

    println("\n--- Kiểm tra điều kiện ---")
    if (tuoi >= 18) {
        println("$ten đã đủ tuổi bầu cử.")
    }

    val xepLoai = when {
        diemTB >= 8.5 -> "Giỏi"
        diemTB >= 7.0 -> "Khá"
        diemTB >= 5.0 -> "Trung bình"
        else -> "Yêu"
    }
    println("Xếp loại học lực: $xepLoai")

    println("\n--- Kiểm tra hàm ---")
    val dtb = tinhDiemTB(8.0, 7.5, 9.0)
    println("Điểm trung bình tính từ hàm: $dtb")
}

fun tinhDiemTB(toan: Double, ly: Double, hoa: Double): Double {
    return (toan + ly + hoa) / 3
}
