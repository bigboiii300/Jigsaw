package enums;

import java.util.List;
import java.util.Random;

/**
 * Тип фигуры.
 */
public enum ShapeType {
    FIRST,
    SECOND,
    THIRD,
    FOURTH,
    FIFTH,
    SIXTH,
    SEVENTH,
    EIGHTH,
    NINTH,
    TENTH,
    ELEVENTH,
    TWELFTH,
    THIRTEENTH,
    FOURTEENTH,
    FIFTEENTH,
    SIXTEENTH,
    SEVENTEENTH,
    EIGHTEENTH,
    NINETEENTH,
    TWENTIETH,
    TWENTY_FIRST,
    TWENTY_SECOND,
    TWENTY_THIRD,
    TWENTY_FOURTH,
    TWENTY_FIFTH,
    TWENTY_SIXTH,
    TWENTY_SEVENTH,
    TWENTY_EIGHTH,
    TWENTY_NINTH,
    THIRTIETH,
    THIRTY_FIRST;

    private static final List<ShapeType> VALUES = List.of(values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    /**
     * @return Получение рандомного типа фигуры
     */
    public static ShapeType generateShape() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}

