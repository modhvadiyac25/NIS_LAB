import java.util.*;
import java.lang.*;

public class Main
{
    public static long multiplyAndSquare(long a,long X,long n){

        long y=1;
        String x=Long.toBinaryString(X);
        //System.out.println("Binary : " + Long.toBinaryString(X));
        for(long i=x.length()-1;i>=0;i--){
            //System.out.println(" B is : " + x.charAt((int)i));
            if(x.charAt((int)i)=='1'){
               y=(y*a) % n;
            }
            a= (a*a) % n;
        }
        return y;
    }
    
    public static boolean isPerfactSquare(long n){
        
        double sqrt = Math.sqrt((double)n);
        return (sqrt - Math.floor(sqrt)==0);
    
    }

    public static long pow(long a,long b){
        if(b == 0){
            return 1;    
        }
        else{
            return a * pow(a,--b);
        }
        
    }

	public static long power(long a,long n,long p){
		 long res= 1;
		 a=a%p;
		 while(n > 0){
			 
			 if(n % 2==1){
			 	res=(res * a)%p;
			 }
			 n=n>>1;
			 a=(a * a) %p;
		 }

		 return res;
	 }

    public static long extendedEuclidian(long a,long n){
		 long[] arr = new long[2];
		 long r1=n,r2=a,r,t,t1=0,t2=1,gcd,inverse,q;
	
		 while(r2 > 0){
			 q=r1/r2;
			 r=r1-q*r2;
			 r1=r2;
			 r2=r;
			 t=t1-q*t2;
			 t1=t2;
			 t2=t;
		 }
		 gcd=r1;
		 return t1;
	}

	public static long isCongruant(long a , long n){
        
        // For a positive integer n, two integers a and b are said to be congruent modulo n (or a is congruent to b modulo n),
        // if a and b have the same remainder when divided by n (or equivalently if a − b is divisible by n )

        if((a+1) % n == 0){
        	//System.out.println("-1 part : " + (a+1));
            return -1;
        }
        else 
        {
        	//System.out.println("1 part : " + (a+1));
            return 1;
        }
    
    }

    public static long positiveInvers(long inverse,long n){

		 while(inverse < 0){
		 	inverse = inverse + n;
		 }
	 	return inverse;
	 }
    
    public static void elipticalCurve(long a,long b,long p)
    {
        
        long x = 0;
        long w = 0;
        //int[][] point = new int[p][2];
        while(x<p){

            System.out.print("x is : " + x + " ");
            long equ1 = pow(x,3) + a*x + b; 
            w = equ1 % p;
            System.out.print(" w is : " + w + "  \n");
     //       System.out.println("extendedEuclidian(pow(w,p-1/2),p) is : " +  (pow(w,p-1/2)%p));
            long temp = pow(w,((p-1)/2)) % p;
            
            if(isCongruant(temp,p) == -1){
                System.out.println("No Solution For This : " + x);
            	x++;
            	System.out.println("\n\n");
            	continue;    
            }

            if(isCongruant(temp,p) == 1){
                
                //System.out.println("1st w is : " +  w);
                while(!isPerfactSquare(w)){
                	
                    //sum untill p^2 >= a + kp 
                    if((p*p) <= w){
                        break;
                    }
                  //  System.out.println("w is : " + w);
                    System.out.println("in while");
                    w =w + p;                
                }
                System.out.println("Before squrt root of w : " + w);
                w = (long)Math.sqrt(w);
            
                long pointA = ~(w-1);
                System.out.println("pointA is : " + pointA);
                //  ~(3) == -4
                //System.out.println(" negetive : " + (int)Math.sqrt(~(w-1)));
                //if((long)Math.sqrt(~(w-1)) % p < 0){
                	pointA = positiveInvers(pointA,p);
                //}

                System.out.println("( " + x +" , " + pointA +")");
                System.out.println("( " + x +" , " + w +")");
                  
            }
            x++;
            System.out.println("\n\n");
            System.out.println("\n\n");
        }
        
    }
    
	public static void main(String[] args) {
	    
	    int i=0;
	    long a=0,b=0;
	    
	    while(true){
	        if(((int)Math.pow(i,3)*4 + 27*(int)Math.pow(i,2))!=0){
	            a=i;
	            b=i;
	            break;
	        }
	        i++;
	    }
	    
	    long p = 13;
		elipticalCurve(a,b,p);
		System.out.println("a is : " + a +" b is : " + b);
	}
}