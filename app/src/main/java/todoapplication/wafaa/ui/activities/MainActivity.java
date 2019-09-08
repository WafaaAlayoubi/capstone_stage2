package todoapplication.wafaa.ui.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import todoapplication.wafaa.R;
import todoapplication.wafaa.database.AppDatabase;
import todoapplication.wafaa.database.AppExecutor;
import todoapplication.wafaa.models.MainViewModel;
import todoapplication.wafaa.models.Note;
import todoapplication.wafaa.notification.AlertReceiver;
import todoapplication.wafaa.ui.fragments.EmptyFragment;
import todoapplication.wafaa.ui.fragments.GridFragment;
import todoapplication.wafaa.ui.fragments.HomeFragment;

import static androidx.core.content.ContextCompat.getSystemService;

public class MainActivity extends AppCompatActivity {

    private ImageView fab;

    public static Integer notesCount;

    ImageView personalon;
    ImageView workon;
    ImageView meetingon;
    ImageView shoppingon;
    ImageView partyon;
    ImageView studyon;

    ImageView imgHome;
    ImageView imgGrid;


    String category = "personal" ;



    boolean timeTrue ;
    public static boolean isTrue  = false;
    public static boolean inGrid;

    public static AppDatabase mDb;

    Fragment mFragment;

    public static LiveData<List<Note>> notesList1 ;
    public static List<Note> notesList = new ArrayList<>();
    public static List<Note> notesListCopy = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mDb = AppDatabase.getInstance(getApplicationContext());
        onRetrieve();

        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        imgHome = (ImageView) findViewById(R.id.home);
        imgGrid = (ImageView) findViewById(R.id.grid);

        imgHome.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                inGrid = false;
                timeTrue = true;
                imgGrid.setImageResource(R.drawable.grid);
                imgHome.setImageResource(R.drawable.home);

                onRetrieve();

                if (!(GridFragment.notesList3.size() == 0)) {
                    HomeFragment.mAdapter.setNotesList(GridFragment.notesList3);
                    notesList.clear();
                    notesList.addAll(GridFragment.notesList3);
                    notesCount = notesList.size();
                }




                if (notesCount > 0) {
                    mFragment = new HomeFragment();
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.fragment,mFragment);
                    ft.commit();
                } else if(notesCount == 0) {
                    mFragment = new EmptyFragment();
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.fragment,mFragment);
                    ft.commit();
                }


            }
        });
        imgGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imgHome.setImageResource(R.drawable.home2);
                imgGrid.setImageResource(R.drawable.grid2);
                mFragment = new GridFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment,mFragment);
                ft.commit();
            }
        });


        fab = (ImageView) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNoteDialog( null, -1);

            }
        });
    }

    public  void showNoteDialog( final Note note, final int position) {
        final ImageView  btnTimePicker2;
        final TextView txtDate,txtDate2, txtTime,txtTime2;

        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getApplicationContext());
        View view = layoutInflaterAndroid.inflate(R.layout.note_dialog, null);

        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(MainActivity.this);
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


                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
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


                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
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
                                        Toast.makeText(MainActivity.this, "Date1 is after Date2", Toast.LENGTH_LONG).show();
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
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
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
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
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
                                        Toast.makeText(MainActivity.this, "Time end befor Time start!", Toast.LENGTH_LONG).show();

                                    }else if (hour == hourOfDay){
                                        if(minute1 < minute){
                                            timeTrue = true;
                                            txtTime2.setText(hourOfDay + ":" + minute);
                                        }
                                        else{
                                            Toast.makeText(MainActivity.this, "Time end should be after Time start!", Toast.LENGTH_LONG).show();
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
                .setPositiveButton("save", new DialogInterface.OnClickListener() {
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
                    Toast.makeText(MainActivity.this, "Enter note!", Toast.LENGTH_SHORT).show();
                    return;
                } else if (txtDate.getText().equals("Date Start")) {
                    Toast.makeText(MainActivity.this, "Enter date!", Toast.LENGTH_SHORT).show();
                    return;
                }else if (txtDate2.getText().equals("Date End")) {
                    Toast.makeText(MainActivity.this, "Enter date!", Toast.LENGTH_SHORT).show();
                    return;
                }  else if (txtTime.getText().equals("Time Start")) {
                    Toast.makeText(MainActivity.this, "Enter start time!", Toast.LENGTH_SHORT).show();
                    return;
                }  else if (txtTime2.getText().equals("Time End")) {
                    Toast.makeText(MainActivity.this, "Enter end time!", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    alertDialog.dismiss();
                }

                    // create new note
                    String[] note = new String[8];
                    note[0] = inputNote.getText().toString();
                    note[1] = category;
                    note[4] = txtDate.getText().toString();
                    note[5] = txtTime.getText().toString();
                    note[6] = txtTime2.getText().toString();
                    note[7] = txtDate2.getText().toString();

                    final Note note1 = new Note(note[0],note[1],"0","0",note[4],note[7],note[5],note[6]);
                    AppExecutor.getInstance().diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            mDb.noteDao().insertNote(note1);

                        }
                    });

                    isTrue=false;
                toggleEmptyNotes();

                }

        });


    }

    public void alert(Note n,int position,Calendar calendar){
        Intent alertIntent = new Intent(getApplicationContext(), AlertReceiver.class)
                .putExtra("taskName",n.getNote())
                .putExtra("position",position);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE );

        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), PendingIntent.getBroadcast(getApplicationContext(), 1, alertIntent,
                PendingIntent.FLAG_UPDATE_CURRENT ));
    }

    public void alert2(){
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent myIntent = new Intent(getApplicationContext(), AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                getApplicationContext(), 1, myIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.cancel(pendingIntent);
    }
    public void toggleEmptyNotes() {

        if (MainActivity.notesList.size() > 0) {
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

    @Override
    protected void onResume() {
        super.onResume();

      onRetrieve();

    }

    public void onRetrieve(){

        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.getTasks().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                HomeFragment.mAdapter.setNotesList(notes);
                notesCount = notes.size();
                notesList = notes;
                toggleEmptyNotes();


            }
        });

    }












}
