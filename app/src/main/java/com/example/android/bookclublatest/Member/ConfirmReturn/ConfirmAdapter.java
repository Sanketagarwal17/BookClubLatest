package com.example.android.bookclublatest.Member.ConfirmReturn;

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
import com.squareup.picasso.Picasso;

import java.util.List;

public class ConfirmAdapter extends RecyclerView.Adapter<ConfirmAdapter.ViewHolder>
{
    List<ConfirmModel> list;
    Context context;
    Clicklistener clickListener;

    public ConfirmAdapter(List<ConfirmModel> list, Context context, Clicklistener clickListener) {
        this.list = list;
        this.context = context;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ConfirmAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.card_confirm_return,viewGroup,false);
        return new ViewHolder(view,clickListener);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ConfirmAdapter.ViewHolder viewHolder, int i)
    {
        ConfirmModel model=list.get(i);
        viewHolder.request.setText("Proceed to Return");
        viewHolder.book.setText(model.getBookname());
        viewHolder.email.setText(model.getEmail());
        Picasso.get().load(model.getUrl()).into(viewHolder.imageView);
        viewHolder.date.setText("Supposed to be returned on  "+model.getReturn_date());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        Clicklistener listener;
        TextView book, date, email, request;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView, Clicklistener clickListener)
        {
            super(itemView);
            listener=clickListener;
            book=itemView.findViewById(R.id.returnlist_name);
            email=itemView.findViewById(R.id.returnlist_email);
            date=itemView.findViewById(R.id.returnlist_date);
            request=itemView.findViewById(R.id.returnlist_button);
            imageView=itemView.findViewById(R.id.imageView13);
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
