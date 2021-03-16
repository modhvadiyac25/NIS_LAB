import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List; 
import java.util.Map;
import java.util.HashMap;

public class SimpleSubstituionCipher 
{
    Map<String,String> ucMapping; 
    Map<String,String> lcMapping;
    
    public SimpleSubstituionCipher()
    {
            ucMapping = new HashMap<String,String>();
            lcMapping = new HashMap<String,String>();
    }

    public void mapCharacters()
    {
        List<Integer> arr = new ArrayList<Integer>();
        int first = 65;

        for (int i = 0; i <= 25; i++) 
        {
            arr.add(first);
            first += 1;
        }

        Collections.shuffle(arr);

        first = 65;
        for(int i = 0; i <= 25; i++)
        {
            String character = String.valueOf((char)first);
            int a = arr.get(i);
            ucMapping.put(character,String.valueOf((char)a));
            first += 1;
        }
        arr.clear();

        first = 97;

        for (int i = 0; i <= 25; i++) 
        {
            arr.add(first);
            first += 1;
        }

        Collections.shuffle(arr);

        first = 97;
        for(int i = 0; i <= 25; i++)
        {
            String character = String.valueOf((char)first);
            int a = arr.get(i);
            lcMapping.put(character,String.valueOf((char)a));
            first += 1;
        }
    }

    public void printUCMapping()
    {
        System.out.println("Mapping of Uppercase letters to some other alphabets for encrypption");
        for (Map.Entry<String, String> me : ucMapping.entrySet()) 
        { 
            System.out.print("["+me.getKey() + " => "); 
            System.out.print(me.getValue()+"] "); 
        }
        System.out.println();
        System.out.println();
    }

    public void printLCMapping()
    {
        System.out.println("Mapping of Lowercase letters to some other alphabets for encrypption");
        for (Map.Entry<String, String> me : lcMapping.entrySet()) 
        { 
            System.out.print("["+me.getKey() + " => "); 
            System.out.print(me.getValue()+"] "); 
        }
        System.out.println();
    }
    public String encryption(String plainText)
    {
        StringBuilder characters = new StringBuilder(plainText.length());

        for(int i = 0; i < plainText.length(); i++)
        {
            if(Character.isUpperCase(plainText.charAt(i)))
            {
                char c = plainText.charAt(i);
                characters.append(ucMapping.get(String.valueOf(c)));
            }
            else
            {
                char c = plainText.charAt(i);
                characters.append(lcMapping.get(String.valueOf(c)));
            }
        }
        return characters.toString();
    }

    public String decryption(String cipherText)
    {
        StringBuilder characters = new StringBuilder(cipherText.length());

        for(int i = 0; i < cipherText.length(); i++)
        {
            String value;
            if(Character.isUpperCase(cipherText.charAt(i)))
            {
                value = String.valueOf(cipherText.charAt(i));
               
               for(Map.Entry<String, String> entry: ucMapping.entrySet())
               {
                    if(entry.getValue().equals(value))
                    {
                        characters.append(entry.getKey());
                    }
               }
            }
            else
            {
                value = String.valueOf(cipherText.charAt(i));

                for(Map.Entry<String, String> entry: lcMapping.entrySet())
                {
                     if(entry.getValue().equals(value))
                     {
                         characters.append(entry.getKey());
                     }
                }
               
            }
        }
        return characters.toString();
    }

   public static void main(String[] args) 
   {
    SimpleSubstituionCipher simpleSubstituionCipher = new SimpleSubstituionCipher();
       Scanner sc = new Scanner(System.in);

       System.out.println("Enter your Plain texts:");
       String plainText = sc.nextLine();

       simpleSubstituionCipher.mapCharacters();
       simpleSubstituionCipher.printUCMapping();
       simpleSubstituionCipher.printLCMapping();   
       
       String encryptedText = simpleSubstituionCipher.encryption(plainText);
       System.out.println();
       System.out.println("Encryption of string"+ " ' "+plainText+" ' "+" using simple substitution cipher is " +" ' "+encryptedText+" ' ");

       String decryptedText = simpleSubstituionCipher.decryption(encryptedText);
       System.out.println();
       System.out.println("Decryption of string"+ " ' "+encryptedText+" ' "+" using simple substitution cipher is " +" ' "+decryptedText+" ' ");
   }
}
