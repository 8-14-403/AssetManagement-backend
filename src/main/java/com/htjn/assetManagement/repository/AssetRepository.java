package com.htjn.assetManagement.repository;

import com.htjn.assetManagement.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.awt.print.Pageable;
import java.util.List;

public interface AssetRepository extends JpaRepository<Asset, String>, JpaSpecificationExecutor<Asset> {

/*    List<Asset> findAll();

    Asset getOne(String id);*/
    List<Asset> getAssetsByAssetCodeContainingOrAssetTypeContainingOrDeviceNameContaining(String var1, String var2, String var3);

}
