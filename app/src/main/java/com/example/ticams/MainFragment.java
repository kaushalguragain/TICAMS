package com.example.ticams;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ticams.Dtos.LoginDto;
import com.example.ticams.services.ApiClient;
import com.example.ticams.services.ApiInterface;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.namespace.NamespaceContext;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment {
    private ImageView studentView,teacherView;
    private TextView studenttext, teachertext, dateText;
    private NavigationView navigationView;
    private Long userId, customerId;
    private String token;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);
        SupportActionBarInitializer.setSupportActionBarTitle( ((AppCompatActivity) getActivity()).getSupportActionBar(),"Dashboard");
        bindViews(view);

        navigationView = getActivity().findViewById(R.id.nav_view);
        addingDate();
        login();


        return view;


    }
    private void login() {
        LoginDto loginDto = new LoginDto("surya","surya","string","string");
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<LoginDto> call= apiInterface.sendUsernameAndPassword(loginDto,"texasintl.edu.np");
        call.enqueue(new Callback<LoginDto>() {
            @Override
            public void onResponse(Call<LoginDto> call, Response<LoginDto> response) {
                if(response.isSuccessful()){
                    customerId = response.body().getUser().getCustomerId();
                    token = response.body().getUser().getToken();
                    userId = response.body().getUser().getLoginId();


                    studentView.setOnClickListener(v -> {
                        Intent i = new Intent(getActivity(),StudentActivity.class);
                        i.putExtra("customer",customerId);
                        i.putExtra("token",token);
                        i.putExtra("userid",userId);
                        startActivity(i);
                    });



                }
            }

            @Override
            public void onFailure(Call<LoginDto> call, Throwable t) {

            }
        });
    }


    public void addingDate(){
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);
        dateText.setText(formattedDate);
    }

    private void bindViews(View view) {
        dateText = view.findViewById(R.id.textView);
        studenttext = view.findViewById(R.id.textView11);

        studentView = view.findViewById(R.id.students);



    }





}
