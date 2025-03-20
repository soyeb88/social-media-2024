package interview2025;

import java.util.*;


/* 
 * Assessment Test Code: March 19, 2025
 * */
public class mindlance2025 {
	public static void main(String[] args) {
		// input <1,2,3,4,5,6> output <3,4,1,2,5,6>
		// input <1,2,3,4,5,6,7,8> output <3,4,1,2,7,8,5,6>
		// input <1,2,3,4,5> output <3,4,1,2,5>
		// input <1,2,3> output <3,2,1>
		// input <1,2> output <1,2>
		// input <1> output <1>

		List<Integer> lst = new ArrayList<>();
		lst.add(1);
		lst.add(2);
		lst.add(3);
		lst.add(4);
		//lst.add(5);
		//lst.add(6);
		//lst.add(7);
		//lst.add(8);
		//lst.add(9);

		System.out.println(lst);
		
		/* 
		 * Exact 3 elements in ArrayList
		 * */
		if (lst.size() == 3) {			
				int x, y = 0;
				x = lst.get(2);
				y = lst.get(0);
				lst.set(0, x);
				lst.set(2, y);
			
		} 
		/* 
		 * More than 3 elements in ArrayList
		 * */
		else {
			for (int i = 2; i < lst.size(); i += 4) {
				
				/* 
				 * To avoid index out of exception on odd size of ArrayList 
				 * */
				if(lst.size() == i + 1) {
					break;
				}
				
				int x, y = 0;
				x = lst.get(i - 2);
				y = lst.get(i - 1);

				lst.set(i - 2, lst.get(i));
				lst.set(i - 1, lst.get(i + 1));
				lst.set(i, x);
				lst.set(i + 1, y);
			}
		}

		System.out.println(lst);
	}
}
