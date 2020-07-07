package com.valera.diplom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.valera.diplom.pojo.Word;

public class EditActivity extends AppCompatActivity {

    private EditText editTextWord;
    private EditText editTextTranslation;
    private EditText editTextType;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        editTextWord = findViewById(R.id.editTextWord);
        editTextTranslation = findViewById(R.id.editTextTranslation);
        editTextType = findViewById(R.id.editTextType);
        db = FirebaseFirestore.getInstance();
    }

    public void onCLickAddWord(View view) {
        Word word = null;
        String wordEng = editTextWord.getText().toString();
        String wordTranslation = editTextTranslation.getText().toString();
        String wordType = editTextType.getText().toString();
        if(wordEng != null && !wordEng.isEmpty() && wordTranslation != null && !wordTranslation.isEmpty() && wordType != null && !wordType.isEmpty()){
            word = new Word(wordEng, wordTranslation, wordType);
        }
        else{
            Toast.makeText(this, "Вы не заполнили все поля", Toast.LENGTH_SHORT).show();
        }
        db.collection("dictionary").add(word).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                editTextWord.setText("");
                editTextTranslation.setText("");
                editTextType.setText("");
                Toast.makeText(EditActivity.this, "Слово было добавлено", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(EditActivity.this, "Слово не было добавлено", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
