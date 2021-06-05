package com.example.lordoftheringsapp.models.bookModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookWithChapters {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("bookName")
    @Expose
    private String bookName;
    @SerializedName("chapterName")
    @Expose
    private String chapterName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

}
