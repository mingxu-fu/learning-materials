package com.yingxue.lesson.service.impl;

import com.yingxue.lesson.contants.Contant;
import com.yingxue.lesson.service.CartService;
import com.yingxue.lesson.service.RedisService;
import com.yingxue.lesson.vo.req.AddCartReqVO;
import com.yingxue.lesson.vo.resp.CartRespVO;
import com.yingxue.lesson.vo.resp.GoodsItemRespVO;
import com.yingxue.lesson.vo.resp.ValueItemRespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: CartServiceImpl
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private RedisService redisService;




    @Override
    public String addCart(AddCartReqVO vo,String userId) {
        //filed = "商品skuid，商品规格ID"
        String filed=vo.getSkuId()+","+vo.getSpecificationIds();
        //每点一下，就触发一次该方法，redis中key为cart_userid的属性field的值就递增 1
        redisService.hIncrBy(Contant.CART_KEY+userId,filed,vo.getNum());
        return "操作成功";
    }

    @Override
    public List<CartRespVO> showCart(String userId) {
        //获取用户购物车所有数据
        Map<Object, Object> maps = redisService.hgetall(Contant.CART_KEY+userId);
        //解析数据
        List<CartRespVO> result=new ArrayList<>();
        if(null==maps){
            return result;
        }
        // 1. entrySet遍历，在键和值都需要时使用（最常用）
        for (Map.Entry<Object, Object> entry : maps.entrySet()) {
            CartRespVO respVO=new CartRespVO();
            //商品数量
            Integer num=Integer.valueOf(entry.getValue().toString());
            String file= (String) entry.getKey();
            //获取商品skuId和商品熟悉id(数组第一个为skuId)
            String ids[]=file.split(",");
            //查询商品相信信息
            String skuId=ids[0];

            //拿到商品详细
            GoodsItemRespVO itemVO=getItem(skuId);
            respVO.setIcon(itemVO.getIcon());
            respVO.setPrice(itemVO.getPrice());
            respVO.setSkuId(itemVO.getSkuId());
            respVO.setProductName(itemVO.getProductName());
            respVO.setNum(num);
            List<ValueItemRespVO> list=new ArrayList<>();
            for (int i=1;i<ids.length;i++){
                //拿属性值
                ValueItemRespVO item=getValueItem(ids[i]);
                list.add(item);
            }
            respVO.setItemRespVOList(list);
            result.add(respVO);
        }
        return result;
    }
    private GoodsItemRespVO getItem(String skuId) {
        //这里是 mock 数据
        GoodsItemRespVO respVO=new GoodsItemRespVO();
        respVO.setIcon("http://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%9B%BE%E7%89%87&hs=0&pn=0&spn=0&di=7590&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&ie=utf-8&oe=utf-8&cl=2&lm=-1&cs=1411728850%2C1869975885&os=69787666%2C250391253&simid=3412044283%2C207046138&adpicid=0&lpn=0&ln=30&fr=ala&fm=&sme=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fpic18.nipic.com%2F20120103%2F8993051_170340691334_2.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bgtrtv_z%26e3Bv54AzdH3Ffi5oAzdH3F9AzdH3F8a0AzdH3Fccb9nlmhbbcud8kj_z%26e3Bip4s&gsm=&islist=&querylist=");
        respVO.setPrice(new BigDecimal(69.99));
        respVO.setSkuId(skuId);
        respVO.setProductName("迎学教育-spring boot 实战系列");
        return respVO;
    }

    private ValueItemRespVO getValueItem(String id){
        //这里是 mock 数据
        ValueItemRespVO respVO=new ValueItemRespVO();
        respVO.setType("1");
        respVO.setTypeName("普通属性");
        respVO.setValueName("VIP 教学");
        respVO.setValueId(id);
        return respVO;
    }
}
