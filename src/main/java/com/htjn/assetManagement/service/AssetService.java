package com.htjn.assetManagement.service;

import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.htjn.assetManagement.entity.Asset;
import com.htjn.assetManagement.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetService {

    private final AssetRepository assetRepository;

    @Autowired
    public AssetService(AssetRepository assetRepository) {
        org.springframework.util.Assert.notNull(assetRepository, "materialDao 不能为空");
        this.assetRepository = assetRepository;
    }

    public List<Asset> getAll(){
        return assetRepository.findAll();
    }

    public Asset save(Asset asset){
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
}
