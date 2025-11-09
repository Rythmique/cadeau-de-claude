package com.example.coloringkids.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

data class ColorableRegion(
    val id: Int,
    val path: Path,
    val bounds: Rect,
    var fillColor: Color = Color.White
)

@Composable
fun ColoringCanvas(
    item: String,
    type: String,
    selectedColor: Color,
    onClear: () -> Unit,
    modifier: Modifier = Modifier
) {
    var regions by remember(item, type) { mutableStateOf(createRegionsForItem(item, type)) }

    Canvas(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(selectedColor) {
                detectTapGestures { offset ->
                    val tappedRegion = regions.find { region ->
                        region.bounds.contains(offset)
                    }
                    tappedRegion?.let {
                        regions = regions.map { region ->
                            if (region.id == it.id) {
                                region.copy(fillColor = selectedColor)
                            } else {
                                region
                            }
                        }
                    }
                }
            }
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        val centerX = canvasWidth / 2f
        val centerY = canvasHeight / 2f

        // Dessiner toutes les régions remplies
        regions.forEach { region ->
            drawPath(
                path = region.path,
                color = region.fillColor
            )
        }

        // Dessiner les contours noirs
        regions.forEach { region ->
            drawPath(
                path = region.path,
                color = Color.Black,
                style = Stroke(
                    width = 6f,
                    pathEffect = PathEffect.cornerPathEffect(4f)
                )
            )
        }
    }
}

fun createRegionsForItem(item: String, type: String): List<ColorableRegion> {
    return if (type == "alphabet") {
        createLetterRegions(item)
    } else {
        createAnimalRegions(item)
    }
}

fun createLetterRegions(letter: String): List<ColorableRegion> {
    val centerX = 500f
    val centerY = 600f
    val scale = 8f

    return when (letter.uppercase()) {
        "A" -> createLetterA(centerX, centerY, scale)
        "B" -> createLetterB(centerX, centerY, scale)
        "C" -> createLetterC(centerX, centerY, scale)
        "D" -> createLetterD(centerX, centerY, scale)
        "E" -> createLetterE(centerX, centerY, scale)
        "F" -> createLetterF(centerX, centerY, scale)
        "G" -> createLetterG(centerX, centerY, scale)
        "H" -> createLetterH(centerX, centerY, scale)
        "I" -> createLetterI(centerX, centerY, scale)
        "J" -> createLetterJ(centerX, centerY, scale)
        "K" -> createLetterK(centerX, centerY, scale)
        "L" -> createLetterL(centerX, centerY, scale)
        "M" -> createLetterM(centerX, centerY, scale)
        "N" -> createLetterN(centerX, centerY, scale)
        "O" -> createLetterO(centerX, centerY, scale)
        "P" -> createLetterP(centerX, centerY, scale)
        "Q" -> createLetterQ(centerX, centerY, scale)
        "R" -> createLetterR(centerX, centerY, scale)
        "S" -> createLetterS(centerX, centerY, scale)
        "T" -> createLetterT(centerX, centerY, scale)
        "U" -> createLetterU(centerX, centerY, scale)
        "V" -> createLetterV(centerX, centerY, scale)
        "W" -> createLetterW(centerX, centerY, scale)
        "X" -> createLetterX(centerX, centerY, scale)
        "Y" -> createLetterY(centerX, centerY, scale)
        "Z" -> createLetterZ(centerX, centerY, scale)
        else -> createLetterA(centerX, centerY, scale)
    }
}

// Lettre A - forme triangulaire avec barre horizontale
fun createLetterA(cx: Float, cy: Float, scale: Float): List<ColorableRegion> {
    return listOf(
        // Jambe gauche
        ColorableRegion(
            id = 1,
            path = Path().apply {
                moveTo(cx - 30 * scale, cy + 30 * scale)
                lineTo(cx - 5 * scale, cy + 30 * scale)
                lineTo(cx, cy - 30 * scale)
                lineTo(cx - 15 * scale, cy - 30 * scale)
                close()
            },
            bounds = Rect(cx - 30 * scale, cy - 30 * scale, cx, cy + 30 * scale)
        ),
        // Jambe droite
        ColorableRegion(
            id = 2,
            path = Path().apply {
                moveTo(cx + 5 * scale, cy + 30 * scale)
                lineTo(cx + 30 * scale, cy + 30 * scale)
                lineTo(cx + 15 * scale, cy - 30 * scale)
                lineTo(cx, cy - 30 * scale)
                close()
            },
            bounds = Rect(cx, cy - 30 * scale, cx + 30 * scale, cy + 30 * scale)
        ),
        // Barre horizontale
        ColorableRegion(
            id = 3,
            path = Path().apply {
                addRect(Rect(cx - 18 * scale, cy - 2 * scale, cx + 18 * scale, cy + 8 * scale))
            },
            bounds = Rect(cx - 18 * scale, cy - 2 * scale, cx + 18 * scale, cy + 8 * scale)
        )
    )
}

// Lettre B - forme avec deux bosses
fun createLetterB(cx: Float, cy: Float, scale: Float): List<ColorableRegion> {
    return listOf(
        // Barre verticale gauche
        ColorableRegion(
            id = 1,
            path = Path().apply {
                addRect(Rect(cx - 25 * scale, cy - 30 * scale, cx - 5 * scale, cy + 30 * scale))
            },
            bounds = Rect(cx - 25 * scale, cy - 30 * scale, cx - 5 * scale, cy + 30 * scale)
        ),
        // Bosse supérieure
        ColorableRegion(
            id = 2,
            path = Path().apply {
                addRoundRect(
                    androidx.compose.ui.geometry.RoundRect(
                        cx - 5 * scale, cy - 30 * scale, cx + 25 * scale, cy - 2 * scale,
                        15f * scale, 15f * scale
                    )
                )
            },
            bounds = Rect(cx - 5 * scale, cy - 30 * scale, cx + 25 * scale, cy - 2 * scale)
        ),
        // Bosse inférieure
        ColorableRegion(
            id = 3,
            path = Path().apply {
                addRoundRect(
                    androidx.compose.ui.geometry.RoundRect(
                        cx - 5 * scale, cy + 2 * scale, cx + 28 * scale, cy + 30 * scale,
                        15f * scale, 15f * scale
                    )
                )
            },
            bounds = Rect(cx - 5 * scale, cy + 2 * scale, cx + 28 * scale, cy + 30 * scale)
        )
    )
}

// Lettre C - arc de cercle
fun createLetterC(cx: Float, cy: Float, scale: Float): List<ColorableRegion> {
    return listOf(
        ColorableRegion(
            id = 1,
            path = Path().apply {
                // Arc extérieur
                addArc(
                    Rect(cx - 30 * scale, cy - 30 * scale, cx + 30 * scale, cy + 30 * scale),
                    startAngleDegrees = 45f,
                    sweepAngleDegrees = 270f
                )
                // Arc intérieur (pour créer l'épaisseur)
                addArc(
                    Rect(cx - 15 * scale, cy - 15 * scale, cx + 15 * scale, cy + 15 * scale),
                    startAngleDegrees = 45f,
                    sweepAngleDegrees = 270f
                )
            },
            bounds = Rect(cx - 30 * scale, cy - 30 * scale, cx + 30 * scale, cy + 30 * scale)
        )
    )
}

// Lettres simplifiées pour les autres (D-Z)
fun createLetterD(cx: Float, cy: Float, scale: Float): List<ColorableRegion> = listOf(
    ColorableRegion(1, Path().apply {
        addRect(Rect(cx - 25*scale, cy - 30*scale, cx - 5*scale, cy + 30*scale))
    }, Rect(cx - 25*scale, cy - 30*scale, cx - 5*scale, cy + 30*scale)),
    ColorableRegion(2, Path().apply {
        addOval(Rect(cx - 5*scale, cy - 30*scale, cx + 25*scale, cy + 30*scale))
    }, Rect(cx - 5*scale, cy - 30*scale, cx + 25*scale, cy + 30*scale))
)

fun createLetterE(cx: Float, cy: Float, scale: Float): List<ColorableRegion> = listOf(
    ColorableRegion(1, Path().apply {
        addRect(Rect(cx - 25*scale, cy - 30*scale, cx - 5*scale, cy + 30*scale))
    }, Rect(cx - 25*scale, cy - 30*scale, cx - 5*scale, cy + 30*scale)),
    ColorableRegion(2, Path().apply {
        addRect(Rect(cx - 5*scale, cy - 30*scale, cx + 25*scale, cy - 15*scale))
    }, Rect(cx - 5*scale, cy - 30*scale, cx + 25*scale, cy - 15*scale)),
    ColorableRegion(3, Path().apply {
        addRect(Rect(cx - 5*scale, cy - 7*scale, cx + 20*scale, cy + 7*scale))
    }, Rect(cx - 5*scale, cy - 7*scale, cx + 20*scale, cy + 7*scale)),
    ColorableRegion(4, Path().apply {
        addRect(Rect(cx - 5*scale, cy + 15*scale, cx + 25*scale, cy + 30*scale))
    }, Rect(cx - 5*scale, cy + 15*scale, cx + 25*scale, cy + 30*scale))
)

fun createLetterF(cx: Float, cy: Float, scale: Float): List<ColorableRegion> = listOf(
    ColorableRegion(1, Path().apply {
        addRect(Rect(cx - 25*scale, cy - 30*scale, cx - 5*scale, cy + 30*scale))
    }, Rect(cx - 25*scale, cy - 30*scale, cx - 5*scale, cy + 30*scale)),
    ColorableRegion(2, Path().apply {
        addRect(Rect(cx - 5*scale, cy - 30*scale, cx + 25*scale, cy - 15*scale))
    }, Rect(cx - 5*scale, cy - 30*scale, cx + 25*scale, cy - 15*scale)),
    ColorableRegion(3, Path().apply {
        addRect(Rect(cx - 5*scale, cy - 7*scale, cx + 20*scale, cy + 7*scale))
    }, Rect(cx - 5*scale, cy - 7*scale, cx + 20*scale, cy + 7*scale))
)

fun createLetterG(cx: Float, cy: Float, scale: Float): List<ColorableRegion> = createLetterC(cx, cy, scale) + listOf(
    ColorableRegion(2, Path().apply {
        addRect(Rect(cx + 10*scale, cy - 5*scale, cx + 30*scale, cy + 30*scale))
    }, Rect(cx + 10*scale, cy - 5*scale, cx + 30*scale, cy + 30*scale))
)

fun createLetterH(cx: Float, cy: Float, scale: Float): List<ColorableRegion> = listOf(
    ColorableRegion(1, Path().apply {
        addRect(Rect(cx - 30*scale, cy - 30*scale, cx - 10*scale, cy + 30*scale))
    }, Rect(cx - 30*scale, cy - 30*scale, cx - 10*scale, cy + 30*scale)),
    ColorableRegion(2, Path().apply {
        addRect(Rect(cx + 10*scale, cy - 30*scale, cx + 30*scale, cy + 30*scale))
    }, Rect(cx + 10*scale, cy - 30*scale, cx + 30*scale, cy + 30*scale)),
    ColorableRegion(3, Path().apply {
        addRect(Rect(cx - 10*scale, cy - 7*scale, cx + 10*scale, cy + 7*scale))
    }, Rect(cx - 10*scale, cy - 7*scale, cx + 10*scale, cy + 7*scale))
)

fun createLetterI(cx: Float, cy: Float, scale: Float): List<ColorableRegion> = listOf(
    ColorableRegion(1, Path().apply {
        addRect(Rect(cx - 10*scale, cy - 30*scale, cx + 10*scale, cy + 30*scale))
    }, Rect(cx - 10*scale, cy - 30*scale, cx + 10*scale, cy + 30*scale))
)

fun createLetterJ(cx: Float, cy: Float, scale: Float): List<ColorableRegion> = listOf(
    ColorableRegion(1, Path().apply {
        addRect(Rect(cx + 5*scale, cy - 30*scale, cx + 25*scale, cy + 20*scale))
    }, Rect(cx + 5*scale, cy - 30*scale, cx + 25*scale, cy + 20*scale)),
    ColorableRegion(2, Path().apply {
        addRoundRect(androidx.compose.ui.geometry.RoundRect(
            cx - 20*scale, cy + 10*scale, cx + 15*scale, cy + 30*scale, 15f*scale, 15f*scale
        ))
    }, Rect(cx - 20*scale, cy + 10*scale, cx + 15*scale, cy + 30*scale))
)

fun createLetterK(cx: Float, cy: Float, scale: Float): List<ColorableRegion> = listOf(
    ColorableRegion(1, Path().apply {
        addRect(Rect(cx - 30*scale, cy - 30*scale, cx - 10*scale, cy + 30*scale))
    }, Rect(cx - 30*scale, cy - 30*scale, cx - 10*scale, cy + 30*scale)),
    ColorableRegion(2, Path().apply {
        moveTo(cx - 10*scale, cy)
        lineTo(cx + 25*scale, cy - 30*scale)
        lineTo(cx + 25*scale, cy - 15*scale)
        lineTo(cx + 5*scale, cy)
        close()
    }, Rect(cx - 10*scale, cy - 30*scale, cx + 25*scale, cy)),
    ColorableRegion(3, Path().apply {
        moveTo(cx - 10*scale, cy)
        lineTo(cx + 5*scale, cy)
        lineTo(cx + 25*scale, cy + 15*scale)
        lineTo(cx + 25*scale, cy + 30*scale)
        close()
    }, Rect(cx - 10*scale, cy, cx + 25*scale, cy + 30*scale))
)

fun createLetterL(cx: Float, cy: Float, scale: Float): List<ColorableRegion> = listOf(
    ColorableRegion(1, Path().apply {
        addRect(Rect(cx - 25*scale, cy - 30*scale, cx - 5*scale, cy + 30*scale))
    }, Rect(cx - 25*scale, cy - 30*scale, cx - 5*scale, cy + 30*scale)),
    ColorableRegion(2, Path().apply {
        addRect(Rect(cx - 5*scale, cy + 15*scale, cx + 25*scale, cy + 30*scale))
    }, Rect(cx - 5*scale, cy + 15*scale, cx + 25*scale, cy + 30*scale))
)

fun createLetterM(cx: Float, cy: Float, scale: Float): List<ColorableRegion> = listOf(
    ColorableRegion(1, Path().apply {
        addRect(Rect(cx - 35*scale, cy - 30*scale, cx - 20*scale, cy + 30*scale))
    }, Rect(cx - 35*scale, cy - 30*scale, cx - 20*scale, cy + 30*scale)),
    ColorableRegion(2, Path().apply {
        moveTo(cx - 20*scale, cy - 30*scale)
        lineTo(cx - 5*scale, cy - 30*scale)
        lineTo(cx, cy + 5*scale)
        lineTo(cx - 10*scale, cy + 5*scale)
        close()
    }, Rect(cx - 20*scale, cy - 30*scale, cx, cy + 5*scale)),
    ColorableRegion(3, Path().apply {
        moveTo(cx + 5*scale, cy - 30*scale)
        lineTo(cx + 20*scale, cy - 30*scale)
        lineTo(cx + 10*scale, cy + 5*scale)
        lineTo(cx, cy + 5*scale)
        close()
    }, Rect(cx, cy - 30*scale, cx + 20*scale, cy + 5*scale)),
    ColorableRegion(4, Path().apply {
        addRect(Rect(cx + 20*scale, cy - 30*scale, cx + 35*scale, cy + 30*scale))
    }, Rect(cx + 20*scale, cy - 30*scale, cx + 35*scale, cy + 30*scale))
)

fun createLetterN(cx: Float, cy: Float, scale: Float): List<ColorableRegion> = listOf(
    ColorableRegion(1, Path().apply {
        addRect(Rect(cx - 30*scale, cy - 30*scale, cx - 10*scale, cy + 30*scale))
    }, Rect(cx - 30*scale, cy - 30*scale, cx - 10*scale, cy + 30*scale)),
    ColorableRegion(2, Path().apply {
        moveTo(cx - 10*scale, cy - 30*scale)
        lineTo(cx + 10*scale, cy - 30*scale)
        lineTo(cx + 30*scale, cy + 30*scale)
        lineTo(cx + 10*scale, cy + 30*scale)
        close()
    }, Rect(cx - 10*scale, cy - 30*scale, cx + 30*scale, cy + 30*scale)),
    ColorableRegion(3, Path().apply {
        addRect(Rect(cx + 10*scale, cy - 30*scale, cx + 30*scale, cy + 30*scale))
    }, Rect(cx + 10*scale, cy - 30*scale, cx + 30*scale, cy + 30*scale))
)

fun createLetterO(cx: Float, cy: Float, scale: Float): List<ColorableRegion> = listOf(
    ColorableRegion(1, Path().apply {
        addOval(Rect(cx - 30*scale, cy - 30*scale, cx + 30*scale, cy + 30*scale))
    }, Rect(cx - 30*scale, cy - 30*scale, cx + 30*scale, cy + 30*scale))
)

fun createLetterP(cx: Float, cy: Float, scale: Float): List<ColorableRegion> = listOf(
    ColorableRegion(1, Path().apply {
        addRect(Rect(cx - 25*scale, cy - 30*scale, cx - 5*scale, cy + 30*scale))
    }, Rect(cx - 25*scale, cy - 30*scale, cx - 5*scale, cy + 30*scale)),
    ColorableRegion(2, Path().apply {
        addRoundRect(androidx.compose.ui.geometry.RoundRect(
            cx - 5*scale, cy - 30*scale, cx + 25*scale, cy + 5*scale, 15f*scale, 15f*scale
        ))
    }, Rect(cx - 5*scale, cy - 30*scale, cx + 25*scale, cy + 5*scale))
)

fun createLetterQ(cx: Float, cy: Float, scale: Float): List<ColorableRegion> =
    createLetterO(cx, cy, scale) + listOf(
        ColorableRegion(2, Path().apply {
            moveTo(cx + 15*scale, cy + 15*scale)
            lineTo(cx + 30*scale, cy + 35*scale)
            lineTo(cx + 20*scale, cy + 40*scale)
            lineTo(cx + 5*scale, cy + 25*scale)
            close()
        }, Rect(cx + 5*scale, cy + 15*scale, cx + 30*scale, cy + 40*scale))
    )

fun createLetterR(cx: Float, cy: Float, scale: Float): List<ColorableRegion> = listOf(
    ColorableRegion(1, Path().apply {
        addRect(Rect(cx - 25*scale, cy - 30*scale, cx - 5*scale, cy + 30*scale))
    }, Rect(cx - 25*scale, cy - 30*scale, cx - 5*scale, cy + 30*scale)),
    ColorableRegion(2, Path().apply {
        addRoundRect(androidx.compose.ui.geometry.RoundRect(
            cx - 5*scale, cy - 30*scale, cx + 25*scale, cy + 5*scale, 15f*scale, 15f*scale
        ))
    }, Rect(cx - 5*scale, cy - 30*scale, cx + 25*scale, cy + 5*scale)),
    ColorableRegion(3, Path().apply {
        moveTo(cx + 5*scale, cy + 5*scale)
        lineTo(cx + 15*scale, cy + 5*scale)
        lineTo(cx + 30*scale, cy + 30*scale)
        lineTo(cx + 20*scale, cy + 30*scale)
        close()
    }, Rect(cx + 5*scale, cy + 5*scale, cx + 30*scale, cy + 30*scale))
)

fun createLetterS(cx: Float, cy: Float, scale: Float): List<ColorableRegion> = listOf(
    ColorableRegion(1, Path().apply {
        addRoundRect(androidx.compose.ui.geometry.RoundRect(
            cx - 25*scale, cy - 30*scale, cx + 25*scale, cy - 5*scale, 15f*scale, 15f*scale
        ))
    }, Rect(cx - 25*scale, cy - 30*scale, cx + 25*scale, cy - 5*scale)),
    ColorableRegion(2, Path().apply {
        addRoundRect(androidx.compose.ui.geometry.RoundRect(
            cx - 25*scale, cy + 5*scale, cx + 25*scale, cy + 30*scale, 15f*scale, 15f*scale
        ))
    }, Rect(cx - 25*scale, cy + 5*scale, cx + 25*scale, cy + 30*scale))
)

fun createLetterT(cx: Float, cy: Float, scale: Float): List<ColorableRegion> = listOf(
    ColorableRegion(1, Path().apply {
        addRect(Rect(cx - 30*scale, cy - 30*scale, cx + 30*scale, cy - 15*scale))
    }, Rect(cx - 30*scale, cy - 30*scale, cx + 30*scale, cy - 15*scale)),
    ColorableRegion(2, Path().apply {
        addRect(Rect(cx - 10*scale, cy - 15*scale, cx + 10*scale, cy + 30*scale))
    }, Rect(cx - 10*scale, cy - 15*scale, cx + 10*scale, cy + 30*scale))
)

fun createLetterU(cx: Float, cy: Float, scale: Float): List<ColorableRegion> = listOf(
    ColorableRegion(1, Path().apply {
        moveTo(cx - 30*scale, cy - 30*scale)
        lineTo(cx - 10*scale, cy - 30*scale)
        lineTo(cx - 10*scale, cy + 15*scale)
        arcTo(Rect(cx - 25*scale, cy + 5*scale, cx + 25*scale, cy + 30*scale), 180f, 60f, false)
        lineTo(cx - 25*scale, cy + 5*scale)
        lineTo(cx - 30*scale, cy + 5*scale)
        close()
    }, Rect(cx - 30*scale, cy - 30*scale, cx - 10*scale, cy + 30*scale)),
    ColorableRegion(2, Path().apply {
        moveTo(cx + 10*scale, cy - 30*scale)
        lineTo(cx + 30*scale, cy - 30*scale)
        lineTo(cx + 30*scale, cy + 5*scale)
        lineTo(cx + 25*scale, cy + 5*scale)
        arcTo(Rect(cx - 25*scale, cy + 5*scale, cx + 25*scale, cy + 30*scale), 0f, -60f, false)
        lineTo(cx + 10*scale, cy + 15*scale)
        close()
    }, Rect(cx + 10*scale, cy - 30*scale, cx + 30*scale, cy + 30*scale)),
    ColorableRegion(3, Path().apply {
        addArc(Rect(cx - 25*scale, cy + 5*scale, cx + 25*scale, cy + 30*scale), 120f, 300f)
    }, Rect(cx - 25*scale, cy + 5*scale, cx + 25*scale, cy + 30*scale))
)

fun createLetterV(cx: Float, cy: Float, scale: Float): List<ColorableRegion> = listOf(
    ColorableRegion(1, Path().apply {
        moveTo(cx - 30*scale, cy - 30*scale)
        lineTo(cx - 15*scale, cy - 30*scale)
        lineTo(cx - 5*scale, cy + 30*scale)
        lineTo(cx - 15*scale, cy + 30*scale)
        close()
    }, Rect(cx - 30*scale, cy - 30*scale, cx - 5*scale, cy + 30*scale)),
    ColorableRegion(2, Path().apply {
        moveTo(cx + 15*scale, cy - 30*scale)
        lineTo(cx + 30*scale, cy - 30*scale)
        lineTo(cx + 15*scale, cy + 30*scale)
        lineTo(cx + 5*scale, cy + 30*scale)
        close()
    }, Rect(cx + 5*scale, cy - 30*scale, cx + 30*scale, cy + 30*scale))
)

fun createLetterW(cx: Float, cy: Float, scale: Float): List<ColorableRegion> = listOf(
    ColorableRegion(1, Path().apply {
        moveTo(cx - 35*scale, cy - 30*scale)
        lineTo(cx - 23*scale, cy - 30*scale)
        lineTo(cx - 15*scale, cy + 30*scale)
        lineTo(cx - 25*scale, cy + 30*scale)
        close()
    }, Rect(cx - 35*scale, cy - 30*scale, cx - 15*scale, cy + 30*scale)),
    ColorableRegion(2, Path().apply {
        moveTo(cx - 10*scale, cy - 30*scale)
        lineTo(cx + 2*scale, cy - 30*scale)
        lineTo(cx, cy + 15*scale)
        lineTo(cx - 8*scale, cy + 15*scale)
        close()
    }, Rect(cx - 10*scale, cy - 30*scale, cx + 2*scale, cy + 15*scale)),
    ColorableRegion(3, Path().apply {
        moveTo(cx + 8*scale, cy - 30*scale)
        lineTo(cx + 20*scale, cy - 30*scale)
        lineTo(cx + 18*scale, cy + 15*scale)
        lineTo(cx + 10*scale, cy + 15*scale)
        close()
    }, Rect(cx + 8*scale, cy - 30*scale, cx + 20*scale, cy + 15*scale)),
    ColorableRegion(4, Path().apply {
        moveTo(cx + 23*scale, cy - 30*scale)
        lineTo(cx + 35*scale, cy - 30*scale)
        lineTo(cx + 25*scale, cy + 30*scale)
        lineTo(cx + 15*scale, cy + 30*scale)
        close()
    }, Rect(cx + 15*scale, cy - 30*scale, cx + 35*scale, cy + 30*scale))
)

fun createLetterX(cx: Float, cy: Float, scale: Float): List<ColorableRegion> = listOf(
    ColorableRegion(1, Path().apply {
        moveTo(cx - 30*scale, cy - 30*scale)
        lineTo(cx - 10*scale, cy - 30*scale)
        lineTo(cx, cy - 5*scale)
        lineTo(cx - 15*scale, cy - 5*scale)
        close()
    }, Rect(cx - 30*scale, cy - 30*scale, cx, cy - 5*scale)),
    ColorableRegion(2, Path().apply {
        moveTo(cx + 10*scale, cy - 30*scale)
        lineTo(cx + 30*scale, cy - 30*scale)
        lineTo(cx + 15*scale, cy - 5*scale)
        lineTo(cx, cy - 5*scale)
        close()
    }, Rect(cx, cy - 30*scale, cx + 30*scale, cy - 5*scale)),
    ColorableRegion(3, Path().apply {
        moveTo(cx - 15*scale, cy + 5*scale)
        lineTo(cx, cy + 5*scale)
        lineTo(cx - 10*scale, cy + 30*scale)
        lineTo(cx - 30*scale, cy + 30*scale)
        close()
    }, Rect(cx - 30*scale, cy + 5*scale, cx, cy + 30*scale)),
    ColorableRegion(4, Path().apply {
        moveTo(cx, cy + 5*scale)
        lineTo(cx + 15*scale, cy + 5*scale)
        lineTo(cx + 30*scale, cy + 30*scale)
        lineTo(cx + 10*scale, cy + 30*scale)
        close()
    }, Rect(cx, cy + 5*scale, cx + 30*scale, cy + 30*scale))
)

fun createLetterY(cx: Float, cy: Float, scale: Float): List<ColorableRegion> = listOf(
    ColorableRegion(1, Path().apply {
        moveTo(cx - 30*scale, cy - 30*scale)
        lineTo(cx - 15*scale, cy - 30*scale)
        lineTo(cx - 7*scale, cy - 5*scale)
        lineTo(cx - 17*scale, cy - 5*scale)
        close()
    }, Rect(cx - 30*scale, cy - 30*scale, cx - 7*scale, cy - 5*scale)),
    ColorableRegion(2, Path().apply {
        moveTo(cx + 15*scale, cy - 30*scale)
        lineTo(cx + 30*scale, cy - 30*scale)
        lineTo(cx + 17*scale, cy - 5*scale)
        lineTo(cx + 7*scale, cy - 5*scale)
        close()
    }, Rect(cx + 7*scale, cy - 30*scale, cx + 30*scale, cy - 5*scale)),
    ColorableRegion(3, Path().apply {
        addRect(Rect(cx - 10*scale, cy - 5*scale, cx + 10*scale, cy + 30*scale))
    }, Rect(cx - 10*scale, cy - 5*scale, cx + 10*scale, cy + 30*scale))
)

fun createLetterZ(cx: Float, cy: Float, scale: Float): List<ColorableRegion> = listOf(
    ColorableRegion(1, Path().apply {
        addRect(Rect(cx - 30*scale, cy - 30*scale, cx + 30*scale, cy - 15*scale))
    }, Rect(cx - 30*scale, cy - 30*scale, cx + 30*scale, cy - 15*scale)),
    ColorableRegion(2, Path().apply {
        moveTo(cx - 20*scale, cy - 15*scale)
        lineTo(cx + 30*scale, cy - 15*scale)
        lineTo(cx + 10*scale, cy + 15*scale)
        lineTo(cx - 30*scale, cy + 15*scale)
        close()
    }, Rect(cx - 30*scale, cy - 15*scale, cx + 30*scale, cy + 15*scale)),
    ColorableRegion(3, Path().apply {
        addRect(Rect(cx - 30*scale, cy + 15*scale, cx + 30*scale, cy + 30*scale))
    }, Rect(cx - 30*scale, cy + 15*scale, cx + 30*scale, cy + 30*scale))
)

// ANIMAUX RÉALISTES

fun createAnimalRegions(animalName: String): List<ColorableRegion> {
    val cx = 500f
    val cy = 600f

    return when (animalName.lowercase()) {
        "chat" -> createCat(cx, cy)
        "chien" -> createDog(cx, cy)
        "lion" -> createLion(cx, cy)
        "ours" -> createBear(cx, cy)
        "lapin" -> createRabbit(cx, cy)
        "poisson" -> createFish(cx, cy)
        "oiseau" -> createBird(cx, cy)
        "papillon" -> createButterfly(cx, cy)
        else -> createCat(cx, cy) // Par défaut: chat
    }
}

// Chat mignon avec oreilles triangulaires
fun createCat(cx: Float, cy: Float): List<ColorableRegion> {
    return listOf(
        // Tête ronde
        ColorableRegion(1, Path().apply {
            addOval(Rect(cx - 150f, cy - 200f, cx + 150f, cy + 100f))
        }, Rect(cx - 150f, cy - 200f, cx + 150f, cy + 100f)),

        // Oreille gauche (triangle)
        ColorableRegion(2, Path().apply {
            moveTo(cx - 120f, cy - 180f)
            lineTo(cx - 180f, cy - 300f)
            lineTo(cx - 60f, cy - 220f)
            close()
        }, Rect(cx - 180f, cy - 300f, cx - 60f, cy - 180f)),

        // Oreille droite (triangle)
        ColorableRegion(3, Path().apply {
            moveTo(cx + 120f, cy - 180f)
            lineTo(cx + 180f, cy - 300f)
            lineTo(cx + 60f, cy - 220f)
            close()
        }, Rect(cx + 60f, cy - 300f, cx + 180f, cy - 180f)),

        // Corps
        ColorableRegion(4, Path().apply {
            addRoundRect(androidx.compose.ui.geometry.RoundRect(
                cx - 120f, cy + 80f, cx + 120f, cy + 350f, 40f, 40f
            ))
        }, Rect(cx - 120f, cy + 80f, cx + 120f, cy + 350f)),

        // Patte avant gauche
        ColorableRegion(5, Path().apply {
            addRoundRect(androidx.compose.ui.geometry.RoundRect(
                cx - 100f, cy + 320f, cx - 50f, cy + 480f, 20f, 20f
            ))
        }, Rect(cx - 100f, cy + 320f, cx - 50f, cy + 480f)),

        // Patte avant droite
        ColorableRegion(6, Path().apply {
            addRoundRect(androidx.compose.ui.geometry.RoundRect(
                cx + 50f, cy + 320f, cx + 100f, cy + 480f, 20f, 20f
            ))
        }, Rect(cx + 50f, cy + 320f, cx + 100f, cy + 480f)),

        // Queue
        ColorableRegion(7, Path().apply {
            moveTo(cx + 100f, cy + 300f)
            quadraticTo(cx + 200f, cy + 250f, cx + 180f, cy + 150f)
            quadraticTo(cx + 220f, cy + 250f, cx + 120f, cy + 300f)
            close()
        }, Rect(cx + 100f, cy + 150f, cx + 220f, cy + 300f))
    )
}

// Chien avec museau
fun createDog(cx: Float, cy: Float): List<ColorableRegion> {
    return listOf(
        // Tête
        ColorableRegion(1, Path().apply {
            addOval(Rect(cx - 130f, cy - 180f, cx + 130f, cy + 80f))
        }, Rect(cx - 130f, cy - 180f, cx + 130f, cy + 80f)),

        // Oreille gauche tombante
        ColorableRegion(2, Path().apply {
            addOval(Rect(cx - 180f, cy - 150f, cx - 100f, cy + 50f))
        }, Rect(cx - 180f, cy - 150f, cx - 100f, cy + 50f)),

        // Oreille droite tombante
        ColorableRegion(3, Path().apply {
            addOval(Rect(cx + 100f, cy - 150f, cx + 180f, cy + 50f))
        }, Rect(cx + 100f, cy - 150f, cx + 180f, cy + 50f)),

        // Museau
        ColorableRegion(4, Path().apply {
            addOval(Rect(cx - 70f, cy + 20f, cx + 70f, cy + 140f))
        }, Rect(cx - 70f, cy + 20f, cx + 70f, cy + 140f)),

        // Corps
        ColorableRegion(5, Path().apply {
            addOval(Rect(cx - 150f, cy + 100f, cx + 150f, cy + 400f))
        }, Rect(cx - 150f, cy + 100f, cx + 150f, cy + 400f)),

        // Patte avant gauche
        ColorableRegion(6, Path().apply {
            addRect(Rect(cx - 120f, cy + 360f, cx - 70f, cy + 500f))
        }, Rect(cx - 120f, cy + 360f, cx - 70f, cy + 500f)),

        // Patte avant droite
        ColorableRegion(7, Path().apply {
            addRect(Rect(cx + 70f, cy + 360f, cx + 120f, cy + 500f))
        }, Rect(cx + 70f, cy + 360f, cx + 120f, cy + 500f)),

        // Queue dressée
        ColorableRegion(8, Path().apply {
            moveTo(cx + 130f, cy + 350f)
            quadraticTo(cx + 200f, cy + 200f, cx + 180f, cy + 100f)
            lineTo(cx + 160f, cy + 110f)
            quadraticTo(cx + 170f, cy + 210f, cx + 110f, cy + 360f)
            close()
        }, Rect(cx + 110f, cy + 100f, cx + 200f, cy + 360f))
    )
}

// Lion avec crinière
fun createLion(cx: Float, cy: Float): List<ColorableRegion> {
    return listOf(
        // Crinière (grand cercle autour de la tête)
        ColorableRegion(1, Path().apply {
            addOval(Rect(cx - 200f, cy - 250f, cx + 200f, cy + 150f))
        }, Rect(cx - 200f, cy - 250f, cx + 200f, cy + 150f)),

        // Tête (plus petite)
        ColorableRegion(2, Path().apply {
            addOval(Rect(cx - 120f, cy - 180f, cx + 120f, cy + 80f))
        }, Rect(cx - 120f, cy - 180f, cx + 120f, cy + 80f)),

        // Corps
        ColorableRegion(3, Path().apply {
            addOval(Rect(cx - 140f, cy + 60f, cx + 140f, cy + 380f))
        }, Rect(cx - 140f, cy + 60f, cx + 140f, cy + 380f)),

        // Pattes (4)
        ColorableRegion(4, Path().apply {
            addRect(Rect(cx - 120f, cy + 350f, cx - 70f, cy + 480f))
        }, Rect(cx - 120f, cy + 350f, cx - 70f, cy + 480f)),

        ColorableRegion(5, Path().apply {
            addRect(Rect(cx + 70f, cy + 350f, cx + 120f, cy + 480f))
        }, Rect(cx + 70f, cy + 350f, cx + 120f, cy + 480f)),

        // Queue avec touffe
        ColorableRegion(6, Path().apply {
            moveTo(cx + 130f, cy + 340f)
            lineTo(cx + 140f, cy + 340f)
            lineTo(cx + 200f, cy + 450f)
            // Touffe au bout
            addOval(Rect(cx + 180f, cy + 430f, cx + 220f, cy + 470f))
        }, Rect(cx + 130f, cy + 340f, cx + 220f, cy + 470f))
    )
}

// Ours
fun createBear(cx: Float, cy: Float): List<ColorableRegion> {
    return listOf(
        // Tête
        ColorableRegion(1, Path().apply {
            addOval(Rect(cx - 140f, cy - 190f, cx + 140f, cy + 90f))
        }, Rect(cx - 140f, cy - 190f, cx + 140f, cy + 90f)),

        // Oreille gauche (ronde)
        ColorableRegion(2, Path().apply {
            addOval(Rect(cx - 160f, cy - 250f, cx - 80f, cy - 170f))
        }, Rect(cx - 160f, cy - 250f, cx - 80f, cy - 170f)),

        // Oreille droite (ronde)
        ColorableRegion(3, Path().apply {
            addOval(Rect(cx + 80f, cy - 250f, cx + 160f, cy - 170f))
        }, Rect(cx + 80f, cy - 250f, cx + 160f, cy - 170f)),

        // Corps large
        ColorableRegion(4, Path().apply {
            addOval(Rect(cx - 160f, cy + 60f, cx + 160f, cy + 400f))
        }, Rect(cx - 160f, cy + 60f, cx + 160f, cy + 400f)),

        // Pattes (larges)
        ColorableRegion(5, Path().apply {
            addRect(Rect(cx - 140f, cy + 360f, cx - 70f, cy + 500f))
        }, Rect(cx - 140f, cy + 360f, cx - 70f, cy + 500f)),

        ColorableRegion(6, Path().apply {
            addRect(Rect(cx + 70f, cy + 360f, cx + 140f, cy + 500f))
        }, Rect(cx + 70f, cy + 360f, cx + 140f, cy + 500f))
    )
}

// Lapin avec longues oreilles
fun createRabbit(cx: Float, cy: Float): List<ColorableRegion> {
    return listOf(
        // Tête
        ColorableRegion(1, Path().apply {
            addOval(Rect(cx - 120f, cy - 160f, cx + 120f, cy + 80f))
        }, Rect(cx - 120f, cy - 160f, cx + 120f, cy + 80f)),

        // Oreille gauche (longue)
        ColorableRegion(2, Path().apply {
            addOval(Rect(cx - 100f, cy - 450f, cx - 40f, cy - 140f))
        }, Rect(cx - 100f, cy - 450f, cx - 40f, cy - 140f)),

        // Oreille droite (longue)
        ColorableRegion(3, Path().apply {
            addOval(Rect(cx + 40f, cy - 450f, cx + 100f, cy - 140f))
        }, Rect(cx + 40f, cy - 450f, cx + 100f, cy - 140f)),

        // Corps rond
        ColorableRegion(4, Path().apply {
            addOval(Rect(cx - 140f, cy + 60f, cx + 140f, cy + 350f))
        }, Rect(cx - 140f, cy + 60f, cx + 140f, cy + 350f)),

        // Pattes arrières
        ColorableRegion(5, Path().apply {
            addOval(Rect(cx - 130f, cy + 320f, cx - 40f, cy + 450f))
        }, Rect(cx - 130f, cy + 320f, cx - 40f, cy + 450f)),

        ColorableRegion(6, Path().apply {
            addOval(Rect(cx + 40f, cy + 320f, cx + 130f, cy + 450f))
        }, Rect(cx + 40f, cy + 320f, cx + 130f, cy + 450f)),

        // Queue pompom
        ColorableRegion(7, Path().apply {
            addOval(Rect(cx + 110f, cy + 250f, cx + 180f, cy + 320f))
        }, Rect(cx + 110f, cy + 250f, cx + 180f, cy + 320f))
    )
}

// Poisson
fun createFish(cx: Float, cy: Float): List<ColorableRegion> {
    return listOf(
        // Corps
        ColorableRegion(1, Path().apply {
            addOval(Rect(cx - 180f, cy - 100f, cx + 80f, cy + 100f))
        }, Rect(cx - 180f, cy - 100f, cx + 80f, cy + 100f)),

        // Nageoire dorsale
        ColorableRegion(2, Path().apply {
            moveTo(cx - 80f, cy - 100f)
            lineTo(cx, cy - 200f)
            lineTo(cx + 20f, cy - 90f)
            close()
        }, Rect(cx - 80f, cy - 200f, cx + 20f, cy - 90f)),

        // Queue
        ColorableRegion(3, Path().apply {
            moveTo(cx + 80f, cy - 50f)
            lineTo(cx + 200f, cy - 120f)
            lineTo(cx + 200f, cy + 120f)
            lineTo(cx + 80f, cy + 50f)
            close()
        }, Rect(cx + 80f, cy - 120f, cx + 200f, cy + 120f)),

        // Nageoire ventrale
        ColorableRegion(4, Path().apply {
            moveTo(cx - 60f, cy + 100f)
            lineTo(cx - 40f, cy + 180f)
            lineTo(cx + 10f, cy + 100f)
            close()
        }, Rect(cx - 60f, cy + 100f, cx + 10f, cy + 180f))
    )
}

// Oiseau
fun createBird(cx: Float, cy: Float): List<ColorableRegion> {
    return listOf(
        // Corps
        ColorableRegion(1, Path().apply {
            addOval(Rect(cx - 100f, cy - 50f, cx + 100f, cy + 150f))
        }, Rect(cx - 100f, cy - 50f, cx + 100f, cy + 150f)),

        // Tête
        ColorableRegion(2, Path().apply {
            addOval(Rect(cx - 80f, cy - 180f, cx + 80f, cy - 20f))
        }, Rect(cx - 80f, cy - 180f, cx + 80f, cy - 20f)),

        // Aile gauche
        ColorableRegion(3, Path().apply {
            moveTo(cx - 100f, cy + 30f)
            quadraticTo(cx - 250f, cy, cx - 200f, cy + 150f)
            lineTo(cx - 80f, cy + 100f)
            close()
        }, Rect(cx - 250f, cy, cx - 80f, cy + 150f)),

        // Aile droite
        ColorableRegion(4, Path().apply {
            moveTo(cx + 100f, cy + 30f)
            quadraticTo(cx + 250f, cy, cx + 200f, cy + 150f)
            lineTo(cx + 80f, cy + 100f)
            close()
        }, Rect(cx + 80f, cy, cx + 250f, cy + 150f)),

        // Bec
        ColorableRegion(5, Path().apply {
            moveTo(cx, cy - 90f)
            lineTo(cx + 80f, cy - 100f)
            lineTo(cx + 20f, cy - 70f)
            close()
        }, Rect(cx, cy - 100f, cx + 80f, cy - 70f)),

        // Queue
        ColorableRegion(6, Path().apply {
            moveTo(cx - 30f, cy + 150f)
            lineTo(cx + 30f, cy + 150f)
            lineTo(cx + 10f, cy + 280f)
            lineTo(cx - 10f, cy + 280f)
            close()
        }, Rect(cx - 30f, cy + 150f, cx + 30f, cy + 280f))
    )
}

// Papillon
fun createButterfly(cx: Float, cy: Float): List<ColorableRegion> {
    return listOf(
        // Corps central
        ColorableRegion(1, Path().apply {
            addOval(Rect(cx - 30f, cy - 150f, cx + 30f, cy + 150f))
        }, Rect(cx - 30f, cy - 150f, cx + 30f, cy + 150f)),

        // Aile supérieure gauche
        ColorableRegion(2, Path().apply {
            moveTo(cx - 30f, cy - 100f)
            quadraticTo(cx - 200f, cy - 250f, cx - 180f, cy - 50f)
            quadraticTo(cx - 100f, cy - 80f, cx - 30f, cy - 50f)
            close()
        }, Rect(cx - 200f, cy - 250f, cx - 30f, cy - 50f)),

        // Aile supérieure droite
        ColorableRegion(3, Path().apply {
            moveTo(cx + 30f, cy - 100f)
            quadraticTo(cx + 200f, cy - 250f, cx + 180f, cy - 50f)
            quadraticTo(cx + 100f, cy - 80f, cx + 30f, cy - 50f)
            close()
        }, Rect(cx + 30f, cy - 250f, cx + 200f, cy - 50f)),

        // Aile inférieure gauche
        ColorableRegion(4, Path().apply {
            moveTo(cx - 30f, cy + 50f)
            quadraticTo(cx - 150f, cy + 200f, cx - 120f, cy + 80f)
            quadraticTo(cx - 70f, cy + 100f, cx - 30f, cy + 100f)
            close()
        }, Rect(cx - 150f, cy + 50f, cx - 30f, cy + 200f)),

        // Aile inférieure droite
        ColorableRegion(5, Path().apply {
            moveTo(cx + 30f, cy + 50f)
            quadraticTo(cx + 150f, cy + 200f, cx + 120f, cy + 80f)
            quadraticTo(cx + 70f, cy + 100f, cx + 30f, cy + 100f)
            close()
        }, Rect(cx + 30f, cy + 50f, cx + 150f, cy + 200f)),

        // Antennes gauche
        ColorableRegion(6, Path().apply {
            moveTo(cx - 20f, cy - 150f)
            quadraticTo(cx - 50f, cy - 200f, cx - 40f, cy - 220f)
            lineTo(cx - 30f, cy - 210f)
            quadraticTo(cx - 35f, cy - 190f, cx - 15f, cy - 145f)
            close()
        }, Rect(cx - 50f, cy - 220f, cx - 15f, cy - 145f)),

        // Antennes droite
        ColorableRegion(7, Path().apply {
            moveTo(cx + 20f, cy - 150f)
            quadraticTo(cx + 50f, cy - 200f, cx + 40f, cy - 220f)
            lineTo(cx + 30f, cy - 210f)
            quadraticTo(cx + 35f, cy - 190f, cx + 15f, cy - 145f)
            close()
        }, Rect(cx + 15f, cy - 220f, cx + 50f, cy - 145f))
    )
}
