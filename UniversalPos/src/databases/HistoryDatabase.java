package databases;
import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
public class HistoryDatabase extends SQLiteOpenHelper
{
	private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "PosDatabases";
    private static final String TABLE_HISTORY = "history";
    
	public HistoryDatabase(Context context) 
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) 
	{
	    db.execSQL("CREATE TABLE " + TABLE_HISTORY + 
	    		"(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
	    		"ItemID TEXT(100),"+
	    		" ItemName TEXT(100)," +
	    		" CustomerName TEXT(100),"+
	    		" SellPrice REAL,"+
	    		" Date TEXT(100)," +
	    		"Describe TEXT(100)");
	    Log.d("CREATE TABLE HISTORY","Create Table Successfully.");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{	
	}
	
	public long InsertData(String id,String itemId, String itemName, String customerName, double sellPrice, String date,String describe) 
	{
		 try 
		 {
			   SQLiteDatabase db;
	    	   db = this.getWritableDatabase(); // Write Data
	    	   ContentValues Val = new ContentValues();
	    	   Val.put("ID",id);
	    	   Val.put("ItemID", itemId); 
	    	   Val.put("ItemName", itemName);
	    	   Val.put("CustomerName", customerName);
	    	   Val.put("SellPrice", sellPrice);
	    	   Val.put("Date", date);
	    	   Val.put("Describe", describe);
			long rows = db.insert(TABLE_HISTORY, null, Val);
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
				
			 Cursor cursor = db.query(TABLE_HISTORY, new String[] { "*" }, 
					 	"ID=?",
			            new String[] { String.valueOf(selectID) }, null, null, null, null);
			 
			 	if(cursor != null)
			 	{
					if (cursor.moveToFirst()) {
						arrData = new String[cursor.getColumnCount()];
						/***
						 *  0 = id
						 *  1 = item id
						 *  2 = item name
						 *  3 = customer name
						 *  4 = sell price
						 *  5 = registry date
						 *  6 = description of an item
						 */
						arrData[0] = cursor.getString(0);
						arrData[1] = cursor.getString(1);
						arrData[2] = cursor.getString(2);
						arrData[3] = cursor.getString(3);
						arrData[4] = cursor.getString(4);
						arrData[5] = cursor.getString(5);
						arrData[6] = cursor.getString(6);
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
	
	//inner class to collect the data
	public class sMembers 
	{
		String _ID,_ItemID, _ItemName, _CustomerName,_Date,_Sellprice,_Describe;
		
		// Set method
		public void sID(String vID){
	        this._ID = vID;
	    }
		public void sItemId(String vItemID){
	        this._ItemID = vItemID;
	    }
		public void sItemName(String vItemName){
	        this._ItemName = vItemName;
	    }
		public void sCustomerName(String vCustomerName){
	        this._CustomerName = vCustomerName;
	    }	
		public void sSellPrice(String vSellPrice){
	        this._Sellprice = vSellPrice;
	    }	
		public void sDate(String vDate){
	        this._Date = vDate;
	    }
		public void sDescribe(String vDescribe){
	        this._Describe = vDescribe;
	    }	
		
		// Get method
		public String gID(){
	        return _ID;
	    }
		public String gItemId(){
	        return _ItemID;
	    }
		public String gItemName(){
			 return _ItemName;
	    }
		public String gCustomerName(){
	        return _CustomerName;
	    }	
		public String gSellPrice(){
	        return _Sellprice;
	    }	
		public String gDate(){
	        return _Date;
	    }	
		public String gDescribe(){
	        return _Describe;
	    }	
	}

	public List<sMembers> SelectAllData() 
	{
		 try 
		 {
			 List<sMembers> MemberList = new ArrayList<sMembers>();
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			 String strSQL = "SELECT  * FROM " + TABLE_HISTORY;
			 Cursor cursor = db.rawQuery(strSQL, null);
			 
			 	if(cursor != null)
			 	{
			 	    if (cursor.moveToFirst()) 
			 	    {
			 	        do 
			 	        {
			 	        	sMembers cMember = new sMembers();
			 	        	cMember.sID(cursor.getString(0));
			 	        	cMember.sItemId(cursor.getString(1));
			 	        	cMember.sItemName(cursor.getString(2));
			 	        	cMember.sCustomerName(cursor.getString(3));
			 	        	cMember.sSellPrice(cursor.getString(4));
			 	        	cMember.sDate(cursor.getString(5));
			 	        	cMember.sDescribe(cursor.getString(6));
			 	        	MemberList.add(cMember);
			 	        } 
			 	        while (cursor.moveToNext());
			 	    }
			 	}
			 	cursor.close();
				db.close();
				return MemberList;
		 } 
		 catch (Exception e) 
		 {
		    return null;
		 }
	}
	
	// Update Data
	public long UpdateData(String id,String itemId,String itemName,String customerName,double sellPrice,String date,String describe) 
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
	           Val.put("ID",id);
	           Val.put("ItemID", itemId);
	           Val.put("ItemName", itemName);
	           Val.put("CustomerName", customerName);
	           Val.put("SellPrice", sellPrice);
	           Val.put("Date", date);
	           Val.put("Describe", describe);
	    
	           long rows = db.update(TABLE_HISTORY, Val, " ID = ?",
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
			long rows =  db.delete(TABLE_HISTORY, "ID = ?",
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
