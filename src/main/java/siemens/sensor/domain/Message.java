package siemens.sensor.domain;


import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "coordinates")
    @Min(value = -180, message = "Значение координато должно находиться в диапозоне от -180 до 180 градусов")
    @Max(value = 180, message = "Значение координато должно находиться в диапозоне от -180 до 180 градусов")
    private int coordinates;

    @Column(name = "temperature")
    @Min(value = -100, message = "Значение температуры должно находиться в диапозоне от -100 до +100  градусов Цельсия")
    @Max(value = 100, message = "Значение температуры должно находиться в диапозоне от -100 до +100 градусов Цельсия")
    private int temperature;

    public Message(int coordinates, int temperature) {
        this.coordinates = coordinates;
        this.temperature = temperature;
    }

    public Message() {
    }

    public int getId() {
        return id;
    }

    public int getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(int coordinates) {
        this.coordinates = coordinates;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
}



