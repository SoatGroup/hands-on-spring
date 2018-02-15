package fr.soat.spring5.step2;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Configuration de jenkins.
 *
 * @author Soat - 10/16/17.
 */
@Component
@ConfigurationProperties("jenkins")
public class JenkinsProperties {

    private String url;

    private Integer interval;

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(final Integer interval) {
        this.interval = interval;
    }
}