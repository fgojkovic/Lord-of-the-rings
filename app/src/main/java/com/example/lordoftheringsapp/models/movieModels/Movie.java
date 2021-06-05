package com.example.lordoftheringsapp.models.movieModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Movie {

    private boolean expanded;

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("runtimeInMinutes")
    @Expose
    private Double runtimeInMinutes;
    @SerializedName("budgetInMillions")
    @Expose
    private Double budgetInMillions;
    @SerializedName("boxOfficeRevenueInMillions")
    @Expose
    private Double boxOfficeRevenueInMillions;
    @SerializedName("academyAwardNominations")
    @Expose
    private Double academyAwardNominations;
    @SerializedName("academyAwardWins")
    @Expose
    private Double academyAwardWins;
    @SerializedName("rottenTomatesScore")
    @Expose
    private Double rottenTomatesScore;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRuntimeInMinutes() {
        return runtimeInMinutes;
    }

    public void setRuntimeInMinutes(Double runtimeInMinutes) {
        this.runtimeInMinutes = runtimeInMinutes;
    }

    public Double getBudgetInMillions() {
        return budgetInMillions;
    }

    public void setBudgetInMillions(Double budgetInMillions) {
        this.budgetInMillions = budgetInMillions;
    }

    public Double getBoxOfficeRevenueInMillions() {
        return boxOfficeRevenueInMillions;
    }

    public void setBoxOfficeRevenueInMillions(Double boxOfficeRevenueInMillions) {
        this.boxOfficeRevenueInMillions = boxOfficeRevenueInMillions;
    }

    public Double getAcademyAwardNominations() {
        return academyAwardNominations;
    }

    public void setAcademyAwardNominations(Double academyAwardNominations) {
        this.academyAwardNominations = academyAwardNominations;
    }

    public Double getAcademyAwardWins() {
        return academyAwardWins;
    }

    public void setAcademyAwardWins(Double academyAwardWins) {
        this.academyAwardWins = academyAwardWins;
    }

    public Double getRottenTomatesScore() {
        return rottenTomatesScore;
    }

    public void setRottenTomatesScore(Double rottenTomatesScore) {
        this.rottenTomatesScore = rottenTomatesScore;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
}
