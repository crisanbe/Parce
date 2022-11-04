package com.gerotac.components_ui.componets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ScaffoldState
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun TopBar(
    title: String,
    buttonIcon: ImageVector,
    icon: ImageVector? = null,
    scaffoldState: ScaffoldState,
    onClickIconButton: (ScaffoldState) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TopAppBar(
            modifier = Modifier
                .offset(y = (-14).dp)
                .aspectRatio(5f)
                .shadow(shape = RoundedCornerShape(21.dp), elevation = 2.dp),
            navigationIcon = {
                IconButton(
                    onClick = { onClickIconButton(scaffoldState) },
                    ) {
                    Icon(
                        buttonIcon,
                        contentDescription = null, tint = Color.White
                    )
                }
            },
            title = {
                if (icon != null) {
                    Icon(
                        modifier = Modifier.padding(horizontal = 110.dp),
                        imageVector = icon, contentDescription = null)
                }
            },
            actions = {
               Image(
                    painter = painterResource(id = com.gerotac.components_ui.R.drawable.logo_parce_original),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.Cyan)
                        .size(45.dp)
                        .border(2.dp, Color(0xFFFFCA4C), CircleShape)
                        .clickable { },
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(5.dp))
            },
            backgroundColor = Color(0xFF21120B)
        )
    }
}
