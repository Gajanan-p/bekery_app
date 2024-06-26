package com.ssinfomate.bakeryapp.ui.saleretuen;
/*
Created by Gaju Pande from SSINFOMATE
 */
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.ssinfomate.bakeryapp.R;
import com.ssinfomate.bakeryapp.utils.AppPreference;
import com.ssinfomate.bakeryapp.webservices.Webservice;
import com.ssinfomate.bakeryapp.webservices.itemmaster.ItemMasterListModel;
import com.ssinfomate.bakeryapp.webservices.saleheaddetails.DeleteGrSaleHeadDetailModel;
import com.ssinfomate.bakeryapp.webservices.saleheaddetails.GrSaleDetailRequest;
import com.ssinfomate.bakeryapp.webservices.saleheaddetails.InsertGrSaleHeadDetail;
import com.ssinfomate.bakeryapp.webservices.saleheaddetails.ObjGrSaleDetailIn;
import com.ssinfomate.bakeryapp.webservices.saleheaddetails.RequestDeleteGrSaleHeadDetail;
import com.ssinfomate.bakeryapp.webservices.saleheaddetails.TempAddSale;
import com.ssinfomate.bakeryapp.webservices.user.UserModel;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaleReturnFragment extends Fragment implements View.OnClickListener,
             IListListener ,CustomItemClickListener   {

  //  private SaleReturnViewModel mViewModel;
  private ProgressDialog progressDialog;
    private ArrayList<ItemMasterListModel> itemMasterListModels;
    ArrayList<TempAddSale>tempAddSales = new ArrayList<>();
    AppCompatTextView appCompatTextViewItemName;
    AppCompatTextView appCompatEditTextDate;
    AppCompatEditText appCompatEditTextQty;
    AppCompatButton appCompatButtonCancel;
    AppCompatButton appCompatButtonSave;
    AppCompatButton appCompatButtonExport;
    AppCompatButton appCompatButtonAdd;
    RecyclerView recyclerViewList;
    ListAdapter adapter;
    TempAddSale tempAddSale;
    CustomDialog customDialog;
    Calendar calendar;

    public static SaleReturnFragment newInstance() {
        return new SaleReturnFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.sale_return_fragment, container, false);
        tempAddSales = new ArrayList<>();
        appCompatTextViewItemName = view.findViewById(R.id.text_sale_return_item_name);
        appCompatTextViewItemName.setOnClickListener(this);
        appCompatEditTextDate = view.findViewById(R.id.text_sale_return_date);
        appCompatEditTextQty = view.findViewById(R.id.text_sale_return_qty);
        appCompatButtonSave = view.findViewById(R.id.button_sale_return_save);
        appCompatButtonSave.setOnClickListener(this);
        appCompatButtonExport = view.findViewById(R.id.button_sale_return_export);
        appCompatButtonExport.setOnClickListener(this);
        appCompatButtonCancel = view.findViewById(R.id.button_sale_return_cancel);
        appCompatButtonCancel.setOnClickListener(this);
        appCompatButtonAdd = view.findViewById(R.id.button_sale_return_add);
        appCompatButtonAdd.setOnClickListener(this);
        calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy  hh:mm");
        String currentDate = simpleDateFormat.format(calendar.getTime());
        appCompatEditTextDate.setText(currentDate);

        recyclerViewList = view.findViewById(R.id.recycle_item_list);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text_sale_return_item_name:{
                  loadItemList();
                break;
            }
            case R.id.button_sale_return_add:{
                if (!TextUtils.isEmpty(appCompatEditTextQty.getText())&&!TextUtils.isEmpty(appCompatTextViewItemName.getText())){
                    updateTempList();}
                else {Toast.makeText(getContext(),"Enter All Fields",Toast.LENGTH_SHORT).show();}
                break;
            }
            case R.id.button_sale_return_save:{
                createSaleReturn();
                break;
            }
            case R.id.button_sale_return_cancel:{
                clearData();
                break;
            }
            case R.id.button_sale_return_export:{
                createExcelSheet();
                break;
            }
        }

    }

    public void loadItemList(){
        progressDialog = createProgressDialog(getContext());
        Call<ArrayList<ItemMasterListModel>> arrayItemListCall = Webservice
                .getItemMasterData()
                .getItemMasterList();
        arrayItemListCall.enqueue(new Callback<ArrayList<ItemMasterListModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ItemMasterListModel>> call, Response<ArrayList<ItemMasterListModel>> response) {
                itemMasterListModels = response.body();
                if (itemMasterListModels!=null){
                    getFilterList(itemMasterListModels.get(0));
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ArrayList<ItemMasterListModel>> call, Throwable t) {

            }
        });
    }

    public void updateTempList(){
        ItemMasterListModel itemMasterListModel = AppPreference.getItemDataPreferences(getContext());
        TempAddSale tempAddSale = new TempAddSale();
        tempAddSale.setDate(appCompatEditTextDate.getText().toString());
        tempAddSale.setItemid(itemMasterListModel.getItemmasterid());
        tempAddSale.setQuantity(Integer.parseInt(appCompatEditTextQty.getText().toString()));
        tempAddSale.setItemname(itemMasterListModel.getItemdesc());
        tempAddSale.setItemcode(itemMasterListModel.getItemcode());
        tempAddSales.add(tempAddSale);
        if (tempAddSales!=null) {
            updateList();
        }else {

        }
    }
    public void createSaleReturn(){
        GrSaleDetailRequest details = new GrSaleDetailRequest();
        UserModel userModel = AppPreference.getLoginDataPreferences(getContext());
        ItemMasterListModel itemMasterListModel = AppPreference.getItemDataPreferences(getContext());
        ArrayList<ObjGrSaleDetailIn> detailModels = new ArrayList<>();
        details.setPartyid(userModel.getAcmasterid());
        details.setOperationType("I");

        ObjGrSaleDetailIn  detail;
        if (tempAddSales!=null){
            if (tempAddSales.size()>0){
                for (int i=0; i<tempAddSales.size(); i++){
                    detail=new ObjGrSaleDetailIn();
                    TempAddSale tempAddSale  = tempAddSales.get(i);
                    detail.setItemid(tempAddSale.getItemid());
                    detail.setQuantity(tempAddSale.getQuantity());
                    detailModels.add(detail);
                }
                details.setObjGrSaleDetailIn(detailModels);
                sendInsertSaleToServer(details);
            }
        }
    }

    public void sendInsertSaleToServer(GrSaleDetailRequest grSaleDetailRequest) {
        progressDialog = createProgressDialog(getContext());
        Gson gson = new Gson();
        JsonObject data = gson.fromJson(gson.toJson(grSaleDetailRequest), JsonObject.class);
        Log.i("data Json", data.toString());

        Call<InsertGrSaleHeadDetail> arrayInsertSaleCall = Webservice
                .setGrSaleHeadDetailData()
                .setInsertSaleHeadDetail(data);
        arrayInsertSaleCall.enqueue(new Callback<InsertGrSaleHeadDetail>() {
            @Override
            public void onResponse(Call<InsertGrSaleHeadDetail> call, Response<InsertGrSaleHeadDetail> response) {
               if (response.isSuccessful()){
                   Log.i("data response", response.body().getMsg());
                   Toast.makeText(getContext(), response.body().getMsg(), Toast.LENGTH_LONG).show();
                   tempAddSales.clear();
                   adapter.notifyDataSetChanged();
                   progressDialog.dismiss();
               } else {
                   Toast.makeText(getContext(), "Sale Saved Successfully....", Toast.LENGTH_LONG).show();
               }
            }

            @Override
            public void onFailure(Call<InsertGrSaleHeadDetail> call, Throwable t) {
                Log.e("data error", t.getMessage());
                Toast.makeText(getContext(), "Sale Not Saved Successfully", Toast.LENGTH_LONG).show();
            }
        });

    }
    public void updateList(){
        if (tempAddSales != null) {
            adapter = new ListAdapter(tempAddSales,this);
            recyclerViewList.setAdapter(adapter);
            recyclerViewList.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        appCompatTextViewItemName.setText("");
        appCompatEditTextQty.setText("");

    }

    public ProgressDialog createProgressDialog(Context mContext) {
        ProgressDialog dialog = new ProgressDialog(mContext);
        try {
            dialog.show();
        } catch (WindowManager.BadTokenException e) {

        }
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.blue(100)));
        dialog.setContentView(R.layout.dialog_progress_layout);
        return dialog;
    }
    public void getDeleteData(RequestDeleteGrSaleHeadDetail requestDelDetail){
        requestDelDetail.setTrsno(Integer.parseInt(tempAddSale.getItemid()));
        Call<DeleteGrSaleHeadDetailModel> deleteCall=Webservice
                .setGrSaleHeadDetailData()
                .setDeleteGrSaleHeadDetail(requestDelDetail);
        deleteCall.enqueue(new Callback<DeleteGrSaleHeadDetailModel>() {
            @Override
            public void onResponse(Call<DeleteGrSaleHeadDetailModel> call, Response<DeleteGrSaleHeadDetailModel> response) {
              if (response.isSuccessful()){
                  Log.i("data response", response.body().getMsg());
                  Toast.makeText(getContext(), response.body().getMsg(), Toast.LENGTH_LONG).show();
              }
            }

            @Override
            public void onFailure(Call<DeleteGrSaleHeadDetailModel> call, Throwable t) {
                Log.e("data error", t.getMessage());
                Toast.makeText(getContext(), "Sale Not Deleted Successfully", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void getFilterList(ItemMasterListModel model){
        customDialog = new CustomDialog(getContext(),
                itemMasterListModels,
                SaleReturnFragment.this);
        customDialog.show();
    }

    @Override
    public void onItemClick(ItemMasterListModel model, int position) {
      //  ArrayList<ItemMasterListModel> itemMasterListModel = AppPreference.getItemDataPreferences(getContext());
        AppPreference.setItemDataPreferences(getContext(),model);
        appCompatTextViewItemName.setText(model.getItemcode());
        if (customDialog !=null){
            customDialog.dismiss();
        }
    }

    @Override
    public void onDeleteButtonClicked(TempAddSale tempAddSale, int position) {

    }
    public void clearData(){
        appCompatTextViewItemName.setText("");
        appCompatEditTextQty.setText("");
    }
    private void createExcelSheet() {
        Workbook wb=new HSSFWorkbook();
        Cell cell=null;
        CellStyle cellStyle=wb.createCellStyle();
        cellStyle.setFillForegroundColor(HSSFColor.LIGHT_BLUE.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        Font font = wb.createFont();
        font.setColor(HSSFColor.WHITE.index);
        cellStyle.setFont(font);
        //Now we are creating sheet
        Sheet sheet=null;
        sheet = wb.createSheet("Name of sheet");
        //Now column and row
        Row row =sheet.createRow(0);

        cell=row.createCell(0);
        cell.setCellValue("Item Code");
        cell.setCellStyle(cellStyle);

        cell=row.createCell(1);
        cell.setCellValue("Item Name");
        cell.setCellStyle(cellStyle);

        cell=row.createCell(2);
        cell.setCellValue("Quantity");
        cell.setCellStyle(cellStyle);

        sheet.setColumnWidth(0,(10*200));
        sheet.setColumnWidth(1,(10*500));
        sheet.setColumnWidth(2,(10*100));

        for (int i=0; i<tempAddSales.size();i++){
            // Create a New Row for every new entry in list
            Row rowData = sheet.createRow(i + 1);
            // Create Cells for each row
            cell = rowData.createCell(0);
            cell.setCellValue(tempAddSales.get(i).getItemcode());
            cell = rowData.createCell(1);
            cell.setCellValue(tempAddSales.get(i).getItemname());
            cell = rowData.createCell(2);
            cell.setCellValue(tempAddSales.get(i).getQuantity());

        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        String currentDate = dateFormat.format(calendar.getTime());
        String excelPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        File file = new File(excelPath,currentDate+".xls");
        FileOutputStream outputStream =null;

        try {
            outputStream=new FileOutputStream(file);
            wb.write(outputStream);
            Toast.makeText(getContext(),"Excel Created Successfully",Toast.LENGTH_LONG).show();
        } catch (java.io.IOException e) {
            e.printStackTrace();

            Toast.makeText(getContext(),"Excel Not Created Successfully",Toast.LENGTH_LONG).show();
            try {
                outputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}