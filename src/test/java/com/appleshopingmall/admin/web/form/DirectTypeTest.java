package com.appleshopingmall.admin.web.form;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

class DirectTypeTest {
    
    @Test
    void 타입(){
        ProductAddForm productAddForm = new ProductAddForm();
        DirectType directType = productAddForm.getDirectType();

        Arrays.stream(DirectType.values()).forEach(str -> System.out.println("str = " + str));
    }

}