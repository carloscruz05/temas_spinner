package com.example.themesspinnerapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "prefs";
    private static final String PREF_THEME = "theme";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(getSavedTheme());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.themes_array, R.layout.spinner_item);  // Usar diseño personalizado


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int selectedTheme;
                switch (position) {
                    case 0:
                        selectedTheme = R.style.LightTheme;
                        break;
                    case 1:
                        selectedTheme = R.style.DarkTheme;
                        break;
                    case 2:
                        selectedTheme = R.style.CustomTheme;
                        break;
                    default:
                        selectedTheme = R.style.AppTheme;
                        break;
                }
                if (getSavedTheme() != selectedTheme) {
                    saveTheme(selectedTheme);
                    recreate(); // Reinicia la actividad para aplicar el nuevo tema
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No hacer nada
            }
        });
    }

    private void saveTheme(int theme) {
        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        editor.putInt(PREF_THEME, theme);
        editor.apply();
    }

    private int getSavedTheme() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        return prefs.getInt(PREF_THEME, R.style.AppTheme); // Tema por defecto
    }
}
