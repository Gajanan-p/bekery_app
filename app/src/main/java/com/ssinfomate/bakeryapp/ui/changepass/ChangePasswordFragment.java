package com.ssinfomate.bakeryapp.ui.changepass;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ssinfomate.bakeryapp.R;
import com.ssinfomate.bakeryapp.utils.AppPreference;
import com.ssinfomate.bakeryapp.webservices.Webservice;
import com.ssinfomate.bakeryapp.webservices.user.TempChangePass;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordFragment extends Fragment implements View.OnClickListener {

    TextView editTextUserName;
    EditText editTextOldPassword;
    EditText editTextNewPassword;
    Button buttonSubmit;
    NavController navController;
    List<TempChangePass> tempChangePass;
    private ArrayList<ChangePasswordModel>changePasswordModels;
    public static ChangePasswordFragment newInstance() {
        return new ChangePasswordFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.change_password_fragment, container, false);

        editTextUserName  = view.findViewById(R.id.edit_user_name);
        editTextOldPassword = view.findViewById(R.id.edit_old_password);
        editTextNewPassword = view.findViewById(R.id.edit_new_password);
        buttonSubmit= view.findViewById(R.id.button_submit);
        buttonSubmit.setOnClickListener(this);
        tempChangePass = AppPreference.getTempDataPreferences(getContext());
        editTextUserName.setText(tempChangePass.get(0).getUsername());
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
    }

    @Override
    public void onClick(View v) {
        if (editTextOldPassword.getText().toString().equals(editTextNewPassword.getText().toString())) {
            getLoginData();
        }else {
            Toast.makeText(getContext(),"Password is not match! Try again...",Toast.LENGTH_LONG).show();
            editTextOldPassword.setText("");
            editTextNewPassword.setText("");
        }
    }

    public void getLoginData(){
        ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest();
        changePasswordRequest.setAcntcode(editTextUserName.getText().toString());
        changePasswordRequest.setAcntpass(editTextNewPassword.getText().toString());
        changePasswordRequest.setNewACNTPASS(editTextNewPassword.getText().toString());

        Call<ArrayList<ChangePasswordModel>>arrayListCall = Webservice
                .getUserData()
                .getChangePassword(changePasswordRequest);

        arrayListCall.enqueue(new Callback<ArrayList<ChangePasswordModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ChangePasswordModel>> call, Response<ArrayList<ChangePasswordModel>> response) {
                if (response.isSuccessful()){
                    Log.i("",response.body().get(0).getMsg());
                    Toast.makeText(getContext(),response.body().get(0).getMsg(),Toast.LENGTH_SHORT).show();
                    editTextUserName.setText("");
                    editTextOldPassword.setText("");
                    navController.navigate(R.id.action_nav_changePassword_to_nav_login);
                }else {
                    Toast.makeText(getContext(),response.body().get(0).getMsg(),Toast.LENGTH_SHORT).show();
                    editTextUserName.setText("");
                    editTextOldPassword.setText("");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ChangePasswordModel>> call, Throwable t) {
                Log.e("data error", t.getMessage());
                Toast.makeText(getContext(), "Password Not Saved Successfully", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void concatString(){

    }
}