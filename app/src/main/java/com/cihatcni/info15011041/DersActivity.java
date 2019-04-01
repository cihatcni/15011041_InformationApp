package com.cihatcni.info15011041;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class DersActivity extends AppCompatActivity implements RecyclerViewClickListener {
    private RecyclerView.LayoutManager layaoutManager;
    private List<Ders> dersler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ders);

        dersler = new ArrayList<>();
        Ders ders= new Ders("BBG","AA",120);
        dersler.add(ders);
        ders= new Ders("ALGO","BA",150);
        dersler.add(ders);
        ders= new Ders("PROGRAMLAMAYA GİRİŞ","CB",100);
        dersler.add(ders);
        ders= new Ders("ASSEMBLY","DC",200);
        dersler.add(ders);
        ders= new Ders("DONANIM","DD",160);
        dersler.add(ders);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);

        layaoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layaoutManager);

        MyAdapter myAdapter = new MyAdapter(dersler);
        recyclerView.setAdapter(myAdapter);

    }


    @Override
    public void recyclerViewListClicked(View v, int position) {

    }
}
