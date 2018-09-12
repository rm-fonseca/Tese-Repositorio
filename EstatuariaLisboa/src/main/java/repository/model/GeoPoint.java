package repository.model;

public class GeoPoint {
    private int latitudePoint;
    private int longitudePoint;

    public GeoPoint(int latitudePoint, int longitudePoint) {
        this.latitudePoint = latitudePoint;
        this.longitudePoint = longitudePoint;
    }

    public int getLatitude() {
        return latitudePoint;
    }

    public int getLongitude() {
        return longitudePoint;
    }
}
