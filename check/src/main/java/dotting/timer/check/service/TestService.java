package dotting.timer.check.service;

import dotting.timer.check.dao.TestDao;
import dotting.timer.core.annos.DottingNode;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Create by 18073 on 2018/12/7.
 */
@Service
public class TestService {

    @Resource
    private TestDao testDao;

    @DottingNode(expect = 20)
    public String getResultService1() {
        StringBuilder sb = new StringBuilder();
        sb.append(testDao.getResultDao11())
                .append(testDao.getResultDao12());
        return sb.toString();
    }

    @DottingNode(expect = 20)
    public String getResultService2() {
        StringBuilder sb = new StringBuilder();
        sb.append(testDao.getResultDao21())
                .append(testDao.getResultDao22());
        return sb.toString();
    }

    @DottingNode(expect = 30)
    public void loopService1() {
        for (int i = 0; i < 21; i++) {
            testDao.loopDao1();
        }
    }
}
