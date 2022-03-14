package cn.smbms.dao.role;

import cn.smbms.pojo.Role;

import java.sql.Connection;
import java.util.List;

public interface RoleDao {

    public List<Role> getRoleList(Connection connection) throws Exception;

    public List<Role> getRoleList(Connection connection, int currentPageNo, int pageSize) throws Exception;

    public int getRoleCount(Connection connection) throws Exception;

    public int modify(Connection connection, Role role) throws Exception;

}
