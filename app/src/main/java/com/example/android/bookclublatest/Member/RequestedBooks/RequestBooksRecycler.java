package com.example.android.bookclublatest.Member.RequestedBooks;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bookclublatest.HomePage.RequestBook.RequestModel;
import com.example.android.bookclublatest.R;

import java.util.List;

public class RequestBooksRecycler extends RecyclerView.Adapter<RequestBooksRecycler.ViewHolder>
{
    List<RequestModel> list;
    Context context;

    public RequestBooksRecycler(List<RequestModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RequestBooksRecycler.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.card_request,viewGroup,false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RequestBooksRecycler.ViewHolder viewHolder, int i) {
        RequestModel model=list.get(i);
        viewHolder.name.setText(model.getBook());
        viewHolder.author.setText(model.getAuthor());
        viewHolder.publ.setText(model.getPubl());
        viewHolder.info.setText(model.getInfo());
        if(model.getPubl().isEmpty())
            viewHolder.publ.setText("Publications Not Provided");
        if(model.getInfo().isEmpty())
            viewHolder.info.setText("Additional Info Not Provided");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView name,author,publ,info;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.requested_name);
            author=itemView.findViewById(R.id.requested_author);
            publ=itemView.findViewById(R.id.requested_publ);
            info=itemView.findViewById(R.id.requested_info);
        }
    }
}
