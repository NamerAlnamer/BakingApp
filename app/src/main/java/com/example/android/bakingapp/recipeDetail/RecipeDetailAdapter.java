package com.example.android.bakingapp.recipeDetail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.model.RecipeModel;

public class RecipeDetailAdapter extends RecyclerView.Adapter<RecipeDetailAdapter.DataViewHolder>{

    private RecipeModel recipeModel;
    private RecipeDetailFragment.OnFragmentInteractionListener mCallBack;

    public RecipeDetailAdapter(RecipeModel model, RecipeDetailFragment.OnFragmentInteractionListener listener) {
        recipeModel = model;
        mCallBack = listener;
    }


    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_fragment_recipe_detail, parent, false);

        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {

        holder.recipeDetailStepLabel.setText(recipeModel.getSteps().get(position).getShortDescription());


    }

    @Override
    public int getItemCount() {
        if(recipeModel != null)
        return recipeModel.getSteps().size();
        else {
            return  0;
        }
    }

    public class DataViewHolder extends RecyclerView.ViewHolder{

        TextView recipeDetailStepLabel;

        public DataViewHolder(View itemView) {
            super(itemView);

            recipeDetailStepLabel = itemView.findViewById(R.id.recipe_detail_step_label);

            itemView.setOnClickListener(view -> mCallBack.onFragmentInteraction( getAdapterPosition()));
        }
    }
}
