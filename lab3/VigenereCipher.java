import java.util.*;
import java.lang.*;

class VigenereCipher
{
    public static int[] charToInt(String text){
       // System.out.println("length : " + text.length());
        int[] convert = new int[text.length()];
        int j=0;
        for(int i=0;i<text.length();i++){
            char c = text.charAt(i);
            if(c != ' '){
                int temp =  (int)c - 65;
                convert[j] = temp;
                j++;
              //System.out.println(convert[i]);
            }
        }
      //  System.out.println("count of j : " + j +"\nlength of convert : " + convert.length);
        return convert;
    }
    
        public static int positiveInvers(int inverse){
            int n=26;
            while(inverse < 0){
                inverse = inverse + n;
            }
            return inverse;
        }
    
        public static void encryption(String p,String key){
    
            int[] pInt = charToInt(p);
            int[] keyInt = charToInt(key);
        
            int enc=0;
            System.out.println("\n\nEncryption : ");
            for(int i=0;i<pInt.length;i++){
                enc = ((pInt[i] + keyInt[i % key.length()]) % 26) + 65;
                System.out.print((char)enc);
            }
            System.out.println("\n");
        }
    
        public static void decryption(String cipher,String key){
    
            int[] cipherInt = charToInt(cipher);
            int[] keyInt = charToInt(key);
            
            int dec=0;
            System.out.println("\nDecryption : ");
            for(int i=0;i<cipherInt.length;i++){
                dec = positiveInvers((cipherInt[i] - keyInt[i % key.length()])) + 65;
                System.out.print((char)dec);
            }
            System.out.println("\n");
        }
    
        public static void main(String args[])
        {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter Plain/Cipher text : ");
            String plainText = sc.nextLine(); 
            System.out.println("Enter Key :");
            String key = sc.nextLine();
    
            encryption(plainText,key);
            decryption(plainText,key);
        }
    }