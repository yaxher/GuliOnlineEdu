package com.xh.ucenter.service;

import com.xh.ucenter.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xh.ucenter.entity.vo.LoginInfoVo;
import com.xh.ucenter.entity.vo.LoginVo;
import com.xh.ucenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author Yaher
 * @since 2021-04-15
 */
public interface MemberService extends IService<Member> {

    String login(LoginVo loginVo);

    void register(RegisterVo registerVo);

    LoginInfoVo getLoginInfo(String memberId);

    Member getByOpenid(String openid);
}
