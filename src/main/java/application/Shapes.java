package application;

public interface Shapes {
    /**
     * @return (Double) Returnerer x start posisjon (Dette er centerX for myCircle og myEllipse)
     */
    double getX();

    /**
     * @return (Double) Returnerer y start posisjon (Dette er centerY for myCircle og myEllipse)
     */
    double getY();

    /**
     * @return (Double) Returnerer areal til shape.
     */
    double getArea();

    /**
     * @return (Double) Returnerer omkrets til shape.
     */
    double getCircumference();

    /**
     * @return Returnerer en String som sier hva slags shape det er og hva det inneholder. Vanligvis i format:
     *         getShape() + ", x: " + getX() + ", y: " + getY() + ", height: " + getHeight() + ", width: " + getWidth() + ", color: " + getColor();
     *         Kan variere litt fra ulike shapes.
     */
    String toString();

    /**
     * @return Returnerer en String som forteller hva slags shape det er.
     */
    String getShape();

    /**
     * Oppdaterer objektets posisjon, bredde og høyde basert på start (x,y) og slutt (x,y).
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     */
    void createShape(double startX, double startY, double endX, double endY);

    void setShapeAt(double x, double y);

}