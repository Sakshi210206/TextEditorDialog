package com.example.texteditordialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

public class TextEditorDialog extends Dialog {
    public interface TextEditorListener {
        void onTextEdited(String editedText);
    }

    private String inputText;
    EditText editTxt;
    Button btnUpperCase, btnLowerCase, btnInitCap, btnReverse;
    private TextEditorListener listener;

    public TextEditorDialog(Context context, String inputText, TextEditorListener listener) {
        super(context);
        this.inputText = inputText;
        this.listener = listener;
    }

    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.text_editor_dialog);
        EditText editTxt = findViewById(R.id.editTxt);
        Button btnUpperCase = findViewById(R.id.btnUpperCase);
        Button btnLowerCase = findViewById(R.id.btnLowerCase);
        Button btnInitCap = findViewById(R.id.btnInitCap);
        Button btnReverse = findViewById(R.id.btnReverse);
        Button btnApply = findViewById(R.id.btnApply);

        // Set the initial input text
        editTxt.setText(inputText);

        // Convert text to Uppercase
        btnUpperCase.setOnClickListener(v -> {
            String text = editTxt.getText().toString();
            editTxt.setText(text.toUpperCase());
        });

        // Convert text to Lowercase
        btnLowerCase.setOnClickListener(v -> {
            String text = editTxt.getText().toString();
            editTxt.setText(text.toLowerCase());
        });

        // Convert text to Initcap
        btnInitCap.setOnClickListener(v -> {
            String text = editTxt.getText().toString();
            editTxt.setText(initCap(text));
        });

        // Reverse the text
        btnReverse.setOnClickListener(v -> {
            String text = editTxt.getText().toString();
            editTxt.setText(new StringBuilder(text).reverse().toString());
        });

        // Apply changes and return the result
        btnApply.setOnClickListener(v -> {
            if (listener != null) {
                listener.onTextEdited(editTxt.getText().toString());
            }
            dismiss();
        });
    }

    // Helper method to capitalize the first letter of each word
    private String initCap(String text) {
        String[] words = text.split(" ");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            if (word.length() > 0) {
                result.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1).toLowerCase())
                        .append(" ");
            }
        }
        return result.toString().trim();
    }
}

