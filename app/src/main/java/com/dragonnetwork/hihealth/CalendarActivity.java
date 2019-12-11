package com.dragonnetwork.hihealth;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.util.Log;
import android.widget.TextView;

import com.dragonnetwork.hihealth.data.User;
import com.dragonnetwork.hihealth.medication.MedicationActivity;
import com.dragonnetwork.hihealth.user.UserProfile;
import com.google.android.material.navigation.NavigationView;

public class CalendarActivity extends MainActivity implements NavigationView.OnNavigationItemSelectedListener {
    private  static final String TAG = "CalendarActivity";
    private CalendarView mCalendarView;

    protected Toolbar toolbar;
    protected DrawerLayout drawer;
    protected ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contentView = R.id.nav_calendar;
        setContentView(R.layout.activity_calendar);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.calendar_layout);
        onCreateDrawer();

        View header = navigationView.getHeaderView(0);
        TextView navUsername = (TextView)header.findViewById(R.id.username_nav);
        navUsername.setText(User.getName());
        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(CalendarActivity.this, UserProfile.class);
                startActivity(intent);
            }
        });

//        navigationView = findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(CalendarActivity.this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView CalendarView, int year, int month, int dayOfMonth) {
//                String date = year + "/" + month + "/"+ dayOfMonth ;
//                Log.d(TAG, "onSelectedDayChange: yyyy/mm/dd:" + date);
//                Intent intent = new Intent(getApplicationContext(),CalenderActivity.class);
//                intent.putExtra("date",date);
//                startActivity(intent);
                addCalendarEvent();
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Intent intent;

        switch (menuItem.getItemId()) {
            case R.id.nav_medications:
                intent = new Intent(this, MedicationActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                break;
            case R.id.nav_reminders:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                break;
            case R.id.nav_calendar:
                drawer.closeDrawer(Gravity.LEFT);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                break;
            case R.id.nav_reports:
                intent = new Intent(this, ReportsActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                break;
            case R.id.nav_symptoms:
                intent = new Intent(this, SymptomsActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                break;
        }

        return true;
    }

    public void addCalendarEvent(){

        Calendar cal = Calendar.getInstance();
        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra("beginTime", cal.getTimeInMillis());
        intent.putExtra("allDay", true);
        intent.putExtra("rrule", "FREQ=WEEKLY");
        intent.putExtra("endTime", cal.getTimeInMillis()+60*60*1000);
        intent.putExtra("title", "Appointment Name");
        intent.putExtra("description", "Appointment Description");
        intent.putExtra("eventLocation", "Appointment Location");
        startActivity(intent);

    }
}
