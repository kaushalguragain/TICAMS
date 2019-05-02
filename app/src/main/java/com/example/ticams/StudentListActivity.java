package com.example.ticams;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ticams.Dtos.StudentListDto;
import com.example.ticams.TestUser.StudentDtoAdapter;
import com.example.ticams.services.ApiClient;
import com.example.ticams.services.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private StudentDtoAdapter studentDtoAdapter;
    private ApiInterface apiInterface;
    private Integer courseId;
    private Long customerId,loginId;
    private String token;
    private List<StudentListDto.Datum> studentListDto;
    private StudentListDto studentListDtoResponse;
    private Button checkin;
    public String total,present;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        recyclerView=findViewById(R.id.recyclerview_student);
        checkin = findViewById(R.id.button);
        studentDtoAdapter=new StudentDtoAdapter(R.layout.user_list_adapter,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(studentDtoAdapter);
        apiInterface= ApiClient.getRetrofit(this).create(ApiInterface.class);
        getIntentValue();

        getStudents();
        getbutton();

    }

    private void getbutton() {




    }

    private void getIntentValue() {
        Intent data=getIntent();
        courseId=data.getExtras().getInt("courseId");
        customerId=data.getExtras().getLong("customerId");
        loginId=data.getExtras().getLong("loginId");
        token=data.getExtras().getString("token");
    }

    public void getStudents() {

        Call<StudentListDto> call = apiInterface.listStudents(courseId, customerId, token, loginId, "firstName,asc", 20, 0, "");
        call.enqueue(new Callback<StudentListDto>() {
            @Override
            public void onResponse(Call<StudentListDto> call, Response<StudentListDto> response) {

                if (response.isSuccessful()) {

                    String students;

                    studentListDto = response.body().getData();

                    if (response.code() == 200) {

                        studentListDtoResponse = response.body();
                        System.out.println("this is the data:::" + response.body().toString());
                        System.out.println("List of data is:::" + response.body().getData());
//
                        List<StudentListDto.Datum> datumList = studentListDtoResponse.getData();
                        Log.d("resonse::", String.valueOf(datumList.size()));
                        studentDtoAdapter.addStudentToList(datumList);

                        checkin.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent intent = new Intent(StudentListActivity.this, ResultDisplay.class);
                                   present = String.valueOf(studentDtoAdapter.getTotal());
                                   total   = String.valueOf(studentDtoAdapter.getItemCount());
                                   intent.putExtra("present",present);
                                   intent.putExtra("total",total);

                                startActivity(intent);

                            }
                        });



                    } else {
                    }
                }



            }



            @Override
            public void onFailure(Call<StudentListDto> call, Throwable t) {

            }
        });


    }

    @Override
    public void onBackPressed() {

        Intent i = new Intent(this,StudentActivity.class);
        i.putExtra("customer",customerId);
        i.putExtra("token",token);
        i.putExtra("userid",loginId);
        startActivity(i);
        super.onBackPressed();
    }



}
