package com.universalpos;
import databases.InventoryDatabase;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	 
	SQLiteDatabase sqliteDB;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		InventoryDatabase inventoryDatabase = new InventoryDatabase(this);
		inventoryDatabase.getWritableDatabase(); // First method
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void goToInventory(View v)
	{
		Intent inventoryintent = new Intent(getApplicationContext(), Inventory.class); //go to inventory page
		startActivity(inventoryintent);
	}
	public void goToCustomer(View v)
	{
		 Intent customerIntent = new Intent(getApplicationContext(), Customer.class); //go to customer page
		startActivity(customerIntent);
	}
	public void goToHistory(View v)
	{
		Intent historyIntent = new Intent(getApplicationContext(), History.class); //go to history page
		startActivity(historyIntent);
	}
	public void goToSale(View v)
	{
		Intent saleIntent = new Intent(getApplicationContext(), Sale.class); //go to sale page
		startActivity(saleIntent);
	}

}
