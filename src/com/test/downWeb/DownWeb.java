package com.test.downWeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
* @author:I0n_P
*
* @date:2008/3/31
*/
public class DownWeb {

public static void main(String[] args) {

   downWebProcess down1 = new downWebProcess();
   System.out.println("Please input a address where you want to down:");
   InputStreamReader isr = new InputStreamReader(System.in);
   BufferedReader br = new BufferedReader(isr);
   String s = null;
   try {
    s = br.readLine();
    System.out.println(" /n ---echo---s = " + s);
   } catch (IOException e) {
    e.printStackTrace();
   }
   System.out.println(" /n ---echo---s = " + s);
   down1.downPage(s);

   System.out.println("all Finished!");
}

}
