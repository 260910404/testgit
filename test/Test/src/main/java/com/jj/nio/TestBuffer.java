package com.jj.nio;

import io.netty.bootstrap.ServerBootstrap;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.WriteAbortedException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.RandomAccess;
import java.util.concurrent.Executors;

import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

public class TestBuffer {
	
	public static void main(String[] args) throws IOException {
//		TestBuffer.TestIntBuffer();
//		TestBuffer.TestChannel();
//		TestBuffer.testBufferAndChannel();
//		TestBuffer.testBuffer2();
//		TestBuffer.testBuffer3();
//		TestBuffer.testBufferReadonly();
//		TestBuffer.testBufferDirect();
//		TestBuffer.testBufferMapped();
	}
	
	/**
	 * 测试Buffer用法
	 */
	public static void TestIntBuffer(){
		IntBuffer buffer = IntBuffer.allocate(8);
		
		for (int i = 0; i < buffer.capacity(); i++) {
			int j=2*(i+1);
			buffer.put(j);
		}
		
		buffer.flip();
		
		while (buffer.hasRemaining()) {
			int j=buffer.get();
			System.out.print(j+" ");
		}
	}
	
	/**
	 * 测试channel
	 * @throws IOException
	 */
	public static void TestChannel() throws IOException {
		FileInputStream fin = new FileInputStream("c:\\log_network.txt");
		
		//获取通道
		FileChannel fc = fin.getChannel();
		
		//创建缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		
		fc.read(buffer);
		buffer.flip();
		
		while (buffer.remaining()>0) {
			byte b = buffer.get();
//			System.out.print(b);
			System.out.print(((char)b));
		}
		fin.close();
	}
	
	/**
	 * 通过缓冲区写入NIO
	 * @throws IOException 
	 */
	public static void testBufferAndChannel() throws IOException{
		final byte message[] = {65,66,67,68,69,70,71};
		FileOutputStream fout = new FileOutputStream("f:\\channelOut.txt");
		FileChannel fc = fout.getChannel();
		ByteBuffer buf = ByteBuffer.allocate(1024);
		for (int i = 0; i < message.length; i++) {
			buf.put(message[i]);
		}
		buf.flip();
		fc.write(buf);
		fout.close();
	}
	
	/**
	 * 测试buffer属性用法
	 * @throws IOException 
	 */
	public static void testBuffer2() throws IOException{
		FileInputStream fin = new FileInputStream("f:\\channelOut.txt");
		FileChannel fc = fin.getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(10);
		printBufferAttr("初始化", buffer);
		
		fc.read(buffer);
		printBufferAttr("调用read()", buffer);
		
		buffer.flip();
		printBufferAttr("调用flip()", buffer);
		
		while (buffer.remaining()>0) {
			byte b = buffer.get();
		}

		printBufferAttr("调用get()", buffer);
		
		buffer.clear();
		printBufferAttr("调用clear()", buffer);
		
		fin.close();
	}
	
	public static void printBufferAttr(String step , Buffer buffer){
		System.out.println(step+" : ");
		System.out.print("capacity: " +buffer.capacity()+", ");
		System.out.print("position: " +buffer.position()+", ");
		System.out.println("limit: " +buffer.limit()+", ");
		System.out.println();
	}
	
	/**
	 * 测试Buffer属性用法
	 * @throws IOException
	 */
	public static void testBuffer3() throws IOException{
		ByteBuffer buffer = ByteBuffer.allocate(10);
		for (int i = 0; i < buffer.capacity(); i++) {
			buffer.put((byte)i);
		}
		
		buffer.position(3);
		buffer.limit(7);
		ByteBuffer slice = buffer.slice();
		
		for (int i = 0; i < slice.capacity(); i++) {
			byte b = slice.get(i);
			b*=10;
			slice.put(i,b);
		}
		
		buffer.position(0);
		buffer.limit(buffer.capacity());
		
		while (buffer.remaining()>0) {
			System.out.println(buffer.get() +" , ");
		}
		
	}
	
	/**
	 * 测试Buffer readonly
	 * @throws IOException
	 */
	public static void testBufferReadonly() throws IOException{
		ByteBuffer buffer = ByteBuffer.allocate(10);
		for (int i = 0; i < buffer.capacity(); i++) {
			buffer.put((byte)i);
		}
		//创建只读缓冲区
		ByteBuffer readBuffer = buffer.asReadOnlyBuffer();
		
		//改变原缓冲区内容
		for (int i = 0; i < buffer.capacity(); i++) {
			byte b = buffer.get(i);
			b*=10;
			buffer.put(i,b);
		}
		
		readBuffer.position(0);
		readBuffer.limit(buffer.capacity());
		
		while (readBuffer.remaining()>0) {
			System.out.println(readBuffer.get() +" , ");
		}
	}
	
	/**
	 * 直接缓冲区
	 * @throws IOException
	 */
	public static void testBufferDirect() throws IOException{
		FileInputStream fin = new FileInputStream("f:\\channelOut.txt");
		FileChannel fcin = fin.getChannel();
		
		FileOutputStream fout = new FileOutputStream("f:\\testOut.txt");
		FileChannel fcout = fout.getChannel();
		
		ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
		
		while (true) {
			buffer.clear();
			int r = fcin.read(buffer);
			
			if (r==-1) {
				break;
			}
			buffer.flip();
			fcout.write(buffer);
		}
	}
	
	

	/**
	 * 内存映射文件I/O
	 * @throws IOException
	 */
	public static void testBufferMapped() throws IOException{
		RandomAccessFile raf = new RandomAccessFile("f:\\channelOut.txt", "rw");
		FileChannel fc = raf.getChannel();
		
		int start=0,size=26;
		
		MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_WRITE, start, size);
		mbb.put(0,(byte)96);
		mbb.put(25,(byte)122);
		raf.close();
	}
	
	public static void startNetty(){
		
		
		
	}
}












