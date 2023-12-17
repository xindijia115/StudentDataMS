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
 * @since 2023/12/16/11:59
 */
@TableName(value = "student")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @TableId(value = "sid", type = IdType.AUTO)
    private Integer sid;
    @TableField("s_class")
    private String sClass;
    @TableField("name")
    private String name;
    @TableField("gender")
    private String gender;
    @TableField("birth")
    private String birth;
    @TableField("major")
    private String major;
}
