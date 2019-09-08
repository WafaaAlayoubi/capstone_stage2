package todoapplication.wafaa.ui.fragments;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import todoapplication.wafaa.R;
import todoapplication.wafaa.models.Note;
import todoapplication.wafaa.ui.activities.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class GridFragment extends Fragment {

    Fragment mFragment;
    ImageView gridPersonal;
    ImageView gridWork;
    ImageView gridMeeting;
    ImageView gridShopping;
    ImageView gridParty;
    ImageView gridStudy;


    public static  List<Note> notesList3 =new ArrayList<>();


    public GridFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewRoot = inflater.inflate(R.layout.fragment_grid, container, false);

        gridPersonal = viewRoot.findViewById(R.id.grid_personal);
        gridWork = viewRoot.findViewById(R.id.grid_work);
        gridMeeting = viewRoot.findViewById(R.id.grid_meeting);
        gridShopping = viewRoot.findViewById(R.id.grid_shopping);
        gridParty = viewRoot.findViewById(R.id.grid_party);
        gridStudy = viewRoot.findViewById(R.id.grid_study);

        gridPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (MainActivity.inGrid){
                    MainActivity.notesList.clear();
                    MainActivity.notesList.addAll(notesList3);
                }
                MainActivity.inGrid =true;
                List<Note> notesList2 = new ArrayList<>();

                notesList3.clear();
                notesList3.addAll(MainActivity.notesList);

                for (Note note:MainActivity.notesList){
                    if (note.getCategory().equals("personal"))
                        notesList2.add(note);
                }

                MainActivity.notesList.clear();
               MainActivity.notesList = notesList2;



                if (notesList2.size() > 0) {
                    mFragment = new HomeFragment();
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.fragment,mFragment);
                    ft.commit();

                } else {
                    mFragment = new EmptyFragment();
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.fragment,mFragment);
                    ft.commit();
                }

            }
        });
        gridWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Note> notesList2 = new ArrayList<>();

                if (MainActivity.inGrid){
                    MainActivity.notesList.clear();
                    MainActivity.notesList.addAll(notesList3);
                }
                MainActivity.inGrid =true;

                notesList3.clear();
                notesList3.addAll(MainActivity.notesList);

                for (Note note:MainActivity.notesList){
                    if (note.getCategory().equals("work"))
                        notesList2.add(note);
                }

                MainActivity.notesList.clear();
               MainActivity.notesList = notesList2;



                if (notesList2.size() > 0) {
                    mFragment = new HomeFragment();
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.fragment,mFragment);
                    ft.commit();

                } else {
                    mFragment = new EmptyFragment();
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.fragment,mFragment);
                    ft.commit();
                }

            }
        });
        gridMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Note> notesList2 = new ArrayList<>();

                if (MainActivity.inGrid){
                    MainActivity.notesList.clear();
                    MainActivity.notesList.addAll(notesList3);
                }
                MainActivity.inGrid =true;

                notesList3.clear();
                notesList3.addAll(MainActivity.notesList);

                for (Note note:MainActivity.notesList){
                    if (note.getCategory().equals("meeting"))
                        notesList2.add(note);
                }

                MainActivity.notesList.clear();
               MainActivity.notesList = notesList2;



                if (notesList2.size() > 0) {
                    mFragment = new HomeFragment();
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.fragment,mFragment);
                    ft.commit();

                } else {
                    mFragment = new EmptyFragment();
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.fragment,mFragment);
                    ft.commit();
                }

            }
        });
        gridShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Note> notesList2 = new ArrayList<>();

                if (MainActivity.inGrid){
                    MainActivity.notesList.clear();
                    MainActivity.notesList.addAll(notesList3);
                }
                MainActivity.inGrid =true;

                notesList3.clear();
                notesList3.addAll(MainActivity.notesList);

                for (Note note:MainActivity.notesList){
                    if (note.getCategory().equals("shopping"))
                        notesList2.add(note);
                }

                MainActivity.notesList.clear();
               MainActivity.notesList = notesList2;



                if (notesList2.size() > 0) {
                    mFragment = new HomeFragment();
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.fragment,mFragment);
                    ft.commit();

                } else {
                    mFragment = new EmptyFragment();
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.fragment,mFragment);
                    ft.commit();
                }

            }
        });
        gridParty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Note> notesList2 = new ArrayList<>();

                if (MainActivity.inGrid){
                    MainActivity.notesList.clear();
                    MainActivity.notesList.addAll(notesList3);
                }
                MainActivity.inGrid =true;

                notesList3.clear();
                notesList3.addAll(MainActivity.notesList);

                for (Note note:MainActivity.notesList){
                    if (note.getCategory().equals("party"))
                        notesList2.add(note);
                }

                MainActivity.notesList.clear();
               MainActivity.notesList = notesList2;



                if (notesList2.size() > 0) {
                    mFragment = new HomeFragment();
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.fragment,mFragment);
                    ft.commit();

                } else {
                    mFragment = new EmptyFragment();
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.fragment,mFragment);
                    ft.commit();
                }

            }
        });
        gridStudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Note> notesList2 = new ArrayList<>();

                if (MainActivity.inGrid){
                    MainActivity.notesList.clear();
                    MainActivity.notesList.addAll(notesList3);
                }
                MainActivity.inGrid =true;

                notesList3.clear();
                notesList3.addAll(MainActivity.notesList);

                for (Note note:MainActivity.notesList){
                    if (note.getCategory().equals("study"))
                        notesList2.add(note);
                }

                MainActivity.notesList.clear();
               MainActivity.notesList = notesList2;



                if (notesList2.size() > 0) {
                    mFragment = new HomeFragment();
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.fragment,mFragment);
                    ft.commit();

                } else {
                    mFragment = new EmptyFragment();
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.fragment,mFragment);
                    ft.commit();
                }

            }
        });

        return viewRoot;
    }



}
