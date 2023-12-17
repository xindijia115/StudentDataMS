package xindijia.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xia
 * @since 2023/12/16/11:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "score")
public class Score {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("sid")
    private Integer sid;
    @TableField("course_id")
    private Integer courseId;
    @TableField("score")
    private Double score;
    @TableField("credit")
    private Double credit;

    public Score(Integer sid, Integer courseId, Double score) {
        this.sid = sid;
        this.courseId = courseId;
        this.score = score;
    }
}
