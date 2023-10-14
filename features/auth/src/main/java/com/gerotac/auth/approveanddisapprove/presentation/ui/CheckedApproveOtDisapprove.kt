package com.gerotac.auth.approveanddisapprove.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DrawerValue
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gerotac.auth.intervention.detailintervention.presentation.viewmodel.DetailInterventionViewModel
import com.gerotac.auth.intervention.getinterventionofdetailrequirement.intervention.InterventionScreen
import com.gerotac.auth.requirement.di.HeaderRequirement
import com.gerotac.auth.requirement.domain.model.detailrequirement.Intervention
import com.gerotac.auth.requirement.presentation.viewmodel.DetailRequirementViewModel
import com.gerotac.components_ui.R

@Composable
fun CheckedApproveAndDisapprove(
    onclickApprove: () -> Unit,
    onclickDisapprove: () -> Unit,
    textApprove: String? = null,
    color1 : Int,
    color2 : Int,
    textDisapprove: String? = null,
    statusIntervention: Intervention
) {
    val state = statusIntervention
    var checked by remember { mutableStateOf(state.status) }
    Box(
        Modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth()
    ) {
        if (checked.toString() == "1" && HeaderRequirement.getRol()["rol"] == "docente") {
            Row(modifier = Modifier.align(Alignment.CenterEnd)) {
                Text(
                    modifier = Modifier.padding(horizontal = 5.dp),
                    text = textApprove.toString(),
                    textAlign = TextAlign.Justify,
                    fontWeight = FontWeight.Bold
                )

                IconToggleButton(
                    checked = checked.toBoolean(),
                    onCheckedChange = { checked = it.toString() }) {
                    Spacer(modifier = Modifier.width(5.dp))
                    Icon(
                        modifier = Modifier
                            .size(50.dp)
                            .clickable { onclickApprove() },
                        painter = painterResource(
                            if (checked.toBoolean()) color1 else color1
                        ),
                        contentDescription =
                        if (checked.toBoolean()) "Añadir a marcadores"
                        else "Quitar de marcadores",
                        tint = Color(0xFF43A047)
                    )
                }
                Text(
                    modifier = Modifier.padding(horizontal = 5.dp),
                    text = textDisapprove.toString(),
                    textAlign = TextAlign.Justify,
                    fontWeight = FontWeight.Bold
                )
                IconToggleButton(
                    checked = checked.toBoolean(),
                    onCheckedChange = { checked = it.toString() }) {
                    Icon(
                        modifier = Modifier
                            .size(50.dp)
                            .clickable { onclickDisapprove() },
                        painter = painterResource(
                            if (checked.toBoolean()) color2 else color2
                        ),
                        contentDescription =
                        if (checked.toBoolean()) "Añadir a marcadores"
                        else "Quitar de marcadores",
                        tint = Color(0xFFE53935)
                    )
                }
            }
        }else if(checked.toString() == "1"){
            Text(
                text = "Pendiente...",
                color = Color.Blue,
                maxLines = 1,
                fontSize = 15.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
        else if(checked.toString() == "0"){
            Text(
                text = "Intervención aprobada",
                color = Color.Green,
                maxLines = 1,
                fontSize = 15.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }else if(checked.toString() == "2"){
            Text(text = "Intervención rechazada",
                color = Color.Red,
                maxLines = 1,
                fontSize = 15.sp,
                fontWeight = FontWeight.ExtraBold
                )
        }
    }
}