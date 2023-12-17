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
 * @since 2023/12/16/12:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "course")
public class Course {
    @TableId(value = "cid", type = IdType.AUTO)
    private Integer cid;
    @TableField("major")
    private String major;
    @TableField("course_name")
    private String courseName;
    @TableField("type")
    private String type;
    @TableField("start_term")
    private String startTerm;
    @TableField("period")
    private String period;
    @TableField("credit")
    private Double credit;
}
