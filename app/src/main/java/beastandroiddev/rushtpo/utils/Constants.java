package beastandroiddev.rushtpo.utils;

public class Constants {
    public static final String USER_SHARED_PREFERANCE = "USER_SHARED_PREFERENCE";
    public static final String USER_CAMPUS = "USER_CAMPUS";
    public static final String USER_CAMPUS_URL = "USER_CAMPUS_URL";

    public static final String FIRE_BASE_PATH_BROTHERS = "brothers";
    public static final String FIRE_BASE_PATH_ASU = "asu";
    public static final String FIRE_BASE_PATH_DATA = "data";
    public static final String FIRE_BASE_PATH_CSU = "cu";
    public static final String FIRE_BASE_PATH_CAMPUSES = "campuses";

    public static final String FIRE_BASE_PATH_INFORMATION_CARDS = "informationCards";
    public static final String FIRE_BASE_PATH_BROTHERHOOD_CARDS = "brotherhoodCards";
    public static final String FIRE_BASE_PATH_SERVICE_CARDS = "serviceCards";
    public static final String FIRE_BASE_PATH_SOCIAL_CARDS = "socialCards";


    public static final String FIRE_BASE_PATH_EVENT_PICTURES = "eventPictures";
    public static final String FIRE_BASE_PATH_SEXY_SHOWCASE_PICTURES = "sexyShowCasePics";
    public static final String FIRE_BASE_PATH_SERVICE_PICTURES = "servicePics";
    public static final String FIRE_BASE_PATH_TRAVELING_PICTURES = "travelingPics";



    public static final String FIRE_BASE_PATH_RUSH_EVENTS = "rushEvents";
    public static final String FIRE_BASE_PATH_RUSH_INFORMATIONALS = "informational";
    public static final String FIRE_BASE_PATH_RUSH_SERVICES = "services";
    public static final String FIRE_BASE_PATH_RUSH_SOCIALS ="socials";



    public static String encodeName(String userEmail){
        return userEmail.replace(".",",");
    }
}
