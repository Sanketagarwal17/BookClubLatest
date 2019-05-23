package com.example.android.bookclublatest.HomePage.BrowseBooks;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.bookclublatest.R;

import java.util.ArrayList;

public class BrowseAdapter extends RecyclerView.Adapter<BrowseAdapter.ViewHolder>
{
    Context context;
    ArrayList<BrowseModel> arrayList;

    public BrowseAdapter(Context context, ArrayList<BrowseModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public BrowseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_browse_books_detail, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BrowseAdapter.ViewHolder viewHolder, int position)
    {
        BrowseModel model=arrayList.get(position);
        viewHolder.name.setText(model.getName());
        viewHolder.author.setText(model.getAuthor());
        viewHolder.publisher.setText(model.getPublisher());
        viewHolder.hard.setText("Hard Sets : "+model.getHard());
        viewHolder.soft.setText("Sost Sets : "+model.getSoft());
        viewHolder.imageView.setImageResource(R.drawable.person);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView name,author,publisher,hard,soft;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            name=itemView.findViewById(R.id.browse_bookname);
            author=itemView.findViewById(R.id.browse_bookauthor);
            publisher=itemView.findViewById(R.id.browse_bookpublisher);
            hard=itemView.findViewById(R.id.browse_bookhard);
            soft=itemView.findViewById(R.id.browse_booksoft);
            imageView=itemView.findViewById(R.id.browse_bookimage);
        }
    }
}
