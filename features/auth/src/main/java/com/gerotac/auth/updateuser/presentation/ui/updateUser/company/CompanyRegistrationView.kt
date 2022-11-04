package com.gerotac.auth.updateuser.presentation.ui.updateUser.company

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gerotac.auth.R
import com.gerotac.auth.dropdownapi.dropacademicprograms.domain.model.dropmodel.Result
import com.gerotac.auth.dropdownapi.dropacademicprograms.presentation.ui.DropAcademic
import com.gerotac.auth.dropdownapi.dropacademicprograms.presentation.viewmodel.GetApisDropViewModel
import com.gerotac.auth.login.presentation.components.logincomposables.userDataStore
import com.gerotac.auth.login.presentation.components.logincomposables.userRepo
import com.gerotac.auth.protodata.ProtoUserRepoImpl
import com.gerotac.components_ui.componets.alertdialog.DialogExit
import com.gerotac.components_ui.componets.drawer.AppScreens
import com.gerotac.components_ui.componets.DividerIcon
import com.gerotac.components_ui.componets.TopPart
import com.gerotac.components_ui.componets.alertdialog.ViewModelDialog
import com.gerotac.components_ui.componets.dropdown.DropString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun CompanyRegistrationView(
    navController: NavController,
    viewModelApisDrop: GetApisDropViewModel = hiltViewModel()
) {
    val state = viewModelApisDrop.state.collectAsState()
    val lifecycleTokenScope = rememberCoroutineScope()
    val viemodel: ViewModelDialog = hiltViewModel()
    var showDialog by remember { mutableStateOf(false) }
    BackHandler(true) { viemodel.showDialog() }
    DialogExit(
        text = "Desea salir sin actualizar tus datos?🚨",
        onClickYes = {
            showDialog = !showDialog
            lifecycleTokenScope.launch {
                userRepo?.getTokenLoginState()
                    ?.collect { tokenLogin ->
                        withContext(Dispatchers.Main) {
                            if (tokenLogin != "") {
                                tokenLogin.let { userRepo?.deleteTokenLoginState() }
                                navController.navigate(AppScreens.StartUp.route)
                            }
                        }
                    }
            }
        }
    )
    state.value.academicProgramsState.let {
        CompanyRegistration(
            result = it,
            onClickNext = {
                lifecycleTokenScope.launch {
                    navController.navigate(
                        route = AppScreens.CompanyProfileViewPagination.route +
                                "?companyName=${it[0]}&identificationType=${it[1]}&idNumber=${it[2]}"
                                + "&companyType=${it[3]}&kindCompany=${it[4]}&economicActivity=${it[5]}"
                                + "&phone=${it[6]}"
                    )
                }
            }
        )
    }
}

@Composable
fun CompanyRegistration(
    onClickNext: (List<String>) -> Unit,
    result: List<Result> = emptyList(),
) {
    userRepo = ProtoUserRepoImpl(LocalContext.current.userDataStore)
    var companyName by remember { mutableStateOf("") }
    var identificationType by remember { mutableStateOf("") }
    var idNumber by remember { mutableStateOf("") }
    var companyType by remember { mutableStateOf("") }
    var kindCompany by remember { mutableStateOf(0) }
    var economicActivity by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    val viemodel: ViewModelDialog = hiltViewModel()
    userRepo = ProtoUserRepoImpl(LocalContext.current.userDataStore)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        TopPart(onClickAction = { viemodel.showDialog() })
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.Text_Company_profile),
                fontSize = 22.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 55.dp, top = 10.dp)
            )
            Column(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.Text_We_need_some_data),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 45.dp)
                        .padding(top = 12.dp)
                )
                Spacer(Modifier.height(10.dp))
                DropString(
                    ValueState = {identificationType = it},
                    text = "Tipo de identificación",
                    options = listOf("NIT", "Cedula", "Pasaporte", "Cédula de Extranjería"),
                    mainIcon = painterResource(id = com.gerotac.components_ui.R.drawable.identity)
                )
                TextField(
                    value = idNumber,
                    onValueChange = { idNumber = it },
                    label = { Text(stringResource(id = R.string.TextField_Id_number)) },
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Pin,
                            contentDescription = stringResource(id = R.string.TextField_Id_number),
                        )
                        DividerIcon()
                    }
                )
                TextField(
                    value = companyName,
                    onValueChange = { companyName = it },
                    label = { Text(stringResource(id = R.string.TextField_Company_name)) },
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Feed,
                            contentDescription = stringResource(id = R.string.TextField_Company_name),
                        )
                        DividerIcon()
                    }
                )
                TextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = { Text(stringResource(id = R.string.TextField_Phone)) },
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.PhoneAndroid,
                            contentDescription = stringResource(id = R.string.TextField_Phone),
                        )
                        DividerIcon()
                    }
                )
                TextField(
                    value = economicActivity,
                    onValueChange = { economicActivity = it },
                    label = { Text(stringResource(id = R.string.TextField_Economic_activity)) },
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.RealEstateAgent,
                            contentDescription = stringResource(id = R.string.TextField_Economic_activity),
                        )
                        DividerIcon()
                    }
                )
                Spacer(Modifier.height(5.dp))
                DropString(
                    ValueState = {companyType = it},
                    text = "Tipo de empresa",
                    options = listOf("Grande", "Mediana", "Pequeña", "Micro"),
                    mainIcon = painterResource(id = R.drawable.company)
                )
                Spacer(Modifier.height(5.dp))
                DropAcademic(
                    selectOptionChange = { kindCompany = it },
                    text = "Tipo de sociedad",
                    options = result,
                    mainIcon = null
                )
                Spacer(Modifier.height(10.dp))
                Button(
                    onClick = {
                        onClickNext.invoke(
                            listOf(
                                companyName,
                                identificationType,
                                idNumber,
                                companyType,
                                kindCompany.toString(),
                                economicActivity,
                                phone
                            ) as List<String>
                        )
                    },
                    shape = RoundedCornerShape(percent = 45),
                    modifier = Modifier.size(height = 55.dp, width = 300.dp),
                    colors = ButtonDefaults.textButtonColors(backgroundColor = Color.Black)
                ) {
                    Text(
                        text = stringResource(R.string.Button_Siguiente),
                        modifier = Modifier,
                        color = Color.White,
                        fontSize = 20.sp,
                    )
                }
                Spacer(Modifier.height(16.dp))
            }
        }
    }
}

@Preview
@Composable
fun CompanyProfilePreview() {
    CompanyRegistration(onClickNext = { })
}
