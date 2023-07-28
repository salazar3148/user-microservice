package com.pragma.powerup.usermicroservice;

import com.pragma.powerup.usermicroservice.domain.model.Role;
import com.pragma.powerup.usermicroservice.domain.spi.IRolePersistencePort;
import com.pragma.powerup.usermicroservice.domain.usecase.RoleUseCase;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoleUseCaseTest {

    @Mock
    private IRolePersistencePort rolePersistencePort;
    @InjectMocks
    private RoleUseCase roleUseCase;

    @Test
    void testGetAllRolesSuccess() {
        //GIVEN
        List<Role> expectedList = Arrays.asList(DataUser.ADMIN_ROLE, DataUser.CUSTOMER_ROLE, DataUser.OWNER_ROLE, DataUser.EMPLOYEE_ROLE);

        when(rolePersistencePort.getAllRoles()).thenReturn(
                expectedList
        );

        //WHEN

        List<Role> roleList = roleUseCase.getAllRoles();

        //THEN

        verify(rolePersistencePort).getAllRoles();
        assertEquals(roleList, expectedList);
        assertEquals(roleList.size(), expectedList.size());
    }
}
