package com.example.recipeasy.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeasy.Models.Recipe;
import com.example.recipeasy.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RandomRecipeAdapter extends RecyclerView.Adapter<RandomRecipeViewHolder>{

    Context context;
    List<Recipe> list;

    public RandomRecipeAdapter(Context context, List<Recipe> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RandomRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RandomRecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.list_random_recipe, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RandomRecipeViewHolder holder, int position) {
        holder.title.setText(list.get(position).title);
        holder.title.setSelected(true);
        holder.fav.setText(list.get(position).aggregateLikes + "Favorite");
        holder.servings.setText(list.get(position).servings + "Servings");
        holder.time.setText(list.get(position).readyInMinutes + "Minutes");
        Picasso.get().load(list.get(position).image).into(holder.food);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class RandomRecipeViewHolder extends RecyclerView.ViewHolder {

    CardView randomListContainer;
    TextView title, servings, fav, time;
    ImageView food;

    public RandomRecipeViewHolder(@NonNull View itemView){
        super(itemView);
        randomListContainer = (CardView) itemView.findViewById(R.id.randomListContainer);
        title = (TextView) itemView.findViewById(R.id.title);
        servings = (TextView) itemView.findViewById(R.id.textServings);
        fav = (TextView) itemView.findViewById(R.id.favText);
        time = (TextView) itemView.findViewById(R.id.textTime);
        food = (ImageView) itemView.findViewById(R.id.imageViewFood);
    }

}
