/*
 *	Filename : MaxTemperatureMapper.java
 *	Author : ihainan
 *	E-mail : ihainan72@gmail.com
 *	Website : http://www.ihainan.me
 *	Date : 2014/08/20
 *	Description : 
 *		The implementation of Mapper
 *	Reference : 
 *		Hadoop : The Definitive Guide
 */

import java.io.IOException;

import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

// Mapper
public class MaxTemperatureMapper extends MapReduceBase
	implements Mapper<LongWritable, Text, Text, IntWritable>{

	private final int MISSING = 9999;
	@Override
	public void map(LongWritable key, Text value,
			OutputCollector<Text, IntWritable> collector, Reporter reporter)
			throws IOException {
		// 获取数据
		String line = value.toString();
		
		// 获取年份
		String year =  line.substring(15, 19);
		
		// 获取温度
		int airTemperature;
		if(line.charAt(87) == '+'){
			airTemperature = Integer.parseInt(line.substring(88, 92));
		}
		else{
			airTemperature = Integer.parseInt(line.substring(87, 92));
		}
		
		// 检测温度值是否合法，合法值存在 collector 中:
		String quality = line.substring(92, 93);
		if(airTemperature != MISSING && quality.matches("[01459]")){
			collector.collect(new Text(year), new IntWritable(airTemperature));
		}
	}
}
