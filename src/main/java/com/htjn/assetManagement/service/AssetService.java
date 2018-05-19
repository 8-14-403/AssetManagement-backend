package com.htjn.assetManagement.service;

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
}
