package com.example.lordoftheringsapp.adapters.quoteAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lordoftheringsapp.R;
import com.example.lordoftheringsapp.models.characterModels.Character;
import com.example.lordoftheringsapp.models.movieModels.Movie;
import com.example.lordoftheringsapp.models.quoteModels.Quote;

import java.util.ArrayList;

public class QuoteAdapter extends RecyclerView.Adapter<QuoteAdapter.QuoteViewHolder> {

    private Context context;
    private ArrayList<Quote> quotes;
    private ArrayList<Movie> movies;
    private ArrayList<Character> characters;

    public QuoteAdapter(Context context, ArrayList<Quote> quotes, ArrayList<Movie> movies, ArrayList<Character> characters) {
        this.context = context;
        this.quotes = quotes;
        this.movies = movies;
        this.characters = characters;
    }

    @NonNull
    @Override
    public QuoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.quote_row, parent, false);
        return new QuoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuoteViewHolder holder, int position) {
        String toSend = "Quote: \"" +quotes.get(position).getDialog() + "\"";
        holder.dialog.setText(toSend);
        for(Movie m : movies) {
            if(m.getId().equals(quotes.get(position).getMovie())) {
                toSend = "Movie: " + m.getName();
                holder.movie.setText(toSend);
                break;
            }
        }

        for(Character c : characters) {
            if(c.getId().equals(quotes.get(position).getCharacter())) {
                toSend = "Charachter: " + c.getName();
                holder.character.setText(toSend);
                break;
            }
        }

    }

    @Override
    public int getItemCount() {
        return quotes.size();
    }

    public class QuoteViewHolder extends RecyclerView.ViewHolder{

        private TextView dialog;
        private TextView movie;
        private TextView character;

        public QuoteViewHolder(@NonNull View itemView) {
            super(itemView);

            dialog = itemView.findViewById(R.id.quote_row_quote_dialog);
            movie = itemView.findViewById(R.id.quote_row_quote_movie);
            character = itemView.findViewById(R.id.quote_row_quote_character);
        }
    }
}
