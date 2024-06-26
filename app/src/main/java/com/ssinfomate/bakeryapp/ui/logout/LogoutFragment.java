package com.ssinfomate.bakeryapp.ui.logout;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ssinfomate.bakeryapp.MainActivity;
import com.ssinfomate.bakeryapp.R;
import com.ssinfomate.bakeryapp.utils.AppPreference;

public class LogoutFragment extends Fragment {

    private LogoutViewModel mViewModel;

    public static LogoutFragment newInstance() {
        return new LogoutFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view  =  inflater.inflate(R.layout.logout_fragment, container, false);
        final TextView textView = view.findViewById(R.id.text_logout);
        AppPreference.clearLoginDataPreferences(getContext());

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LogoutViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onResume() {
        super.onResume();
        Intent intent=new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}