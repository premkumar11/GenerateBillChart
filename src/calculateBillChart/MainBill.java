package calculateBillChart;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MainBill {
	
	private String billNo;
	private String customerId;
	private float grossTotal;
	private float netTotal;
	private List<String> listOfItemIds;
	
	private List<Item> listOfItems;
	private List<Customer> listOfCustomers;
	
	

	public float getGrossTotal() {
		return grossTotal;
	}

	public void setGrossTotal(float grossTotal) {
		this.grossTotal = grossTotal;
	}

	public float getNetTotal() {
		return netTotal;
	}

	public void setNetTotal(float netTotal) {
		this.netTotal = netTotal;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public List<String> getListOfItemIds() {
		return listOfItemIds;
	}

	public void setListOfItemIds(List<String> listOfItemIds) {
		this.listOfItemIds = listOfItemIds;
	}

	public List<Item> getListOfItems() {
		return listOfItems;
	}

	public void setListOfItems(List<Item> listOfItems) {
		this.listOfItems = listOfItems;
	}

	public List<Customer> getListOfCustomers() {
		return listOfCustomers;
	}

	public void setListOfCustomers(List<Customer> listOfCustomers) {
		this.listOfCustomers = listOfCustomers;
	}

	public MainBill() {
		// TODO Auto-generated constructor stub
		List<Customer> listOfCustomers = new ArrayList<Customer>();
		Customer cus1 = new Customer();
		cus1.setCustomerId("c1");
		cus1.setCustomerType("Employee");
		cus1.setRegDate(new Date("04/11/2015"));
		listOfCustomers.add(cus1);
		Customer cus2 = new Customer();
		cus2.setCustomerId("c2");
		cus2.setCustomerType("Affiliate");
		cus2.setRegDate(new Date("04/11/2016"));
		listOfCustomers.add(cus2);
		Customer cus3 = new Customer();
		cus3.setCustomerId("c3");
		cus3.setCustomerType("Old Customer");
		cus3.setRegDate(new Date("04/11/2012"));
		listOfCustomers.add(cus3);
		setListOfCustomers(listOfCustomers);
	}
	
	public void calculateBill(String billNo, String customerId, List<Item> items, float grossTotal, float netAmount) {
		boolean customerFlag= false;
		int discount = 0;
		List<Item> itemList = new ArrayList<Item>();
		if(items!=null && !items.isEmpty()) {
			for(Customer customer : listOfCustomers) {
				if(customer.getCustomerId().equals(customerId)) {
					customerFlag = true;
					if(customer.getCustomerType().equalsIgnoreCase("Employee")) {
						discount=30;
						itemList = calTotalAmount(discount, items);
					} else if (customer.getCustomerType().equalsIgnoreCase("Affiliate")) {
						discount=10;
						itemList = calTotalAmount(discount, items);
					} else if (!customer.getCustomerType().equalsIgnoreCase("Employee") && !customer.getCustomerType().equalsIgnoreCase("Affiliate")) {
						boolean oldCustomerFlag = compareDate(customer);
						System.out.println("oldCustomerFlag:"+ oldCustomerFlag+"\n" );
						System.out.println("\n");
						if(oldCustomerFlag) {
							discount=5;
							itemList = calTotalAmount(discount, items);
						} else {
							itemList = calTotalAmount(discount, items);
						}
					} 
					break;
				} 
				
			}
			System.out.println("\n");
			System.out.println("*******BILL SUMMARY*******"+"\n");
			System.out.println("(Except Groceries, item total price calculated with "+ discount+"% discount)"+"\n");
			if(!customerFlag) {
				itemList = addNewCustomer(customerId, items);
			}
			if(!itemList.isEmpty()) {
				
				findTotal(itemList, grossTotal, netAmount);
//				setListOfItems(itemList);
//				setGrossTotal(grossTotal);
//				setNetTotal(netAmount);
				
			}
		}
		System.out.println("Bill No: "+ billNo);
		System.out.print("Customer Id: "+ customerId+"\n");
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		Date billDate= new Date();
		System.out.println("Date: "+format.format(billDate)+"\n");
		System.out.print("\n");
		if(itemList!=null && !itemList.isEmpty()) {
			System.out.print("List Of Items:"+"\n");
			for(Item item : itemList) {
//				System.out.print("Item: "+ item+"\n");
				System.out.print("Item Id: "+ item.getItemId()+"|"+ "Item Type: "+ item.getItemType()+"|"+"Item Qty: "+ item.getItemQty()+"|"+"Item Price: " 
						+ item.getItemPrice()+"|"+"Item Total Price: " + item.getItemTotalPrice() +"\n");
				
			}
		}
		System.out.print("\n");
		System.out.print("\n");
		System.out.print("Gross Total: "+ this.grossTotal+"\n");
		System.out.print("Net Amount: "+ this.netTotal+"\n");
		System.out.println("(For every Rs.100 on the Gross Total, there would be a 5% discount)"+"\n");
		
	}

	public void findTotal(List<Item> itemList, float grossTotal, float netAmount) {
//		Float grossTotal = 0.0f;
		for(Item item : itemList) {
			grossTotal = grossTotal+item.getItemTotalPrice();
		}
		int grossInt = (int)grossTotal;
		float rsDiscout = (grossInt/100)*5;
		netAmount = grossTotal-rsDiscout;
		this.listOfItems=itemList;
		this.grossTotal=grossTotal;
		this.netTotal=netAmount;
	}

	public List<Item> addNewCustomer(String customerId, List<Item> items) {
		List<Item> itemList;
		Customer newCus = new Customer();
		newCus.setCustomerId(customerId);
		newCus.setCustomerType("new Customer");
		newCus.setRegDate(new Date());
		getListOfCustomers().add(newCus);
		itemList = calTotalAmount(0, items);
		return itemList;
	}

	public List<Item> calTotalAmount(int discount, List<Item> items) {
		List<Item> itemList = new ArrayList<Item>();
			for(Item item : items) {
				if(!item.getItemType().equals("groceries")) {
					float itemPrice = item.getItemPrice()-(item.getItemPrice()*(discount/100f));
					item.setItemTotalPrice(itemPrice*item.getItemQty());
					itemList.add(item);
				} else {
					item.setItemTotalPrice(item.getItemPrice()*item.getItemQty());
					itemList.add(item);
				}
			}
			return itemList;
		
	}


	
	public boolean compareDate(Customer customer) {
		String pattern = "MM/dd/yyyy";
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		  Date regDate = customer.getRegDate();		  
		  Date currDate = new Date(); 
		  Calendar calendar = Calendar.getInstance();
		  System.out.println(calendar.getTime());
		  calendar.add(Calendar.YEAR, -2);
		  currDate = calendar.getTime();		  
		  
		  if(regDate.before(currDate) ){
			  
			  return true;
		  } 
		  
		
		return false;
	}


	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Entered the applicaton");
		
		Scanner sc = new Scanner(System.in);
        // String input
		System.out.print("Enter Bill No: ");
        String billNo = sc.nextLine();
        System.out.print("Enter Customer No: ");
        String customerId = sc.nextLine();
        
        List<Item> items = new ArrayList<Item>();
        System.out.print("Enter integers please ");
        System.out.println("(EOF or non-integer to terminate): ");
        System.out.print("Enter Number of items: ");
        int noOfitems = sc.nextInt();
        for(int i=0; i<noOfitems; i++){
        	sc.nextLine();
        	Item item = new Item();
        	System.out.print("Enter item id: ");        	
        	item.setItemId(sc.nextLine());
        	
        	System.out.print("Enter item type: ");
        	item.setItemType(sc.nextLine());
        	
        	System.out.print("Enter item price: ");
        	item.setItemPrice(sc.nextFloat());
        	
        	System.out.print("Enter item qty: ");
        	item.setItemQty(sc.nextInt());   
        	System.out.print("Press Enter");
        	sc.nextLine();
        	items.add(item);
        }
        float grossTotal = 0.0f;
		float netAmount  = 0.0f;
		MainBill mainBill = new MainBill();
		mainBill.calculateBill(billNo, customerId, items, grossTotal, netAmount);
		
		/*System.out.print("Bill No: "+ billNo);
		System.out.print("Customer Id: "+ customerId);
		if(mainBill.getListOfItems()!=null && !mainBill.getListOfItems().isEmpty()) {
			System.out.print("List Of Items:");
			for(Item item : mainBill.getListOfItems()) {
				System.out.print("item: "+ item);
			}
		}
		System.out.print("Gross Total: "+ getGrossTotal());
		System.out.print("Net Amount: "+ mainBill.getNetTotal());*/
		
		
		
		
	}

	
	}


