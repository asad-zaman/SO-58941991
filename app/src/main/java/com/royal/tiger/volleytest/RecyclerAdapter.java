package com.royal.tiger.volleytest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> implements Filterable {

    private List<Recycler> liste;
    private List<Recycler> fullListe;

    RecyclerAdapter(List<Recycler> liste) {
        this.liste = liste;
        fullListe = new ArrayList<>(liste);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.test_tekst, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Recycler recycler = liste.get(position);
        holder.textView.setText(recycler.getNavn());
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView123);
        }
    }

    @Override
    public int getItemCount() {
        return liste.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Recycler> filtrert = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0) {
                filtrert.addAll(fullListe);
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for (Recycler navn : fullListe) {
                    if (navn.getNavn().toLowerCase().contains(filterPattern)) {
                        filtrert.add(navn);
                    }
                }
            }

            FilterResults resultat = new FilterResults();
            resultat.values = filtrert;

            return resultat;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults resultat) {
            liste.clear();
            liste.addAll((List) resultat.values);
            notifyDataSetChanged();
        }
    };


}
