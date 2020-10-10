package com.yingxue.lesson.service;

import com.yingxue.lesson.vo.req.AddCartReqVO;
import com.yingxue.lesson.vo.resp.CartRespVO;

import java.util.List;

/**
 * @ClassName: CartService
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
public interface CartService {
    String addCart(AddCartReqVO vo,String userId);
    List<CartRespVO> showCart(String userId);
}
