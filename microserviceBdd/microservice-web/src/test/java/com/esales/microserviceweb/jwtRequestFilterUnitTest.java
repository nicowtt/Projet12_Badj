package com.esales.microserviceweb;

import com.esales.microservicebusiness.securitytoken.JwtUserDetailsService;
import com.esales.microserviceweb.security.JwtRequestFilter;
import com.esales.microserviceweb.security.JwtTokenUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Collection;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class jwtRequestFilterUnitTest {

    private static final String token = "Bearer 260bce87-6be9-4897-add7-b3b675952538";
    private static final String testUri = "/testUri";

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @Mock
    private JwtUserDetailsService jwtUserDetailsService;

    @InjectMocks
    private JwtRequestFilter jwtRequestFilter;

    @Test
    public void testDoFilterInternalPositiveScenarioWhenTokenIsInHeader() throws ServletException, IOException {
        String TOKEN = "Authorization";
        String userName = "nico";
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader(TOKEN, token);
        request.setRequestURI(testUri);
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterChain filterChain = new MockFilterChain();
        when(jwtTokenUtil.getUsernameFromToken(anyString())).thenReturn(userName);
        when(jwtTokenUtil.validateToken(anyString(), any())).thenReturn(true);
        when(jwtUserDetailsService.loadUserByUsername(anyString())).thenReturn(this.createUserDetails());
        jwtRequestFilter.doFilter(request, response, filterChain);
        Assert.assertEquals(200, response.getStatus());
    }

    public UserDetails createUserDetails() {
        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public String getPassword() {
                return null;
            }

            @Override
            public String getUsername() {
                return null;
            }

            @Override
            public boolean isAccountNonExpired() {
                return false;
            }

            @Override
            public boolean isAccountNonLocked() {
                return false;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return false;
            }

            @Override
            public boolean isEnabled() {
                return false;
            }
        };
    }
}
