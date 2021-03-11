package com.xy.user.web.service.impl;

import com.xy.common.mvc.ResponseResult;
import com.xy.common.mvc.enums.ResponseStatus;
import com.xy.user.web.annotation.LocalTransaction;
import com.xy.user.web.domain.User;
import com.xy.user.web.service.UserService;
import org.apache.commons.collections.CollectionUtils;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(user);
            transaction.commit();
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
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> query = cb.createTupleQuery();
        Root<User> root = query.from(User.class);
        // 查询多个参数
        query.multiselect(root.get("name"), root.get("email"));
        query.where(cb.equal(root.get("email"), email))
                .where(cb.equal(root.get("password"), password))
                .orderBy(cb.asc(root.get("id")));
        // 查询结果为Tuple，按顺序取值
        List<Tuple> tupleList = entityManager.createQuery(query).getResultList();
        List<User> userList = tupleList.stream().map(tuple -> new User(tuple.get(0, String.class), tuple.get(1, String.class)))
                .collect(Collectors.toList());
        return userList.get(0);
    }
}
