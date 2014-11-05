package com.example.googlemaps;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;

public class MainActivity extends Activity implements ConnectionCallbacks, OnConnectionFailedListener, LocationListener {
//public class MainActivity extends Activity{
	private GoogleMap map;
	private LocationClient locationClient;
	private LocationRequest locationRequest;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		

		locationClient = new LocationClient(this, this, this);

		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		
		List<Restaurante> restaurantes = Arrays.asList(new Restaurante("Braseiro", "Vargem", -23.608616, -49.697124),
				new Restaurante("Varanda", "Sao Joao", -23.608617, -49.697123));
		
		map.setInfoWindowAdapter(new InfoAdapter(restaurantes, getLayoutInflater()));
		
		LatLng latLng = new LatLng(-23.608616, -49.697124);
		MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("QConf SP").snippet("Apenas um teste").draggable(true);
		
		map.addMarker(markerOptions);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {		
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onConnectionFailed(ConnectionResult arg0) {

	}

	@Override
	public void onConnected(Bundle arg0) {

		locationRequest = LocationRequest.create();
		locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
		locationRequest.setInterval(5000);

		locationClient.requestLocationUpdates(locationRequest, this);

		Location location = locationClient.getLastLocation();
		
		LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
		MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("Eu").snippet("Minha localização").draggable(true);
		map.addMarker(markerOptions);

		map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13));
	}

	@Override
	public void onDisconnected() {

	}

	@Override
	protected void onPause() {
		locationClient.disconnect();
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		locationClient.connect();

	}

	@Override
	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public void onLocationChanged(Location loc) {
//
//		//map.clear();		
//		LatLng meuLocal = new LatLng(loc.getLatitude(), loc.getLongitude());
//		MarkerOptions opcoesDoMeuLocal = new MarkerOptions().position(meuLocal).title("Eu").snippet("Minha localização").draggable(true);
//		map.addMarker(opcoesDoMeuLocal);
//		
//		CircleOptions circleOptions = new CircleOptions().center(meuLocal).radius(1000); 
//		
//		map.addCircle(circleOptions);
//						
//		map.animateCamera(CameraUpdateFactory.newLatLngZoom(meuLocal, 12));
//
//	}

}
