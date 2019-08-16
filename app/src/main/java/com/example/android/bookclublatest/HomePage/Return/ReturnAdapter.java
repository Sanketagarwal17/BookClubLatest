package com.example.android.bookclublatest.HomePage.Return;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.bookclublatest.HomePage.History.HistoryAdapter;
import com.example.android.bookclublatest.HomePage.History.HistoryModel;
import com.example.android.bookclublatest.Member.ConfirmIssue.ConfirmIssueAdapter;
import com.example.android.bookclublatest.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ReturnAdapter extends RecyclerView.Adapter<ReturnAdapter.ViewHolder> {
    Context context;
    List<HistoryModel> list;
    ReturnAdapter.Clicklistener clicklistener;

    public ReturnAdapter(Context context, List<HistoryModel> list, Clicklistener clicklistener) {
        this.context = context;
        this.list = list;
        this.clicklistener = clicklistener;
    }

    @NonNull
    @Override
    public ReturnAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_return_list, viewGroup, false);
        return new ViewHolder(view,clicklistener);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ReturnAdapter.ViewHolder viewHolder, int i) {
        HistoryModel model = list.get(i);
        viewHolder.book.setText("BookName : " + model.getBookname());
        viewHolder.issue.setText(model.getIssue_date());
        viewHolder.ret.setText(model.getReturn_date());
        Picasso.get().load(model.getUrl()).into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView book, isbn, issue, ret, request;
        Clicklistener listener;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView,Clicklistener clicklistener) {
            super(itemView);

            listener=clicklistener;
            book = itemView.findViewById(R.id.history_book);
            isbn = itemView.findViewById(R.id.history_isbn);
            issue = itemView.findViewById(R.id.history_issue);
            request = itemView.findViewById(R.id.history_returnRequest);
            ret = itemView.findViewById(R.id.history_return);
            imageView= itemView.findViewById(R.id.return_book_image);

            request.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.proceed(getAdapterPosition());
                }
            });
        }
    }
    interface Clicklistener
    {
        void proceed(int pos);
    }
}