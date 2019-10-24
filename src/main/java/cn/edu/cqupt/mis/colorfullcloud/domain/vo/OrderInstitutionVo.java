package cn.edu.cqupt.mis.colorfullcloud.domain.vo;

import cn.edu.cqupt.mis.colorfullcloud.domain.entity.PictureEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author :DengSiYuan
 * @date :2019/9/7 8:52
 * @desc :
 */
@Data
@ToString
@ApiModel(value ="orderInstitutionVo", description = "订单机构展示信息")
public class OrderInstitutionVo {

    @ApiModelProperty(name = "institutionId",value = "机构id",dataType = "int")
    private Integer institutionId;
    @ApiModelProperty(name = "institutionName",value = "机构名称",dataType = "string")
    private String institutionName;
    @ApiModelProperty(name = "address",value = "机构地址",dataType = "string")
    private String address;
    @ApiModelProperty(name = "leader",value = "联系人",dataType = "string")
    private String leader;
    @ApiModelProperty(name = "phone",value = "联系方式",dataType = "int")
    private String phone;
    @ApiModelProperty(name = "icon", value = "机构icon地址",dataType = "string")
    private String icon;
    @ApiModelProperty(name = "introduction",value = "机构详细介绍",dataType = "introduction")
    private String introduction;
    @ApiModelProperty(name = "orderCourseVoList",value = "开设课程",dataType = "list")
    private List<OrderCourseVo> orderCourseVoList;

}
