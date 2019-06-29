package com.example.android.bookclublatest.Faq.MVP;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bookclublatest.Faq.Model.FAQModel;
import com.example.android.bookclublatest.R;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.ArrayList;

public class FAQAdapter extends RecyclerView.Adapter<FAQAdapter.ViewHolder> {
    ArrayList<FAQModel> list;
    Context context;

    public FAQAdapter(ArrayList<FAQModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public FAQAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.faq_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FAQAdapter.ViewHolder viewHolder, int i) {
        FAQModel model = list.get(i);

        viewHolder.title.setText(model.getQuestion());
        viewHolder.content.setText(model.getContent());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ExpandableTextView content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.expand_title);
            content = itemView.findViewById(R.id.expand_text_view);
        }
    }
}