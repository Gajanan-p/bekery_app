package com.ssinfomate.bakeryapp.ui.contactus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.ssinfomate.bakeryapp.R;

public class ContactUsFragment extends Fragment {

    private ContactUsViewModel mViewModel;

    public static ContactUsFragment newInstance() {
        return new ContactUsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contact_us_fragment, container, false);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ContactUsViewModel.class);
        // TODO: Use the ViewModel
    }

}