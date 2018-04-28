package com.example.riko.classroomapplication.manager;


import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.riko.classroomapplication.Model.Subject;
import com.example.riko.classroomapplication.R;

import java.util.List;

public class SubjectListAdapter extends RecyclerView.Adapter<SubjectListAdapter.SubjectViewHolder> {

    public interface OnItemClickListener{
        void onItemClick(Subject subject);
    }

    private Context mContext;
    private final List<Subject> subjects;
    private View v;
    private final OnItemClickListener listener;


    public SubjectListAdapter(Context mContext, List<Subject> subjects, OnItemClickListener listener) {
        this.mContext = mContext;
        this.subjects = subjects;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        v = LayoutInflater.from(mContext).inflate(R.layout.list_subject_names, parent, false);
        final SubjectViewHolder vHolder = new SubjectViewHolder(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectViewHolder holder, int position) {
        /*holder.textSubjectID.setText(subjects.get(position).getSubjectID());
        holder.textSubject.setText(subjects.get(position).getSubjectname());*/
        holder.bind(subjects.get(position), listener);
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

        public void bind(final Subject subject, final OnItemClickListener listener) {
            textSubjectID.setText(subject.getSubjectID());
            textSubject.setText(subject.getSubjectname());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(subject);
                }
            });
        }
    }
}
