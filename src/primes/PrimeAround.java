package primes;
import java.util.Scanner;
public class PrimeAround {
    static boolean isPrime(int num){
if(num<2)
    return false;
for(int i=2;i*i<=num;i++){
    if(num%i==0)
        return false;
}
return true;
    }

public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter a number: ");
        int n=sc.nextInt();
        int count=0;

        System.out.println("\n5 primes before "+n+":");
        int current=n-1;

        while(count<5 && current>=2){
            if(isPrime(current)){
                System.out.print(current+" ");
                count++;
            }
            current--;
        }

        System.out.print("\n\n5 primes after "+n+":");
        count=0;
        current=n+1;

        while(count<5){
            if(isPrime(current)){
                System.out.print(current+" ");
                count++;
            }
            current++;
        }
              sc.close();
}



}
