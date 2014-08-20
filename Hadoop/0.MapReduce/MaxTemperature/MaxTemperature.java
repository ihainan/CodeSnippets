/*
 *	Filename : MaxTemperature.java
 *	Author : ihainan
 *	E-mail : ihainan72@gmail.com
 *	Website : http://www.ihainan.me
 *	Date : 2014/08/20
 *	Description : 
 *		A simple application that calculates the highest temperature of each year.
 *	Reference : 
 *		Hadoop : The Definitive Guide
 */

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;

public class MaxTemperature {

	// 主函数
	public static void main(String[] args) throws IOException{
		// 检测参数个数
		if(args.length != 2){
			System.err.println("Usage MaxTemperature <input path> <output path>");
			System.exit(-1);
		}
		
		// 新建 JonConf，设置任务名称
		JobConf conf = new JobConf(MaxTemperature.class);
		conf.setJobName("Max Temperature");
		
		// 设置 Input Format 和 Out Format
		FileInputFormat.addInputPath(conf, new Path(args[0]));
		FileOutputFormat.setOutputPath(conf, new Path(args[1]));
	
		// 指定 Mapper 和 Reducer 类型
		conf.setMapperClass(MaxTemperatureMapper.class);
		conf.setReducerClass(MaxTemperatureReducer.class);
		
		// 指定输出键值对类型
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(IntWritable.class);
		
		// 运行任务
		JobClient.runJob(conf);
	}
}
