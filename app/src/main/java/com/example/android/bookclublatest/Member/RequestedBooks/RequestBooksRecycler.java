package com.example.android.bookclublatest.Member.RequestedBooks;

import android.annotation.SuppressLint;
import android.content.Context;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import es.dmoral.toasty.Toasty;

import com.example.android.bookclublatest.HomePage.RequestBook.RequestModel;
import com.example.android.bookclublatest.R;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class RequestBooksRecycler extends RecyclerView.Adapter<RequestBooksRecycler.ViewHolder> {
    List<RequestModel> list;
    Context context;
    RequestModel model;
    final static String[] months={"Jan","Feb","Mar","Apr","May","June","July","Aug","Sep","Oct","Nov","Dec"};


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
         model = list.get(i);
         final int pos = i;
        if(model.getPubl().isEmpty())
            viewHolder.name.setText(model.getBook() + ", "+model.getAuthor());
        else
            viewHolder.name.setText(model.getBook() + ", "+model.getAuthor()+", "+model.getPubl());
//        viewHolder.author.setText(model.getAuthor());
//        viewHolder.publ.setText(model.getPubl());
        viewHolder.info.setText(model.getInfo());
        viewHolder.user.setText(model.getEmail());
        viewHolder.addedon.setText("Added on "+model.getApproval_date());
        viewHolder.date.setText("Date: "+ model.getRequested_date());
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
        viewHolder.done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference idRef = FirebaseDatabase.getInstance().getReference("Request Book").child(list.get(pos).getId());
                Calendar calendar2=Calendar.getInstance(TimeZone.getDefault());
                final String current=calendar2.get(Calendar.DAY_OF_MONTH) + " " + months[(calendar2.get(Calendar.MONTH))]
                        + "," + calendar2.get(Calendar.YEAR);
                idRef.child("approval_date").setValue(current);
                idRef.child("status").setValue("Approved");
                notifyDataSetChanged();

            }
        });
        viewHolder.deny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference idRef = FirebaseDatabase.getInstance().getReference("Request Book").child(list.get(pos).getId());
                idRef.removeValue(new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                        Toasty.success(context, "Request denied", Toast.LENGTH_SHORT,true).show();
                        notifyDataSetChanged();

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, author, publ, info, user , addedon, date;
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
            date = itemView.findViewById(R.id.request_date);

        }
    }
}
