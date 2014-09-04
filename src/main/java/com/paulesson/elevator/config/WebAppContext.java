package com.paulesson.elevator.config;

import com.paulesson.elevator.db.dao.CommandDao;
import com.paulesson.elevator.db.dao.CommandDaoImpl;
import com.paulesson.elevator.elevatorcontrol.CommandProcessingThread;
import com.paulesson.elevator.elevatorcontrol.Elevator;
import com.paulesson.elevator.elevatorcontrol.ElevatorCommandRouter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;



@Configuration
@EnableAsync
@EnableWebMvc
@ComponentScan(basePackages = {"com.paulesson.elevator.web.controllers"})
public class WebAppContext extends WebMvcConfigurerAdapter {

    public static final String JSP_LOCATION = "/WEB-INF/views/";
    public static final String JSP_SUFFIX = ".jsp";
    
    public static final String CSS_LOCATION = "/WEB-INF/css/";
    public static final String CSS_LOCATION_WEB_PATH = "/css/**";
    
    public static final int NUMBER_ELEVATORS = 4;
    public static final char FIRST_ELEVATOR_NAME = 'A';
    
    public static final String JDBC_DRIVER_CLASS_NAME = "org.h2.Driver";
    public static final String JDBC_DRIVER_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    public static final String JDBC_DB_USERNAME = "sa";
    public static final String JDBC_DB_PASSWORD = "";
    
    
    
    @Bean
    public UrlBasedViewResolver setupViewResolver() {
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setPrefix(JSP_LOCATION);
        resolver.setSuffix(JSP_SUFFIX);
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
        return cpt;
    }
    //DataSource
    @Bean
    public DriverManagerDataSource setupDataSource(){
        DriverManagerDataSource dmds = new DriverManagerDataSource();
        dmds.setDriverClassName(JDBC_DRIVER_CLASS_NAME);
        dmds.setUrl(JDBC_DRIVER_URL);
        dmds.setUsername(JDBC_DB_USERNAME);
        dmds.setPassword(JDBC_DB_PASSWORD);
        return dmds;
    }
    
    @Bean
    public AnnotationSessionFactoryBean getSessionFactory()
    {
        AnnotationSessionFactoryBean sessionFactory = new AnnotationSessionFactoryBean();
        sessionFactory.setDataSource(setupDataSource());
        String[] packages = {"com.paulesson.elevator.db.entities"};
        sessionFactory.setAnnotatedClasses(com.paulesson.elevator.db.entities.Command.class);
        //sessionFactory.setAnnotatedPackages(packages);
        //sessionFactory.setAnnotatedClasses(annotatedClasses);
        Properties hibernateProps = new Properties();
        
        hibernateProps.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        hibernateProps.setProperty("hibernate.current_session_context_class", "thread");
        hibernateProps.setProperty("hibernate.show_sql", "true");
        hibernateProps.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        
        sessionFactory.setHibernateProperties(hibernateProps);
        return sessionFactory;
    }
    
    @Bean
    public CommandDao setupCommandDao(){
        CommandDao commandDao = new CommandDaoImpl();
        ((CommandDaoImpl)commandDao).setSessionFactory(getSessionFactory().getObject());
        return commandDao;
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler(CSS_LOCATION_WEB_PATH).addResourceLocations(CSS_LOCATION);
    }
}