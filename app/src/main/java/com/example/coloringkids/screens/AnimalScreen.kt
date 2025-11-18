package com.example.coloringkids.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.coloringkids.Screen

data class Animal(val name: String, val emoji: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimalScreen(navController: NavController) {
    val animals = listOf(
        Animal("Chat", "🐱"),
        Animal("Chien", "🐶"),
        Animal("Lion", "🦁"),
        Animal("Tigre", "🐯"),
        Animal("Ours", "🐻"),
        Animal("Panda", "🐼"),
        Animal("Lapin", "🐰"),
        Animal("Renard", "🦊"),
        Animal("Éléphant", "🐘"),
        Animal("Girafe", "🦒"),
        Animal("Zèbre", "🦓"),
        Animal("Singe", "🐵"),
        Animal("Poisson", "🐠"),
        Animal("Dauphin", "🐬"),
        Animal("Baleine", "🐋"),
        Animal("Oiseau", "🐦"),
        Animal("Papillon", "🦋"),
        Animal("Abeille", "🐝"),
        Animal("Grenouille", "🐸"),
        Animal("Tortue", "🐢")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "🐾 Animaux",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Retour")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFA5D6A7),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFC8E6C9),
                            Color(0xFFA5D6A7)
                        )
                    )
                )
                .padding(padding)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(animals) { animal ->
                    AnimalCard(
                        animal = animal,
                        onClick = {
                            navController.navigate(
                                Screen.Coloring.createRoute("animal", animal.name)
                            )
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun AnimalCard(animal: Animal, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .aspectRatio(0.8f)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = animal.emoji,
                fontSize = 48.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = animal.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF2E7D32),
                textAlign = TextAlign.Center
            )
        }
    }
}
