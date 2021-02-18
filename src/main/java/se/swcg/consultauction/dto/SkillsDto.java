package se.swcg.consultauction.dto;

public class SkillsDto {

    private String skillsId;

    private String skillName;

    public SkillsDto(String skillsId, String skillName) {
        this.skillsId = skillsId;
        this.skillName = skillName;
    }

    public String getSkillsId() {
        return skillsId;
    }

    public void setSkillsId(String skillsId) {
        this.skillsId = skillsId;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }
}
