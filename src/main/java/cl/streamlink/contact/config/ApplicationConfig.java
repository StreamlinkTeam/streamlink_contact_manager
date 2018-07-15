package cl.streamlink.contact.config;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.util.TimeZone;

@Configuration
@EnableJpaAuditing
public class ApplicationConfig implements ApplicationContextAware {

    private static ApplicationContext __applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        __applicationContext = applicationContext;
    }

    public static <T> T getService(String name, Class<T> clazz)
    {
        return __applicationContext.getBean(name, clazz);
    }

    public static <T> T getService(Class<T> clazz)
    {
        return __applicationContext.getBean(clazz);
    }


    @Bean
    public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
        return new Jackson2ObjectMapperBuilder()
                .timeZone(TimeZone.getDefault())
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .findModulesViaServiceLoader(true)
                .modulesToInstall(new JavaTimeModule());
    }

    @Bean
    public MessageSource messageSource()
    {
        final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

        messageSource.setBasename("classpath:i18n/messages");
        messageSource.setFallbackToSystemLocale(false);
        messageSource.setAlwaysUseMessageFormat(true);
        messageSource.setCacheSeconds(0);
        messageSource.setDefaultEncoding("UTF-8");

        return messageSource;
    }

}
