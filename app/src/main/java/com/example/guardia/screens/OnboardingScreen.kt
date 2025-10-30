package com.example.guardia.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import com.google.accompanist.pager.*
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen() {
    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(count = 2, state = pagerState) { page ->
            /*  when (page) {
                0 -> OnboardingPage(
                    imageRes = R.drawable.onboarding1,
                    title = "Proteção Inteligente",
                    description = "Sua segurança começa aqui."
                )
                1 -> OnboardingPage(
                    imageRes = R.drawable.onboarding2,
                    title = "Denúncia Fácil",
                    description = "Ajude a combater riscos com um toque."
                )
            }
        }*/

            Spacer(modifier = Modifier.height(16.dp))

            HorizontalPagerIndicator(
                pagerState = pagerState,
                activeColor = Color.White,
                inactiveColor = Color.Gray
            )
        }
    }
}

