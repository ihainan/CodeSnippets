/*
 *	Filename : FileDecompressor.java
 *	Author : ihainan
 *	E-mail : ihainan72@gmail.com
 *	Website : http://www.ihainan.me
 *	Date : 2014/08/21
 *	Description : 
 *		A simple application used to decompress file & choose a suitable codec 
 *		according to the postfix of this file.
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
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import org.apache.hadoop.io.compress.CompressionInputStream;


public class FileDecompressor {
	// 解压缩本地文件
	private static void decompressLocalFile(String args[]) throws IOException{
		// 得到文件的 InputStream
		InputStream in = new BufferedInputStream(new FileInputStream(args[0]));
		
		// 实例化 CompressionCodeFactory
		Configuration conf = new Configuration();
		CompressionCodecFactory factory = new CompressionCodecFactory(conf);
		
		// 根据文件路径得到相应的 Codec
		CompressionCodec codec = factory.getCodec(new Path(args[0]));
		
		// 解压缩文件，输出到标准输出中
		if(codec == null){
			System.err.println("No codec found for " + args[0]);
			System.exit(-1);
		}
		else{
			// System.out.print(codec.getCompressorType().toString());
			CompressionInputStream compressIn = codec.createInputStream(in);
			IOUtils.copyBytes(compressIn, System.out, 4096, false);
		}
	}
	
	// 解压缩 DFS 中的文件
	private static void decompressDFSFile(String args[]) throws IOException, URISyntaxException{
		// 文件路径
		String pathStr = args[0];
		
		// 根据文件路径得到文件系统
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(new URI(pathStr), conf);
		
		// 根据文件路径得到相应的 Codec
		CompressionCodecFactory factory = new CompressionCodecFactory(conf);
		CompressionCodec codec = factory.getCodec(new Path(pathStr));
		
		// 解压缩到取出后缀名的文件中
		String outputPathStr = CompressionCodecFactory.removeSuffix(pathStr, codec.getDefaultExtension());
		
		// 输入与输出流
		OutputStream out = null;
		InputStream in = null;
		try{
			in = codec.createInputStream(fs.open(new Path(pathStr)));
			out = fs.create(new Path(outputPathStr));
			IOUtils.copyBytes(in, out, 4096, false);
		}
		finally{
			IOUtils.closeStream(in);
			IOUtils.closeStream(out);
		}
	}
	
	// 主函数
	public static void main(String[] args) throws IOException, URISyntaxException{
		// decompressLocalFile(args);
		decompressDFSFile(args);
	}
}
