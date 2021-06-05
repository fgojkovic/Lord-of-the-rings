package com.example.lordoftheringsapp.interfaces;

import com.example.lordoftheringsapp.models.Example;
import com.example.lordoftheringsapp.models.bookModels.Book;
import com.example.lordoftheringsapp.models.bookModels.BookExample;
import com.example.lordoftheringsapp.models.bookModels.BookWithChapters;
import com.example.lordoftheringsapp.models.bookModels.BookWithChaptersExample;
import com.example.lordoftheringsapp.models.chapterModels.ChapterExample;
import com.example.lordoftheringsapp.models.characterModels.CharacterExample;
import com.example.lordoftheringsapp.models.movieModels.MovieExample;
import com.example.lordoftheringsapp.models.quoteModels.QuoteExample;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {

    /* BOOKS */
    @GET("book")
    Call<BookExample> getBooks();

    @GET("book/{id}")
    Call<BookExample> getBookById(@Path("id") String id);

    @GET("book/{id}/chapter")
    Call<BookWithChaptersExample> getChaptersOfBook(@Path("id") String id);


    /* MOVIES */
    @GET("movie")
    Call<MovieExample> getMovies(@Header("Authorization") String token);

    @GET("movie/{id}")
    Call<MovieExample> getMovieById(@Header("Authorization") String token, @Path("id") String id);

    @GET("movie/{id}/quote")//working only for LotR Trilogy
    Call<MovieExample> getMovieQuotes(@Header("Authorization") String token, @Path("id") String id);


    /* CHARACTERS */
    @GET("character")
    Call<CharacterExample> getCharacters(@Header("Authorization") String token);

    @GET("character/{id}")
    Call<CharacterExample> getCharacterById(@Header("Authorization") String token, @Path("id") String id);

    @GET("character/{id}/quote")
    Call<CharacterExample> getCharacterQuotes(@Header("Authorization") String token, @Path("id") String id);


    /* QUOTES */
    @GET("quote")
    Call<QuoteExample> getQuotes(@Header("Authorization") String token);

    @GET("quote/{id}")
    Call<QuoteExample> getQuoteById(@Header("Authorization") String token, @Path("id") String id);


    /* CHAPTERS */
    @GET("chapter")
    Call<ChapterExample> getChapters(@Header("Authorization") String token);

    @GET("chapter/{id}")
    Call<ChapterExample> getChapterById(@Header("Authorization") String token, @Path("id") String id);
}
