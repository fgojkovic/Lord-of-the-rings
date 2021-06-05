package com.example.lordoftheringsapp.models.characterModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CharacterExample {
    @SerializedName("docs")
    @Expose
    private List<Character> characters = null;

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }
}
