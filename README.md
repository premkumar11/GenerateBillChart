# Generate Bill

This simple java Project is used to generating the bill to a customer for his puchased items.

# Prerequisites
Install eclipse and jdk1.8 in your local.

# Create Classes
    # create below java class
    Customer.java--POJO--Having details about a customer
    Item.java---POJO--Having details about an Item
    MainBill.java---Main Class
    - The MainBill.java have bill details and list of customer and list of items
    - Instead of DB maitain List<Customer> and List<item>
  
  calculateBill(String billNo, String customerId, List<Item> items, float grossTotal, float netAmount){
    // Method is used for calculate the amount and generates the bill summary.
    // Here the billNo, customerId and List<Item> are get as inputs 
  }
   List<Item> calTotalAmount(int discount, List<Item> items) {
    //calculate the total amout for an item and return the updated item list
  }
  boolean compareDate(Customer customer) {
  //Method is used for identify the user has been a cutomer over2 years
  }
  List<Item> addNewCustomer(String customerId, List<Item> items) {
  //If the user is new to the shop, add that user into List<Customer> and also update the List<Item> for the customer.
  }
  
  findTotal(List<Item> itemList, float grossTotal, float netAmount) {
  //Calculate the grossTotal and netTotal for a bill
  }
  
  main(){
  //get the user input by using Scanner sc = new Scanner(System.in);
  // call the calculateBill(billNo, customerId, items, grossTotal, netAmount)

  }
  
  
# Execution
Right clcick the main class and run as java application. We will get the output like below.

# Sample inputs:
Entered the applicaton
Enter Bill No: sdgs22
Enter Customer No: c3
Enter integers please (EOF or non-integer to terminate): 
Enter Number of items: 2
Enter item id: asf
Enter item type: groceries
Enter item price: 124
Enter item qty: 1
Press Enter
Enter item id: 1222fff
Enter item type: aaaaa
Enter item price: 1547
Enter item qty: 2
Press EnterWed Apr 18 01:41:05 IST 2018
oldCustomerFlag:true



# Sample Output:

*******BILL SUMMARY*******

(Except Groceries, item total price calculated with 5% discount)

Bill No: sdgs22
Customer Id: c3
Date: 04/18/2018


List Of Items:
Item Id: asf|Item Type: groceries|Item Qty: 1.0|Item Price: 124.0|Item Total Price: 124.0
Item Id: 1222fff|Item Type: aaaaa|Item Qty: 2.0|Item Price: 1547.0|Item Total Price: 2939.3


Gross Total: 3063.3
Net Amount: 2913.3
(For every Rs.100 on the Gross Total, there would be a 5% discount)



