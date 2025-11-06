package com.example.coloringkids.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.sp

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
    var regions by remember { mutableStateOf(createRegionsForItem(item, type)) }
    val textMeasurer = rememberTextMeasurer()

    Canvas(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(selectedColor) {
                detectTapGestures { offset ->
                    // Find which region was tapped
                    val tappedRegion = regions.find { region ->
                        region.bounds.contains(offset)
                    }
                    tappedRegion?.let {
                        // Update the color of the tapped region
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

        if (type == "alphabet") {
            // Draw the letter with colored regions
            val textSize = 500.sp
            val textStyle = TextStyle(
                fontSize = textSize,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            val textLayoutResult = textMeasurer.measure(item, textStyle)
            val textWidth = textLayoutResult.size.width
            val textHeight = textLayoutResult.size.height

            val x = centerX - textWidth / 2f
            val y = centerY - textHeight / 2f

            // Draw filled regions
            regions.forEach { region ->
                drawPath(
                    path = region.path,
                    color = region.fillColor
                )
            }

            // Draw the letter outline on top
            drawText(
                textMeasurer = textMeasurer,
                text = item,
                style = textStyle.copy(color = Color.Transparent),
                topLeft = Offset(x, y)
            )

            // Draw outline
            val outlinePath = Path().apply {
                addRect(Rect(x, y, x + textWidth, y + textHeight))
            }
            drawPath(
                path = outlinePath,
                color = Color.Black,
                style = Stroke(width = 8f)
            )
        } else {
            // Draw animal shape with regions
            regions.forEach { region ->
                drawPath(
                    path = region.path,
                    color = region.fillColor
                )
                drawPath(
                    path = region.path,
                    color = Color.Black,
                    style = Stroke(
                        width = 4f,
                        pathEffect = PathEffect.cornerPathEffect(8f)
                    )
                )
            }
        }
    }

    LaunchedEffect(Unit) {
        // Trigger onClear callback setup
        // This is handled by the parent ColoringScreen
    }
}

fun createRegionsForItem(item: String, type: String): List<ColorableRegion> {
    // For simplicity, we'll create simple geometric regions
    // In a real app, you'd have pre-defined paths for each letter/animal

    return if (type == "alphabet") {
        // Create regions for letter (simplified - just rectangles as example)
        listOf(
            ColorableRegion(
                id = 1,
                path = Path().apply {
                    addRect(Rect(100f, 200f, 300f, 800f))
                },
                bounds = Rect(100f, 200f, 300f, 800f)
            ),
            ColorableRegion(
                id = 2,
                path = Path().apply {
                    addRect(Rect(300f, 300f, 600f, 500f))
                },
                bounds = Rect(300f, 300f, 600f, 500f)
            ),
            ColorableRegion(
                id = 3,
                path = Path().apply {
                    addRect(Rect(300f, 550f, 600f, 750f))
                },
                bounds = Rect(300f, 550f, 600f, 750f)
            )
        )
    } else {
        // Create regions for animal (simplified geometric shapes)
        createAnimalRegions(item)
    }
}

fun createAnimalRegions(animalName: String): List<ColorableRegion> {
    // Create simple shapes for animals
    // In a production app, these would be detailed SVG paths
    return listOf(
        // Body
        ColorableRegion(
            id = 1,
            path = Path().apply {
                addOval(Rect(300f, 400f, 700f, 800f))
            },
            bounds = Rect(300f, 400f, 700f, 800f)
        ),
        // Head
        ColorableRegion(
            id = 2,
            path = Path().apply {
                addOval(Rect(350f, 200f, 650f, 500f))
            },
            bounds = Rect(350f, 200f, 650f, 500f)
        ),
        // Left ear
        ColorableRegion(
            id = 3,
            path = Path().apply {
                addOval(Rect(320f, 150f, 420f, 300f))
            },
            bounds = Rect(320f, 150f, 420f, 300f)
        ),
        // Right ear
        ColorableRegion(
            id = 4,
            path = Path().apply {
                addOval(Rect(580f, 150f, 680f, 300f))
            },
            bounds = Rect(580f, 150f, 680f, 300f)
        ),
        // Left leg
        ColorableRegion(
            id = 5,
            path = Path().apply {
                addRect(Rect(350f, 800f, 450f, 1000f))
            },
            bounds = Rect(350f, 800f, 450f, 1000f)
        ),
        // Right leg
        ColorableRegion(
            id = 6,
            path = Path().apply {
                addRect(Rect(550f, 800f, 650f, 1000f))
            },
            bounds = Rect(550f, 800f, 650f, 1000f)
        )
    )
}
