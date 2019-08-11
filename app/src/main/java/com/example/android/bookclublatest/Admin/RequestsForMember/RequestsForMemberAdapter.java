package com.example.android.bookclublatest.Admin.RequestsForMember;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.bookclublatest.HomePage.BrowseBooks.BrowseAdapter;
import com.example.android.bookclublatest.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RequestsForMemberAdapter extends RecyclerView.Adapter <RequestsForMemberAdapter.ViewHolder> {
 Context context;
 ArrayList<RequetsForMemberModel> arrayList;

    public RequestsForMemberAdapter(Context context, ArrayList<RequetsForMemberModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public RequestsForMemberAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.allrequestformember, viewGroup, false);
        return new RequestsForMemberAdapter.ViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull RequestsForMemberAdapter.ViewHolder viewHolder, int i) {
  final RequetsForMemberModel requetsForMemberModel=arrayList.get(i);

       viewHolder.email.setText(requetsForMemberModel.getEmail());
        final  String email2=requetsForMemberModel.getEmail();
        final  String email1=email2.replace('.',',');
       viewHolder.makemember.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               AlertDialog.Builder alertdialog =new AlertDialog.Builder(context);
               alertdialog.setTitle("Are You Sure ?");
               alertdialog.setMessage(" Make him a member of a the BookCLub");
               alertdialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       Toast.makeText(context,"Successfully made member",Toast.LENGTH_LONG).show();
                       FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Status");

                        databaseReference.child(email1).setValue("Member").addOnCompleteListener(new OnCompleteListener<Void>() {
                           @Override
                           public void onComplete(@NonNull Task<Void> task) {
                               if(task.isSuccessful())
                               {
                                  DatabaseReference databaseReference2=FirebaseDatabase.getInstance().getReference("requstsformember");
                                  databaseReference2.child(email1).removeValue();
                               }
                           }
                       });

                        DatabaseReference databaseReference1 = firebaseDatabase.getReference("users");
                        databaseReference1.child(email1).child("status").setValue("Member").addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                    Toast.makeText(context,"Successfully made member",Toast.LENGTH_LONG).show();
                                else
                                    Toast.makeText(context,"Try Again",Toast.LENGTH_LONG).show();
                            }
                        });

                   }
               });
               alertdialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       Toast.makeText(context,"Not made member",Toast.LENGTH_LONG).show();

                   }
               });

            alertdialog.show();
           }
       });


       viewHolder.rejectformember.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Toast.makeText(context ,"request rejected",Toast.LENGTH_LONG).show();
               final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
               DatabaseReference databaseReference2=FirebaseDatabase.getInstance().getReference("requstsformember");
               databaseReference2.child(email1).removeValue();

           }
       });


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView email;
        TextView makemember,rejectformember;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            email=itemView.findViewById(R.id.requets_member_email);
            makemember=itemView.findViewById(R.id.make_him_member);
            rejectformember=itemView.findViewById(R.id.dont_make_him_member);



        }
    }

}
