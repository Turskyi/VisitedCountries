package ua.turskyi.domain.models

data class Country (
    var id: Int,
    val name: String,
    val flag: String,
    var visited: Boolean?
)