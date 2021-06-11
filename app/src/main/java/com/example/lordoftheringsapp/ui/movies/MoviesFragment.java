package com.example.lordoftheringsapp.ui.movies;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.example.lordoftheringsapp.MainActivity;
import com.example.lordoftheringsapp.R;
import com.example.lordoftheringsapp.adapters.movieAdapters.MovieAdapter;
import com.example.lordoftheringsapp.apiCalls.ApiCall;
import com.example.lordoftheringsapp.interfaces.APIInterface;
import com.example.lordoftheringsapp.interfaces.AdInterface;
import com.example.lordoftheringsapp.models.movieModels.Movie;
import com.example.lordoftheringsapp.models.movieModels.MovieExample;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.StringTokenizer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MoviesFragment extends Fragment {

    private MoviesViewModel moviesViewModel;

    private APIInterface apiInterface;
    private RecyclerView recyclerView;
    private ArrayList<Movie> movies = new ArrayList<>();
    private MovieAdapter movieAdapter;

    private AdInterface adInterface;

    //private MovieAdapter movieAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        moviesViewModel =
                new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MoviesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_movies, container, false);
        /*final TextView textView = root.findViewById(R.id.text_gallery);
        moviesViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        recyclerView = root.findViewById(R.id.movies_recycler_view);

        //removing blinks
        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);

        movieAdapter = new MovieAdapter(getContext(), movies);
        recyclerView.setAdapter(movieAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //recyclerView.setHasFixedSize(true);

        adInterface = (AdInterface) getActivity();

        callApi();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adInterface.TriggerInterstitialAd();
    }

    private void callApi() {
        apiInterface = ApiCall.getApiCall().create(APIInterface.class);

        Call<MovieExample> call = apiInterface.getMovies(ApiCall.TOKEN);
        call.enqueue(new Callback<MovieExample>() {
            @Override
            public void onResponse(@NotNull Call<MovieExample> call, @NotNull Response<MovieExample> response) {
                if (movies != null) {
                    movies.addAll(response.body().getMovies());
                }
                movieAdapter.notifyDataSetChanged();
                recyclerView.scheduleLayoutAnimation();
            }

            @Override
            public void onFailure(@NotNull Call<MovieExample> call, @NotNull Throwable t) {
                Toast.makeText(getContext(), "Movie call FAILED!!", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });

    }
}