package repository.model;

public class GeoBox {

    private int latitudeTo;
    private int latitudeFrom;
    private int longitudeTo;
    private int longitudeFrom;

    public GeoBox(int latitudeTo, int latitudeFrom, int longitudeTo, int longitudeFrom) {
        if(latitudeTo > latitudeFrom) {
            this.latitudeTo = latitudeFrom;
            this.latitudeFrom = latitudeTo;
        }
        else {
            this.latitudeTo = latitudeTo;
            this.latitudeFrom = latitudeFrom;
        }

        if(longitudeTo > longitudeFrom) {
            this.longitudeTo = longitudeFrom;
            this.longitudeFrom = longitudeTo;
        }
        else {
            this.longitudeTo = longitudeTo;
            this.longitudeFrom = longitudeFrom;
        }
    }

    public int getLatitudeTo() {
        return latitudeTo;
    }

    public int getLatitudeFrom() {
        return latitudeFrom;
    }

    public int getLongitudeTo() {
        return longitudeTo;
    }

    public int getLongitudeFrom() {
        return longitudeFrom;
    }

    public boolean isGeoPointWithinBox(GeoPoint point) {
        boolean isWithinBox = false;
        if(between(point.getLatitude(), latitudeTo, latitudeFrom) && between(point.getLongitude(), longitudeTo, longitudeFrom))
            isWithinBox = true;

        return isWithinBox;
    }

    private boolean between(int i, int minValueInclusive, int maxValueInclusive) {
        return (i >= minValueInclusive && i <= maxValueInclusive);
    }
}
