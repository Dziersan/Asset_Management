package com.example.asset_management.deviceCard;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.asset_management.connection.Connection;
import com.example.asset_management.deviceCard.ui.card.CardFragment;

import com.example.asset_management.login.UserInfo;
import com.example.asset_management.mainHub.MainHubActivity;
import com.example.asset_management.recycleViewDeviceList.Device;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.asset_management.R;

import java.io.File;

/**
 * DeviceCardActivity
 * <p>
 *     Version 1.0
 * </p>
 * 30.08.2020
 * AUTHOR: Dominik Dziersan
 */
public class DeviceCardActivity extends AppCompatActivity implements
         CardFragment.FragmentDeviceCardListener{
    public boolean onOffSwitch = false;
    public String fileName = "Switch.json";
    private String clickedCalendar;
    private String currentDateString;
    private boolean isOldDevice;
    @Override
    public void onInputASent(String input) {
        clickedCalendar = input;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String isOldVersion = getString(R.string.isOldVersion);
        try {
            isOldDevice = (Boolean)intent.getSerializableExtra(isOldVersion);
        } catch (Exception e) {
            isOldDevice = false;
        }

        AppBarConfiguration appBarConfiguration;
        BottomNavigationView navView;
        NavController navController;

        if(!isOldDevice){
            setContentView(R.layout.activity_device_card);
            navView = findViewById(R.id.nav_view);
            Toolbar toolbar = findViewById(R.id.toolbardevicecard);
            setSupportActionBar(toolbar);

            appBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.navigation_device, R.id.navigation_reservation, R.id.navigation_map)
                    .build();
            navController = Navigation.findNavController(this, R.id.nav_host_fragment);
            NavigationUI.setupActionBarWithNavController(this, navController,
                    appBarConfiguration);
        } else {
            setContentView(R.layout.activity_device_card_old_versions);
            navView = findViewById(R.id.nav_view_oldversion);
            Toolbar toolbar = findViewById(R.id.toolbardevicecard);
            setSupportActionBar(toolbar);

            appBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.navigation_device, R.id.navigation_reservation, R.id.navigation_map)
                    .build();
            navController = Navigation.findNavController(this,
                    R.id.nav_host_fragment_old_devicecard);
//            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        }

        NavigationUI.setupWithNavController(navView, navController);

        File file = this.getFileStreamPath(fileName);

        if(file == null || !file.exists()){
            file = new File(this.getFilesDir(),fileName);
            SwitchEditable switchEditable = new SwitchEditable(onOffSwitch);
            SwitchEditable.createSwitch(switchEditable,this);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        UserInfo user = MainHubActivity.getUser();
        if(user.intToBool(user.getDeleteDevice())
        && user.intToBool(user.getEditDevice())){
            getMenuInflater().inflate(R.menu.menu_devicecard_all, menu);
            return true;
        }
        if(user.intToBool(user.getDeleteDevice())){
            getMenuInflater().inflate(R.menu.menu_devicecard_delete, menu);
            return true;
        }
        if(user.intToBool(user.getEditDevice())){
            getMenuInflater().inflate(R.menu.menu_devicecard_edit, menu);
            return true;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            SwitchEditable switchEditable = new SwitchEditable(true);
            SwitchEditable.createSwitch(switchEditable,getApplicationContext());
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);
            return true;
        }
        if (id == R.id.action_history) {
        Connection connection = new Connection();
        connection.getDeviceOldVersion(getDevice().getInventoryNumberInt(), this);

        Intent intent = new Intent (this, DeviceCardOldVersionsListActivity.class);
        startActivity(intent);

            return true;
        }

        if(id == R.id.action_delete){
            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.deleteDeviceTitle))
                    .setMessage(getString(R.string.deleteDeviceText))
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Connection connection = new Connection();
                            connection.deleteDevice(getDevice(),getApplicationContext());
                            finish();
                        }
                    })
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        return super.onOptionsItemSelected(item);
    }

    public Device getDevice(){
        String deviceName = getString(R.string.deviceName);
        Intent intent = getIntent();
        return (Device)intent.getSerializableExtra(deviceName);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
        super.onRestart();
    }

    /**
     * restarts the activity with the saved intent
     */
    public void refreshUI() {
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }

    @Override
    public void onBackPressed() {
        onOffSwitch = SwitchEditable.isClicked(getApplicationContext());
        if(onOffSwitch){
            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.discardTitle))
                    .setMessage(getString(R.string.discardText))
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            SwitchEditable switchEditable = new SwitchEditable(false);
                            SwitchEditable.createSwitch(switchEditable,getApplicationContext());
                            finish();
                        }
                    })
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        } else {
            finish();
        }
    }

    public String getCurrentDateString(){
        return currentDateString;
    }
}