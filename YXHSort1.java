import java.io.*;

public class YXHSort1 {
	//*** this is mergeSort1 --- allocating a dynamic memory for left array and right array within merge method 
		static void mergeSort(int[] a, int first, int last){
			if(first < last){
				if((last - first) > 11){
					int middle = (first + last)/2;
					mergeSort(a, first, middle);
					mergeSort(a, middle+1, last);
					merge(a, first, middle, last);
				}else{ //insertion sort
					for(int i=first, j=i; j<last; j=++i){
						int temp = a[i+1];
						while(j>=first && a[j]>temp){
							a[j+1] = a[j];
							j--;
						}
						a[j+1] = temp;
					}
				}	
			}	
		}

	//*** this is merge method is used for mergeSort1 ***
		static void merge(int[] a, int first, int middle, int last){
			int leftLength = middle-first+1;
			int rightLength = last - middle;
			int[] leftArray = new int[leftLength];
			int[] rightArray = new int[rightLength];
			
			for(int i=first; i<=middle; i++) leftArray[i-first] = a[i];
			for(int i=middle+1; i<=last; i++) rightArray[i-(middle+1)] = a[i];
			
			int i=0; //used to count leftArray
			int j=0; //used to count rightArray
			int k=first;
			
			while(i<leftLength && j<rightLength){
				if(leftArray[i] < rightArray[j]){
					a[k] = leftArray[i];
					i++;
				}else{
					a[k] = rightArray[j];
					j++;
				}
				k++;
			}
			while(i < leftLength){
					a[k] = leftArray[i];
					i++;
					k++;
				}
			while(j < rightLength){
					a[k] = rightArray[j];
					j++;
					k++;
				}
			return;
		}
		
	//*** main function to test mergeSort2 *****	
		public static void main(String[] args) throws IOException{
		    int testNum = Integer.parseInt(args[0]);
			int[] testArray = new int[testNum];
			
			for(int i=0; i<testNum; i++){
			    testArray[i] = testNum-i;
		    }
			
			long startTime = System.currentTimeMillis();
			mergeSort(testArray, 0, testNum-1);
			long finishTime = System.currentTimeMillis();
			
			for(int i=0; i<testArray.length-1; i++){
			    if(testArray[i] > testArray[i+1]){
				    System.out.println("Sorting failed......");
					return;
				}
			}
			System.out.println("Success!");
			System.out.println("Elapsed Time is: " + (finishTime -startTime));
		}

}

