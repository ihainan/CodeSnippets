/*
 *	Filename : MaxTemperatureReducer.java
 *	Author : ihainan
 *	E-mail : ihainan72@gmail.com
 *	Website : http://www.ihainan.me
 *	Date : 2014/08/20
 *	Description : 
 *		The implementation of Reducer
 *	Reference : 
 *		Hadoop : The Definitive Guide
 */

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

// Reducer
public class MaxTemperatureReducer extends MapReduceBase
	implements Reducer<Text, IntWritable, Text, IntWritable>{

	@Override
	public void reduce(Text key, Iterator<IntWritable> values,
			OutputCollector<Text, IntWritable> collector, Reporter reporter)
			throws IOException {
		
		// 获取温度最大值
		int maxValue = Integer.MIN_VALUE;
		while(values.hasNext()){
			maxValue = Math.max(maxValue, values.next().get());
		}
		
		// 存储在 collector 中
		collector.collect(key, new IntWritable(maxValue));
	}

}
