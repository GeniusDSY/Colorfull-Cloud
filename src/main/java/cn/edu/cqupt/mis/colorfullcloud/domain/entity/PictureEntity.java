package cn.edu.cqupt.mis.colorfullcloud.domain.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author :DengSiYuan
 * @date :2019/9/23 20:53
 * @desc :
 */
@Data
@ToString
public class PictureEntity {

    @ApiModelProperty(name = "pictureId",value = "图片id")
    private Integer pictureId;
    @ApiModelProperty(name = "institutionId",value = "图片所属机构id")
    private Integer institutionId;
    @ApiModelProperty(name = "pictureName",value = "图片名称")
    private String pictureName;
    @ApiModelProperty(name = "path",value = "图片地址")
    private String path;

}
