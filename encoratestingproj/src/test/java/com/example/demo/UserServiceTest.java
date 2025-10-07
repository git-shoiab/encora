package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.binder.jvm.JvmGcMetrics;
import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldReturnUserName_whenUserExists() {
    	PrometheusMeterRegistry registry = new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);
        Timer timer = registry.timer("application.process.time");  
        timer.record(()->{
			User user = new User(1L, "Alice");
	        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

	        String result = userService.getUserNameById(1L);

	        assertEquals("Alice", result);
	        verify(userRepository).findById(1L);
		});
        
        System.out.println(registry.scrape());

       
    }

    @Test
    void shouldReturnUnknown_whenUserNotFound() {
        when(userRepository.findById(2L)).thenReturn(Optional.empty());

        String result = userService.getUserNameById(2L);
        System.out.println(".....:"+result);
        assertEquals("Unknown", result);
    }
}

