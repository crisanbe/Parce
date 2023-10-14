package com.gerotac.auth.requirement.presentation.ui.homerequirement

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gerotac.auth.R
import com.gerotac.components_ui.componets.alertdialog.ViewModelDialog

@Composable
fun HomeContent(nameUser: String?) {
    val textState = remember { mutableStateOf(TextFieldValue()) }
    val viemodel: ViewModelDialog = hiltViewModel()
    BackHandler(true) { viemodel.showDialog() }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hola, $nameUser",
            style = androidx.compose.ui.text.TextStyle(
                color = colorResource(id = com.gerotac.components_ui.R.color.primary)
            ),
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold
        )
        TextField(
            value = textState.value,
            onValueChange = { textState.value = it },
            placeholder = { Text(text = stringResource(R.string.TextField_Search_request)) },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(15.dp)),
            trailingIcon = {
                IconButton(onClick = {  }) {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = null,
                        modifier = Modifier
                            .size(55.dp)
                            .clip(RoundedCornerShape(15.dp))
                            .background(Color(0xFF21120B))
                            .scale(scale = 0.6f),
                        tint = Color.White
                    )
                }
            }
        )
    }
}