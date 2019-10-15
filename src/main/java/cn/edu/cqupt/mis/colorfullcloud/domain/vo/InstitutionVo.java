package cn.edu.cqupt.mis.colorfullcloud.domain.vo;

import cn.edu.cqupt.mis.colorfullcloud.domain.entity.CourseEntity;
import cn.edu.cqupt.mis.colorfullcloud.domain.entity.PictureEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author :DengSiYuan
 * @date :2019/8/26 23:00
 * @desc : 机构展示信息实体类
 */
@ApiModel(value = "institutionVo",description = "机构展示信息")
@Data
@ToString
public class InstitutionVo {

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
    @ApiModelProperty(name = "pictures",value = "机构图片组",dataType = "list")
    private List<PictureEntity> pictures;
    @ApiModelProperty(name = "distance",value = "距离信息",dataType = "double")
    private Integer distance;
    @ApiModelProperty(name = "courseVoList",value = "开设课程",dataType = "list")
    private List<CourseVo> courseVoList;

}
