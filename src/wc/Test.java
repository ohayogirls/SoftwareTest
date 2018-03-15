package wc;
import gnu.getopt.Getopt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;  
import java.io.InputStreamReader;  
  

import java.io.Reader;  
public class Test {
	public static void main(String[] args)
	{
		Getopt testOpt  = new Getopt("wc", args, "c:w:l:o::");  
        int res;  
        while( (res = testOpt.getopt()) != -1 ) {  
          switch(res) {  
            case 'c': 
            	String fileName = testOpt.getOptarg();
            	readFileByChars(fileName);
                
              break;  
            case 'w':  
                
              break;  
            case 'l':  
              
              break; 
            case 'o':
            	
            	break;
            default:  
              System.out.println("wrong");  
	}
        }
	}
	public static void readFileByChars(String fileName){
		File file =new File(fileName);
		Reader reader =null;
		try{
			reader = new InputStreamReader(new FileInputStream(file));
			int tempchar;
			int num=0;
			while ((tempchar = reader.read()) != -1){
				 if(((char)tempchar)!='\n'&&((char)tempchar)!='\r'){
					 num++;					 
				 }
				 //else continue;
			}
			System.out.print(num);
			System.out.print('\n');
			reader.close();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
 
