package com.smartsatu.android.live.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI

class ExampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)
        NavigationUI.setupActionBarWithNavController(this, findNavController(R.id.navigationHostFragment))
    }

    override fun onSupportNavigateUp(): Boolean {

        findNavController(R.id.navigationHostFragment).popBackStack()

        return super.onSupportNavigateUp()
    }
}
