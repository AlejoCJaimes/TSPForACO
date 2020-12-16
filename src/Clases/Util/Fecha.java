/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases.Util;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Alejandro Jaimes
 */
public class Fecha {
    
    //Atributos
    private final Calendar date = new GregorianCalendar();
    private final int year;
    private final int month;
    private final int day;
    private final int hour;
    private final int minute;
    private String meridian;

    public Fecha() {
        this.year = date.get(Calendar.YEAR);
        this.month = date.get(Calendar.MONTH);
        this.day = date.get(Calendar.DAY_OF_MONTH);
        this.hour = date.get(Calendar.HOUR_OF_DAY);
        this.minute = date.get(Calendar.MINUTE);
        //this.meridian = date.get(Calendar.AM_PM);
    }
    
    public String GetCurrentDate () {
        
        return "Fecha Actual: "+ day + " / " + (month+1) + " / " + year;
    }
    
    private String GetMeridian() {
        
        return (date.get(Calendar.AM_PM) != 0)?"PM":"AM";
    }
    public String GetCurrentDateComplete () {
        
        return "Fecha Actual: "+ day + "/" + (month+1) + "/" + year + " Hora Actual: "+ hour+":" + minute + " " + GetMeridian();
    }
    
}