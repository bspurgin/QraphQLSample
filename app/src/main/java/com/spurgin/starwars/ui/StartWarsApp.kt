import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.spurgin.starwars.ui.screens.PeopleListScreen
import com.spurgin.starwars.ui.screens.PersonDetailScreen
import com.spurgin.starwars.viewmodel.MainViewModel

@Composable
fun StarWarsApp(mainViewModel: MainViewModel) {
    val navController = rememberNavController()

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

