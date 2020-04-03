package erp.service;

import erp.dao.UserDao;
import erp.domain.User;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Transactional(readOnly = true)
    public User findOneByUser(User form) {
        return userDao.findOneByUser(form);
    }

    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public void updatePasswordByUser(User user) {
        // 密码加密
        SimpleHash result = new SimpleHash("md5", user.getU_password(), user.getU_username(), 7);
        user.setU_password(result.toString());
        userDao.updateByUser(user);
    }

    public List<String> listUsername() {
        return userDao.listUsername();
    }

    public Integer getLevelByUsername(String username) {
        return userDao.getLevelByUsername(username);
    }
}
