package com.example.infosync.presentation.onboarding

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.example.infosync.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int,
    val bgColor: Color,
    val cirColor: Color
)
val pages = listOf(

    Page(
        title = "Stay Informed, Stay Connected",
        description = "Discover the world’s latest stories, trending topics, and personalized updates all in one place. With InfoSync, staying informed has never been easier or more engaging",
        image = R.drawable.onboarding_lady,
        bgColor = Color(red = 218, green = 211, blue = 200),
        cirColor = Color(red = 245, green = 229, blue = 215, alpha = 255)
    ),
    Page(
        title = "Save What Matters",
        description = "Bookmark your favorite articles and stories to read later. With InfoSync, you’ll never miss a moment of the news that matters most to you.",
        image = R.drawable.onboarding_man2,
        bgColor = Color(red = 255, green = 229, blue = 222),
        cirColor = Color(red = 251, green = 203, blue = 193, alpha = 255),
    ),
    Page(
        title = "Share the Stories That Matter",
        description = "Keep your friends in the loop! With InfoSync, sharing news and articles is just a tap away. Stay connected and spread the word.",
        image = R.drawable.onboarding_man1,
        bgColor = Color(red = 220, green = 246, blue = 230),
        cirColor = Color(red = 188, green = 248, blue = 216, alpha = 255),
    ),

)
