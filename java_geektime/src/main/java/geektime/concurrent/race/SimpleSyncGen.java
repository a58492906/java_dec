package geektime.concurrent.race;

import java.util.Random;
import java.util.concurrent.locks.*;

public class SimpleSyncGen implements GenRunnable {
	private ReentrantLock lock = new ReentrantLock(true);
	SimpleShareData ssd;
	int size;
	int offset;
	public SimpleSyncGen(SimpleShareData ssd, int size, int offset) {
		this.ssd = ssd;
		this.size = size;
		this.offset = offset;
	}

	@Override
	public void run() {
		gen();
	}

	@Override
	public void gen() {
		long mils=System.currentTimeMillis();
		Random rand = new Random(System.currentTimeMillis());
		int r;
		int i;
		for (i = 0; i < size; i++) {
			r = rand.nextInt(SimpleShareData.COUNT);
			ssd.queue.offer(r);
		}
		ssd.drainToScore();
		//

		ssd.getGenSig().countDown();
	}

}
