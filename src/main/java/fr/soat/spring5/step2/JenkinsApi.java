package fr.soat.spring5.step2;

import java.util.List;

/**
 * Représente le noeud racine de la sortie de l'api {@code /api/json} de jenkins.
 *
 * @author Soat - 10/16/17.
 */
public class JenkinsApi {

    private List<JenkinsJob> jobs;

    public List<JenkinsJob> getJobs() {
        return jobs;
    }

    public void setJobs(final List<JenkinsJob> jobs) {
        this.jobs = jobs;
    }
}
