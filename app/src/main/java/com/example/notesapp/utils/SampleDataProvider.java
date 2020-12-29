package com.example.notesapp.utils;

import com.example.notesapp.database.NoteEntity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class SampleDataProvider
{
    public static  String SAMPLE_TITLE = "Sample Title";
    public static String SAMPLE_DESCRIPTION =
    "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum";


    public static Date getDate(int diffAmount)
    {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.add(Calendar.MILLISECOND,diffAmount);
        return gregorianCalendar.getTime();

    } // getDate closed

    public static List<NoteEntity>getSampleData()
    {
        List<NoteEntity>noteEntityList = new ArrayList<>();
        noteEntityList.add(new NoteEntity(1,getDate(0),SAMPLE_TITLE,SAMPLE_DESCRIPTION));
        noteEntityList.add(new NoteEntity(2,getDate(-1),SAMPLE_TITLE,SAMPLE_DESCRIPTION));
        noteEntityList.add(new NoteEntity(3,getDate(-2),SAMPLE_TITLE,SAMPLE_DESCRIPTION));
        return  noteEntityList;
    } //


}
