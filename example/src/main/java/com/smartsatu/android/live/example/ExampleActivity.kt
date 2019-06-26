package com.smartsatu.android.live.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.snackbar.Snackbar

class ExampleActivity : AppCompatActivity() {

    private val sharedViewModel by lazy {
        ViewModelProviders.of(this).get(SharedViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)
        NavigationUI.setupActionBarWithNavController(this, findNavController(R.id.navigationHostFragment))

        sharedViewModel.listener.observe(this, Observer { callback ->
            // Empty listener for events designated to the
            when (callback) {
                is SharedViewModel.Callback.ShowToast -> {
                    supportFragmentManager.findFragmentById(R.id.navigationHostFragment)?.view?.let {
                        Snackbar.make(it, "Activity's: ${callback.toast}", Snackbar.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {

        findNavController(R.id.navigationHostFragment).popBackStack()

        return super.onSupportNavigateUp()
    }
}
