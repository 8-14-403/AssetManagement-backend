package com.htjn.assetManagement.controller;

import com.htjn.assetManagement.entity.Asset;
import com.htjn.assetManagement.entity.ResultEnum;
import com.htjn.assetManagement.service.AssetService;
import com.htjn.assetManagement.util.Result;
import com.htjn.assetManagement.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "asset")
@Api("物资相关接口")
public class AssetControl {

    private final AssetService assetService;


    @Autowired
    public AssetControl(AssetService assetService) {
        Assert.notNull(assetService, "AssetService 不能为空");
        this.assetService = assetService;
    }

    @GetMapping(value = "/getAll")
    @ApiOperation(value = "查询所有资产信息")
    public @ResponseBody Result getAllMaterial(){
        return ResultUtil.success(ResultEnum.SUCCESS, assetService.getAll());
    }

    @PostMapping(value = "/saveAsset")
    @ApiOperation(value = "添加资产信息")
    public @ResponseBody Result save(@RequestBody Asset asset){
        return ResultUtil.success(ResultEnum.SUCCESS, assetService.save(asset));
    }

    @PutMapping(value = "/updateAsset")
    @ApiOperation(value = "更新资产信息")
    public @ResponseBody Result update(@RequestBody Asset asset){
        return ResultUtil.success(ResultEnum.SUCCESS, assetService.update(asset));
    }

    @DeleteMapping(value = "/deleteAsset")
    @ApiOperation(value = "删除资产信息")
    public @ResponseBody Result delete(@RequestParam("assetId") String id){
        assetService.deleteById(id);
        return ResultUtil.success(ResultEnum.SUCCESS, null);
    }
}
