package cs440_fall_2017_hw3_flags.flags;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
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

}
