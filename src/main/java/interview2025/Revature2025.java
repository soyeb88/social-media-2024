package interview2025;

import java.util.*;

/*
 *Find Unique Value
 * By HashSet
 * */
public class Revature2025 {
	public static void main(String[] args) {		
		 List<String> lst = new ArrayList<String>();
	     lst.add("ahmed");
	     lst.add("ahmed");
	     lst.add("soyeb");
	       
	     Set<String> uniqueValue = new HashSet<>(lst);
	     System.out.println(uniqueValue.toString());	        
	}	     
}


