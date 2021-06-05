package com.example.lordoftheringsapp.adapters.chapterAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lordoftheringsapp.R;
import com.example.lordoftheringsapp.models.bookModels.Book;
import com.example.lordoftheringsapp.models.chapterModels.Chapter;

import java.util.ArrayList;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ChapterViewHolder> {

    Context context;
    ArrayList<Chapter> chapters;
    ArrayList<Book> books;

    public ChapterAdapter(Context context, ArrayList<Chapter> chapters, ArrayList<Book> books) {
        this.context = context;
        this.chapters = chapters;
        this.books = books;
    }

    @NonNull
    @Override
    public ChapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.chapter_row, parent, false);
        return new ChapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChapterViewHolder holder, int position) {
        String toSend = "Chapter: " + chapters.get(position).getChapterName();
        holder.name.setText(toSend);

        for(Book b : books) {
            if(b.getId().equals(chapters.get(position).getBook())) {
                toSend = "Book: " + b.getName();
                holder.book.setText(toSend);
                break;
            }
        }

    }

    @Override
    public int getItemCount() {
        return chapters.size();
    }

    public class ChapterViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView book;

        public ChapterViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.chapter_row_chapter_name);
            book = itemView.findViewById(R.id.chapter_row_book);
        }
    }
}
