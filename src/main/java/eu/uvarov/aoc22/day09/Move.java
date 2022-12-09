package eu.uvarov.aoc22.day09;

public record Move (String direction, String distance) {

    public Move(String direction, String distance) {
        this.direction = direction;
        this.distance = distance;
    }
}

