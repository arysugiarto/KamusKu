package com.example.arysugiarto.mykamuss.ViewHolder;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.arysugiarto.mykamuss.DetailAcivity;
import com.example.arysugiarto.mykamuss.Model.KamusModel;
import com.example.arysugiarto.mykamuss.R;

public class SearchViewHolder extends RecyclerView.ViewHolder {

    TextView tvKosakata, tvArti;

    public SearchViewHolder(View itemView) {
        super(itemView);

        tvKosakata  = itemView.findViewById(R.id.tvKosakata);
        tvArti      = itemView.findViewById(R.id.tvArti);
    }

    public void bind(final KamusModel kamusModel) {
        tvKosakata.setText(kamusModel.getKoskata());
        tvArti.setText(kamusModel.getDeskripsi());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(itemView.getContext(), DetailAcivity.class);
                intent.putExtra(DetailAcivity.ITEM_KOSAKATA, kamusModel.getKoskata());
                intent.putExtra(DetailAcivity.ITEM_ARTI, kamusModel.getDeskripsi());
                intent.putExtra(DetailAcivity.ITEM_CATEGORY, kamusModel.getKategori());
                itemView.getContext().startActivity(intent);
            }
        });
    }
}
