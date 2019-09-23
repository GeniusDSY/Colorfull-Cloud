package cn.edu.cqupt.mis.colorfullcloud.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author :DengSiYuan
 * @date :2019/8/28 18:53
 * @desc :
 */
@ApiModel(value = "productVo",description = "产品展示模型")
@Data
@ToString
public class ProductVo {

    @ApiModelProperty(name = "type",value = "商品展示方式",dataType = "int")
    private Integer type;
    @ApiModelProperty(name = "institutionVoList",value = "机构列表",dataType = "list")
    private List<InstitutionVo> institutionVoList;

}
