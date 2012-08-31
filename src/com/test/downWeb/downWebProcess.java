package com.test.downWeb;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
* @author:I0n_P
*
* @date:2008/3/31
*/
public class downWebProcess {

private boolean downStat=true;
private String pageContents;
private Vector<String> addressVect;
private String addresslink;
private int singleErrorTime=0;
private Iterator e;
private String host;
private String path;
private URL url;
private Pattern pattern;
private Matcher matcher;

public downWebProcess(){
   pageContents=null;
   addressVect=new Vector<String>();
  
}

public boolean downPage(String downURL){
  
   pageContents=null;
   String[] urlSplited=downURL.split("/");
   try {
    url = new URL(downURL);
//    System.out.println("echo:  /n " + url);
    URLConnection conn=url.openConnection(); 
    conn.connect(); 
     host=url.getHost();
     path=new String(url.getPath().getBytes(),0,url.getPath().lastIndexOf("/"));
    if(host.equals(url.getHost())){
     System.out.print("\nDownloading:"+downURL+".......");
     InputStream is=conn.getInputStream(); 
     BufferedReader br=new BufferedReader(new InputStreamReader(is,"GBK")); 
     String line; 
     while((line=br.readLine())!=null){
      if(null==pageContents){
       pageContents=line+"\n";
      }else{
       pageContents=pageContents+line+"\n";
      }
     } 
     fileOutput fio=new fileOutput();
     downStat=fio.writeFile("./"+url.getHost(),pageContents,urlSplited[urlSplited.length-1]);
     br.close();
     is.close();
     System.out.print("Finished.");
    }
   
   } catch (Exception e) {
    return false;
   } 
  
  
  
   pattern = Pattern.compile("<a\\s*href\\s*=\\s*([\\w\\.\\-/:]+)");
   matcher = pattern.matcher(pageContents);
   while(matcher.find()){
    if(!matcher.group(1).startsWith("http")){
      addressVect.addElement(matcher.group(1));
      System.out.println(matcher.group(1)+"................add a task");
      }
   }
   pattern = Pattern.compile("<a\\s*href\\s*=\\s*\"([\\w\\.\\-/:]+)\"");
   matcher = pattern.matcher(pageContents);
   while(matcher.find()){
      
      if(!matcher.group(1).startsWith("http")){
      addressVect.addElement(matcher.group(1));
      System.out.println(matcher.group(1)+"................add a task");
      }
   }
  
   pattern = Pattern.compile("<img\\s*src\\s*=\\s*\"([\\w\\.\\-/:]+)\"");
   matcher = pattern.matcher(pageContents);
   while(matcher.find()){
      
      if(!matcher.group(1).startsWith("http")){
      addressVect.addElement(matcher.group(1));
      System.out.println(matcher.group(1)+"................add a task");
      }
   }
   pattern = Pattern.compile("<img\\s*src\\s*=\\s*([\\w\\.\\-/:]+)");
   matcher = pattern.matcher(pageContents);
   while(matcher.find()){
      
      if(!matcher.group(1).startsWith("http")){
      addressVect.addElement(matcher.group(1));
      System.out.println(matcher.group(1)+"................add a task");
      }
   }
  
  
   e=addressVect.iterator();
   while(e.hasNext()||false==downStat){
    if(false==downStat){
     singleErrorTime++;
     if(singleErrorTime>=3){
      System.out.print("\npage:http://"+host+path+"/"+addresslink+"download failed!!");
      downStat=true;
     }else{
      downStat=new downWebProcess().downPage("http://"+host+path+"/"+addresslink);
      System.out.print("\nRetry:http://"+host+path+"/"+addresslink);
     }
    }else{
     addresslink=(String) e.next();
     downStat=new downWebProcess().downPage("http://"+host+path+"/"+addresslink);
    }
   
   }
   return true;
}
}
