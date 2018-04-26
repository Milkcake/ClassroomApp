package com.example.riko.classroomapplication;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.riko.classroomapplication.Model.Subject;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.List;

public class CoursesFragment extends Fragment {

    private EditText searchField;
    private ImageButton searchBtn;
    private RecyclerView recyclerViewSubject;
    DatabaseReference table_teacherSubject;

    View view;
    Context c;
    private List<Subject> subjectList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_courses, container, false);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        table_teacherSubject = database.getReference("Subject");

        searchField = view.findViewById(R.id.search_field);
        searchBtn = view.findViewById(R.id.searchBtn);
        recyclerViewSubject = view.findViewById(R.id.recyclerViewSubject);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewSubject.setLayoutManager(llm);
        recyclerViewSubject.setHasFixedSize(true);


        // Search
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchText = searchField.getText().toString();
                firebaseSubjectsearch(searchText);
            }
        });

        return view;
    }

    // SearchView
    private void firebaseSubjectsearch(String searchText) {

        Toast.makeText(getActivity(), "Started Search", Toast.LENGTH_LONG).show();
        Query firebaseSearchQuery = table_teacherSubject.orderByChild("Subject").startAt(searchText).startAt(searchText + "\uf8ff");

        FirebaseRecyclerOptions<Subject> options =
                new FirebaseRecyclerOptions.Builder<Subject>()
                        .setQuery(firebaseSearchQuery, Subject.class)
                        .build();

        FirebaseRecyclerAdapter<Subject, SubjectViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Subject, SubjectViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull SubjectViewHolder holder, int position, @NonNull Subject model) {
                        holder.setSubjectID(model.getSubjectID());
                        holder.setSubjectname(model.getSubjectname());
                    }


                    @NonNull
                    @Override
                    public SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.list_subject_names, parent, false);

                        return new SubjectViewHolder(view);
                    }
                };

        recyclerViewSubject.setAdapter(firebaseRecyclerAdapter);
    }

    // View Holder Class
    public class SubjectViewHolder extends RecyclerView.ViewHolder {
        View mView;
        TextView textSubjectId;
        TextView textSubject;

        public SubjectViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            textSubjectId = mView.findViewById(R.id.textSubjectId);
            textSubject = mView.findViewById(R.id.textSubject);
        }

        public void setSubjectID(String subjectID){
            textSubjectId.setText(subjectID);
        }

        public void setSubjectname(String subjectname){
            textSubject.setText(subjectname);
        }
    }
}

