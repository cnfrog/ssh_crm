package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import domain.User;
import service.UserService;

public class UserAction extends ActionSupport implements ModelDriven<User> {
    private User user = new User();

    private UserService userService;

    public String login() throws Exception {
        System.out.println(user);
        User u = userService.findUserByUserCode(user);
        ActionContext.getContext().put("user", u);
        return "toHome";
    }

    @Override
    public User getModel() {
        return user;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
