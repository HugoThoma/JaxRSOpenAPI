package fr.istic.taa.jaxrs.dto;

public class FicheDTO {
    private String type;
    private String title;
    private String description;
    private Long userID;

    public String getType() {
        return type;
    }

    public FicheDTO(String type, String title, String description, Long userID) {
        this.type = type;
        this.title = title;
        this.description = description;
        this.userID = userID;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }
}
