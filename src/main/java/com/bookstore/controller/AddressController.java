package com.bookstore.controller;

import com.bookstore.bean.Address;
import com.bookstore.bean.MyPageInfo;
import com.bookstore.service.AddressService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by heanxing on 2018/9/11.
 */
@RestController
@RequestMapping("/api/address")
public class AddressController extends BaseApiController {
    @Autowired
    private AddressService addressService;

    @GetMapping("list")
    public Map<String,Object> list(@RequestParam(defaultValue = "1") Integer page_num, @RequestParam(defaultValue = "5") Integer page_size){
        PageHelper.startPage(page_num,page_size);
        return onDataResp(new MyPageInfo<Address>(addressService.list()));

    }

    @GetMapping("selectByUserId")
    public Map<String,Object> selectByUserId(@RequestParam Long user_id){
        return onDataResp(addressService.selectByUserId(user_id));

    }

    @PostMapping("/insert")
    public Map<String,Object> insert(@RequestParam String name,@RequestParam String area,@RequestParam String detailed,
                                     @RequestParam String code,@RequestParam String phone,@RequestParam Long user_id){
        if (name !=null && name.trim().length() == 0) return onBadResp("联系人不能为空");
        if (area !=null && area.trim().length() == 0) return onBadResp("地区不能为空");
        if (detailed !=null && detailed.trim().length() == 0) return onBadResp("详细地址不能为空");
        if (code !=null && code.trim().length() == 0) return onBadResp("邮政编码不能为空");
        if (phone !=null && phone.trim().length() == 0) return onBadResp("联系方式不能为空");
        Address address=new Address();
        address.setName(name);
        address.setArea(area);
        address.setDetailed(detailed);
        address.setCode(code);
        address.setPhone(phone);
        address.setUser_id(user_id);
        if (addressService.insert(address)>0) return  onSuccessRep("添加成功");
        return onBadResp("添加失败");
    }
    @PostMapping ("/updateById")
    public Map<String,Object> updateById(@RequestParam Long id,@RequestParam Long user_id,@RequestParam String name,@RequestParam String area,@RequestParam String detailed,
                                         @RequestParam String code,@RequestParam String phone){
        if (name !=null && name.trim().length() == 0) return onBadResp("联系人不能为空");
        if (area !=null && area.trim().length() == 0) return onBadResp("地区不能为空");
        if (detailed !=null && detailed.trim().length() == 0) return onBadResp("详细地址不能为空");
        if (code !=null && code.trim().length() == 0) return onBadResp("邮政编码不能为空");
        if (phone !=null && phone.trim().length() == 0) return onBadResp("联系方式不能为空");
        Address address=new Address();
        address.setId(id);
        address.setUser_id(user_id);
        address.setName(name);
        address.setArea(area);
        address.setDetailed(detailed);
        address.setCode(code);
        address.setPhone(phone);

        if (addressService.updateById(address)>0) return  onSuccessRep("修改成功");
        return onBadResp("修改失败");
    }
    @PostMapping ("/deleteById")
    public Map<String,Object> deleteById(@RequestParam Long id){
        addressService.deleteById(id);
        return onSuccessRep("删除成功");
    }




}
