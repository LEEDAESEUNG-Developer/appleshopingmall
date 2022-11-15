package com.appleshopingmall.admin;

import com.appleshopingmall.admin.entity.AdminProductEntity;
import com.appleshopingmall.admin.entity.Order;
import com.appleshopingmall.admin.repository.dto.ProductAddDTO;
import com.appleshopingmall.admin.web.form.ProductAddForm;
import com.appleshopingmall.admin.web.form.UpdateOrderForm;
import com.appleshopingmall.admin.repository.AdminMemberRepository;
import com.appleshopingmall.admin.repository.AdminOrderRepository;
import com.appleshopingmall.admin.repository.AdminProductRepository;
import com.appleshopingmall.member.MemberEntity;
import com.appleshopingmall.shop.product.ProductEntity;
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

        ProductAddDTO productAddDTO = new ProductAddDTO();

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
        return productRepository.deleteProduct(productId);
    }

    @Override
    public List<Order> findOrders() {
        return orderRepository.orders();
    }

    @Override
    public Integer updateOrder(Long orderId, UpdateOrderForm form) {
        Order order = new Order(orderId, form.isCheckStock(), form.isCheckShipment());
        return orderRepository.updateOrder(orderId, order);
    }

    @Override
    public Order findOrder(Long orderId) {
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
