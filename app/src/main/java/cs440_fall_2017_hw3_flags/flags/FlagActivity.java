package cs440_fall_2017_hw3_flags.flags;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class FlagActivity extends AppCompatActivity {
    /* list of selected country */
    ArrayList<String> mSelectedContinents = null;

    /* reference to all the game images / flags */
    ImageView mFlag1, mFlag2, mFlag3, mFlag4;

    /* reference to all the game buttons */
    Button btn1, btn2, btn3, btn4;

    /* reference to all the textviews */
    TextView mRound_textView;   // displays the current round per game
    TextView mLevel_textView;   // displays the current level per game
    TextView mRegion_textView;  // dislays the select region message

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag);


        //  first thing is to save the list of continents into
        //  the intent
        mSelectedContinents = getIntent().getStringArrayListExtra("continents");

        //this is to test the received list
        if (mSelectedContinents != null) {
            Log.d("receiveListTest", Arrays.toString(mSelectedContinents.toArray()));
        } else {
            Log.d("receiveListTest", "Nothing was recevied");
        }

        //  get the id's of all elements on the screen
        getAllViewByID();

        //  get the display metrics of the device
        DisplayMetrics dsplyMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dsplyMetrics);
        Log.d("r-size","width: " + dsplyMetrics.widthPixels + "pixels" + " " +
                "height: " + dsplyMetrics.heightPixels + "pixels");

        //  set and position the elements on the screen
        setFrameSize(dsplyMetrics);

    }

    /* get the id's of all the views in the framelayout */
    private void getAllViewByID() {

        mLevel_textView  = (TextView) findViewById(R.id.level_textView);
        mRound_textView  = (TextView) findViewById(R.id.round_textView);
        mRegion_textView = (TextView) findViewById(R.id.region_textView);

        //  reference to all the flag views
        mFlag1 = (ImageView) findViewById(R.id.flag1_imageView);
        mFlag2 = (ImageView) findViewById(R.id.flag2_imageView);
        mFlag3 = (ImageView) findViewById(R.id.flag3_imageView);
        mFlag4 = (ImageView) findViewById(R.id.flag4_imageView);

        //  reference to all the buttons
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);

    }

    /*
        Sets the frame size of the screen and
        position the various elements on the screen
     */
    private void setFrameSize(DisplayMetrics displayMetrics) {
        //  get the size of the screen
        int dispWidth = displayMetrics.widthPixels;
        int dispHeight = displayMetrics.heightPixels;

        //  get the dimensions of the text views
        int txtViewWidth  = (int) (dispWidth * .5);
        int txtViewHeight = (int) (dispHeight * .1);

        //  get the dimensions of the flags
        int flagWidth  = (int) (dispWidth * 0.5);
        int flagHeight = (int) (dispHeight * 0.2);

        //  get the dimensions of the buttons
        int buttonWidth  = (int) (dispWidth * 0.5);
        int buttonHeight = (int) (dispHeight * 0.1);

        //  set the offset in the x and y direction
        //  the x-offset is the distance from the left edge of the screen to the view
        int yOffset = (int) (dispHeight * 0.02);
        //  the y-offset is the distatnce from the top of the screen to the view
        int xOffset = (int) (dispWidth * 0.02);


        //  set the size and position of the level texview
        FrameLayout.LayoutParams levelCountParam = new FrameLayout.LayoutParams(txtViewWidth,txtViewHeight);
        levelCountParam.setMargins(xOffset, yOffset, 0, 0);
        mLevel_textView.setLayoutParams(levelCountParam);
        mLevel_textView.setText("Level 1 place holder");

        // set the size and position of the round textview to some distace
        // after the level text view
        FrameLayout.LayoutParams roundCountParams = new FrameLayout.LayoutParams(txtViewWidth,txtViewHeight);
        roundCountParams.setMargins((txtViewWidth + xOffset), yOffset, 0, 0);
        mRound_textView.setLayoutParams(roundCountParams);
        mRound_textView.setText("Round 1");

        // the first row of flag images are postioned some offset from the top of the screen
        yOffset += txtViewHeight;

        //  set the size and position of flag 1
        FrameLayout.LayoutParams flag1_params = new FrameLayout.LayoutParams(flagWidth, flagHeight);
        flag1_params.setMargins(xOffset, yOffset, 0, 0);
        mFlag1.setLayoutParams(flag1_params);
        mFlag1.setPadding(4,4,4,4);
        mFlag1.setBackgroundColor(Color.BLACK);

        //  set the size and position of flag 2
        FrameLayout.LayoutParams flag2_params = new FrameLayout.LayoutParams(flagWidth, flagHeight);
        flag2_params.setMargins((xOffset + flagWidth), yOffset, 0, 0);
        mFlag2.setLayoutParams(flag2_params);
        mFlag2.setPadding(4,4,4,4);
        mFlag2.setBackgroundColor(Color.GRAY);

        // the second row of flag images are positoned some offset from the top of
        // the screen plus the distance from the first row of images
        yOffset += flagHeight;

        //  set the size and position of flag 3
        FrameLayout.LayoutParams flag3_params = new FrameLayout.LayoutParams(flagWidth, flagHeight);
        flag3_params.setMargins(xOffset, yOffset, 0, 0);
        mFlag3.setLayoutParams(flag3_params);
        mFlag3.setPadding(4,4,4,4);
        mFlag3.setBackgroundColor(Color.RED);

        //  set the size and position of flag 4
        FrameLayout.LayoutParams flag4_params = new FrameLayout.LayoutParams(flagWidth, flagHeight);
        flag4_params.setMargins((xOffset + flagWidth), yOffset, 0, 0);
        mFlag4.setLayoutParams(flag4_params);
        mFlag4.setPadding(4,4,4,4);
        mFlag4.setBackgroundColor(Color.BLUE);


        //  the select region text view is positioned immeditely below the images
        //  and above the buttons.
        yOffset += flagHeight;

        //  the select regione text view
        FrameLayout.LayoutParams selectRegionParams = new FrameLayout.LayoutParams(txtViewWidth,
                txtViewHeight, Gravity.CENTER_HORIZONTAL);
        selectRegionParams.setMargins(xOffset, yOffset, 0, 0);
        mRegion_textView.setLayoutParams(selectRegionParams);
        mRegion_textView.setText("select region place holder");


        //  the first row of buttons are positioned immeditely below the images
        //  in additon to some offset values
        yOffset += flagHeight / 2;

        //  set the size and position of the first button
        FrameLayout.LayoutParams but1_params = new FrameLayout.LayoutParams(buttonWidth, buttonHeight);
        but1_params.setMargins(xOffset, yOffset, 0, 0);
        btn1.setLayoutParams(but1_params);
        btn1.setPadding(4,4,4,4);
        btn1.setText("Sample-Africa");

        //  set the size and position of the second button
        FrameLayout.LayoutParams but2_params = new FrameLayout.LayoutParams(buttonWidth, buttonHeight);
        but2_params.setMargins((xOffset + buttonWidth), yOffset, 0, 0);
        btn2.setLayoutParams(but2_params);
        btn2.setPadding(4,4,4,4);
        btn2.setText("Sample-America");

        //  the second row of buttons are positioned immeditely below the first row of buttons
        //  in additon to some offset values
        yOffset += buttonHeight;

        //  set the size and position of the third button
        FrameLayout.LayoutParams but3_params = new FrameLayout.LayoutParams(buttonWidth, buttonHeight);
        but3_params.setMargins(xOffset, yOffset, 0, 0);
        btn3.setLayoutParams(but3_params);
        btn3.setPadding(4,4,4,4);
        btn3.setText("Europe");

        //  set the size and position of the fourth button
        FrameLayout.LayoutParams but4_params = new FrameLayout.LayoutParams(buttonWidth, buttonHeight);
        but4_params.setMargins((xOffset + buttonWidth), yOffset, 0, 0);
        btn4.setLayoutParams(but4_params);
        btn4.setPadding(4,4,4,4);
        btn4.setText("Nigeria");





    }

}
