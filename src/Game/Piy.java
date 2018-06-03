package Game;

import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public  class Piy extends Pane implements Runnable {

    private static final int columns  = 3;//кол-во столбцов в спрайтовой картинке
    public static final int count = 3;//количество картинок
    public static final int offsetX = 0;//смещение по картике
    public static final int offsetY = 0;
    public  int width = 10;//ширина картинки
    public  int height = 10;//длинна картинки
    MyElements element;

    private String side;
    private String system;


    SpriteAnimation animation;
    Image piyImg = new Image(getClass().getResourceAsStream("Wall.1.1.png"));
    ImageView piy = new ImageView(piyImg);

    public String GetMeaning()
    {
        return element.meaning;
    }

    public Piy(MyElements tank, String strSide)
    {
        this.piy.setViewport(new Rectangle2D(offsetX,offsetY,width,height));
        animation = new SpriteAnimation(piy, Duration.millis(500),count,columns,offsetX,offsetY,width,height);
        element = tank;

        if(strSide == "UP"){
            this.setTranslateX(tank.getTranslateX()+40);
            this.setTranslateY(tank.getTranslateY() - 10);
        }else if(strSide == "DOWN")
        {
            this.setTranslateX(tank.getTranslateX()+40);
            this.setTranslateY(tank.getTranslateY() + 97);
        }else if(strSide == "RIGHT")
        {
            this.setTranslateX(tank.getTranslateX()+97);
            this.setTranslateY(tank.getTranslateY() + 40);
        }else if(strSide == "LEFT")
        {
            this.setTranslateX(tank.getTranslateX() - 10);
            this.setTranslateY(tank.getTranslateY() + 40);
        }

        system = "TANK";

        side = strSide;
        getChildren().addAll(piy);// Добавляем объект на экран благодаря наследованию от Pane
        GameDisplay.piys.add(this);
        GameDisplay.gameRoot.getChildren().add(this);
    }
    public Piy(BotTank tank, String strSide)
    {
        this.piy.setViewport(new Rectangle2D(offsetX,offsetY,width,height));
        animation = new SpriteAnimation(piy, Duration.millis(500),count,columns,offsetX,offsetY,width,height);

        if(strSide == "UP"){
            this.setTranslateX(tank.getTranslateX()+40);
            this.setTranslateY(tank.getTranslateY() - 10);
        }else if(strSide == "DOWN")
        {
            this.setTranslateX(tank.getTranslateX()+40);
            this.setTranslateY(tank.getTranslateY() + 97);
        }else if(strSide == "RIGHT")
        {
            this.setTranslateX(tank.getTranslateX()+97);
            this.setTranslateY(tank.getTranslateY() + 40);
        }else if(strSide == "LEFT")
        {
            this.setTranslateX(tank.getTranslateX() - 10);
            this.setTranslateY(tank.getTranslateY() + 40);
        }

        system = "BOT";
        side = strSide;
        getChildren().addAll(piy);// Добавляем объект на экран благодаря наследованию от Pane
        GameDisplay.piys.add(this);
        GameDisplay.gameRoot.getChildren().add(this);
    }

    public void  moveX(int x)
    {
            int pogreshnost = 10;
            boolean movingRight = x > 0;
            for (int i = 0; i < Math.abs(x); i++) {

                for (Node platform : GameDisplay.platforms) {
                    if (this.getBoundsInParent().intersects(platform.getBoundsInParent()))
                    {
                        if (movingRight)
                        {

                            if (this.getTranslateX()-10 == platform.getTranslateX())
                            {

                                this.setTranslateY(this.getTranslateY() - 1);
                                GameDisplay.piys.remove(this);
                                getChildren().remove(piy);
                                GameDisplay.gameRoot.getChildren().remove(this);
                                return;
                            }
                        }else{
                            if(this.getTranslateX()+10  == platform.getTranslateX()+GameDisplay.blockSize)
                            {
                                GameDisplay.piys.remove(this);
                                getChildren().remove(piy);
                                GameDisplay.gameRoot.getChildren().remove(this);
                                return;
                            }
                        }
                    }
                }


                for(/*Node bot : GameDisplay.bots*/ int q = 0;q<GameDisplay.bots.size();q++)
                {

                    if (this.getBoundsInParent().intersects(GameDisplay.bots.get(q).getBoundsInParent()))
                    {

                        if (movingRight)
                        {
                            if (this.getTranslateX() - 10 == GameDisplay.bots.get(q).getTranslateX() + pogreshnost)
                            {

                                GameDisplay.piys.remove(this);
                                getChildren().remove(piy);
                                GameDisplay.gameRoot.getChildren().remove(this);
                                GameDisplay.bots.get(q).dieBaby();
                                GameDisplay.countOfTanks--;
                                return;
                            }
                        }else{
                            if (this.getTranslateX()+ pogreshnost +10 == GameDisplay.bots.get(q).getTranslateX() + GameDisplay.widthTank)
                            {
                                GameDisplay.piys.remove(this);
                                getChildren().remove(piy);
                                GameDisplay.gameRoot.getChildren().remove(this);
                                GameDisplay.bots.get(q).dieBaby();
                                GameDisplay.countOfTanks--;
                                return;
                            }
                        }
                    }
                }
                this.setTranslateX(this.getTranslateX() + (movingRight ? 1 : -1));
            }
        }

    public void  moveXbt(int x,MyElements element)
    {
        int pogreshnost = 10;
        boolean movingRight = x > 0;
        for (int i = 0; i < Math.abs(x); i++) {

            for (Node platform : GameDisplay.platforms) {
                if (this.getBoundsInParent().intersects(platform.getBoundsInParent()))
                {
                    if (movingRight)
                    {

                        if (this.getTranslateX()-10 == platform.getTranslateX())
                        {

                            this.setTranslateY(this.getTranslateY() - 1);
                            GameDisplay.piys.remove(this);
                            getChildren().remove(piy);
                            GameDisplay.gameRoot.getChildren().remove(this);

                            return;
                        }
                    }else{
                        if(this.getTranslateX()+10  == platform.getTranslateX()+GameDisplay.blockSize)
                        {
                            GameDisplay.piys.remove(this);
                            getChildren().remove(piy);
                            GameDisplay.gameRoot.getChildren().remove(this);
                            return;
                        }
                    }
                }
            }


                if (this.getBoundsInParent().intersects(element.getBoundsInParent()))
                {

                    if (movingRight)
                    {
                        if (this.getTranslateX() - 10 == element.getTranslateX() + pogreshnost)
                        {
                            GameDisplay.piys.remove(this);
                            getChildren().remove(piy);
                            GameDisplay.gameRoot.getChildren().remove(this);
                            element.LifeOfMyTank --;
                            if(element.LifeOfMyTank<= 0 ) {
                                getChildren().remove(element.getIMG());
                                GameDisplay.gameRoot.getChildren().remove(element);
                                element.life =false;
                                return;
                            }
                        }
                    }else{
                        if (this.getTranslateX()+ pogreshnost +10 ==element.getTranslateX() + GameDisplay.widthTank)
                        {
                            GameDisplay.piys.remove(this);
                            getChildren().remove(piy);
                            GameDisplay.gameRoot.getChildren().remove(this);
                            element.LifeOfMyTank --;
                            if(element.LifeOfMyTank<= 0 ) {
                                getChildren().remove(element.getIMG());
                                GameDisplay.gameRoot.getChildren().remove(element);
                                element.life =false;
                                return;
                            }
                        }
                    }
                }

            this.setTranslateX(this.getTranslateX() + (movingRight ? 1 : -1));
        }
    }

    public void  moveYbt(int x,MyElements element)
    {
        int pogreshnost = 10;
        boolean movingDown = x > 0;
        for (int i = 0; i < Math.abs(x); i++) {

            for (Node platform : GameDisplay.platforms) {
                if (this.getBoundsInParent().intersects(platform.getBoundsInParent()))
                {
                    if (movingDown)
                    {

                        if (this.getTranslateY()-10 == platform.getTranslateY())
                        {


                            GameDisplay.piys.remove(this);
                            getChildren().remove(piy);
                            GameDisplay.gameRoot.getChildren().remove(this);

                            return;
                        }
                    }else{
                        if(this.getTranslateY()+10  == platform.getTranslateY()+GameDisplay.blockSize)
                        {
                            GameDisplay.piys.remove(this);
                            getChildren().remove(piy);
                            GameDisplay.gameRoot.getChildren().remove(this);
                            return;
                        }
                    }
                }
            }


            if (this.getBoundsInParent().intersects(element.getBoundsInParent()))
            {

                if (movingDown)
                {
                    if (this.getTranslateY() - 10 == element.getTranslateY() + pogreshnost)
                    {
                        GameDisplay.piys.remove(this);
                        getChildren().remove(piy);
                        GameDisplay.gameRoot.getChildren().remove(this);
                        element.LifeOfMyTank --;
                        if(element.LifeOfMyTank<= 0 ) {
                            getChildren().remove(element.getIMG());
                            GameDisplay.gameRoot.getChildren().remove(element);
                            element.life =false;
                            return;
                        }
                    }
                }else{
                    if (this.getTranslateY()+ pogreshnost +10 ==element.getTranslateY() + GameDisplay.widthTank)
                    {
                        GameDisplay.piys.remove(this);
                        getChildren().remove(piy);
                        GameDisplay.gameRoot.getChildren().remove(this);
                        element.LifeOfMyTank --;
                        if(element.LifeOfMyTank<= 0 ) {
                            getChildren().remove(element.getIMG());
                            GameDisplay.gameRoot.getChildren().remove(element);
                            element.life =false;
                            return;
                        }
                    }
                }
            }

            this.setTranslateY(this.getTranslateY() + (movingDown ? 1 : -1));
        }
    }

    public void moveY(int x)
    {
            int pogreshnost = 10;
            boolean movingDown = x > 0;
            for (int i = 0; i < Math.abs(x); i++) {

                for (Node platform : GameDisplay.platforms) {
                    if (this.getBoundsInParent().intersects(platform.getBoundsInParent()))
                    {
                        if (movingDown)
                        {

                            if (this.getTranslateY()-10 == platform.getTranslateY())
                            {
                                GameDisplay.piys.remove(this);
                                getChildren().remove(piy);
                                GameDisplay.gameRoot.getChildren().remove(this);
                                return;
                            }
                        }else{
                            if(this.getTranslateY()+10  == platform.getTranslateY()+GameDisplay.blockSize)
                            {
                                GameDisplay.piys.remove(this);
                                getChildren().remove(piy);
                                GameDisplay.gameRoot.getChildren().remove(this);
                                return;
                            }
                        }
                    }
                }

                for(/*Node bot : GameDisplay.bots*/int q = 0;q<GameDisplay.bots.size();q++)
                {

                    if (this.getBoundsInParent().intersects(GameDisplay.bots.get(q).getBoundsInParent()))
                    {

                        if (movingDown)
                        {
                            if (this.getTranslateY() - 10 == GameDisplay.bots.get(q).getTranslateY() + pogreshnost)
                            {
                                GameDisplay.piys.remove(this);
                                getChildren().remove(piy);
                                GameDisplay.gameRoot.getChildren().remove(this);
                                GameDisplay.bots.get(q).dieBaby();
                                GameDisplay.countOfTanks--;
                                return;
                            }
                        }else{
                            if (this.getTranslateY()+ pogreshnost +10 == GameDisplay.bots.get(q).getTranslateY() + GameDisplay.widthTank)
                            {
                                GameDisplay.piys.remove(this);
                                getChildren().remove(piy);
                                GameDisplay.gameRoot.getChildren().remove(this);
                                GameDisplay.bots.get(q).dieBaby();
                                GameDisplay.countOfTanks--;
                                return;
                            }
                        }
                    }
                }
                this.setTranslateY(this.getTranslateY() + (movingDown ? 1 : -1));
            }
        }
    public String  getSide()
        {
            return side;
        }
    @Override
    public void run()
    {

        if(this.system == "TANK") {
            switch (this.getSide()) {
                case "UP": {
                    this.moveY(-10 * 2);
                    break;
                }
                case "DOWN": {
                    this.moveY(10 * 2);
                    break;
                }
                case "RIGHT": {
                    this.moveX(10 * 2);
                    break;
                }
                case "LEFT": {
                    this.moveX(-10 * 2);
                }
            }

        }
    }
}
