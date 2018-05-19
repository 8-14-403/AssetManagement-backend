package com.htjn.assetManagement.controller;

import com.htjn.assetManagement.entity.ResultEnum;
import com.htjn.assetManagement.service.AssetService;
import com.htjn.assetManagement.util.Result;
import com.htjn.assetManagement.util.ResultUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "material")
@Api("物资相关接口")
public class AssetControl {

    private final AssetService assetService;


    @Autowired
    public AssetControl(AssetService assetService) {
        Assert.notNull(assetService, "MaterialService 不能为空");
        this.assetService = assetService;
    }

    @GetMapping(value = "/getAll")
    public @ResponseBody Result getAllMaterial(){
        return ResultUtil.success(ResultEnum.SUCCESS, assetService.getAll());
    }
}
