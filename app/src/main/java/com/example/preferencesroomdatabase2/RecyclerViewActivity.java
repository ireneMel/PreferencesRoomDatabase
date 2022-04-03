package com.example.preferencesroomdatabase2;

import static com.example.preferencesroomdatabase2.MainActivity.BACKGROUND_KEY;
import static com.example.preferencesroomdatabase2.MainActivity.TEXT_KEY;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.preferencesroomdatabase2.databinding.ActivityRecyclerViewBinding;

import java.util.List;
import java.util.Objects;

public class RecyclerViewActivity extends AppCompatActivity {

    ActivityRecyclerViewBinding binding;
    private PrefViewModel prefViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecyclerViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final PrefAdapter adapter = new PrefAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // ViewModelProviders to associate your ViewModel with your UI controller
        prefViewModel = new ViewModelProvider(this).get(PrefViewModel.class);

        prefViewModel.getAllPrefs().observe(this, new Observer<List<TblPrefs>>() {
            @Override
            public void onChanged(List<TblPrefs> tblPrefs) {
                adapter.setPrefs(tblPrefs);
            }
        });

        Intent intent = getIntent();
        addToDb(intent.getIntExtra(BACKGROUND_KEY, R.color.white), intent.getIntExtra(TEXT_KEY, R.color.black));

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    private void addToDb(long key, long value) {
        Log.v("AAAAAAAAAAAA", key + " : " + value);
        TblPrefs pr = new TblPrefs(getColorName(key), getColorName(value));
        Log.v("AAAAAAAAAAAA", pr.getPrefKey() + " : " + pr.getPrefValue());
        prefViewModel.insert(pr);
        prefViewModel.insert(new TblPrefs("a", "b"));
    }

    private String getColorName(long a) {
        if (a == ContextCompat.getColor(this, R.color.white)) return "White";
        if (a == ContextCompat.getColor(this, R.color.black)) return "Black";
        if (a == ContextCompat.getColor(this, R.color.blue)) return "Blue";
        if (a == ContextCompat.getColor(this, R.color.cream)) return "Cream";
        if (a == ContextCompat.getColor(this, R.color.purple)) return "Purple";
        if (a == ContextCompat.getColor(this, R.color.green)) return "Green";
        if (a == ContextCompat.getColor(this, R.color.brown)) return "Brown";
        if (a == ContextCompat.getColor(this, R.color.dark_blue)) return "Dark blue";
        if (a == ContextCompat.getColor(this, R.color.dark_red)) return "Dark red";
        return "None";
    }


}