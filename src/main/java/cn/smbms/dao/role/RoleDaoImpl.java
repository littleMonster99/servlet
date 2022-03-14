package cn.smbms.dao.role;

import cn.smbms.dao.BaseDao;
import cn.smbms.pojo.Role;
import cn.smbms.pojo.User;
import com.mysql.jdbc.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl implements RoleDao{

	@Override
	public List<Role> getRoleList(Connection connection) throws Exception {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Role> roleList = new ArrayList<Role>();
		if(connection != null){
			String sql = "select * from smbms_role";
			Object[] params = {};
			rs = BaseDao.execute(connection, pstm, rs, sql, params);
			while(rs.next()){
				Role _role = new Role();
				_role.setId(rs.getInt("id"));
				_role.setRoleCode(rs.getString("roleCode"));
				_role.setRoleName(rs.getString("roleName"));
				roleList.add(_role);
			}
			BaseDao.closeResource(null, pstm, rs);
		}
		
		return roleList;
	}

	@Override
	public List<Role> getRoleList(Connection connection, int currentPageNo, int pageSize) throws Exception {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Role> roles = new ArrayList<Role>();
		if(connection != null){
			StringBuffer sql = new StringBuffer();
			sql.append("select * from smbms_role");
			List<Object> list = new ArrayList<Object>();

			sql.append(" order by creationDate DESC limit ?,?");
			currentPageNo = (currentPageNo-1)*pageSize;
			list.add(currentPageNo);
			list.add(pageSize);

			Object[] params = list.toArray();
			System.out.println("sql ----> " + sql.toString());
			rs = BaseDao.execute(connection, pstm, rs, sql.toString(), params);
			while(rs.next()){
				Role role = new Role();
				role.setId(rs.getInt("id"));
				role.setRoleCode(rs.getString("roleCode"));
				role.setRoleName(rs.getString("roleName"));
				roles.add(role);
			}
			BaseDao.closeResource(null, pstm, rs);
		}
		return roles;
	}

	@Override
	public int getRoleCount(Connection connection) throws Exception {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int count = 0;
		if (connection != null){
			StringBuffer sql = new StringBuffer();
			sql.append("select count(1) from smbms_role");
			List<Object> list = new ArrayList<>();
			count = BaseDao.execute(connection, pstm, sql.toString(), null);
		}
		return count;
	}

	@Override
	public int modify(Connection connection, Role role) throws Exception {
		PreparedStatement pstm = null;
		int result = 0;
		if(null != connection){
			String sql = "update smbms_role set roleCode = ?,roleName = ?,modifyBy = ?,modifyDate = ? where id = ?";
			List<Object> list = new ArrayList<>();
			list.add(role.getRoleCode());
			list.add(role.getRoleName());
			list.add(role.getModifyBy());
			list.add(role.getModifyDate());
			result = BaseDao.execute(connection,pstm,sql,list.toArray());
		}
		return result;
	}

}
