/*
 *	Filename : FileSystemAPICat.java
 *	Author : ihainan
 *	E-mail : ihainan72@gmail.com
 *	Website : http://www.ihainan.me
 *	Date : 2014/08/20
 *	Description : 
 *		A simple application that reads data using File System API.
 *	Reference : 
 *		Hadoop : The Definitive Guide
 */

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class FileSystemAPICat {
	public static void main(String[] args) throws IOException{
		// 获取 uri
		String uri = args[0];
		
		// 新建 Configuration 对象
		Configuration conf = new Configuration();
		
		// 根据 uri 和 Configuration 中实例化文件系统
		FileSystem fs = FileSystem.get(URI.create(uri), conf);
		

		InputStream in = null;
		try {
			// 从文件系统中获取输入流
			in = fs.open(new Path(uri));
			
			// 输入流复制到标准输出中
			IOUtils.copyBytes(in, System.out, 4096, false);
		} 
		finally{
			// 关闭输入流
			IOUtils.closeStream(in);
		}
	}
}
