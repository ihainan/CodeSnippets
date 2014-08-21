/*
 *	Filename : FileSystemAPIDoubleCat.java
 *	Author : ihainan
 *	E-mail : ihainan72@gmail.com
 *	Website : http://www.ihainan.me
 *	Date : 2014/08/20
 *	Description : 
 *		A simple application that copy file from local to hdfs with progress displaying.
 *	Reference : 
 *		Hadoop : The Definitive Guide
 *	Note : 
 *		It is very boring to read this kind of API Reference Book……
 */

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

public class FileCopyWithProgress {
	// 主函数
	public static void main(String[] args) throws IOException, URISyntaxException{
		// 文件路径
		String localSrc = args[0];
		String dst = args[1];
		
		// 得到本地文件的 InputStream 对象
		InputStream in = new BufferedInputStream(new FileInputStream(localSrc));
		
		// 根据远程文件 uri 得到 FileSystem 对象
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(new URI(dst), conf);
		
		// 创建文件，并得到 OutStream 对象
		OutputStream out = fs.create(new Path(dst), new Progressable() {
			
			@Override
			public void progress() {
				// TODO Auto-generated method stub
				System.out.print(".");
			}
		});
		
		// 从 in 复制到 out 中
		IOUtils.copyBytes(in, out, 4096, true);
	}
}
