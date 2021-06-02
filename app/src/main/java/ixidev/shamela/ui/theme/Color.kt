package ixidev.shamela.ui.theme

import androidx.compose.ui.graphics.Color

val White = Color(0xFFFFFFFF)
val ShamelaPrimaryColor = Color(0xfff1f5f8)
val ShamelaAccentColor = Color(0xfff1f5f8)
val ShamelaSecondaryColor = Color(0xff60656b)
val SelectionColor = Color(0xFFFFB300)

val CategoriesColors = listOf(
    Color(0xFF7CB342),
    Color(0xFF1E88E5),
    Color(0xFF8E24AA),
    Color(0xFFD81B60),
    Color(0xFFFFB300),
    Color(0xFF7E57C2),
    Color(0xFF7CB342),
    Color(0xFF00897B),
    Color(0xFFC0CA33),
    Color(0xFF5C6BC0),
    Color(0xFF66BB6A),
    Color(0xFFE65100),
    Color(0xFF4CAF50),
    Color(0xFF01579B),
)

fun getCategoryColor(catId: Long): Color {
    val size = CategoriesColors.size
    return CategoriesColors[catId.toInt() % size]
}
