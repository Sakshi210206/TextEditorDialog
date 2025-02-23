package com.example.texteditordialog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button btnEditText;
    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.textView);
        Button btnEditText = findViewById(R.id.btnEditText);

        btnEditText.setOnClickListener(v -> {
            TextEditorDialog dialog = new TextEditorDialog(this, textView.getText().toString(), editedText -> {
                // Update the TextView with the edited text
                textView.setText(editedText);
            });
            dialog.show();
        });
    }
}
