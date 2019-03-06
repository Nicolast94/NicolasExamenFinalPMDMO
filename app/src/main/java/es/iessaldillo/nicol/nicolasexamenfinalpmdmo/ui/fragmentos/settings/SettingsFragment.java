package es.iessaldillo.nicol.nicolasexamenfinalpmdmo.ui.fragmentos.settings;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;
import es.iessaldillo.nicol.nicolasexamenfinalpmdmo.R;

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }
}
