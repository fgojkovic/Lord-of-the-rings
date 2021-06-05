package com.example.lordoftheringsapp.adapters.supportAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lordoftheringsapp.R;
import com.example.lordoftheringsapp.interfaces.SupportInterface;

import java.util.ArrayList;

public class SupportAdapter extends RecyclerView.Adapter<SupportAdapter.SupportViewHolder> {

    Context context;
    ArrayList<String> textList = new ArrayList<>();
    ArrayList<Integer> picList = new ArrayList<>();
    SupportInterface supportInterface;

    public SupportAdapter (Context context, ArrayList<String> textList, ArrayList<Integer> picList, SupportInterface supportInterface) {
        this.context = context;
        this.textList = textList;
        this.picList = picList;
        this.supportInterface = supportInterface;
    }

    @NonNull
    @Override
    public SupportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.support_row, parent, false);
        return new SupportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SupportViewHolder holder, int position) {
        holder.imageView.setImageResource(picList.get(position));
        holder.textView.setText(textList.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                supportInterface.onRowClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return textList.size();
    }

    public class SupportViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView textView;

        public SupportViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.support_row_image);
            textView = itemView.findViewById(R.id.support_row_text);
        }
    }
}
