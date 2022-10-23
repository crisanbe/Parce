package com.parce.parce.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.parce.components_ui.componets.drawer.AppScreens
import com.parce.parce.R
import com.parce.parce.ui.theme.Montserrat

@Composable
fun StartUpView(
    navController: NavController
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.padding(top = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_parce_vista_login_registro),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = stringResource(id = R.string.Text_Discover_your),
                fontSize = 22.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontFamily = Montserrat,
                textAlign = TextAlign.Center,
                letterSpacing = .02.sp,
                modifier = Modifier.padding(horizontal = 15.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
            ) {
                Button(
                    onClick = { navController.navigate(AppScreens.LoginScreen.route) },
                    modifier = Modifier.size(height = 50.dp, width = 120.dp),
                    colors = ButtonDefaults.textButtonColors(
                        backgroundColor = Color.White
                    )
                ) {
                    Text(
                        text = stringResource(R.string.Button_Login),
                        modifier = Modifier,
                        color = Color.Black,
                        fontSize = 16.sp,
                    )
                }
                Button(
                    onClick = { navController.navigate(AppScreens.RegisterScreen.route) },
                    modifier = Modifier.size(height = 50.dp, width = 120.dp),
                    colors = ButtonDefaults.textButtonColors(
                        backgroundColor = Color.Black
                    )
                ) {
                    Text(
                        text = stringResource(R.string.Button_Register),
                        modifier = Modifier,
                        color = Color.White,
                        fontSize = 16.sp,
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSlider() {
    StartUpView(rememberNavController())
}
