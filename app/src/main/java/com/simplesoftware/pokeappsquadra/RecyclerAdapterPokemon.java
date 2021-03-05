package com.simplesoftware.pokeappsquadra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

import java.util.ArrayList;

public class RecyclerAdapterPokemon extends RecyclerView.Adapter<RecyclerAdapterPokemon.ViewHolder> implements Filterable {

    private ArrayList<Pokemon> dataset;
    private ArrayList<Pokemon> datasetFull;

    private Context context;


    public RecyclerAdapterPokemon(Context context) {
        this.context = context;
        dataset = new ArrayList<>();
        datasetFull = dataset;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pokemon pokemon = dataset.get(position);
        holder.tv_nomeTextView.setText(pokemon.getName());

        Glide.with(context)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + pokemon.getNumber() + ".png")
                .centerCrop()
                .transition(withCrossFade())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.iv_imageView);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void adicionarListaPokemon(ArrayList<Pokemon> listaPokemon) {
        dataset.addAll(listaPokemon);
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {

        return listFilter;
    }

    private Filter listFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Pokemon> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(datasetFull);

            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Pokemon p : datasetFull) {
                    if (p.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(p);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            dataset = ((ArrayList)results.values);
            notifyDataSetChanged();
        }
    };


    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_imageView;
        private TextView tv_nomeTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_imageView = itemView.findViewById(R.id.iv_imageView);
            tv_nomeTextView = itemView.findViewById(R.id.tv_nomeTextView);

        }
    }

}
