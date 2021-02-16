package com.example.android.lazyengineer.Notes.Document;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.android.lazyengineer.Notes.Document.PdfDocument;
import com.example.android.lazyengineer.R;

import java.util.List;

public class PdfDocumentAdapter extends ArrayAdapter<PdfDocument> {
    public PdfDocumentAdapter(@NonNull Context context, @NonNull List<PdfDocument> PdfDocuments) {
        super(context, 0, PdfDocuments);
    }



    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null)
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.gridview,parent,false);


        PdfDocument currentPdfDocument = getItem(position);


        TextView Heading = (TextView) listItemView.findViewById(R.id.Heading);
        Heading.setText(currentPdfDocument.getTitle());

        TextView description = (TextView) listItemView.findViewById(R.id.Description);
        description.setText(currentPdfDocument.getDescription());

        TextView date = (TextView) listItemView.findViewById(R.id.Date);
        date.setText(currentPdfDocument.getDate());

        return listItemView;
    }
}