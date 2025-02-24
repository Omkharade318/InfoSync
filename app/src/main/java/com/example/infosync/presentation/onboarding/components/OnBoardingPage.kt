package com.example.infosync.presentation.onboarding.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.infosync.presentation.Dimens.MediumPadding1
import com.example.infosync.presentation.Dimens.MediumPadding2
import com.example.infosync.presentation.onboarding.Page
import com.example.infosync.R
import com.example.infosync.presentation.Dimens.PageIndicatorWidth
import com.example.infosync.presentation.common.NewsButton
import com.example.infosync.presentation.common.NewsTextButton
import com.example.infosync.presentation.onboarding.OnBoardingEvent
import com.example.infosync.presentation.onboarding.pages
import com.example.infosync.ui.theme.InfoSyncTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingPage(
    modifier: Modifier = Modifier,
    page: Page,
    pagerState: PagerState, // Add pagerState as a parameter
    event: (OnBoardingEvent) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = page.bgColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(0.3f))
        Box(
            modifier = Modifier
                .clip(shape = CircleShape)
                .fillMaxWidth(0.8f)
                .fillMaxHeight(0.4f)
                .background(
                    color = page.cirColor,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.fillMaxSize(0.8f),
                painter = painterResource(id = page.image),
                contentDescription = null
            )
        }

        Spacer(modifier = Modifier.height(MediumPadding1))

        Text(
            text = page.title,
            modifier = Modifier.padding(horizontal = MediumPadding2),
        style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
        color = colorResource(id = R.color.display_small)
        )

        Text(
            text = page.description,
            modifier = Modifier.padding(horizontal = MediumPadding2),
        style = MaterialTheme.typography.bodyMedium,
        color = colorResource(id = R.color.text_medium)
        )

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MediumPadding2)
                .navigationBarsPadding(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
        ) {
        PageIndicator(
            modifier = Modifier.width(PageIndicatorWidth),
        pageSize = pages.size,
        selectedPage = pagerState.currentPage
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            val scope = rememberCoroutineScope()

            val buttonState = remember {
                derivedStateOf {
                    when (pagerState.currentPage) {
                        0 -> listOf("", "Next")
                        1 -> listOf("Back", "Next")
                        2 -> listOf("Back", "Get Started")
                        else -> listOf("", "")
                    }
                }
            }

            if (buttonState.value[0].isNotEmpty()) {
                NewsTextButton(
                    text = buttonState.value[0],
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(
                                page = pagerState.currentPage - 1
                            )
                        }
                    }
                )
            }

            NewsButton(
                text = buttonState.value[1],
                onClick = {
                    scope.launch {
                        if (pagerState.currentPage == 2) { // Fixed: Check for the last page
                            event(OnBoardingEvent.SaveAppEntry)
                        } else {
                            pagerState.animateScrollToPage(
                                page = pagerState.currentPage + 1
                            )
                        }
                    }
                }
            )
        }
    }
        Spacer(modifier = Modifier.weight(0.3f))
    }
}


@Preview(showBackground = true, showSystemUi = true, uiMode = 1)
@Composable
fun OnBoardingPagePreview(){
    InfoSyncTheme {

    }
}