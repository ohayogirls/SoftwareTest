package wc;
import java.io.*;
import java.util.ArrayList;
public class Test {
	public static void main(String[] args) throws IOException
	{
		ArrayList<String> opt = new ArrayList<String>();
		ArrayList<Integer> optNum = new ArrayList<Integer>();
		ArrayList<String> fileName = new ArrayList<String>();
		ArrayList<Integer> fileNum = new ArrayList<Integer>();
		
		String outputPath = "result.txt";
		File output = new File(outputPath);
		output.createNewFile();
		FileWriter fw =  new FileWriter(output);
		fw.write("");
		fw.close();
		
		for(int i=0;i<args.length;++i){
			if(args[i].charAt(0)=='-'){
				opt.add(args[i]);
				optNum.add(i);
			} 
			else{
				fileName.add(args[i]);
				fileNum.add(i);					
				}
			}
		int optnum;
		if((optnum=opt.indexOf("-o"))!=-1){
			int i = optNum.get(optnum);//获得选项在args中的位置
			while(true){				
				if(fileName.contains(args[++i])){
					break;
				}
			}
			outputPath=args[i];
		}
		if((optnum=opt.indexOf("-c"))!=-1){
			int i = optNum.get(optnum);//获得选项在args中的位置
			while(true){				
				if(fileName.contains(args[++i])){
					break;
				}
			}
			readFile(args[i],'c',outputPath);
		}
		if((optnum=opt.indexOf("-w"))!=-1){
			int i = optNum.get(optnum);//获得选项在args中的位置
			while(true){				
				if(fileName.contains(args[++i])){
					break;
				}
			}
			readFile(args[i],'w',outputPath);
		}
		if((optnum=opt.indexOf("-l"))!=-1){
			int i = optNum.get(optnum);//获得选项在args中的位置
			while(true){				
				if(fileName.contains(args[++i])){
					break;
				}
			}
			readFile(args[i],'l',outputPath);
		}
	}
	public static void readFile(String fileName,int res,String Path){ 
		//按字符读文件
		int countChar = 0;
		int countword = 0;
		int countline = 0;
		File file =new File(fileName);		
		try{
			
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String s ="";
			while((s=br.readLine())!=null)//readline()=null代表数据读取完毕
			  {
			   
//			   System.out.println(s);
			   countChar += s.length();//字符个数就是字符长度
			   if(s.length()!=0){
			   countword += s.split(" |,").length;//split() 方法用于把一个字符串分割成字符串数组,字符串数组的长度，就是单词个数
			   }			  
			   countline++;//因为是按行读取，所以每次增加一即可计算出行的数目
			  }
			br.close();
			
			FileWriter fw = new FileWriter(Path, true);  
	        BufferedWriter bw = new BufferedWriter(fw);  	
			String c_out = new String(fileName+", 字符数: "+countChar);
			String w_out = new String(fileName+", 单词数: "+countword);
			String l_out = new String(fileName+", 行数: "+countline);
			
			switch(res) {  
            case 'c': 
            	bw.write(c_out);
            	bw.newLine();
              break;  
            case 'w':  
            	bw.write(w_out);
            	bw.newLine();
              break;  
            case 'l':  
            	bw.write(l_out);
            	bw.newLine();
              break; 	
            default:  
              bw.write("wrong");  
	}
	          bw.close();  
	          fw.close(); 
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
