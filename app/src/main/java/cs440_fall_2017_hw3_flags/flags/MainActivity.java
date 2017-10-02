package cs440_fall_2017_hw3_flags.flags;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    /* keeps track of the number of continents selected */
    int mSelectedContinentCount = 0;

    /* stores the names of the selected continent */
    ArrayList<String> mSelectedContinents = new ArrayList<>();

    /* alert dialog for selecting the continents */
    AlertDialog mSelectContinent_Dialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /* create and inflate the menu */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.continentmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /* when clicked on the options menu, launches the continent selector */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.continentCategory_Menu:
                launchContinentSelector();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /* launches the continent selector
       and creates the arraylist for tracking the selected continents
     */
    private void launchContinentSelector() {
        Log.d("Ovie", "Create the dialog here");
        // setup the alert dialog builder here
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        //set the title of the dialog here
        builder.setTitle(R.string.dialog_title);

        // specify the list of continents
        builder.setMultiChoiceItems(R.array.continents_list, null,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which,
                                                boolean isChecked) {
                                // disable the start game button at dialog launches
                                // enable the start game after the user selects at least
                                // four buttons
                                if (mSelectContinent_Dialog != null) {
                                    mSelectContinent_Dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                                }

                                // If the user checked the item, add it to the selected items
                                if (isChecked) {
                                    mSelectedContinents.add(""+which);
                                    //increment the selected count
                                    mSelectedContinentCount++;
                                } else {
                                    // Else, remove the item from the list array
                                    mSelectedContinents.remove(""+which);
                                    //decrement the number of items selected
                                    mSelectedContinentCount--;
                                }

                                // enable the start game aka the positive button if the user selects
                                // at least four continents
                                if (mSelectedContinentCount >= 4 && mSelectContinent_Dialog != null) {
                                    mSelectContinent_Dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                                }
                            }
                        });

        // launches the game if the user selects at least four continents
        builder.setPositiveButton(R.string.startGame, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                // start the game activity if the user selected at least four continents
                if (mSelectedContinentCount >= 4) {

                    // create an intent to launch the game activity
                    // also figure out a way to save the selected continents
                    // and send this to the next activity
                }
            }
        });


        // Cancels the selection
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // removes the dialog screen if the user hits cancel

            }
        });


        // create and launch the dialog here
        mSelectContinent_Dialog = builder.create();
        mSelectContinent_Dialog.show();

        // disable the button when the dialog is created
        mSelectContinent_Dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);


    }



}


