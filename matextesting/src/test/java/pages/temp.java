package pages;
import java.util.Scanner;

import org.apache.commons.lang.StringUtils;
public class temp {

	 public static void main(String[] args) {
         Scanner sc=new Scanner(System.in);
         System.out.println("================================");
         for(int i=0;i<3;i++){
             String s1=sc.next();
             int x=sc.nextInt();
             //Complete this line
             int l = s1.length();
//             System.out.println("lenght is : "+l);
             System.out.print(StringUtils.rightPad(s1, 15, ""));
             int ll =Integer.toString(x).length();
             if(ll>=3) {
            	 System.out.println(x);
             }
             else {
            	 
            	 System.out.println(StringUtils.leftPad(Integer.toString(x), 3, "0"));
             }
         }
         System.out.println("================================");

 }
}
