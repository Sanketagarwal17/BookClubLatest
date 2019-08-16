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
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.bookclublatest.Member.AddBook.AddBookModel;
import com.example.android.bookclublatest.Member.AddBook.AddBookModel1;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class IssueBookAdapter extends RecyclerView.Adapter<IssueBookAdapter.IssueBookViewHolder> {

    private static final String TAG = "IssueBookAdapter";
    Context context;
    ArrayList<AddBookModel1> addBookModel;

    public IssueBookAdapter(Context context, ArrayList<AddBookModel1> addBookModel) {
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
       final AddBookModel1 addBookModel1 = addBookModel.get(i);
        Picasso.get().load(addBookModel1.getUrl()).into(issueBookViewHolder.book_image);
        issueBookViewHolder.book.setText(addBookModel1.getBook());
        issueBookViewHolder.author.setText(addBookModel1.getAuthor());
        issueBookViewHolder.pub.setText(addBookModel1.getPublisher());
        issueBookViewHolder.status.setText(addBookModel1.getStatus());
        issueBookViewHolder.no.setText(addBookModel1.getNo());
        issueBookViewHolder.bookCard.setAnimation(AnimationUtils.loadAnimation(context,R.anim.anim));
        issueBookViewHolder.bookCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,IssueBookDetailActivity.class);
                intent.putExtra("author",addBookModel1.getAuthor());
                intent.putExtra("book",addBookModel1.getBook());
                intent.putExtra("hardsofy",addBookModel1.getHardsofy());
                intent.putExtra("isbn",addBookModel1.getIsbn());
                intent.putExtra("ism",addBookModel1.getIsm());
                intent.putExtra("publisher",addBookModel1.getPublisher());
                intent.putExtra("tags",addBookModel1.getTags());
                intent.putExtra("status",addBookModel1.getStatus());
                intent.putExtra("no",addBookModel1.getNo());
                intent.putExtra("url",addBookModel1.getUrl());
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
        TextView book,pub,author,status,no;
        ConstraintLayout bookCard;
        ImageView book_image;

        public IssueBookViewHolder(@NonNull View itemView) {
            super(itemView);
            book = itemView.findViewById(R.id.tv_book);
            pub = itemView.findViewById(R.id.tv_pub);
            author = itemView.findViewById(R.id.tv_author);
            bookCard = itemView.findViewById(R.id.book_card);
            status=itemView.findViewById(R.id.textView24);
            no = itemView.findViewById(R.id.textView25);
            book_image = itemView.findViewById(R.id.issue_card_book_image);
        }
    }
    public void filterList(ArrayList<AddBookModel1> addBookModels)
    {
        addBookModel=addBookModels;
        notifyDataSetChanged();
    }
}
