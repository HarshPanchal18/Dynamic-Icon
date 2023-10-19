package com.example.dynamic_app_icon

import android.content.ComponentName
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dynamic_app_icon.ui.theme.DynamicAppIconTheme

class MainActivity : ComponentActivity() {

    private val mainActivity = BuildConfig.main_activity
    private val mainActivityAlias = BuildConfig.main_activity_alias

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DynamicAppIconTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Screen(
                        on30Click = {
                            changeEnabledComponent(
                                enabled = mainActivity,
                                disabled = mainActivityAlias
                            )
                        },
                        on60Click = {
                            changeEnabledComponent(
                                enabled = mainActivityAlias,
                                disabled = mainActivity
                            )
                        }
                    )
                }
            }
        }
    }

    private fun changeEnabledComponent(
        enabled: String,
        disabled: String
    ) {
        packageManager.setComponentEnabledSetting(
            ComponentName(this@MainActivity, enabled),
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
            PackageManager.DONT_KILL_APP
        )

        packageManager.setComponentEnabledSetting(
            ComponentName(this@MainActivity, disabled),
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            PackageManager.DONT_KILL_APP
        )
    }

    @Composable
    private fun Screen(
        on30Click: () -> Unit = {},
        on60Click: () -> Unit = {}
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Button(
                onClick = { on30Click() },
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 16.dp)
            ) {
                Text(text = "30")
            }

            Button(
                onClick = { on60Click() },
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 16.dp)
            ) {
                Text(text = "60")
            }
        }
    }
}
