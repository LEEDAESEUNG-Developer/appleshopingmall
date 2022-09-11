package com.appleshopingmall.mybatisTest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
@RequiredArgsConstructor
@Component
public class DatasourceConnectTest {

    private final DataSource dataSource;

    /* 접속 테스트 */
    public void connTest() throws SQLException {
        Connection connection = dataSource.getConnection();
        boolean closed = connection.isClosed();
        log.debug("db 접속 여부: " + closed);
    }

    /* 쿼리 select 테스트*/
    public void selectTest() throws SQLException {
        ResultSet resultSet = dataSource.getConnection().prepareStatement("select * from member").executeQuery();

        while (resultSet.next()) {
            log.debug("select 결과: " + resultSet.getString(2));

        }
    }
}
