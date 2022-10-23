package com.parce.parce.ui.view

import android.content.Intent
import android.preference.PreferenceManager
import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.*
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.parce.parce.R
import com.parce.parce.ui.MainActivity
import com.parce.parce.ui.theme.Montserrat
import com.parce.parce.util.Items
import kotlinx.coroutines.launch

@ExperimentalAnimationApi
@ExperimentalCoilApi
@ExperimentalPermissionsApi
@ExperimentalPagerApi
@Composable
fun ImageSliderView() {
    val pagerState = rememberPagerState()
    val items = Items.get()
    val context = LocalContext.current
    val prefs = PreferenceManager.getDefaultSharedPreferences(context)
    val scope = rememberCoroutineScope()
    val preferencesAmount = prefs.edit()
    preferencesAmount.putBoolean("key", true)
    preferencesAmount.apply()
    HorizontalPager(
        count = Items.get().size,
        modifier = Modifier.fillMaxSize(),
        state = pagerState
    ) { index ->
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            OnBoardingItem(items[index], pagerState)
            ButtonSkip(
                index = pagerState.currentPage,
                OnClickLogin = {
                    val intent = Intent(context, MainActivity::class.java)
                    intent.putExtra("status", true)
                    context.startActivity(intent)
                }
            )
            Buttons(
                pagerState,
                OnClickLogin = {
                    val intent = Intent(context, MainActivity::class.java)
                    intent.putExtra("status", true)
                    context.startActivity(intent)
                },
                OnClickNext = {
                    if (pagerState.currentPage + 1 < items.size) scope.launch {
                        pagerState.scrollToPage(page = pagerState.currentPage + 1)
                    }
                },
                OnClickBack = {
                    if (pagerState.currentPage - 1 < items.size) scope.launch {
                        pagerState.scrollToPage(page = pagerState.currentPage - 1)
                    }
                }
            )
        }
    }
}

@ExperimentalPagerApi
@Composable
fun OnBoardingItem(items: Items, pagerState: PagerState) {
    Column(
        modifier = Modifier.padding(top = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(items.image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = stringResource(items.title),
            fontSize = 20.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontFamily = Montserrat,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(modifier = Modifier.padding(10.dp)) {
            Text(
                modifier = Modifier.padding(10.dp),
                text = stringResource(items.text),
                fontSize = 14.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                letterSpacing = 1.sp
            )
        }

        Spacer(modifier = Modifier.height(50.dp))
        HorizontalPagerIndicator(
            pagerState = pagerState,
            activeColor = Color.Green,
            inactiveColor = Color.Gray
        )
    }
}

@ExperimentalCoilApi
@ExperimentalPermissionsApi
@ExperimentalPagerApi
@Composable
fun Buttons(
    pagerState: PagerState,
    OnClickLogin: () -> Unit,
    OnClickNext: () -> Unit,
    OnClickBack: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ButtonBack(
                index = pagerState.currentPage,
                OnClickLogin = OnClickLogin,
                OnClickBack = OnClickBack
            )
            ButtonNext(
                index = pagerState.currentPage,
                OnClickLogin = OnClickLogin,
                OnClickNext = OnClickNext
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@ExperimentalCoilApi
@ExperimentalPermissionsApi
@ExperimentalPagerApi
@Composable
fun ButtonBack(
    index: Int,
    OnClickLogin: () -> Unit,
    OnClickBack: () -> Unit
) {
    when (index) {
        0 -> { //StartUpView
            TextButton(
                modifier = Modifier.size(height = 50.dp, width = 120.dp),
                onClick = { OnClickLogin.invoke() },
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = stringResource(R.string.TextButton_Skip),
                    modifier = Modifier,
                    color = Color.Black,
                    fontSize = 16.sp
                )
            }
        }
        1 -> { //View 1
            Button(
                modifier = Modifier.size(height = 50.dp, width = 120.dp),
                onClick = { OnClickBack.invoke() },
                colors = ButtonDefaults.textButtonColors(backgroundColor = Color.White)
            ) {
                Text(
                    text = stringResource(R.string.Button_Back),
                    modifier = Modifier,
                    color = Color.Black,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@ExperimentalCoilApi
@ExperimentalPermissionsApi
@ExperimentalPagerApi
@Composable
fun ButtonNext(
    index: Int,
    OnClickLogin: () -> Unit,
    OnClickNext: () -> Unit
) {
    when (index) {
        0 -> { //View 2
            Button(
                modifier = Modifier.size(height = 50.dp, width = 120.dp),
                onClick = { OnClickNext.invoke() },
                colors = ButtonDefaults.textButtonColors(backgroundColor = Color.Black)
            ) {
                Text(
                    text = stringResource(R.string.Button_Next),
                    modifier = Modifier,
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }
        1 -> { //View 3
            Button(
                modifier = Modifier.size(height = 50.dp, width = 120.dp),
                onClick = { OnClickNext.invoke() },
                colors = ButtonDefaults.textButtonColors(backgroundColor = Color.Black)
            ) {
                Text(
                    text = stringResource(R.string.Button_Next),
                    modifier = Modifier,
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }
        2 -> { //StartUpView
            Button(
                modifier = Modifier.size(height = 50.dp, width = 300.dp),
                onClick = { OnClickLogin.invoke() },
                colors = ButtonDefaults.textButtonColors(backgroundColor = Color.Black)
            ) {
                Text(
                    text = stringResource(R.string.Button_Lets_Start),
                    modifier = Modifier,
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@ExperimentalCoilApi
@ExperimentalPagerApi
@ExperimentalPermissionsApi
@Composable
fun ButtonSkip(
    index: Int,
    OnClickLogin: () -> Unit
) {
    when (index) {
        1 -> { //StartUpView
            TextButton(
                onClick = { OnClickLogin.invoke() },
            ) {
                Text(
                    text = stringResource(R.string.TextButton_Skip),
                    modifier = Modifier,
                    color = Color.Black,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@ExperimentalAnimationApi
@ExperimentalCoilApi
@ExperimentalPermissionsApi
@ExperimentalPagerApi
@Preview(showBackground = true)
@Composable
fun ImageSliderPreview() {
    ImageSliderView()
}
