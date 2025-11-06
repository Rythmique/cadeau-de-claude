package com.example.coloringkids.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.coloringkids.components.ColoringCanvas
import com.example.coloringkids.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColoringScreen(
    navController: NavController,
    type: String,
    item: String
) {
    var selectedColor by remember { mutableStateOf(Red) }
    var clearTrigger by remember { mutableStateOf(0) }

    val availableColors = listOf(
        Red, Blue, Green, Yellow, Orange,
        Purple, Pink, Brown, LightBlue, LightGreen,
        Color(0xFF795548), // Dark Brown
        Color(0xFF9E9E9E), // Gray
        Color.Black
    )

    val title = if (type == "alphabet") {
        "Lettre $item"
    } else {
        item
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        title,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Retour")
                    }
                },
                actions = {
                    IconButton(onClick = { clearTrigger++ }) {
                        Icon(
                            Icons.Default.Delete,
                            contentDescription = "Effacer",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = if (type == "alphabet") Color(0xFFFFE082) else Color(0xFFA5D6A7),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F5F5))
                .padding(padding)
        ) {
            // Color palette
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(16.dp)
                ) {
                    Text(
                        text = "🎨 Choisis une couleur",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF424242),
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(availableColors) { color ->
                            ColorButton(
                                color = color,
                                isSelected = color == selectedColor,
                                onClick = { selectedColor = color }
                            )
                        }
                    }
                }
            }

            // Drawing canvas
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ) {
                    ColoringCanvas(
                        item = item,
                        type = type,
                        selectedColor = selectedColor,
                        onClear = { clearTrigger++ },
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ColorButton(
    color: Color,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(56.dp)
            .clip(CircleShape)
            .background(color)
            .border(
                width = if (isSelected) 4.dp else 2.dp,
                color = if (isSelected) Color(0xFF1976D2) else Color(0xFFBDBDBD),
                shape = CircleShape
            )
            .clickable(onClick = onClick)
    )
}
