package com.example.android.bookclublatest.HomePage.History;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.bookclublatest.R;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder>
{
    Context context;
    List<HistoryModel> list;

    public HistoryAdapter(Context context, List<HistoryModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.issue_history_book_card,viewGroup,false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder viewHolder, int i)
    {
        HistoryModel model=list.get(i);
        viewHolder.status.setText(model.getStatus());
        viewHolder.book.setText(model.getBookname());
        viewHolder.issue.setText(model.getIssue_date());
        viewHolder.ret.setText(model.getReturn_date());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView book,issue,ret,status;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            book=itemView.findViewById(R.id.bookname_tv);
            issue=itemView.findViewById(R.id.textView22);
            status=itemView.findViewById(R.id.author_tv);
            ret=itemView.findViewById(R.id.return_date_tv);
            imageView = itemView.findViewById(R.id.imageView5);
        }
    }
}
