import java.util.Arrays;
import java.util.Scanner;

public class Divied_Chocolates_AtCoder {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		int r=sc.nextInt(); int c=sc.nextInt(); int k=sc.nextInt();
		int[][] grid= new int[r][c];
		int ans=Integer.MAX_VALUE;
		for(int i=0;i<r;i++) {
			String s=sc.next();
			for(int j=0;j<c;j++)
				grid[i][j]=s.charAt(j)-'0';
		}
		for(int mask=0;mask<(1<<(r-1));mask++) {
			int[][] arr= new int[countOnes(mask)+1][c];
			int maxValue=0;
			for(int i=0;i<c;i++) {
				int j=0; int y=0;
				for(int shift=0;shift<r;shift++,y++) {
					arr[j][i]+=grid[y][i];
					maxValue=Math.max(maxValue, arr[j][i]);
					if(((1<<shift)&mask)!=0)
						j++;
				}
			}
			if(maxValue>k)
				continue;
//			System.out.println(Arrays.deepToString(arr));
			int cuts=arr.length-1;
			for(int i=0;i<c-1;i++) {
				if(needsCut(i,arr,k))
					cuts++;
				else
					for(int j=0;j<arr.length;j++) 
						arr[j][i+1]+=arr[j][i];
			}
			ans=Math.min(cuts,ans);
			}
		System.out.println(ans);
	}
	public static int countOnes(int x) {
		int count=0;
		for(int i=1;i<=x;i<<=1)
			if((i&x)!=0)
				count++;
		return count;
	}
	public static boolean needsCut(int c, int[][] arr, int k) {
		for(int i=0;i<arr.length;i++)
			if(arr[i][c]+arr[i][c+1]>k)
				return true;
		return false;
	}
}

