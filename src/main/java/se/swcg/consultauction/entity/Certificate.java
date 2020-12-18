package se.swcg.consultauction.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Certificate {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String certificateId;
    private String certificateContent;

    public Certificate(String certificateId, String certificateContent) {
        this(certificateContent);
        this.certificateId = certificateId;
    }

    public Certificate(String certificateContent) {
        this.certificateContent = certificateContent;
    }

    public Certificate() {
    }

    public String getCertificateId() {
        return certificateId;
    }

    public String getCertificateContent() {
        return certificateContent;
    }

    public void setCertificateContent(String certificateContent) {
        this.certificateContent = certificateContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Certificate that = (Certificate) o;
        return Objects.equals(certificateId, that.certificateId) &&
                Objects.equals(certificateContent, that.certificateContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(certificateId, certificateContent);
    }

    @Override
    public String toString() {
        return "Certificate{" +
                "id='" + certificateId + '\'' +
                ", certificateContent='" + certificateContent + '\'' +
                '}';
    }
}
