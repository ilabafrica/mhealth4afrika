package h2020.mhealth4afrika.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.github.fcannizzaro.materialstepper.AbstractStep;
import com.github.fcannizzaro.materialstepper.style.TabStepper;

import h2020.mhealth4afrika.R;
import h2020.mhealth4afrika.activities.fragments.steps.setup.Register;
import h2020.mhealth4afrika.activities.fragments.steps.setup.SelectCountry;
import h2020.mhealth4afrika.activities.fragments.steps.setup.Select_District;
import h2020.mhealth4afrika.activities.fragments.steps.setup.Select_Province;


public class SetupActivity extends TabStepper {
    private int i = 1;
    private Context context = SetupActivity.this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        boolean linear = false;

        setErrorTimeout(1500);
        setLinear(linear);
        setTitle("SetUp");
        setAlternativeTab(false);
        setStartPreviousButton();
        setPreviousVisible();




        addStep(createFragment(new SelectCountry()));
        addStep(createFragment(new Select_Province()));
        addStep(createFragment(new Select_District()));
        addStep(createFragment(new Register()) );



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setHomeButtonEnabled(true);

        super.onCreate(savedInstanceState);


    }


    private AbstractStep createFragment(AbstractStep fragment) {
        Bundle b = new Bundle();
        b.putInt("position", i++);
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    public void onBackPressed() {

        // Prompt user to choose what they wish to do
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getResources().getString(R.string.action_go_back));
        builder.setMessage(context.getResources().getString(R.string.sure_to_go_back));

        builder.setPositiveButton(context.getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {


                // Move to Login Activity
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

                finish();
            }
        });

        builder.setNegativeButton(context.getResources().getString(R.string.no), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.setup, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // Go back?
            //onBackPressed();

            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }



}


