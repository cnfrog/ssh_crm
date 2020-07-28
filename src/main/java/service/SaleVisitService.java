package service;

import domain.SaleVisit;
import org.hibernate.criterion.DetachedCriteria;
import utils.PageBean;

public interface SaleVisitService {
    //保存客户拜访记录
    void saveOrUpdate(SaleVisit saleVisit);

    PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize);

    SaleVisit getById(String id);
}
