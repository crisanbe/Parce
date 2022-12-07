package com.gerotac.auth.profileUser.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.gerotac.auth.requirement.di.HeaderRequirement
import com.gerotac.auth.requirement.presentation.ui.homerequirement.listrequirement.mToast
import com.gerotac.components_ui.componets.drawer.DrawerScreens
import com.gerotac.components_ui.componets.drawer.screen

@Composable
fun Drawer(
    onClickDestination: (screen: String) -> Unit,
    navController: NavController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF21120B)),
        verticalArrangement = Arrangement.Top
    ) {
        Column(verticalArrangement = Arrangement.Top) {
            Image(
                painterResource(id = com.gerotac.components_ui.R.drawable.logo_parce_white),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-50).dp)
                    .clip(CircleShape)
            )
            Text(
                modifier = Modifier
                    .offset(y = (-130).dp)
                    .padding(horizontal = 95.dp),
                text = "${HeaderRequirement.getEmailUser()["email"]}",
                style = androidx.compose.ui.text.TextStyle(
                    color = colorResource(id = com.gerotac.components_ui.R.color.secondary)
                ),
                maxLines = 1,
                fontSize = 15.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = (-100).dp)
        ) {
            screen.forEach { screen ->
                DrawerItem(
                    item = screen,
                    selected = currentRoute == screen.route,
                    onItemClick = { onClickDestination(screen.route) },
                )
            }
        }
    }
    Divider()
}

@Composable
fun DrawerItem(
    item: DrawerScreens,
    selected: Boolean,
    onItemClick: (DrawerScreens) -> Unit,
) {
    val context = LocalContext.current
    val background = if (selected) Color(0xFFFFCA4C) else Color.Transparent
    Divider()
    if (HeaderRequirement.getRol()["rol"] == "empresa"
        || HeaderRequirement.getRol()["rol"] == "docente"
        || HeaderRequirement.getRol()["rol"] == "estudiante"
    ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {

                        if (item.route == "Report"){mToast(context,"No posse permisos de administador🔑")}else{
                            onItemClick(item)
                        }
                    }
                    .height(45.dp)
                    .background(background)
                    .padding(start = 10.dp)
            ) {
                Icon(
                    tint = Color.White,
                    imageVector = item.IconDrawer,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = item.title,
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold
                )
        }
    }else{
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onItemClick(item) }
                .height(45.dp)
                .background(background)
                .padding(start = 10.dp)
        ) {
            Icon(
                tint = Color.White,
                imageVector = item.IconDrawer,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = item.title,
                fontSize = 16.sp,
                color = Color.White,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
    Divider()
}
