package se.swcg.consultauction.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Skills {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String skillsId;

    private String skillsName;

    public Skills(String skillsId, String skillName) {
        this.skillsId = skillsId;
        this.skillsName = skillName;
    }

    public Skills(String skillName) {
        this.skillsName = skillName;
    }

    public Skills() {
    }

    public String getSkillsId() {
        return skillsId;
    }

    public String getSkillName() {
        return skillsName;
    }

    public void setSkillName(String skillName) {
        this.skillsName = skillName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skills skills = (Skills) o;
        return Objects.equals(skillsId, skills.skillsId) && Objects.equals(skillsName, skills.skillsName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skillsId, skillsName);
    }

    @Override
    public String toString() {
        return "Skills{" +
                "skillsId='" + skillsId + '\'' +
                ", skillName='" + skillsName + '\'' +
                '}';
    }
}
