package com.thescreamer74.mycvandroid.presentation.ui.main

import android.Manifest
import android.content.DialogInterface
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.thescreamer74.mycvandroid.R
import com.thescreamer74.mycvandroid.databinding.MainActivityBinding
import com.thescreamer74.mycvandroid.network.CvServerMotivationalApi
import com.thescreamer74.mycvandroid.network.CvServerRetrofitInterface
import org.koin.android.ext.android.inject
import org.koin.core.component.KoinApiExtension
import org.koin.core.parameter.parametersOf

@KoinApiExtension
class MainActivity : AppCompatActivity(), CvServerRetrofitInterface {
    private val MY_PERMISSION_REQUEST_CODE_CALL_PHONE = 555
    private val viewModel: MainActivityViewModel by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: MainActivityBinding =
            DataBindingUtil.setContentView(this, R.layout.main_activity)

        val navController = (supportFragmentManager.findFragmentById(R.id.main_fragment_container_view) as NavHostFragment).navController
        binding.mainBottomNavigationView.setupWithNavController(navController)
        checkPermissions()

        viewModel.delegate = this
    }

    private fun checkPermissions(){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) { // 23

            // Check if we have Call permission
            val sendSmsPermission = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE)

            if (sendSmsPermission != PackageManager.PERMISSION_GRANTED) {
                // If don't have permission so prompt the user.
                this.requestPermissions(
                    arrayOf(Manifest.permission.CALL_PHONE),
                    MY_PERMISSION_REQUEST_CODE_CALL_PHONE
                )
                return
            }
        }
    }

    @KoinApiExtension
    override fun onResult() {
        if (!viewModel.isDialogComplete){
            val builder = AlertDialog.Builder(this)
                .setTitle("Bienvenue !")
                .setMessage(viewModel.motivational.value?.motivational)
                .setPositiveButton(
                    "J'ai compris"
                ) { dialog, which ->
                    dialog.dismiss()
                    viewModel.isDialogComplete = true
                }

            val dialog = builder.create()
            dialog.window?.attributes?.windowAnimations = R.style.DialogTheme
            dialog.show()
        }
    }
}