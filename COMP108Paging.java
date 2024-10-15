// Name: Al Aiham Ahmed Saleh
// Student ID: 201765086

// Time Complexity and explanation: Will use the following variables for easier reference:
// n denotes the number of requests, p denotes the size of the cache
// n and p can be different and there is no assumption which one is larger
//
// evictFIFO():
// this method iterates through the requests Array(n), we check for each element if it exists in the cache or not "existIn() method".
//  So, we iterate through the cache array (p).
// If its a miss, we insert it into the cache. 
// The insertion operation has a constant time complexity O(1).

// So, the overall time complexity is: O(n*p).
// 
//
// evictLFD():
// we first create a sub-array of the requests starting from the current pointer position "subArray() method". O(n).
// then we compute the next position array "nextIndexArray() method", which involves iterating through the cache array of size p.
// and for each cache element, we find its first index in the rArray. O(n). This operation has O(p*n).
//then, iterate through the request array (n).
// For each request, check if it exists in the cache,  "iterating through the cache array"  (p).
//  If request is a miss, evict the page with the latest next request index (findMax method), O(p).

// So, the overall time complexity is: O(n^2 *p).         


import java.util.*;
class COMP108Paging {


	// evictFIFO
	// Aim: 
	// if a request is not in cache, evict the page present in cache for longest time
	// count number of hit and number of miss, and find the hit-miss pattern; return an object COMP108PagingOutput
	// Input:
	// cArray is an array containing the cache with cSize entries
	// rArray is an array containing the requeset sequence with rSize entries
	static COMP108PagingOutput evictFIFO(int[] cArray, int cSize, int[] rArray, int rSize) {
		COMP108PagingOutput output = new COMP108PagingOutput();
		int pointer = 0;

		for (int i=0; i<rSize; i++) {
			// Check if request exists in cache.
			if (existIn(rArray[i] , cArray , cSize)){
				output.hitCount++;
				output.hitPattern += "h";
			}
			else{
				output.missCount++;
				output.hitPattern += "m";
                
				// Insert request into cache at FIFO position.
				cArray[pointer%cSize] = rArray[i];
				pointer++;
			}
		}

		output.cache = arrayToString(cArray, cSize);   // Convert cache array to string
		return output;
	}

	// evictLFD
	// Aim:
	// if a request is not in cache, evict the number whose next request is the latest
	// count number of hit and number of miss, and find the hit-miss pattern; return an object COMP108PagingOutput
	// Input:
	// cArray is an array containing the cache with cSize entries
	// rArray is an array containing the requeset sequence with rSize entries
	static COMP108PagingOutput evictLFD(int[] cArray, int cSize, int[] rArray, int rSize) {
		COMP108PagingOutput output = new COMP108PagingOutput();
        
		int evictedIndex;
		int pointer = 0;
		int[] subArray = subArray(pointer, rSize, rArray);  // calls a method that slice rArray based on pointer
		int[] nextPositionArray = nextIndexArray(subArray, cArray, cSize);
		pointer++;

		for (int i=0; i<rSize; i++) {
			// Check if request exists in cache
			if (existIn(rArray[i] , cArray , cSize) ){  // if hit.
				output.hitCount++;
				output.hitPattern += "h";
			}
			else{   // if miss
				output.missCount++;
				output.hitPattern += "m";
                
				// Evict page with latest next request index
				evictedIndex= findMax(nextPositionArray);
				cArray[evictedIndex] = rArray[i];
			}
			if (pointer== rSize){
				// do nothing
				//System.out.print("pointer = 6");
			}
			else{       // Update sub-array and next position array for next iteration
			 subArray = subArray(pointer, rSize, rArray);
		     nextPositionArray = nextIndexArray(subArray, cArray, cSize);
			 pointer++;
			}
		}

		output.cache = arrayToString(cArray, cSize);
		return output;
	}


	// this will turn the cache into a String
	// Only to be used for output, do not use it to manipulate the cache
	static String arrayToString(int[] array, int cSize) {
		String outString="";
		
		for (int i=0; i<cSize; i++) {
			outString += array[i];
			outString += ",";
		}
		return outString;
	}

	static int[] subArray(int pointer, int rSize , int[] rArray){
		int subArrayLength = rSize - pointer;
		int[] subArray = new int[subArrayLength];

		for (int i = 0; i < subArrayLength; i++) {
			subArray[i] = rArray[pointer + i];
		}
		return subArray;
	}


	static boolean existIn(int key , int[] array , int cSize){
		int i = 0;
		boolean found = false;

		while(i < cSize && found == false){
			if (key == array[i]){
				found = true;
			}
			else{
				i++;
			}
		}
		return found;

	}

	static int[] nextIndexArray(int[] rArray , int[] cArray , int cSize){
		int [] nextIndexArray = new int [cSize];
		for (int i=0; i< cSize; i++){
			int position = firstIndex(rArray, cArray[i]);
			nextIndexArray[i] = position;
		}
		return nextIndexArray;
	}
	static int firstIndex(int [] array , int key){
		for (int i = 0; i < array.length; i++) {
            if (array[i] == key) {
                return i; // Return index of first occurrence
            }
        }
        return Integer.MAX_VALUE; // Key not found
    }

	static int findMax(int[] data) {

		int maxIndex = 0;
		for (int i=1; i< data.length ; i++) {
			if (data[i] > data[maxIndex]){
				maxIndex = i;
			}
		}
		return maxIndex;
	}
}

