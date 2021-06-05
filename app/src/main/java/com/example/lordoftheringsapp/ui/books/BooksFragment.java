package com.example.lordoftheringsapp.ui.books;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.lordoftheringsapp.MainActivity;
import com.example.lordoftheringsapp.R;
import com.example.lordoftheringsapp.adapters.bookAdapters.BookAdapter;
import com.example.lordoftheringsapp.adapters.bookAdapters.BookWithChaptersAdapter;
import com.example.lordoftheringsapp.apiCalls.ApiCall;
import com.example.lordoftheringsapp.interfaces.APIInterface;
import com.example.lordoftheringsapp.interfaces.BookChaptersInterface;
import com.example.lordoftheringsapp.models.bookModels.Book;
import com.example.lordoftheringsapp.models.bookModels.BookExample;
import com.example.lordoftheringsapp.models.bookModels.BookWithChapters;
import com.example.lordoftheringsapp.models.bookModels.BookWithChaptersExample;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BooksFragment extends Fragment implements BookChaptersInterface {

    private BooksViewModel booksViewModel;
    private TextView textView;
    private TextView textViewTwo;

    private APIInterface apiInterface;

    private ViewPager2 bookViewPager;
    private ArrayList<Book> bookList = new ArrayList<>();
    private BookAdapter bookAdapter;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        booksViewModel =
                new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(BooksViewModel.class);
        View root = inflater.inflate(R.layout.fragment_books, container, false);
        //final TextView
        /*textView = root.findViewById(R.id.text_home);
        booksViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        ((MainActivity) requireActivity()).hideFloatingActionButton();

        bookViewPager = root.findViewById(R.id.books_view_pager);
        bookAdapter = new BookAdapter(getContext(), bookList, this);

        bookViewPager.setClipToPadding(false);
        bookViewPager.setClipChildren(false);
        bookViewPager.setOffscreenPageLimit(3);
        bookViewPager.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);

        bookViewPager.setAdapter(bookAdapter);

        CompositePageTransformer transformer = new CompositePageTransformer();
        transformer.addTransformer(new MarginPageTransformer(8));
        transformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float v = 1 - Math.abs(position);
                page.setScaleY(0.8f + v * 0.2f);
            }
        });

        bookViewPager.setPageTransformer(transformer);



        //for now this is gone
        recyclerView = root.findViewById(R.id.books_recycler_view);
        recyclerView.setAdapter(bookAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        callApi();


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) requireActivity()).showFloatingActionButton();
    }

    private void callApi() {
        apiInterface = ApiCall.getApiCall().create(APIInterface.class);


        Call<BookExample> call = apiInterface.getBooks();
        call.enqueue(new Callback<BookExample>() {
            @Override
            public void onResponse(Call<BookExample> call, Response<BookExample> response) {
                //Toast.makeText(getContext(), "Book call Successful!!!", Toast.LENGTH_SHORT).show();
                bookList.clear();
                if(response.body() != null) {
                    bookList.addAll(response.body().getDocs());
                }
                bookAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<BookExample> call, Throwable t) {
                Toast.makeText(getContext(), "Book call FAILED!!!", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onChaptersButtonClick(String book_id) {
        Bundle bundle = new Bundle();
        bundle.putString("Book_id", book_id);

        NavHostFragment.findNavController(BooksFragment.this).navigate(R.id.action_nav_books_to_bookChaptersFragment, bundle);
    }
}