package com.gerotac.parce.ui.view

import android.Manifest
import android.annotation.SuppressLint
import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gerotac.components_ui.componets.alertdialog.ViewModelDialog
import com.gerotac.components_ui.componets.drawer.AppScreens
import com.gerotac.parce.R
import com.gerotac.parce.ui.theme.Montserrat
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState

@SuppressLint("PermissionLaunchedDuringComposition")
@ExperimentalPermissionsApi
@Composable
fun StartUpView(
    navController: NavController
) {
    val permissionsStatesReadStorage = rememberPermissionState(permission = Manifest.permission.READ_EXTERNAL_STORAGE)
    val permissionsStatesNotification = rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)
    val viewModelDialog: ViewModelDialog = hiltViewModel()
    BackHandler(true) { viewModelDialog.showDialog() }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        LaunchedEffect(true){
            permissionsStatesNotification.launchPermissionRequest()
        }
    }else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R){
        LaunchedEffect(true){
            permissionsStatesReadStorage.launchPermissionRequest()
        }
    }
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
    // StartUpView(rememberNavController())
}
