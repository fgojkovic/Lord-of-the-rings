package com.example.lordoftheringsapp.ui.bookChapters;

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
import androidx.viewpager2.widget.ViewPager2;

import com.example.lordoftheringsapp.MainActivity;
import com.example.lordoftheringsapp.R;
import com.example.lordoftheringsapp.adapters.bookAdapters.BookWithChaptersAdapter;
import com.example.lordoftheringsapp.apiCalls.ApiCall;
import com.example.lordoftheringsapp.interfaces.APIInterface;
import com.example.lordoftheringsapp.models.bookModels.BookWithChapters;
import com.example.lordoftheringsapp.models.bookModels.BookWithChaptersExample;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookChaptersFragment extends Fragment {

    private BookChaptersViewModel bookChaptersViewModel;
    private APIInterface apiInterface;

    private String bookId;

    private RecyclerView recyclerView;
    private ArrayList<BookWithChapters> bookWithChaptersList = new ArrayList<>();
    private BookWithChaptersAdapter bookWithChaptersAdapter;

    private TextView bookTitle;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        if(bundle != null) {
            bookId = bundle.getString("Book_id");
        }

        bookChaptersViewModel =
                new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(BookChaptersViewModel.class);
        View root = inflater.inflate(R.layout.fragment_book_chapters, container, false);
        /*final TextView textView = root.findViewById(R.id.text_book_chapters);
        bookChaptersViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        /*FloatingActionButton fab = root.findViewById(R.id.fab);
        fab.setVisibility(View.GONE);*/
        ((MainActivity) requireActivity()).hideFloatingActionButton();
        bookTitle = root.findViewById(R.id.book_chapters_book_name);
        recyclerView = root.findViewById(R.id.book_chapters_recycler_view);
        bookWithChaptersAdapter = new BookWithChaptersAdapter(getContext(), bookWithChaptersList);
        recyclerView.setAdapter(bookWithChaptersAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        callApi();

        return root;
    }

    private void callApi() {
        apiInterface = ApiCall.getApiCall().create(APIInterface.class);

        Call<BookWithChaptersExample> call2 = apiInterface.getChaptersOfBook(bookId);
        call2.enqueue(new Callback<BookWithChaptersExample>() {
            @Override
            public void onResponse(Call<BookWithChaptersExample> call, Response<BookWithChaptersExample> response) {
                //Toast.makeText(getContext(), "Call 2 SUCCESFULL!!!", Toast.LENGTH_SHORT).show();
                bookTitle.setText(response.body().getDocs().get(0).getBookName());
                bookWithChaptersList.clear();
                bookWithChaptersList.addAll(response.body().getDocs());
                bookWithChaptersAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<BookWithChaptersExample> call, Throwable t) {
                Toast.makeText(getContext(), "Call 2 FAILED!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
