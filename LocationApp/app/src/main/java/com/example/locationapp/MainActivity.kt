package com.example.locationapp

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.locationapp.ui.theme.LocationAppTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: LocationViewModel = viewModel()
            LocationAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    MyApp(viewModel)
                }
            }
        }
    }
}

@Composable
fun MyApp(viewModel: LocationViewModel) {
    val context = LocalContext.current
    val locationUtils = LocationUtils(context)
    LocationDisplay(viewModel, locationUtils, context)
}

@Composable
fun LocationDisplay(
    viewModel: LocationViewModel,
    locationUtils: LocationUtils,
    context: Context
) {
    val location = viewModel.location.value
    val address = location?.let {
        locationUtils.reverseGeocodeLocation(it)
    }
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = {permissions ->
            if(permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
                && permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true) {
                // I have access to locations
                locationUtils.requestLocationUpdates(viewModel)
            } else {
                // ASK for permission
                val rationalRequired = ActivityCompat.shouldShowRequestPermissionRationale(
                    context as MainActivity,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) || ActivityCompat.shouldShowRequestPermissionRationale(
                    context as MainActivity,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
                if(rationalRequired) {
                    Toast.makeText(context, "Location Permission is required for this app", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(context, "Location Permission is required, Please enable it in android settings", Toast.LENGTH_LONG).show()
                }
            }
        }
    )

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        if(location != null) {
            Text(text = "Latitude: ${location.latitude}, Longitude: ${location.longitude} \n Address: $address")
        }
        else {
            Text(text = "Location not available")
        }
        Button(
            onClick = {
                if(locationUtils.hasLocationPermission(context)) {
                    // Permission already granted update the location
                    locationUtils.requestLocationUpdates(viewModel)
                } else {
                    // Request location permission
                    requestPermissionLauncher.launch(
                        arrayOf(
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        )
                    )
                }
            }
        ) {
            Text(text = "Get Location")
        }
    }
}