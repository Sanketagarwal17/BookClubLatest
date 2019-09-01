package com.example.android.bookclublatest.Member.ConfirmIssue;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.bookclublatest.ContactUs.ContactUsActivity;
import com.example.android.bookclublatest.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ConfirmIssueAdapter extends RecyclerView.Adapter<ConfirmIssueAdapter.ViewHolder>
{
    List<ConfirmIssueModel> list;
    Context context;
    Clicklistener clicklistener;

    public ConfirmIssueAdapter(List<ConfirmIssueModel> list, Context context,Clicklistener clicklistener) {
        this.list = list;
        this.context = context;
        this.clicklistener=clicklistener;
    }

    @NonNull
    @Override
    public ConfirmIssueAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(context).inflate(R.layout.card_issue_list,viewGroup,false);
        return new ViewHolder(view,clicklistener);
    }

    @Override
    public void onBindViewHolder(@NonNull ConfirmIssueAdapter.ViewHolder viewHolder, int i)
    {
        ConfirmIssueModel model=list.get(i);

        viewHolder.name.setText(model.getName());
        viewHolder.email.setText(model.getEmail());
        viewHolder.isbn.setText(model.getIsbn());
        viewHolder.send.setText("Proceed to issue book");
        Picasso.get().load(model.getUrl()).into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public void filterList(ArrayList<ConfirmIssueModel> filteredList) {
        list = filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView name,email,isbn,send;
        Clicklistener listener;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView,Clicklistener clicklistener) {
            super(itemView);

            listener=clicklistener;
            name=itemView.findViewById(R.id.isuuelist_name);
            email=itemView.findViewById(R.id.isuuelist_email);
            isbn=itemView.findViewById(R.id.isuuelist_isbn);
            send=itemView.findViewById(R.id.isuuelist_button);
            imageView=itemView.findViewById(R.id.imageView13);
            send.setOnClickListener(new View.OnClickListener() {
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
