package com.example.lordoftheringsapp.ui.chapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lordoftheringsapp.MainActivity;
import com.example.lordoftheringsapp.R;
import com.example.lordoftheringsapp.adapters.chapterAdapters.ChapterAdapter;
import com.example.lordoftheringsapp.adapters.quoteAdapters.QuoteAdapter;
import com.example.lordoftheringsapp.apiCalls.ApiCall;
import com.example.lordoftheringsapp.interfaces.APIInterface;
import com.example.lordoftheringsapp.models.bookModels.Book;
import com.example.lordoftheringsapp.models.bookModels.BookExample;
import com.example.lordoftheringsapp.models.chapterModels.Chapter;
import com.example.lordoftheringsapp.models.chapterModels.ChapterExample;
import com.example.lordoftheringsapp.models.characterModels.Character;
import com.example.lordoftheringsapp.models.movieModels.Movie;
import com.example.lordoftheringsapp.models.quoteModels.Quote;
import com.example.lordoftheringsapp.ui.quotes.QuotesViewModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChaptersFragment extends Fragment {

    private ChaptersViewModel chaptersViewModel;

    private APIInterface apiInterface;
    private RecyclerView recyclerView;
    /*private ArrayList<Quote> quotes = new ArrayList<>();
    private ArrayList<Movie> movies = new ArrayList<>();
    private ArrayList<Character> characters = new ArrayList<>();*/
    private ArrayList<Chapter> chapters = new ArrayList<>();
    private ArrayList<Book> books = new ArrayList<>();
    private ChapterAdapter chapterAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        chaptersViewModel =
                new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(ChaptersViewModel.class);
        View root = inflater.inflate(R.layout.fragment_chapters, container, false);
        /*final TextView textView = root.findViewById(R.id.text_quotes);
        quotesViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        ((MainActivity) requireActivity()).hideFloatingActionButton();

        recyclerView = root.findViewById(R.id.chapters_recycler_view);
        chapterAdapter = new ChapterAdapter(getContext(), chapters, books);
        recyclerView.setAdapter(chapterAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        callApi();

        return root;
    }

    private void callApi() {
        apiInterface = ApiCall.getApiCall().create(APIInterface.class);

        Call<ChapterExample> call = apiInterface.getChapters(ApiCall.TOKEN);
        call.enqueue(new Callback<ChapterExample>() {
            @Override
            public void onResponse(Call<ChapterExample> call, Response<ChapterExample> response) {
                //Toast.makeText(getContext(), "Chapter call Success!", Toast.LENGTH_SHORT).show();
                chapters.clear();
                chapters.addAll(response.body().getChapters());
                chapterAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ChapterExample> call, Throwable t) {
                Toast.makeText(getContext(), "Chapter call FAILED!", Toast.LENGTH_SHORT).show();
            }
        });

        Call<BookExample> bookCall = apiInterface.getBooks();
        bookCall.enqueue(new Callback<BookExample>() {
            @Override
            public void onResponse(Call<BookExample> call, Response<BookExample> response) {
                books.clear();
                books.addAll(response.body().getDocs());
                chapterAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<BookExample> call, Throwable t) {

            }
        });
    }


}
