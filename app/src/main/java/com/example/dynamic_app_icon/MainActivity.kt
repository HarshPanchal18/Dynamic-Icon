package com.example.dynamic_app_icon

import android.content.ComponentName
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
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
    private val mainActivityAlias0 = BuildConfig.main_activity_alias_zero
    private val mainActivityAlias1 = BuildConfig.main_activity_alias_one

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
                                disabled0 = mainActivityAlias0,
                                disabled1 = mainActivityAlias1
                            )
                        },
                        on60Click = {
                            changeEnabledComponent(
                                enabled = mainActivityAlias0,
                                disabled0 = mainActivity,
                                disabled1 = mainActivityAlias1
                            )
                        },
                        onCloudClick = {
                            changeEnabledComponent(
                                enabled = mainActivityAlias1,
                                disabled0 = mainActivity,
                                disabled1 = mainActivityAlias0
                            )
                        }
                    )
                }
            }
        }
    }

    private fun changeEnabledComponent(
        enabled: String,
        disabled0: String,
        disabled1: String
    ) {
        packageManager.setComponentEnabledSetting(
            ComponentName(this@MainActivity, enabled),
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
            PackageManager.DONT_KILL_APP
        )

        packageManager.setComponentEnabledSetting(
            ComponentName(this@MainActivity, disabled0),
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            PackageManager.DONT_KILL_APP
        )

        packageManager.setComponentEnabledSetting(
            ComponentName(this@MainActivity, disabled1),
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            PackageManager.DONT_KILL_APP
        )
    }

    @Composable
    private fun Screen(
        on30Click: () -> Unit = {},
        on60Click: () -> Unit = {},
        onCloudClick: () -> Unit = {}
    ) {
        Row(
            modifier = Modifier
                .padding(all = 16.dp)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = { on30Click() }) {
                Text(text = "30")
            }

            Button(onClick = { on60Click() }) {
                Text(text = "60")
            }

            Button(onClick = { onCloudClick() }) {
                Icon(imageVector = Icons.Default.CloudCircle, contentDescription = null)
            }
        }
    }
}
