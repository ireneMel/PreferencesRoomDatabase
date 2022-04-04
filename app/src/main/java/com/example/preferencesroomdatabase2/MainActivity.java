package com.example.preferencesroomdatabase2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.preferencesroomdatabase2.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    //current background color
    private int mBackground;
    //current text color
    private int mText;
    //show color
    private TextView mShowColor;

    public static final String BACKGROUND_KEY = "background_color";
    public static final String TEXT_KEY = "text_color";

    private SharedPreferences mPreferences;
    //file where system will save data
    private String sharedPrefFile = "com.example.android.preferencesroomdatabase2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        new PrefRoomDatabase.PopulateAsync(PrefRoomDatabase.getINSTANCE(this)).execute();

        //initialize
        mBackground = ContextCompat.getColor(this, R.color.white);
        mText = ContextCompat.getColor(this, com.google.android.material.R.color.material_on_surface_stroke);
        mShowColor = binding.mainTextview;

        //get from shared preferences
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        mBackground = mPreferences.getInt(BACKGROUND_KEY, mBackground);
        mShowColor.setBackgroundColor(mBackground);
        mText = mPreferences.getInt(TEXT_KEY, mText);
        mShowColor.setTextColor(mText);

        //restore saved instance state
        if (savedInstanceState != null) {
            mBackground = savedInstanceState.getInt(BACKGROUND_KEY);
            mShowColor.setBackgroundColor(mBackground);

            mText = savedInstanceState.getInt(TEXT_KEY);
            mShowColor.setTextColor(mText);
        }

        //enable changing colors
        onClickChange();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (R.id.rec_view == item.getItemId()) {
            //go to recycler view activity
            Intent intent = new Intent(this, RecyclerViewActivity.class);

            //put extras here
            intent.putExtra(BACKGROUND_KEY, mBackground);
            intent.putExtra(TEXT_KEY, mText);

            Log.v("AAAAAAAAAAAAAAAAAAAAAAAA",  mBackground +" : "+ ContextCompat.getColor(this,R.color.blue));
            startActivity(intent);

        } else if (R.id.reset == item.getItemId())
            reset();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(BACKGROUND_KEY, mBackground);
        editor.putInt(TEXT_KEY, mText);
        editor.apply();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

        outState.putInt(BACKGROUND_KEY, mBackground);
        outState.putInt(TEXT_KEY, mText);
    }

    private void reset() {
        mBackground = ContextCompat.getColor(this, R.color.white);
        mText = ContextCompat.getColor(this, com.google.android.material.R.color.material_on_surface_stroke);
        mShowColor.setBackgroundColor(mBackground);
        mShowColor.setTextColor(mText);

        SharedPreferences.Editor editor = mPreferences.edit();
        editor.clear();
        editor.apply();

        Toast.makeText(this, "Data was reset", Toast.LENGTH_SHORT).show();
    }

    private void onClickChange() {
        binding.blueBackground.setOnClickListener(this::changeBackgroundColor);
        binding.creamBackground.setOnClickListener(this::changeBackgroundColor);
        binding.greenBackground.setOnClickListener(this::changeBackgroundColor);
        binding.purpleBackground.setOnClickListener(this::changeBackgroundColor);

        binding.darkRedFont.setOnClickListener(this::changeTextColor);
        binding.darkBlueFont.setOnClickListener(this::changeTextColor);
        binding.blackFontButton.setOnClickListener(this::changeTextColor);
        binding.brownFontButton.setOnClickListener(this::changeTextColor);
    }

    public void changeBackgroundColor(View view) {
        int color = ((ColorDrawable) view.getBackground()).getColor();
        mShowColor.setBackgroundColor(color);
        mBackground = color;
    }

    private void changeTextColor(View view) {
        int color = ((ColorDrawable) view.getBackground()).getColor();
        mShowColor.setTextColor(color);
        mText = color;
    }
}