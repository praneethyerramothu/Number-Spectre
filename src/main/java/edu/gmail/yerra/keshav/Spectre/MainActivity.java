package edu.gmail.yerra.keshav.Spectre;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;



public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        applyViewStyling();
    }



    public void applyViewStyling()
    {
        // Fetch OpenSans Semibold typeface
        Typeface tf = Typeface.createFromAsset(getResources().getAssets(), "OpenSans-Semibold.ttf");

        // Fetch Activity's views
        TextView titleTextView = (TextView) findViewById(R.id.app_title);
        Button easyDiffButton = (Button) findViewById(R.id.easy_difficulty_button);
        Button mediumDiffButton = (Button) findViewById(R.id.medium_difficulty_button);
        Button hardDiffButton = (Button) findViewById(R.id.hard_difficulty_button);
        Button aboutButton = (Button) findViewById(R.id.about_button);

        // Set OpenSans as default typeface
        titleTextView.setTypeface(tf);
        easyDiffButton.setTypeface(tf);
        mediumDiffButton.setTypeface(tf);
        hardDiffButton.setTypeface(tf);
        aboutButton.setTypeface(tf);

        // Elevate Buttons to create drop shadows
        ViewCompat.setElevation(easyDiffButton, 5);
        ViewCompat.setElevation(mediumDiffButton, 5);
        ViewCompat.setElevation(hardDiffButton, 5);
        ViewCompat.setElevation(aboutButton,5);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void easyDiffButtonClicked(View view)
    {
        initiateBoardController(edu.gmail.yerra.keshav.Spectre.BoardDifficulty.EASY);
    }


    public void mediumDiffButtonClicked(View view)
    {
        initiateBoardController(edu.gmail.yerra.keshav.Spectre.BoardDifficulty.MEDIUM);
    }


    public void hardDiffButtonClicked(View view)
    {
        initiateBoardController(edu.gmail.yerra.keshav.Spectre.BoardDifficulty.HARD);
    }


    public void initiateBoardController(edu.gmail.yerra.keshav.Spectre.BoardDifficulty difficulty)
    {
        Intent boardControllerIntent = new Intent(this, edu.gmail.yerra.keshav.Spectre.BoardControllerActivity.class);

        boardControllerIntent.putExtra("difficulty",difficulty);
        startActivity(boardControllerIntent);
    }


}
