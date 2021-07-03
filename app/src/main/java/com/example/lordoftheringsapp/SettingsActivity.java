package com.example.lordoftheringsapp;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.provider.FontRequest;
import android.provider.FontsContract;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.ListPreference;
import androidx.preference.PreferenceFragmentCompat;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {

    private CharSequence[] fonts;
    private Context context;

    private ListPreference fontPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        context = getBaseContext();

        Handler handler = new Handler();


        fontPreference = new ListPreference(getApplicationContext());
        FontRequest request = new FontRequest("com.example.fontprovider",
                "com.example.fontprovider", "my font");

        FontsContract.FontRequestCallback callback =
                new FontsContract.FontRequestCallback() {
                    @Override
                    public void onTypefaceRetrieved(Typeface typeface) {
                        // Your code to use the font goes here
                        String typefaceString = typeface.toString();
                    }

                    @Override
                    public void onTypefaceRequestFailed(int reason) {
                        // Your code to deal with the failure goes here
                        Toast.makeText(getApplicationContext(), "Typeface failed", Toast.LENGTH_SHORT).show();
                    }
                };
        FontsContract.requestFonts(context, request, handler, null, callback);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            Toast.makeText(getApplicationContext(), "Backarrow pressed!", Toast.LENGTH_SHORT).show();
            getSupportFragmentManager().popBackStack();
            this.onBackPressed();
            return true;
        }

        return false;
    }



    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }

    }
}