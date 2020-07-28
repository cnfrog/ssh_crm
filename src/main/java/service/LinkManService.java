package service;

import domain.LinkMan;
import org.hibernate.criterion.DetachedCriteria;
import sun.awt.image.ImageWatched;
import utils.PageBean;

public interface LinkManService {
    PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize);

    LinkMan getById(long lkm_id);

    void saveOrUpdate(LinkMan linkMan);
}
