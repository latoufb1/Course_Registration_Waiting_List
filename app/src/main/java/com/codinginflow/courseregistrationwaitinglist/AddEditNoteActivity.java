package com.codinginflow.courseregistrationwaitinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.codinginflow.courseregistrationwaitinglist.R;

public class AddEditNoteActivity extends AppCompatActivity {
    public static final String EXTRA_ID =
            "com.codinginflow.courseregistrationwaitinglist.EXTRA_ID";
    public static final String EXTRA_FNAME =
            "com.codinginflow.courseregistrationwaitinglist.EXTRA_FNAME";
    public static final String EXTRA_LNAME =
            "com.codinginflow.courseregistrationwaitinglist.EXTRA_LNAME";
    public static final String EXTRA_DESCRIPTION =
            "com.codinginflow.courseregistrationwaitinglist.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY =
            "com.codinginflow.courseregistrationwaitinglist.EXTRA_PRIORITY";

    private EditText editTextFName;
    private EditText editTextLName;
    private EditText editTextDescription;
    private NumberPicker numberPickerPriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTextFName = findViewById(R.id.edit_text_FName);
        editTextLName = findViewById(R.id.edit_text_LName);
        editTextDescription = findViewById(R.id.edit_text_description);
        numberPickerPriority = findViewById(R.id.number_picker_priority);

        numberPickerPriority.setMinValue(0);
        numberPickerPriority.setMaxValue(4);
        numberPickerPriority.setDisplayedValues( new String[] { "Grad", "1st", "2nd", "3rd", "4th"} );

        numberPickerPriority.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                int valuenumberPickerPriority = numberPickerPriority.getValue();
                Log.d("number values", String.valueOf(numberPickerPriority));
            }
        });




        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Note");
            editTextFName.setText(intent.getStringExtra(EXTRA_FNAME));
            editTextLName.setText(intent.getStringExtra(EXTRA_LNAME));
            editTextDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            numberPickerPriority.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1));
        } else {
            setTitle("Add Note");
        }
    }

    private void saveNote() {
        String FName = editTextFName.getText().toString();
        String LName = editTextLName.getText().toString();
        String description = editTextDescription.getText().toString();
        int priority = numberPickerPriority.getValue();

        if (FName.trim().isEmpty() || LName.trim().isEmpty()  || description.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a title and description", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_FNAME, FName);
        data.putExtra(EXTRA_LNAME, LName);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_PRIORITY, priority);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1){
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
