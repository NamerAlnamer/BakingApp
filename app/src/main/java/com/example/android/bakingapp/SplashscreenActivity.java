package com.example.android.bakingapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android.bakingapp.data.RecipeRepository;
import com.example.android.bakingapp.main.MainActivity;

import java.io.IOException;

public class SplashscreenActivity extends AppCompatActivity {


    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        //Getting shared preference
        SharedPreferences sharedPreferences = this.getSharedPreferences(getString(R.string.RECIPE_DATA_PREFERENCE_FILE), MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //if the shared preferences file is null, I need to first get the data and meanwhile
        //show the splash screen, but if there is data, we just move to MainActivity

        if(sharedPreferences.getBoolean("initialStart", true)) {
            try {
                editor.putBoolean("initialStart", false);
                editor.apply();
                RecipeRepository.getInstance().getRecipeData(getApplication().getApplicationContext());
                /*
                 * Showing splash screen with a timer. This will be useful when you
                 * want to show case your app logo / company
                 */
                new Handler().postDelayed(() -> {
                    // This method will be executed once the timer is over
                    // Start your app main activity
                    Intent i = new Intent(SplashscreenActivity.this, MainActivity.class);
                    startActivity(i);

                    // close this activity
                    finish();
                }, SPLASH_TIME_OUT);
            } catch (IOException e) {
                e.printStackTrace();
                finish();
            }

        } else {
            Intent i = new Intent(SplashscreenActivity.this, MainActivity.class);
            startActivity(i);
        }




    }
}
