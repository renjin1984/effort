package test.iotest;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BigFileIOTest {
	String inputFilePath;
	String outPutFilePath;
	
	public BigFileIOTest(String inputFilePath, String outPutFilePath) {
		super();
		this.inputFilePath = inputFilePath;
		this.outPutFilePath = outPutFilePath;
	}

	//io bufferedStrem
	public long bufferedStremTest() throws Exception{
		BufferedInputStream in=new BufferedInputStream(new FileInputStream(inputFilePath));
		byte[] bytes=new byte[1000];
		BufferedOutputStream out=new BufferedOutputStream(new FileOutputStream(outPutFilePath));
		while(in.read(bytes)!=-1){
			out.write(bytes);
		}
		in.close();
		out.close();
		return System.currentTimeMillis();
	}
	
	//io bufferedReader/bufferedWriter
    public long bufferedReaderTest() throws Exception{
		BufferedReader reader=new BufferedReader(new InputStreamReader(new BufferedInputStream(new FileInputStream(inputFilePath))));
		BufferedWriter write =new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outPutFilePath)));
		
		String data = null;
		while((data = reader.readLine())!=null){
			System.out.println(data);
			write.write(data);
			write.newLine();
		}
		write.write("水水水水");
		reader.close();
		write.close();
		return System.currentTimeMillis();
	}
    
    
	
	public static void main(String[] args) throws Exception{
		BigFileIOTest test=new BigFileIOTest("d://新建文本文档.txt","d://新建文本文档1.txt");
	//	test.bufferedStremTest();
		test.bufferedReaderTest();
	}
}
