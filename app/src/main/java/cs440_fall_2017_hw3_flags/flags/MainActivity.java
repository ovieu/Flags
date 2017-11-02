package cs440_fall_2017_hw3_flags.flags;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    /* keeps track of the number of continents selected */
    int mSelectedContinentCount = 0;

    /* stores the names of the selected continent */
    ArrayList<String> mSelectedContinents = new ArrayList<>();

    /* alert dialog for selecting the continents */
    AlertDialog mSelectContinent_Dialog = null;

    /* list of continents in the asset folder */
    private String[] assetContinents = null;

    /* used to identify the request to send list of continent to game activity */
    private static final int SEND_CONTINENT_LIST_REQUEST = 1000;

    //  create a local array list to hold the list of continents
    ArrayList<String> continents = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //  launch the continent selection menu
        //  if the user is restarting a new game
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            boolean playNewGame = extras.getBoolean("isNewGame");
            if (playNewGame) {
                launchContinentSelector();
            }
        }

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
        Log.d("DialogMonitor", "Create the dialog here");
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

                    //create the intent to move to the next activity
                    Intent intent = new Intent(MainActivity.this,FlagActivity.class);

                    //save the selected continent in a list and send it to the next activity
                    // delete --> this is just to show the contents of the selection
                    System.out.println(mSelectedContinents.toString());

                    //1. get list of continents from the asset folder
                    getAssetContinents();

                    //2. add the continents to a put extra and send to
                    //   game activity
                    intent.putExtra("continents", continents);

                    //3. start the activity for result
                    startActivityForResult(intent, SEND_CONTINENT_LIST_REQUEST);


                }

                //  reset the selected conitinent count to zero
                mSelectedContinentCount = 0;
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

    /*
        This methods goes through the asset folder and gets all
        the continents listed there
     */
    private void getAssetContinents() {
        //  use the asset manager to get reference to all the continents
        //  in the asset folder
        AssetManager assets = getAssets();

        try {
            //  since there is no straight forward way to get the
            //  list of subdirectories in the asset folder
            //  a general directory was made and all the continents
            //  were included as subdirectories. That way, assets.list
            //  would return the list of all subdirectories
            assetContinents = assets.list("asset_continents");

            //  create a local array list to hold the list of continents
            continents = new ArrayList<>();

            Log.d("AssetContents", Arrays.toString(assetContinents));
            for (String index : mSelectedContinents) {
                String sContinents = assetContinents[Integer.parseInt(index)];
                continents.add(sContinents);
            }

            //  test the list of continents to see if it display
            //  the selected list
            Log.d("Send", Arrays.toString(continents.toArray()));

        } catch (Exception io) {
            //  if the asset manager has no content, print no content
        }

    }


}


