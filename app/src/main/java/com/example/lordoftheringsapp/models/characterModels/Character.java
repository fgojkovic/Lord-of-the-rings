package com.example.lordoftheringsapp.models.characterModels;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Character {

    private boolean expanded;

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("height")
    @Expose
    private String height;
    @SerializedName("race")
    @Expose
    private String race;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("birth")
    @Expose
    private String birth;
    @SerializedName("spouse")
    @Expose
    private String spouse;
    @SerializedName("death")
    @Expose
    private String death;
    @SerializedName("realm")
    @Expose
    private String realm;
    @SerializedName("hair")
    @Expose
    private String hair;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("wikiUrl")
    @Expose
    private String wikiUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getSpouse() {
        return spouse;
    }

    public void setSpouse(String spouse) {
        this.spouse = spouse;
    }

    public String getDeath() {
        return death;
    }

    public void setDeath(String death) {
        this.death = death;
    }

    public String getRealm() {
        return realm;
    }

    public void setRealm(String realm) {
        this.realm = realm;
    }

    public String getHair() {
        return hair;
    }

    public void setHair(String hair) {
        this.hair = hair;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWikiUrl() {
        return wikiUrl;
    }

    public void setWikiUrl(String wikiUrl) {
        this.wikiUrl = wikiUrl;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if( this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(getClass() != obj.getClass()) {
            return false;
        }

        final Character other = (Character) obj;

        if(id.equals(String.valueOf(0))) {
            if(!other.id.equals(String.valueOf(0))) {
                return false;
            }
        }else if(!id.equals(other.id)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
