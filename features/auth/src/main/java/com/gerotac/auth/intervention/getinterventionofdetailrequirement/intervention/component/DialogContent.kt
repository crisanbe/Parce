package com.gerotac.auth.intervention.getinterventionofdetailrequirement.intervention.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
 fun DialogContent(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (ColumnScope)-> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(990.dp)
            .clip(shape = RoundedCornerShape(topStart = 60.dp, topEnd = 60.dp))
            .background(color = Color(0xFFFFFFFF)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        content = content
    )
}
