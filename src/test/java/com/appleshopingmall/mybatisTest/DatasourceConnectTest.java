package com.appleshopingmall.mybatisTest;

import com.appleshopingmall.entity.MemberEntity;
import com.appleshopingmall.service.MemberService;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

@SpringBootTest
@Transactional
public class DatasourceConnectTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private MemberService memberService;

    /* 접속 테스트 */
    @Test
    @DisplayName("DB연결테스트")
    void connTest(){
        try {
            try{
                Connection connection = dataSource.getConnection();
                boolean closed = connection.isClosed();
                Assertions.assertThat(closed).isFalse();
            } catch (CommunicationsException e) {
                throw new RuntimeException("연결오류");
            } catch (SQLException e) {
                throw new SQLException("SQL오류");
            }
        } catch (RuntimeException e){
            org.junit.jupiter.api.Assertions.assertEquals("연결오류", e.getMessage());
        } catch (SQLException e) {
            org.junit.jupiter.api.Assertions.assertEquals("SQL오류", e.getMessage());
        }
    }

    /* 쿼리 insert 테스트*/
    @Test
    void insertTest(){
        java.util.Date date = new java.util.Date();
        MemberEntity member1 = MemberEntity.builder().
                memberFirstname("홍길동").memberAddress("경기도").memberBirthday(new Date(date.getTime())).memberEmail("홍길동")
                .memberPwd("1234").memberPhoneNumber("000-0000-0000").build();
        int memberDbCount = memberService.addMember(member1);

        Assertions.assertThat(memberDbCount).isEqualTo(1);

    }

}
