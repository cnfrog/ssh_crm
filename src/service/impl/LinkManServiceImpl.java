package service.impl;

import dao.LinkManDao;
import domain.LinkMan;
import org.hibernate.criterion.DetachedCriteria;
import service.LinkManService;
import utils.PageBean;

import java.util.List;

public class LinkManServiceImpl implements LinkManService {
    private LinkManDao lmd;

    @Override
    public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize) {
        // 调用dao查询总记录数
        Integer totalCount = lmd.getTotalCount(dc);

        // 创建PageBean对象
        PageBean pb = new PageBean(currentPage, totalCount, pageSize);

        // 调用dao查询分页列表数据
        List<LinkMan> list = lmd.getPageList(dc, pb.getStart(), pb.getPageSize());
        // 列表数据放入pageBean中,并返回
        pb.setList(list);
        return pb;
    }

    @Override
    public LinkMan getById(long lkm_id) {

        return lmd.getById(lkm_id);
    }

    @Override
    public void saveOrUpdate(LinkMan linkMan) {
        lmd.savetOrUpdate(linkMan);

    }


    public void setLmd(LinkManDao lmd) {
        this.lmd = lmd;
    }
}
