package com.jj.foundation;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

public class BlockingQueueTest2 {

	/**
	 * 定义容器
	 *     放苹果的篮子
	 * @author Admintrator
	 *
	 */
	public class Basket{
		//容器队列
		BlockingQueue<String> basket = new LinkedBlockingDeque<String>(3);
		
		//生产
		public void produce() throws InterruptedException{
			basket.put("An apple");
		}
		//消费
		public String consume() throws InterruptedException{
			return basket.take();
		}
	}
	
	
	//生产者
	class Producer implements Runnable{
		private String instance;
		private Basket basket;
		
		public Producer(String instance,Basket basket){
			this.instance = instance;
			this.basket = basket;
		}
		
		public void run() {
			try {
				while(true){
					System.out.println("生产者准备生产苹果："+instance);
					basket.produce();
					System.out.println("生产者生产完毕："+instance);
					Thread.sleep(1000);
				}
			} catch (InterruptedException e) {
				System.out.println("Producer Interrupted");
			}
		}
		
	}
	
	//定义苹果消费者
	class Consumer implements Runnable{
		private String instance;
		private Basket basket;
		
		public Consumer(String instance,Basket basket){
			this.instance = instance;
			this.basket = basket;
		}
		
		public void run() {
			try {
				while(true){
					System.out.println("消费者准备消费苹果："+instance);
					System.out.println(basket.consume());
					System.out.println("消费者消费完毕："+instance);
					Thread.sleep(5000);
				}
			} catch (InterruptedException e) {
				System.out.println("Producer Interrupted");
			}
		}
	}
	
	public static void main(String[] args) {
		BlockingQueueTest2 test = new BlockingQueueTest2();
		
		Basket basket = test.new Basket();
		
		ExecutorService service = Executors.newCachedThreadPool();
		Producer producer = test.new Producer("生产者001", basket);
		Producer producer2 = test.new Producer("生产者002", basket);
		Consumer consumer = test.new Consumer("消费者001", basket);
		service.submit(producer);
		service.submit(producer2);
		service.submit(consumer);
	}
}
