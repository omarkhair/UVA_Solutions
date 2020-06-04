package Algorithms;

import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

public class FastestScanner {
	 static class Scanner {
		 
	        private InputStream in;
	        private byte[] buffer = new byte[1024];
	        private int curbuf;
	        private int lenbuf;
	 
	        public Scanner(InputStream in) {
	            this.in = in;
	            this.curbuf = this.lenbuf = 0;
	        }
	 
	        public boolean hasNextByte() {
	            if (curbuf >= lenbuf) {
	                curbuf = 0;
	                try {
	                    lenbuf = in.read(buffer);
	                } catch (IOException e) {
	                    throw new InputMismatchException();
	                }
	                if (lenbuf <= 0)
	                    return false;
	            }
	            return true;
	        }
	 
	        private int readByte() {
	            if (hasNextByte())
	                return buffer[curbuf++];
	            else
	                return -1;
	        }
	 
	        private boolean isSpaceChar(int c) {
	            return !(c >= 33 && c <= 126);
	        }
	 
	        private void skip() {
	            while (hasNextByte() && isSpaceChar(buffer[curbuf]))
	                curbuf++;
	        }
	 
	        public boolean hasNext() {
	            skip();
	            return hasNextByte();
	        }
	 
	        public String next() {
	            if (!hasNext())
	                throw new NoSuchElementException();
	            StringBuilder sb = new StringBuilder();
	            int b = readByte();
	            while (!isSpaceChar(b)) {
	                sb.appendCodePoint(b);
	                b = readByte();
	            }
	            return sb.toString();
	        }
	 
	        public int nextInt() {
	            if (!hasNext())
	                throw new NoSuchElementException();
	            int c = readByte();
	            while (isSpaceChar(c))
	                c = readByte();
	            boolean minus = false;
	            if (c == '-') {
	                minus = true;
	                c = readByte();
	            }
	            int res = 0;
	            do {
	                if (c < '0' || c > '9')
	                    throw new InputMismatchException();
	                res = res * 10 + c - '0';
	                c = readByte();
	            } while (!isSpaceChar(c));
	            return (minus) ? -res : res;
	        }
	 
	        public long nextLong() {
	            if (!hasNext())
	                throw new NoSuchElementException();
	            int c = readByte();
	            while (isSpaceChar(c))
	                c = readByte();
	            boolean minus = false;
	            if (c == '-') {
	                minus = true;
	                c = readByte();
	            }
	            long res = 0;
	            do {
	                if (c < '0' || c > '9')
	                    throw new InputMismatchException();
	                res = res * 10 + c - '0';
	                c = readByte();
	            } while (!isSpaceChar(c));
	            return (minus) ? -res : res;
	        }
	 
	        public double nextDouble() {
	            return Double.parseDouble(next());
	        }
	 
	        public int[] nextIntArray(int n) {
	            int[] a = new int[n];
	            for (int i = 0; i < n; i++)
	                a[i] = nextInt();
	            return a;
	        }
	 
	        public long[] nextLongArray(int n) {
	            long[] a = new long[n];
	            for (int i = 0; i < n; i++)
	                a[i] = nextLong();
	            return a;
	        }
	 
//	        public Long [] nextSortedLongArray(int n) {
//	        	Long [] a=new Long[n];
//	        	for(int i=0;i<n;i++) a[i]=sc.nextLong();
//	        	Arrays.sort(a);
//	        	return a;
//	        }
//	        public Integer [] nextSortedIntegerArray(int n) {
//	        	Integer [] a=new Integer[n];
//	        	for(int i=0;i<n;i++) a[i]=sc.nextInt();
//	        	Arrays.sort(a);
//	        	return a;
//	        }
	 
	    }
}
