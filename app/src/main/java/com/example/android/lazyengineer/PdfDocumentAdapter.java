package com.example.android.lazyengineer;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.android.lazyengineer.data.DocumentContract;
import com.example.android.lazyengineer.data.DocumentdbHelper;

import java.util.List;

public class PdfDocumentAdapter extends ArrayAdapter<PdfDocument> {
    public PdfDocumentAdapter(@NonNull Context context, @NonNull List<PdfDocument> PdfDocuments) {
        super(context, 0, PdfDocuments);
    }



    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.gridview,parent,false);
        }

        PdfDocument currentPdfDocument = getItem(position);
        TextView Heading = listItemView.findViewById(R.id.Heading);
        Heading.setText(currentPdfDocument.getTitle());

        TextView description = (TextView) listItemView.findViewById(R.id.Description);
        description.setText(currentPdfDocument.getDescription());

        TextView date = (TextView) listItemView.findViewById(R.id.Date);
        date.setText(currentPdfDocument.getDate());

        TextView size = (TextView) listItemView.findViewById(R.id.Size);
        size.setText(currentPdfDocument.getSize());

        return listItemView;
    }
}