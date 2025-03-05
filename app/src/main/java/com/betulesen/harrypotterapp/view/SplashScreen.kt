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

    // Navigasyon işlemi için gecikme ekleyelim
    LaunchedEffect(Unit) {
        Handler(Looper.getMainLooper()).postDelayed({
            navController.navigate("character_list_screen") {
                popUpTo("splash_screen") { inclusive = true }
            }
        }, 3000) // Splash ekranı 3 saniye kaldıktan sonra yönlendirme yapılacak
    }

    // Arka planın alfa animasyonu
    val alphaAnimation = remember { Animatable(0.3f) }

    // Animasyonu başlatmak için bir etki (LaunchedEffect)
    LaunchedEffect(Unit) {
        // 1 saniye içinde soluklaşacak şekilde animasyonu başlat
        alphaAnimation.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
        )
    }

    // Box ile tüm öğeleri saralım
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Arka plan görseli, alfa animasyonu uygulanacak
        Image(
            painter = painterResource(id = R.drawable.harrypotter), // Harry Potter arka plan görseli
            contentDescription = "Harry Potter Background",
            modifier = Modifier
                .fillMaxSize()
                .alpha(alphaAnimation.value), // Animasyonlu opaklık
            contentScale = ContentScale.Crop
        )
    }
}
