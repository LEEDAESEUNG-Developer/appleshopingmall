package com.appleshopingmall.admin.web.form;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

@Data
public class ProductAddForm {

    private DirectType directType;

    @NotBlank
    private String productName;

    @Min(value = 0, message = "0 보다 작을 수 없습니다.")
    private long productPrice;

    @NotNull
    @Past(message = "생산년도 오류")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date productData;

    @NotBlank
    private String series;


    @Min(value = 0, message = "0 보다 작을 수 없습니다.")
    private int productCategory;

    @NotBlank
    private String productColor;

    @NotBlank
    private String productAP;

    @NotBlank
    private String productRam;

    @NotBlank
    private String productOperatingSystem;

    @NotBlank
    private String productStorage;

    @Min(value = 0, message = "0 보다 작을 수 없습니다.")
    private int productStock;

    private MultipartFile productMainImg;
    private MultipartFile productTopImg;
    private MultipartFile productLeftImg;
    private MultipartFile productRightImg;
    private MultipartFile productContentImg01;
}
