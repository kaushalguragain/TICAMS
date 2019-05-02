package com.example.ticams;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ticams.Dtos.CoursesDto;
import com.example.ticams.Dtos.LoginDto;
import com.example.ticams.Dtos.StudentListDto;
import com.example.ticams.TestUser.StudentDtoAdapter;
import com.example.ticams.services.ApiClient;
import com.example.ticams.services.ApiInterface;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentActivity extends AppCompatActivity implements CustomCourseAdapter.ClickListener {
    private RecyclerView courseRecyclerView, studentRecyclerView;
    private ProgressBar progressBar;
    private List<CoursesDto> coursesDtoList = new ArrayList<>();
    StudentListDto studentListDtoResponse;
    private Map<String, Integer> courseNameIdMap = new HashMap<String, Integer>();
    public static Spinner mSemesterSpinner, courseSpinner;
    public static View myView, subjectContainer;
    private TextView tvSubjectName;
    List<StudentListDto.Datum> studentListDto;

    private StudentDtoAdapter studentDtoAdapter;
    private String[] courseid;
    private TextView tvSubjectCode;
    private CustomCourseAdapter customCourseAdapter;
    LinearLayoutManager layoutManager;
    private Integer getCourseId;
    private String getSemester;
    private String[] semester;
    private Integer selectedCourseId;
    private String index1;
    private String getCourseName;
    private List<String> courses = new ArrayList<>();
    private List<String> student = new ArrayList<>();
    private String spinner1;
    private MainActivity mainActivity;
    private ApiInterface apiInterface;
    String courseLevel;
    Long customer;
    String token;
    Long userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        courseSpinner = findViewById(R.id.spnr_class_routine_course);
        courseRecyclerView = findViewById(R.id.recyclerview_courses);
        studentRecyclerView = findViewById(R.id.student_recyclerview1);
        apiInterface = ApiClient.getRetrofit(StudentActivity.this).create(ApiInterface.class);
        mSemesterSpinner = findViewById(R.id.semester_spinner);
        myView = findViewById(R.id.course_view_container);
        subjectContainer = findViewById(R.id.subject_view_container);
        tvSubjectName = findViewById(R.id.label_courses_sub_name);
        tvSubjectCode = findViewById(R.id.label_courses_sub_code);
        layoutManager = new LinearLayoutManager(this);
        studentDtoAdapter = new StudentDtoAdapter(R.layout.user_list_adapter, this);
        courseRecyclerView.setLayoutManager(layoutManager);
        customCourseAdapter = new CustomCourseAdapter(this, R.layout.courses_list_row);
        customCourseAdapter.setNotificationClickListener(this);
        courseRecyclerView.setAdapter(customCourseAdapter);
        studentRecyclerView.setVisibility(View.VISIBLE);
        studentRecyclerView.setLayoutManager(new LinearLayoutManager(StudentActivity.this));
        studentRecyclerView.setAdapter(studentDtoAdapter);
        callServer();
    }

    private void callServer() {

        mainWork();
    }

    private void mainWork() {
//        courseRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Bundle bundle = getIntent().getExtras();
        customer = bundle.getLong("customer");
        userId = bundle.getLong("userid");
        token = bundle.getString("token");

        Call<List<CoursesDto>> call = apiInterface.getAllCourse(customer, token, userId);
        call.enqueue(new Callback<List<CoursesDto>>() {

            @Override
            public void onResponse(Call<List<CoursesDto>> call, Response<List<CoursesDto>> response) {

                if (response.isSuccessful()) {
                    coursesDtoList = response.body();
                    customCourseAdapter.addCourseInList(coursesDtoList);


//                    coursesCustomAdapter=new CoursesCustomAdapter(coursesDtoList,R.layout.courses_list_row,getContext(),courseRecyclerView,subjectRecyclerView,tvSubjectName,tvSubjectCode);
//                    courseRecyclerView.setAdapter(coursesCustomAdapter);


                } else {
                }
            }

            @Override
            public void onFailure(Call<List<CoursesDto>> call, Throwable t) {


            }
        });


    }

    @Override
    public void itemClicked(View v, CoursesDto notificationDto, int position) {
        StudentActivity.myView.setVisibility(View.GONE);
        StudentActivity.subjectContainer.setVisibility(View.VISIBLE);
        getCourseId = coursesDtoList.get(position).getId();
        getCourseName = coursesDtoList.get(position).getName();
//        courseLevel = courseNameIdMap.get(coursesDtoList.);
        courseLevel = coursesDtoList.get(position).getLevel();
        System.out.println("Course Level is:::::" + courseLevel);
        if (courses.contains(getCourseName)) {

            index1 = getCourseName;
        }

        Intent intent = new Intent(StudentActivity.this, StudentListActivity.class);
        intent.putExtra("courseId", getCourseId);
        intent.putExtra("customerId", customer);
        intent.putExtra("token", token);
        intent.putExtra("loginId", userId);
        startActivity(intent);

    }


    @Override
    public void onBackPressed() {
        Intent i = new Intent(this,MainActivity.class);

        startActivity(i);
        super.onBackPressed();
    }
}

