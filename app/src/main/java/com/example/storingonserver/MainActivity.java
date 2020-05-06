package com.example.storingonserver;

import androidx.appcompat.app.AppCompatActivity;            //Go to my4app and open dashboard and see in the class name
                                                            //and not forget to refresh every time
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class MainActivity extends AppCompatActivity {
    private Button btn;
    private EditText editTextName, editTextPhone, editTextAge, editTextMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ParseInstallation.getCurrentInstallation().saveInBackground();
        btn = findViewById(R.id.button);
        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextMessage = findViewById(R.id.editTextMessage);
    }
    public void saveMethodOnClick(View view) {
        try {                                                                               //used try to catch the invalid entry in the text fields
            final ParseObject myFirstClass = new ParseObject("MySaving");
            myFirstClass.put("Message", editTextMessage.getText().toString());
            myFirstClass.put("Age", editTextAge.getText().toString());
            myFirstClass.put("Phone", editTextPhone.getText().toString());
            myFirstClass.put("Name", editTextName.getText().toString());
            myFirstClass.saveInBackground(new SaveCallback() {              // we can directly save the content by without writing new Savecallback()
                @Override
                                                                                //and dont inmplement the exception part.
                                                                                             //But it is imp because of connection to show user weather success or not
                public void done(ParseException e) {                        //Also i implemented the designer toast search fancy toast on google
                    if (e == null) {
                        FancyToast.makeText(MainActivity.this,
                                "info for " + editTextName.getText().toString() + " is saved to server",
                                FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                        editTextMessage.setText("");                        // to clear text after the saving.
                        editTextName.setText("");
                        editTextPhone.setText("");
                        editTextAge.setText("");
                    } else {
                        FancyToast.makeText(MainActivity.this, e.getMessage(),
                                FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                    }
                }
            });
        }
        catch (Exception e){
            FancyToast.makeText(MainActivity.this, e.getMessage(),
                    FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
        }
    }
}
