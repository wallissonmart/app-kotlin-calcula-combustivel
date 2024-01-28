package com.example.calculacombustivel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.calculacombustivel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val homeFragment = HomeFragment()
    private val gasolinaEtanolFragment = GasolinaEtanolFragment()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onNavigationItemSelectedListener()
        switchFragment(homeFragment)
    }

    private fun onNavigationItemSelectedListener() {
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menuHome -> {
                    switchFragment(homeFragment)
                    return@setOnItemSelectedListener true
                }

                R.id.menuGasolinaEtanol -> {
                    switchFragment(gasolinaEtanolFragment)
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }

    private fun switchFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(binding.container.id, fragment).commit()
    }
}
