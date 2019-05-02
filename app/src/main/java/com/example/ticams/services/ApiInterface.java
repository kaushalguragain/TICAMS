package com.example.ticams.services;


import com.example.ticams.Dtos.CoursesDto;
import com.example.ticams.Dtos.LoginDto;
import com.example.ticams.Dtos.StudentDto;
import com.example.ticams.Dtos.StudentListDto;
import com.example.ticams.Dtos.SubjectsDto;
import com.example.ticams.Dtos.TeacherDto;
import com.example.ticams.Dtos.TeacherListDto;
import com.example.ticams.Dtos.TeamDto;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    final String preFix = "auth/api/v1/";
    final String preFixForRoutine = "routine/api/v1/";

    @POST(preFix + "login")
    Call<LoginDto> sendUsernameAndPassword(@Body LoginDto loginDto,
                                           @Header("domain") String domain);




    @GET(preFix + "courses")
    Call<List<CoursesDto>> getAllCourse(@Header("customerId") Long customerId,
                                        @Header("token") String token,
                                        @Header("userId") Long userId);

    @GET(preFix + "students")
    Call<StudentListDto> listStudents(@Query("courseId") int courseid,
                                      @Header("customerId") Long customerId,
                                      @Header("token") String token,
                                      @Header("loginId") Long loginId,
                                      @Query("sort") String sort,
                                      @Query("size") int size,
                                      @Query("page") int page,
                                      @Query("search") String search);

    @GET(preFix + "teachers")
    Call<TeacherListDto> listTeachers(@Header("loginId") Long loginId,
                                      @Header("customerId") Long customerId,
                                      @Query("sort") String sort,
                                      @Query("size") int size,
                                      @Query("page") int page,
                                      @Query("search") String search,
                                      @Header("token") String token);

    @GET(preFix + "students/{studentId}")
    Call<StudentDto> studentInfo(@Header("customerId") Long customerId,
                                 @Path("studentId") Long studentId,
                                 @Header("token") String token,
                                 @Header("loginId") Long loginId);


    @GET(preFix + "teachers/{teacherId}")
    Call<TeacherDto> teacherInfo(
            @Header("loginId") Long loginId,
            @Header("customerId") Long customerId,
            @Path("teacherId") Long teacherId,
            @Header("token") String token);


    @GET(preFix + "teams")
    Call<TeamDto> getTeams(@Header("customerId") Long customerId,
                           @Header("token") String token,
                           @Header("loginId") Long loginId,
                           @Query("page") int page,
                           @Query("size") int size,
                           @Query("sort") String sort,
                           @Query("search") String search
    );


    @GET(preFix + "subjects/{courseId}/{semester}")
    Call<List<SubjectsDto>> getListOfSubjects(@Header("customerId") Long customerId,
                                              @Path("courseId") Long courseId,
                                              @Path("semester") String semester,
                                              @Header("token") String token,
                                              @Header("loginId") Long loginId);


}
