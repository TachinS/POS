package com.universalpos;

import controller.InventoryController;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

public class AddInventory extends Activity 
{
	private InventoryController inventoryController;
	public long insertItem(View v)
	{
		inventoryController = new InventoryController((Context)this);
		String itemName,buyPrice,sellPrice,itemDescribe,itemID;
		itemID = ((EditText)findViewById(R.id.itemID_txt)).getText().toString();
		itemName = ((EditText)findViewById(R.id.itemName_txt)).getText().toString();
		buyPrice = ((EditText)findViewById(R.id.buyPrice_txt)).getText().toString();
		sellPrice = ((EditText)findViewById(R.id.sellPrice_txt)).getText().toString();
		itemDescribe = ((EditText)findViewById(R.id.describe_txt)).getText().toString();
		//inventoryController.insert(itemID,itemName,buyPrice,sellPrice,itemDescribe);
		//NEED NULL CASE FIX
		long flg1 = inventoryController.insert(itemID, itemName, buyPrice, sellPrice, itemDescribe);
      	if(flg1 != -1)
      	{
      	 Toast.makeText(AddInventory.this,"Insert Data Successfully",
      			 	Toast.LENGTH_LONG).show(); 
      	}
      	else
      	{
         	 Toast.makeText(AddInventory.this,"Insert Data Failed.",
   			 	Toast.LENGTH_LONG).show(); 
      	}
		((EditText)findViewById(R.id.itemID_txt)).setText("");
		((EditText)findViewById(R.id.itemName_txt)).setText("");
		((EditText)findViewById(R.id.buyPrice_txt)).setText("");
		((EditText)findViewById(R.id.sellPrice_txt)).setText("");
		((EditText)findViewById(R.id.describe_txt)).setText("");
		return 1;
	}
	public void clear(View v)
	{
		((EditText)findViewById(R.id.itemID_txt)).setText("");
		((EditText)findViewById(R.id.itemName_txt)).setText("");
		((EditText)findViewById(R.id.buyPrice_txt)).setText("");
		((EditText)findViewById(R.id.sellPrice_txt)).setText("");
		((EditText)findViewById(R.id.describe_txt)).setText("");
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_inventory);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_inventory, menu);
		return true;
	}


}
