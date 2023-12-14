package com.example.puiyeeng_coleanam_mapd711_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Spinner
import android.widget.Switch
import com.example.puiyeeng_coleanam_mapd711_project.databinding.ActivityOrderBinding
import com.example.puiyeeng_coleanam_mapd711_project.databinding.ActivityOurLocationsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class OurLocationsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityOurLocationsBinding


    private lateinit var mMap: GoogleMap

    // Create Store Class
    class Store(var city: String, var name: String, var address: String, var lat:Double, var long:Double, var openingHours: String)

    // Create a list for Store objects
    var storeList = mutableListOf<Store>()

    // Add Store function
    fun addStore(city: String, name: String, address: String, lat:Double, long:Double, openingHours: String) {
        val store = Store(city, name, address, lat, long, openingHours)
        storeList.add(store)
    }

    // Get store function
    fun getStoreByCity(city: String): List<Store>? {
        val specificStores = mutableListOf<Store>()
        for (store in storeList) {
            if (store.city == city) {
                specificStores.add(store)
            }
        }
        return specificStores
    }

    // Create City Class
    class City(var city: String, var lat:Double, var long:Double)

    // Create a list to store City objects
    var cityList = mutableListOf<City>()

    // Add City function
    fun addCity(city: String, lat:Double, long:Double) {
        val city = City(city, lat, long)
        cityList.add(city)
    }

    // Get City function
    fun getCityByName(city: String): City? {
        val city = cityList.find { it.city == city }
        return city
    }

    // create markerList to store marker history
    val markerList = mutableListOf<Marker>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOurLocationsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // Create and add City objects to the City list
        addCity("Toronto Downtown",43.651070,-79.347015)
        addCity("Yorkdale",43.725414, -79.452293)
        addCity("Mississauga",43.595310,-79.640579)
        addCity("Markham", 43.856098, -79.337021)


        // Create and add Store objects to the Store list
        addStore("Toronto Downtown", "Fashion Store Downtown", "220 Yonge St, Toronto, ON M5B 2L7",43.655695417686275, -79.38141813243658,"10:00 a.m. to 09:30 p.m.")
        addStore("Yorkdale", "Fashion Store Yorkdale", "3401 Dufferin St, North York, ON M6A 2T9",43.727326766989044, -79.4501061601766,"09:30 a.m. to 09:30 p.m.")
        addStore("Mississauga", "Fashion Store Mississauga", "100 City Centre Dr, Mississauga, ON L5B 2C9",43.59473442163362, -79.64439838778866,"09:30 a.m. to 09:30 p.m.")
        addStore("Markham", "Fashion Store Markham", "5000 Hwy 7, Markham, ON L3R 4M9",43.868658935557214, -79.28872613242464,"09:30 a.m. to 09:30 p.m.")


        // when search button is pressed
        binding.search.setOnClickListener {
            var selectedCity = binding.citySpinner.getSelectedItem().toString();

            mapFragment.getMapAsync { googleMap ->
                mMap = googleMap

                // Remove all previous markers
                for (marker in markerList) {
                    marker.remove()
                }
                markerList.clear()

                // Adds custom info window to markers that have been clicked
                mMap.setInfoWindowAdapter(CustomInfoWindowAdapter(this))

                // Get the selected city lat long and move the camera to the selected city
                val city = getCityByName(selectedCity)
                val lat = city?.lat
                val long = city?.long
                if (lat != null && long != null) {
                    val location = LatLng(lat, long)
                    // move the camera and zoom the map
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 12f))
                }

                // Get the selected city's stores and add the marker
                val stores = getStoreByCity(selectedCity)
                if (stores != null) {
                    for (store in stores){
                        val lat = store?.lat
                        val long = store?.long
                        val name = store?.name
                        if (name!= null && lat != null && long != null) {

                            val location = LatLng(lat, long)
                            // Add markers and add into markerList for record
                            mMap.addMarker(MarkerOptions().position(location).title(name).snippet(store.address + "\n" + lat + " " + long + "\n" + "Opening Hours: " + store.openingHours))
                                ?.let { it1 -> markerList.add(it1) }

                        }
                    }
                }
            }
        }
        val mapTypeSwitch: Switch = findViewById(R.id.mapTypeSwitch)
        mapTypeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                mapFragment.getMapAsync { googleMap ->
                    mMap = googleMap
                    mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
                }
            } else {
                mapFragment.getMapAsync { googleMap ->
                    mMap = googleMap
                    mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
                }
            }
        }
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.mapType = GoogleMap.MAP_TYPE_NORMAL

        mMap.setInfoWindowAdapter(CustomInfoWindowAdapter(this))

        // Add a marker in Toronto and move the camera
        val yourLocation = LatLng(43.7852, -79.2282)
        mMap.addMarker(MarkerOptions().position(yourLocation).title("Centennial College").snippet("941 Progress Ave " + "\n" + "43.7852 " + "-79.2282" + "\n" + "Opening Hours: 09:00 a.m.– 04:30 p.m."))
            ?.let { it1 -> markerList.add(it1) }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(yourLocation, 10f))

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.women -> {
                startActivity(Intent(this, WomenListingActivity::class.java))
                true
            }
            R.id.men -> {
                startActivity(Intent(this, MenListingActivity::class.java))
                true
            }
            R.id.ourLocations -> {
                startActivity(Intent(this, OurLocationsActivity::class.java))
                true
            }
            R.id.fashionGuide -> {
                startActivity(Intent(this, FashionGuideActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
        //return super.onOptionsItemSelected(item)
    }
}