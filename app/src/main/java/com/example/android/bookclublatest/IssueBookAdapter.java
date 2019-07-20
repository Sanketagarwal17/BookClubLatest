package com.example.android.bookclublatest;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bookclublatest.Member.AddBook.AddBookModel;

import java.util.ArrayList;

public class IssueBookAdapter extends RecyclerView.Adapter<IssueBookAdapter.IssueBookViewHolder> {

    private static final String TAG = "IssueBookAdapter";
    Context context;
    ArrayList<AddBookModel> addBookModel;

    public IssueBookAdapter(Context context, ArrayList<AddBookModel> addBookModel) {
        this.context = context;
        this.addBookModel = addBookModel;
    }

    @NonNull
    @Override
    public IssueBookViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.issue_book_card,viewGroup,false);
        final IssueBookViewHolder issueBookViewHolder = new IssueBookViewHolder(v);
        /*v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,IssueBookDetailActivity.class);
                intent.putExtra("author",addBookModel.get(i).getAuthor());
                intent.putExtra("book",addBookModel.get(i).getBook());
                intent.putExtra("hardsofy",addBookModel.get(i).getHardsofy());
                intent.putExtra("isbn",addBookModel.get(i).getIsbn());
                intent.putExtra("ism",addBookModel.get(i).getIsm());
                intent.putExtra("publisher",addBookModel.get(i).getPublisher());
                intent.putExtra("tags",addBookModel.get(i).getTags());
                Log.d(TAG, "onClick: " + i);
                Pair[] pairs = new Pair[3];
                pairs[0] = new Pair<View,String>(issueBookViewHolder.book,"ts_book");
                pairs[1] = new Pair<View,String>(issueBookViewHolder.author,"ts_author");
                pairs[2] = new Pair<View,String>(issueBookViewHolder.pub,"ts_pub");
                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation((Activity) context,pairs);
                context.startActivity(intent,activityOptions.toBundle());

            }
        });*/
        return issueBookViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final IssueBookViewHolder issueBookViewHolder, final int i) {
        AddBookModel addBookModel1 = addBookModel.get(i);
        issueBookViewHolder.book.setText(addBookModel1.getBook());
        issueBookViewHolder.author.setText(addBookModel1.getAuthor());
        issueBookViewHolder.pub.setText(addBookModel1.getPublisher());
        issueBookViewHolder.bookCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,IssueBookDetailActivity.class);
                intent.putExtra("author",addBookModel.get(i).getAuthor());
                intent.putExtra("book",addBookModel.get(i).getBook());
                intent.putExtra("hardsofy",addBookModel.get(i).getHardsofy());
                intent.putExtra("isbn",addBookModel.get(i).getIsbn());
                intent.putExtra("ism",addBookModel.get(i).getIsm());
                intent.putExtra("publisher",addBookModel.get(i).getPublisher());
                intent.putExtra("tags",addBookModel.get(i).getTags());
                Log.d(TAG, "onClick: " + i);
                Pair[] pairs = new Pair[3];
                pairs[0] = new Pair<View,String>(issueBookViewHolder.book,"ts_book");
                pairs[1] = new Pair<View,String>(issueBookViewHolder.author,"ts_author");
                pairs[2] = new Pair<View,String>(issueBookViewHolder.pub,"ts_pub");
                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation((Activity) context,pairs);
                context.startActivity(intent,activityOptions.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount:" + addBookModel.size());

        return addBookModel.size();
    }

    public class IssueBookViewHolder extends RecyclerView.ViewHolder
    {
        TextView book,pub,author;
        ConstraintLayout bookCard;
        public IssueBookViewHolder(@NonNull View itemView) {
            super(itemView);
            book = itemView.findViewById(R.id.tv_book);
            pub = itemView.findViewById(R.id.tv_pub);
            author = itemView.findViewById(R.id.tv_author);
            bookCard = itemView.findViewById(R.id.book_card);
        }
    }
}
