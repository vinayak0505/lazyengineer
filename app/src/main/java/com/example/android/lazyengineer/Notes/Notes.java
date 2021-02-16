package com.example.android.lazyengineer.Notes;
import com.example.android.lazyengineer.MainActivity;
import com.example.android.lazyengineer.Notes.Document.PdfDocument;
import com.example.android.lazyengineer.Notes.Document.PdfDocumentAdapter;
import com.example.android.lazyengineer.Notes.Document.PdfLoader;
import com.example.android.lazyengineer.R;
import com.example.android.lazyengineer.data.DocumentContract;
import com.example.android.lazyengineer.data.DocumentContract.DocumentEntry;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.lazyengineer.data.DocumentdbHelper;

import java.util.ArrayList;
import java.util.List;

public class Notes extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<PdfDocument>>{

    private static final String TAG = "MyActivity";
    private PdfDocumentAdapter pdfDocuments;
    private DownloadManager dm;
    public static String MAIN_URL = "https://lazyengineer.tech/leapi/polytechnic/?branch=Computer%20Engineering&types=ebook&api=ayjLCzL0IphkNbN1IjugUQ&format=json";
    private View[] mView = new View[5];
    private int n = 0;
    private int m = 0;
    DocumentdbHelper documentdbHelper;
    private long uriId;
    private long documentId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        
        ConnectivityManager cm = (ConnectivityManager)this.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        if(isConnected) {
            pdfDocuments = new PdfDocumentAdapter(this, new ArrayList<PdfDocument>());
            GridView gridView = (GridView) findViewById(R.id.grid);
            gridView.setAdapter(pdfDocuments);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    PdfDocument currentPdfDocument = pdfDocuments.getItem(position);
                    mView[m] = view;
                    m++;
                    String Title = currentPdfDocument.getTitle();
                    String url = currentPdfDocument.getFileUrl();
                    String Description = currentPdfDocument.getDescription();
                    documentId = currentPdfDocument.getId();
                    uriId = checkUriFromDatabase(documentId);
                    Log.e(TAG, "onItemClick: " + uriId );
                    if (uriId == 0){
                        uriId = downloadPdf(url, Description, Title);
                    }
                    else {
                        Uri uri = Uri.parse("content://downloads/all_downloads/"+uriId);
                        boolean isDownloaded = IntentToOpenDocument(uri);
                        if (!isDownloaded) {
                            uriId = downloadPdf(url, Description, Title);
                        }
                    }
                }
            });
            LoaderManager.getInstance(this).initLoader(0, null, this).forceLoad();
        }
        else {
            //TODO set text that not able to connect to the internet
        }



        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)){
                    insertDownload(uriId,documentId);
                    n++;
                    DownloadManager.Query req_query = new DownloadManager.Query();
                    req_query.setFilterById(uriId);
                    Cursor cursor = dm.query(req_query);

                    if(cursor.moveToFirst()){
                        int columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
                        if(DownloadManager.STATUS_SUCCESSFUL == cursor.getInt(columnIndex)){
                            Uri uri = dm.getUriForDownloadedFile(uriId);
                            IntentToOpenDocument(uri);
                        }
                    }
                }
            }
        };
        registerReceiver(broadcastReceiver, new IntentFilter(dm.ACTION_DOWNLOAD_COMPLETE));

        documentdbHelper = new DocumentdbHelper(this);
    }

    private Boolean IntentToOpenDocument(Uri uri) {
        Intent openAttachmentIntent = new Intent(Intent.ACTION_VIEW);
        openAttachmentIntent.setDataAndType(uri, "application/pdf");
        openAttachmentIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        try {
            startActivity(openAttachmentIntent);
            return true;
        } catch (ActivityNotFoundException e) {
            Toast.makeText(getApplicationContext(), "Not able to open file", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    @NonNull
    @Override
    public Loader<List<PdfDocument>> onCreateLoader(int id, @Nullable Bundle args) {
        return new PdfLoader(this,MAIN_URL);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<PdfDocument>> loader, List<PdfDocument> data) {
        updateUi(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<PdfDocument>> loader) {
        loader.reset();
    }

    private void updateUi(final List<PdfDocument> data) {
        if(data != null) {
            if (!data.isEmpty()) {
                pdfDocuments.clear();
                pdfDocuments.addAll(data);
            }
        }
    }


    //downloads the pdf file from the url returns uriId
    // sets the download file description and Title
    public long downloadPdf(String url, String Description, String Title){
        Toast.makeText(this,"Downloading...",Toast.LENGTH_SHORT).show();
        dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setDescription(Description).setTitle(Title);
        return dm.enqueue(request);
    }


    // checks for downloaded file call checkUriFromDatabase
    // opens up the document id available
    // returns true if the file is already existed and open successful.
    // handles if the file is not downloaded or if not found where expected or nat able to open the returns false.
    // takes in parameter for documentId provided by the firebase
    private boolean openDocumentFromUri(long uriId){
        Intent openAttachmentIntent = new Intent(Intent.ACTION_VIEW);
        Uri uri = dm.getUriForDownloadedFile(uriId);
        openAttachmentIntent.setDataAndType(uri, "application/pdf");
        openAttachmentIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        try {
            this.startActivity(openAttachmentIntent);
            return true;
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this,"Unable to open Pdf try downloading a pdf viewer",Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private long insertDownload(long queueId,long id) {

        SQLiteDatabase db = documentdbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DocumentEntry._ID,id);
        values.put(DocumentEntry.COLUMN_URI,queueId);
        return db.insert(DocumentEntry.TABLE_NAME,null,values);

    }


    // check if the file uri is saved in the database or not and return the UriId if it saved
    // which needs to be converted into uri and return 0 if uri is not saved on the database.
    // does by matching the Document id...
    private long checkUriFromDatabase(long id) {

        DocumentdbHelper mDbHelper = new DocumentdbHelper(this);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] columns = new String[]{DocumentEntry.COLUMN_URI};
        String selection = DocumentEntry._ID + " == " + id + ";";
        long uriId = 0;
        try{
            Cursor cursor = db.query(DocumentContract.DocumentEntry.TABLE_NAME,columns,selection,null,null,null,null);
            int columnIndex = cursor.getColumnIndex(DocumentEntry.COLUMN_URI);
            while (cursor.moveToNext()) {
                uriId = cursor.getInt(columnIndex);
            }
            cursor.close();
        } catch (Exception e) {
            Log.e(TAG, "checkDownloaded: error occurring please check"+ uriId );
        } finally {
            db.close();
            mDbHelper.close();
        }
        return uriId;
    }
}