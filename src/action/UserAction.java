package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import domain.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import service.UserService;

import javax.annotation.Resource;

@Controller("userAction")
@Scope("prototype")
public class UserAction extends ActionSupport implements ModelDriven<User> {
    private User user = new User();

    @Resource(name = "userService")
    private UserService userService;

    public String login() throws Exception {
        System.out.println(user);
        User u = userService.findUserByUserCode(user);
        ActionContext.getContext().getSession().put("user", u);
        return "toHome";
    }

    public String regist() throws Exception {
        //1调用service保存注册用户
        try {
            userService.saveUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            ActionContext.getContext().put("error", e.getMessage());
            return "regist";
        }
        return "toLogin";
    }

    @Override
    public User getModel() {
        return user;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
