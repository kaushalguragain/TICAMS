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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.namespace.NamespaceContext;

public class MainFragment extends Fragment {
    private ImageView studentView,teacherView;
    private TextView studenttext, teachertext, dateText;
    private NavigationView navigationView;
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

        studentView.setOnClickListener(v -> {
            Intent i = new Intent(getActivity(),StudentActivity.class);
            startActivity(i);
        });
        return view;
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
        teachertext = view.findViewById(R.id.textView12);
        studentView = view.findViewById(R.id.students);
        teacherView = view.findViewById(R.id.teachers);


    }





}
