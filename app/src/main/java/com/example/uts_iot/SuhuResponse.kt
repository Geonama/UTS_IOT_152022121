package com.example.uts_iot.model

data class SuhuResponse(
    val suhu_max: String,
    val suhu_min: String,
    val suhu_avg: Double,
    val nilai_suhu_max_humid_max: List<NilaiSuhu>,
    val month_year_max: List<MonthYear>
)

data class NilaiSuhu(
    val id: String,
    val suhu: String,
    val humid: String,
    val lux: String,
    val timestamp: String
)

data class MonthYear(
    val month_year: String
)
