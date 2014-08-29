/*
 *	Filename : HelloAvro.java
 *	Author : ihainan
 *	E-mail : ihainan72@gmail.com
 *	Website : http://www.ihainan.me
 *	Date : 2014/08/29
 *	Description : 
 *		A simple application used to practise the Avro  System.
 *	Reference : 
 *		Hadoop : The Definitive Guide
 *	Note : 
 *		It is very boring to read this kind of API Reference Book……
 */

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.util.Utf8;


public class HelloAvro {	
	// Avro Datum Writer
	private static void DatumWriterDemo(String[] args) throws IOException{
		// 从本地 Avsc 模式文件中读取得到 Schema
		Schema schema = Schema.parse(new File(args[0]));
		
		// 创建 Avro Record 对象并添加数据
		GenericRecord datum = new GenericData.Record(schema);
		datum.put("left", new Utf8("a"));
		datum.put("right", new Utf8("c"));
		GenericRecord datum2 = new GenericData.Record(schema);
		datum2.put("left", new Utf8("b"));
		datum2.put("right", new Utf8("d"));
		
		// 获取文件对象，文件指向需要写入数据的文件
		File file = new File(args[1]);
		
		// 实例化 DatumWriter
		DatumWriter<GenericRecord> writer = new GenericDatumWriter(schema);
		
		// 实例化 DataFileWriter
		DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<GenericRecord>(writer);
		
		// 根据 schema 和 file 创建 writer
		dataFileWriter.create(schema, file);
		
		// 往 writer 中添加数据
		dataFileWriter.append(datum2);
		dataFileWriter.append(datum);
		
		// 关闭 writer
		dataFileWriter.close();
		
		System.out.println("写入文件 " + args[1] + " 成功");
	}
	
	// Avro Datum Reader
	private static void DatumReaderDemo(String[] args) throws IOException{
		System.out.println("读取文件 " + args[1]);

		// 实例化 DatumReader 对象，无需指定 schema
		DatumReader<GenericRecord> reader = new GenericDatumReader<GenericRecord>();
		
		// 实例化 DataFileReader
		DataFileReader<GenericRecord> dataFileReader = new DataFileReader<GenericRecord>(new File(args[1]), reader);
		
		// 读取数据
		while(dataFileReader.hasNext()){
			GenericRecord record = dataFileReader.next();
			System.out.println(record.get("left") + " " + record.get("right"));
		}
		
		// 关闭 Reader
		dataFileReader.close();
	}
	
	// main function
	public static void main(String[] args) throws IOException{
		// SeriAndDeSeriDemo(args);
		DatumWriterDemo(args);
		DatumReaderDemo(args);
	}
}
