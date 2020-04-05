package ua.turskyi.domain.model

data class Country (
    var id: Int,
    val name: String,
    val flag: String,
    var visited: Boolean?
)