package com.apps.peterlewis.multi_dimensionallists;
//Built by: Peter Lewis
//Date: Friday, March 31 - Saturday, April 2
//this is an app which generates matrices.




import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int spinnerPosition;
    private int[][] matrix;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //fill the dimen_spinner with choices
        Spinner spinner = (Spinner) findViewById(R.id.dimen_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.choices_for_spinner, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        //set item selected listener for the spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                spinnerPosition = position;
                matrix = create_matrix(position+2);
                showMatrix(matrix);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                //do nothing
            }

        });

        //set button listener for transposeButton
        Button transposeButton = (Button) findViewById(R.id.transposeButton);
        transposeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                matrix = transpose(matrix);
                showMatrix(matrix);
            }
        });

        //set button listener for reGenButton
        Button reGenButton = (Button) findViewById(R.id.reGenButton);
        reGenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                matrix = create_matrix(spinnerPosition+2);
                showMatrix(matrix);
            }
        });
    }

    private void showMatrix(int[][] matrix){
        int size = matrix.length;
        TextView matrixTextView = (TextView) findViewById(R.id.matrixTextView);
        TextView isSymTextView = (TextView) findViewById(R.id.isSymTextView);

        //change the font size of matrixTextView
        if(size == 2){
            matrixTextView.setTextSize(64);
        }else if(size >= 3 && size <= 4){
            matrixTextView.setTextSize(32);
        }else if(size >= 5 && size <= 7){
            matrixTextView.setTextSize(20);
        }else if(size >= 8 && size <= 10){
            matrixTextView.setTextSize(13);
        }

        //set text for matrixTextView
        String text = "";
        for(int i = 0; i<size; i++){
            for(int j = 0; j<size; j++){
                text = text + matrix[i][j];
                if(size - 1 != j){//not last time going through j
                    text = text + "  ";
                }
            }
            if(size - 1 != i){//not last time going through n
                text = text + "\n";
            }
        }
        matrixTextView.setText(text);
        if(is_symmetric(matrix)){
            isSymTextView.setText("This matrix is symmetrical.");
        }else{
            isSymTextView.setText("This matrix is not symmetrical.");
        }
    }

    private int[][] create_matrix(int n){
        Random rand = new Random();
        int[][] output = new int[n][n];
        for(int i = 0; i<n; i++){
            int[] tempArray = new int[n];
            for(int j = 0; j<n; j++){
                int randomNum = rand.nextInt(10);
                tempArray[j] = randomNum;
            }
            output[i] = tempArray;
        }
        return output;
    }

    private boolean is_symmetric(int[][] m){
        int [][] transposeM = transpose(m);
        for(int i = 0; i<m.length; i++){
            for(int j = 0; j<m.length; j++){
                if(m[i][j]!=transposeM[i][j]){
                    return false;//returns immediately
                }
            }
        }
        return true;//will not get here unless it is symmetrical
    }

    private int[][] transpose(int[][] m){
        int[][] output = new int[m.length][m.length];
        for(int i = 0; i<m.length; i++){
            for(int j = 0; j<m.length; j++){
                output[i][j] = m[j][i];
            }
        }
        return output;
    }
}
