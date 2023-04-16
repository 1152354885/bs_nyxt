package com.me.test.pojo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

@Data
@Setter
@Getter
//使用@AllArgsConstructor自动生成有参构造
@AllArgsConstructor
//使用@NoArgsConstructor自动生成无参构造
@NoArgsConstructor
public class Datainfo_datainfo {
    @TableField(value = "temperature")
    private  Integer temperature;
    @TableId(type= IdType.AUTO)
    private  Integer id;
    @TableField(value = "humidity")
    private  Integer humidity;
    private  Integer Co2;
    private  Integer light;
    private  String  time;
}
