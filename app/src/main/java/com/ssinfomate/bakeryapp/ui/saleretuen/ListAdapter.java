package com.ssinfomate.bakeryapp.ui.saleretuen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ssinfomate.bakeryapp.R;
import com.ssinfomate.bakeryapp.webservices.saleheaddetails.TempAddSale;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    ArrayList<TempAddSale>tempAddSales;
    IListListener iListListener;

    public ListAdapter(ArrayList<TempAddSale> tempAddSales, IListListener iListListener) {
        this.tempAddSales = tempAddSales;
        this.iListListener = iListListener;
    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_sale_return_list,
                parent,false);

        return new ListAdapter.ViewHolder(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int position) {
        TempAddSale tempAddSale= tempAddSales.get(position);
        holder.textViewItemName.setText(tempAddSale.getItemname());
        holder.textViewItemCode.setText(tempAddSale.getItemcode());
        holder.textViewQty.setText(tempAddSale.getQuantity().toString());
//        holder.buttonDel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //iListListener.onDeleteButtonClicked(tempAddSale,position);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return tempAddSales.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewItemCode,textViewItemName,textViewQty;
        Button buttonDel;
        private ListAdapter adapter;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewItemCode = itemView.findViewById(R.id.text_row_item_code);
            textViewItemName = itemView.findViewById(R.id.text_row_item_name);
            textViewQty = itemView.findViewById(R.id.text_row_qty);
            itemView.findViewById(R.id.button_row_delete).setOnClickListener(view->{
                adapter.tempAddSales.remove(getAdapterPosition());
                adapter.notifyItemRemoved(getAdapterPosition());
            });
        }
        public ViewHolder linkAdapter(ListAdapter adapter){
            this.adapter = adapter;
            return this;

        }
    }
}
