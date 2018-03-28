package fr.soat.spring5.step2;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Représente un job jenkins.
 *
 * @author Soat - 10/16/17.
 */
public class JenkinsJob {

    @JsonProperty(value = "_class")
    private String classType;

    private JenkinsJob parent;

    private String name;

    private String url;

    private String color;

    public String getClassType() {
        return classType;
    }

    public void setClassType(final String classType) {
        this.classType = classType;
    }

    public JenkinsJob getParent() {
        return parent;
    }

    public void setParent(final JenkinsJob parent) {
        this.parent = (this.parent != null) ? this.parent : parent;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public String getColor() {
        return color;
    }

    public void setColor(final String color) {
        this.color = color;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final JenkinsJob that = (JenkinsJob) o;
        return Objects.equals(classType, that.classType) &&
                Objects.equals(parent, that.parent) &&
                Objects.equals(name, that.name) &&
                Objects.equals(url, that.url) &&
                Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classType, parent, name, url, color);
    }

    @Override
    public String toString() {
        return "JenkinsJob{" +
                "classType='" + classType + '\'' +
                ", parent=" + parent +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}