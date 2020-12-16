package se.swcg.consultauction.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
public class Qualifications {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;
    private boolean frontend;
    private boolean backend;
    private List<String> certificate;
    private List<String> experience;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private List<Languages> language;

    public Qualifications(String id, boolean frontend, boolean backend, List<String> certificate, List<String> experience, List<Languages> language) {
        this.id = id;
        this.frontend = frontend;
        this.backend = backend;
        this.certificate = certificate;
        this.experience = experience;
        this.language = language;
    }

    public Qualifications(boolean frontend, boolean backend, List<String> certificate, List<String> experience, List<Languages> language) {
        this.frontend = frontend;
        this.backend = backend;
        this.certificate = certificate;
        this.experience = experience;
        this.language = language;
    }

    public Qualifications() {
    }
}
