package com.example.lordoftheringsapp.ui.characters;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lordoftheringsapp.MainActivity;
import com.example.lordoftheringsapp.R;
import com.example.lordoftheringsapp.adapters.characterAdapters.CharacterAdapter;
import com.example.lordoftheringsapp.adapters.movieAdapters.MovieAdapter;
import com.example.lordoftheringsapp.apiCalls.ApiCall;
import com.example.lordoftheringsapp.interfaces.APIInterface;
import com.example.lordoftheringsapp.interfaces.CharactersInterface;
import com.example.lordoftheringsapp.models.characterModels.Character;
import com.example.lordoftheringsapp.models.characterModels.CharacterExample;
import com.example.lordoftheringsapp.models.movieModels.Movie;
import com.example.lordoftheringsapp.models.movieModels.MovieExample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharactersFragment extends Fragment implements CharactersInterface {

    private CharactersViewModel charactersViewModel;

    private APIInterface apiInterface;
    private RecyclerView recyclerView;
    private ArrayList<Character> mCharacters = new ArrayList<>();
    private CharacterAdapter characterAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        charactersViewModel =
                new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(CharactersViewModel.class);

        View root = inflater.inflate(R.layout.fragment_characters, container, false);
       /* final TextView textView = root.findViewById(R.id.text_slideshow);
        charactersViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        ((MainActivity) requireActivity()).hideFloatingActionButton();

        recyclerView = root.findViewById(R.id.character_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        characterAdapter = new CharacterAdapter();
        recyclerView.setAdapter(characterAdapter);




        charactersViewModel.getAllCharacters().observe(getViewLifecycleOwner(), new Observer<List<Character>>() {
            @Override
            public void onChanged(List<Character> characters) {
                characterAdapter.setCharacters(characters);
                recyclerView.scheduleLayoutAnimation();
            }
        });



        //callApi();

        return root;
    }

    private void callApi() {
        apiInterface = ApiCall.getApiCall().create(APIInterface.class);

        Call<CharacterExample> call = apiInterface.getCharacters(ApiCall.TOKEN);
        call.enqueue(new Callback<CharacterExample>() {
            @Override
            public void onResponse(Call<CharacterExample> call, Response<CharacterExample> response) {
                //Toast.makeText(getContext(), "Character call Success!!", Toast.LENGTH_SHORT).show();
                mCharacters.clear();
                mCharacters.addAll(response.body().getCharacters());
                characterAdapter.notifyDataSetChanged();
                recyclerView.scheduleLayoutAnimation();
            }

            @Override
            public void onFailure(Call<CharacterExample> call, Throwable t) {
                Toast.makeText(getContext(), "Character call FAILED!!", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });

    }

    @Override
    public void openWikiLink(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}