import java.util.*;

public class BogoSort {
	public static int count = 0;
	public static int fullcount = 0;
	public static boolean bool = false;
	public static void Sort(int[] arr, int max) {
		ArrayList<Integer> used = new ArrayList<Integer>();
		Random time = new Random();
		
		int[] temp = new int[max];
		int newMax = max - 1;
		for(int i = 0; i < max; i++) {
			int tmp = time.nextInt((newMax - 0)+1) + 0;
			
			if(used.contains(tmp)) {
				i--;
			}else {
				
				temp[tmp] = arr[i];
				used.add(tmp);
			}
		}
		System.out.println(theArray(temp));
		used.clear();
		if(test(temp, max)) { 
			bool = true;
			System.out.println(theArray(temp));
			System.out.println("the number of iterations " + fullcount);
			return;
		}
		else {
			count++;
			fullcount++;
			if (count >= 3000) {
				
				return;
			}
			Sort(arr, max);
			
		}
	}
	
	public static Boolean test(int[] arr, int max) {
		for(int i = 0; i < arr.length; i++) {
			int num1 = arr[i];
			int num2 = i + 1;
			
			if (num2 >= arr.length) {
				return true;
			}
			else if (num1 == arr[num2]){
				int tmp = num2 + 1;
				if (tmp >= arr.length) return true;
				
				if (num1 > arr[tmp]) {
				return false;
				}
				
				
			}
		else if(num1 >= arr[num2]) {
				return false;
				
			}
		}
		return false;
	}
	
	public static String theArray(int[] arr) {
		String tmp = "[";
		for(int i = 0; i <(arr.length - 1); i++) {
			tmp = tmp + arr[i] + ", " ;
		}
		tmp = tmp + arr[arr.length - 1]+ "]";
		return tmp;
	}
	public static void main(String arg[]) {
		Scanner in = new Scanner(System.in);  // Create a Scanner object
	    System.out.println("number of variables");

	    int num = in.nextInt();
		
	    int[] test = new int[num];
	    
		for(int i = 0; i<num; i++) {
			Random r = new Random();
		     
			test[i] = r.nextInt((30 - 0) + 1) + 0;
			
		}
		
		
		
	    while (bool == false) {
	    	if(fullcount >= 200000000) {
	    		System.out.println("it is done nothing found");
	    		break;
	    	}
	    	count = 0;
	    	Sort(test, num);
	    }
	    
	    
		in.close();
	}
}
