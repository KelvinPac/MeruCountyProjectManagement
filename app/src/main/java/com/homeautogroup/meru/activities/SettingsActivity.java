package com.homeautogroup.meru.activities;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.homeautogroup.meru.R;

import java.net.URL;

public class SettingsActivity extends AppCompatActivity {

    private TextView textViewUrl;
    private EditText inputUrl;
    private SharedPreferences sharedPreferences;
    public static final String SERVER_URL = "server_url";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_activuty);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        String current_url = sharedPreferences.getString(SERVER_URL,null);
        textViewUrl = findViewById(R.id.textViewUrl);
        inputUrl = findViewById(R.id.editTextLink);

        if (current_url !=null){
            inputUrl.setText(current_url);
            textViewUrl.setText(textViewUrl.getText()+" "+current_url);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    public void saveServerUrl(View view) {
        String url = inputUrl.getText().toString().trim();
        if (!TextUtils.isEmpty(url)){

            if (Patterns.WEB_URL.matcher(url).matches()){
                //save to prefs
                sharedPreferences.edit().putString(SERVER_URL,url).apply();
                Toast.makeText(this, "Re open application", Toast.LENGTH_LONG).show();
            }else {
                inputUrl.requestFocus();
                inputUrl.setError("enter valid url");
                Toast.makeText(this, "enter valid url", Toast.LENGTH_SHORT).show();
            }

        }else {
            inputUrl.requestFocus();
            inputUrl.setError("url cannot be empty");
            Toast.makeText(this, "url cannot be empty", Toast.LENGTH_SHORT).show();
        }

    }
}
