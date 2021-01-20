package ng.mathemandy.ulesson.ui

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import ng.mathemandy.ulesson.R
import ng.mathemandy.ulesson.databinding.ActivityDashboardBinding
import javax.inject.Inject
import javax.inject.Provider

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var navControllerProvider: Provider<NavController>

    private val navController: NavController
        get() = navControllerProvider.get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityDashboardBinding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        goFullScreen()
    }

    override fun onBackPressed() {
        if (!onSupportNavigateUp()) {
            super.onBackPressed()
        }
    }

    fun goFullScreen() {
        val window: Window = window
        val winParams: WindowManager.LayoutParams = window.getAttributes()
        winParams.flags =
            winParams.flags and WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS.inv()
        window.setAttributes(winParams)
        window.getDecorView()
            .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

    fun showSnackBar(
        rootView: View,
        text: String,
        isError: Boolean = false,
        duration: Int = Snackbar.LENGTH_SHORT
    ) {
        val snackBar = Snackbar.make(rootView, text, duration)
        val param = snackBar.view.layoutParams as? CoordinatorLayout.LayoutParams
        val snackBarLayout = snackBar.view as? Snackbar.SnackbarLayout
        if (isError) snackBarLayout?.setBackgroundColor(
            ContextCompat.getColor(
                this,
                R.color.colorPrimaryDark
            )
        ) else snackBarLayout?.setBackgroundColor(
            ContextCompat.getColor(
                this,
                R.color.colorPrimary
            )
        )
        snackBarLayout?.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
            ?.setTextColor(
                if (isError) ContextCompat.getColor(
                    this,
                    R.color.colorAccent
                ) else ContextCompat.getColor(this, R.color.colorAccent)
            )
        param?.gravity = Gravity.BOTTOM
        snackBar.view.layoutParams = param
        snackBar.duration = Snackbar.LENGTH_LONG
        snackBar.show()
    }
}
