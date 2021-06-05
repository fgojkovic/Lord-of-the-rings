package com.example.lordoftheringsapp.adapters.bookAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lordoftheringsapp.R;
import com.example.lordoftheringsapp.models.bookModels.BookWithChapters;

import java.util.ArrayList;

public class BookWithChaptersAdapter extends RecyclerView.Adapter<BookWithChaptersAdapter.BookWithChaptersViewHolder> {

    private Context context;
    private ArrayList<BookWithChapters> bookWithChapters = new ArrayList<>();

    public BookWithChaptersAdapter(Context context, ArrayList<BookWithChapters> bookWithChapters) {
        this.context = context;
        this.bookWithChapters = bookWithChapters;
    }

    @NonNull
    @Override
    public BookWithChaptersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.book_with_chapter_row, parent, false);
        return new BookWithChaptersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookWithChaptersViewHolder holder, int position) {
        String number = String.valueOf(position + 1) + ".";
        holder.ordinalNumber.setText(number);
        holder.chapterName.setText(bookWithChapters.get(position).getChapterName());
    }

    @Override
    public int getItemCount() {
        return bookWithChapters.size();
    }

    public static class BookWithChaptersViewHolder extends RecyclerView.ViewHolder {
        TextView ordinalNumber;
        TextView chapterName;

        public BookWithChaptersViewHolder(@NonNull View itemView) {
            super(itemView);
            ordinalNumber = itemView.findViewById(R.id.book_with_chapter_ordinal_number);
            chapterName = itemView.findViewById(R.id.book_with_chapter_row_chapter_name);

        }
    }
}
