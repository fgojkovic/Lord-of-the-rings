package com.example.lordoftheringsapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceFragmentCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lordoftheringsapp.adapters.supportAdapters.SupportAdapter;
import com.example.lordoftheringsapp.interfaces.SupportInterface;
import com.example.lordoftheringsapp.ui.books.BooksViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.zip.Inflater;

public class SupportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.support_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.support, new SupportFragment())
                    .commit();
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

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

    public static class SupportFragment extends Fragment implements SupportInterface {

        RecyclerView recyclerView;
        ArrayList<String> textList = new ArrayList<>();

        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.fragment_support, container, false);

            recyclerView = root.findViewById(R.id.support_recycler_view);

            ArrayList<Integer> picList = new ArrayList<>();
            picList.add(R.drawable.ic_menu_cart);
            picList.add(R.drawable.ic_menu_changelog);
            picList.add(R.drawable.ic_menu_share);
            picList.add(R.drawable.ic_menu_laptop_search);
            picList.add(R.drawable.ic_menu_feedback);

            String[] textString = getResources().getStringArray(R.array.support_string_array);
            textList.addAll(Arrays.asList(textString));

            SupportAdapter supportAdapter = new SupportAdapter(getContext(), textList, picList, this);
            recyclerView.setAdapter(supportAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


            return root;
        }

        @Override
        public void onRowClick(int position) {
            Toast.makeText(getContext(), "You clicked on: " + textList.get(position).toUpperCase() , Toast.LENGTH_SHORT).show();
        }
    }


}