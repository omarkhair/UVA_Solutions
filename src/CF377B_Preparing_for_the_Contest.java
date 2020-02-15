
	import java.io.*;
	import java.util.*;

	public class CF377B_Preparing_for_the_Contest {
		static int n,m,s;
		static Bug[] bugs;
		static Student[] students;
	    public static void main(String[] args) throws Exception{
	        Scanner sc=new Scanner(System.in);
	        PrintWriter out= new PrintWriter(System.out);
	        n=sc.nextInt();
	        m=sc.nextInt();
	        s=sc.nextInt();
	        bugs= new Bug[m];
	        students= new Student[n];
	        for(int i=0;i<m; i++)
	        	bugs[i]=new Bug(sc.nextInt(), i);
	        Arrays.parallelSort(bugs);
	        int[] tmp=new int[n];
	        for(int i=0; i<n; i++)
	        	tmp[i]=sc.nextInt();
	        for(int i=0; i<n; i++) {
	        	students[i]= new Student(tmp[i],sc.nextInt(), i+1);
	        }
	        tmp=null;
	        Arrays.parallelSort(students);
	        int lo=1; int hi=m;
	        int[] ans= new int[m];
	        int[] arr= new int[m];
	        while(lo<=hi) {
	        	int mid= (hi+lo)/2;
	        	if(isValid(mid, arr)) {        		
	        		hi=mid-1;
	        		ans=arr.clone();
	        	}
	        	else
	        		lo=mid+1;
	        }
	        if(ans[0]==0)
	        	out.println("NO");
	        else {
	        	out.println("YES");
	        for(int x: ans)
	        	out.print(x+" ");
	        }
	        out.close();
	    }
	    
	    public static boolean isValid(int x, int[] arr) {
	    	long cost=0;
	    	int i=0; int j=0;
	    	PriorityQueue<Price> pq= new PriorityQueue<>();
	    	while(i<m) {
	    		int diff= bugs[i].comp;
	    		while(j<n && students[j].level>=diff){
	    			pq.add(students[j++].p);
	    		}
	    		if(pq.isEmpty())
	    			return false;
	    		Price p1= pq.remove();
	    		cost+=p1.pass;
	    		for(int k=0; i<m && k<x; k++, i++) {    			
	    			arr[bugs[i].index]= p1.index;
	    		}
	    	}
	    	if(cost<=s) 
	    		return true;    	
	    	else
	    		return false;
	    }
	    static class Bug implements Comparable<Bug>{
	    	int comp;
	    	int index;
	    	public Bug(int c, int i) {
	    		comp=c;
	    		index=i;
	    	}
	    	public int compareTo(Bug other) {
	    		return other.comp-comp;
	    	}
	    	public String toString() {
	    		return comp+" "+index;
	    	}
	    	
	    }
	    static class Price implements Comparable<Price>{
	    	int pass;
	    	int index;
	    	public Price(int p , int i) {
	    		pass=p;
	    		index=i;
	    	}
	    	public int compareTo(Price other) {
	    		return pass-other.pass;
	    	}
	    	public String toString() {
	    		return pass+" "+index;
	    	}
	    }
	    static class Student implements Comparable<Student>{
	    	int level;
	    	Price p;
	    	public Student(int l, int p, int i) {
	    		level=l;
	    		this.p= new Price(p,i);
	    	}
	    	public int compareTo(Student other) {
	    		return other.level-level;
	    	}
	    }
	  
	    static class Scanner {
	        StringTokenizer st;
	        BufferedReader br;

	        public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

	        public String next() throws IOException
	        {
	            while (st == null || !st.hasMoreTokens())
	                st = new StringTokenizer(br.readLine());
	            return st.nextToken();
	        }

	        public int nextInt() throws IOException {return Integer.parseInt(next());}

	        public long nextLong() throws IOException {return Long.parseLong(next());}

	        public String nextLine() throws IOException {return br.readLine();}

	        public boolean ready() throws IOException {return br.ready();}


	    }

	}
