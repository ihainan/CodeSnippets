/*
 *	Filename : TextPair.java
 *	Author : ihainan
 *	E-mail : ihainan72@gmail.com
 *	Website : http://www.ihainan.me
 *	Date : 2014/08/22
 *	Description : 
 *		A simple application used to implement a custom Writale Class.
 *	Reference : 
 *		Hadoop : The Definitive Guide
 *	Note : 
 *		It is very boring to read this kind of API Reference Book……
 */

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

public class TextPair implements WritableComparable<TextPair>{
	private Text first;
	private Text second;
	
	// 构造函数 - 不带参数
	public TextPair() {
		first = new Text();
		second = new Text();
	}
	
	// 构造函数 - 指定 Text 类型
	public TextPair(Text t1, Text t2){
		first = t1;
		second = t2;
	}
	
	// 构造函数 - 指定 String 类型
	public TextPair(String t1, String t2){
		first = new Text(t1);
		second = new Text(t2);
	}
	
	// 赋值
	public void set(Text t1, Text t2){
		first = t1;
		second = t2;
	}
	
	// 获取第一个元素
	public Text getFirst(){
		return first;
	}
	
	// 获取第二个元素
	public Text getSecond(){
		return second;
	}
	
	@Override
	public void readFields(DataInput in) throws IOException {
		// TODO Auto-generated method stub
		first.readFields(in);
		second.readFields(in);
	}

	@Override
	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		first.write(out);
		second.write(out);
	}

	@Override
	public int compareTo(TextPair pair) {
		// TODO Auto-generated method stub
		int cmp = first.compareTo(pair.first);
		if(cmp != 0){
			return cmp;
		}
		else{
			return second.compareTo(pair.second);
		}
	}
	
	@Override
	public int hashCode(){
		return first.hashCode() * 163 + second.hashCode();
	}
	
	@Override
	public boolean equals(Object o){
		return false;
	}
	
	@Override
	public String toString(){
		return first.toString() + "\t" + second.toString();
	}
}
