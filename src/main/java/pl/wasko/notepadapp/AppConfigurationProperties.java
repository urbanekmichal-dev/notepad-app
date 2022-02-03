package pl.wasko.notepadapp;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties("properties")
@Configuration
@Getter
@Setter
public class AppConfigurationProperties {
    private String myProperties;
}
