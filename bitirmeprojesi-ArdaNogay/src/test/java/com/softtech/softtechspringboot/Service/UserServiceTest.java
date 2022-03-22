package com.softtech.softtechspringboot.Service;


import com.softtech.softtechspringboot.Converter.UserMapper;
import com.softtech.softtechspringboot.Dto.UserSaveAndUpdateRequestDto;
import com.softtech.softtechspringboot.Entity.User;
import com.softtech.softtechspringboot.Exception.DoesNotExistExceptions;
import com.softtech.softtechspringboot.Service.EntityService.UserEntityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserEntityService userEntityService;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldSave() {
        UserSaveAndUpdateRequestDto requestDto = mock(UserSaveAndUpdateRequestDto.class);
        when(requestDto.getUserName()).thenReturn("test");
        when(requestDto.getPassword()).thenReturn("123");

        User user = mock(User.class);

        when(passwordEncoder.encode(anyString())).thenReturn("1235_asd");
        when(userEntityService.save(any())).thenReturn(user);
        UserSaveAndUpdateRequestDto result = userService.save(requestDto);

        assertEquals("1235_asd", result.getPassword());
    }

    @Test
    void shouldNotSaveWhenUserNameIsExist() {
        UserSaveAndUpdateRequestDto requestDto = mock(UserSaveAndUpdateRequestDto.class);

        when(requestDto.getUserName()).thenReturn("userName");
        when(userEntityService.findByUserName(requestDto.getUserName())).thenReturn("userName");

        assertThrows(DoesNotExistExceptions.class, () -> userService.save(requestDto));
    }

    @Test
    void shouldNotSaveWhenParameterIsNull() {
        assertThrows(NullPointerException.class, () -> userService.save(null));
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void shouldFindAll() {

        User user = mock(User.class);
        List<User>  userList = new ArrayList<>();
        userList.add(user);

        when(userEntityService.findAll()).thenReturn(userList);

        List<UserSaveAndUpdateRequestDto> requestDtoList = userService.findAll();

        assertEquals(1,requestDtoList.size());

    }

    @Test
    void shouldNotFindAll() {
    }
}