package com.example.riko.classroomapplication.manager;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.riko.classroomapplication.Model.Subject;
import com.example.riko.classroomapplication.R;
import com.example.riko.classroomapplication.TeacherCoursesFragment;
import com.example.riko.classroomapplication.TeacherMenuActivity;
import com.example.riko.classroomapplication.View.BottomSheetSelectMenu;

import java.util.List;

public class SubjectListAdapter extends RecyclerView.Adapter<SubjectListAdapter.SubjectViewHolder> {

    Context mContext;
    List<Subject> subjects;
    Dialog menuDialog;
    BottomSheetSelectMenu bottomSelectMenu;
    View v;


    public SubjectListAdapter(Context mContext, List<Subject> subjects) {
        this.mContext = mContext;
        this.subjects = subjects;
    }

    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        v = LayoutInflater.from(mContext).inflate(R.layout.list_subject_names, parent, false);
        final SubjectViewHolder vHolder = new SubjectViewHolder(v);


        bottomSelectMenu = new BottomSheetSelectMenu();
        vHolder.list_item_subject_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Subject " + String.valueOf(vHolder.textSubject), Toast.LENGTH_SHORT).show();
            }
        });

        // Dialog init
        /*menuDialog = new Dialog(mContext);
        menuDialog.setContentView(R.layout.dialog_menu_subject);
        menuDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        vHolder.list_item_subject_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Subject " + String.valueOf(vHolder.getAdapterPosition()+1), Toast.LENGTH_SHORT).show();
                menuDialog.show();
            }
        });*/



        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectViewHolder holder, int position) {
        holder.textSubjectID.setText(subjects.get(position).getSubjectID());
        holder.textSubject.setText(subjects.get(position).getSubjectname());
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    public static class SubjectViewHolder extends RecyclerView.ViewHolder {

        private CardView list_item_subject_id;
        private TextView textSubjectID;
        private TextView textSubject;

        public SubjectViewHolder(View itemView) {
            super(itemView);
            list_item_subject_id = itemView.findViewById(R.id.list_item_subject_id);
            textSubjectID = itemView.findViewById(R.id.textSubjectId);
            textSubject = itemView.findViewById(R.id.textSubject);
        }
    }
}
