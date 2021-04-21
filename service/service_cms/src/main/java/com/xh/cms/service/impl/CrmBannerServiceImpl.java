package com.xh.cms.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xh.cms.entity.CrmBanner;
import com.xh.cms.mapper.CrmBannerMapper;
import com.xh.cms.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author hser
 * @since 2021-03-25
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    @Override
    public void pageBanner(Page<CrmBanner> pageParam, Object o) {
        this.page(pageParam, null);
    }

    @Override
    public CrmBanner getBannerById(String id) {
        return this.getBannerById(id);
    }

    @CacheEvict(value = "banner", allEntries=true)
    @Override
    public void saveBanner(CrmBanner banner) {
        this.save(banner);
    }

    @CacheEvict(value = "banner", allEntries=true)
    @Override
    public void updateBannerById(CrmBanner banner) {
        this.updateById(banner);

    }

    @CacheEvict(value = "banner", allEntries=true)
    @Override
    public void removeBannerById(String id) {
        this.removeById(id);
    }

    @Cacheable(value = "banner", key = "'selectIndexList'")
    @Override
    public List<CrmBanner> selectIndexList() {
        return this.list(null);
    }
}
