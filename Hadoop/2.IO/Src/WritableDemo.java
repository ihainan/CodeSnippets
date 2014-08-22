/*
 *	Filename : WritableDemo.java
 *	Author : ihainan
 *	E-mail : ihainan72@gmail.com
 *	Website : http://www.ihainan.me
 *	Date : 2014/08/22
 *	Description : 
 *		A simple application used to practise the Writable Classes of Hadoop.
 *	Reference : 
 *		Hadoop : The Definitive Guide
 *	Note : 
 *		It is very boring to read this kind of API Reference Book……
 */

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.io.RawComparator;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.VIntWritable;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparator;
import org.apache.hadoop.util.StringUtils;

public class WritableDemo {
	
	// 主函数
	public static void main(String[] args) throws IOException{	
		// SerializeTest();
		// VIntegerTest();
		// TextTest();
		// ByteWriableTest();
		// WritableArrayTest();
		CustomWritableTest();
	}
	
	// 自定义 Writable 测试
	private static void CustomWritableTest(){
		TextPair pair = new TextPair("A", "B");
		TextPair pair2 = new TextPair("A", "B");
		System.out.println(pair.compareTo(pair2));
	}
	
	// WriableArray 测试
	private static void WritableArrayTest(){
		// 写入和获取
		ArrayWritable array  = new ArrayWritable(Text.class);
		Text[] textArray = new Text[10];
		textArray[0] = new Text("Hello World.");
		array.set(textArray);
		
		// MapWriable
		MapWritable src = new MapWritable();
		src.put(new IntWritable(1), new Text("Hello World"));
		Text t = (Text) src.get(new IntWritable(1));
		System.out.println(t.toString());
	}
	
	// ByteWriable 测试
	private static void ByteWriableTest() throws IOException{
		BytesWritable wriable = new BytesWritable(new byte[] {3, 5});
		byte[] data = serialize(wriable);
		System.out.println(StringUtils.byteToHexString(data));
	}
	
	// 序列化测试
	private static void SerializeTest() throws IOException{
		// 序列化测试
		IntWritable i = new IntWritable();
		i.set(300);
		byte[] data = serialize(i);
		for(byte d : data){
			System.out.println(d);
		}
		
		// 反序列化测试
		IntWritable i2 = new IntWritable();
		deserialize(i2, data);
		System.out.println(i2.get());
		
		// 比较两个 Writable 的大小
		// i2 = new IntWritable(i.get() + 1);
		RawComparator<IntWritable> comparator = WritableComparator.get(IntWritable.class);
		System.out.println(comparator.compare(i, i2));	

	}
	
	// 变长 int 测试
	private static void VIntegerTest() throws IOException{
		// 变长 int 测试
		VIntWritable i3 = new VIntWritable();
		i3.set(128);
		byte[] data = serialize(i3);
		System.out.println(StringUtils.byteToHexString(data));
	}
	
	// Text 测试
	private static void TextTest(){
		// Text 测试
		System.out.println();
		Text t = new Text("你好呀.");
		System.out.println(t.getLength() + " " + "你要呀.".length());
		System.out.println(t.getBytes().length);
		System.out.println((char)t.charAt(1));
		System.out.println(t.getBytes()[t.getLength()]);
		System.out.println(t.charAt(100));
		
		// 比较 Text 和 String 的异同
		System.out.println();
		String tmpS = "\u0041\u00DF\u6771\uD801\uDC00";
		System.out.println(tmpS);
		System.out.println(tmpS.indexOf("\u0041"));
		System.out.println(tmpS.indexOf("\uD801\uDC00"));
		
		Text t2 = new Text(tmpS);
		System.out.println();
		System.out.println(t2.find("\uD801\uDC00"));
		
		// 遍历 Text
		System.out.println();
		TextInterator(t2);
		
		// Text.getLength() 与 Text.getBytes().length 可能不一致（我操明明是一致的！）
		System.out.println();
		Text t3 = new Text("Hadoop");
		t3.set("Pig");
		System.out.println(t3.getLength());
		System.out.println(t3.getBytes().length);
	}
	
	// 遍历 Text
	private static void TextInterator(Text text){
		ByteBuffer buffer = ByteBuffer.wrap(text.getBytes(), 0, text.getLength());
		int cp = 0;
		while(buffer.hasRemaining() && (cp = Text.bytesToCodePoint(buffer)) != -1){
			System.out.println(Integer.toHexString(cp));
		}
	}
	
	// 序列化 ： Wriable 转化成 字节流（byte[]）
	private static byte[] serialize(Writable writable) throws IOException{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		DataOutputStream dataOut = new DataOutputStream(out);
		writable.write(dataOut);
		dataOut.close();
		return out.toByteArray();
	}
	
	// 反序列化：字节流(byte[]) 存入 Wriable 中
	private static void deserialize(Writable writable, byte[] data) throws IOException{
		ByteArrayInputStream in = new ByteArrayInputStream(data);
		DataInputStream dataIn = new DataInputStream(in);
		writable.readFields(dataIn);
	}
}
