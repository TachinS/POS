package com.universalpos;
import controller.InventoryController;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
public class Inventory extends Activity 
{
	 private ListView m_listview ;//= (ListView) findViewById(R.id.listView1);
	 private InventoryController inventoryController = new InventoryController(this);
	public void goToAddItem(View v)
	{
		Intent inventoryintent = new Intent(getApplicationContext(), AddInventory.class); //go to inventory page
		startActivity(inventoryintent);
	}
	public void refresher(View v)
	{
		m_listview = (ListView) findViewById(R.id.listView1);
	    String[] items = inventoryController.selectAll();
	    ArrayAdapter<String> adapter =
	    new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
	    m_listview.setAdapter(adapter);
	    
	}
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3)
	{
		Toast.makeText(Inventory.this, position+" here!", 1000);
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inventory);
	    m_listview = (ListView) findViewById(R.id.listView1);
	    String[] items = inventoryController.selectAll();
	    //String[] items = new String[] {"No item !","item2","item3"};
	    ArrayAdapter<String> adapter =
	    new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
	    m_listview.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.inventory, menu);
		return true;
	}
	
}
