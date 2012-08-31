package com.test.downWeb;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
* @author:I0n_P
*
* @date:2008/3/31
*/
public class fileOutput {

public boolean writeFile(String path,String pagecontents,String fileName){
  
   FileOutputStream fos;
   try {
    File f1=new File(path);
    f1.mkdirs();
    fos = new FileOutputStream(path+"/"+fileName);
    BufferedOutputStream bos=new BufferedOutputStream(fos);
    DataOutputStream dos=new DataOutputStream(bos);
    dos.write(pagecontents.getBytes());
    dos.close();
   
   } catch (Exception e) {
    e.printStackTrace();
   }
   return true;
  
}
}
