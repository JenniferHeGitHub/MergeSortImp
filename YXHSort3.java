import java.io.*;

public class YXHSort3 {
	//*** this is mergeSort3 --- using auxiliary desArray to sort all values in srcArray  
		static int mergeSort(int[] srcArray, int[] desArray, int first, int last){
			if(first < last){
				int middle = (first+last)/2;
				int leftHeight = mergeSort(srcArray, desArray, first, middle);
				int rightHeight = mergeSort(srcArray, desArray, middle+1, last);
			//this part is to deal with value of n not power of 2	
				if(leftHeight != rightHeight){
					if((leftHeight & 1) == 0) mergeDiffHeight(srcArray, desArray, first, middle, last);
				    else mergeDiffHeight(desArray,srcArray,first, middle, last);	
				}else{
					if((leftHeight & 1) != 0) merge(desArray, srcArray, first, middle, last);
				    else merge(srcArray, desArray, first, middle, last);
				} 
			    return leftHeight+1;	
			}else{
				 //insertion sort
				for(int i=first, j=i; j<last; j=++i){
					int temp = srcArray[i+1];
					while(j>=first && srcArray[j]>temp){
						srcArray[j+1] = srcArray[j];
						j--;
					}
					srcArray[j+1] = temp;
				}
			}	
			return 0;
		}  

	//this method is to deal with merge part with n of power of 2
		private static void merge(int[] srcArray, int[] desArray, int first, int middle, int last){
			int leftCount = first;
			int rightCount = middle+1;
			int desCount = first;
			
			while(leftCount<=middle && rightCount<=last){
				if(srcArray[leftCount] < srcArray[rightCount]){
					desArray[desCount] = srcArray[leftCount];
					leftCount++;
				}else{
					desArray[desCount] = srcArray[rightCount];
					rightCount++;
				} 
				desCount++;
			}
			
			while(leftCount>middle && rightCount<=last){
				desArray[desCount] = srcArray[rightCount];
				rightCount++;
				desCount++;
			}
			
			while(leftCount<=middle && rightCount>last){
				desArray[desCount] = srcArray[leftCount];
				leftCount++;
				desCount++;
			}
			
			return;
		}
	//*** this method is to deal with merge part with n of not power of 2	 	
		private static void mergeDiffHeight(int[] sortedArray, int[] addArray, int first, int middle, int last){
			int leftCount = first;
			int rightCount = middle+1;
			int desCount = first;
			
			while(leftCount<=middle && rightCount<=last){
				if(sortedArray[leftCount] < addArray[rightCount]){
					addArray[desCount] = sortedArray[leftCount];
					leftCount++;
				}else{
					addArray[desCount] = addArray[rightCount];
					rightCount++;
				}
				desCount++;
			}
			while(leftCount>middle && rightCount<=last){
				addArray[desCount] = addArray[rightCount];
				rightCount++;
				desCount++;
			}
			while(leftCount<=middle && rightCount>last){
				addArray[desCount] = sortedArray[leftCount];
				leftCount++;
				desCount++;
			}
			
			return;
		}
		
	//*** main function to test mergeSort2 *****
		public static void main(String[] args) throws IOException{
		    int testNum = Integer.parseInt(args[0]);
			int[] testArray = new int[testNum];
			int[] tempArray = new int[testNum]; //Temporary array used in mergeSort3
			
			for(int i=0; i<testNum; i++){
			    testArray[i] = testNum-i;
		    }
			
			long startTime = System.currentTimeMillis();
			int height = mergeSort(testArray, tempArray, 0, testNum-1);
			long finishTime = System.currentTimeMillis();
			
			if((height & 1) == 0){
				for(int i=0; i<testArray.length-1; i++){
				    if(testArray[i] > testArray[i+1]){
					    System.out.println("Sorting failed......");
						return;
					}
				}
			}else{
				for(int i=0; i<tempArray.length-1; i++){
			    if(tempArray[i] > tempArray[i+1]){
				    System.out.println("Sorting failed......");
					return;
				}
			}
			}
			System.out.println("Success!");
			System.out.println("Elapsed Time is: " + (finishTime -startTime));
		}

}
