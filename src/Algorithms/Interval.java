package Algorithms;

import java.util.ArrayList;

public class Interval{
	            int start; int end; long value;
	            public Interval(int s, int e, long v) {
	                start=s;
	                end=e;
	                value=v;
	            }
	        
	            public Interval intersect(Interval other) {
	                int s=Math.max(this.start, other.start);
	                int e=Math.min(this.end, other.end);
	                long v=this.value+other.value;
	                return new Interval(s,e,v);
	            }
	            public String toString() {
	                return "["+start+","+end+"]--> "+value;
	            }
	            
	            public static boolean addIfValid(ArrayList<Interval> arr, int s,int e, long v) {
	    	        if(e<s)
	    	            return false;
	    	        arr.add(new Interval(s, e, v) );
	    	        return true;
	    	    }
	    	    public static boolean isOverlapping(Interval a, Interval b) {
	    	        if(a.start>b.end || b.start>a.end)
	    	            return false;
	    	        return true;
	    	    }
	        }