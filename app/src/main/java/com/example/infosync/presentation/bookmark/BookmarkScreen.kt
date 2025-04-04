package com.example.infosync.presentation.bookmark

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import com.example.infosync.presentation.Dimens.MediumPadding1
import com.example.infosync.R
import com.example.infosync.domain.model.Article
import com.example.infosync.presentation.common.ArticlesList
import com.example.infosync.presentation.navigation.Route

@Composable
fun BookmarkScreen(
    state: BookmarkState,
    navigateToDetails: (Article) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(top = MediumPadding1, start = MediumPadding1, end = MediumPadding1)
    ) {

        Text(
            text = "Bookmark",
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
            color =
                if (isSystemInDarkTheme())
                    colorResource(id = R.color.white)
                else
                    colorResource(id = R.color.text_title)
        )

        Spacer(modifier = Modifier.height(MediumPadding1))


        ArticlesList(
            articles = state.articles,
            onClick = { navigateToDetails(it) }
        )


    }

}