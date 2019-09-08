package todoapplication.wafaa.ui.fragments;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;

import androidx.appcompat.app.AlertDialog;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import todoapplication.wafaa.R;
import todoapplication.wafaa.adapters.NotesAdapter;
import todoapplication.wafaa.database.AppExecutor;
import todoapplication.wafaa.models.Note;
import todoapplication.wafaa.rv.MyDividerItemDecoration;
import todoapplication.wafaa.rv.RecyclerTouchListener;
import todoapplication.wafaa.ui.activities.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    public static NotesAdapter mAdapter;

    ImageView personalon;
    ImageView workon;
    ImageView meetingon;
    ImageView shoppingon;
    ImageView partyon;
    ImageView studyon;
    ImageView dialog_title;

    String category = "personal" ;

    boolean timeTrue ;



    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View viewRoot = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = viewRoot.findViewById(R.id.recycler_view);




        mAdapter = new NotesAdapter(this.getActivity(), MainActivity.notesList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this.getActivity(), LinearLayoutManager.VERTICAL, 16));
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(),
                recyclerView, new RecyclerTouchListener.ClickListener() {

            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {

                if(!MainActivity.inGrid)
                showActionsDialog(position);

            }
        }));


        return viewRoot;

    }
    private void showActionsDialog(final int position) {
        CharSequence colors[] = new CharSequence[]{"Edit", "Delete"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose option");
        builder.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    showNoteDialog( MainActivity.notesList.get(position), position);
                } else {
                   AppExecutor.getInstance().diskIO().execute(new Runnable() {
                       @Override
                       public void run() {
                           List<Note> notes1 = mAdapter.getNotesList();
                           MainActivity.mDb.noteDao().deleteNote(notes1.get(position));
                           MainActivity.notesList.clear();
                           MainActivity.notesList.addAll(notes1);
                       }
                   });
                }
            }
        });
        builder.show();
    }


    public  void showNoteDialog( final Note note, final int position) {
        final ImageView btnTimePicker2;
        final TextView txtDate,txtDate2, txtTime,txtTime2;

        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getActivity().getApplicationContext());
        View view = layoutInflaterAndroid.inflate(R.layout.note_dialog, null);

        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(getActivity());
        alertDialogBuilderUserInput.setView(view);

        final EditText inputNote = view.findViewById(R.id.note);



        personalon = (ImageView) view.findViewById(R.id.personal);
        workon = (ImageView) view.findViewById(R.id.work);
        meetingon = (ImageView) view.findViewById(R.id.meeting);
        shoppingon = (ImageView) view.findViewById(R.id.shopping);
        partyon = (ImageView) view.findViewById(R.id.party);
        studyon = (ImageView) view.findViewById(R.id.study);

        personalon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personalon.setImageResource(R.drawable.personalon);
                workon.setImageResource(R.drawable.work);
                meetingon.setImageResource(R.drawable.meeting);
                shoppingon.setImageResource(R.drawable.shopping);
                partyon.setImageResource(R.drawable.party);
                studyon.setImageResource(R.drawable.study);
                category = "personal";
            }
        });

        workon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workon.setImageResource(R.drawable.workon);
                personalon.setImageResource(R.drawable.personal);
                meetingon.setImageResource(R.drawable.meeting);
                shoppingon.setImageResource(R.drawable.shopping);
                partyon.setImageResource(R.drawable.party);
                studyon.setImageResource(R.drawable.study);
                category = "work";
            }
        });

        meetingon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meetingon.setImageResource(R.drawable.meetingon);
                workon.setImageResource(R.drawable.work);
                personalon.setImageResource(R.drawable.personal);
                shoppingon.setImageResource(R.drawable.shopping);
                partyon.setImageResource(R.drawable.party);
                studyon.setImageResource(R.drawable.study);
                category = "meeting";
            }
        });

        shoppingon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoppingon.setImageResource(R.drawable.shoppingon);
                workon.setImageResource(R.drawable.work);
                meetingon.setImageResource(R.drawable.meeting);
                personalon.setImageResource(R.drawable.personal);
                partyon.setImageResource(R.drawable.party);
                studyon.setImageResource(R.drawable.study);
                category = "shopping";
            }
        });

        partyon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                partyon.setImageResource(R.drawable.partyon);
                workon.setImageResource(R.drawable.work);
                meetingon.setImageResource(R.drawable.meeting);
                shoppingon.setImageResource(R.drawable.shopping);
                personalon.setImageResource(R.drawable.personal);
                studyon.setImageResource(R.drawable.study);
                category = "party";
            }
        });

        studyon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studyon.setImageResource(R.drawable.studyon);
                workon.setImageResource(R.drawable.work);
                meetingon.setImageResource(R.drawable.meeting);
                shoppingon.setImageResource(R.drawable.shopping);
                partyon.setImageResource(R.drawable.party);
                personalon.setImageResource(R.drawable.personal);
                category = "study";
            }
        });


        txtDate=view.findViewById(R.id.in_date);
        txtTime=view.findViewById(R.id.in_time);
        txtTime2=view.findViewById(R.id.in_time3);
        txtDate2=view.findViewById(R.id.in_dateend);

        dialog_title = (ImageView) view.findViewById(R.id.dialog_title);
        dialog_title.setImageResource(R.drawable.updatetask);

        inputNote.setText(note.getNote());
        txtDate.setText(note.getDateStart());
        txtDate2.setText(note.getDateEnd());
        txtTime.setText(note.getTimestart());
        txtTime2.setText(note.getTimeend());

        if(note.getCategory().equals("personal")){
            personalon.setImageResource(R.drawable.personalon);
            category = "personal";
        }
        if(note.getCategory().equals("work")){
            workon.setImageResource(R.drawable.workon);
            personalon.setImageResource(R.drawable.personal);
            category = "work";
        }
        if(note.getCategory().equals("meeting")){
            meetingon.setImageResource(R.drawable.meetingon);
            personalon.setImageResource(R.drawable.personal);
            category = "meeting";
        }
        if(note.getCategory().equals("shopping")){
            shoppingon.setImageResource(R.drawable.shoppingon);
            personalon.setImageResource(R.drawable.personal);
            category = "shopping";
        }
        if(note.getCategory().equals("party")){
            partyon.setImageResource(R.drawable.partyon);
            personalon.setImageResource(R.drawable.personal);
            category = "party";
        }
        if(note.getCategory().equals("study")){
            studyon.setImageResource(R.drawable.studyon);
            personalon.setImageResource(R.drawable.personal);
            category = "study";
        }



        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int mYear, mMonth, mDay, mHour, mMinute;
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                txtTime2.setText("Time End");
                txtDate2.setText("Date End");


                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();


            }
        });
        txtDate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int mYear, mMonth, mDay, mHour, mMinute;
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                try {

                                    String sDate1= txtDate.getText().toString();
                                    Date date1=new SimpleDateFormat("dd-MM-yyyy").parse(sDate1);
                                    String sDate2= dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                                    Date date2=new SimpleDateFormat("dd-MM-yyyy").parse(sDate2);

                                    if(date1.compareTo(date2) > 0){
                                        Toast.makeText(getActivity(), "Date1 is after Date2", Toast.LENGTH_LONG).show();
                                    }
                                    else if (date1.compareTo(date2) == 0){
                                        timeTrue = false;
                                        txtDate2.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                    }
                                    else {
                                        timeTrue = true;
                                        txtDate2.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                    }

                                } catch (ParseException e) {              // Insert this block.
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();


            }
        });
        txtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int mYear, mMonth, mDay, mHour, mMinute;
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);
                txtTime2.setText("Time End");
                txtDate2.setText("Date End");

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {


                                txtTime.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });
        txtTime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int mYear, mMonth, mDay, mHour, mMinute;
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                if (timeTrue) {
                                    txtTime2.setText(hourOfDay + ":" + minute);
                                }
                                else {
                                    String string1 = txtTime.getText().toString();
                                    String[] parts1 = string1.split(":");
                                    int hour = Integer.parseInt(parts1[0]);
                                    int minute1 = Integer.parseInt(parts1[1]);
                                    if(hour > hourOfDay){
                                        Toast.makeText(getActivity(), "Time end befor Time start!", Toast.LENGTH_LONG).show();

                                    }else if (hour == hourOfDay){
                                        if(minute1 < minute){
                                            timeTrue = true;
                                            txtTime2.setText(hourOfDay + ":" + minute);
                                        }
                                        else{
                                            Toast.makeText(getActivity(), "Time end should be after Time start!", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                    else{
                                        timeTrue = true;
                                        txtTime2.setText(hourOfDay + ":" + minute);
                                    }

                                }
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();






            }
        });



        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton("update", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {

                    }
                })
                .setNegativeButton("cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        });

        final AlertDialog alertDialog = alertDialogBuilderUserInput.create();
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show toast message when no text is entered
                if (TextUtils.isEmpty(inputNote.getText().toString())) {
                    Toast.makeText(getActivity(), "Enter note!", Toast.LENGTH_SHORT).show();
                    return;
                } else if (txtDate.getText().equals("Date Start")) {
                    Toast.makeText(getActivity(), "Enter date!", Toast.LENGTH_SHORT).show();
                    return;
                }else if (txtDate2.getText().equals("Date End")) {
                    Toast.makeText(getActivity(), "Enter date!", Toast.LENGTH_SHORT).show();
                    return;
                }  else if (txtTime.getText().equals("Time Start")) {
                    Toast.makeText(getActivity(), "Enter start time!", Toast.LENGTH_SHORT).show();
                    return;
                }  else if (txtTime2.getText().equals("Time End")) {
                    Toast.makeText(getActivity(), "Enter end time!", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    alertDialog.dismiss();
                }


                note.setNote( inputNote.getText().toString());
                note.setCategory(category);
                note.setAlert("0");
                note.setFinish("0");
                note.setDateStart(txtDate.getText().toString());
                note.setTimestart(txtTime.getText().toString());
                note.setTimeend(txtTime2.getText().toString());
                note.setDateEnd(txtDate2.getText().toString());


                AppExecutor.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        MainActivity.mDb.noteDao().updateNote(note);
                    }
                });
                onResume();
            }

        });




    }
    @Override
    public void onResume() {
        super.onResume();

        mAdapter.setNotesList(MainActivity.notesList);

    }


}
