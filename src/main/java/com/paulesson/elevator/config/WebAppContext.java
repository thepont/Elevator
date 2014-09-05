package com.paulesson.elevator.config;

import com.paulesson.elevator.db.dao.CommandDao;
import com.paulesson.elevator.db.dao.CommandDaoImpl;
import com.paulesson.elevator.db.dao.ElevatorDao;
import com.paulesson.elevator.db.dao.ElevatorDaoImpl;
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
    
    public static final String JDBC_DRIVER_CLASS_NAME = "org.h2.Driver";
    public static final String JDBC_DRIVER_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    public static final String JDBC_DB_USERNAME = "sa";
    public static final String JDBC_DB_PASSWORD = "";

    public static final String HIBERNATE_DIALECT_PROPERTY = "hibernate.dialect";
    public static final String HIBERNATE_DIALECT_PROPERTY_VALUE = "org.hibernate.dialect.H2Dialect";

    public static final String HIBERNATE_HBM2DDL_PROPERTY = "hibernate.hbm2ddl.auto";
    public static final String HIBERNATE_HBM2DDL_PROPERTY_VALUE = "create-drop";

    public static final String HIBERNATE_CURRENT_SESSION_CONTEXT_PROPERTY = "hibernate.current_session_context_class";
    public static final String HIBERNATE_CURRENT_SESSION_CONTEXT_PROPERTY_VALUE = "thread";

    public static final String HIBERNATE_SHOW_SQL_PROPERTY = "hibernate.show_sql";
    public static final String HIBERNATE_SHOW_SQL_PROPERTY_VALUE = "true";
    
    public static final String ELEVATOR_A_NAME = "A";
    public static final String ELEVATOR_B_NAME = "B";
    public static final String ELEVATOR_C_NAME = "C";
    public static final String ELEVATOR_D_NAME = "D";
    
    public static final int ELEVATOR_A_ID = 0;
    public static final int ELEVATOR_B_ID = 1;
    public static final int ELEVATOR_C_ID = 2;
    public static final int ELEVATOR_D_ID = 3;
    
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
        
        ElevatorDao elevatorDao = setupElevatorDao();
        List<Elevator> availibleElevators = new ArrayList<Elevator>();
        
        availibleElevators.add(new Elevator(ELEVATOR_A_NAME,ELEVATOR_A_ID));
        availibleElevators.add(new Elevator(ELEVATOR_B_NAME,ELEVATOR_B_ID));
        availibleElevators.add(new Elevator(ELEVATOR_C_NAME,ELEVATOR_C_ID));
        availibleElevators.add(new Elevator(ELEVATOR_D_NAME,ELEVATOR_D_ID));
        
        ElevatorCommandRouter ecr = new ElevatorCommandRouter(availibleElevators);
        ecr.setElevatorDao(elevatorDao);
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
        sessionFactory.setAnnotatedClasses( com.paulesson.elevator.db.entities.Command.class,
                                            com.paulesson.elevator.db.entities.Elevator.class );
        Properties hibernateProps = new Properties();
        
        hibernateProps.setProperty(HIBERNATE_DIALECT_PROPERTY, HIBERNATE_DIALECT_PROPERTY_VALUE);
        hibernateProps.setProperty(HIBERNATE_CURRENT_SESSION_CONTEXT_PROPERTY, HIBERNATE_CURRENT_SESSION_CONTEXT_PROPERTY_VALUE);
        hibernateProps.setProperty(HIBERNATE_SHOW_SQL_PROPERTY , HIBERNATE_SHOW_SQL_PROPERTY_VALUE );
        hibernateProps.setProperty(HIBERNATE_HBM2DDL_PROPERTY, HIBERNATE_HBM2DDL_PROPERTY_VALUE);
        
        sessionFactory.setHibernateProperties(hibernateProps);
        return sessionFactory;
    }
    
    @Bean
    public CommandDao setupCommandDao(){
        CommandDao commandDao = new CommandDaoImpl();
        ((CommandDaoImpl)commandDao).setSessionFactory(getSessionFactory().getObject());
        return commandDao;
    }
    
    @Bean
    public ElevatorDao setupElevatorDao(){
        ElevatorDao elevatorDao = new ElevatorDaoImpl() {};
        ((ElevatorDaoImpl)elevatorDao).setSessionFactory(getSessionFactory().getObject());
        return elevatorDao;
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler(CSS_LOCATION_WEB_PATH).addResourceLocations(CSS_LOCATION);
    }
}