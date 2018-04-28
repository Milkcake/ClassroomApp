package com.example.riko.classroomapplication;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
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
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class TeacherCoursesFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getName();

    private ImageButton searchBtn;
    private TextView textSubjectID, textSubjectname;
    private RecyclerView recyclerViewSubject;
    private List<Subject> subjects;
    private FloatingActionButton fab;
    private EditText searchField;
    private BottomSheetDialog bottomSheetMenu;
    private SubjectListAdapter subjectListAdapter;
    private View view, sheetView;


    public TeacherCoursesFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_courses1, container, false);

        initInstance();
        searchBtn.setOnClickListener(this);
        fab.setOnClickListener(this);
        recyclerViewSubjectList();
        bottomSheetSelectMenu();
        fabButtomAddSubject();

        return view;
    }


    private void initInstance() {
        //-------------------- Search --------------------------//
        textSubjectID = view.findViewById(R.id.textSubjectId);
        textSubjectname = view.findViewById(R.id.textSubject);
        searchField = view.findViewById(R.id.search_field);
        searchBtn = view.findViewById(R.id.searchBtn);

        //---------- Fad Button -------------//
        fab = view.findViewById(R.id.fabPlus);

        //--------------- RecyclerView --------------------//
        recyclerViewSubject = view.findViewById(R.id.recyclerViewSubject);
    }


    //RecyclerView: subject
    private void recyclerViewSubjectList() {
        //<----------------- RecyclerView: subject ---------------------->
        subjectListAdapter = new SubjectListAdapter(getContext(), subjects, new SubjectListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Subject subject) {
                displaySelectMenu();
            }
        });
        recyclerViewSubject.setHasFixedSize(true);
        recyclerViewSubject.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewSubject.setAdapter(subjectListAdapter);
        subjects = new ArrayList<>();
        //<-----------------------------------------------------------//>

    }



    // Bottom sheet dialog: Select menu
    private void bottomSheetSelectMenu() {

        bottomSheetMenu = new BottomSheetDialog(getActivity());
        sheetView = getActivity().getLayoutInflater().inflate(R.layout.bottom_sheet_menu, null);
        bottomSheetMenu.setContentView(sheetView);
    }
    private void displaySelectMenu() {
        Toast.makeText(getContext(), "Subect Clicked", Toast.LENGTH_SHORT).show();
        bottomSheetMenu.show();
    }



    //Flot action button: Add subject
    private void fabButtomAddSubject() {
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
        //<-----------------------------------------------------------//>
    }




    @Override
    public void onClick(View v) {
        if (v == searchBtn) {
            Toast.makeText(getActivity(), "Started Search", Toast.LENGTH_SHORT).show();
        } else if (v == fab) {
            Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG).setAction("Action", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("GG", "GG");
                }
            }).show();
        }
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Data Array
        subjects = new ArrayList<>();
        subjects.add(new Subject("01236120", "Object-Oriented programming"));
        subjects.add(new Subject("01237550", "Web Technology"));
        subjects.add(new Subject("01238134", "Mobile Technology"));
        subjects.add(new Subject("01238134", "Mobile Technology"));
        subjects.add(new Subject("01238134", "Mobile Technology"));
        subjects.add(new Subject("01238134", "Mobile Technology"));
        subjects.add(new Subject("01238134", "Mobile Technology"));
    }
}
