package com.maven.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.maven.model.pojo.User;
import com.maven.service.ResourceService;
import com.maven.service.RoleService;
import com.maven.service.UserService;

/**
 * 自定义的指定Shiro验证用户登录的类
 * 
 * @create
 * @author
 */	
public class MyRealm extends AuthorizingRealm {

	@Autowired
	private RoleService roleService;
	@Autowired
	private ResourceService resourceService;
	@Autowired
	private UserService userService;

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		// 获取用户名
		String username = (String) principals.getPrimaryPrincipal();

		// 创建授权对象
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

		// 设置角色
		authorizationInfo.setRoles(roleService.findRoles(username));

		// 设置权限
		authorizationInfo.setStringPermissions(resourceService.findPermissions(username));

		return authorizationInfo;
	}

	/**
	 * 登录验证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		// 从主体传过来的认证信息中，获得用户名
		String username = (String) token.getPrincipal();

		User user = userService.findByUsername(username);

		if (user == null) {
			throw new UnknownAccountException();// 没找到帐号
		}

		if (Boolean.TRUE.equals(user.getLocked())) {
			throw new LockedAccountException(); // 帐号锁定
		}

		// 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUsername(), // 用户名
				user.getPassword(), // 密码
				ByteSource.Util.bytes(user.getSalt()), // salt
				"myRealm" // realm name
		);
		return authenticationInfo;
	}

}
