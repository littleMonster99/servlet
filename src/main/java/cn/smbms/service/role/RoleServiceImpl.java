package cn.smbms.service.role;

import cn.smbms.dao.BaseDao;
import cn.smbms.dao.role.RoleDao;
import cn.smbms.dao.role.RoleDaoImpl;
import cn.smbms.pojo.Role;
import cn.smbms.pojo.User;

import java.sql.Connection;
import java.util.List;

public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    public RoleServiceImpl() {
        roleDao = new RoleDaoImpl();
    }

    @Override
    public List<Role> getRoleList() {
        Connection connection = null;
        List<Role> roleList = null;
        try {
            connection = BaseDao.getConnection();
            roleList = roleDao.getRoleList(connection);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return roleList;
    }

    @Override
    public List<Role> getRoleList(String queryRoleName, int currentPageNo, int pageSize) {
        Connection connection = null;
        List<Role> roles = null;
        System.out.println("currentPageNo ---- > " + currentPageNo);
        System.out.println("pageSize ---- > " + pageSize);

        try {
            connection = BaseDao.getConnection();
            roles = roleDao.getRoleList(connection, currentPageNo, pageSize);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return roles;
    }

    @Override
    public int getRoleCount(String queryUserName) {
        // TODO Auto-generated method stub
        Connection connection = null;
        int count = 0;
        System.out.println("queryUserName ---- > " + queryUserName);
        try {
            connection = BaseDao.getConnection();
            count = roleDao.getRoleCount(connection);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return count;
    }

    @Override
    public boolean modify(Role role) {
        // TODO Auto-generated method stub
        Connection connection = null;
        boolean flag = false;
        try {
            connection = BaseDao.getConnection();
            if (roleDao.modify(connection, role) > 0)
                flag = true;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return flag;
    }

}
