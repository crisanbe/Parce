package com.gerotac.auth.profileUser.presentation.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gerotac.auth.profileUser.domain.model.User
import com.gerotac.components_ui.componets.card.CardAlternative
import com.gerotac.components_ui.componets.card.CardView
import com.gerotac.components_ui.componets.drawer.AppScreens
import com.gerotac.components_ui.componets.ui.theme.Montserrat

@Composable
fun CardProfile(
    modifier: Modifier = Modifier,
    item: User,
    elevation: Dp = 4.dp,
    border: BorderStroke? = null,
    background: Color = Color.White,
    shape: Shape = RoundedCornerShape(3.dp),
    navController: NavController,
) {
    Card(
        backgroundColor = background,
        shape = shape,
        elevation = elevation,
        border = border,
        modifier = modifier,
    ) {
        Column {
            Row(
                Modifier
                    .fillMaxWidth()
                    .background(color = Color.White)
                    .height(100.dp)
                    .padding(start = 22.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = item.name,
                        style = MaterialTheme.typography.h6,
                        fontFamily = Montserrat,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = item.role,
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.SemiBold
                    )
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
                        Text(text = item.email, style = MaterialTheme.typography.body1)
                    }
                }
            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(color = Color.White)
                    .align(Alignment.CenterHorizontally)
            ) {
                CardView(
                    item = item.type_document,
                    elevation = 10.dp,
                    text = "Tipo de documento: ",
                    border = BorderStroke(1.dp, Color.Black)
                )
                CardView(
                    item = item.document.toString(),
                    elevation = 10.dp,
                    text = "Documento: ",
                    border = BorderStroke(1.dp, Color.Black)
                )
                CardView(
                    item = item.phone,
                    elevation = 10.dp,
                    text = "Telefono: ",
                    border = BorderStroke(1.dp, Color.Black)
                )
                CardView(
                    item = item.birthday.toString(),
                    elevation = 10.dp,
                    text = "Fecha de naciemiento: ",
                    border = BorderStroke(1.dp, Color.Black)
                )
                CardView(
                    item = item.gener.toString(),
                    elevation = 10.dp,
                    text = "Genero: ",
                    border = BorderStroke(1.dp, Color.Black)
                )
                CardView(
                    item = item.group_etnic,
                    elevation = 10.dp,
                    text = "Grupo etnico: ",
                    border = BorderStroke(1.dp, Color.Black)
                )
                CardView(
                    item = item.presents_disability,
                    elevation = 10.dp,
                    text = "Discapacidad: ",
                    border = BorderStroke(1.dp, Color.Black)
                )
                when (item.student?.academic_program) {
                    null -> {}
                    else -> {
                        CardAlternative(
                            item = item.student.academic_program ?: 0,
                            elevation = 10.dp,
                            text = "Programa académico: ",
                            border = BorderStroke(1.dp, Color.Black)
                        )
                    }
                }
                when (item.bussine?.activity_economy) {
                    null -> Unit
                    else -> {
                        CardView(
                            item = item.bussine.activity_economy,
                            elevation = 10.dp,
                            text = "Actividad economica: ",
                            border = BorderStroke(1.dp, Color.Black)
                        )
                    }
                }
                when (item.bussine?.type_bussiness) {
                    null -> Unit
                    else -> {
                        CardView(
                            item = item.bussine.type_bussiness,
                            elevation = 10.dp,
                            text = "Tipo de empresa: ",
                            border = BorderStroke(1.dp, Color.Black)
                        )
                    }
                }
                when (item.bussine?.person_contact) {
                    null -> Unit
                    else -> {
                        CardView(
                            item = item.bussine.person_contact,
                            elevation = 10.dp,
                            text = "Contacto: ",
                            border = BorderStroke(1.dp, Color.Black)
                        )
                    }
                }
                when (item.bussine?.address) {
                    null -> Unit
                    else -> {
                        CardView(
                            item = item.bussine.address,
                            elevation = 10.dp,
                            text = "Direccion: ",
                            border = BorderStroke(1.dp, Color.Black)
                        )
                    }
                }
            }

            Box(
                Modifier
                    .fillMaxWidth()
                    .background(color = Color.White)
                    .padding()
                    .padding(horizontal = 8.dp, vertical = 5.dp)
            ) {
                Row(
                    modifier = Modifier
                        .align(Alignment.Center)
                ) {
                    ButtonValid(
                        onClick = {
                            when (item.role) {
                                "empresa" -> {
                                    navController.navigate(
                                        AppScreens.UpdateCompanyView.route
                                                + "?companyName=${item.name}" +
                                                "&identificationType=${item.type_document}" +
                                                "&idNumber=${item.document}" +
                                                "&companyType=${item.bussine?.type_bussiness}" +
                                                "&kindCompany=${item.bussine?.type_society}" +
                                                "&economicActivity=${item.bussine?.activity_economy}" +
                                                "&contactPerson=${item.bussine?.person_contact}" +
                                                "&department=${item.bussine?.departament}" +
                                                "&municipality=${item.bussine?.municipality}" +
                                                "&birthday=${item.birthday}" +
                                                "&gene=${item.gener}" +
                                                "&ethnicGroup=${item.group_etnic}" +
                                                "&presentsDisability=${item.presents_disability}" +
                                                "&address=${item.bussine?.address}" +
                                                "&phone=${item.phone}"
                                    )
                                }
                                "docente" -> {
                                    navController.navigate(
                                        AppScreens.UpdateTeacherView.route
                                                + "?teacherName=${item.name}" +
                                                "&identificationType=${item.type_document}" +
                                                "&idNumber=${item.document}" +
                                                "&birthday=${item.birthday}" +
                                                "&gene=${item.gener}" +
                                                "&ethnicGroup=${item.group_etnic}" +
                                                "&presentsDisability=${item.presents_disability}" +
                                                "&address=${item.bussine?.address}" +
                                                "&phone=${item.phone}"
                                    )
                                }
                                "estudiante" -> {
                                    navController.navigate(
                                        AppScreens.UpdateStudentView.route
                                                + "?studentName=${item.name}" +
                                                "&identificationType=${item.type_document}" +
                                                "&idNumber=${item.document}" +
                                                "&birthday=${item.birthday}" +
                                                "&gene=${item.gener}" +
                                                "&ethnicGroup=${item.group_etnic}" +
                                                "&presentsDisability=${item.presents_disability}" +
                                                "&academic_program=${item.student?.academic_program}" +
                                                "&address=${item.bussine?.address}" +
                                                "&phone=${item.phone}"
                                    )
                                }
                            }
                        }, text = "Actualizar"
                    )
                }
            }
        }
    }
}


