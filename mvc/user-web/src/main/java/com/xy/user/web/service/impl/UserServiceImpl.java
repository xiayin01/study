package com.xy.user.web.service.impl;

import com.xy.common.mvc.ResponseResult;
import com.xy.common.mvc.enums.ResponseStatus;
import com.xy.user.web.annotation.LocalTransaction;
import com.xy.user.web.domain.User;
import com.xy.user.web.service.UserService;
import org.apache.commons.collections.CollectionUtils;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

public class UserServiceImpl implements UserService {

    @Resource(name = "bean/EntityManager")
    private EntityManager entityManager;
    @Resource(name = "bean/Validator")
    private Validator validator;

    @Override
    @LocalTransaction
    public ResponseResult register(User user) {
        ResponseResult result = new ResponseResult();
        Set<ConstraintViolation<User>> validators = validator.validate(user);
        if (CollectionUtils.isNotEmpty(validators)) {
            StringBuilder error = new StringBuilder();
            for (ConstraintViolation c : validators) {
                error.append(c.getMessage()).append(";");
                result.setStatus(ResponseStatus.FAIL.getStatus());
                result.setMsg("错误信息：" + error.toString());
            }
        } else {
            entityManager.persist(user);
            result.setStatus(ResponseStatus.SUCCESS.getStatus());
        }
        return result;
    }

    @Override
    public boolean deregister(User user) {
        return false;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public User queryUserById(Long id) {
        return null;
    }

    @Override
    public User queryUserByEmailAndPassword(String email, String password) {
        return null;
    }
}
