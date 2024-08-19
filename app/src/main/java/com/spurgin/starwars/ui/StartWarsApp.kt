import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.spurgin.starwars.api.ApolloClientInstance
import com.spurgin.starwars.repository.StarWarsRepository
import com.spurgin.starwars.ui.screens.PeopleListScreen
import com.spurgin.starwars.ui.screens.PersonDetailScreen
import com.spurgin.starwars.viewmodel.MainViewModel
import com.spurgin.starwars.viewmodel.ViewModelFactory

@Composable
fun StarWarsApp() {
    val navController = rememberNavController()
    val mainViewModel: MainViewModel = viewModel(
        factory = ViewModelFactory(StarWarsRepository(ApolloClientInstance.client))
    )

    NavHost(navController = navController, startDestination = "peopleList") {
        composable("peopleList") {
            PeopleListScreen(navController = navController, mainViewModel)
        }
        composable("personDetail/{personId}") { backStackEntry ->
            val personId = backStackEntry.arguments?.getString("personId")
            personId?.let {
                PersonDetailScreen(navController = navController, personId = it, mainViewModel)
            }
        }
    }
}

