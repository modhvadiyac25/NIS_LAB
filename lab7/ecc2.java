//odd p operation is not working properly make it currect
//othe 2^i and 2P is working fine
//even will working perfectly if odd will work 
// stuck at finding e2 = d * e1(x,y)

import java.util.*;
import java.lang.*;

class Point{

    long x = 0;
    long y = 0;

    public Point(){}
    public Point(long a,long b){
        this.x=a;
        this.y=b;
    }
    public long getX(){
        return x;
    }
    public long getY(){
        return y;
    }
    public long setX(long a){
        return this.x = a;
    }
    public long setY(long b){
        return this.y = b;
    }

    @Override
    public boolean equals(Object obj){
        //check for the object value wheter it is equal or not 
        return obj != null && this.getX() == ((Point)obj).getX() && this.getY() == ((Point)obj).getY() ? true : false;
    }
}

public class ecc2
{
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

	public static long isCongruant(long a , long n){
        
        if((a+1) % n == 0){
            return -1;
        }
        else 
        {
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
        while(x<p){

            w = (pow(x,3) + a*x + b) % p;
            long temp = pow(w,((p-1)/2)) % p;
            
            if(isCongruant(temp,p) == -1){
                System.out.println("No Solution For : " + x);
            	x++;
            	continue;    
            }

            if(isCongruant(temp,p) == 1){
                
                while(!isPerfactSquare(w)){
                
                    if((p*p) <= w){
                        break;
                    }
                    w =w + p;                
                }
                w = (long)Math.sqrt(w);
            
                long pointA = ~(w-1);
                pointA = positiveInvers(pointA,p);
                

                System.out.println("( " + x +" , " + pointA +")");
                System.out.println("( " + x +" , " + w +")");
                  
            }
            x++;
        }
        
    }

    public static long extendedEuclidian(long a,long n){
        long[] arr = new long[2];
        long r1=n,r2=a,r,t,t1=0,t2=1,gcd,inverse,q;
        // EUA does not find the invers of the nagative r2
        // f so i make change in condition instand of while(r2 > 0) i had write !=
        while(r2 != 0){
            q=r1/r2;
            r=r1-q*r2;
            r1=r2;
            r2=r;
            t=t1-q*t2;
            t1=t2;
            t2=t;
        }
        inverse=t1;
        if(inverse < 0){
            inverse = positiveInvers(inverse,n);
        }
        return inverse;
    }

    public static Point pointOperation(Point P ,Point Q,long prime,long a){
        //System.out.println("control is here2 !");
        Point R = new Point();
        long lemda = 0;

        // bug found : negative sign of denominator and numrator doesnot get cancellled here 

        if(P.equals(Q)){
            lemda = ((3 * pow(P.x,2) + a) * extendedEuclidian((2*P.y),prime)) % prime;
            R.x = (pow(lemda,2) - (2*P.x)) % prime;
        }
        else{
            long eq1 = (Q.y - P.y);
            long eq2 = (Q.x - P.x);
            if(eq1 < 0 && eq2 < 0)
            {
                eq1 = Math.abs(eq1);
                eq2 = Math.abs(eq2);
            }

            lemda = (eq1 * extendedEuclidian(eq2,prime)) % prime;
          //  System.out.println("\n\n (Q.y - P.y) : " + (Q.y - P.y) + "\n(Q.x - P.x) : " + (Q.x - P.x) +"\nextendedEuclidian((Q.x - P.x),prime) is :  " + extendedEuclidian((Q.x - P.x),prime));
            R.x = (pow(lemda,2) - P.x - Q.x) % prime;
        }
        R.x = R.x < 0 ? positiveInvers(R.x,prime) : R.x;
        // R.y (y3) part remain same in both the method
        R.y = (lemda*(P.x - R.x) - P.y) % prime;
        R.y = R.y < 0 ? positiveInvers(R.y,prime) : R.y;
        //System.out.println("mix lemada is : "+ lemda + "\nx is : " + R.x + " y is :" + R.y);
        return R;
    }

    public static boolean isPowerOfTwo(long a){
     
        //for example 4 -> 100 & 011 = 0 means 4 is somepower of 2
        return ((a != 0) && ((a & (a-1))==0));
    
    }

    public static Point keyGeneration(Point P,long d,long p,long a){
   
        Point temp = P;
        if(isPowerOfTwo(d))
        {
            //its better to go with this way if d is power of 2
            while(d > 1){
                d=d/2;
                P =pointOperation(P,P,p,a);
            }
        }
        else
        {
            if(d >= 2){
                //first is find 2P and then remain P addition will calculated with for loop part
                d=d-2;
                P =pointOperation(P,P,p,a);
              //  System.out.println("2P :-  x is : " + P.x + " y is :" + P.y);
            }
            for(int i=0;i<d;i++){
                //d is odd
                P = pointOperation(P,temp,p,a);
            }
        }
        //System.out.println("new x is : " + P.x + " y is :" + P.y);
        return P;
    }

    public static Point pointsAddition(Point P , Point Q,long prime){
        Point R = new Point();


        long eq1 = (Q.y - P.y);
        long eq2 = (Q.x - P.x);
        
        if(eq1 < 0 && eq2 < 0)
        {
            eq1 = Math.abs(eq1);
            eq2 = Math.abs(eq2);
        }
        if(eq1 >= 0 && eq2 == -1){
            //when numrater is possitive and denominator is -1 then inverse of -1 will be 1 in EUA so numrater remains possitive that should not happen 
            // to convert numrater to nagetive we multiply it with -2 and add with itself
            eq1 = (eq1 * -2) + eq1;
        }

        System.out.println("eq1 : " + eq1 +"\n\n (Q.y - P.y) : " + (Q.y - P.y) + "\n(Q.x - P.x) : " + (Q.x - P.x) +"\nextendedEuclidian((Q.x - P.x),prime) is :  " + extendedEuclidian((Q.x - P.x),prime));
      
        long lemda = (eq1 * extendedEuclidian(eq2,prime)) % prime;
        R.x = (pow(lemda,2) - P.x - Q.x) % prime;
        R.x = R.x < 0 ? positiveInvers(R.x,prime) : R.x;
        R.y = (lemda*(P.x - R.x) - P.y) % prime;
        R.y = R.y < 0 ? positiveInvers(R.y,prime) : R.y;
        System.out.println("\n point addition : \nlemada is : "+ lemda + "\nx is : " + R.x + " y is :" + R.y);
        return R;
    }

    public static Point[] eccEncryption(Point M,Point e1,Point e2,long prime,long a){
        Random rand = new Random();
        long r = (int)Math.random() % prime;
        Point[] c = new Point[2];
        
        c[0]= keyGeneration(e1,r,prime,a);
        Point e1_r = keyGeneration(e2,r,prime,a);
        c[1] = pointsAddition(M,e1_r,prime);
        
        System.out.println("c1 : x is : " + c[0].x + " y is :" + c[0].y);
        System.out.println("c2 : x is : " + c[1].x + " y is :" + c[1].y);

        return c;
    }

    
    public static void eccDesryption(Point[] c, long d,long prime,long a){
        // M = c2 - (d * c1)

        // d * c1
        Point d_c1 = keyGeneration(c[0],d,prime,a);
        
        // for subtraction operation we are giving negative sign to Y corrdinate
        d_c1.y = (d_c1.y * -2) + d_c1.y;
        Point M = pointsAddition(c[1],d_c1,prime);
        System.out.println("M : x is : " + M.x + " y is :" + M.y);
        
    }
	public static void main(String[] args) {
        long p = 67;
        Point P = new Point(2,22);
        // Point Q = new Point(10,6);
        // System.out.println("is tru " + (P.equals(Q) ));
        // Point R = pointsAddition(P,Q,p);
	    int i=0;
	    long a=0,b=0;
	    while(true){
	        if(((int)Math.pow(i,3)*4 + 27*(int)Math.pow(i,2))!=0){
	            a=i;
	            b=i;
	            break;
	        }i++;
	    }
        a=2;
        b=3;
        //Point R = pointMultiplication(P,p,a);
        long d=4;
        //assune P == e1
        elipticalCurve(a,b,p);
        Point e2 = keyGeneration(P,d,p,a);

        System.out.println("e2 is : (" + e2.x + "," + e2.y + ")");
        Point M = new Point(24,26);

        Point[] c = eccEncryption(M,P,e2,p,a);
        eccDesryption(c,d,p,a);

        //Point R = pointOperation(P,Q,p,a);
	    System.out.println("a is : " + a +" b is : " + b);
	    
        
		
	}
}


























/*
    public static Point pointMultiplication(Point P ,long prime,long a){

        Point R = new Point();

        long lemda = ((3 * pow(P.x,2) + a) * extendedEuclidian((2*P.y),prime)) % prime;
        R.x = (pow(lemda,2) - (2*P.x)) % prime;
        R.y = (lemda*(P.x - R.x) - P.y) % prime;
        R.y = R.y < 0 ? positiveInvers(R.y,prime) : R.y;
        System.out.println("lemada is : "+ lemda + "\nx is : " + R.x + " y is :" + R.y);
        return R;
    } 

*/