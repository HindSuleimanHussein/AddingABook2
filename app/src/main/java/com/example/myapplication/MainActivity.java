package com.example.myapplication;



import android.content.Intent;
import android.transition.Explode;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.transition.Scene;
import android.transition.Transition;
import androidx.transition.TransitionManager;
import com.example.myapplication.newPackage.Book;
import com.example.myapplication.newPackage.BookDs;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btnShow;
    private Spinner spnTypes;
    private TextView textResult;
    private Button buttonScene;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // on create is used for initializing and is called only when the activity is not created, however if the activity exists then the onCreate doesn't get called on
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnShow=findViewById(R.id.btnShow);
        spnTypes=findViewById(R.id.spnTypes);
        // spinner calls on toString and exists on polymorphism and therefore when an object is called then spinner calls on the toString in order to print, so we have to override

        textResult=findViewById(R.id.textResult);
        buttonScene=findViewById(R.id.addMore);
        BookDs bookDs=new BookDs();
        String[] types = bookDs.getBookTypes();
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, types); // it takes which activity, alongside the default spinner, and the data

        spnTypes.setAdapter(adapter);

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = spnTypes.getSelectedItem().toString();
                BookDs bookDs=new BookDs();
                List<Book> listBooks = bookDs.getBooksByTypes(type);

                String str=" ";
                for(Book b: listBooks){
                    str+=b.getTitle().toString() + "\n";
                }
                textResult.setText(str);
            }
        });

        buttonScene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });

    }

    public void openActivity2(){
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);

    }


}