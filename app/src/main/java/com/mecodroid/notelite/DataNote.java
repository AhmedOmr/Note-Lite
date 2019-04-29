package com.mecodroid.notelite;

import android.os.Parcel;
import android.os.Parcelable;


class DataNote implements Parcelable {
    public int id;
    public String title;
    public String subject;
    public String date;
    public String ShowdateEn;
    public String ShowdateAr;
    public  int Prcolor;
    public DataNote(int id, String title, String subject, String date, String showdateEn, String showdateAr, int prcolor) {
        this.id = id;
        this.title = title;
        this.subject = subject;
        this.date = date;
        ShowdateEn = showdateEn;
        ShowdateAr = showdateAr;
        Prcolor = prcolor;
    }




    public int getPrcolor() {
        return Prcolor;
    }

    public void setPrcolor(int prcolor) {
        Prcolor = prcolor;
    }

    public String getShowdateAr() {
        return ShowdateAr;
    }

    public void setShowdateAr(String showdateAr) {
        ShowdateAr = showdateAr;
    }

    public String getShowdateEn() {
        return ShowdateEn;
    }

    public void setShowdateEn(String showdateEn) {
        ShowdateEn = showdateEn;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public DataNote(int id, String title, String subject, String date) {
        this.id = id;
        this.title = title;
        this.subject = subject;
        this.date = date;
    }

    protected DataNote(Parcel in) {
        id = in.readInt();
        title = in.readString();
        subject = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(subject);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DataNote> CREATOR = new Creator<DataNote>() {
        @Override
        public DataNote createFromParcel(Parcel in) {
            return new DataNote(in);
        }

        @Override
        public DataNote[] newArray(int size) {
            return new DataNote[size];
        }
    };

    public DataNote(int id, String title, String subject) {
        this.id = id;
        this.title = title;
        this.subject = subject;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

}
