import java.time.LocalDateTime;

public class Fire {
    private Float latitude;
    private Float longitude;
    private LocalDateTime start;
    private LocalDateTime end;
    private User recordResponsible;
    private Enum<Identification> identification;
    private String satelliteName;
    private Address address;
    private String parkName;
    private LocalDateTime savedAt;

    public Fire(Float latitude, Float longitude, LocalDateTime start, LocalDateTime end, User recordResponsible, Enum<Identification> identification, String satelliteName, Address address, String parkName, LocalDateTime savedAt) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.start = start;
        this.end = end;
        this.recordResponsible = recordResponsible;
        this.identification = identification;
        this.satelliteName = satelliteName;
        this.address = address;
        this.parkName = parkName;
        this.savedAt = savedAt;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public User getRecordResponsible() {
        return recordResponsible;
    }

    public void setRecordResponsible(User recordResponsible) {
        this.recordResponsible = recordResponsible;
    }

    public Enum<Identification> getIdentification() {
        return identification;
    }

    public void setIdentification(Enum<Identification> identification) {
        this.identification = identification;
    }

    public String getSatelliteName() {
        return satelliteName;
    }

    public void setSatelliteName(String satelliteName) {
        this.satelliteName = satelliteName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public LocalDateTime getSavedAt() {
        return savedAt;
    }

    public void setSavedAt(LocalDateTime savedAt) {
        this.savedAt = savedAt;
    }
}
