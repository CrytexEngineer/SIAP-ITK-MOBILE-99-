package com.example.siapitk

class DaysUtils {
    fun covertDays(day:Int):String{
        if (day==1)return "Senin"
        if (day==2)return "Selasa"
        if(day==3)return "Rabu"
        if(day==4)return "Kamis"
        if (day==5)return "Jumat"
        if (day==6)return "Sabtu"
        else return  "-"
    }
}