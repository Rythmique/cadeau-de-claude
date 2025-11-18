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
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput

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
                style = Stroke(width = 6f)
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

// Lettre A
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

fun createLetterB(cx: Float, cy: Float, scale: Float): List<ColorableRegion> = listOf(
    ColorableRegion(1, Path().apply {
        addRect(Rect(cx - 25*scale, cy - 30*scale, cx - 5*scale, cy + 30*scale))
    }, Rect(cx - 25*scale, cy - 30*scale, cx - 5*scale, cy + 30*scale)),
    ColorableRegion(2, Path().apply {
        addOval(Rect(cx - 5*scale, cy - 30*scale, cx + 25*scale, cy - 2*scale))
    }, Rect(cx - 5*scale, cy - 30*scale, cx + 25*scale, cy - 2*scale)),
    ColorableRegion(3, Path().apply {
        addOval(Rect(cx - 5*scale, cy + 2*scale, cx + 28*scale, cy + 30*scale))
    }, Rect(cx - 5*scale, cy + 2*scale, cx + 28*scale, cy + 30*scale))
)

fun createLetterC(cx: Float, cy: Float, scale: Float): List<ColorableRegion> = listOf(
    ColorableRegion(1, Path().apply {
        addArc(
            Rect(cx - 30*scale, cy - 30*scale, cx + 30*scale, cy + 30*scale),
            startAngleDegrees = 45f,
            sweepAngleDegrees = 270f
        )
    }, Rect(cx - 30*scale, cy - 30*scale, cx + 30*scale, cy + 30*scale))
)

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

fun createLetterG(cx: Float, cy: Float, scale: Float): List<ColorableRegion> =
    createLetterC(cx, cy, scale) + listOf(
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
        addOval(Rect(cx - 20*scale, cy + 10*scale, cx + 15*scale, cy + 30*scale))
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
        addOval(Rect(cx - 5*scale, cy - 30*scale, cx + 25*scale, cy + 5*scale))
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
        addOval(Rect(cx - 5*scale, cy - 30*scale, cx + 25*scale, cy + 5*scale))
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
        addOval(Rect(cx - 25*scale, cy - 30*scale, cx + 25*scale, cy - 5*scale))
    }, Rect(cx - 25*scale, cy - 30*scale, cx + 25*scale, cy - 5*scale)),
    ColorableRegion(2, Path().apply {
        addOval(Rect(cx - 25*scale, cy + 5*scale, cx + 25*scale, cy + 30*scale))
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
        addRect(Rect(cx - 30*scale, cy - 30*scale, cx - 10*scale, cy + 20*scale))
    }, Rect(cx - 30*scale, cy - 30*scale, cx - 10*scale, cy + 20*scale)),
    ColorableRegion(2, Path().apply {
        addRect(Rect(cx + 10*scale, cy - 30*scale, cx + 30*scale, cy + 20*scale))
    }, Rect(cx + 10*scale, cy - 30*scale, cx + 30*scale, cy + 20*scale)),
    ColorableRegion(3, Path().apply {
        addOval(Rect(cx - 25*scale, cy + 10*scale, cx + 25*scale, cy + 30*scale))
    }, Rect(cx - 25*scale, cy + 10*scale, cx + 25*scale, cy + 30*scale))
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
        addRect(Rect(cx - 35*scale, cy - 30*scale, cx - 20*scale, cy + 30*scale))
    }, Rect(cx - 35*scale, cy - 30*scale, cx - 20*scale, cy + 30*scale)),
    ColorableRegion(2, Path().apply {
        moveTo(cx - 15*scale, cy - 30*scale)
        lineTo(cx - 5*scale, cy - 30*scale)
        lineTo(cx - 10*scale, cy + 15*scale)
        lineTo(cx - 18*scale, cy + 15*scale)
        close()
    }, Rect(cx - 18*scale, cy - 30*scale, cx - 5*scale, cy + 15*scale)),
    ColorableRegion(3, Path().apply {
        moveTo(cx + 5*scale, cy - 30*scale)
        lineTo(cx + 15*scale, cy - 30*scale)
        lineTo(cx + 18*scale, cy + 15*scale)
        lineTo(cx + 10*scale, cy + 15*scale)
        close()
    }, Rect(cx + 5*scale, cy - 30*scale, cx + 18*scale, cy + 15*scale)),
    ColorableRegion(4, Path().apply {
        addRect(Rect(cx + 20*scale, cy - 30*scale, cx + 35*scale, cy + 30*scale))
    }, Rect(cx + 20*scale, cy - 30*scale, cx + 35*scale, cy + 30*scale))
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

// ANIMAUX

fun createAnimalRegions(animalName: String): List<ColorableRegion> {
    val cx = 500f
    val cy = 600f

    return when (animalName.lowercase()) {
        "chat" -> createCat(cx, cy)
        "chien" -> createDog(cx, cy)
        "lion" -> createLion(cx, cy)
        "tigre" -> createTiger(cx, cy)
        "ours" -> createBear(cx, cy)
        "panda" -> createPanda(cx, cy)
        "lapin" -> createRabbit(cx, cy)
        "renard" -> createFox(cx, cy)
        "éléphant" -> createElephant(cx, cy)
        "elephant" -> createElephant(cx, cy)
        "girafe" -> createGiraffe(cx, cy)
        "zèbre" -> createZebra(cx, cy)
        "zebre" -> createZebra(cx, cy)
        "singe" -> createMonkey(cx, cy)
        "poisson" -> createFish(cx, cy)
        "dauphin" -> createDolphin(cx, cy)
        "baleine" -> createWhale(cx, cy)
        "oiseau" -> createBird(cx, cy)
        "papillon" -> createButterfly(cx, cy)
        "abeille" -> createBee(cx, cy)
        "grenouille" -> createFrog(cx, cy)
        "tortue" -> createTurtle(cx, cy)
        else -> createCat(cx, cy)
    }
}

fun createCat(cx: Float, cy: Float): List<ColorableRegion> {
    return listOf(
        // Tête
        ColorableRegion(1, Path().apply {
            addOval(Rect(cx - 150f, cy - 200f, cx + 150f, cy + 100f))
        }, Rect(cx - 150f, cy - 200f, cx + 150f, cy + 100f)),
        // Oreille gauche
        ColorableRegion(2, Path().apply {
            moveTo(cx - 120f, cy - 180f)
            lineTo(cx - 180f, cy - 300f)
            lineTo(cx - 60f, cy - 220f)
            close()
        }, Rect(cx - 180f, cy - 300f, cx - 60f, cy - 180f)),
        // Oreille droite
        ColorableRegion(3, Path().apply {
            moveTo(cx + 120f, cy - 180f)
            lineTo(cx + 180f, cy - 300f)
            lineTo(cx + 60f, cy - 220f)
            close()
        }, Rect(cx + 60f, cy - 300f, cx + 180f, cy - 180f)),
        // Corps
        ColorableRegion(4, Path().apply {
            addOval(Rect(cx - 120f, cy + 80f, cx + 120f, cy + 350f))
        }, Rect(cx - 120f, cy + 80f, cx + 120f, cy + 350f)),
        // Patte gauche
        ColorableRegion(5, Path().apply {
            addRect(Rect(cx - 100f, cy + 320f, cx - 50f, cy + 480f))
        }, Rect(cx - 100f, cy + 320f, cx - 50f, cy + 480f)),
        // Patte droite
        ColorableRegion(6, Path().apply {
            addRect(Rect(cx + 50f, cy + 320f, cx + 100f, cy + 480f))
        }, Rect(cx + 50f, cy + 320f, cx + 100f, cy + 480f)),
        // Queue
        ColorableRegion(7, Path().apply {
            moveTo(cx + 100f, cy + 300f)
            lineTo(cx + 200f, cy + 250f)
            lineTo(cx + 180f, cy + 150f)
            lineTo(cx + 120f, cy + 290f)
            close()
        }, Rect(cx + 100f, cy + 150f, cx + 200f, cy + 300f))
    )
}

fun createDog(cx: Float, cy: Float): List<ColorableRegion> {
    return listOf(
        // Tête
        ColorableRegion(1, Path().apply {
            addOval(Rect(cx - 130f, cy - 180f, cx + 130f, cy + 80f))
        }, Rect(cx - 130f, cy - 180f, cx + 130f, cy + 80f)),
        // Oreille gauche
        ColorableRegion(2, Path().apply {
            addOval(Rect(cx - 180f, cy - 150f, cx - 100f, cy + 50f))
        }, Rect(cx - 180f, cy - 150f, cx - 100f, cy + 50f)),
        // Oreille droite
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
        // Pattes
        ColorableRegion(6, Path().apply {
            addRect(Rect(cx - 120f, cy + 360f, cx - 70f, cy + 500f))
        }, Rect(cx - 120f, cy + 360f, cx - 70f, cy + 500f)),
        ColorableRegion(7, Path().apply {
            addRect(Rect(cx + 70f, cy + 360f, cx + 120f, cy + 500f))
        }, Rect(cx + 70f, cy + 360f, cx + 120f, cy + 500f))
    )
}

fun createLion(cx: Float, cy: Float): List<ColorableRegion> {
    return listOf(
        // Crinière
        ColorableRegion(1, Path().apply {
            addOval(Rect(cx - 200f, cy - 250f, cx + 200f, cy + 150f))
        }, Rect(cx - 200f, cy - 250f, cx + 200f, cy + 150f)),
        // Tête
        ColorableRegion(2, Path().apply {
            addOval(Rect(cx - 120f, cy - 180f, cx + 120f, cy + 80f))
        }, Rect(cx - 120f, cy - 180f, cx + 120f, cy + 80f)),
        // Corps
        ColorableRegion(3, Path().apply {
            addOval(Rect(cx - 140f, cy + 60f, cx + 140f, cy + 380f))
        }, Rect(cx - 140f, cy + 60f, cx + 140f, cy + 380f)),
        // Pattes
        ColorableRegion(4, Path().apply {
            addRect(Rect(cx - 120f, cy + 350f, cx - 70f, cy + 480f))
        }, Rect(cx - 120f, cy + 350f, cx - 70f, cy + 480f)),
        ColorableRegion(5, Path().apply {
            addRect(Rect(cx + 70f, cy + 350f, cx + 120f, cy + 480f))
        }, Rect(cx + 70f, cy + 350f, cx + 120f, cy + 480f)),
        // Queue
        ColorableRegion(6, Path().apply {
            moveTo(cx + 130f, cy + 340f)
            lineTo(cx + 140f, cy + 340f)
            lineTo(cx + 200f, cy + 450f)
            lineTo(cx + 190f, cy + 450f)
            close()
        }, Rect(cx + 130f, cy + 340f, cx + 200f, cy + 450f))
    )
}

fun createTiger(cx: Float, cy: Float) = createCat(cx, cy)
fun createPanda(cx: Float, cy: Float) = createBear(cx, cy)
fun createFox(cx: Float, cy: Float) = createDog(cx, cy)

fun createBear(cx: Float, cy: Float): List<ColorableRegion> {
    return listOf(
        // Tête
        ColorableRegion(1, Path().apply {
            addOval(Rect(cx - 140f, cy - 190f, cx + 140f, cy + 90f))
        }, Rect(cx - 140f, cy - 190f, cx + 140f, cy + 90f)),
        // Oreilles
        ColorableRegion(2, Path().apply {
            addOval(Rect(cx - 160f, cy - 250f, cx - 80f, cy - 170f))
        }, Rect(cx - 160f, cy - 250f, cx - 80f, cy - 170f)),
        ColorableRegion(3, Path().apply {
            addOval(Rect(cx + 80f, cy - 250f, cx + 160f, cy - 170f))
        }, Rect(cx + 80f, cy - 250f, cx + 160f, cy - 170f)),
        // Corps
        ColorableRegion(4, Path().apply {
            addOval(Rect(cx - 160f, cy + 60f, cx + 160f, cy + 400f))
        }, Rect(cx - 160f, cy + 60f, cx + 160f, cy + 400f)),
        // Pattes
        ColorableRegion(5, Path().apply {
            addRect(Rect(cx - 140f, cy + 360f, cx - 70f, cy + 500f))
        }, Rect(cx - 140f, cy + 360f, cx - 70f, cy + 500f)),
        ColorableRegion(6, Path().apply {
            addRect(Rect(cx + 70f, cy + 360f, cx + 140f, cy + 500f))
        }, Rect(cx + 70f, cy + 360f, cx + 140f, cy + 500f))
    )
}

fun createRabbit(cx: Float, cy: Float): List<ColorableRegion> {
    return listOf(
        // Tête
        ColorableRegion(1, Path().apply {
            addOval(Rect(cx - 120f, cy - 160f, cx + 120f, cy + 80f))
        }, Rect(cx - 120f, cy - 160f, cx + 120f, cy + 80f)),
        // Longues oreilles
        ColorableRegion(2, Path().apply {
            addOval(Rect(cx - 100f, cy - 450f, cx - 40f, cy - 140f))
        }, Rect(cx - 100f, cy - 450f, cx - 40f, cy - 140f)),
        ColorableRegion(3, Path().apply {
            addOval(Rect(cx + 40f, cy - 450f, cx + 100f, cy - 140f))
        }, Rect(cx + 40f, cy - 450f, cx + 100f, cy - 140f)),
        // Corps
        ColorableRegion(4, Path().apply {
            addOval(Rect(cx - 140f, cy + 60f, cx + 140f, cy + 350f))
        }, Rect(cx - 140f, cy + 60f, cx + 140f, cy + 350f)),
        // Pattes
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

fun createElephant(cx: Float, cy: Float): List<ColorableRegion> {
    return listOf(
        // Tête
        ColorableRegion(1, Path().apply {
            addOval(Rect(cx - 160f, cy - 200f, cx + 160f, cy + 100f))
        }, Rect(cx - 160f, cy - 200f, cx + 160f, cy + 100f)),
        // Oreilles
        ColorableRegion(2, Path().apply {
            addOval(Rect(cx - 230f, cy - 180f, cx - 140f, cy + 80f))
        }, Rect(cx - 230f, cy - 180f, cx - 140f, cy + 80f)),
        ColorableRegion(3, Path().apply {
            addOval(Rect(cx + 140f, cy - 180f, cx + 230f, cy + 80f))
        }, Rect(cx + 140f, cy - 180f, cx + 230f, cy + 80f)),
        // Trompe
        ColorableRegion(4, Path().apply {
            addRect(Rect(cx - 40f, cy + 80f, cx + 40f, cy + 350f))
        }, Rect(cx - 40f, cy + 80f, cx + 40f, cy + 350f)),
        // Corps
        ColorableRegion(5, Path().apply {
            addOval(Rect(cx - 180f, cy + 50f, cx + 180f, cy + 400f))
        }, Rect(cx - 180f, cy + 50f, cx + 180f, cy + 400f)),
        // Pattes
        ColorableRegion(6, Path().apply {
            addRect(Rect(cx - 150f, cy + 380f, cx - 100f, cy + 530f))
        }, Rect(cx - 150f, cy + 380f, cx - 100f, cy + 530f)),
        ColorableRegion(7, Path().apply {
            addRect(Rect(cx + 100f, cy + 380f, cx + 150f, cy + 530f))
        }, Rect(cx + 100f, cy + 380f, cx + 150f, cy + 530f))
    )
}

fun createGiraffe(cx: Float, cy: Float): List<ColorableRegion> {
    return listOf(
        // Tête
        ColorableRegion(1, Path().apply {
            addOval(Rect(cx - 80f, cy - 500f, cx + 80f, cy - 400f))
        }, Rect(cx - 80f, cy - 500f, cx + 80f, cy - 400f)),
        // Long cou
        ColorableRegion(2, Path().apply {
            addRect(Rect(cx - 50f, cy - 400f, cx + 50f, cy + 50f))
        }, Rect(cx - 50f, cy - 400f, cx + 50f, cy + 50f)),
        // Corps
        ColorableRegion(3, Path().apply {
            addOval(Rect(cx - 140f, cy + 20f, cx + 140f, cy + 320f))
        }, Rect(cx - 140f, cy + 20f, cx + 140f, cy + 320f)),
        // Pattes longues
        ColorableRegion(4, Path().apply {
            addRect(Rect(cx - 120f, cy + 300f, cx - 80f, cy + 550f))
        }, Rect(cx - 120f, cy + 300f, cx - 80f, cy + 550f)),
        ColorableRegion(5, Path().apply {
            addRect(Rect(cx + 80f, cy + 300f, cx + 120f, cy + 550f))
        }, Rect(cx + 80f, cy + 300f, cx + 120f, cy + 550f))
    )
}

fun createZebra(cx: Float, cy: Float) = createDog(cx, cy)
fun createMonkey(cx: Float, cy: Float) = createCat(cx, cy)

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

fun createDolphin(cx: Float, cy: Float) = createFish(cx, cy)
fun createWhale(cx: Float, cy: Float) = createFish(cx, cy)

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
            lineTo(cx - 250f, cy)
            lineTo(cx - 200f, cy + 150f)
            lineTo(cx - 80f, cy + 100f)
            close()
        }, Rect(cx - 250f, cy, cx - 80f, cy + 150f)),
        // Aile droite
        ColorableRegion(4, Path().apply {
            moveTo(cx + 100f, cy + 30f)
            lineTo(cx + 250f, cy)
            lineTo(cx + 200f, cy + 150f)
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

fun createButterfly(cx: Float, cy: Float): List<ColorableRegion> {
    return listOf(
        // Corps
        ColorableRegion(1, Path().apply {
            addOval(Rect(cx - 30f, cy - 150f, cx + 30f, cy + 150f))
        }, Rect(cx - 30f, cy - 150f, cx + 30f, cy + 150f)),
        // Aile supérieure gauche
        ColorableRegion(2, Path().apply {
            moveTo(cx - 30f, cy - 100f)
            lineTo(cx - 200f, cy - 250f)
            lineTo(cx - 180f, cy - 50f)
            lineTo(cx - 30f, cy - 50f)
            close()
        }, Rect(cx - 200f, cy - 250f, cx - 30f, cy - 50f)),
        // Aile supérieure droite
        ColorableRegion(3, Path().apply {
            moveTo(cx + 30f, cy - 100f)
            lineTo(cx + 200f, cy - 250f)
            lineTo(cx + 180f, cy - 50f)
            lineTo(cx + 30f, cy - 50f)
            close()
        }, Rect(cx + 30f, cy - 250f, cx + 200f, cy - 50f)),
        // Aile inférieure gauche
        ColorableRegion(4, Path().apply {
            moveTo(cx - 30f, cy + 50f)
            lineTo(cx - 150f, cy + 200f)
            lineTo(cx - 120f, cy + 80f)
            lineTo(cx - 30f, cy + 100f)
            close()
        }, Rect(cx - 150f, cy + 50f, cx - 30f, cy + 200f)),
        // Aile inférieure droite
        ColorableRegion(5, Path().apply {
            moveTo(cx + 30f, cy + 50f)
            lineTo(cx + 150f, cy + 200f)
            lineTo(cx + 120f, cy + 80f)
            lineTo(cx + 30f, cy + 100f)
            close()
        }, Rect(cx + 30f, cy + 50f, cx + 150f, cy + 200f))
    )
}

fun createBee(cx: Float, cy: Float) = createButterfly(cx, cy)

fun createFrog(cx: Float, cy: Float): List<ColorableRegion> {
    return listOf(
        // Corps
        ColorableRegion(1, Path().apply {
            addOval(Rect(cx - 140f, cy - 120f, cx + 140f, cy + 150f))
        }, Rect(cx - 140f, cy - 120f, cx + 140f, cy + 150f)),
        // Tête/Yeux
        ColorableRegion(2, Path().apply {
            addOval(Rect(cx - 100f, cy - 200f, cx - 40f, cy - 100f))
        }, Rect(cx - 100f, cy - 200f, cx - 40f, cy - 100f)),
        ColorableRegion(3, Path().apply {
            addOval(Rect(cx + 40f, cy - 200f, cx + 100f, cy - 100f))
        }, Rect(cx + 40f, cy - 200f, cx + 100f, cy - 100f)),
        // Pattes avant
        ColorableRegion(4, Path().apply {
            addOval(Rect(cx - 180f, cy, cx - 120f, cy + 100f))
        }, Rect(cx - 180f, cy, cx - 120f, cy + 100f)),
        ColorableRegion(5, Path().apply {
            addOval(Rect(cx + 120f, cy, cx + 180f, cy + 100f))
        }, Rect(cx + 120f, cy, cx + 180f, cy + 100f)),
        // Pattes arrières
        ColorableRegion(6, Path().apply {
            addOval(Rect(cx - 200f, cy + 120f, cx - 100f, cy + 200f))
        }, Rect(cx - 200f, cy + 120f, cx - 100f, cy + 200f)),
        ColorableRegion(7, Path().apply {
            addOval(Rect(cx + 100f, cy + 120f, cx + 200f, cy + 200f))
        }, Rect(cx + 100f, cy + 120f, cx + 200f, cy + 200f))
    )
}

fun createTurtle(cx: Float, cy: Float): List<ColorableRegion> {
    return listOf(
        // Carapace
        ColorableRegion(1, Path().apply {
            addOval(Rect(cx - 160f, cy - 100f, cx + 160f, cy + 200f))
        }, Rect(cx - 160f, cy - 100f, cx + 160f, cy + 200f)),
        // Tête
        ColorableRegion(2, Path().apply {
            addOval(Rect(cx - 80f, cy - 200f, cx + 80f, cy - 80f))
        }, Rect(cx - 80f, cy - 200f, cx + 80f, cy - 80f)),
        // Pattes
        ColorableRegion(3, Path().apply {
            addOval(Rect(cx - 180f, cy - 50f, cx - 140f, cy + 50f))
        }, Rect(cx - 180f, cy - 50f, cx - 140f, cy + 50f)),
        ColorableRegion(4, Path().apply {
            addOval(Rect(cx + 140f, cy - 50f, cx + 180f, cy + 50f))
        }, Rect(cx + 140f, cy - 50f, cx + 180f, cy + 50f)),
        ColorableRegion(5, Path().apply {
            addOval(Rect(cx - 180f, cy + 120f, cx - 140f, cy + 220f))
        }, Rect(cx - 180f, cy + 120f, cx - 140f, cy + 220f)),
        ColorableRegion(6, Path().apply {
            addOval(Rect(cx + 140f, cy + 120f, cx + 180f, cy + 220f))
        }, Rect(cx + 140f, cy + 120f, cx + 180f, cy + 220f)),
        // Queue
        ColorableRegion(7, Path().apply {
            moveTo(cx, cy + 200f)
            lineTo(cx - 20f, cy + 280f)
            lineTo(cx + 20f, cy + 280f)
            close()
        }, Rect(cx - 20f, cy + 200f, cx + 20f, cy + 280f))
    )
}
