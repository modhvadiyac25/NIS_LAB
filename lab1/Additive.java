import java.util.*;
import java.io.*;
import java.lang.*;

public class Additive{

    public static void cryptAnalisys(String text){
        System.out.println("CryptAnalisys :-");
        for(int j=1;j<=25;j++)
        {
            for(int i=0;i<text.length();i++){
                
                char c = text.charAt(i);
                int encInt = (((int)c - 97 - j) % 26)+97;
                char e = (char)encInt;
                System.out.print(e);
            
            }
            System.out.println(" ");
        }

    }

    public static void encryption(String text,int key){
        System.out.println("Encryption :-");
        for(int i=0;i<text.length();i++){

           char c = text.charAt(i);
           int encInt =(((int)c-97 + key) % 26)+97;
           char e = (char)encInt;
           System.out.print(e);
    
        }

    }

    public static void decreption(String text,int key){
        System.out.println("Decryption :-");
        for(int i=0;i<text.length();i++){

            char c = text.charAt(i);
            int encInt = (((int)c-97 - key) % 26)+97;
            char e = (char)encInt;
            System.out.print(e);
     
         }
    }

    public static void main(String args[]){

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the text :");
        String text = sc.nextLine();

        System.out.println("Enter your choice 0 for Encryption and 1 for decryption 2 for crypanalisys :");
        int choice = sc.nextInt();

        if(choice==2){
            cryptAnalisys(text);
        }
        if(choice == 1)
        {
            System.out.println("Enter the key :");
            int key = sc.nextInt();
            decreption(text,key);
        }
        else if(choice == 0)
        {
            System.out.println("Enter the key :");
            int key = sc.nextInt();
            encryption(text,key);
        }else
        {
            System.out.println("Invalide choice !");
        }
    }
}