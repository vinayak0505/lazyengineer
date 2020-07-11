package com.example.android.lazyengineer.data;

import android.provider.BaseColumns;

public class DocumentContract {

    public static abstract class DocumentEntry implements BaseColumns {
        public static final String TABLE_NAME = "documents";
        public static final String _ID = "ID";
        public static final String COLUMN_URI = "uri";
    }
}