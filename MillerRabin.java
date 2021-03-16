import java.util.*;
import java.lang.*;

class MillerRabin{

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

    public boolean millerRabin(long n){

        long n1 = n-1;
        long m=0,k=0;

        //n-1 = (2^k)*m
        // 1 < a < n-1
        long a = 2 + (int)(Math.random() % (n - 2));

        //for k
        while(true){
            if(n1%2==0){
                k++;
                n1/=2;
            }
            else{
                break;
            }
        }
        
        //for m = n-1/2^k
        m=(n-1)/((long)Math.pow(2,k));

        //System.out.println("m is : " + m + " k is : " + k);
        //b=a^m mod n
        long b = multiplyAndSquare(a,m,n); 

        if(b%n == 1){
            return true;
        }
        
        for(int i=0;i<k;i++){
            if(b%n == -1){
                return true;
            }
            else
            {
                b = (b*b)%n;
            }
        }
        return false;
    }

    public boolean isPrime(long p){
        
        for(int i=0;i<100;i++){
            if(!millerRabin(p)){
                return false;
            }
        }
        return true;
    }

    public static void main(String args[]){

        MillerRabin elgamal= new MillerRabin();
        Scanner sc = new Scanner(System.in);

        long p = 5009; //7; //13;
        System.out.println("Is "+p+" prime : " + elgamal.isPrime(p));
    }
}