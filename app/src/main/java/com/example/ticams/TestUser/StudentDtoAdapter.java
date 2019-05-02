package com.example.ticams.TestUser;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ticams.Dtos.StudentDto;
import com.example.ticams.Dtos.StudentListDto;
import com.example.ticams.ImageViewLoader;
import com.example.ticams.R;


import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by deepbhai on 9/15/17.
 */

public class StudentDtoAdapter extends RecyclerView.Adapter<StudentDtoAdapter.StudentListHolder> {
    private LayoutInflater inflator;
    private Context context;
    int total1=0;
    private List<StudentListDto.Datum> studentList = new ArrayList<>();
//    private List<StudentList> studentList = new ArrayList<>();

    private int rowLayout;

    public StudentDtoAdapter(int rowLayout,Context context) {
        this.context = context;
        this.rowLayout = rowLayout;
    }

    @Override
    public StudentListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new StudentListHolder(view);
    }

    @Override
    public void onBindViewHolder(StudentListHolder holder, int position) {
       StudentListDto.Datum studenList1  = studentList.get(position);
        holder.fullName.setText((studentList.get(position).getFirstName()) + " " + studentList.get(position).getLastName());
        String picUrl=studentList.get(position).getProfilePicture();
//        String picUrl = "";
        if (studenList1.getFirstName().isEmpty()) {
            holder.checkbox.setChecked(false);
        } else {
        }



        ImageViewLoader.loadImage(context, picUrl, holder.show);
//        holder.fullName.setText(CheckError.checkNullString(studentList.get(position).getFirstName()));

//        String picUrl = "";



        //Decode Base64 to Bitmap and display in circle view
        /*String stringPicture=studentList.get(position).getProfilePicture();
        if(stringPicture!=null) {
            byte[] picCode = Base64.decode(stringPicture, Base64.DEFAULT);
            Bitmap pic = BitmapFactory.decodeByteArray(picCode, 0, picCode.length);
            holder.show.setImageBitmap(pic);
        }*/
    }

    //    public void setFilter(List<StudentList> newList){
//        studentList = new ArrayList<>();
//        studentList.addAll(newList);
//        notifyDataSetChanged();
//    }
    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public void addStudentToList(List<StudentListDto.Datum> newStudentList) {
        studentList.addAll(newStudentList);
        notifyDataSetChanged();

    }
//    void addStudentToList(List<StudentList> newStudentList){
//        studentList.addAll(newStudentList);
//        notifyDataSetChanged();
//
//    }


    public class StudentListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView show;
        TextView fullName;
        CheckBox checkbox;

        private StudentListDto.Datum student;

        public StudentListHolder(View view) {
            super(view);

            fullName = view.findViewById(R.id.fullName);
            checkbox = view.findViewById(R.id.checkBox);
            show = view.findViewById(R.id.list_image_view);
            view.setOnClickListener(this);



            checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        total1= total1+1;
                        getTotal();
                    }
                }



            });

        }

        @Override
        public void onClick(View v) {

        }




        //        @Override
//        public void onClick(View v) {
//            if(clickListener!=null){
//                clickListener.itemClicked(v, studentList.get(getPosition()), getPosition());
//            }
//        }

    }

    public int getTotal() {
        int total = total1;
       
        return total;
    }



}
