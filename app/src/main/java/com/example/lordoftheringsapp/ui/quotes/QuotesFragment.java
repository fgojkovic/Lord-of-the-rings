package com.example.lordoftheringsapp.ui.quotes;

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
import com.example.lordoftheringsapp.adapters.quoteAdapters.QuoteAdapter;
import com.example.lordoftheringsapp.apiCalls.ApiCall;
import com.example.lordoftheringsapp.interfaces.APIInterface;
import com.example.lordoftheringsapp.models.bookModels.BookWithChapters;
import com.example.lordoftheringsapp.models.bookModels.BookWithChaptersExample;
import com.example.lordoftheringsapp.models.characterModels.Character;
import com.example.lordoftheringsapp.models.characterModels.CharacterExample;
import com.example.lordoftheringsapp.models.movieModels.Movie;
import com.example.lordoftheringsapp.models.movieModels.MovieExample;
import com.example.lordoftheringsapp.models.quoteModels.Quote;
import com.example.lordoftheringsapp.models.quoteModels.QuoteExample;
import com.example.lordoftheringsapp.ui.characters.CharactersViewModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuotesFragment extends Fragment {

    private QuotesViewModel quotesViewModel;

    private APIInterface apiInterface;
    private RecyclerView recyclerView;
    private ArrayList<Quote> quotes = new ArrayList<>();
    private ArrayList<Movie> movies = new ArrayList<>();
    private ArrayList<Character> characters = new ArrayList<>();
    private QuoteAdapter quoteAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        quotesViewModel =
                new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(QuotesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_quotes, container, false);
        /*final TextView textView = root.findViewById(R.id.text_quotes);
        quotesViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        ((MainActivity) requireActivity()).hideFloatingActionButton();

        recyclerView = root.findViewById(R.id.quotes_recycler_view);
        quoteAdapter = new QuoteAdapter(getContext(), quotes, movies, characters);
        recyclerView.setAdapter(quoteAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        callApi();

        return root;
    }

    private void callApi() {
        apiInterface = ApiCall.getApiCall().create(APIInterface.class);

        Call<QuoteExample> call = apiInterface.getQuotes(ApiCall.TOKEN);
        call.enqueue(new Callback<QuoteExample>() {
            @Override
            public void onResponse(Call<QuoteExample> call, Response<QuoteExample> response) {
                //Toast.makeText(getContext(), "Quotes call Success!!", Toast.LENGTH_SHORT).show();
                if (response.body() != null) {
                    quotes.clear();
                }
                quotes.addAll(response.body().getQuotes());
            }

            @Override
            public void onFailure(Call<QuoteExample> call, Throwable t) {
                //Toast.makeText(getContext(), "Quotes call FAILED!!", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });

        Call<MovieExample> callMovies = apiInterface.getMovies(ApiCall.TOKEN);
        callMovies.enqueue(new Callback<MovieExample>() {
            @Override
            public void onResponse(Call<MovieExample> call, Response<MovieExample> response) {
                movies.clear();
                movies.addAll(response.body().getMovies());
            }

            @Override
            public void onFailure(Call<MovieExample> call, Throwable t) {

            }
        });

        Call<CharacterExample> callCharacter = apiInterface.getCharacters(ApiCall.TOKEN);
        callCharacter.enqueue(new Callback<CharacterExample>() {
            @Override
            public void onResponse(Call<CharacterExample> call, Response<CharacterExample> response) {
                characters.clear();
                characters.addAll(response.body().getCharacters());
                quoteAdapter.notifyDataSetChanged();
                recyclerView.scheduleLayoutAnimation();
            }

            @Override
            public void onFailure(Call<CharacterExample> call, Throwable t) {

            }
        });



    }
}
