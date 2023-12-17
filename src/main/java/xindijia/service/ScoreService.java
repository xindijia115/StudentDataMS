package xindijia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xindijia.entity.Score;

import java.util.List;

/**
 * @author xia
 * @since 2023/12/16/12:15
 */
public interface ScoreService extends IService<Score> {
    List<Score> getScoreList();

    Score getScoreBySidAndCid(int sid, int cid);

    void saveScore(Score score);

    void updateScore(Score score);

    void deleteScore(int sid, int cid);
}
