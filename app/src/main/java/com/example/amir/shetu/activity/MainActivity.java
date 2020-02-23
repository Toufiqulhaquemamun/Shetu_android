package com.example.amir.shetu.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.amir.shetu.R;
import com.example.amir.shetu.fragment.AgentBuyerFragment;
import com.example.amir.shetu.fragment.AgentComissionFragment;
import com.example.amir.shetu.fragment.AgentSellFragment;
import com.example.amir.shetu.fragment.AssignProductListFragment;
import com.example.amir.shetu.fragment.DeliverableFragment;
import com.example.amir.shetu.fragment.HomeFragment;
import com.example.amir.shetu.fragment.LoanApplyFragment;
import com.example.amir.shetu.fragment.PreOrderPurchaseBidsFragment;
import com.example.amir.shetu.fragment.PreOrderSaleMarketingFragment;
import com.example.amir.shetu.fragment.PreorderFragment;
import com.example.amir.shetu.fragment.ProductGradeFragment;
import com.example.amir.shetu.fragment.ProfileFragment;
import com.example.amir.shetu.fragment.PurchaseBidsFragment;
import com.example.amir.shetu.fragment.PurchaseProductFragment;
import com.example.amir.shetu.fragment.ReceiveableFragment;
import com.example.amir.shetu.fragment.SMEListFragment;
import com.example.amir.shetu.fragment.SaleBidsFragment;
import com.example.amir.shetu.fragment.SaleMarketingFragment;
import com.example.amir.shetu.fragment.SoldProductFragment;
import com.example.amir.shetu.fragment.TradelistFragment;
import com.example.amir.shetu.manager.PreferenceManager;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseMessaging.getInstance().setAutoInitEnabled(true);

        initializeNavDrawer();
    }


    private void initializeNavDrawer() {

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        final DrawerLayout drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(

                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);

        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);

        MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, HomeFragment.newInstance()).commit();

        if(PreferenceManager.getInstance().getString(PreferenceManager.TOKEN)!=null){
            if(PreferenceManager.getInstance().getString(PreferenceManager.TYPE).equals("Agent")){
                Menu menu=navigationView.getMenu();
                menu.clear();
                getMenuInflater().inflate(R.menu.activity_main_agent_drawer,menu);
            }
            else if(PreferenceManager.getInstance().getString(PreferenceManager.TYPE).equals("Guest"))
            {
                Menu menu=navigationView.getMenu();
                menu.clear();
                getMenuInflater().inflate(R.menu.activity_main_guest_drawer,menu);
            }

            navigationView.setCheckedItem(R.id.nav_home);

        }else {


            Menu menu=navigationView.getMenu();
            menu.clear();

            getMenuInflater().inflate(R.menu.activity_main_login_drawer,menu);

        }

        drawer.closeDrawer(GravityCompat.START);
        navigationView.setNavigationItemSelectedListener(this);

    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.login) {

            startActivity(new Intent(this,LoginActivity.class));

        }else if (id == R.id.nav_sale) {

            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, SaleMarketingFragment.newInstance()).addToBackStack(null).commit();

        } else if (id == R.id.nav_product_information) {

            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, AssignProductListFragment.newInstance()).addToBackStack(null).commit();

        }else if (id == R.id.nav_product_information_agent) {

            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, AssignProductListFragment.newInstance()).addToBackStack(null).commit();
        }

        else if (id == R.id.nav_profile) {

            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, ProfileFragment.newInstance()).addToBackStack(null).commit();

        } else if (id == R.id.nav_sale_bids) {

            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, SaleBidsFragment.newInstance()).addToBackStack(null).commit();

        }
        else if (id == R.id.nav_sale_bids_agent) {

            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, SaleBidsFragment.newInstance()).addToBackStack(null).commit();

        }else if (id == R.id.nav_sold_product) {

            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, SoldProductFragment.newInstance()).addToBackStack(null).commit();
        }
        else if (id == R.id.nav_sold_product_agent) {

            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, SoldProductFragment.newInstance()).addToBackStack(null).commit();
        }
        else if (id == R.id.nav_purchase_bids) {

            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, PurchaseBidsFragment.newInstance()).addToBackStack(null).commit();

        }
        else if (id == R.id.nav_purchase_bids_agent) {

            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, PurchaseBidsFragment.newInstance()).addToBackStack(null).commit();

        }
        else if (id == R.id.nav_purchase_product) {

            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, PurchaseProductFragment.newInstance()).addToBackStack(null).commit();

        }
        else if (id == R.id.nav_purchase_product_agent) {

            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, PurchaseProductFragment.newInstance()).addToBackStack(null).commit();

        }
        else if(id==R.id.sme_list_agent){
            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.container_layout,SMEListFragment.newInstance()).addToBackStack(null).commit();

        }
        else if(id==R.id.trade_list){
            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, TradelistFragment.newInstance()).addToBackStack(null).commit();

        }
        else if(id==R.id.nav_product_standard){
            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, ProductGradeFragment.newInstance()).addToBackStack(null).commit();

        }
        else if(id==R.id.installment){
            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, LoanApplyFragment.newInstance()).addToBackStack(null).commit();

        }
        else if(id==R.id.receiveable_list){
            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, ReceiveableFragment.newInstance()).addToBackStack(null).commit();

        }
        else if(id==R.id.deliverable_list){
            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, DeliverableFragment.newInstance()).addToBackStack(null).commit();

        }
        else if(id==R.id.sold_product_list){
            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, AgentSellFragment.newInstance()).addToBackStack(null).commit();

        }
        else if(id==R.id.buy_product_list){
            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, AgentBuyerFragment.newInstance()).addToBackStack(null).commit();

        }
        else if(id==R.id.comission_list){
            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, AgentComissionFragment.newInstance()).addToBackStack(null).commit();

        }
        else if(id==R.id.nav_preorder_bid){
            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, PreorderFragment.newInstance()).addToBackStack(null).commit();

        }else if(id==R.id.nav_add_preorder_bid){
            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, PreOrderSaleMarketingFragment.newInstance()).addToBackStack(null).commit();

        }
        else if(id==R.id.nav_preorder_bids){
            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, PreOrderPurchaseBidsFragment.newInstance()).addToBackStack(null).commit();

        }
        else if (id == R.id.login) {

            startActivity(new Intent(MainActivity.this,LoginActivity.class));

        } else if (id == R.id.nav_logout) {

            PreferenceManager.getInstance().setString(PreferenceManager.TOKEN,null);
            PreferenceManager.getInstance().setString(PreferenceManager.TOTAL_Agent_Share,null);

            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);

            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            startActivity(intent);

        } else {

            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, HomeFragment.newInstance()).addToBackStack(null).commit();

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }


    public void onBackPressed() {

        new AlertDialog.Builder(this)
                .setTitle("Exit")
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        android.os.Process.killProcess(android.os.Process.myPid());
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

}