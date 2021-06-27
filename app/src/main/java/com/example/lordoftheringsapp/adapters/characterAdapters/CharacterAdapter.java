package com.example.lordoftheringsapp.adapters.characterAdapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SectionIndexer;
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
public class CharacterAdapter extends PagingDataAdapter<Character, CharacterAdapter.CharacterViewHolder> implements SectionIndexer {

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
        Character character = characters.get(position);

        holder.Bind(character, context);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isExpanded = character.isExpanded();
                character.setExpanded(!isExpanded);
                notifyItemChanged(position);
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

    @Override
    public Object[] getSections() {
        return new Object[0];
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        return 0;
    }

    @Override
    public int getSectionForPosition(int position) {
        return 0;
    }


    public static class CharacterViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView nameSubMenu;
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
        private LinearLayout subMenu;


        public CharacterViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.character_row_character_name);
            nameSubMenu = itemView.findViewById(R.id.character_row_character_name_sub_menu);
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
            subMenu = itemView.findViewById(R.id.character_row_sub_menu);
        }

        private void Bind(Character character, Context context) {
            boolean expanded = character.isExpanded();

            subMenu.setVisibility(expanded ? View.VISIBLE : View.GONE);

            String toSend = "Name: " + character.getName();
            name.setText(character.getName());
            nameSubMenu.setText(toSend);
            if(!character.getHeight().equals("")) {
                toSend = "Height: " + character.getHeight();
                height.setText(toSend);
            } else {
                height.setVisibility(View.GONE);
            }

            if(!character.getRace().equals("")) {
                toSend = "Race: " + character.getRace();
                race.setText(toSend);
            }else {
                race.setVisibility(View.GONE);
            }

            if(!character.getGender().equals("")) {
                toSend = "Gender: " + character.getGender();
                gender.setText(toSend);
            }else {
                gender.setVisibility(View.GONE);
            }

            if(!character.getBirth().equals("")) {
                toSend = "Birth: " + character.getBirth();
                birth.setText(toSend);
            }else {
                birth.setVisibility(View.GONE);
            }

            if(!character.getSpouse().equals("")) {
                toSend = "Spouse: " + character.getSpouse();
                spouse.setText(toSend);
            }else {
                spouse.setVisibility(View.GONE);
            }

            if(!character.getDeath().equals("")) {
                toSend = "Death: " + character.getDeath();
                death.setText(toSend);
            }else {
                death.setVisibility(View.GONE);
            }

            if(!character.getRealm().equals("")) {
                toSend = "Realm: " + character.getRealm();
                realm.setText(toSend);
            }else {
                realm.setVisibility(View.GONE);
            }

            if(!character.getHair().equals("")) {
                toSend = "Hair: " + character.getHair();
                hair.setText(toSend);
            } else {
                hair.setVisibility(View.GONE);
            }

            //holder.wikiUrl.setText(characters.get(position).getWikiUrl());
            //holder.wikiUrl.setMovementMethod(LinkMovementMethod.getInstance());

            wikiLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri webpage = Uri.parse(character.getWikiUrl());
                    Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                    if (intent.resolveActivity(context.getPackageManager()) != null) {
                        context.startActivity(intent);
                    }
                    //charactersInterface.openWikiLink(characters.get(position).getWikiUrl());
                    //getActivity().getPackageManager())
                }
            });
        }
    }



}
