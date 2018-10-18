package com.example.neto.exemplomenuaction;

import android.graphics.Color;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ListAdapter.OnItemClick{

    private Toolbar toolbar;
    private int itemPositionSelected;
    private ListAdapter listAdapter;
    private View itemView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.toolbar = findViewById(R.id.my_toolbar);
        this.itemPositionSelected=-1;
        setSupportActionBar(toolbar);

        this.listAdapter = (ListAdapter) getSupportFragmentManager().getFragments().get(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menuitem_init, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.menuitem_add:
                MyEditDialog.show(getSupportFragmentManager(), new MyEditDialog.OnTextListener() {
                    @Override
                    public void onSetText(String text) {
                        if(!text.isEmpty()) {
                            listAdapter.add(text);
                        }
                    }
                });
                return true;
            case R.id.menuitem_edit:

                MyEditDialog.showWithValue(getSupportFragmentManager(), new MyEditDialog.OnTextListener() {
                    @Override
                    public void onSetText(String text) {
                        if(!text.isEmpty()) {
                            listAdapter.insert(itemPositionSelected,text);
                        }
                    }
                }, listAdapter.get(itemPositionSelected));

                return true;
            case R.id.menuitem_delete:

                this.listAdapter.delete(itemPositionSelected);
                this.itemPositionSelected =-1;
                this.toolbar.getMenu().clear();
                getMenuInflater().inflate(R.menu.menuitem_init,this.toolbar.getMenu());
                this.itemView.setBackgroundColor(Color.WHITE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onClick(String item,View iv, int position) {

        if(this.itemPositionSelected==-1){
            this.itemPositionSelected = position;
            this.toolbar.getMenu().clear();
            getMenuInflater().inflate(R.menu.menuitem_edit,this.toolbar.getMenu());
            iv.setBackgroundColor(Color.LTGRAY);
            this.itemView = iv;
        }else{
            if(this.itemPositionSelected == position){
                this.itemPositionSelected = -1;
                this.toolbar.getMenu().clear();
                getMenuInflater().inflate(R.menu.menuitem_init,this.toolbar.getMenu());
                iv.setBackgroundColor(Color.TRANSPARENT);
                this.itemView = iv;
            }
        }

    }
}
