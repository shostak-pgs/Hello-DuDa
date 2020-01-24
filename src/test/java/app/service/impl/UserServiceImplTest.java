package app.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserServiceImplTest {

    @Mock
    private ServiceProvider serviceProviderMock;

    @Mock
    private UserServiceImpl userServiceMock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void getUser() {
    }

    @Test
    public void create() {
    }
}