package domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SaleVisit {
   /* CREATE TABLE sale_visit
            (
    visit_id          VARCHAR(32) DEFAULT '' NOT NULL
    PRIMARY KEY,
    visit_cust_id     BIGINT(19)             NULL
    COMMENT '客户id',
    visit_user_id     BIGINT(19)             NULL
    COMMENT '负责人id',
    visit_time        DATE (10) NULL COMMENT '拜访时间',
    visit_interviewee VARCHAR(32)            NULL
    COMMENT '被拜访人',
    visit_addr        VARCHAR(128)           NULL
    COMMENT '拜访地点',
    visit_detail      VARCHAR(256)           NULL
    COMMENT '拜访详情',
    visit_nexttime    DATE (10) NULL COMMENT '下次拜访时间',
    CONSTRAINT FK_sale_visit_cust_id
    FOREIGN KEY (visit_cust_id) REFERENCES cst_customer (cust_id),
    CONSTRAINT FK_sale_visit_user_id
    FOREIGN KEY (visit_user_id) REFERENCES sys_user (user_id)
            );
*/
    private String visit_id;
    private String visit_interviewee;
    private String visit_addr;
    private String visit_detail;
    private Date visit_time;
    private Date visit_nexttime;

    //关联属性,多对一
    private Customer customer;
    private User user;


    public String getVisit_id() {
        return visit_id;
    }

    public void setVisit_id(String visit_id) {
        this.visit_id = visit_id;
    }

    public String getVisit_interviewee() {
        return visit_interviewee;
    }

    public void setVisit_interviewee(String visit_interviewee) {
        this.visit_interviewee = visit_interviewee;
    }

    public String getVisit_addr() {
        return visit_addr;
    }

    public void setVisit_addr(String visit_addr) {
        this.visit_addr = visit_addr;
    }

    public String getVisit_detail() {
        return visit_detail;
    }

    public void setVisit_detail(String visit_detail) {
        this.visit_detail = visit_detail;
    }

    public Date getVisit_time() {
        return visit_time;
    }

    public void setVisit_time(Date visit_time) {
        this.visit_time = visit_time;
    }

    public Date getVisit_nexttime() {
        return visit_nexttime;
    }

    public void setVisit_nexttime(Date visit_nexttime) {
        this.visit_nexttime = visit_nexttime;
    }
    //-----------------------------------------------
    public String getVisit_time_s() {
        return transferDate(visit_time,"yyyy-MM-dd");
    }
    public String getVisit_nexttime_s() {
        return transferDate(visit_nexttime,"yyyy-MM-dd");
    }
    public static String  transferDate(Date date,String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }
    //-----------------------------------------------
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
