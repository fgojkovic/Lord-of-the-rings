package com.example.lordoftheringsapp.ui.characters;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;
import androidx.paging.PagingDataAdapter;

import com.example.lordoftheringsapp.adapters.characterAdapters.CharacterAdapter;
import com.example.lordoftheringsapp.apiCalls.ApiCall;
import com.example.lordoftheringsapp.interfaces.APIInterface;
import com.example.lordoftheringsapp.models.characterModels.Character;
import com.example.lordoftheringsapp.models.characterModels.CharacterExample;
import com.example.lordoftheringsapp.repositories.CharacterRepository;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
                                        //AndroidViewModel
public class CharactersViewModel extends ViewModel {

    private CharacterRepository characterRepository;

    public CharactersViewModel() {
        characterRepository = new CharacterRepository();

    }

    public LiveData<List<Character>> getAllCharacters() {
        return characterRepository.getMutableLiveData();
    }


    // Creates a PagedList object with 50 items per page.
    /*public ConcertViewModel(ConcertDao concertDao) {
        this.concertDao = concertDao;
        concertList = new LivePagedListBuilder<>(
                concertDao.concertsByDate(), 50).build();
    }*/


}