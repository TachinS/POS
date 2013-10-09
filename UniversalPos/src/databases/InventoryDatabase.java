package databases;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.universalpos.Inventory;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class InventoryDatabase extends SQLiteOpenHelper
{
	private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "PosDatabases";
    private static final String TABLE_INVENTORY = "inventory";
	public InventoryDatabase(Context context) 
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase db) 
	{
	    db.execSQL("CREATE TABLE " + TABLE_INVENTORY + 
	    		"(ItemID INTEGER PRIMARY KEY ," +
	    		" ItemName TEXT(100),"+
	    		" BuyPrice REAL,"+
	    		" SellPrice REAL,"+
	    		" Date TEXT(100)," +
	    		" Describe TEXT(100))");
	    Log.d("CREATE TABLE INVENTORY","Create Table Successfully.");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{	
	}
	
	@SuppressLint("SimpleDateFormat")
	public long InsertData(String itemID,String itemName, double buyPrice, double sellPrice,String describe) 
	{
		 try 
		 {
			   Date now = new Date();
			   String format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z").format(now);
			   SQLiteDatabase db;
	    	   db = this.getWritableDatabase(); // Write Data
	    	   ContentValues Val = new ContentValues();
	    	   Val.put("ItemID", itemID); 
	    	   Val.put("ItemName", itemName);
	    	   Val.put("BuyPrice",buyPrice);
	    	   Val.put("SellPrice",sellPrice);
	    	   Val.put("Date", format);
	    	   Val.put("Describe", describe);
			long rows = db.insert(TABLE_INVENTORY, null, Val);
			db.close();
			return rows; // return rows inserted.
		 } 
		 catch (Exception e) 
		 {
		    return -1;
		 }

	}

	public String[] SelectData(String selectID) 
	{
		 try 
		 {
			String arrData[] = null;	
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			 Cursor cursor = db.query(TABLE_INVENTORY, new String[] { "*" }, 
					 	"ItemID=?",
			            new String[] { String.valueOf(selectID) }, null, null, null, null);
			 
			 	if(cursor != null)
			 	{
					if (cursor.moveToFirst()) {
						arrData = new String[cursor.getColumnCount()];
						/***
						 *  0 = id
						 *  1 = name
						 *  2 = sell price
						 *  3 = buy price
						 *  4 = registry date
						 *  5 = description of an item
						 */
						arrData[0] = cursor.getString(0);
						arrData[1] = cursor.getString(1);
						arrData[2] = cursor.getString(2);
						arrData[3] = cursor.getString(3);
						arrData[4] = cursor.getString(4);
						arrData[5] = cursor.getString(5);
					}
			 	}
			 	cursor.close();
				db.close();
				return arrData;
				
		 } catch (Exception e) 
		 {
		    return null;
		 }

	}
	
	// Select All Data
	public String[] SelectAllData() {
		// TODO Auto-generated method stub
		
		 try {
			String arrData[][] = null;	
			String data[] = null;
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			 String strSQL = "SELECT  * FROM " + TABLE_INVENTORY;
			 Cursor cursor = db.rawQuery(strSQL, null);
			 
			 	if(cursor != null)
			 	{
					if (cursor.moveToFirst()) {
						arrData = new String[cursor.getCount()][cursor.getColumnCount()];
						data = new String[cursor.getCount()];
						/***
						 *  [x][0] = ID
						 *  [x][1] = Name
						 *  [x][2] = Buy Price
						 *  [x][3] = Sell Price
						 *  [x][4] = Date
						 *  [x][5] = Description
						 */
						int i= 0;
						do {				
							arrData[i][0] = cursor.getString(0);
							arrData[i][1] = cursor.getString(1);
							arrData[i][2] = cursor.getString(3);
							arrData[i][3] = cursor.getString(5);
							data[i] = "ID:"+cursor.getString(0)+"\t"+cursor.getString(1)+"\tPrice:"+cursor.getString(3);
							i++;
						} while (cursor.moveToNext());						

					}
			 	}
			 	cursor.close();
				
				return data;//arrData;
				
		 } catch (Exception e) {
		    return null;
		 }

	}

	/*public String[] SelectAllData() //replace List<sMembers> with String[]
	{
		 try 
		 {
			 String[] data = new String[10000];
			 int pointer = 0;
			 List<sMembers> MemberList = new ArrayList<sMembers>();
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			 String strSQL = "SELECT  * FROM " + TABLE_INVENTORY;
			 Cursor cursor = db.rawQuery(strSQL, null);
			 
			 	if(cursor != null)
			 	{
			 	    if (cursor.moveToFirst()) 
			 	    {
			 	        do 
			 	        {
			 	        	sMembers cMember = new sMembers();
			 	        	cMember.sItemId(cursor.getString(0));
			 	        	cMember.sItemName(cursor.getString(1));
			 	        	cMember.sBuyPrice(cursor.getString(2));
			 	        	cMember.sSellPrice(cursor.getString(3));
			 	        	cMember.sDate(cursor.getString(4));
			 	        	cMember.sDescribe(cursor.getString(5));
			 	        	MemberList.add(cMember);
			 	        	data[pointer] = cursor.getString(0)+" "+cursor.getString(1)+" "+cursor.getString(3)+" "+cursor.getString(5);
			 	        	pointer++;
			 	        } 
			 	        while (cursor.moveToNext());
			 	    }
			 	}
			 	cursor.close();
				db.close();
				return data;
		 } 
		 catch (Exception e) 
		 {
		    return null;
		 }
	}*/
	
	// Update Data
	public long UpdateData(String itemId,String itemName,double buyPrice,double sellPrice,String date,String describe) 
	{
		 try {
			
			SQLiteDatabase db;
	    		db = this.getWritableDatabase(); // Write Data
	    		/**
	    		 *  for API 11 and above
			SQLiteStatement insertCmd;
			String strSQL = "UPDATE " + TABLE_MEMBER
					+ " SET Name = ? "
					+ " , Tel = ? "
					+ " WHERE MemberID = ? ";
			
			insertCmd = db.compileStatement(strSQL);
			insertCmd.bindString(1, strName);
			insertCmd.bindString(2, strTel);
			insertCmd.bindString(3, strMemberID);
				
			return insertCmd.executeUpdateDelete();
			*
			*/
	           ContentValues Val = new ContentValues();
	           Val.put("ItemID", itemId);
	           Val.put("ItemName", itemName);
	           Val.put("BuyPrice", buyPrice);
	           Val.put("SellPrice", sellPrice);
	           Val.put("Date", date);
	           Val.put("Describe", describe);
	    
	           long rows = db.update(TABLE_INVENTORY, Val, " ItemID = ?",
	                   new String[] { String.valueOf(itemId) });
			db.close();
			return rows; // return rows updated.

		 } catch (Exception e) {
		    return -1;
		 }
	}
	
	public long DeleteData(String itemID) 
	{
		 try 
		 {
			SQLiteDatabase db;
	    		db = this.getWritableDatabase(); // Write Data	    		
	    		/**
	    		 * for API 11 and above
			SQLiteStatement insertCmd;
			String strSQL = "DELETE FROM " + TABLE_MEMBER
					+ " WHERE MemberID = ? ";
			
			insertCmd = db.compileStatement(strSQL);
			insertCmd.bindString(1, strMemberID);
			
			return insertCmd.executeUpdateDelete();
			*
			*/
			long rows =  db.delete(TABLE_INVENTORY, "ItemID = ?",
		            new String[] { String.valueOf(itemID) });
			db.close();
			return rows; // return rows delete.

		 }
		 catch (Exception e) 
		 {
		    return -1;
		 }

	}
	
}
