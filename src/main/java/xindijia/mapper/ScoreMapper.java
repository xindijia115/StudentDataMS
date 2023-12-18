package xindijia.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import xindijia.entity.Score;

/**
 * @author xia
 * @since 2023/12/16/12:14
 */
public interface ScoreMapper extends BaseMapper<Score> {
    Double getTotalCreditBySid(int sid);

    Integer countByCategoryScore(@Param("start") int start, @Param("end")int end, @Param("clazzId")int clazzId, @Param("courseId")int courseId);
}
