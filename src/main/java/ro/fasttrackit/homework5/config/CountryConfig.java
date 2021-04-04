package ro.fasttrackit.homework5.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotBlank;

@Configuration
@ConfigurationProperties(prefix = "country")
@Validated
public class CountryConfig {
    @NotBlank
    private String fileLocation;

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }
}
