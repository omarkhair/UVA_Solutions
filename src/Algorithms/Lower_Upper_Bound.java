package Algorithms;

public class Lower_Upper_Bound {

	public static int lowerbound(int[] arr, int key) {
		int i=arr.length;
		int lo=0; int hi=arr.length-1;
		while(lo<=hi) {
			int mid=(lo+hi)/2;
			if(arr[mid]>=key) {
				i=mid;
				hi=mid-1;
			}
			else
				lo=mid+1;		
		}
		return i;
	}
	public static int upperbound(int[] arr, int key) {
		int i=0;
		int lo=0; int hi=arr.length-1;
		while(lo<=hi) {
			int mid=(lo+hi)/2;
			if(arr[mid]<=key) {
				i=mid;
				lo=mid+1;
			}
			else
				hi=mid-1;		
		}
		return i;
	}

}
