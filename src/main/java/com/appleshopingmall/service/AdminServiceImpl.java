package com.appleshopingmall.service;

import com.appleshopingmall.entity.AdminProductEntity;
import com.appleshopingmall.entity.AdminOrder;
import com.appleshopingmall.dto.AdminProductAddDTO;
import com.appleshopingmall.controller.web.adminForm.ProductAddForm;
import com.appleshopingmall.controller.web.adminForm.UpdateOrderForm;
import com.appleshopingmall.repository.AdminMemberRepository;
import com.appleshopingmall.repository.AdminOrderRepository;
import com.appleshopingmall.repository.AdminProductRepository;
import com.appleshopingmall.entity.MemberEntity;
import com.appleshopingmall.util.FileStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import static com.appleshopingmall.util.FileCharacter.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final AdminMemberRepository memberRepository;
    private final AdminProductRepository productRepository;
    private final AdminOrderRepository orderRepository;
    private final FileStore fileStore;

    @Override
    public List<MemberEntity> findMembers() {
        return memberRepository.findMembers();
    }

    @Override
    public MemberEntity findMember(Long memberId) {
        return memberRepository.findMember(memberId);
    }

    @Override
    public int deleteMember(Long memberId) {
        return memberRepository.deleteMember(memberId);
    }

    @Override
    public List<AdminProductEntity> findProducts() {
        return productRepository.findProducts();
    }

    @Override
    public AdminProductEntity findProduct(Long productId) {
        return productRepository.findProduct(productId);
    }


    @Override
    public int addProduct(ProductAddForm productAddForm) throws IOException {
        log.info("form => {}", productAddForm);

        final String productLocation = productMainName(productAddForm); //절대 바뀌면 안되는 경로

        log.info("directoryLocation => {}", productLocation);

        AdminProductAddDTO productAddDTO = new AdminProductAddDTO();

        productAddDTO.setProductName(productAddForm.getProductName());
        productAddDTO.setProductPrice(productAddForm.getProductPrice());
        productAddDTO.setProductData(new Date(productAddForm.getProductData().getTime()));
        productAddDTO.setProductCategory(productAddForm.getProductCategory());
        productAddDTO.setProductColor(productAddForm.getProductColor());
        productAddDTO.setProductAP(productAddForm.getProductAP());
        productAddDTO.setProductRam(productAddForm.getProductRam());
        productAddDTO.setProductOperatingSystem(productAddForm.getProductOperatingSystem());
        productAddDTO.setProductStorage(productAddForm.getProductStorage());
        productAddDTO.setProductStock(productAddForm.getProductStock());


        if(!productAddForm.getProductMainImg().isEmpty()) {
            String filename = productLocation + "-main" + extractExt(productAddForm.getProductMainImg().getOriginalFilename());
            log.info("getProductMainImg => {}", filename);
            productAddDTO.setProductMainImg("/" + filename);
            fileStore.storeFile(filename, productAddForm.getProductMainImg());
        } if (!productAddForm.getProductLeftImg().isEmpty()) {
            String filename =  productLocation + "-left" + extractExt(productAddForm.getProductLeftImg().getOriginalFilename());
            log.info("getProductLeftImg => {}", filename);
            productAddDTO.setProductLeftImg("/" + filename);
            fileStore.storeFile(filename, productAddForm.getProductLeftImg());
        } if(!productAddForm.getProductTopImg().isEmpty()){
            String filename =  productLocation + "-top" + extractExt(productAddForm.getProductTopImg().getOriginalFilename());
            log.info("getProductTopImg => {}", filename);
            productAddDTO.setProductTopImg("/" + filename);
            fileStore.storeFile(filename, productAddForm.getProductTopImg());
        } if (!productAddForm.getProductRightImg().isEmpty()) {
            String filename =  productLocation + "-right" + extractExt(productAddForm.getProductRightImg().getOriginalFilename());
            log.info("getProductRightImg => {}", filename);
            productAddDTO.setProductRightImg("/" + filename);
            fileStore.storeFile(filename, productAddForm.getProductRightImg());
        } if (!productAddForm.getProductContentImg01().isEmpty()) {
            String filename =  productLocation + "-content" + extractExt(productAddForm.getProductContentImg01().getOriginalFilename());
            log.info("getProductContentImg01 => {}", filename);
            productAddDTO.setProductContentImg01("/" + filename);
            fileStore.storeFile(filename, productAddForm.getProductContentImg01());
        }

        return productRepository.addProduct(productAddDTO);
    }

    @Override
    public Integer updateProduct(AdminProductEntity product) {
        return productRepository.updateProduct(product);
    }

    @Override
    public Integer deleteProduct(Long productId) {

        Integer result = 0;

        try{
             result = productRepository.deleteProduct(productId);
        } catch (Exception e){
            log.info("어떤 회원이 주문한 상품이 있어 상품을 삭제 할 수 없음.");
        }
        return result;
    }

    @Override
    public List<AdminOrder> findOrders() {
        return orderRepository.orders();
    }

    @Override
    public Integer updateOrder(Long orderId, UpdateOrderForm form) {
        AdminOrder order = new AdminOrder(orderId, form.isCheckStock(), form.isCheckShipment());
        return orderRepository.updateOrder(orderId, order);
    }

    @Override
    public AdminOrder findOrder(Long orderId) {
        return orderRepository.findOrder(orderId);
    }

    // ProductAddForm 파일명을 가져오기 (메인)
    public String productMainName(ProductAddForm productAddForm){
        String fileName = "";

        fileName += stringAdditionSlash(productAddForm.getDirectType().getName()) +
                stringAdditionSlash(productAddForm.getSeries()) +
                stringAdditionMinus(productAddForm.getProductName()) +
                stringAdditionMinus(productAddForm.getProductAP()) +
                productAddForm.getProductColor();

        log.info("productAddFormFilename().fileName => {}", fileName);

        return fileName;
    }

    // ProductAddForm 파일명을 가져오기 (왼쪽, 탑, 오른쪽 등)
    public String productName(ProductAddForm productAddForm){
        String fileName = "";

        fileName += stringAdditionSlash(productAddForm.getDirectType().getName()) +
                stringAdditionSlash(productAddForm.getSeries()) +
                stringAdditionMinus(productAddForm.getProductName()) +
                stringAdditionMinus(productAddForm.getProductAP()) +
                productAddForm.getProductColor();

        log.info("productAddFormFilename().fileName => {}", fileName);

        return fileName;
    }
}
