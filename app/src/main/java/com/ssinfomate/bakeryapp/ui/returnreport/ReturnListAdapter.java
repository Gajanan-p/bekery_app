package com.ssinfomate.bakeryapp.ui.returnreport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ssinfomate.bakeryapp.R;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ReturnListAdapter extends RecyclerView.Adapter<ReturnListAdapter.ViewHolder> {
    ArrayList<ReturnReportViewModel> reportViewModels;
    Context context;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    public ReturnListAdapter(ArrayList<ReturnReportViewModel> reportViewModels,
                             Context context) {
        this.reportViewModels = reportViewModels;
        this.context = context;
    }

    @NonNull
    @Override
    public ReturnListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_return_list,parent,false);
        return new ReturnListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReturnListAdapter.ViewHolder holder, int position) {
        String pattern = "dd|MM|yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        ReturnReportViewModel reportViewModel = reportViewModels.get(position);
      //  holder.textViewGRSALEDTID.setText(reportViewModel.getGrsaledtid());
        holder.textViewTRSNO.setText(reportViewModel.getTrsno());
        holder.textViewTRDATE.setText(reportViewModel.getTrdate());
        holder.textViewITEMID.setText(reportViewModel.getSrno());
        holder.textViewSRNO.setText(reportViewModel.getItemid());
        holder.textViewQUANTITY.setText(reportViewModel.getQuantity());

    }

    @Override
    public int getItemCount() {
        return reportViewModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewGRSALEDTID,textViewTRSNO,textViewTRDATE,textViewSRNO,textViewITEMID,textViewQUANTITY;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
         //   textViewGRSALEDTID = itemView.findViewById(R.id.text_row_return_GRSALEDTID);
            textViewTRSNO = itemView.findViewById(R.id.text_row_return_TRSNO);
            textViewTRDATE = itemView.findViewById(R.id.text_row_return_TRDATE);
            textViewSRNO = itemView.findViewById(R.id.text_row_return_SRNO);
            textViewITEMID = itemView.findViewById(R.id.text_row_return_ITEMID);
            textViewQUANTITY = itemView.findViewById(R.id.text_row_return_QUANTITY);
        }

    }
}
