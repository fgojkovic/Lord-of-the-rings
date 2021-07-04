package com.example.lordoftheringsapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.example.lordoftheringsapp.apiCalls.ApiCall;
import com.example.lordoftheringsapp.interfaces.APIInterface;
import com.example.lordoftheringsapp.interfaces.AdInterface;
import com.example.lordoftheringsapp.models.Example;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements AdInterface {

    private AppBarConfiguration mAppBarConfiguration;
    private AppBarConfiguration mAppBarConfigurationForBottomNavigation;

    private APIInterface apiInterface;

    private FloatingActionButton fab;

    private BottomNavigationView bottomNavigationView;

    private AdView adView;

    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ADS part
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                //Toast.makeText(getApplicationContext(), "Ad Initialization completed!", Toast.LENGTH_SHORT).show();
            }
        });

        List<String> testDevices = new ArrayList<>();
        //06C2B9D75D7F35801B3B791920CE66ED
        testDevices.add(AdRequest.DEVICE_ID_EMULATOR);

        RequestConfiguration requestConfiguration = new RequestConfiguration.Builder()
                .setTestDeviceIds(testDevices)
                .build();

        MobileAds.setRequestConfiguration(requestConfiguration);


        adView = findViewById(R.id.content_main_ad_view);


        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        setAdListener();

        //my interstitialAd ca-app-pub-6997427691996385/7560452676
        //my app open ad ca-app-pub-6997427691996385/3238064289
        //default test ca-app-pub-3940256099942544/1033173712
        InterstitialAd.load(this, "ca-app-pub-6997427691996385/7560452676", adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                //super.onAdLoaded(interstitialAd);
                mInterstitialAd = interstitialAd;
                setFullScreenContentCallback();

            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                //super.onAdFailedToLoad(loadAdError);
                mInterstitialAd = null;
            }
        });






        /*Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
        /*fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Set action of your own choosing", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //NavController navController1 = Navigation.findNavController(this, R.id.content_main_ads_fragment);

        //DrawerLayout drawer = findViewById(R.id.drawer_layout);
        //NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_books, R.id.nav_movies, R.id.nav_characters, R.id.nav_quotes, R.id.nav_chapters)
                .build();

        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        //NavigationUI.setupWithNavController(navigationView, navController);

        /*mAppBarConfigurationForBottomNavigation = new AppBarConfiguration.Builder(
                R.id.nav_books, R.id.nav_movies, R.id.nav_characters, R.id.nav_quotes, R.id.nav_chapters)
                .setOpenableLayout(drawer)
                .build();*/
        //NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfigurationForBottomNavigation);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);


    }

    private void setFullScreenContentCallback() {
        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
            @Override
            public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                super.onAdFailedToShowFullScreenContent(adError);
                // Called when fullscreen content failed to show.
                Log.d("TAG", "The ad failed to show.");
            }

            @Override
            public void onAdShowedFullScreenContent() {
                super.onAdShowedFullScreenContent();
                // Called when fullscreen content is shown.
                // Make sure to set your reference to null so you don't
                // show it a second time.
                mInterstitialAd = null;
                Log.d("TAG", "The ad was shown.");
            }

            @Override
            public void onAdDismissedFullScreenContent() {
                super.onAdDismissedFullScreenContent();
                // Called when fullscreen content is dismissed.
                Log.d("TAG", "The ad was dismissed.");
            }

            @Override
            public void onAdImpression() {
                super.onAdImpression();
            }
        });
    }

    private void setAdListener() {
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdClicked() {
                super.onAdClicked();
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdImpression() {
                super.onAdImpression();
                //Toast.makeText(getBaseContext(), "Ad impression!", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        } else if ( item.getItemId() == R.id.action_support) {
            Intent intent = new Intent(this, SupportActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void showFloatingActionButton() {
        if(fab != null)
        fab.show();
    };

    public void hideFloatingActionButton() {
        if(fab != null)
        fab.hide();
    };

    @Override
    public void TriggerInterstitialAd() {
        if (mInterstitialAd != null) {
            mInterstitialAd.show(MainActivity.this);
        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.");
        }
    }
}