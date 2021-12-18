package com.example.translate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.ViewHolder> {

    private ArrayList<WordsModel> wordsModelArrayList;
    private Context context;

    public WordAdapter(ArrayList<WordsModel> wordsModelArrayList, Context context) {
        this.wordsModelArrayList=wordsModelArrayList;
        this.context=context;
    }
    public void filterList(ArrayList<WordsModel> filterList){
        wordsModelArrayList=filterList;
        notifyDataSetChanged();

    }


    @NonNull
    @Override
    public WordAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.word_item,parent,false);
        return new WordAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordAdapter.ViewHolder holder, int position) {
        WordsModel wordsModel = wordsModelArrayList.get(position);
        holder.textViewResult.setText(wordsModel.getWord());
    }

    @Override
    public int getItemCount() {
        return wordsModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewResult;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewResult=itemView.findViewById(R.id.textViewResult);
        }
    }
}
