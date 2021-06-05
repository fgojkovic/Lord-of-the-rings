package com.example.lordoftheringsapp.adapters.movieAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lordoftheringsapp.R;
import com.example.lordoftheringsapp.models.movieModels.Movie;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MoviesViewHolder> {

    ArrayList<Movie> movies;
    Context context;

    public MovieAdapter(Context context, ArrayList<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.movie_row, parent, false);
        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder holder, int position) {
        Movie movie = movies.get(position);

        holder.Bind(movie);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isExpanded = movie.isExpanded();
                movie.setExpanded(!isExpanded);
                notifyItemChanged(position);
            }
        });


        /*holder.name.setText(movie.getName());
        holder.runtime.setText(String.valueOf(movie.getRuntimeInMinutes()));
        holder.budget.setText(String.valueOf(movie.getBudgetInMillions()));
        holder.boxOfficeRevenue.setText(String.valueOf(movie.getBoxOfficeRevenueInMillions()));
        holder.awardNominations.setText(String.valueOf(movie.getAcademyAwardNominations()));
        holder.awardWins.setText(String.valueOf(movie.getAcademyAwardWins()));
        holder.score.setText(String.valueOf(movie.getRottenTomatesScore()));*/
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MoviesViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView runtime;
        private TextView budget;
        private TextView boxOfficeRevenue;
        private TextView awardNominations;
        private TextView awardWins;
        private TextView score;
        private LinearLayout subItemsMenu;


        public MoviesViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.movie_row_name);
            runtime = itemView.findViewById(R.id.movie_row_runtimeInMinutes);
            budget = itemView.findViewById(R.id.movie_row_budgetInMillions);
            boxOfficeRevenue = itemView.findViewById(R.id.movie_row_boxOfficeRevenueInMillions);
            awardNominations = itemView.findViewById(R.id.movie_row_academyAwardNominations);
            awardWins = itemView.findViewById(R.id.movie_row_academyAwardWins);
            score = itemView.findViewById(R.id.movie_row_rottenTomatesScore);
            subItemsMenu = itemView.findViewById(R.id.movie_row_sub_menu);
        }

        private void Bind(Movie movie) {
            boolean expanded = movie.isExpanded();
            // Set the visibility based on state
            subItemsMenu.setVisibility(expanded ? View.VISIBLE : View.GONE);

            //String toSend = "Name: " + movie.getName();
            name.setText(movie.getName());

            String toSend = "Runtime(minutes): " + movie.getRuntimeInMinutes();
            runtime.setText(toSend);

            toSend = "Budget(milions): " + movie.getBudgetInMillions();
            budget.setText(toSend);

            toSend = "Box office revenue(milions): " + movie.getBoxOfficeRevenueInMillions();
            boxOfficeRevenue.setText(toSend);

            toSend = "Academy award nominations: " + movie.getAcademyAwardNominations();
            awardNominations.setText(toSend);

            toSend = "Academy award wins: " + movie.getAcademyAwardWins();
            awardWins.setText(toSend);

            toSend = "Rotten Tomates score: " + movie.getRottenTomatesScore();
            score.setText(toSend);
        }
    }
}
