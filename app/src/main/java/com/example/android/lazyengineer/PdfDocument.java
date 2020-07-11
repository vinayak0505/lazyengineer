package com.example.android.lazyengineer;

public class PdfDocument {
    private String mFileUrl;
    private String mTitle;
    private String mDescription = "No Description";
    private String mDate = null;
    private float mSize = 0;
    private int mId = 0;

    public PdfDocument(String FileUrl, String Title, String Description, String Date,int id){
        mFileUrl = FileUrl;
        mTitle = Title;
        mDescription = Description;
        mDate = Date;
        mId = id;
    }

    public String getFileUrl(){
        return mFileUrl;
    }
    public String getTitle(){
        return mTitle;
    }
    public String getDescription(){
        return mDescription;
    }
    public String getSize(){
        return String.valueOf(mSize);
    }
    public String getDate(){
        return mDate;
    }
    public int getId(){
        return mId;
    }
}
