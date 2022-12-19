package com.appleshopingmall;

import com.appleshopingmall.util.SessionUtil;
import com.appleshopingmall.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

@Slf4j
public class SideBar {

    private static final SideBar instance = new SideBar();

    private SideBar(){}

    public static SideBar getInstance() {
        return instance;
    }

    /**
     * 카트에 장바구니 갯수
     * 파라미터에 cartRepositoy를 객체를 안받고 다른 방법을 사용해볼려고 했지만 실패(컨테이너 주입)
     */
    public void modelAddCartCount(Model model, HttpSession httpSession, CartService cartService){
        try {
            Long memberId = SessionUtil.getSessionUtil().getMemberId(httpSession);
            model.addAttribute("cartCount", cartService.getMemberCartCount(memberId));
        } catch (Exception e) {
            log.debug("비회원 접속자");
        }
    }

}
