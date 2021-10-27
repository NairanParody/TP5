package com.example.tp5.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tp5.Models.Pokemon;
import com.example.tp5.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PokemonAdapter extends BaseAdapter {

    private List<Pokemon> listaPokemon;

    public PokemonAdapter(List<Pokemon> listaPokemon){ this.listaPokemon = listaPokemon; }

    @Override
    public int getCount() {
        return listaPokemon.size();
    }

    @Override
    public Pokemon getItem(int position) {
        return listaPokemon.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View vista;

        if (convertView == null){
            vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon_adapter, parent, false);
        }else {
            vista = convertView;
        }

        Pokemon item = getItem(position);

        TextView textItemNombre = vista.findViewById(R.id.textItemNombre);
        TextView textItemTipo = vista.findViewById(R.id.textItemTipo);
        ImageView imageItemFoto = vista.findViewById(R.id.imageItemFoto);

        textItemNombre.setText(item.getNombre());
        textItemTipo.setText((item.getTipo() +", "+item.getTipo2()));
        Picasso.get()
                .load(item.getFoto())
                .into(imageItemFoto);

        return vista;
    }
}
