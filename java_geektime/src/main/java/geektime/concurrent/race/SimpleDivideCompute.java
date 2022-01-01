package geektime.concurrent.race;

import java.util.*;
import java.util.stream.*;

public class SimpleDivideCompute implements ComputeRunnable {

	SimpleShareData ssd;
	int size;
	int offset;
	public SimpleDivideCompute(SimpleShareData ssd, int size, int offset) {
		this.ssd = ssd;
		this.size = size;
		this.offset = offset;
	}
	
	@Override
	public void run() {
		go();

	}

	@Override
	public void go() {
		long ms=System.currentTimeMillis();
	//	System.out.println("开始计算随机数: " + size + " " + offset);
		int[] alist = new int[size];
		List<Integer>  list =ssd.getScore();
		//List<Integer> myList = list.stream().collect(Collectors.toCollection(LinkedList::new));
		for (int i = 0; i < size; i++) {
			//System.out.println("计算随机数一次: " +(System.currentTimeMillis()-ms)) ;
		//	alist[i] = list.indexOf(offset * size + i);
	     	alist[i] = list.get(offset * size + i);
		}
		Arrays.sort(alist);
		//System.out.println("计算随机数完毕: " +(System.currentTimeMillis()-ms)) ;
		for (int i = 0; i < SimpleShareData.BUFSIZE * size / SimpleShareData.COUNT; i++) {
			//System.out.println("随机数: " + alist[alist.length - i - 1]);
			ssd.addExchange(alist[alist.length - i - 1]);
		}
		ssd.getCompSig().countDown();
	}
}
