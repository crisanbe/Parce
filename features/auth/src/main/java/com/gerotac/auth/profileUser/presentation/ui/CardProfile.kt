@file:Suppress("UselessCallOnNotNull")

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
                        text = if(!item.name.isNullOrEmpty()){item.name}else{""},
                        style = MaterialTheme.typography.h6,
                        fontFamily = Montserrat,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = if(!item.role.isNullOrEmpty()){item.role}else{""},
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.SemiBold
                    )
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
                        Text(
                            text = if(!item.email.isNullOrEmpty()){item.email}else{""},
                            style = MaterialTheme.typography.body1)
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
                    item = if(!item.type_document.isNullOrEmpty()){item.type_document}else{""},
                    elevation = 10.dp,
                    text = "Tipo de documento: ",
                    border = BorderStroke(1.dp, Color.Black)
                )
                CardView(
                    item = if(!item.document.isNullOrEmpty()){item.document}else{""},
                    elevation = 10.dp,
                    text = "Documento: ",
                    border = BorderStroke(1.dp, Color.Black)
                )
                CardView(
                    item = if(!item.phone.isNullOrEmpty()){item.phone}else{""},
                    elevation = 10.dp,
                    text = "Teléfono: ",
                    border = BorderStroke(2.dp, Color.Black)
                )
                CardView(
                    item = if(!item.birthday.isNullOrEmpty()){item.birthday}else{""},
                    elevation = 10.dp,
                    text = "Fecha de nacimiento: ",
                    border = BorderStroke(1.dp, Color.Black)
                )
                CardView(
                    item = if(!item.gener.isNullOrEmpty()){item.gener}else{""},
                    elevation = 10.dp,
                    text = "Genero: ",
                    border = BorderStroke(1.dp, Color.Black)
                )
                CardView(
                    item = if(!item.group_etnic.isNullOrEmpty()){item.group_etnic}else{""},
                    elevation = 10.dp,
                    text = "Grupo étnico: ",
                    border = BorderStroke(1.dp, Color.Black)
                )
                CardView(
                    item = if(!item.presents_disability.isNullOrEmpty()){item.presents_disability}else{""},
                    elevation = 10.dp,
                    text = "Discapacidad: ",
                    border = BorderStroke(1.dp, Color.Black)
                )
                when {
                    !item.student?.academicprogram?.name.isNullOrEmpty() -> {
                        CardAlternative(
                            item =  (if(!item.student?.academicprogram?.name.isNullOrEmpty()){item.student?.academicprogram?.name}else{""}).toString(),
                            elevation = 10.dp,
                            text = "Programa: ",
                            border = BorderStroke(1.dp, Color.Black)
                        )
                    }
                    else -> {}
                }
                when {
                    !item.bussine?.activity_economy.isNullOrEmpty() -> {
                        CardView(
                            item = (if(!item.bussine?.activity_economy.isNullOrEmpty()){item.bussine?.activity_economy}else{""}).toString(),
                            elevation = 10.dp,
                            text = "Actividad económica: ",
                            border = BorderStroke(1.dp, Color.Black)
                        )
                    }
                    else -> {}
                }

                when {
                    !item.bussine?.type_bussiness.isNullOrEmpty() -> {
                        CardView(
                            item = (if(!item.bussine?.type_bussiness.isNullOrEmpty()){item.bussine?.type_bussiness}else{""}).toString(),
                            elevation = 10.dp,
                            text = "Tipo de empresa: ",
                            border = BorderStroke(1.dp, Color.Black)
                        )
                    }
                    else -> {}
                }
                when {
                    !item.bussine?.person_contact.isNullOrEmpty() -> {
                        CardView(
                            item = (if(!item.bussine?.person_contact.isNullOrEmpty()){item.bussine?.person_contact}else{""}).toString(),
                            elevation = 10.dp,
                            text = "Contacto: ",
                            border = BorderStroke(1.dp, Color.Black)
                        )
                    }
                    else -> {}
                }
                when {
                    !item.bussine?.address.isNullOrEmpty() -> {
                        CardView(
                            item = (if(!item.bussine?.address.isNullOrEmpty()){item.bussine?.address}else{""}).toString(),
                            elevation = 10.dp,
                            text = "Dirección: ",
                            border = BorderStroke(1.dp, Color.Black)
                        )
                    }
                    else -> {}
                }
                when {
                    !item.bussine?.typesociety?.name.isNullOrEmpty() -> {
                        CardView(
                            item = (if(!item.bussine?.typesociety?.name.isNullOrEmpty()){item.bussine?.typesociety?.name}else{""}).toString(),
                            elevation = 10.dp,
                            text = "Tipo de sociedad: ",
                            border = BorderStroke(1.dp, Color.Black)
                        )
                    }
                    else -> {}
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
                                    "admin" -> {
                                        navController.navigate(
                                            AppScreens.UpdateAdmin.route
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
                                    "empresa" -> {
                                        navController.navigate(
                                            AppScreens.UpdateCompanyView.route
                                                    + "?companyName=${item.name}" +
                                                    "&identificationType=${item.type_document}" +
                                                    "&idNumber=${item.document}" +
                                                    "&companyType=${item.bussine?.type_bussiness}" +
                                                    "&kindCompany=${item.bussine?.typesociety?.name}" +
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
                                                    "&academic_program=${item.student?.academicprogram?.name}" +
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
}


