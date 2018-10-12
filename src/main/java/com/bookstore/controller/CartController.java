package com.bookstore.controller;

import com.bookstore.bean.Cart;
import com.bookstore.bean.Product;
import com.bookstore.service.CartService;
import com.bookstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by heanxing on 2018/10/10.
 */
@RestController
@RequestMapping("/api/cart")
public class CartController extends BaseApiController {
    @Autowired
    CartService cartService;

    @Autowired
    ProductService productService;


    @GetMapping("/selectByUser")
    public Map<String,Object>selectByUser(@RequestParam Long user_id){
        return onDataResp(cartService.seleByUser(user_id));
    }


    @PostMapping("/insert")
    public Map<String,Object> insert(@RequestParam Long user_id,@RequestParam Long itemId,@RequestParam int num){
        List<Cart> cartList=cartService.seleByUser(user_id);
        Cart cart=null;
        for (Cart c: cartList){
            if (c.getProduct_id().longValue()==itemId.longValue()){
                cart=c;
                break;
            }
        }
        if (cart !=null){
            cart.setNumber(cart.getNumber()+num);
            cart.setUpdated(new Date());
            if (cartService.update(cart)>0) return onSuccessRep("修改成功");
            return onBadResp("修改失败");
        }else {
            Product product=productService.selectById(itemId);
            cart = new Cart();
            cart.setUser_id(user_id);
            cart.setProduct_id(itemId);
            cart.setItemTitle(product.getName());
            cart.setItemPrice(product.getPromotePrice());
            cart.setCreatedate(new Date());
            cart.setUpdated(cart.getUpdated());
            cartList.add(cart);
            if (cartService.insert(cart)>0)  return onSuccessRep("添加购物车成功");
            return onBadResp("添加购物车失败");
        }

    }
    @PostMapping("/update")
    public Map<String,Object> update(@RequestParam Long id,@RequestParam int num){
        Cart cart=new Cart();
        cart.setId(id);cart.setNumber(num);
        cart.setUpdated(new Date());
        if (cartService.update(cart)>0) return onSuccessRep("修改购物车成功");
        return onBadResp("修改购物车失败");

    }
    @PostMapping("/delete")
    public Map<String, Object> delete(@RequestParam Long[] id ){
        productService.deleteBatch(id);
        return onSuccessRep("删除成功");
    }

}
