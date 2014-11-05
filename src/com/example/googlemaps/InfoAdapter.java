package com.example.googlemaps;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

public class InfoAdapter implements InfoWindowAdapter {

	private List<Restaurante> restaurantes;
	private LayoutInflater inflater;	

	public InfoAdapter(List<Restaurante> restaurantes, LayoutInflater inflater) {
		this.restaurantes = restaurantes;
		this.inflater = inflater;
	}

	@Override
	public View getInfoContents(Marker marker) {
		// Getting view from the layout file info_window_layout
        View v = null;
        
        for(Restaurante restaurante : restaurantes){
        	if(restaurante.getLatitude().equals(marker.getPosition().latitude)
        			&& restaurante.getLongitude().equals(marker.getPosition().longitude)){
        	    
        		v = inflater.inflate(R.layout.info_windows, null);
        		
                TextView nome = (TextView) v.findViewById(R.id.nome);
                TextView cidade = (TextView) v.findViewById(R.id.cidade);
                WebView link = (WebView) v.findViewById(R.id.link);
        		
                nome.setText("Nome:" + restaurante.getNome());
                cidade.setText("Cidade:" + restaurante.getCidade());
                link.loadData("<html><body><a href='http://google.com'>Acesse a rota</a></body></html>", "text/html", "UTF-8");
        		
        	}
        }
       
        return v;
	}

	@Override
	public View getInfoWindow(Marker arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
