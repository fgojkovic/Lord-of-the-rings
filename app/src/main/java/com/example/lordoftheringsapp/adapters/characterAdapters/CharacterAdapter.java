package com.example.lordoftheringsapp.adapters.characterAdapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lordoftheringsapp.R;
import com.example.lordoftheringsapp.adapters.movieAdapters.MovieAdapter;
import com.example.lordoftheringsapp.interfaces.CharactersInterface;
import com.example.lordoftheringsapp.models.characterModels.Character;
import com.google.android.material.button.MaterialButton;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

//RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>
public class CharacterAdapter extends PagingDataAdapter<Character, CharacterAdapter.CharacterViewHolder> {

    private List<Character> characters = new ArrayList<>();
    private Context context;

    public CharacterAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public CharacterAdapter.CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.character_row, parent, false);
        return new CharacterAdapter.CharacterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterAdapter.CharacterViewHolder holder, int position) {
        String toSend = "Name: " + characters.get(position).getName();
        holder.name.setText(toSend);
        if(!characters.get(position).getHeight().equals("")) {
            toSend = "Height: " + characters.get(position).getHeight();
            holder.height.setText(toSend);
        } else {
            holder.height.setVisibility(View.GONE);
        }

        if(!characters.get(position).getRace().equals("")) {
            toSend = "Race: " + characters.get(position).getRace();
            holder.race.setText(toSend);
        }else {
            holder.race.setVisibility(View.GONE);
        }

        if(!characters.get(position).getGender().equals("")) {
            toSend = "Gender: " + characters.get(position).getGender();
            holder.gender.setText(toSend);
        }else {
            holder.gender.setVisibility(View.GONE);
        }

        if(!characters.get(position).getBirth().equals("")) {
            toSend = "Birth: " + characters.get(position).getBirth();
            holder.birth.setText(toSend);
        }else {
            holder.birth.setVisibility(View.GONE);
        }

        if(!characters.get(position).getSpouse().equals("")) {
            toSend = "Spouse: " + characters.get(position).getSpouse();
            holder.spouse.setText(toSend);
        }else {
            holder.spouse.setVisibility(View.GONE);
        }

        if(!characters.get(position).getDeath().equals("")) {
            toSend = "Death: " + characters.get(position).getDeath();
            holder.death.setText(toSend);
        }else {
            holder.death.setVisibility(View.GONE);
        }

        if(!characters.get(position).getRealm().equals("")) {
            toSend = "Realm: " + characters.get(position).getRealm();
            holder.realm.setText(toSend);
        }else {
            holder.realm.setVisibility(View.GONE);
        }

        if(!characters.get(position).getHair().equals("")) {
            toSend = "Hair: " + characters.get(position).getHair();
            holder.hair.setText(toSend);
        } else {
            holder.hair.setVisibility(View.GONE);
        }

        //holder.wikiUrl.setText(characters.get(position).getWikiUrl());
        //holder.wikiUrl.setMovementMethod(LinkMovementMethod.getInstance());

        holder.wikiLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri webpage = Uri.parse(characters.get(position).getWikiUrl());
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                if (intent.resolveActivity(context.getPackageManager()) != null) {
                    context.startActivity(intent);
                }
                //charactersInterface.openWikiLink(characters.get(position).getWikiUrl());
                //getActivity().getPackageManager())
            }
        });
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    public void setCharacters(List<Character> characters){
        this.characters = characters;
        notifyDataSetChanged();
    }

    private static DiffUtil.ItemCallback<Character> DIFF_CALLBACK = new DiffUtil.ItemCallback<Character>() {
        @Override
        public boolean areItemsTheSame(@NonNull Character oldItem, @NonNull Character newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Character oldItem, @NonNull Character newItem) {
            return oldItem.equals(newItem);
        }
    };



    public static class CharacterViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView height;
        private TextView race;
        private TextView gender;
        private TextView birth;
        private TextView spouse;
        private TextView death;
        private TextView realm;
        private TextView hair;
        //private TextView wikiUrl;
        private MaterialButton wikiLink;


        public CharacterViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.character_row_character_name);
            height = itemView.findViewById(R.id.character_row_character_height);
            race = itemView.findViewById(R.id.character_row_character_race);
            gender = itemView.findViewById(R.id.character_row_character_gender);
            birth = itemView.findViewById(R.id.character_row_character_birth);
            spouse = itemView.findViewById(R.id.character_row_character_spouse);
            death = itemView.findViewById(R.id.character_row_character_death);
            realm = itemView.findViewById(R.id.character_row_character_realm);
            hair = itemView.findViewById(R.id.character_row_character_hair);
            //wikiUrl = itemView.findViewById(R.id.character_row_character_wiki_url);
            wikiLink = itemView.findViewById(R.id.character_row_character_wiki_url_button);
        }
    }



}
