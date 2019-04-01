package com.cihatcni.info15011041;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Ders> dersler;


    public MyAdapter(List<Ders> dersler) {
        this.dersler = dersler;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView dersAdiText;
        public TextView dersNotuText;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            dersAdiText = v.findViewById(R.id.dersAdiText);
            dersNotuText = v.findViewById(R.id.harfNotuText);
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.derslistlayout,viewGroup,false);

        ViewHolder vh = new ViewHolder(view);
        return vh;

    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        final String dersAdi = dersler.get(position).getDersAdı();
        final String harfNotu = dersler.get(position).getDersNotu();
        viewHolder.dersAdiText.setText(dersAdi);
        viewHolder.dersNotuText.setText(harfNotu);

    }

    @Override
    public int getItemCount() {
        return dersler.size();
    }


}
