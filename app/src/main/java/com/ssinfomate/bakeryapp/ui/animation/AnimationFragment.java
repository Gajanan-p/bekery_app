package com.ssinfomate.bakeryapp.ui.animation;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.ssinfomate.bakeryapp.R;
import com.ssinfomate.bakeryapp.utils.AppPreference;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;


public class AnimationFragment extends Fragment {

    private AnimationViewModel mViewModel;
    BottomNavigationView bottomNavView;
    NavController navController;
    NavigationView navigationView;
    ImageView imageViewTop,imageViewBottom,imageViewBeat,imageViewHeart;
    TextView textViewName;
    CharSequence charSequence;
    int index;
    long delay=200;
    Handler handler= new Handler();
    public static AnimationFragment newInstance() {
        return new AnimationFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.animation_fragment, container, false);

        imageViewTop=view.findViewById(R.id.iv_top);
        imageViewBottom = view.findViewById(R.id.iv_bottom);
        imageViewBeat = view.findViewById(R.id.iv_beat);
        imageViewHeart = view.findViewById(R.id.iv_heart);
        textViewName = view.findViewById(R.id.iv_name);
        navigationView = view.findViewById(R.id.nav_view);
        bottomNavView = view.findViewById(R.id.bottom_nav_view);
        // initialize top animation
        Animation animation1= AnimationUtils.loadAnimation(getContext(),
                R.anim.sp_top_wave);
        //set top animation
        imageViewTop.setAnimation(animation1);

        // initialize object animator
        ObjectAnimator objectAnimator=ObjectAnimator.ofPropertyValuesHolder(
                imageViewHeart,
                PropertyValuesHolder.ofFloat("scaleX",1.2f),
                PropertyValuesHolder.ofFloat("scaleY",1.2f)
        );

        //set Duration
        objectAnimator.setDuration(500);
        //set Repeat Count
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        // set Repeat mode
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);

        // start Animator
        objectAnimator.start();

        // set Animator text
        animateText("ARKAM FOODS");

        // initialize bottom animation
        Animation animation2= AnimationUtils.loadAnimation(getContext(),
                R.anim.sp_bottom_wave);
        //set top animation
        imageViewBottom.setAnimation(animation2);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                startActivity(
////                        new Intent(AnimationFragment.newInstance().getActivity(),HomeFragment.class)
////                                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//
//
//                );
//                getActivity().finish();
              //  navController.navigate(R.id.action_nav_animation_to_nav_login);
            }
        },3000);

        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AnimationViewModel.class);
        // TODO: Use the ViewModel
    }
    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            // when Runnable is run set text
            textViewName.setText(charSequence.subSequence(0,index++));
            // check the condition
            if(index<=charSequence.length()){
                handler.postDelayed(runnable,delay);
            }
        }
    };

    // create text Animator method
    public void animateText(CharSequence charSequence){
        //set Text
        this.charSequence=charSequence;
        //clear Index
        index=0;
        //clear text View
        textViewName.setText("");
        //Remove Callback
        handler.removeCallbacks(runnable);
        // run handler
        handler.postDelayed(runnable,delay);


    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public void onResume() {
        super.onResume();
        AppPreference.clearLoginDataPreferences(getContext());
    }

    private void hideMenuItem()
    {
        Menu nav_Menu = bottomNavView.getMenu();
        nav_Menu.findItem(R.id.bottom_saleReturn).setVisible(false);
        nav_Menu.findItem(R.id.bottom_home).setVisible(false);
        nav_Menu.findItem(R.id.bottom_returnReport).setVisible(false);
    }
}