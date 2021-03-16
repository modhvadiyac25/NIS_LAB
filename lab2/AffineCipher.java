import java.util.*;
import java.io.*;
import java.lang.*;

public class AffineCipher{

    public static void encryption(String text,int k1,int k2,int n){
        System.out.println("Encryption is :");
        
        for(int i=0;i<text.length();i++){
            char c = text.charAt(i);
            int encInt =(int)c;
            encInt = ( ( (encInt-97) * k1 + k2 ) % n )+97;
            char e = (char)encInt;
            System.out.print(e);
         }
    }

    public static void decreption(String text,int k1,int k2,int n){
        System.out.println("\nDescription is :");

        int inverse[] = extendedEuclidian(k1,n);
        //System.out.println("\ninverse is : " + inverse[0]);
        
        for(int i=0;i<text.length();i++){
            char c = text.charAt(i);
            //System.out.println("\nchar c is : " + c);
            int encInt = (int)c;
            //System.out.println("\nencInt is : " + encInt);
            encInt = encInt - k2-97;
            //System.out.println("\nCT-k2 : " + encInt);
            int ctIntoInvers = (((encInt) * inverse[0]) % n)+97;
            //System.out.println("\n(CT-k2)*invers % 26 is :"+ ctIntoInvers);
            if(ctIntoInvers < 0){
                ctIntoInvers = positiveInvers(ctIntoInvers,n);
            }
            char e = (char)ctIntoInvers;
            System.out.print(e);
        }
    }
    //Extended Euclidian algoritham
    public static int[] extendedEuclidian(int a,int n){
        
        int[] arr = new int[2];

        int r1=n,r2=a,r,t,t1=0,t2=1,gcd,inverse,q;

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
        arr[0]=inverse;
        arr[1]=gcd;

        return arr;
    }

    public static int positiveInvers(int inverse,int n){
        
        while(inverse < 0){
            inverse = inverse + n;
        }
        return inverse;
    } 

    public static void main(String args[]){

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the text :");
        String text = sc.nextLine();

        System.out.println("Enter the key1 :");
        int k1 = sc.nextInt();
        System.out.println("Enter the key2 :");
        int k2 = sc.nextInt();

        System.out.println("Enter n : ");
        int n = sc.nextInt();
        encryption(text,k1,k2,n);
        decreption(text,k1,k2,n); 
       
    }
}