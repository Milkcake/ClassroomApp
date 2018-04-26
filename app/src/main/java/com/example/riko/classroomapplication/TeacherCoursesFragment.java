package com.example.riko.classroomapplication;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.riko.classroomapplication.Model.Subject;
import com.example.riko.classroomapplication.manager.SubjectListAdapter;

import java.util.ArrayList;
import java.util.List;


public class TeacherCoursesFragment extends Fragment {

    private ImageButton searchBtn;
    private TextView textSubjectID, textSubjectname;
    private RecyclerView recyclerViewSubject;
    private List<Subject> subjects;
    private FloatingActionButton fab;
    private EditText searchField;
    private BottomSheetBehavior bottomSheetMenu;


    public TeacherCoursesFragment(){
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_courses1, container, false);

        textSubjectID = view.findViewById(R.id.textSubjectId);
        textSubjectname = view.findViewById(R.id.textSubject);
        searchField = view.findViewById(R.id.search_field);
        searchBtn = view.findViewById(R.id.searchBtn);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Started Search", Toast.LENGTH_SHORT).show();
            }
        });


        //--------------- RecyclerView --------------------//
        recyclerViewSubject = view.findViewById(R.id.recyclerViewSubject);
        SubjectListAdapter subjectListAdapter = new SubjectListAdapter(getContext(), subjects);
        recyclerViewSubject.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewSubject.setAdapter(subjectListAdapter);
        //------------------------------------------------//

        //---------- Fad Button -------------//
        fab = view.findViewById(R.id.fabPlus);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Log.d( "GG", "GG");
                            }
                        }).show();

            }
        });
        // Hide Floating Action Button when scrolling in Recycler View
        recyclerViewSubject.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && fab.getVisibility() == View.VISIBLE) {
                    fab.hide();
                } else if (dy < 0 && fab.getVisibility() != View.VISIBLE) {
                    fab.show();
                }
            }
        });
        //-------------------------------------//



        return view;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Data Array
        subjects = new ArrayList<>();
        subjects.add(new Subject("01236120","Object-Oriented programming"));
        subjects.add(new Subject("01237550","Web Technology"));
        subjects.add(new Subject("01238134","Mobile Technology"));
        subjects.add(new Subject("01238134","Mobile Technology"));
        subjects.add(new Subject("01238134","Mobile Technology"));
        subjects.add(new Subject("01238134","Mobile Technology"));
        subjects.add(new Subject("01238134","Mobile Technology"));
    }
}
