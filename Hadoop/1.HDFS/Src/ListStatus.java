/*
 *	Filename : ListStatus.java
 *	Author : ihainan
 *	E-mail : ihainan72@gmail.com
 *	Website : http://www.ihainan.me
 *	Date : 2014/08/20
 *	Description : 
 *		A simple application that lists the status of files.
 *	Reference : 
 *		Hadoop : The Definitive Guide
 *	Note : 
 *		It is very boring to read this kind of API Reference Book……
 */

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;

public class ListStatus {
	// 主函数
	public static void main(String[] args) throws IOException, URISyntaxException{
		// 得到 FileSystem
		Configuration conf = new Configuration();
		URI uri = new URI(args[0]);
		FileSystem fs = FileSystem.get(uri, conf);
		
		// 得到所有的 Paths
		Path[] paths = new Path[args.length];
		for(int i = 0; i < args.length; ++i){
			paths[i] = new Path(args[i]);
		}
		
		// 得到存储文件状态的 Status 数组
		FileStatus[] status = fs.listStatus(paths);
		
		// 再次转换为 Path
		// Path[] newPaths = FileUtil.stat2Paths(status);
		
		// 输出相关信息
		System.out.println("Found " + status.length + "items");
		for(FileStatus s : status){
			System.out.println(String.format("%s\t%d %s %s\t%d %s %s", 
					s.getPermission().toString(),
					s.getReplication(),
					s.getOwner(),
					s.getGroup(),
					s.getLen(),
					new Date(s.getModificationTime()).toString(),
					s.getPath().toString()
					));
		}
	}
}
