package com.bookstore.handler;

import com.bookstore.enums.UserSex;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserSexHandler implements TypeHandler<UserSex> {

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, UserSex userSex, JdbcType jdbcType) throws SQLException {
        if (userSex != null) preparedStatement.setInt(i, userSex.getCode());
    }

    @Override
    public UserSex getResult(ResultSet resultSet, String columnName) throws SQLException {
        return UserSex.valueOf(resultSet.getInt(columnName));
    }

    @Override
    public UserSex getResult(ResultSet resultSet, int columnName) throws SQLException {
        return UserSex.valueOf(resultSet.getInt(columnName));
    }

    @Override
    public UserSex getResult(CallableStatement callableStatement, int columnName) throws SQLException {
        return UserSex.valueOf(callableStatement.getInt(columnName));
    }
}
