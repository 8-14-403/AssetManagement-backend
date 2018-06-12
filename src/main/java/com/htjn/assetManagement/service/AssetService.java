package com.htjn.assetManagement.service;

import com.htjn.assetManagement.entity.Asset;
import com.htjn.assetManagement.repository.AssetRepository;
import com.htjn.assetManagement.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class AssetService {

    private final AssetRepository assetRepository;

    @Autowired
    public AssetService(AssetRepository assetRepository) {
        Assert.notNull(assetRepository, "materialDao 不能为空");
        this.assetRepository = assetRepository;
    }

    public List<Asset> getAll(){
        return assetRepository.findAll();
    }

    public Page<Asset> getAll(Pageable pageable){
        return assetRepository.findAll(pageable);
    }

    public Asset save(Asset asset){
        return assetRepository.save(asset);
    }

    @Transactional
    public List<Asset> save(List<Asset> asset){
        return assetRepository.save(asset);
    }

    public Asset update(Asset asset){
        if(!assetRepository.exists(asset.getId())) {
           throw new RuntimeException("目标不存在");
        }
        return assetRepository.save(asset);
    }

    public void deleteById(String id){
        assetRepository.delete(id);
    }

    public Page<Asset> getByCondition(String condition, Pageable pageable) {
        condition = CommonUtils.transference(condition);
        List<Asset> list = assetRepository.getAssetsByAssetCodeContainingOrAssetTypeContainingOrDeviceNameContaining(condition, condition, condition);
        return paging(list, pageable);
    }

    //将查询结果分页
    public Page<Asset> paging(List<Asset> list, Pageable pageable) {
        Collections.sort(list, new Comparator<Asset>() {
            @Override
            //按更新时间进行排序
            public int compare(Asset o1, Asset o2) {
                try {
                    Date dt1 = o1.getUpdateTime();
                    Date dt2 = o2.getUpdateTime();
                    if (dt1.before(dt2)) {
                        return 1;
                    } else if (dt1.after(dt2)) {
                        return -1;
                    } else {
                        return 0;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });
        List<Asset> content = new ArrayList<>();
        long size = list.size();
        if (pageable.getPageNumber() == 0) {
            if (list.size() > pageable.getPageSize()) {
                content = list.subList(0, pageable.getPageSize());
            } else {
                content = list;
            }
        } else if (list.size() > pageable.getOffset() + pageable.getPageSize()) {
            content = list.subList(pageable.getOffset(), pageable.getOffset() + pageable.getPageSize());
        } else if (list.size() > pageable.getOffset() && list.size() <= (pageable.getOffset() + pageable.getPageSize())){
            content = list.subList(pageable.getOffset(), list.size());
        } else {
            content.clear();
        }
        return new PageImpl<>(content, pageable, size);
    }

    @Transactional
    public void deleteInBatch(List<Asset> assets) {
        assetRepository.deleteInBatch(assets);
    }
}
