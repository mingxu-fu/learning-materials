package com.yingxue.lesson.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: ValueItemRespVO
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
@Data
public class ValueItemRespVO {
    @ApiModelProperty(value = "属性id")
    private String valueId;
    @ApiModelProperty(value = "商品规格属性名称")
    private String valueName;
    @ApiModelProperty(value = "商品规格属性类型名称")
    private String typeName;
    @ApiModelProperty(value = "商品规格属性类型")
    private String type;

    public String getValueId() {
        return valueId;
    }

    public void setValueId(String valueId) {
        this.valueId = valueId;
    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
