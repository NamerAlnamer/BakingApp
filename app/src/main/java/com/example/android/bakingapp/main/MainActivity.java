package com.example.android.bakingapp.main;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.IdlingResource;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.model.RecipeModel;
import com.example.android.bakingapp.main.IdlingResource.RecipeIdlingResource;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MessageDelayer.DelayerCallback{

    private MainActivityViewModel viewModel;
    private RecipeListAdapter mAdapter;
    private RecyclerView mainRecipeListRv;

    // The Idling Resource which will be null in production.
    @Nullable
    private RecipeIdlingResource mIdlingResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        //LeakCanary setup to see the leaks in the application
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(getApplication());*/


        //Viewmodel setup for the MainActivity
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        viewModel.init();

        //setting up the views in the activity
        setupActivity();


    }



    /**
     * MainActivity setup method that displays data on the views
     */
    private void setupActivity() {
        //testing purpose only
        MessageDelayer.processMessage("hi", this, mIdlingResource, this);
        mainRecipeListRv = findViewById(R.id.main_recipe_list_rv);
        //Getting data from the view model
        List<RecipeModel> recipeData = viewModel.getRecipeData();
        //Setting up the recycler view
        mAdapter = new RecipeListAdapter(recipeData, this);
        mainRecipeListRv.setAdapter(mAdapter);
        if(getResources().getBoolean(R.bool.isTablet)) {
            GridLayoutManager manager = new GridLayoutManager(this, 2);
            manager.setOrientation(LinearLayoutManager.VERTICAL);
            mainRecipeListRv.setLayoutManager(manager);
        } else {
            LinearLayoutManager manager = new LinearLayoutManager(this);
            manager.setOrientation(LinearLayoutManager.HORIZONTAL);
           mainRecipeListRv.setLayoutManager(manager);
        }
    }
    public void onDone(List<RecipeModel> recipeModel) {
        Toast.makeText(this, "done", Toast.LENGTH_SHORT).show();
    }

    /**
     * Only called from test, creates and returns a new {@link RecipeIdlingResource}.
     */
    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new RecipeIdlingResource();
        }
        return mIdlingResource;
    }
}
