import java.util.*;
import java.lang.*;

public class Elgamal
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
    
    public static long[] encryption(long m,long e1,long e2,long d,long prime_no){
        
         long[] c = new long[2];
         long r = 2 + (int)(Math.random() % prime_no);
         c[0] = power(e1,r,prime_no);
         c[1] = ((long)Math.pow(e2,r) * m) % prime_no;
         System.out.println(" c1 : " + c[0] + " c2 : " + c[1]);
         return c;

    }
    
    public static void decryption(long c1,long c2,long e1,long e2,long d,long prime_no){
        long m = ((c2 % prime_no) * (extendedEuclidian((long)Math.pow(c1,d),prime_no) % prime_no)) % prime_no;
        System.out.println("M is : " + m);
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
    
    public static void main(String[] args) {
        
        Main obj1 = new Main();
        long prime_no =23;// 3433;
        
        int order = (int)phi(phi(prime_no));
        
        HashSet[] ArrOfSet = new HashSet[(int)prime_no];
        long[] PrimitiveRoot = primitiveRoot(prime_no,ArrOfSet,order);
        
        long e1=PrimitiveRoot[0];
        long d=0,j=0;
        
        while(j<prime_no){
            if(j>1 && j<=prime_no-2){
                d=j;
                break;
            }
            j++;
        }
        long e2 = power(e1,d,prime_no);
        
        System.out.println("\n order : " + order + " e1 :" + e1 + " d :" + d + " e2 " + e2);
        long m = 1212;
        long[] c = encryption(m,e1,e2,d,prime_no);
        decryption(c[0],c[1],e1,e2,d,prime_no);
    
        //  for(int i=0;i<PrimitiveRoot.length;i++){
        //      System.out.println(PrimitiveRoot[i]);
    	//   }   
    }
}




