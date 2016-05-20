package com.truedev.officeoffice;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by dipanshugarg on 19/5/16.
 */
public class CommonUtils {

    public static String getCurrentDate(){
        Calendar calander = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String date = df.format(calander.getTime());
        return date;
    }

}
