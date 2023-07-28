package com.pragma.powerup.usermicroservice;

import com.pragma.powerup.usermicroservice.domain.api.MailExtractor;
import com.pragma.powerup.usermicroservice.domain.exceptions.ForbiddenException;
import com.pragma.powerup.usermicroservice.domain.exceptions.UnauthorizedException;
import com.pragma.powerup.usermicroservice.domain.model.Role;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.spi.ICognitoServicePort;
import com.pragma.powerup.usermicroservice.domain.spi.IRolePersistencePort;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import com.pragma.powerup.usermicroservice.domain.usecase.UserUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserUseCaseTest {

    @Mock
    private IUserPersistencePort userPersistencePort;

    @Mock
    private IRolePersistencePort rolePersistencePort;

    @Mock
    private MailExtractor mailExtractor;

    @Mock
    private ICognitoServicePort cognitoServicePort;

    @InjectMocks
    private UserUseCase userUseCase;

    @BeforeEach
    public void setup() {
    }

    private void setupMockExtractEmailByToken(){
        doAnswer(invocation -> {
            String argument = invocation.getArgument(0);
            Map<String, String> emails = Map.of(
                    "adminToken", "admin@mail.com",
                    "ownerToken", "owner@mail.com",
                    "employeeToken", "employee@mail.com"
            );
            return emails.getOrDefault(argument, "customer@mail.com");
        }).when(mailExtractor).extractEmail(anyString());
    }

    private void setupMockAssignRoleByEmail(){
        doAnswer(invocation -> {
            String argument = invocation.getArgument(0);
            Map<String, User> roles = Map.of(
                    "admin@mail.com", DataUser.USER_WITH_ADMIN_ROLE,
                    "owner@mail.com", DataUser.USER_WITH_OWNER_ROLE,
                    "employee@mail.com", DataUser.USER_WITH_EMPLOYEE_ROLE
            );
            return roles.getOrDefault(argument, DataUser.USER_WITH_CUSTOMER_ROLE);
        }).when(userPersistencePort).getUser(anyString());
    }

    private void setupMockAssignRoleById(){
        doAnswer(invocation -> {
            Long argument = invocation.getArgument(0);
            Map<Long, Role> roles = Map.of(
                    1L, DataUser.ADMIN_ROLE,
                    3L, DataUser.OWNER_ROLE,
                    4L, DataUser.EMPLOYEE_ROLE
            );
            return roles.getOrDefault(argument, DataUser.CUSTOMER_ROLE);
        }).when(rolePersistencePort).getRole(anyLong());
    }


    @ParameterizedTest
    @ValueSource(strings = {"customerToken", "ownerToken", "employeeToken", "tokenNonsense"})
    @DisplayName("Test that an exception is thrown if a non-admin user attempts to create an Owner user.")
     void testExceptionOnNonAdminUserCreatingOwner(String token) {
        //Given
        setupMockExtractEmailByToken();
        setupMockAssignRoleByEmail();

        //THEN
        assertThrows(ForbiddenException.class,
                //WHEN
                () -> userUseCase.saveOwner(token, DataUser.USER)
        );
        verify(userPersistencePort, times(0)).saveUser(any(User.class));
        verify(cognitoServicePort, times(0)).signUpUser(any(User.class));
    }

    @Test
    @DisplayName("Test successful Owner user creation.")
    void testOwnerUserCreationSuccess(){
        //GIVEN
        User newUser = DataUser.USER_WITHOUT_ROLE;
        setupMockExtractEmailByToken();
        setupMockAssignRoleByEmail();
        setupMockAssignRoleById();

        //WHEN

        userUseCase.saveOwner("adminToken", newUser);

        //THEN

        verify(userPersistencePort).saveUser(newUser);
        assertEquals(DataUser.OWNER_ROLE_ID, newUser.getRole().getId());
    }

    @ParameterizedTest
    @ValueSource(strings = {"customerToken", "adminToken", "employeeToken", "tokenNonsense"})
    @DisplayName("Test that an exception is thrown if a non-admin user attempts to create an Owner user.")
    void testExceptionOnNonOwnerUserCreatingEmployee(String token) {
        //Given
        setupMockExtractEmailByToken();
        setupMockAssignRoleByEmail();

        //THEN
        assertThrows(UnauthorizedException.class,
                //WHEN
                () -> userUseCase.saveEmployee(token, DataUser.USER)
        );
        verify(userPersistencePort, times(0)).saveUser(any(User.class));
    }

    @Test
    @DisplayName("Test successful Employee user creation.")
    void testEmployeeUserCreationSuccess(){
        //GIVEN
        User newUser = DataUser.USER_WITHOUT_ROLE;
        setupMockExtractEmailByToken();
        setupMockAssignRoleByEmail();
        setupMockAssignRoleById();

        //WHEN

        userUseCase.saveEmployee("ownerToken", newUser);

        //THEN

        verify(userPersistencePort).saveUser(newUser);
        assertEquals(DataUser.EMPLOYEE_ROLE_ID, newUser.getRole().getId());
    }

    @Test
    @DisplayName("Test successful saving of a customer user.")
    void testCustomerUserSaveSuccess() {
        //GIVEN
        User newUser = DataUser.USER_WITHOUT_ROLE;
        setupMockAssignRoleById();

        //WHEN

        userUseCase.saveCustomer(newUser);

        //THEN

        verify(userPersistencePort).saveUser(newUser);
        assertEquals(DataUser.CUSTOMER_ROLE_ID, newUser.getRole().getId());
    }

    @Test
    @DisplayName("Test successful retrieval of a user by ID.")
    void testGetUserByIdSuccess() {

        //GIVEN
        Long userId = 1L;
        User expectedUser = DataUser.USER;

        when(userPersistencePort.getUserById(userId)).thenReturn(expectedUser);

        //WHEN

        User actualUser = userUseCase.getUserById(userId);

        //THEN
        verify(userPersistencePort).getUserById(userId);
        assertEquals(expectedUser, actualUser);
    }



}