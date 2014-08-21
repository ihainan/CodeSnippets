/*
 *	Filename : StreamCompressor.java
 *	Author : ihainan
 *	E-mail : ihainan72@gmail.com
 *	Website : http://www.ihainan.me
 *	Date : 2014/08/21
 *	Description : 
 *		A simple application used to compress stream data.
 *	Reference : 
 *		Hadoop : The Definitive Guide
 *	Note : 
 *		It is very boring to read this kind of API Reference Book……
 */

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CodecPool;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.io.compress.Compressor;
import org.apache.hadoop.util.ReflectionUtils;

public class StreamCompressor {
	// 不适用 CodecPool 进行压缩
	private static void compressorWithoutCodecPool(String[] args) throws ClassNotFoundException, IOException{
		// 获取压缩类型（DEFLATE, gzip, bzip2, LZO）
		String className = args[0];
		
		// 根据压缩类型得到相应的压缩编码器类（Codec Class）
		Class<?> codecClass = Class.forName(className);
		
		// 实例化编码器
		Configuration conf = new Configuration();
		CompressionCodec codec = (CompressionCodec) 
				ReflectionUtils.newInstance(codecClass, conf);
		
		// 对输出数据进行压缩，得到 CompressionOutputStream
		CompressionOutputStream out = codec.createOutputStream(System.out);
		
		// 复制标准输入到 CompressionOutputStream 中
		IOUtils.copyBytes(System.in, out, 4096, false);
		
		// 不关闭数据流（？）
		out.finish();
	}
	
	private static void compressorWithCodecPool(String[] args) throws ClassNotFoundException, IOException{
		// 获取压缩类型（DEFLATE, gzip, bzip2, LZO）
		String className = args[0];
		
		// 根据压缩类型得到相应的压缩编码器类（Codec Class）
		Class<?> codecClass = Class.forName(className);
		
		// 实例化编码器
		Configuration conf = new Configuration();
		CompressionCodec codec = (CompressionCodec) 
				ReflectionUtils.newInstance(codecClass, conf);
		
		Compressor compressor = null;
		try{
			// 根据 codec 获得 compressor
			compressor = CodecPool.getCompressor(codec);
			
			// 得到 
			CompressionOutputStream out = codec.createOutputStream(System.out, compressor);
			IOUtils.copyBytes(System.in, out, 4096, false);
		}
		finally{
			CodecPool.returnCompressor(compressor);
		}
	}
	
	// 主函数
	public static void main(String[] args) throws ClassNotFoundException, IOException{
		// compressorWithoutCodecPool(args);
		compressorWithCodecPool(args);
	}
}
