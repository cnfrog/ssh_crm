package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import domain.LinkMan;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import service.LinkManService;
import utils.PageBean;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {
    private LinkMan linkMan = new LinkMan();
    private LinkManService lms;

    // 当前页数
    private Integer currentPage;
    // 每条显示页数
    private Integer pageSize;

    public String list() throws Exception {
        DetachedCriteria dc = DetachedCriteria.forClass(LinkMan.class);
        if (StringUtils.isNotBlank(linkMan.getLkm_name())) {
            dc.add(Restrictions.like("lkm_name", "%" + linkMan.getLkm_name() + "%"));
        }
        if (linkMan.getCustomer() != null && linkMan.getCustomer().getCust_id() != null) {
            dc.add(Restrictions.eq("customer.cust_id", linkMan.getCustomer().getCust_id()));
        }
        PageBean pb = lms.getPageBean(dc, currentPage, pageSize);
        ActionContext.getContext().put("pageBean", pb);
        return "list";
    }

    public String toEdit() throws Exception {
        //1调用service,查询linkman
        LinkMan lm = lms.getById(linkMan.getLkm_id());
        //2放入linkman对象放入request域
        ActionContext.getContext().put("linkman", lm);
        //3转发到add页面
        return "add";
    }

    public String add() throws Exception {
        //1调用Service
        lms.saveOrUpdate(linkMan);
        return "toList";
    }

    @Override
    public LinkMan getModel() {
        return linkMan;
    }

    public void setLms(LinkManService lms) {
        this.lms = lms;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
