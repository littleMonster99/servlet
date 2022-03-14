package cn.smbms.service.role;

import cn.smbms.pojo.Role;
import cn.smbms.pojo.User;

import java.util.List;

public interface RoleService {

    public List<Role> getRoleList();

    public List<Role> getRoleList(String queryRoleName, int currentPageNo, int pageSize);

    public int getRoleCount(String queryUserName);

	public boolean modify(Role role);

}
