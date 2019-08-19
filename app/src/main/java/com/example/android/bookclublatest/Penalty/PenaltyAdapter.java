package com.example.android.bookclublatest.Penalty;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bookclublatest.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static android.provider.Settings.System.DATE_FORMAT;

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
        String issuedate = list.get(i).getIssue_date();
        String returndate = list.get(i).getReturned_on();
        String expectedreturndate = list.get(i).getReturn_date();
        viewHolder.issue_date.setText("Issued on : " + list.get(i).getIssue_date());
        viewHolder.expected_return.setText("Expected date: " + list.get(i).getReturn_date());
        viewHolder.isbn_code.setText("ISBN " + list.get(i).getIsbn());
        viewHolder.return_date.setText("Returned On:     " + list.get(i).getReturned_on());



        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sep", "Oct", "Nov", "Dec"};
        int first = issuedate.indexOf(' ');
        String date2 = issuedate.substring(0, first);
        int second = issuedate.indexOf(',');
        String month2 = issuedate.substring(first + 1, second);
        String year2 = issuedate.substring(second + 1);
        for (int j = 0; j < 12; j++) {
            if (month2.equals(months[j])) {
                month2 = "" + (j + 1);
                break;
            }
        }
        String pissue = date2 + "/" + month2 + "/" + year2;

        first = returndate.indexOf(' ');
        date2 = returndate.substring(0, first);
        second = returndate.indexOf(',');
        month2 = returndate.substring(first + 1, second);
        year2 = returndate.substring(second + 1);
        for (int k = 0; k < 12; k++) {
            if (month2.equals(months[k])) {
                month2 = "" + (k + 1);
                break;
            }
        }
        String preturn = date2 + "/" + month2 + "/" + year2;


        long totalfine=10;
        long  totaldays=getDaysBetweenDates(issuedate,returndate);
        long totallegaldayes=getDaysBetweenDates(issuedate,expectedreturndate);

        if(totaldays<=totallegaldayes)
            totalfine=0;
        else {
            totaldays=totaldays-totallegaldayes;
            if (totaldays >= 0) {
                totalfine = 10;
            }
            if (totaldays >= 7) {
                totaldays = totaldays - 7;
            }
            if (totaldays > 0) {
                totalfine = totalfine + 2 * totaldays;
            }
        }
        viewHolder.penaltyi.setText("₹ "+String.valueOf(totalfine));




    }

    private long getDaysBetweenDates(String issuedate, String returndate) {
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sep", "Oct", "Nov", "Dec"};
        int first = issuedate.indexOf(' ');
        String date = issuedate.substring(0, first);
        int second = issuedate.indexOf(',');
        String month = issuedate.substring(first + 1, second);
        String year = issuedate.substring(second + 1);
        for (int j = 0; j < 12; j++) {
            if (month.equals(months[j])) {
                month = "" + (j + 1);
                break;
            }
        }

        Calendar c1=Calendar.getInstance();
        c1.set(Integer.parseInt(year) ,Integer.parseInt(month) , Integer.parseInt(date)  );

         first = returndate.indexOf(' ');
        String date2 = returndate.substring(0, first);
         second = returndate.indexOf(',');
        String month2 = returndate.substring(first + 1, second);
        String year2 = returndate.substring(second + 1);
        for (int k = 0; k < 12; k++) {
            if (month2.equals(months[k])) {
                month2 = "" + (k + 1);
                break;
            }
        }
        Calendar c2= Calendar.getInstance();
        c2.set(Integer.parseInt(year2) ,Integer.parseInt(month2) , Integer.parseInt(date2)  );
        Date d1=c1.getTime();
        Date d2=c2.getTime();
        long diff=d2.getTime()-d1.getTime();
        int noofdays=(int)(diff/(1000*24*60*60));
        return  noofdays;
    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       TextView book_name,issue_date,return_date,penaltyi,isbn,expected_return,isbn_code;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            book_name=itemView.findViewById(R.id.p_book_name);
            issue_date=itemView.findViewById(R.id.p_issue_date);
            return_date=itemView.findViewById(R.id.p_return_date);
            penaltyi=itemView.findViewById(R.id.p_penalty);
            expected_return=itemView.findViewById(R.id.p_expected_return_date);
            isbn_code=itemView.findViewById(R.id.p_isbn_code);



        }
    }
}
