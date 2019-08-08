package com.example.android.bookclublatest.Penalty;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bookclublatest.R;

import java.util.List;

public class PenaltyAdapter extends RecyclerView.Adapter<PenaltyAdapter.ViewHolder> {

    List<PenaltyModel>list;
    Context context;

    public PenaltyAdapter(List<PenaltyModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public PenaltyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.layout_penalty,viewGroup,false);
        return new ViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull PenaltyAdapter.ViewHolder viewHolder, int i) {

        viewHolder.book_name.setText(list.get(i).getBookname());
        String issuedate=list.get(i).getIssue_date();
        String returndate=list.get(i).getReturn_date();
        viewHolder.issue_date.setText(list.get(i).getIssue_date());
        viewHolder.return_date.setText(list.get(i).getReturn_date());
       viewHolder.penaltyi.setText("0");

        String[] months={"Jan","Feb","Mar","Apr","May","June","July","Aug","Sep","Oct","Nov","Dec"};

/*        for(i=0; i<is.length(); i++)
        {
            if(is[i]=='-')
            {

            }
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy",Locale.ENGLISH);
        Date Date1 = null,Date2 = null;
        try{
            Date1 = sdf.parse(issuedate);
            Date2 = sdf.parse(returndate);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        long noofdays= (Date2.getTime() - Date1.getTime())/(24*60*60*1000);


*/

 //assume penalty =0;

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       TextView book_name,issue_date,return_date,penaltyi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            book_name=itemView.findViewById(R.id.p_book_name);
            issue_date=itemView.findViewById(R.id.p_issue_date);
            return_date=itemView.findViewById(R.id.p_return_date);
            penaltyi=itemView.findViewById(R.id.p_penalty);


        }
    }
}
