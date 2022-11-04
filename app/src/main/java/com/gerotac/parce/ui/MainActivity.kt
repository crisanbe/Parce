package com.gerotac.parce.ui

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import androidx.preference.PreferenceManager
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.gerotac.auth.login.presentation.components.logincomposables.userDataStore
import com.gerotac.auth.login.presentation.components.logincomposables.userRepo
import com.gerotac.auth.protodata.ProtoUserRepoImpl
import com.gerotac.parce.ui.providers.LocalNavHost
import com.gerotac.parce.ui.theme.ParceTheme
import com.gerotac.parce.ui.navegation.holder.HolderScreen
import com.gerotac.parce.util.LocalScreenSize
import com.gerotac.parce.util.getScreenSize
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalAnimationApi
@AndroidEntryPoint
@ExperimentalCoilApi
@ExperimentalPermissionsApi
@ExperimentalPagerApi
class MainActivity : ComponentActivity() {
    override fun onConfigurationChanged(newConfig: Configuration) {
        newConfig.let { super.onConfigurationChanged(it) } }

    private val viewModelMain by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val context = LocalContext.current
            userRepo = ProtoUserRepoImpl(context.userDataStore)
            val defaultStatusBarColor = MaterialTheme.colors.background.toArgb()
            var statusBarColor by remember { mutableStateOf(defaultStatusBarColor) }
            window.statusBarColor = statusBarColor
            val navController = rememberNavController()
            val size = LocalContext.current.getScreenSize()
            val prefs = PreferenceManager.getDefaultSharedPreferences(context)
            val prefsKey = prefs.getBoolean("key", false)

            ParceTheme() {
                CompositionLocalProvider(
                    LocalScreenSize provides size,
                    LocalNavHost provides navController
                )
                {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    )
                    {
                        viewModelMain.main(navController, prefsKey)
                        HolderScreen(
                            onStatusBarColorChange = {
                                statusBarColor = it.toArgb()
                            }
                        )
                    }
                }
            }
        }
    }
}
