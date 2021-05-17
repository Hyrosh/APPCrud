package com.example.appcrud;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class AdapterAnime {

    private List<Anime> animeList;
    private Context context;
    private LayoutInflater inflater;

    public AdapterAnime(Context context, List<Anime> listaAnimes){
        this.animeList = listaAnimes;
        this.context = context;
        this.inflater = LayoutInflater.from( context );
    }

    @Override
    public int getCount() {
        return animeList.size();
    }

    @Override
    public Object getItem(int i) {
        return animeList.get( i );
    }

    @Override
    public long getItemId(int i) {
        return animeList.get(i).id;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        AdapterAnime.ItemSuporte item;

        if( convertView == null){
            convertView = inflater.inflate(R.layout.layout_lista, null);

            item = new AdapterAnime.ItemSuporte();
            item.tvNome = convertView.findViewById(R.id.tvListaNome);
            item.tvCategoria = convertView.findViewById(R.id.tvListaCategoria);
            item.tvAno = convertView.findViewById(R.id.tvListaAno);
            item.layout = convertView.findViewById(R.id.llFundoLista);
            convertView.setTag( item );
        }else {
            item = (AdapterAnime.ItemSuporte) convertView.getTag();
        }

        Anime anime = animeList.get(i);
        item.tvNome.setText(  anime.nome );
        item.tvCategoria.setText(  anime.categoria );
        item.tvAno.setText(  String.valueOf( anime.getAno() ) );

        if( i % 2 == 0 ){
            item.layout.setBackgroundColor(Color.rgb(230, 230, 230));
        }else {
            item.layout.setBackgroundColor( Color.WHITE );
        }
        return convertView;
    }

    private class ItemSuporte{
        TextView tvNome, tvCategoria, tvAno;
        LinearLayout layout;
    }



}
