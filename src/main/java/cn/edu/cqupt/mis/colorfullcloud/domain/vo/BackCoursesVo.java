package cn.edu.cqupt.mis.colorfullcloud.domain.vo;

import cn.edu.cqupt.mis.colorfullcloud.domain.entity.ChildrenEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author :DengSiYuan
 * @date :2019/11/7 20:34
 * @desc :
 */
@Data
@ToString
@ApiModel("已订购课程展示层")
public class BackCoursesVo {

    @ApiModelProperty(name = "buyerName",value = "购买者昵称",dataType = "string")
    private String buyerName;
    @ApiModelProperty(name = "children",value = "孩子信息")
    private ChildrenEntity children;
    @ApiModelProperty(name = "institutionName",value = "机构名称",dataType = "string")
    private String institutionName;
    @ApiModelProperty(name = "courseName",value = "课程名称",dataType = "string")
    private String courseName;
    @ApiModelProperty(name = "count",value = "数量",dataType = "int")
    private Integer count;
    @ApiModelProperty(name = "cycleNum",value = "课时数量",dataType = "int")
    private Integer cycleNum;
    @ApiModelProperty(name = "isActivity",value = "是否活动订单(0:否,1:是)",dataType = "int")
    private Integer isActivity;

}
