package com.example.android.lazyengineer;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

public class NotesSettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.pref_visualizer_notes);
    }
}
