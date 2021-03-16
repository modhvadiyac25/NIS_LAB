import java.util.*;

class Main{
    
    public static long[] primitiveRoot(long prime_no,HashSet<Long>[] ArrOfSet,int phi){
        
        System.out.print("Primitive roots are : ");
        long[] PR = new long[phi];
        
        int k=0;
        for(int i=1;i<prime_no;i++){
            
            ArrOfSet[i-1]= new HashSet<Long>();
    
            for(int j=1;j<prime_no;j++){
                
                ArrOfSet[i-1].add( ( (long)Math.pow(i,j) ) % prime_no);
        
            }
            
            if(ArrOfSet[i-1].size() == prime_no-1){
                System.out.print(i);
                PR[k]=(long)i;
                k++;
            }
        }
        /*
        for(int i=1;i<prime_no;i++){
            System.out.println("set " + i + " contains : " + ArrOfSet[i-1]);
        }*/
        return PR;
        
    }
    
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
    
    public static void main(String args[]){
        Main obj1 = new Main();
		long prime_no =23;// 3433;
		
		int order = (int)phi(phi(prime_no));
		int phi = order;
        System.out.println("order : " + order);
		HashSet[] ArrOfSet = new HashSet[(int)prime_no];
		//long[] PrimitiveRoot = primitiveRoot(prime_no,ArrOfSet,order);
		
        System.out.println("Primitive roots are : ");
        long[] PR = new long[phi];
        
        int k=0;
        for(int i=1;i<prime_no;i++){
            
            ArrOfSet[i-1]= new HashSet<Long>();
    
            for(int j=1;j<=prime_no;j++){
                
                ArrOfSet[i-1].add( ( (long)Math.pow(i,j) ) % prime_no);
        
            }
            //System.out.println("set : " + ArrOfSet[i-1]);
            if(ArrOfSet[i-1].size() == prime_no-1){
                System.out.println(" root : " + i);
                PR[k]=(long)i;
                k++;
            }
        }

    }
}