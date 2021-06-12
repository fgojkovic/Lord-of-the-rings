package com.example.lordoftheringsapp.adapters.bookAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lordoftheringsapp.R;
import com.example.lordoftheringsapp.interfaces.BookChaptersInterface;
import com.example.lordoftheringsapp.models.bookModels.Book;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.Collection;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BooksViewHolder>{

    ArrayList<Book> books;
    Context context;
    ArrayList<Integer> pictureList = new ArrayList<>();
    BookChaptersInterface bookChaptersInterface;

    public BookAdapter(Context context, ArrayList<Book> books, BookChaptersInterface bookChaptersInterface) {
        this.context = context;
        this.books = books;
        this.bookChaptersInterface = bookChaptersInterface;
        //pictureList.add(R.mipmap.book_one_cover_foreground);

        pictureList.add(R.drawable.book_1_cover);
        pictureList.add(R.drawable.book_2_cover);
        pictureList.add(R.drawable.book_3_cover);
    }

    @NonNull
    @Override
    public BooksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.book_row, parent, false);
        return new BooksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BooksViewHolder holder, int position) {
        holder.bookCover.setImageResource(pictureList.get(position));
        holder.bookName.setText(books.get(position).getName());

        holder.chaptersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookId = books.get(position).getId();
                bookChaptersInterface.onChaptersButtonClick(bookId);
            }
        });
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public static class BooksViewHolder extends RecyclerView.ViewHolder {

        private final ShapeableImageView bookCover;
        private final TextView bookName;
        private final MaterialButton chaptersButton;

        public BooksViewHolder(@NonNull View itemView) {
            super(itemView);

            bookCover = itemView.findViewById(R.id.book_row_image);
            bookName = itemView.findViewById(R.id.book_row_name);
            chaptersButton = itemView.findViewById(R.id.book_row_chapters_button);
        }
    }
}
