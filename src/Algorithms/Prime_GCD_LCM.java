package Algorithms;

public class Prime_GCD_LCM {
	public static int primelessthan(int a) {
		while(a-->1)
			if(isPrime(a))
				return a;
		return 1;
	}
	public static long LCM(int a, int b, int c) {
		long ans= (1l*a*b)/GCD(a,b);
		ans=(ans*c)/GCD(ans,c);
		return ans;
	}
	public static long GCD(long a, long b) {
		if(a==0)
			return b;
		return GCD(b%a, a);
	}
	public static boolean isPrime(long a) {
		if(a<2 || a%2==0)
			return a==2;
		for(int i=3;i<=Math.sqrt(a);i+=2)
			if(a%i==0)
				return false;
		return true;
	}
}
