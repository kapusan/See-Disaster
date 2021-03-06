package id.rackspira.seedisaster.ui.main

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Gravity
import android.view.MenuItem
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import id.rackspira.seedisaster.R
import id.rackspira.seedisaster.ui.main.home.HomeFragment
import id.rackspira.seedisaster.ui.main.infoglobal.GlobalFragment
import id.rackspira.seedisaster.ui.main.petabencana.PetaBencanaFragment
import id.rackspira.seedisaster.ui.main.posko.PoskoSayaFragment
import id.rackspira.seedisaster.ui.tentang.TentangFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val homeFragment = HomeFragment()
    private val globalFragment = GlobalFragment()
    private val petaFragment = PetaBencanaFragment()
    private val poskoSaya = PoskoSayaFragment()
    private val tentangApps = TentangFragment()
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUp()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.lastLocation
        }

    }

    private fun setUp() {
        setSupportActionBar(toolbarMain)
        val actionbarDrawer = ActionBarDrawerToggle(this, drawerLayout, toolbarMain, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(actionbarDrawer)
        actionbarDrawer.syncState()
        navigationMain.setNavigationItemSelectedListener(this)
        setFragment(homeFragment)
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when (p0.itemId) {
            R.id.beranda -> {
                setFragment(homeFragment)
                drawerLayout.closeDrawer(Gravity.START)
            }
            R.id.cuaca -> {
                setFragment(petaFragment)
                drawerLayout.closeDrawer(Gravity.START)
            }
            R.id.global -> {
                setFragment(globalFragment)
                drawerLayout.closeDrawer(Gravity.START)
            }
            R.id.posko -> {
                setFragment(poskoSaya)
                drawerLayout.closeDrawer(Gravity.START)
            }
            R.id.tentang -> {
                setFragment(tentangApps)
                drawerLayout.closeDrawer(Gravity.START)
            }
        }
        return true
    }

    private fun setFragment(fragment: Fragment){
        val trans = supportFragmentManager.beginTransaction()
        trans.replace(R.id.containerFragment, fragment)
        trans.commit()
    }

}
