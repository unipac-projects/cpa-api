package br.com.unipac.cpa.service;

import br.com.unipac.cpa.exception.URLException;
import br.com.unipac.cpa.model.domain.Role;
import br.com.unipac.cpa.model.domain.User;
import br.com.unipac.cpa.model.repository.UserRepository;
import br.com.unipac.cpa.model.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    public ExpectedException thrown = ExpectedException.none();

    private User getUser() {

        return User.builder()
                .email("Bradesco")
                .password("root@localhost")
                .roles(getRoles())
                .build();
    }

    private Set<Role> getRoles() {
        Set<Role> roles = new HashSet<>();

        Role role = new Role();
        role.setRole("ADMIN");
        roles.add(role);

        Role role1 = new Role();
        role1.setRole("USER");
        roles.add(role1);

        return roles;
    }

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllEmployeesTest() {
        List<User> list = new ArrayList<>();

        User client = getUser();
        User client1 = getUser();
        User client2 = getUser();

        list.add(client);
        list.add(client1);
        list.add(client2);

        when(userRepository.findAll()).thenReturn(list);

        // test
        List<User> urls = userService.listAll();

        assertEquals(3, urls.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void getUserByIdTest() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(getUser()));

        Optional<User> client = userService.findById(1L);

        assertEquals("fontestz@gmail.com", client.get().getEmail());
    }

    @Test
    public void getFindUserByShortIdTest() {
        User client = getUser();
        when(userService.findById(1l)).thenReturn(Optional.ofNullable(client));

        Optional<User> result = userService.findById(1l);

        assertEquals("fontestz@gmail.com", client.getEmail());
    }

    @Test
    public void createUserTest() {
        User user = getUser();
        userRepository.save(user);

        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void createAndStoreUserTest() {
        User user = getUser();
        userRepository.save(user);

        when(userRepository.save(user)).thenReturn(user);
        User result = userRepository.save(user);

        assertEquals("fontestz@gmail.com", result.getEmail());
    }

    @Test(expected = URLException.class)
    public void createAndStoreUserTest_and_should_throw_constraint_violation_execption() {
        thrown.expect(URLException.class);
        thrown.expectMessage("User is not valid!");

        User user = getUser();
        userRepository.save(user);

        when(userRepository.save(user)).thenReturn(user);
        User result = userRepository.save(user);

        assertEquals("root@localhost", result.getEmail());
    }

}
