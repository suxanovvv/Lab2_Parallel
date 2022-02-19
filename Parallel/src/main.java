import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import static javafx.application.Application.launch;

public class main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Pane p = new Pane();


        Task<Void> first = new Task<Void>() {
            @Override
            protected Void call() {
                Platform.runLater(() -> {
                    Rectangle rect = new Rectangle(10, 10, 180, 40);
                    rect.setFill(Color.BLUE);
                    p.getChildren().add(rect);

                    Text t = new Text();
                    t = Square(rect, 75, 80);
                    p.getChildren().add(t);

                });
                return null;
            }

        };



        Task<Void> second = new Task<Void>() {
            @Override
            protected Void call() {
                Platform.runLater(() -> {
                    Rectangle rect = new Rectangle(300, 10, 130, 160);
                    rect.setFill(Color.ORANGE);
                    p.getChildren().add(rect);

                    Text t = new Text();
                    t = Square(rect, 345, 200);
                    p.getChildren().add(t);
                });
                return null;
            }


        };


        Task<Void> third = new Task<Void>() {
            @Override
            protected Void call() {
                Platform.runLater(() -> {
                    Rectangle rect = new Rectangle(550, 10, 180, 230);
                    rect.setFill(Color.RED);
                    p.getChildren().add(rect);

                    Text t = new Text();
                    t = Square(rect, 630, 270);
                    p.getChildren().add(t);

                });
                return null;
            }
        };

        Task<Void> general = new Task<Void>() {
            @Override
            protected Void call() throws Exception{
                Thread.sleep(99);
                Platform.runLater(() -> {

                    double generalsqueare = 0;

                    for (int i = 1; i < p.getChildren().size(); i+=2 ) {

                        generalsqueare += Double.parseDouble(((Text)p.getChildren().get(i)).getText());

                    }
                    System.out.println(generalsqueare);
                    Text text = new Text(250, 340,
                            CreateDisplay("Загальна площа усіх прямокутників: " + Double.toString(generalsqueare),
                                    Color.BLUE));
                    p.getChildren().add(text);

                });
                return null;
            }
        };


        Thread thread_1 = new Thread(first);
        Thread thread_2 = new Thread(second);
        Thread thread_3 = new Thread(third);
        Thread thread_4 = new Thread(general);
        thread_1.start();
        thread_2.start();
        thread_3.start();
        thread_4.start();

        Scene sn = new Scene(p, 745, 400);
        primaryStage.setScene(sn);
        primaryStage.show();

        primaryStage.setOnCloseRequest(windowEvent -> {
            first.cancel();
            second.cancel();
            third.cancel();
            general.cancel();
        });

    };


    String CreateDisplay(String meaning, Color color){
        Text t = new Text();
        t.setFont(Font.font("Arial", FontWeight.BOLD, 48));
        t.setFill(color.BLACK);
        t.getText();
        return meaning;
    }


    public Text Square(Rectangle rectangle, double x, double y){
        double square = 0;

        square = rectangle.getWidth()* rectangle.getHeight();
        String str = Double.toString(square);
        Text text = new Text(x, y, CreateDisplay(str, Color.BLACK));

        return text;
    }


        public static void main (String[]args){
            Application.launch(args);
        }

}
