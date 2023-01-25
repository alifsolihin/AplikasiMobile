package com.example.mimpi_kita;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.applikeysolutions.cosmocalendar.selection.RangeSelectionManager;
import com.applikeysolutions.cosmocalendar.utils.SelectionType;
import com.applikeysolutions.cosmocalendar.view.CalendarView;

import java.util.Calendar;
import java.util.HashSet;

public class Calender1 extends AppCompatActivity {

    CalendarView calendarView;
    Button button;
    String startDate, endDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender1);

        calendarView = findViewById(R.id.cosmo_calendar);
        button = findViewById(R.id.selectButton);

        //Set First day of the week
        calendarView.setFirstDayOfWeek(Calendar.MONDAY);

        //Set Orientation 0 = Horizontal | 1 = Vertical
        calendarView.setCalendarOrientation(0);

        calendarView.setWeekendDays(new HashSet(){{
            add(Calendar.SATURDAY);
            add(Calendar.SUNDAY);
        }});

        //Range Selection
        calendarView.setSelectionType(SelectionType.RANGE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (calendarView.getSelectionManager() instanceof RangeSelectionManager) {
                    RangeSelectionManager rangeSelectionManager = (RangeSelectionManager) calendarView.getSelectionManager();
                    if(rangeSelectionManager.getDays() != null) {
                        startDate = rangeSelectionManager.getDays().first.toString();
                        endDate = rangeSelectionManager.getDays().second.toString();
                    } else {
                        Toast.makeText(Calender1.this, "Gagal Memilih", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}