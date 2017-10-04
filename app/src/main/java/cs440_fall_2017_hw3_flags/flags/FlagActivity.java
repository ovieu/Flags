package cs440_fall_2017_hw3_flags.flags;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

public class FlagActivity extends AppCompatActivity {
    /* list of selected country */
    ArrayList<String> mSelectedContinents = null;

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
    }

}
