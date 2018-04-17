package calculateBillChart;

public class Item {
	private String itemId;
	private String itemType;
	private float itemPrice;
	private float itemTotalPrice;
	private float itemQty;
	public Item() {
		// TODO Auto-generated constructor stub
		
	}
	
	public float getItemTotalPrice() {
		return itemTotalPrice;
	}

	public void setItemTotalPrice(float itemTotalPrice) {
		this.itemTotalPrice = itemTotalPrice;
	}

	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public float getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(float itemPrice) {
		this.itemPrice = itemPrice;
	}
	public float getItemQty() {
		return itemQty;
	}
	public void setItemQty(float itemQty) {
		this.itemQty = itemQty;
	}
	

}
