package com.example.android.lazyengineer.Notes;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.example.android.lazyengineer.R;

public class NotesSettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.pref_visualizer_notes);
    }
}
