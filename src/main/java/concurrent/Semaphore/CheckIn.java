package concurrent.Semaphore;

import java.util.Random;
import java.util.concurrent.Semaphore;

import static javafx.scene.input.KeyCode.R;

/**
 * ClassName: CheckIn <br/>
 * Description: <br/>
 * date: 2019/5/31 17:15<br/>
 *
 * @author HuJian<br />
 * @since JDK 1.8
 */
public class CheckIn implements Runnable{

    private  static Semaphore semaphore = new Semaphore(3);

    public void run() {

        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread thread = Thread.currentThread();
        long l = System.currentTimeMillis();

        System.out.println(thread.getName()+":开始检票");
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thread.getName()+":检票完成，共花费："+(System.currentTimeMillis()-l)+"ms");
        semaphore.release();
    }
}
