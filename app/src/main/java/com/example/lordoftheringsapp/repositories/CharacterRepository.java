package com.example.lordoftheringsapp.repositories;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;

import com.example.lordoftheringsapp.apiCalls.ApiCall;
import com.example.lordoftheringsapp.interfaces.APIInterface;
import com.example.lordoftheringsapp.models.characterModels.Character;
import com.example.lordoftheringsapp.models.characterModels.CharacterExample;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharacterRepository {
    private ArrayList<Character> characters = new ArrayList<>();
    private MutableLiveData<List<Character>> mutableLiveData = new MutableLiveData<>();
    //private Application application;
    private APIInterface apiInterface;
    private Context context;

    @Inject
    public CharacterRepository() {
        /*Application application
        this.application = application;
        this.context = application.getApplicationContext();*/
        // Creates a PagedList object with 50 items per page.
    /*public ConcertViewModel(ConcertDao concertDao) {
        this.concertDao = concertDao;
        concertList = new LivePagedListBuilder<>(
                concertDao.concertsByDate(), 50).build();
    }*/

    }

    public MutableLiveData<List<Character>> getMutableLiveData() {
        apiInterface = ApiCall.getApiCall().create(APIInterface.class);

        Call<CharacterExample> call = apiInterface.getCharacters(ApiCall.TOKEN);
        call.enqueue(new Callback<CharacterExample>() {
            @Override
            public void onResponse(Call<CharacterExample> call, Response<CharacterExample> response) {
                //Toast.makeText(getContext(), "Character call Success!!", Toast.LENGTH_SHORT).show();
                characters.clear();
                characters.addAll(response.body().getCharacters());
                mutableLiveData.setValue(characters);
            }

            @Override
            public void onFailure(Call<CharacterExample> call, Throwable t) {
                //Toast.makeText(context, "Character call FAILED!!", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });

        return mutableLiveData;
    }
}
