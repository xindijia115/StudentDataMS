package xindijia.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xia
 * @since 2023/12/17/15:40
 */
@TableName(value = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String number;
    private String password;
}
