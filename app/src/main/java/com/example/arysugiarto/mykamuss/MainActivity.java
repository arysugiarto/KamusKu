package com.example.arysugiarto.mykamuss;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.arysugiarto.mykamuss.Adapter.SearchAdapter;
import com.example.arysugiarto.mykamuss.Helper.KamusHelper;
import com.example.arysugiarto.mykamuss.Model.KamusModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,SearchView.OnQueryTextListener {
    private KamusHelper kamusHelper;
    private SearchAdapter searchAdapter;

    private ArrayList<KamusModel> list = new ArrayList<>();

    RecyclerView recyclerView;
    android.widget.SearchView searchView;
    String lang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);

        recyclerView = findViewById(R.id.recycler_view);

        searchView = findViewById(R.id.searchbar);
        searchView.onActionViewExpanded();
        searchView.setOnQueryTextListener(this);

        kamusHelper = new KamusHelper(this);
        searchAdapter = new SearchAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(searchAdapter);

        lang = "Eng";
        getData(lang, "");
    }
    private void getData(String selection,  String search) {
        try {
            kamusHelper.open();
            if (search.isEmpty()) {
                list = kamusHelper.getAllData(selection);
            } else {
                list = kamusHelper.getDataByName(search, selection);
            }

            String title = null;
            String hint = null;
            if (selection == "Eng") {
                title   = getResources().getString(R.string.ing_ind);
                hint    = getResources().getString(R.string.search);
            } else {
                title = getResources().getString(R.string.ind_ing);
                hint    = getResources().getString(R.string.cari);
            }
            getSupportActionBar().setSubtitle(title);
            searchView.setQueryHint(hint);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            kamusHelper.close();
        }
        searchAdapter.replaceAll(list);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.nav_ing) {
            lang = "Eng";
            getData(lang, "");
        } else if (id == R.id.nav_ind) {
            lang = "Ind";
            getData(lang, "");
        }
        else if (id == R.id.nav_about) {
            Intent intent = new Intent(MainActivity.this,AboutActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String keyword) {
        getData(lang, keyword);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String keyword) {
        getData(lang, keyword);
        return false;
    }
}
