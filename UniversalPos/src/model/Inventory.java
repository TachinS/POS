package model;
import databases.InventoryDatabase;
import android.content.Context;
public class Inventory
{
	private InventoryDatabase inventoryDatabase;
	public Inventory(Context context) 
	{
		inventoryDatabase = new InventoryDatabase(context);
	}
	public long insert(String itemID,String itemName, String buyPrice, String sellPrice,String itemDescribe) 
	{
		return inventoryDatabase.InsertData(itemID,itemName, Double.parseDouble(buyPrice), Double.parseDouble(sellPrice),itemDescribe);
	}
	public String[] selectAll()
	{
		return inventoryDatabase.SelectAllData();
	}
}
