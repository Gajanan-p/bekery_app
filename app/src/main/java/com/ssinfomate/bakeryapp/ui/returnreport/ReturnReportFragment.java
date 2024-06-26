package com.ssinfomate.bakeryapp.ui.returnreport;



import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import com.ssinfomate.bakeryapp.R;
import com.ssinfomate.bakeryapp.utils.AppPreference;
import com.ssinfomate.bakeryapp.webservices.Webservice;
import com.ssinfomate.bakeryapp.webservices.user.UserModel;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReturnReportFragment extends Fragment implements View.OnClickListener {

  //  private ReturnReportViewModel mViewModel;
    ArrayList<ReturnReportViewModel> listReportViewModels;
    ReturnListAdapter adapter;
    RecyclerView recyclerViewReport;
    SimpleDateFormat simpleDateFormat;
    Calendar calendar;
    Button buttonCreatePDF;
    public static ReturnReportFragment newInstance() {
        return new ReturnReportFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.return_report_fragment, container, false);

        buttonCreatePDF = view.findViewById(R.id.button_create_pdf);
        buttonCreatePDF.setOnClickListener(this);
        recyclerViewReport  = view.findViewById(R.id.recycle_return_list);
        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getReportList();
    }
    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.button_create_pdf){
            try {
                createPdf(listReportViewModels);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    public void getReportList(){
        GetPartyReturnDetailsRequest request = new GetPartyReturnDetailsRequest();
        UserModel userModel = AppPreference.getLoginDataPreferences(getContext());
        request.setPartyid(userModel.getAcmasterid());

        Call<ArrayList<ReturnReportViewModel>>callReport = Webservice
                .getReport()
                .getReportList(request);
        callReport.enqueue(new Callback<ArrayList<ReturnReportViewModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ReturnReportViewModel>> call, Response<ArrayList<ReturnReportViewModel>> response) {
                listReportViewModels = response.body();
                if (listReportViewModels!=null) {
                    changeDataSet(listReportViewModels);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ReturnReportViewModel>> call, Throwable t) {

            }
        });
    }

    public void changeDataSet(ArrayList<ReturnReportViewModel> reportViewModels){
        listReportViewModels=reportViewModels;
        adapter=new ReturnListAdapter(reportViewModels,getContext());
        recyclerViewReport.setAdapter(adapter);
        recyclerViewReport.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void createPdf(ArrayList<ReturnReportViewModel> reportViewModels) throws FileNotFoundException {
        String pdfPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        String currentDate = dateFormat.format(calendar.getTime());
        File file = new File(pdfPath, currentDate+".pdf");//listReportViewModels.get(0).getTrdate()+

        OutputStream outputStream = new FileOutputStream(file);

        PdfWriter pdfWriter = new PdfWriter(file);

        PdfDocument pdfDocument = new PdfDocument(pdfWriter);

        Document document = new Document(pdfDocument);

        pdfDocument.setDefaultPageSize(PageSize.A4);
        document.setMargins(0, 5, 0, 5);

        Drawable drawable = ContextCompat.getDrawable(getActivity(),R.drawable.logo_icon);

        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
        ByteArrayOutputStream outputStream1 = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream1);
        byte[] bitmapData = outputStream1.toByteArray();

        ImageData imageData = ImageDataFactory.create(bitmapData);
        Image image = new Image(imageData);
        image.scaleToFit(400, 700);
        image.setFixedPosition(100,350);
        image.setOpacity(0.3f);



        float[] width = {100f,150f,100f,300f,100f};
        Table table = new Table(width);
        table.setHorizontalAlignment(HorizontalAlignment.CENTER);

        table.addCell(new Cell().add(new Paragraph("TRSNO")).setBold().setFontColor(ColorConstants.WHITE)
                .setBackgroundColor(ColorConstants.BLUE));
        table.addCell(new Cell().add(new Paragraph("TRDATE")).setBold().setFontColor(ColorConstants.WHITE)
                .setBackgroundColor(ColorConstants.BLUE));
        table.addCell(new Cell().add(new Paragraph("ITEMCODE")).setBold().setFontColor(ColorConstants.WHITE)
                .setBackgroundColor(ColorConstants.BLUE));
        table.addCell(new Cell().add(new Paragraph("IETEMDEC")).setBold().setFontColor(ColorConstants.WHITE)
                .setBackgroundColor(ColorConstants.BLUE));
        table.addCell(new Cell().add(new Paragraph("QUANTITY")).setBold().setFontColor(ColorConstants.WHITE)
                .setBackgroundColor(ColorConstants.BLUE));

        for (int i=0;i<listReportViewModels.size();i++){
            ReturnReportViewModel reportViewModel = listReportViewModels.get(i);
            table.addCell(new Cell().add(new Paragraph(reportViewModel.getTrsno())));
            table.addCell(new Cell().add(new Paragraph(reportViewModel.getTrdate())));
            table.addCell(new Cell().add(new Paragraph(reportViewModel.getSrno())));
            table.addCell(new Cell().add(new Paragraph(reportViewModel.getItemid())));
            table.addCell(new Cell().add(new Paragraph(reportViewModel.getQuantity())));
        }
        document.add(image);
        document.add(table);
        document.close();
        Toast.makeText(getContext(),"pdf Created",Toast.LENGTH_LONG).show();
    }

}