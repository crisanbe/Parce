package com.parce.components_ui.componets.alertdialog

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.parce.components_ui.R

@Composable
fun DialogExit(
    text:String,
    onClickYes: () -> Unit,
    negativeButtonColor: Color = Color(0xFF080809),
    positiveButtonColor: Color = Color(0xFFFF0000),
    IconColor: Color = Color(0xFFFFEB3B),
    spaceBetweenElements: Dp = 18.dp,
    context: Context = LocalContext.current.applicationContext,
    viemodel: ViewModelDialog = hiltViewModel()
) {

    if (viemodel.isDialogOpen.value) {
        Dialog(onDismissRequest = {
            viemodel.dismissDialog()
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
                            text = "Cerrar?",
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
                            DialogButton(
                                buttonColor = negativeButtonColor,
                                buttonText = "No"
                            ) {
                                viemodel.dismissDialog()
                            }
                            DialogButton(
                                buttonColor = positiveButtonColor,
                                buttonText = "Si"
                            ) {
                                onClickYes()
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

@Composable
fun DialogButton(
    cornerRadiusPercent: Int = 26,
    buttonColor: Color,
    buttonText: String,
    onDismiss: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(
                color = buttonColor,
                shape = RoundedCornerShape(percent = cornerRadiusPercent)
            )
            .clickable {
                onDismiss()
            }
            .padding(horizontal = 16.dp, vertical = 6.dp)
    ) {
        Text(
            text = buttonText,
            color = Color.White,
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.montserrat_medium, FontWeight.Medium))
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DialogExitPreview() {
    DialogExit("", {})
}