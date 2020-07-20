package com.mysoft.alpha.service;

import lombok.extern.log4j.Log4j2;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.mysoft.alpha.dao.CompanyDAO;
import com.mysoft.alpha.dao.UserDAO;
import com.mysoft.alpha.dto.UserDTO;
import com.mysoft.alpha.entity.AdminRole;
import com.mysoft.alpha.entity.Company;
import com.mysoft.alpha.entity.User;
import com.mysoft.alpha.model.RegisterForm;
import com.mysoft.alpha.service.AdminRoleService;
import com.mysoft.alpha.service.UserService;
import com.mysoft.alpha.service.impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;

@Log4j2
@RunWith(MockitoJUnitRunner.class)
//@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Mock
    private UserDAO userDAO;
    @Mock
    private CompanyDAO companyDAO;
    @Mock
    private AdminRoleService adminRoleService;
    @InjectMocks
    private UserServiceImpl userService;

    List<User> users = new ArrayList<>();
    List<AdminRole> roles = new ArrayList<>();

    @Before
    public void before() {

    }

    @Test
    public void testList() {
        User testUser = User.builder().username("utest").build();
        AdminRole testRole = AdminRole.builder().name("rtest").build();
        users.add(testUser);
        roles.add(testRole);

        when(userDAO.findAll()).thenReturn(users);
        when(adminRoleService.listRolesByUser(testUser.getUsername())).thenReturn(roles);

        List<UserDTO> userDTOS = userService.list();
        Assert.assertThat(userDTOS.get(0).getUsername(), is("utest"));
        Assert.assertThat(userDTOS.get(0).getRoles().get(0).getName(), is("rtest"));
    }

    @Test
    public void testRegister_Normal() {
        User user = User.builder()
                .username("utest").password("123").name("测试用户").email("123@456.com").phonenum("12312312312").build();
                
        when(userDAO.save(any(User.class))).thenAnswer(i ->  i.getArguments()[0]);
        when(companyDAO.save(any(Company.class))).thenAnswer(i ->  i.getArguments()[0]);

        Assert.assertThat(userService.register(user), is(1));
    }

    @Test
    public void testResetPassword_Normal() {
        User testUser = User.builder()
                .username("utest").password("123456").name("测试用户").email("123@456.com").phonenum("12312312312").build();

        when(userDAO.findByUsername("utest")).thenReturn(testUser);
        when(userDAO.save(any(User.class))).thenAnswer(i ->  i.getArguments()[0]);

        User resetUser = userService.resetPassword(testUser);
        Assert.assertThat(resetUser.getPassword(),is(new SimpleHash("md5", "123", resetUser.getSalt(), 2).toString()));
    }

    @After
    public void after() {

    }
}
