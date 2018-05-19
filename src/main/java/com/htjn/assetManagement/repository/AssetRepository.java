package com.htjn.assetManagement.repository;

import com.htjn.assetManagement.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AssetRepository extends JpaRepository<Asset, String>, JpaSpecificationExecutor<Asset> {

/*    List<Asset> findAll();

    Asset getOne(String id);*/


}
