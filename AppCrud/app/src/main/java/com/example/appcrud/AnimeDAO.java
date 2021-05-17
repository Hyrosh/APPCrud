package com.example.appcrud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class AnimeDAO {

    public static void inserir(Anime anime, Context context){
        ContentValues valores = new ContentValues();
        valores.put("nome", anime.nome );
        valores.put("categoria", anime.categoria );
        valores.put("ano", anime.getAno() );

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.insert("anime", null, valores);
    }

    public static void editar(Anime anime, Context context){
        ContentValues valores = new ContentValues();
        valores.put("nome", anime.nome );
        valores.put("categoria", anime.categoria );
        valores.put("ano", anime.getAno() );

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.update("anime", valores, " id = " + anime.id , null );
    }

    public static void excluir(int id, Context context){
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();
        db.delete("anime", " id = " + id , null);
    }

    public static List<Anime> getAnimes(Context context){
        List<Anime> lista = new ArrayList<>();
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, nome, categoria, ano FROM anime ORDER BY nome", null );
        if( cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do{
                Anime anime = new Anime();
                anime.id = cursor.getInt( 0);
                anime.nome = cursor.getString(1);
                anime.categoria = cursor.getString(2);
                anime.setAno( cursor.getInt(3) );
                lista.add(anime);
            }while( cursor.moveToNext() );
        }
        return lista;
    }

    public static Anime getAnimeById(Context context, int id){
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, nome, categoria, ano FROM anime WHERE id = " + id, null );
        if( cursor.getCount() > 0 ){
            cursor.moveToFirst();
            Anime anime = new Anime();
            anime.id = cursor.getInt( 0);
            anime.nome = cursor.getString(1);
            anime.categoria = cursor.getString(2);
            anime.setAno( cursor.getInt(3) );
            return anime;
        }else{
            return null;
        }
    }



}