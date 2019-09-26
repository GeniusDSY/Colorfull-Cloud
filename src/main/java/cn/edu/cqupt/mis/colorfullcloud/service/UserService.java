package cn.edu.cqupt.mis.colorfullcloud.service;

import cn.edu.cqupt.mis.colorfullcloud.domain.dto.SuggestionDto;
import cn.edu.cqupt.mis.colorfullcloud.domain.dto.UserDto;
import cn.edu.cqupt.mis.colorfullcloud.domain.entity.ChildrenEntity;
import cn.edu.cqupt.mis.colorfullcloud.domain.vo.UserVo;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author :DengSiYuan
 * @date :2019/5/1 17:46
 * @desc : 用户Service
 */
@Service
public interface UserService {

    /**
     * 用户登录
     * @param code
     * @param userDto
     * @return userVo
     * */
    UserVo userLogin(String code, UserDto userDto);

    /**
     * 修改用户信息
     * @param userDto
     * @return userVo
     * */
    UserVo modifyUser(UserDto userDto);

    /**
     * 用户注销
     * @param request
     * @return true/false
     * */
    boolean userLogOut(HttpServletRequest request);

    /**
     * 创建孩子信息
     * @param userId 用户id
     * @param childrenEntityList 孩子信息列表
     * @return 创建后的孩子信息列表
     */
    List<ChildrenEntity> createChildren(Integer userId,List<ChildrenEntity> childrenEntityList);

    /**
     * 查询孩子是否可用(已登记的，不可用返回false)
     * @param idCard
     * @return true / false
     */
    Boolean judgeChildren(String idCard);

    /**
     * 删除孩子信息
     * @param userId
     * @param childrenIdList 孩子id列表
     * @return 孩子信息
     */
    List<ChildrenEntity> deleteChildren(Integer userId,List<Integer> childrenIdList);

    /**
     * 创建意见反馈
     * @param suggestionDto 意见反馈
     * @return
     */
    Boolean createSuggestion(SuggestionDto suggestionDto);

}
