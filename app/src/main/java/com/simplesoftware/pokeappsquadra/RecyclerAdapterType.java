package com.simplesoftware.pokeappsquadra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapterType extends RecyclerView.Adapter<RecyclerAdapterType.ViewHolder> implements Filterable{

    private ArrayList<Type> dataset;
    private ArrayList<Type> datasetFull;
    private RecyclerViewClickListener listener;

    private Context context;

    public RecyclerAdapterType(Context context, RecyclerViewClickListener listener){
        this.listener = listener;
        this.context = context;
        dataset = new ArrayList<>();
        datasetFull = dataset;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Type type = dataset.get(position);
        holder.tv_nomeType.setText(type.getName());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void adicionarListaType(ArrayList<Type> listaType){
        dataset.addAll(listaType);
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return listFilter;
    }

    private Filter listFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Type> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(datasetFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Type t : datasetFull){
                    if (t.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(t);
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


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView tv_nomeType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_nomeType = itemView.findViewById(R.id.tv_nomeType);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View itemView) {
            listener.onClick(itemView, getAdapterPosition());
        }
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }

}
