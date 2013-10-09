package controller;
import android.content.Context;
import model.Inventory;

public class InventoryController 
{
	private Inventory inventory;
	public InventoryController(Context context) 
	{
		inventory = new Inventory(context);
	}
	public long insert(String itemID,String itemName, String buyPrice,String sellPrice, String itemDescribe) 
	{	
		return inventory.insert(itemID,itemName,buyPrice,sellPrice,itemDescribe);
	}
	public String[] selectAll()
	{
		return inventory.selectAll();
	}

}
