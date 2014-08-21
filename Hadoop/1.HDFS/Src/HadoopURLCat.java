/*
 *	Filename : HadoopURLCat.java
 *	Author : ihainan
 *	E-mail : ihainan72@gmail.com
 *	Website : http://www.ihainan.me
 *	Date : 2014/08/20
 *	Description : 
 *		A simple application that reads data from Hadoop URL.
 *	Reference : 
 *		Hadoop : The Definitive Guide
 */

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.io.IOUtils;

public class HadoopURLCat {
	
	// 使 Java 程序能够支持 hdfs URL 方案
	static {
		URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
	}
	
	// 主函数
	public static void main(String[] args) throws MalformedURLException, IOException{
		// 输入流
		InputStream in = null;
		
		try{
			// 打开流
			in = new URL(args[0]).openStream();
			
			// 复制到标准输出中
			IOUtils.copyBytes(in, System.out, 4096, false);
		}
		finally{
			// 关闭流
			IOUtils.closeStream(in);
		}
	}
}
