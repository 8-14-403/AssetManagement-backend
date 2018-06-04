package com.htjn.assetManagement.controller;

import com.htjn.assetManagement.entity.Asset;
import com.htjn.assetManagement.entity.ResultEnum;
import com.htjn.assetManagement.service.AssetService;
import com.htjn.assetManagement.util.CommonUtils;
import com.htjn.assetManagement.util.Result;
import com.htjn.assetManagement.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    public @ResponseBody Result getAll(@RequestParam(value = "page", required = false) Integer page,
                                       @RequestParam(value = "size", defaultValue = "10") Integer size){
        if (page != null && page >= 0) {
            Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
            Pageable pageable = new PageRequest(page, size, sort);
            return ResultUtil.success(ResultEnum.SUCCESS, assetService.getAll(pageable));
        }
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

    @DeleteMapping(value = "/deleteAsset/{assetId}")
    @ApiOperation(value = "删除资产信息")
    public @ResponseBody Result delete(@PathVariable("assetId") String id){
        assetService.deleteById(id);
        return ResultUtil.success(ResultEnum.SUCCESS, null);
    }

    @PostMapping(value = "/importExcel")
    @ApiOperation(value = "导入excel")
    public @ResponseBody Result importExcel(@RequestParam("file") MultipartFile[] files) throws IOException {
        List<Asset> assets = new ArrayList<>();
        if ( files != null && files.length > 0) {
            for (MultipartFile file : files) {
                assets.addAll(CommonUtils.readXls(file.getInputStream()));
            }
        } else {
            throw new RuntimeException("导入的表格不存在或内容为空");
        }
        return ResultUtil.success(ResultEnum.SUCCESS, assetService.save(assets));
    }
}
