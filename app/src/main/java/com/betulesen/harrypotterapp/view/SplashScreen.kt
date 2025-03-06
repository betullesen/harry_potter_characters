package com.betulesen.harrypotterapp.view

import android.os.Handler
import android.os.Looper
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.betulesen.harrypotterapp.R

@Composable
fun CharacterSplashScreen(navController: NavController) {

    
    LaunchedEffect(Unit) {
        Handler(Looper.getMainLooper()).postDelayed({
            navController.navigate("character_list_screen") {
                popUpTo("splash_screen") { inclusive = true }
            }
        }, 3000) 
    }

    
    val alphaAnimation = remember { Animatable(0.3f) }

    
    LaunchedEffect(Unit) {
        
        alphaAnimation.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
        )
    }

    
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        
        Image(
            painter = painterResource(id = R.drawable.harrypotter),
            contentDescription = "Harry Potter Background",
            modifier = Modifier
                .fillMaxSize()
                .alpha(alphaAnimation.value), 
            contentScale = ContentScale.Crop
        )
    }
}
