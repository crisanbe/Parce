package com.gerotac.components_ui.componets.alertdialog

import android.Manifest
import android.content.Context
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.gerotac.components_ui.R

@Composable
fun AlertNext(
    text: String,
    onClickYes: () -> Unit,
    positiveButtonColor: Color = Color(0xFFFFEB3B),
    IconColor: Color = Color(0xFFFFEB3B),
    spaceBetweenElements: Dp = 18.dp,
    viemodel: ViewModelExitDialog = hiltViewModel()
) {
    var galle by remember {
        mutableStateOf(false)
    }
    val launcheAndroid11 = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { status->
            if (status) {
                galle = true
            }
        })
    if (viemodel.isDialogOpen.value) {
        Dialog(onDismissRequest = {
            viemodel.dismissAlertDialog()
        }
        ) {
            Surface(
                modifier = Modifier.fillMaxWidth(0.92f),
                color = Color.Transparent
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .padding(top = 30.dp)
                            .fillMaxWidth()
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(percent = 10)
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(height = 36.dp))
                        Text(
                            text = "☑️",
                            fontFamily = FontFamily(
                                Font(
                                    R.font.montserrat_medium,
                                    FontWeight.Bold
                                )
                            ),
                            fontSize = 24.sp
                        )
                        Spacer(modifier = Modifier.height(height = spaceBetweenElements))
                        Text(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            text = text,
                            fontFamily = FontFamily(
                                Font(
                                    R.font.montserrat_medium,
                                    FontWeight.Normal
                                )
                            ),
                            fontSize = 18.sp
                        )
                        Spacer(modifier = Modifier.height(height = spaceBetweenElements))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            Button(
                                modifier = Modifier.width(200.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color(0xFFFFEB3B),
                                ),
                                onClick = {
                                launcheAndroid11.launch(Manifest.permission.POST_NOTIFICATIONS)
                                onClickYes()},
                                shape = RoundedCornerShape(50)) {
                                Text("Si")
                            }
                        }
                        Spacer(modifier = Modifier.height(height = spaceBetweenElements * 2))
                    }
                    Icon(
                        painter = painterResource(id = R.drawable.ic_alert_circle),
                        contentDescription = "Cerrar",
                        tint = IconColor,
                        modifier = Modifier
                            .background(color = Color.Black, shape = CircleShape)
                            .border(
                                width = 2.dp,
                                shape = CircleShape,
                                color = IconColor
                            )
                            .padding(all = 16.dp)
                            .align(alignment = Alignment.TopCenter)
                    )
                }
            }
        }
    }
}

