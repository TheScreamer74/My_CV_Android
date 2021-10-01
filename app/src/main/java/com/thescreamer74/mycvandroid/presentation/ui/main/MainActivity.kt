package com.thescreamer74.mycvandroid.presentation.ui.main

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.thescreamer74.mycvandroid.R
import com.thescreamer74.mycvandroid.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {
    private val MY_PERMISSION_REQUEST_CODE_CALL_PHONE = 555

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: MainActivityBinding =
            DataBindingUtil.setContentView(this, R.layout.main_activity)

        val navController = (supportFragmentManager.findFragmentById(R.id.main_fragment_container_view) as NavHostFragment).navController
        binding.mainBottomNavigationView.setupWithNavController(navController)

        checkPermissions()

    }

    fun checkPermissions(){
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
}