package service.impl;

import dao.SaleVisitDao;
import domain.SaleVisit;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service.SaleVisitService;
import utils.PageBean;

import javax.annotation.Resource;
import java.util.List;
@Service("saleVisitService")
@Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.NEVER,readOnly = false)
public class SaleVisitServiceImpl implements SaleVisitService {
    @Resource(name = "saleVisitDao")
    private SaleVisitDao svd;

    @Override
    public void saveOrUpdate(SaleVisit saleVisit) {
        svd.savetOrUpdate(saleVisit);
    }

    @Override
    public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize) {
        // 调用dao查询总记录数
        Integer totalCount = svd.getTotalCount(dc);

        // 创建PageBean对象
        PageBean pb = new PageBean(currentPage, totalCount, pageSize);

        // 调用dao查询分页列表数据
        List<SaleVisit> list = svd.getPageList(dc, pb.getStart(), pb.getPageSize());
        // 列表数据放入pageBean中,并返回
        pb.setList(list);
        return pb;
    }

    @Override
    public SaleVisit getById(String id) {
        return svd.getById(id);
    }


    public void setSvd(SaleVisitDao svd) {
        this.svd = svd;
    }
}
