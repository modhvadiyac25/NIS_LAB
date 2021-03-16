import java.util.*;
import java.lang.*;

public class dhk
{
    public static long phi(long n)
    {
        long result = n;
        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                while (n % i == 0)
                    n /= i;
                result -= result / i;
            }
        }
        if (n > 1)
            result -= result / n;
        return result;
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
        inverse=t1;
        if(inverse < 0){
            inverse = positiveInvers(inverse,n);
        }
        
        return inverse;
    }

    public static long positiveInvers(long inverse,long n){
        
        while(inverse < 0){
            inverse = inverse + n;
        }
        return inverse;
    }
    
    
    public static long[] primitiveRoot(long prime_no,HashSet[] ArrOfSet,int phi){
        
        System.out.print("Primitive roots are : ");
        long[] PR = new long[phi];
        
        int k=0;
        for(int i=1;i<prime_no;i++){
            ArrOfSet[i-1]= new HashSet<Long>();
            for(int j=0;j<=prime_no;j++){
                ArrOfSet[i-1].add(power(i,j,prime_no));
            }
            if(ArrOfSet[i-1].size() == prime_no-1){
                System.out.print(" " + i);
                PR[k]=(long)i;
                k++;
            }
        }
        System.out.println("count : " + k);
        
      return PR;
        
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


    public static long Alice_r1(long prime_no,long g,long x){
        return (long)Math.pow(g,x) % prime_no;
    }

    public static long Bob_r2(long prime_no,long g,long y){
        long r2 = (long)Math.pow(g,y) % prime_no;
        return r2;
    }

    public static long Alice(long prime_no,long r2,long x){
        return (long)Math.pow(r2,x) % prime_no;
    }

    public static long Bob(long prime_no,long r1,long y){
        return (long)Math.pow(r1,y) % prime_no;
    }


    public static void main(String[] args) 
    {
        Random rand = new Random();
        //Main obj1 = new Main();
        long prime_no =23;// 3433;
        
        int order = (int)phi(phi(prime_no));
        
        HashSet[] ArrOfSet = new HashSet[(int)prime_no];
        long[] PrimitiveRoot = primitiveRoot(prime_no,ArrOfSet,order);
        long g = PrimitiveRoot[rand.nextInt((int)order)];
        long x = rand.nextInt((int)prime_no);
        long y = rand.nextInt((int)prime_no);

        System.out.println("x is : " + x + "y is :" + y);

        long r1 = power(g,x,prime_no);
        long r2 = power(g,y,prime_no);

        long k1 = power(r2,x,prime_no);
        long k2 = power(r1,y,prime_no);

        System.out.println("r1 : " + r1 + " r2 : " + r2 +"\nk1 : " + k1 + " k2 : " + k2 + "\nFinal key : " + (power(g,(x*y), prime_no)));

        //  for(int i=0;i<PrimitiveRoot.length;i++){
        //      System.out.println(PrimitiveRoot[i]);
        //   }   
    }
}