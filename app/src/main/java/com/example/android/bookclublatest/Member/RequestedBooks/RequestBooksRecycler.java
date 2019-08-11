package com.example.android.bookclublatest.Member.RequestedBooks;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.android.bookclublatest.HomePage.RequestBook.RequestModel;
import com.example.android.bookclublatest.R;

import java.util.List;

public class RequestBooksRecycler extends RecyclerView.Adapter<RequestBooksRecycler.ViewHolder> {
    List<RequestModel> list;
    Context context;

    public RequestBooksRecycler(List<RequestModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RequestBooksRecycler.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_request, viewGroup, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RequestBooksRecycler.ViewHolder viewHolder, int i) {
        RequestModel model = list.get(i);
        if(model.getPubl().isEmpty())
            viewHolder.name.setText(model.getBook() + ", "+model.getAuthor());
        else
            viewHolder.name.setText(model.getBook() + ", "+model.getAuthor()+", "+model.getPubl());
//        viewHolder.author.setText(model.getAuthor());
//        viewHolder.publ.setText(model.getPubl());
        viewHolder.info.setText(model.getInfo());
        viewHolder.user.setText(model.getEmail());
        viewHolder.addedon.setText("Added on "+model.getApproval_date());
        if (model.getInfo().isEmpty())
            viewHolder.info.setText("Additional Info Not Provided");
        if(model.getStatus().equals("Pending")){
            viewHolder.done.setVisibility(View.VISIBLE);
            viewHolder.deny.setVisibility(View.VISIBLE);
            viewHolder.greenbar.setVisibility(View.GONE);
        }else if(model.getStatus().equals("Approved")) {
            viewHolder.done.setVisibility(View.GONE);
            viewHolder.deny.setVisibility(View.GONE);
            viewHolder.greenbar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, author, publ, info, user , addedon;
        RelativeLayout done,deny;
        ConstraintLayout greenbar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.requested_name);
//            author=itemView.findViewById(R.id.requested_author);
//            publ=itemView.findViewById(R.id.requested_publ);
            info = itemView.findViewById(R.id.requested_info);
            user = itemView.findViewById(R.id.requested_user);
            done = itemView.findViewById(R.id.done_button);
            deny = itemView.findViewById(R.id.deny_button);
            addedon = itemView.findViewById(R.id.requestapprovedon);
            greenbar = itemView.findViewById(R.id.greenbar);
        }
    }
}
