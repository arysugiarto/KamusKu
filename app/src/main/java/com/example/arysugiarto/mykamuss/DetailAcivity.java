package com.example.arysugiarto.mykamuss;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailAcivity extends AppCompatActivity {
    public static final String ITEM_KOSAKATA    = "kosakata";
    public static final String ITEM_ARTI        = "arti";
    public static final String ITEM_CATEGORY    = "category";

    TextView tvKosakata, tvArti;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_acivity);

        getSupportActionBar().setTitle("Detail Kata");
        getSupportActionBar().setSubtitle(getIntent().getStringExtra(ITEM_CATEGORY));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvKosakata  = findViewById(R.id.tvDetailkata);
        tvArti      = findViewById(R.id.tvDetailarti);

        tvKosakata.setText(getIntent().getStringExtra(ITEM_KOSAKATA));
        tvArti.setText(getIntent().getStringExtra(ITEM_ARTI));

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
