package Game;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class BotTank extends Pane {

    private static final int columns  = 3;//кол-во столбцов в спрайтовой картинке
    public static final int count = 3;//количество картинок
    public static final int offsetX = 0;//смещение по картике
    public static final int offsetY = 0;
    public  int width = 87;//ширина картинки
    public  int height = 87;//длинна картинки
    public static final int pogreshnost = 10;

    boolean Life = true;

    Image bot = new Image(getClass().getResourceAsStream("2.3.6.png"/*"11.png"*/));
    ImageView imageView = new ImageView(bot);


    SpriteAnimation animation;

    BotTank(double firstX, double firstY)
    {


        this.imageView.setViewport(new Rectangle2D(offsetX,offsetY,width,height));
        animation = new SpriteAnimation(imageView, Duration.millis(500),count,columns,offsetX,offsetY,width,height);

        this.setTranslateX(firstX);
        this.setTranslateY(firstY);
        getChildren().addAll(imageView);// Добавляем объект на экран благодаря наследованию от Pane
        GameDisplay.bots.add(this);
        GameDisplay.gameRoot.getChildren().add(this);


    }

    public void dieBaby()
    {
        Life = false;
        getChildren().remove(imageView);
        GameDisplay.bots.remove(this);
        GameDisplay.gameRoot.getChildren().remove(this);
    }
    public void  moveX(int x, MyElements tank)
    {
        boolean movingRight = x > 0;

        for (int i = 0; i < Math.abs(x); i++)
        {
            if(this.getBoundsInParent().intersects(tank.getBoundsInParent())) {
                if (movingRight) {

                    if(this.getTranslateX()+GameDisplay.widthTank - pogreshnost == tank.getTranslateX()){
                        this.setTranslateX(this.getTranslateX()- 1);
                      //  GameDisplay.gameRoot.getChildren().remove(this);
                        return;
                    }

                }else{
                    if(this.getTranslateX() + pogreshnost==tank.getTranslateX()+GameDisplay.widthTank ){
                        this.setTranslateX(this.getTranslateX()+1);
                        return;
                    }
                }
            }
            this.setTranslateX(this.getTranslateX() + (movingRight ? 1 : -1));
        }

    }

    public void moveY(int y,MyElements tank)
    {
        boolean movingDown = y > 0;

        for (int i = 0; i < Math.abs(y); i++)
        {
            if (this.getBoundsInParent().intersects(tank.getBoundsInParent()))
            {
                if (movingDown)
                {
                    if (this.getTranslateY() + GameDisplay.widthTank - pogreshnost == tank.getTranslateY())
                    {
                        this.setTranslateY(this.getTranslateY() - 1);
                        return;
                    }
                }

                else
                    {
                        if (this.getTranslateY() + pogreshnost == tank.getTranslateY() + GameDisplay.widthTank)
                        {
                            this.setTranslateY(this.getTranslateY() + 1);
                            return;
                        }
                    }
            }
            this.setTranslateY(this.getTranslateY() + (movingDown ? 1 : -1));
        }

    }









}





