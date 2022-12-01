package com.darshan09200.featuretest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    GridLayout gridLayout;
    ArrayList<ImageView> gridImageItems;
    CheckBox checkBox;
    ImageView refresh;
    Button verifyBtn;

    ArrayList<Integer> correctIndex = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
    ArrayList<Integer> imageNos;
    ArrayList<Boolean> gridItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridLayout = findViewById(R.id.gridLayout);
        gridImageItems = new ArrayList<>();
        gridItems = new ArrayList<>();
        imageNos = new ArrayList<>();

        gridImageItems.add(findViewById(R.id.gridItem1));
        gridImageItems.add(findViewById(R.id.gridItem2));
        gridImageItems.add(findViewById(R.id.gridItem3));
        gridImageItems.add(findViewById(R.id.gridItem4));
        gridImageItems.add(findViewById(R.id.gridItem5));
        gridImageItems.add(findViewById(R.id.gridItem6));
        gridImageItems.add(findViewById(R.id.gridItem7));
        gridImageItems.add(findViewById(R.id.gridItem8));
        gridImageItems.add(findViewById(R.id.gridItem9));

        for (int i = 0; i < gridImageItems.size(); i++) {
            gridImageItems.get(i).setOnClickListener(this);
            gridItems.add(false);
            imageNos.add(i);
        }

        checkBox = findViewById(R.id.checkbox);
        refresh = findViewById(R.id.refresh);
        verifyBtn = findViewById(R.id.verify);

        refresh.setOnClickListener(this);
        verifyBtn.setOnClickListener(this);

        randomize();
    }

    private void reset() {
        for (int i = 0; i < gridItems.size(); i++) {
            gridItems.set(i, false);
            gridImageItems.get(i).setAlpha(1.0f);
            gridImageItems.get(i).setForeground(null);
        }
        checkBox.setChecked(false);
    }

    private void randomize() {
        reset();

        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            numbers.add(i);
        }

        for (int i = 0; i < gridImageItems.size(); i++) {
            ImageView gridItem = gridImageItems.get(i);

            Random r = new Random();
            int low = 0;
            int high = numbers.size() - 1;
            int randomIndex = r.nextInt(high - low) + low;
            String uri = "@drawable/img" + numbers.get(randomIndex);
            imageNos.set(i, numbers.get(randomIndex));
            numbers.remove(randomIndex);

            int imageResource = getResources().getIdentifier(uri, null, getPackageName());

            Drawable res = ResourcesCompat.getDrawable(getResources(), imageResource, null);
            gridItem.setImageDrawable(res);
        }
    }

    private void toggleImage(int index) {
        Boolean gridItem = gridItems.get(index);
        if (gridItem) {
            gridItems.set(index, false);
            gridImageItems.get(index).setAlpha(1.0f);
            gridImageItems.get(index).setForeground(null);
        } else {
            System.out.println(index);

            gridImageItems.get(index).setAlpha(0.5f);
            gridImageItems.get(index).setForeground(ResourcesCompat.getDrawable(getResources(), R.drawable.checked, null));
            gridItems.set(index, true);
        }
    }

    private void onVerifyPress() {
        if (checkBox.isChecked()) {

            Boolean verified = true;
            ArrayList<Integer> correctIndexes = (ArrayList<Integer>) this.correctIndex.clone();
            for (int i = 0; i < imageNos.size(); i++) {
                Integer imageNo = imageNos.get(i);
                Boolean isChecked = gridItems.get(i);

                if (correctIndexes.contains(imageNo)) {
                    System.out.println(imageNo);
                    correctIndexes.remove(imageNo);
                    if (isChecked) continue;

                } else if (!isChecked) continue;
                verified = false;
                break;
            }
            imageNos.forEach(item -> {
                System.out.println(item);
            });
            System.out.println(correctIndexes);
            if (!verified || correctIndexes.size() > 0) showToast("You are a robot");
            else showToast("Verification successful");
        } else {
            showToast("Please select the checkbox");
        }
    }


    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.gridItem1:
                toggleImage(0);
                break;
            case R.id.gridItem2:
                toggleImage(1);
                break;
            case R.id.gridItem3:
                toggleImage(2);
                break;
            case R.id.gridItem4:
                toggleImage(3);
                break;
            case R.id.gridItem5:
                toggleImage(4);
                break;
            case R.id.gridItem6:
                toggleImage(5);
                break;
            case R.id.gridItem7:
                toggleImage(6);
                break;
            case R.id.gridItem8:
                toggleImage(7);
                break;
            case R.id.gridItem9:
                toggleImage(8);
                break;
            case R.id.refresh:
                randomize();
                break;
            case R.id.verify:
                onVerifyPress();
                break;
        }
    }
}