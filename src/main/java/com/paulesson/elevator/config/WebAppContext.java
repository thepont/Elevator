package com.paulesson.elevator.config;

import com.paulesson.elevator.elevatorcontrol.CommandProcessingThread;
import com.paulesson.elevator.elevatorcontrol.Elevator;
import com.paulesson.elevator.elevatorcontrol.ElevatorCommandRouter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

@Configuration
@EnableAsync
@EnableWebMvc
@ComponentScan(basePackages = {"com.paulesson.elevate.mvc.controllers"})
public class WebAppContext extends WebMvcConfigurerAdapter {

    public static int NUMBER_ELEVATORS = 4;
    public static char FIRST_ELEVATOR_NAME = 'A';
    
    @Bean
    public UrlBasedViewResolver setupViewResolver() {
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        return resolver;
    }
    
    @Bean
    public ElevatorCommandRouter setupCommandRouter(){
        List<Elevator> availibleElevators = new ArrayList<Elevator>();
        for(int i = 0; i < NUMBER_ELEVATORS; i++)
        {
            availibleElevators.add(new Elevator(String.valueOf(FIRST_ELEVATOR_NAME+i)));
        }
        ElevatorCommandRouter ecr = new ElevatorCommandRouter(availibleElevators);
        return ecr;
    }
    
    @Bean
    public CommandProcessingThread setupCommandProcessingThread(){
        CommandProcessingThread cpt = new CommandProcessingThread();
        //cpt.start();
        return cpt;
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/css/");
    }
}