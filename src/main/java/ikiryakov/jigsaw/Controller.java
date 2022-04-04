package ikiryakov.jigsaw;

import enums.ShapeType;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.text.ParseException;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public Text timer;
    public Button stopGame;
    public Pane pane;
    private GraphicsContext context;
    public Canvas canvas;
    private Canvas generateCanvas;
    private int[][] boardArray;
    private int[][] generateBoard;
    private String currentTime;
    private int counter;

    // Таймер для подсчета времени.
    Timer time = new Timer(0, 0, 0);
    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
        time.calculateTime();
        try {
            currentTime = time.getTime();
            timer.setText(currentTime);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }));


    /**
     * Рисует поле 9x9 на основном поле.
     */
    public void drawCellsInMainBoard() {
        for (int row = 0; row < 9; ++row) {
            for (int column = 0; column < 9; ++column) {
                double positionY = row * 50 + 2;
                double positionX = column * 50 + 2;
                int width = 46;
                context.setFill(Color.WHITE);
                context.fillRect(positionX, positionY, width, width);
            }
        }
    }


    /**
     * Закрашивание квадрата в выбранной позиции.
     *
     * @param row    номер строки.
     * @param column номер столбца
     */
    public void paintCell(int row, int column) {
        boardArray[row][column] = 1;
        context.setFill(Color.DARKSALMON);
        context.fillRect(row * 50 + 2, column * 50 + 2, 46, 46);
    }

    /**
     * Генерация фигуры и размещение ее справа от основного поля.
     */
    public void generateShape() {
        Shape shape = new Shape(515, 126, 150,
                150, ShapeType.generateShape());
        // Генерация фигуры.
        generateBoard = shape.generateShape();
        generateCanvas = shape.getShape();
        GraphicsContext generateContext = generateCanvas.getGraphicsContext2D();
        // Добавление фигуры на панель.
        pane.getChildren().add(generateCanvas);
        int counter = 0;
        double positionX = 0;
        double positionY = 0;
        for (int row = 0; row < 3; ++row) {
            for (int column = 0; column < 3; ++column) {
                // Если в ячейке 1,то клетка закрашивается.
                if (generateBoard[row][column] == 1) {
                    double positionGenerateY = row * 50 + 2;
                    double positionGenerateX = column * 50 + 2;
                    // Получение координат только первого закрашенного квадрата.
                    if (counter == 0) {
                        positionY = positionGenerateY;
                        positionX = positionGenerateX;
                    }
                    int width = 46;
                    generateContext.setFill(Color.DARKSALMON);
                    generateContext.fillRect(positionGenerateX,
                            positionGenerateY, width, width);
                    ++counter;
                }
            }
        }
        double finalPosition_x = positionX;
        double finalPosition_y = positionY;
        // Обработка ивента drag&drop
        generateCanvas.setOnMouseDragged(event -> dragged
                (event, finalPosition_x, finalPosition_y));
        generateCanvas.setOnMouseReleased(this::released);
    }


    /**
     * Закрытие приложения
     */
    public void exit() {
        javafx.application.Platform.exit();
    }

    /**
     * Отображение диалогового окна с информацией по игре.
     */
    public void finishGameDialog() {
        // Остановка таймера.
        timeline.stop();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Информация");
        alert.setHeaderText("Игра завершена! Выберите продолжение");
        alert.setContentText("Choose your option.");
        alert.setContentText("Время, проведенное в игре: " + currentTime +
                "\nКоличество сделанных ходов: " + counter);
        ButtonType buttonNewGame = new ButtonType("Новая игра");
        ButtonType buttonExit = new ButtonType("Выход из игры");
        alert.getButtonTypes().setAll(buttonNewGame, buttonExit);
        Optional<ButtonType> result = alert.showAndWait();
        // Проверка на выбор кнопки.
        if (result.isPresent()) {
            if (result.get() == buttonNewGame) {
                time = new Timer(0, 0, 0);
                timeline.play();
                startGame();
                pane.getChildren().remove(generateCanvas);
                generateShape();
            } else if (result.get() == buttonExit) {
                exit();
            }
        }
    }


    /**
     * Отображение справки для пользователя.
     */
    public void helpDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Информация");
        alert.setHeaderText("");
        alert.setContentText("""
                Игра осуществляется на поле 9x9 клеток.
                Ход игрока заключается, в том, что игрок должен расположить сгенерированную\s
                фигуру в свободное место, так чтобы фигура не\s
                пересекалась с уже расположенными фигурами на поле. Фигуры, которые игрок пытается расположить
                за пределами поля или наложить на другие фигуры, возвращаются в исходное положение.
                Фигуры переворачивать нельзя. А также нельзя изменять положение уже расположенных на поле фигур.
                При нажатии на кнопку "Завершить игру" игрок должен получить сообщение с информацией о том,
                сколько игрок сделал ходов в игре и сколько времени заняла игра
                (время игры начинается с открытия поля и
                заканчивается нажатием на кнопку “Завершить игру”).""");
        ButtonType buttonClose = new ButtonType("Закрыть");
        alert.getButtonTypes().setAll(buttonClose);
        alert.showAndWait();
    }


    /**
     * Инициализация приложения.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            startGame();
            generateShape();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(e.getMessage());
            ButtonType buttonOk = new ButtonType("Выход из игры");
            alert.getButtonTypes().setAll(buttonOk);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent()) {
                if (result.get() == buttonOk) {
                    time = new Timer(0, 0, 0);
                    timeline.play();
                    startGame();
                    pane.getChildren().remove(generateCanvas);
                    generateShape();
                }
            }
        }
    }

    /**
     * Обработка события перемещения объекта.
     *
     * @param event               Ивент
     * @param position_x_generate Позиция по оси x
     * @param position_y_generate Позиция по оси y
     */
    private void dragged(MouseEvent event, double position_x_generate, double position_y_generate) {
        double x = event.getX();
        double y = event.getY();
        // Изменение положения полотна, на котором нарисована фигура.
        generateCanvas.setLayoutX(generateCanvas.getLayoutX() - position_x_generate + x);
        generateCanvas.setLayoutY(generateCanvas.getLayoutY() - position_y_generate + y);
    }

    /**
     * Обработка события, при котором пользователь прерывает перемещение и отпускает объект.
     *
     * @param event Ивент
     */
    private void released(MouseEvent event) {
        double generateX = generateCanvas.getLayoutX();
        double generateY = generateCanvas.getLayoutY();
        // Проверка на размещение фигуры.
        if (!setShapeOnBoard(generateX, generateY)) return;
        for (int row = 0; row < 3; ++row) {
            for (int column = 0; column < 3; ++column) {
                // Если клетка закрашена, то на основном борде закрашивается клетка
                // с теми же координатами.
                if (generateBoard[row][column] == 1) {
                    double position_x = column * 50 + 2 + generateX;
                    double position_y = row * 50 + 2 + generateY;
                    int row_main = (int) ((position_x + 25) / 50);
                    int column_main = (int) ((position_y + 25) / 50);
                    paintCell(row_main, column_main);
                }
            }
        }
        // Увеличение количества ходов.
        ++counter;
        // Удаление фигуры с панели.
        pane.getChildren().remove(generateCanvas);
        // Генерация новой фигуры.
        generateShape();
    }

    /**
     * @param generateX Координаты по оси x
     * @param generateY Координаты по оси y
     * @return Устанавливаем фигуру или нет.
     */
    private boolean setShapeOnBoard(double generateX, double generateY) {
        for (int row = 0; row < 3; ++row) {
            for (int column = 0; column < 3; ++column) {
                if (generateBoard[row][column] == 1) {
                    double positionY = row * 50 + 2 + generateY;
                    double positionX = column * 50 + 2 + generateX;
                    // Проверка на выход за границы основного борда.
                    if (positionX < -24 || positionX > 424 || positionY < -24 || positionY > 424) {
                        generateCanvas.setLayoutX(515);
                        generateCanvas.setLayoutY(126);
                        return false;
                    }
                    int row_main = (int) ((positionX + 25) / 50);
                    int column_main = (int) ((positionY + 25) / 50);
                    // Проверка на наложение фигур.
                    if (boardArray[row_main][column_main] == 1) {
                        generateCanvas.setLayoutX(515);
                        generateCanvas.setLayoutY(126);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Начало игры и инициализация объектов.
     */
    private void startGame() {
        counter = 0;
        boardArray = Board.getMainBoard();
        generateBoard = Board.getGenerateShape();
        context = canvas.getGraphicsContext2D();
        drawCellsInMainBoard();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}
