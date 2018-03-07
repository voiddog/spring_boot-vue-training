package org.voiddog.spring.test.user;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.voiddog.spring.test.TokenUtils;
import org.voiddog.spring.test.model.Constants;
import org.voiddog.spring.test.model.MessageException;
import org.voiddog.spring.test.model.Response;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository repository;

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public Response<UserDAO> register(@RequestBody RegisterUserRequest request) {
        UserDAO userDAO = new UserDAO();
        BeanUtils.copyProperties(request, userDAO);
        userDAO = repository.save(userDAO);
        return Response.success(userDAO);
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public Response<UserDAO> login(@RequestBody LoginRequest request) {
        Specifications<UserDAO> specifications = Specifications
                .where(UserFilterHelper.filterByAccount(request.getAccount()));

        List<UserDAO> userDAOList = repository.findAll(specifications);
        if (userDAOList == null || userDAOList.isEmpty()) {
            throw new MessageException("未找到用户");
        }
        for (UserDAO dao : userDAOList) {
            if (Objects.equals(request.getPassword(), dao.getPassword())) {
                dao.setToken(TokenUtils.createToken());
                repository.save(dao);
                return Response.success(dao);
            }
        }
        throw new MessageException("密码不对");
    }

    @RequestMapping(path = "/refresh_token", method = RequestMethod.POST)
    public Response<UserDAO> refreshToken(@RequestBody RefreshTokenRequest request) {
        UserDAO dao = repository.findOne(request.getId());
        if (dao == null) {
            throw new MessageException("用户不存在");
        }
        if (!Objects.equals(dao.getToken(), request.getToken())) {
            throw new MessageException("token 已过期");
        }
        dao.setToken(TokenUtils.createToken());
        repository.save(dao);
        return Response.success(dao);
    }
}
