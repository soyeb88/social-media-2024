package interview2025;

import java.util.ArrayList;
import java.util.List;


/*
 * Technical Interview Round 1 First Task
 * */

/* 
 * input: 4 
 * output: 1+2+3+4 = 10
 */
class Summation{
	int summation(int num) {
		
		int returnValue = 0; 
		for(int i = 1; i<=num; i++) {
			
			/*unary operator which indicate explicitly the sign
			 * of i become positive furthermore whatever i
			 * positive or negative remain same returnValue 
			 * positive or negative value
			 */
			//returnValue = + i;  
			returnValue += i;
		}
		return returnValue;
	}
}

/*
 * Technical Interview Round 1 Second Task
 * */

/*
 * Products = [
 * 				{"Iphone 11", 1200, 0.25d},
 * 				{"Umidigo", 180, 0.5d},
 * 				"Samsung", 600, 0.1d}
 * 			   ]
 * addProduct Method added a Product and return boolean value
 * 
 * calPrice Method calculate Price and return total price of User
 * 		450.0 = 1200*.25+180*.5+500*.1
 * */
 class Product {
    String name;
    int price;
    double dsc;
 
    
    Product(String name, int price, double dsc) {
        this.name = name;
        this.price = price;
        this.dsc = dsc;
    }
}

class User {
	
	List<Product> addedProduct = new ArrayList<>();
	List<Product> myProducts = new ArrayList<>();
	
	//Added A Product from the Object
	boolean addProduct(Product product) {
		addedProduct.add(product);
		return addedProduct.size()>0;
	}
	

	double calPrice(List<Product> myProducts) {
		double returnPrice = 0.0;
		
		/*
		 * By Using Enhanced For Loop
		 */
		
		//for(Product product: returnPrice){
        //    returnPrice += product.price*product.disc;
        //}
		
		for(int i = 0; i<myProducts.size();i++) {
			returnPrice += myProducts.get(i).price*myProducts.get(i).dsc;
		}
		return returnPrice;
	}
	
	
}

/*
 * Technical Interview Round 2 First Task
 * */

class Palindrome {

	/* 
	 * Palindrom "cattac" true else false 
	 * Mid Pointer Cut Method 
	 * */
	boolean isPalindromStrOp1(String word) {			
		
		int n= word.length();
		
		/*
		 * j=n-1 shift right index to avoid array index out of exception 
		 * i<n/2 to loop 0 to 2 index on String word 
		 * j>=n/2 to loop 5 to 3 index on String word 
		 * 
		 * */
		
		/*
		 * check index of character of 0 and 5 that equal or not 
		 * 		if not equal break the loop
		 * 		if equal avoid if condition 
		 * 			->and go to index of character 1 and 4 that equal or not 
		 * 			-> run until mid of the String that indicates n/2
		*/
		
		for(int i = 0, j = n - 1; i<n/2 && j>=n/2; i++, j--) { 
			
			if(word.charAt(i) != word.charAt(j)) {
				return false;
			}
			
		}
		return true;
	}
	
	/* 
	 * Palindrom "cattac" true else false 
	 * Reverse Method
	 * Use String Object
	 * */
	
	boolean isPalindromStrOp2(String word) {			
		
		String reverseWord = "";
		word = word.toLowerCase();
		int n= word.length()-1;		
		
		for(int i = n; i>=0; i--) { 
			reverseWord += word.charAt(i);
		}
		
		return word.equals(reverseWord);
	}
	
	/* 
	 * Palindrom "cattac" true else false 
	 * Reverse Method
	 * Use StrngBuffer
	 * */
	
	@SuppressWarnings("unlikely-arg-type")
	boolean isPalindromStrOp3(String word) {			
		
		int n= word.length()-1;	
		word = word.toLowerCase();
		StringBuffer reverseWord = new StringBuffer(); 
		
		for(int i = n; i>=0; i--) { 
			reverseWord.append(word.charAt(i));	
		}
		
		
		return word.equals(reverseWord.toString());
	}
	
	/* 
	 * Palindrom "cattac" true else false 
	 * Reverse Integer
	 * */
	boolean isPalindromIntOp(int number) {			
		
		int originalNumber = number;
		int reverseNumber = 0;
		while(originalNumber>0) {
			reverseNumber = reverseNumber*10 + originalNumber % 10;
			originalNumber /= 10;
		}
		return number == reverseNumber;
	}
}

class SonyPlayStation2025{
	public static void main(String[] args) {	
			
		System.out.println(new Summation().summation(4));		
		List<Product> products = new ArrayList<Product>();		
		products.add(new Product("Iphone 11", 1200, 0.25d));
		products.add(new Product("Umidigo", 180, 0.5d));
		products.add(new Product("Samsung", 600, 0.1d));
		
		System.out.println(new User().addProduct(products.get(0)));
		System.out.println(new User().calPrice(products));
		
		System.out.println();
		
		System.out.println("cattac is Palindorm? " + new Palindrome().isPalindromStrOp1("cattac"));
		System.out.println(121 + " is Palindorm? " + new Palindrome().isPalindromStrOp2(""+121));
		System.out.println(121 + " is Palindorm? " + new Palindrome().isPalindromStrOp3(""+121));
		System.out.println(121 + " is Palindorm? " + new Palindrome().isPalindromIntOp(121));
	
	}
}
