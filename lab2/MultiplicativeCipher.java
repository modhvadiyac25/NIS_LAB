import java.util.*;
import java.io.*;
import java.lang.*;

public class MultiplicativeCipher{

    public static void encryption(String text,int key,int n){
        
        System.out.println("Encryption is :");
        for(int i=0;i<text.length();i++)
        {
           char c = text.charAt(i);
           int encInt = (int)c;
          // System.out.println(encInt);
           encInt = (((encInt-97) * key) % n)+97;
           char e = (char)encInt;
          // System.out.println(encInt);
           System.out.print(e);
        }

    }

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

    public static void decreption(String text,int key,int n){
        System.out.println("\nDecryption is :");
        int inverse[] = extendedEuclidian(key,n);
        
        for(int i=0;i<text.length();i++){
            
            char c = text.charAt(i);
            int encInt = (int)c;
            int ctIntoInvers = (((encInt-97) * inverse[0]) % n)+97;
            
            if(ctIntoInvers < 0){
                ctIntoInvers = positiveInvers(ctIntoInvers,n);
            }

            char e = (char)ctIntoInvers;
            System.out.print(e);
        }

    }
    public static void main(String args[]){

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the text :");
        String text = sc.nextLine();

        System.out.println("Enter the key :");
        int key = sc.nextInt();
        System.out.println("Enter n : ");
        int n = sc.nextInt();

        encryption(text,key,n);
        decreption(text,key,n);
        return;
        /*
        System.out.println("Enter your choice 0 for Encryption and 1 for decryption :");
        int choice = sc.nextInt();

        if(choice == 1)
        {
            decreption(text,key,n);
        }
        else if(choice == 0)
        {
            encryption(text,key,n);
        }else
        {
            System.out.println("Invalide choice !");
        }
        */
    }
}
