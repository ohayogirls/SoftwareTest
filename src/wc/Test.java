package wc;
import java.io.*;
import java.util.ArrayList;
public class Test {
	public static void main(String[] args) throws IOException
	{
		for(String a:args)
		System.out.println(a);
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

		//-o选项：将结果输出到指定文件
		if((optnum=opt.indexOf("-o"))!=-1){
			int i = optNum.get(optnum);//获得选项在args中的位置
			while(true){				
				if(fileName.contains(args[++i])){
					break;
				}
			}
			outputPath=args[i];
		}
		
		//-s选项：递归处理目录下符合条件的文件
		if((optnum=opt.indexOf("-s"))!=-1){
			int i = optNum.get(optnum);//获得选项在args中的位置
			while(true){				
				if(fileName.contains(args[++i])){
					break;
				}
			}		
			
			//ArrayList<File> filelist=getfile(file.getAbsolutePath(),args[i]);
			ArrayList<String> filelist=new ArrayList<String>();
			for(int j = i;j<args.length;++j)
			{
				if(args[j].charAt(0)!='-'){
					filelist.add(args[j]);
				}
				else break;
			}
			//-c选项：返回文件 file.c 的字符数
			if((optnum=opt.indexOf("-c"))!=-1){
				for(String f:filelist)
				readFile(f,'c',outputPath);
			}
			//-w选项：返回文件 file.c 的单词总数
			if((optnum=opt.indexOf("-w"))!=-1){
				for(String f:filelist){
					//停用词表
					if((optnum=opt.indexOf("-e"))!=-1)
					{
						int j =optNum.get(optnum);
						ArrayList<String> StopList = new ArrayList<String>();
						File stopfile =new File(args[++j]);
						File file =new File(f);
						BufferedReader stopbr = new BufferedReader(new InputStreamReader(new FileInputStream(stopfile)));	
						BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
						String s1="";
						while((s1=stopbr.readLine())!=null)//readline()=null代表数据读取完毕
						  {			
						   if(s1.length()!=0){
							   for(String stop:s1.split(" "))
							   StopList.add(stop);					   
						   }			  					   
						  }					
						stopbr.close();
						int countword =0;
						String s ="";
						while((s=br.readLine())!=null)//readline()=null代表数据读取完毕
						  {
						   	
						   if(s.length()!=0){
							   for(String s2:s.split(" |,")){
								   if(StopList.contains(s2)!=true)
									   countword++;
							   }
						   }			  	   
						  }
						br.close();
						FileWriter fw1 = new FileWriter(outputPath, true);  
				        BufferedWriter bw = new BufferedWriter(fw1);
				        String w_out = new String(file.getName()+", 单词数: "+countword);
				        bw.write(w_out);
		            	bw.newLine();
		            	bw.close();
		            	fw1.close();
					}
					else readFile(f,'w',outputPath);
				}
			}
				
			//-l选项：返回文件 file.c 的总行数
			if((optnum=opt.indexOf("-l"))!=-1){
				for(String f:filelist)
				readFile(f,'l',outputPath);
			}
			
			if((optnum=opt.indexOf("-a"))!=-1){
				for(String f:filelist)
					countCode(f,outputPath);
			}
		}
		
		//非递归处理时
		if((optnum=opt.indexOf("-s"))==-1){			
			//-c选项：返回文件 file.c 的字符数
			if((optnum=opt.indexOf("-c"))!=-1){
				int i = optNum.get(optnum);//获得选项在args中的位置
				while(true){				
					if(fileName.contains(args[++i])){
						break;
					}
				}
				readFile(args[i],'c',outputPath);
			}
			//-w选项：返回文件 file.c 的单词总数
			if((optnum=opt.indexOf("-w"))!=-1){
				int i = optNum.get(optnum);//获得选项在args中的位置
				while(true){				
					if(fileName.contains(args[++i])){
						break;
					}
				}
				//停用词表
				if((optnum=opt.indexOf("-e"))!=-1)
				{
					int j =optNum.get(optnum);
					ArrayList<String> StopList = new ArrayList<String>();
					File stopfile =new File(args[++j]);
					File file =new File(args[i]);
					BufferedReader stopbr = new BufferedReader(new InputStreamReader(new FileInputStream(stopfile)));	
					BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
					String s1="";
					while((s1=stopbr.readLine())!=null)//readline()=null代表数据读取完毕
					  {			
					   if(s1.length()!=0){
						   for(String stop:s1.split(" "))
						   StopList.add(stop);					   
					   }			  					   
					  }					
					stopbr.close();
					int countword =0;
					String s ="";
					while((s=br.readLine())!=null)//readline()=null代表数据读取完毕
					  {
					   	
					   if(s.length()!=0){
						   for(String s2:s.split(" |,")){
							   if(StopList.contains(s2)!=true)
								   countword++;
						   }
					   }			  	   
					  }
					br.close();
					FileWriter fw1 = new FileWriter(outputPath, true);  
			        BufferedWriter bw = new BufferedWriter(fw1);
			        String w_out = new String(file.getName()+", 单词数: "+countword);
			        bw.write(w_out);
	            	bw.newLine();
	            	bw.close();
	            	fw1.close();
				}
				else readFile(args[i],'w',outputPath);
			}
			//-l选项：返回文件 file.c 的总行数
			if((optnum=opt.indexOf("-l"))!=-1){
				int i = optNum.get(optnum);//获得选项在args中的位置
				while(true){				
					if(fileName.contains(args[++i])){
						break;
					}
				}
				readFile(args[i],'l',outputPath);
			}
			//-a选项
			if((optnum=opt.indexOf("-a"))!=-1){
				int i = optNum.get(optnum);//获得选项在args中的位置
				while(true){				
					if(fileName.contains(args[++i])){
						break;
					}
				}
				countCode(args[i],outputPath);
			}
		}
	}
	private static void countCode(String f,String Path) {
		int commmentLines=0,whiteLines=0,normalLines=0;
		File file =new File(f);
		BufferedReader br = null;  
        boolean comment = false;  
        try {  
            br = new BufferedReader(new FileReader(f));  
            String line = "";  
            while ((line = br.readLine()) != null) {  
                line = line.trim();  
                if (line.matches("//.*")) {  
                    ++commmentLines;  
                } else if (line.matches("^/\\*.*\\*/$")) {  
                    ++commmentLines;  
                } else if (comment) {  
                    ++commmentLines;  
                    if (line.matches(".*\\*/$")) {  
                        comment = false;  
                    }  
                } else if (line.matches("^/\\*.*[^\\*/$]")) {  
                    ++commmentLines;  
                    comment = true;  
                } else if (!comment &&line.matches("[\\s&&[^\\n]]*")) {  
                    ++whiteLines;  
                } else {  
                    ++normalLines;  
                } 
            }
                FileWriter fw = new FileWriter(Path, true);  
    	        BufferedWriter bw = new BufferedWriter(fw);  	
    			String c_out = new String(file.getName()+", 代码行/空行/注释行: "+normalLines+"/"+whiteLines+"/"+commmentLines);
    			bw.write(c_out);
            	bw.newLine();
            	bw.close();
            	fw.close();
              
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
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
			   countChar += s.length()+1;//字符个数就是字符长度
			   if(s.length()!=0){
			   countword += s.split(" |,").length;//split() 方法用于把一个字符串分割成字符串数组,字符串数组的长度，就是单词个数
			   }			  
			   if(s.length()!=0)countline++;//因为是按行读取，所以每次增加一即可计算出行的数目
			  }
			br.close();
			
			FileWriter fw = new FileWriter(Path, true);  
	        BufferedWriter bw = new BufferedWriter(fw);  	
			String c_out = new String(file.getName()+", 字符数: "+countChar);
			String w_out = new String(file.getName()+", 单词数: "+countword);
			String l_out = new String(file.getName()+", 行数: "+countline);
			
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
	public static ArrayList<File> getfile(String fileDir,String filename) {  
	        ArrayList<File> fileList = new ArrayList<File>();  
	        File file = new File(fileDir);  
	        File[] files = file.listFiles();// 获取目录下的所有文件或文件夹  
	        if (files == null) {// 如果目录为空，直接退出  
	            System.out.println(file.getAbsolutePath());  
	        }  
	        else{
	        // 遍历，目录下的所有文件  
	        for (File f : files) {  
	            if (f.isFile()&&match(filename,f.getName())) {  
	                fileList.add(f); 
	                System.out.println(filename+match(filename,"file.c"));
	            } else if (f.isDirectory()) {  	                 
	                getfile(f.getAbsolutePath(),filename);  
	            }  
	        } 
	        }
	        return fileList;
	    }  
	//字符串含通配符* ?匹配
	public static boolean match(String pattern, String str) {
	        if (pattern == null || str == null)
	            return false;
	 
	        boolean result = false;
	        char c; // 当前要匹配的字符串
	        boolean beforeStar = false; // 是否遇到通配符*
	        int back_i = 0;// 回溯,当遇到通配符时,匹配不成功则回溯
	        int back_j = 0;
	        int i, j;
	        for (i = 0, j = 0; i < str.length();) {
	            if (pattern.length() <= j) {
	                if (back_i != 0) {// 有通配符,但是匹配未成功,回溯
	                    beforeStar = true;
	                    i = back_i;
	                    j = back_j;
	                    back_i = 0;
	                    back_j = 0;
	                    continue;
	                }
	                break;
	            }
	 
	            if ((c = pattern.charAt(j)) == '*') {
	                if (j == pattern.length() - 1) {// 通配符已经在末尾,返回true
	                    result = true;
	                    break;
	                }
	                beforeStar = true;
	                j++;
	                continue;
	            }
	 
	            if (beforeStar) {
	                if (str.charAt(i) == c) {
	                    beforeStar = false;
	                    back_i = i + 1;
	                    back_j = j;
	                    j++;
	                }
	            } else {
	                if (c != '?' && c != str.charAt(i)) {
	                    result = false;
	                    if (back_i != 0) {// 有通配符,但是匹配未成功,回溯
	                        beforeStar = true;
	                        i = back_i;
	                        j = back_j;
	                        back_i = 0;
	                        back_j = 0;
	                        continue;
	                    }
	                    break;
	                }
	                j++;
	            }
	            i++;
	        }
	 
	        if (i == str.length() && j == pattern.length())// 全部遍历完毕
	            result = true;
	        return result;
	    }
}
