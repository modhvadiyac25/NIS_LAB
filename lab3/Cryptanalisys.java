import java.util.*;
import java.lang.*;

class Cryptanalisys{

    public static void mutulIndexOfCoincidence(char[] Y){
        
        double english_freq[]={8.167,1.492,2.782,4.253,12.702,2.228,2.015,6.094,6.996,0.153,0.772,4.025,2.406,6.749,7.507,1.929,0.095,5.987,6.327,9.056,2.758,0.978,2.360,0.150,1.974,0.074};
        double[] p=new double[26];
        double[] q=new double[26];
        
        double IC = 0.0;
        int[] f = frequency(Y);
        double sum = 0;
        
        for (int i = 0; i < 26; i++)
        {
            p[i] = english_freq[i] / 100;
            q[i] =(double)f[i]/Y.length;
           // System.out.println("q[i]" + q[i]);
        }
        
        
        int j=0;
        for(int k=0;k<26;k++)
        {
            sum = 0;
            for(int i=0;i<26;i++){
                sum = sum + (p[i] * q[(i + k) %26 ]);    
            }
            System.out.println("Sum : " + sum + "    index : " + (char)((int)j+97));     
            j++;
        }
        System.out.println("\n");
      
    }

    public static int[] frequency(char[] Y){
        
        int[] f = new int[26];
        for(int i=0;i<Y.length;i++)
        {
            //System.out.print(Y[i]);
            f[(int)(Y[i]-'A')]++;
           // System.out.println("Y["+Y[i]+"] = " + (f[(int)(Y[i]-65)]));
        }
        
        return f;
    }

    public static void analisys(String ct1){
        
        int m = 4;
        char[] ct = ct1.toCharArray();
        double size =Math.ceil((double)ct.length/m);

        char[][] Y = new char[m][(int)size];

        double[] IC = new double[m];
        String y1 = "LWGWCRAOKTEPGTQCTJVUEGVGUQGECVPRPVJGTJEUGCJG";
        Y[0]= y1.toCharArray();
        String y2 = "IGGGQHGWGKVCTSOSQSWVWFVYSHSVFSHZHWWFSOHCOQSL";
        Y[1]= y2.toCharArray();
        String y3 = "OFDHURWQZKLZHGVVLUVLSZWHWKHFDUKDHVIWHUHFWLUW";
        Y[2]= y3.toCharArray();
        String y4 = "MEVHCWILEMWVVXGETMEXLMLCXVELGMIMBWXLGEVVITX";
        Y[3]= y4.toCharArray();
        
        for(int i=0;i<m;i++)
        {
            mutulIndexOfCoincidence(Y[i]);
        }

    }

    public static void main(String args[]){       
        String ct ="LIOMWGFEGGDVWGHHCQUCRHRWAGWIOWQLKGZETKKMEVLWPCZVGTHVTSGXQOVGCSVETQLTJSUMVWVEUVLXEWSLGFZMVVWLGYHCUSWXQHKVGSHEEVFLCFDGVSUMPHKIRZDMPHHBVWVWJWIXGFWLTSHGJOUEEHHVUCFVGOWICQLTJSUXGLW";  
        analisys(ct);
    } 

}