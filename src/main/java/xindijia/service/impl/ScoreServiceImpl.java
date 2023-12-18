package xindijia.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.session.SqlSession;
import xindijia.entity.Score;
import xindijia.mapper.ScoreMapper;
import xindijia.service.ScoreService;
import xindijia.utils.MybatisUtil;

import java.util.List;

/**
 * @author xia
 * @since 2023/12/16/12:17
 */
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score> implements ScoreService {
    @Override
    public List<Score> getScoreList() {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        ScoreMapper mapper = sqlSession.getMapper(ScoreMapper.class);
        return mapper.selectList(null);
    }

    @Override
    public Score getScoreBySidAndCid(int sid, int cid) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        ScoreMapper mapper = sqlSession.getMapper(ScoreMapper.class);
        LambdaQueryWrapper<Score> scoreLambdaQueryWrapper = new LambdaQueryWrapper<>();
        scoreLambdaQueryWrapper.eq(Score::getSid, sid).eq(Score::getCourseId, cid);
        return mapper.selectOne(scoreLambdaQueryWrapper);
    }

    @Override
    public void saveScore(Score score) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        ScoreMapper mapper = sqlSession.getMapper(ScoreMapper.class);
        mapper.insert(score);
        sqlSession.commit();
    }

    @Override
    public void updateScore(Score score) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        ScoreMapper mapper = sqlSession.getMapper(ScoreMapper.class);
        mapper.updateById(score);
    }

    @Override
    public void deleteScore(int sid, int cid) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        ScoreMapper mapper = sqlSession.getMapper(ScoreMapper.class);
        LambdaUpdateWrapper<Score> scoreLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        scoreLambdaUpdateWrapper.eq(Score::getSid, sid).eq(Score::getCourseId, cid);
        mapper.delete(scoreLambdaUpdateWrapper);
        sqlSession.commit();
    }

    @Override
    public Double getTotalCreditById(int sid) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        ScoreMapper mapper = sqlSession.getMapper(ScoreMapper.class);
        return mapper.getTotalCreditBySid(sid);
    }

    @Override
    public Integer countByCategoryScore(int start, int end, int clazzId, int courseId) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        ScoreMapper mapper = sqlSession.getMapper(ScoreMapper.class);
        return mapper.countByCategoryScore(start, end, clazzId, courseId);
    }
}
