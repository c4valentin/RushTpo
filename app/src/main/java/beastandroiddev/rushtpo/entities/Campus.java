package beastandroiddev.rushtpo.entities;

public class Campus {
    private String campusName;
    private String campusUrl;

    public Campus(String campusName, String campusUrl) {
        this.campusName = campusName;
        this.campusUrl = campusUrl;
    }

    public Campus() {
    }

    public String getCampusName() {
        return campusName;
    }

    public String getCampusUrl() {
        return campusUrl;
    }
}
