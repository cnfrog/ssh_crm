package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import domain.SaleVisit;
import domain.User;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import service.SaleVisitService;
import utils.PageBean;

import javax.annotation.Resource;

@Controller("saleVisitAction")
@Scope("prototype")
public class SaleVisitAction extends ActionSupport implements ModelDriven<SaleVisit> {
    private SaleVisit saleVisit = new SaleVisit();
    @Resource(name = "saleVisitService")
    private SaleVisitService svs;
    // 当前页数
    private Integer currentPage;
    // 每条显示页数
    private Integer pageSize;
    //修改的salevist的Id
    private String updateVisitId;

    public String list() throws Exception {
        DetachedCriteria dc = DetachedCriteria.forClass(SaleVisit.class);
        if (saleVisit.getCustomer() != null && saleVisit.getCustomer().getCust_id() != null) {
            dc.add(Restrictions.eq("customer.cust_id", saleVisit.getCustomer().getCust_id()));
        }
        PageBean pb = svs.getPageBean(dc, currentPage, pageSize);
        ActionContext.getContext().put("pageBean", pb);
        return "list";
    }

    //添加客户拜访记录
    public String add() throws Exception {
        //取出登陆用户,放入SaleVisit实体,表达关系
        User user = (User) ActionContext.getContext().getSession().get("user");
        //1调用Service保存客户拜访记录
        saleVisit.setUser(user);
        if (StringUtils.isNotBlank(updateVisitId)) {
            saleVisit.setVisit_id(updateVisitId);
        }
        svs.saveOrUpdate(saleVisit);
        //2重定向到拜访记录Action
        return "toList";
    }

    public String toEdit() throws Exception {
        //1调用service,查询linkman
        SaleVisit sv = svs.getById(this.saleVisit.getVisit_id());
        //2放入linkman对象放入request域
        ActionContext.getContext().put("saleVisit", sv);
        //3转发到add页面
        return "add";
    }

    public void setSvs(SaleVisitService svs) {
        this.svs = svs;
    }

    @Override
    public SaleVisit getModel() {
        return saleVisit;
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

    public String getUpdateVisitId() {
        return updateVisitId;
    }

    public void setUpdateVisitId(String updateVisitId) {
        this.updateVisitId = updateVisitId;
    }
}
