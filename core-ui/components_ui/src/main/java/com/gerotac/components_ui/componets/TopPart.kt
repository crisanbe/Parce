package com.gerotac.components_ui.componets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.gerotac.components_ui.R.*

@Composable
fun TopPart(
    onClickAction: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .width(184.dp)
    ) {
        Image(
            painter = painterResource(id = drawable.ic_top_part),
            contentDescription = null
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 70.dp, start = 20.dp)
        ) {
            IconButton(onClick = { onClickAction() }) {
                Icon(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF21120B))
                        .scale(scale = 0.6f),
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
fun TopPartLogin(
    modifier: Modifier,
    onClickAction: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .width(184.dp)
    ) {
        Image(
            painter = painterResource(id = drawable.ic_top_part),
            contentDescription = null
        )
        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 70.dp, start = 20.dp)
        ) {
            IconButton(onClick = { onClickAction() }) {
                Icon(
                    modifier = modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF21120B))
                        .scale(scale = 0.6f),
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
fun TopBartDetailRequirement(
    upPress: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .width(184.dp)
    ) {
        Image(
            painter = painterResource(id = drawable.ic_top_part),
            contentDescription = null
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 70.dp, start = 20.dp)
        ) {
            IconButton(onClick = upPress) {
                Icon(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF21120B))
                        .scale(scale = 0.6f),
                    imageVector = mirroringBackIcon(),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
fun mirroringIcon(ltrIcon: ImageVector, rtlIcon: ImageVector): ImageVector =
    if (LocalLayoutDirection.current == LayoutDirection.Ltr) ltrIcon else rtlIcon

@Composable
fun mirroringBackIcon() = mirroringIcon(
    ltrIcon = Icons.Outlined.ArrowBack, rtlIcon = Icons.Outlined.ArrowForward
)

@Preview(showBackground = true)
@Composable
fun TopPartPreview() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .width(184.dp)
    ) {
        Image(
            painter = painterResource(id = drawable.ic_top_part),
            contentDescription = null
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 70.dp, start = 20.dp)
        ) {
            IconButton(onClick = { }) {
                Icon(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF21120B))
                        .scale(scale = 0.6f),
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    }
}
