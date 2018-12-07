package dotting.timer.check.dao;

import dotting.timer.core.annos.DottingNode;
import org.springframework.stereotype.Component;

/**
 * Create by 18073 on 2018/12/7.
 */
@Component
public class TestDao {

    @DottingNode(expect = 10)
    public String getResultDao11() {
        try {
            Thread.sleep(10L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "resultDao11";
    }

    @DottingNode(expect = 10)
    public String getResultDao12() {
        try {
            Thread.sleep(10L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "resultDao12";
    }

    @DottingNode(expect = 10)
    public String getResultDao21() {
        try {
            Thread.sleep(10L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "resultDao21";
    }

    @DottingNode(expect = 10)
    public String getResultDao22() {
        try {
            Thread.sleep(10L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "resultDao22";
    }

}
