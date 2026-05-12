package com.example.applicationex.lab1

fun main() {
    println("--- BÀI 2: OOP & COLLECTIONS ---")

    val monHocs = listOf("Toán", "Lý", "Hóa", "Anh", "Tin")
    println("Danh sách môn học:")
    for (i in monHocs.indices) {
        println("Môn ${i + 1}: ${monHocs[i]}")
    }

    println("\n--- Kiểm tra OOP ---")
    val sv = SinhVienLab1("Nguyen Van A", 2002, "20211234", "CTK41")
    println(sv.gioiThieu())
    println("Tuổi của sinh viên: ${sv.tinhTuoi(2024)}")

    val lt = LapTrinhVienLab1("Le Van C", 8000000.0, "Kotlin")
    lt.inThongTin()
}

abstract class PersonLab1(val hoTen: String, val namSinh: Int) {
    abstract fun gioiThieu(): String
    fun tinhTuoi(namHienTai: Int) = namHienTai - namSinh
}

class SinhVienLab1(
    hoTen: String,
    namSinh: Int,
    val mssv: String,
    val lop: String
) : PersonLab1(hoTen, namSinh) {
    override fun gioiThieu() = "Tôi là sinh viên $hoTen, lớp $lop"
}

open class NhanVienLab1(val hoTen: String, val luongCoBan: Double) {
    open fun tinhLuong() = luongCoBan
    fun inThongTin() = println("Nhân viên: $hoTen, Lương: ${tinhLuong()}")
}

class LapTrinhVienLab1(
    hoTen: String,
    luongCoBan: Double,
    val ngonNgu: String
) : NhanVienLab1(hoTen, luongCoBan) {
    override fun tinhLuong() = super.tinhLuong() * 1.2
}
