package com.gerotac.auth.approveanddisapprove.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.gerotac.components_ui.R

@Composable
fun CheckedApproveAndDisapprove(
    onclickApprove: () -> Unit,
    onclickDisapprove: () -> Unit,
    textApprove: String? = null,
    textDisapprove: String? = null
) {
    var checked by remember { mutableStateOf(false) }
    Box(
        Modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth()
    ) {
        Row(modifier = Modifier.align(Alignment.CenterEnd)) {
            Text(
                modifier = Modifier.padding(horizontal = 5.dp),
                text = textApprove.toString(),
                textAlign = TextAlign.Justify,
                fontWeight = FontWeight.Bold
            )
            IconToggleButton(checked = checked, onCheckedChange = { checked = it }) {
                Spacer(modifier = Modifier.width(5.dp))
                Icon(
                    modifier = Modifier
                        .size(50.dp)
                        .clickable { onclickApprove() }
                        .background(
                            color = Color.Green,
                            shape = RoundedCornerShape(60.dp)
                        ),
                    painter = painterResource(R.drawable.disapprove),
                    contentDescription =
                    if (checked) "Añadir a marcadores"
                    else "Quitar de marcadores"
                )
            }
            Text(
                modifier = Modifier.padding(horizontal = 5.dp),
                text = textDisapprove.toString(),
                textAlign = TextAlign.Justify,
                fontWeight = FontWeight.Bold
            )
            IconToggleButton(checked = checked, onCheckedChange = { checked = it }) {
                Icon(
                    modifier = Modifier
                        .size(50.dp)
                        .clickable { onclickDisapprove() }
                        .background(
                            color = Color.Red,
                            shape = RoundedCornerShape(60.dp)
                        ),
                    painter = painterResource(R.drawable.disapprove),
                    contentDescription =
                    if (checked) "Añadir a marcadores"
                    else "Quitar de marcadores"
                )
            }
        }
    }
}