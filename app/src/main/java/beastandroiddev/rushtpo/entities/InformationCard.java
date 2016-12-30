package beastandroiddev.rushtpo.entities;

public class InformationCard {
    private String cardDescription;
    private String cardPicture;
    private String cardTitle;
    private String url;
    private boolean video;


    public InformationCard() {
    }


    public InformationCard(String cardDescription, String cardPicture, String cardTitle, String url, boolean video) {
        this.cardDescription = cardDescription;
        this.cardPicture = cardPicture;
        this.cardTitle = cardTitle;
        this.url = url;
        this.video = video;
    }

    public String getCardDescription() {
        return cardDescription;
    }

    public String getCardPicture() {
        return cardPicture;
    }

    public String getCardTitle() {
        return cardTitle;
    }

    public String getUrl() {
        return url;
    }

    public boolean isVideo() {
        return video;
    }
}
