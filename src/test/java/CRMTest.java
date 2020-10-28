import com.yiyi.crm.exception.LoginException;
import com.yiyi.crm.settings.dao.UserDao;
import com.yiyi.crm.settings.domain.User;
import com.yiyi.crm.settings.service.UserService;
import com.yiyi.crm.settings.service.impl.UserServiceImpl;
import com.yiyi.crm.utils.ServiceFactory;
import com.yiyi.crm.utils.SqlSessionUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


public class CRMTest {
    @Test
    public void getUserMapper() {
      UserDao userDao= SqlSessionUtil.getSqlSession().getMapper(UserDao.class);
        System.out.println ("++"+userDao );
    }
    @Test
    public void getUserServiceImpl() throws LoginException {


    }
}
