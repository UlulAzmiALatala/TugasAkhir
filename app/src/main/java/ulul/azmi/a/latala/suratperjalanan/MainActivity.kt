package ulul.azmi.a.latala.suratperjalanan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.compose.*
import ulul.azmi.a.latala.suratperjalanan.data.TripData
import ulul.azmi.a.latala.suratperjalanan.ui.screens.AddTripScreen
import ulul.azmi.a.latala.suratperjalanan.ui.screens.TripDetailScreen
import ulul.azmi.a.latala.suratperjalanan.ui.screens.TripListScreen

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: TripViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TripViewModel::class.java)

        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "tripList") {
                composable("tripList") {
                    TripListScreen(
                        trips = viewModel.tripList,
                        onAddTripClick = { navController.navigate("addTrip") },
                        onDeleteTrip = viewModel::deleteTrip,
                        onEditTrip = viewModel::editTrip,
                        onDetailTrip = { id -> navController.navigate("tripDetail/$id") },
                        navController = navController
                    )
                }
                composable("addTrip") {
                    AddTripScreen(
                        onSaveTrip = { nama, tujuan, tanggal, keterangan ->
                            viewModel.saveTrip(nama, tujuan, tanggal, keterangan)
                            navController.navigate("tripList")
                        },
                        onCancel = { navController.navigate("tripList") },
                        navController = navController
                    )
                }
                composable("tripDetail/{id}") { backStackEntry ->
                    val tripId = backStackEntry.arguments?.getString("id")?.toIntOrNull()
                    val trip = viewModel.getTripById(tripId)
                    trip?.let {
                        TripDetailScreen(
                            trip = it,
                            onBackClick = { navController.navigateUp() },
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}
