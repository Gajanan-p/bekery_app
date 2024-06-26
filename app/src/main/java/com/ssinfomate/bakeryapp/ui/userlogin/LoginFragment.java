package com.ssinfomate.bakeryapp.ui.userlogin;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ssinfomate.bakeryapp.MainActivity;
import com.ssinfomate.bakeryapp.R;
import com.ssinfomate.bakeryapp.utils.AppPreference;
import com.ssinfomate.bakeryapp.webservices.Webservice;
import com.ssinfomate.bakeryapp.webservices.user.RequestUser;
import com.ssinfomate.bakeryapp.webservices.user.TempChangePass;
import com.ssinfomate.bakeryapp.webservices.user.UserModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment implements
        View.OnClickListener {

    private LoginViewModel mViewModel;
    UserModel userModel;
    NavController navController;
    Button buttonLogin;
    TextInputEditText editTextUserName;
    TextInputEditText editTextPassword;
    TextView textViewChangePassword;
    TextView textViewForgetPassword;
    ArrayList<TempChangePass> tempTestChangePass = new ArrayList<>() ;
    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        editTextUserName = view.findViewById(R.id.inputUsername);
        editTextPassword = view.findViewById(R.id.inputPassword);
        buttonLogin = view.findViewById(R.id.button_login);
        buttonLogin.setOnClickListener(this);
        tempTestChangePass = new ArrayList<>();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        // TODO: Use the ViewModel
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
    }


    public void checkCredentialWithServer(){
       // progressDialog = createProgressDialog(getContext());
        if(!TextUtils.isEmpty(editTextUserName.getText())
                && !TextUtils.isEmpty(editTextPassword.getText())){
            RequestUser user=new RequestUser(editTextUserName.getText().toString(),editTextPassword.getText().toString());
           // String BASE_URL=appSetting.getSettingServerURL();
            Call<ArrayList<UserModel>> listUser= Webservice
                    .getUserData()
                    .getUserModel(user);
            listUser.enqueue(new Callback<ArrayList<UserModel>>() {
                @Override
                public void onResponse(Call<ArrayList<UserModel>> call, Response<ArrayList<UserModel>> response) {
                    ArrayList<UserModel> userModels=response.body();
//                    SharedPreferences.Editor editor=AppPreference.sharedPreferences.edit();
                    if(userModels.get(0).getAcmasterid()!=null){
                        navController.navigate(R.id.action_nav_login_to_bottom_home);
                        UserModel userModel=userModels.get(0);
                        AppPreference.setLoginDataPreferences(getContext(),userModel);
                        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                        navController.navigate(R.id.bottom_home);
                        Intent intent=new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                        if (!TextUtils.isEmpty(editTextUserName.getText())&&(!TextUtils.isEmpty(editTextPassword.getText()))) {
                         }else {Toast.makeText(getContext(),"No Login Data Here.....",Toast.LENGTH_SHORT).show();}
                        Log.i("",response.body().get(0).getMsg().toString());
                        Toast.makeText(getContext(),response.body().get(0).getMsg().toString(),Toast.LENGTH_LONG).show();
                    }else{
                        if (userModels.get(0).getStatus()!=null){
                            navController.navigate(R.id.action_nav_login_to_nav_changePassword);
                        }
                        Toast.makeText(getContext(),response.body().get(0).getMsg().toString(),Toast.LENGTH_LONG).show();

                    }

                }

                @Override
                public void onFailure(Call<ArrayList<UserModel>> call, Throwable t) {
                    Log.e("Login",t.getStackTrace().toString());
                   // progressDialog.dismiss();
                }
            });

        }
    }

    public void tempStoreLoginDetail(){
        TempChangePass tempChangePass = new TempChangePass();
        tempChangePass.setUsername(editTextUserName.getText().toString());
        tempChangePass.setPassword(editTextPassword.getText().toString());
        tempTestChangePass.add(tempChangePass);
        AppPreference.setTempDataPreferences(getContext(),tempTestChangePass);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_login: {
               checkCredentialWithServer();
                tempStoreLoginDetail();
                break;
            }
        }
    }

}