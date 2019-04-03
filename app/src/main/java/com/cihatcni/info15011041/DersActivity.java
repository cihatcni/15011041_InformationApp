package com.cihatcni.info15011041;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class DersActivity extends AppCompatActivity {

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
        myAdapter.setOnItemClickListener(new RecyclerViewClickListener() {
            @Override
            public void userItemClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(),DersBilgiActivity.class);
                intent.putExtra("lessonName",dersler.get(position).getDersAdı());
                intent.putExtra("stuCount",dersler.get(position).getOgrenciSayisi());
                intent.putExtra("lessonGrade",dersler.get(position).getDersNotu());
                startActivity(intent);
            }
        });


        recyclerView.setAdapter(myAdapter);

    }


}
