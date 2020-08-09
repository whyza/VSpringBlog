package com.whyzaa.vspringblog.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Description:
 * date: 2020/8/3 11:07
 *
 * @author whyzaa<br />
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class imageVo implements Serializable {
    private String imgName;
    private String url;
}
