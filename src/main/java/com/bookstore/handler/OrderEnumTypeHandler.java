package com.bookstore.handler;

import com.bookstore.enums.FreezeEnum;
import com.bookstore.enums.OrderEnum;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by heanxing on 2018/10/23.
 */
public class OrderEnumTypeHandler implements TypeHandler<OrderEnum>{


    @Override
    public void setParameter(PreparedStatement ps, int i, OrderEnum parameter, JdbcType jdbcType) throws SQLException {
        if (parameter != null) ps.setInt(i, parameter.getValue());
    }

    @Override
    public OrderEnum getResult(ResultSet rs, String columnName) throws SQLException {
        return OrderEnum.valueOf(rs.getInt(columnName));
    }

    @Override
    public OrderEnum getResult(ResultSet rs, int columnIndex) throws SQLException {
        return  OrderEnum.valueOf(rs.getInt(columnIndex));
    }

    @Override
    public OrderEnum getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return  OrderEnum.valueOf(cs.getInt(columnIndex));
    }
}
