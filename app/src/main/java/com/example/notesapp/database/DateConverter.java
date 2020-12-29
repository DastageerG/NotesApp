package com.example.notesapp.database;

import androidx.room.TypeConverter;

import java.util.Date;

public class DateConverter
{
    @TypeConverter
    public static Long toTimeStamp(Date date)
    {
        return date == null? null:date.getTime();
    } // toTimeStamp closed


    @TypeConverter
    public static Date toDate(Long timestamp)
    {
        return timestamp == null? null:new Date(timestamp);
    } // toTimeStamp closed


}
