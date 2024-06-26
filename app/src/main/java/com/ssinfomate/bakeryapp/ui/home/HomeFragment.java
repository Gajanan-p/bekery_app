package com.ssinfomate.bakeryapp.ui.home;
/*
Created by Gaju Pande from SSINFOMATE
 */
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.ssinfomate.bakeryapp.R;
import com.ssinfomate.bakeryapp.utils.AppPreference;
import com.ssinfomate.bakeryapp.webservices.Webservice;
import com.ssinfomate.bakeryapp.webservices.user.UserModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements View.OnClickListener {

   // private HomeViewModel homeViewModel;
   // private FragmentHomeBinding binding;
    NavController navController;
    AppCompatTextView textViewTotalValue;
    HomeViewModel model;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        textViewTotalValue = view.findViewById(R.id.edit_home_total_qty);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
      //  binding = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
    }

    @Override
    public void onResume() {
        super.onResume();
        getTotalQuantity();
    }

    @Override
    public void onClick(View v) {}

    void getTotalQuantity(){
        PartyWiseTotalQtyRequest request = new PartyWiseTotalQtyRequest();
        UserModel userModel = AppPreference.getLoginDataPreferences(getContext());
        request.setPartyid(userModel.getAcmasterid());
        Call<HomeViewModel> viewModelCall = Webservice
                .getReport()
                .getValue(request);
        viewModelCall.enqueue(new Callback<HomeViewModel>() {
            @Override
            public void onResponse(Call<HomeViewModel> call, Response<HomeViewModel> response) {
                model = response.body();
                setValue(model);
            }

            @Override
            public void onFailure(Call<HomeViewModel> call, Throwable t) {

            }
        });
    }

    public void setValue(HomeViewModel model){
        if (model!=null) {
            textViewTotalValue.setText(model.getQty());
        }
    }
}