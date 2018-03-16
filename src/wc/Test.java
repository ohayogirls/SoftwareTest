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
			int i = optNum.get(optnum);//���ѡ����args�е�λ��
			while(true){				
				if(fileName.contains(args[++i])){
					break;
				}
			}
			outputPath=args[i];
		}
		if((optnum=opt.indexOf("-c"))!=-1){
			int i = optNum.get(optnum);//���ѡ����args�е�λ��
			while(true){				
				if(fileName.contains(args[++i])){
					break;
				}
			}
			readFile(args[i],'c',outputPath);
		}
		if((optnum=opt.indexOf("-w"))!=-1){
			int i = optNum.get(optnum);//���ѡ����args�е�λ��
			while(true){				
				if(fileName.contains(args[++i])){
					break;
				}
			}
			readFile(args[i],'w',outputPath);
		}
		if((optnum=opt.indexOf("-l"))!=-1){
			int i = optNum.get(optnum);//���ѡ����args�е�λ��
			while(true){				
				if(fileName.contains(args[++i])){
					break;
				}
			}
			readFile(args[i],'l',outputPath);
		}
	}
	public static void readFile(String fileName,int res,String Path){ 
		//���ַ����ļ�
		int countChar = 0;
		int countword = 0;
		int countline = 0;
		File file =new File(fileName);		
		try{
			
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String s ="";
			while((s=br.readLine())!=null)//readline()=null�������ݶ�ȡ���
			  {
			   
//			   System.out.println(s);
			   countChar += s.length();//�ַ����������ַ�����
			   if(s.length()!=0){
			   countword += s.split(" |,").length;//split() �������ڰ�һ���ַ����ָ���ַ�������,�ַ�������ĳ��ȣ����ǵ��ʸ���
			   }			  
			   countline++;//��Ϊ�ǰ��ж�ȡ������ÿ������һ���ɼ�����е���Ŀ
			  }
			br.close();
			
			FileWriter fw = new FileWriter(Path, true);  
	        BufferedWriter bw = new BufferedWriter(fw);  	
			String c_out = new String(fileName+", �ַ���: "+countChar);
			String w_out = new String(fileName+", ������: "+countword);
			String l_out = new String(fileName+", ����: "+countline);
			
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
