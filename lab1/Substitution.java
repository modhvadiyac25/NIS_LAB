import java.io.*;
import java.util.*;
import java.lang.*;

public class Substitution{

    public static void encryption(String text,HashMap<String,String> map){
        System.out.println("Encryption :-");
        for(int i=0;i<text.length();i++){
            
            String s = Character.toString(text.charAt(i));
            for(Map.Entry<String,String> entry : map.entrySet()){
                
                if(entry.getKey().equals(s)){
                    System.out.print(entry.getValue());
                }
            }
        }
    }

    public static void decreption(String text,HashMap<String,String> map){
        System.out.println("\nDecryption :-");
        for(int i=0;i<text.length();i++){
            
            String s = Character.toString(text.charAt(i));
            for(Map.Entry<String,String> entry : map.entrySet()){
                
                if(entry.getValue().equals(s)){
                    System.out.print(entry.getKey());
                }
            }
        }
    }

    public static void main(String args[]){

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the text :");
        String text = sc.nextLine();

        HashMap<String,String> map = new HashMap<>();
        map.put("a","z");
        map.put("b","p");
        map.put("c","o");
        map.put("d","n");
        map.put("e","m");
        map.put("f","l");
        map.put("g","k");
        map.put("h","j");
        map.put("i","i");
        map.put("j","h");
        map.put("k","g");
        map.put("l","f");
        map.put("m","e");
        map.put("n","d");
        map.put("o","c");
        map.put("p","b");
        map.put("q","a");
        map.put("r","q");
        map.put("s","r");
        map.put("t","s");
        map.put("u","t");
        map.put("v","u");
        map.put("w","v");
        map.put("x","y");
        map.put("y","x");
        map.put("z","w");

        encryption(text,map);
        decreption(text,map);
      
    }
}