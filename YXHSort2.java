import java.io.*;

public class YXHSort2 {
		//*** this is mergeSort2 --- using an auxiliary array B in merge method
			static void mergeSort(int[] a, int[] tempArray, int first, int last){
				if(first < last){
					if((last - first) > 11){
						int middle = (first+last)/2;
						mergeSort(a, tempArray, first, middle);
						mergeSort(a, tempArray, middle+1, last);
						merge(a,tempArray,first, middle, last);
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
			
		//*** this method is merge part 
			private static void merge(int[] a, int[] tempArray, int first, int middle, int last){
				for(int i=first; i<=middle; i++) tempArray[i] = a[i];
				for(int i=middle+1; i<=last; i++) tempArray[i] = a[i];
				
				int i = first;
				int j = middle+1;
				int k = first;
				
				while(i <= middle && j <=last){
					if(tempArray[i] < tempArray[j]){
						a[k] = tempArray[i];
						i++;
					}else{
						a[k] = tempArray[j];
						j++;
					}
					k++;
				}
				while(i > middle && j <= last){
					a[k] = tempArray[j];
					j++;
					k++;
				}
				while(j > last && i <= middle){
					a[k] = tempArray[i];
					i++;
					k++;
				}
				return;
			}
		
	  //*** main function to test mergeSort2 *****
			public static void main(String[] args) throws IOException{
			    int testNum = Integer.parseInt(args[0]);
				int[] testArray = new int[testNum];
				int[] tempArray = new int[testNum]; //temp array used in mergeSort2
				
				for(int i=0; i<testNum; i++){
				    testArray[i] = testNum-i;
			    }
				
				long startTime = System.currentTimeMillis();
				mergeSort(testArray, tempArray, 0, testNum-1);
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

