/*
 *	Filename : WordCount.java
 *	Author : ihainan
 *	E-mail : ihainan72@gmail.com
 *	Website : http://www.ihainan.me
 *	Date : 2014/08/20
 *	Description : 
 *		A simple application that counts the number of occurrences of each word in a given input set.
 *	Reference : 
 *		http://hadoop.apache.org/docs/current/hadoop-mapreduce-client/hadoop-mapreduce-client-core/MapReduceTutorial.html#Example:_WordCount_v1.0
 */

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


// 单词数目统计器
public class WordCount {
	
	// 实现 Mapper
	public static class TokenizerMapper extends Mapper<LongWritable, Text, Text, LongWritable>{
		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException{
			// 分词器
			StringTokenizer tokenizer = new StringTokenizer(value.toString());
			while(tokenizer.hasMoreTokens()){
				context.write(new Text(tokenizer.nextToken()), 
						new LongWritable(1));
			}
		}
	}
	
	// 实现 Reducer
	public static class TokenizerReducer extends Reducer<Text, LongWritable, Text, LongWritable>{
		public void reduce(Text key, Iterable<LongWritable> values, Context context) 
				throws IOException, InterruptedException{
			// 直接计数
			long sum = 0;
			for(LongWritable value : values){
				sum = sum + value.get();
			}
			
			// 添加到 context 中
			context.write(key, new LongWritable(sum));
		}
	}
	
	// 主函数
	public static void main(String args[]) throws IOException, ClassNotFoundException, InterruptedException{
		// 检查参数数目
		if(args.length != 2){
			System.err.println("Usage : WordCount <input path> <output path>");
			System.exit(-1);
		}
		
		// 实例化 Configuration 类
	    Configuration conf = new Configuration();
		
		// 新建 Job
		Job job = Job.getInstance(conf, "Word Counter");
		
		// 设置 Reducer 的数量
		job.setNumReduceTasks(2);
		
		// 指定 Jar、Mapper、Combiner, Reducer 对应类
		job.setJarByClass(WordCount.class);
		job.setMapperClass(TokenizerMapper.class);
		// job.setCombinerClass(TokenizerReducer.class);
		job.setReducerClass(TokenizerReducer.class);
		
		// 设置输出键值类型
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);
		
		// 设置输入输出文件路径
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		// 运行任务，系统等待返回
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
