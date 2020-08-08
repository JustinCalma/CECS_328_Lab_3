import java.util.*;

public class QuickSelect {

	public static void main(String[] args) {
		QuickSelect qs = new QuickSelect();
		Scanner scnr = new Scanner(System.in);
		
		System.out.println("Enter the desired array length: ");
		int n = scnr.nextInt(); // Gets the desired length of the array from the user
		int[] a = new int[n]; // Creates an array of the desired length
		
		for (int i = 0; i < n; i++) { // Adds random generated integers into the array
			int randNum = - 100 + (int)(Math.random() * (100 - -100) + 1);
			a[i] = randNum;
		}
		
		int[] tempArr = a.clone(); // Creates a clone of the array a
		
		System.out.println("The generated random array of length " + n + " is " + Arrays.toString(a));
		
		System.out.println("Enter a number between 1 to " + n);
		int k = scnr.nextInt(); // Gets the kth numbers to find
		
		int firstElem = 0;
		int lastElem = a.length - 1;
		
		int result1 = qs.quickSelectPartA(a, firstElem, lastElem, k); // Calls QuickSelect Part A to find the kth smallest number
		System.out.println("The kth smallest element in the random generated array of length " + n + " is " + result1);
		
		int[] result2 = qs.quickSelectPartB(tempArr, firstElem, lastElem, k); // Calls QuickSelect Part B to find the kth largest elements
		System.out.println("The " + k + " largest elements in the random generated array of length " + n + " is " + Arrays.toString(result2));
		
	}
	
	// QuickSelect() Method For Part A
	public int quickSelectPartA(int[] a, int firstElem, int lastElem, int k) { // O(1) + O(n) = O(n)
		int pivotIdx = partitionPartA(a, firstElem, lastElem); // Call partition to find new pivot;
		int result = 0;
		
		if (k == pivotIdx) { // Base Case: Checks if k is equal to the current index of the pivot
			result = a[pivotIdx];
		} else if (k > pivotIdx) { // Checks if k is greater than the current index of pivot
				result = quickSelectPartA(a, pivotIdx + 1, lastElem, k); // Keep partitioning array
		} else { // K is less than the current index of pivot
			result = quickSelectPartA(a, firstElem, pivotIdx - 1, k); // Keep partitioning array
		}
		
		return result; // Return median value

	}
	
	// Partitioning Method For Part A
	public int partitionPartA(int[] a, int firstElem, int lastElem) { // O(n)
		QuickSelect swapper = new QuickSelect();
		
		int pivot = a[firstElem];
		int i = firstElem - 1;
		int j = lastElem + 1;
		
		while (i < j) {
			i++;
			while (a[i] < pivot) { // Left pointer i starts at left of array and increases towards to pivot
				// start at beginning of array and move toward pivot
				i++;
			}
			
			j--;
			while (a[j] > pivot){ // Right pointer j starts at right of array and decrements towards pivot
				// start at end of array and move toward pivot
				j--;
			}
			
			// Call the swap method to swap the two values 	
			if (i < j) {
				swapper.swap(a, i, j);
				
			}		
		}
		
		return j; // Return the index of the median
		
	}
	
	// QuickSelect() Method ofr Part B
	public int[] quickSelectPartB(int[] tempArr, int firstElem, int lastElem, int k) { // O(n) + O(1) = O(n)
		partitionPartB(tempArr, firstElem, lastElem, k); // Calls the partition method of Part B
		int counter = 0;
		int[] result = new int[k]; // Creates an array of size k 
		for (int i = tempArr.length - k; i < tempArr.length; i++) { // Iterates through the nums greater than the median
			result[counter] = tempArr[i]; // Adds the largest element into an array in the correct index
			counter++;
		}
		return result; // Returns the kth largest elements as an array
	}
	
	// Partitioning Method for Part B
	public int partitionPartB (int[] tempArr, int firstElem, int lastElem, int k) { // O(n)
		QuickSelect swapper = new QuickSelect();
		
		int pivot = tempArr[firstElem];
		int i = firstElem - 1;
		int j = lastElem + 1;
		
		while (i < j) {
			i++;
			while (tempArr[i] < pivot) { // Left pointer i starts at left of array and increases towards to pivot
				// start at beginning of array and move toward pivot
				i++;
			}
			
			j--;
			while (tempArr[j] > pivot){ // Right pointer j starts at right of array and decrements towards pivot
				// start at end of array and move toward pivot
				j--;
			}
			
			// Call the swap method to swap the two values 	
			if (i < j) {
				swapper.swap(tempArr, i, j);
				
			}		
		}

		return j; // Return the index of the median
		
	}
	
	// Helper function to swap two elements that need to be swapped 
	public void swap (int[] a, int x, int y) { // O(1)
		int temp = a[x];
		a[x] = a[y];
		a[y] = temp;
		
	}
	
}

// The Total Time complexity of my algorthm will be O(n) because QuickSelectPartA & B both take O(n) time.
// Calculating the time complexity I get, O(n) + O(n) = O(2n) = O(n).