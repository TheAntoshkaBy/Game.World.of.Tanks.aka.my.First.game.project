package Game;


import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.lang.reflect.Array;

public class MyElements extends Pane
{

    private static final int columns  = 3;//кол-во столбцов в спрайтовой картинке
    public static final int count = 3;//количество картинок
    public static final int offsetX = 0;//смещение по картике
    public static final int offsetY = 0;
    public  int width = 87;//ширина картинки
    public  int height = 87;//длинна картинки
    public static final int widthScreen = 1470;
    public static final int heightScreen = 765;
    private String side;
    public int LifeOfMyTank = 3;
    public boolean life = true;
    String meaning;

    ImageView imageView;

    SpriteAnimation animation;
    Image piyImg = new Image(getClass().getResourceAsStream("Wall.1.1.png"));
    ImageView piy = new ImageView(piyImg);

    public  ImageView getIMG()
    {
        return imageView;
    }
    public MyElements(ImageView imageView,double x, double y, String handle)
    {
        this.imageView = imageView;
        this.imageView.setViewport(new Rectangle2D(offsetX,offsetY,width,height));
        animation = new SpriteAnimation(imageView, Duration.millis(500),count,columns,offsetX,offsetY,width,height);
        getChildren().addAll(imageView);// Добавляем объект на экран благодаря наследованию от Pane
        piy.setViewport(new Rectangle2D(offsetX,offsetY,10,10));
        this.setTranslateX(x);
        this.setTranslateY(y);
        this.meaning = handle;

    }

    public MyElements(ImageView imageView,double x, double y,int widthPic,int heightPic)
    {

        this.imageView = imageView;
        this.imageView.setViewport(new Rectangle2D(offsetX,offsetY,width,height));
        this.width = widthPic;
        this.height = heightPic;
        animation = new SpriteAnimation(imageView, Duration.millis(500),count,columns,offsetX,offsetY,width,height);
        getChildren().addAll(imageView);// Добавляем объект на экран благодаря наследованию от Pane
        this.setTranslateX(x);
        this.setTranslateY(y);

    }
    public void setEl(double x, double y)
    {
        this.setTranslateX(x);
        this.setTranslateY(y);
    }
    public boolean Gran(double x, double y)
    {
        for(int i=  0;i<GameDisplay.widthTank;i++)
        {
          if(this.getTranslateX()+i == x && this.getTranslateY()+i == y )
              return true;
        }
        return false;

    }
    public void  moveX(int x)
    {
        int pogreshnost = 10;
      boolean movingRight = x>0;
      for(int i = 0;i<Math.abs(x);i++)
      {

         /**Check for Block's*/
          for(Node platform : GameDisplay.platforms)
          {
              if(this.getBoundsInParent().intersects(platform.getBoundsInParent())){
                  if(movingRight){
                      if(this.getTranslateX()+GameDisplay.widthTank == platform.getTranslateX()){
                          this.setTranslateX(this.getTranslateX()- 1);
                          return;
                      }
                  }else{
                      if(this.getTranslateX()  ==platform.getTranslateX()+GameDisplay.blockSize)
                      {
                          this.setTranslateX(this.getTranslateX()+1);
                          return;
                      }
                  }
              }
          }

          /**End Check Blocks*/
          /**Start check bots*/

          for(Node bot : GameDisplay.bots)
          {

              if (this.getBoundsInParent().intersects(bot.getBoundsInParent()))
              {

                  if (movingRight)
                  {
                      if (this.getTranslateX()+GameDisplay.widthTank - pogreshnost == bot.getTranslateX())
                      {
                          System.out.print("Stop11111!");
                          this.setTranslateX(this.getTranslateX() - 1);
                          return;
                      }
                  } else {
                      if (this.getTranslateX()+ pogreshnost == bot.getTranslateX() + GameDisplay.widthTank)
                      {
                          this.setTranslateX(this.getTranslateX() + 1);
                          return;
                      }
                  }
              }
          }
          this.setTranslateX(this.getTranslateX()+(movingRight ? 1 : -1));

      }
   }
    public void moveX(int x, MyElements element)
    {
        int pogreshnost = 10;
        boolean movingRight = x>0;
        for(int i = 0;i<Math.abs(x);i++)
        {

            /**Check for Block's*/
            for(Node platform : GameDisplay.platforms)
            {
                if(this.getBoundsInParent().intersects(platform.getBoundsInParent())){
                    if(movingRight){
                        if(this.getTranslateX()+GameDisplay.widthTank == platform.getTranslateX()){
                            this.setTranslateX(this.getTranslateX()- 1);
                            return;
                        }
                    }else{
                        if(this.getTranslateX()  ==platform.getTranslateX()+GameDisplay.blockSize)
                        {
                            this.setTranslateX(this.getTranslateX()+1);
                            return;
                        }
                    }
                }
            }

            /**End Check Blocks*/
            /**Start check bots*/

            for(Node bot : GameDisplay.bots)
            {

                if (this.getBoundsInParent().intersects(bot.getBoundsInParent()))
                {

                    if (movingRight)
                    {
                        if (this.getTranslateX()+GameDisplay.widthTank - pogreshnost == bot.getTranslateX())
                        {
                            System.out.print("Stop11111!");
                            this.setTranslateX(this.getTranslateX() - 1);
                            return;
                        }
                    } else {
                        if (this.getTranslateX()+ pogreshnost == bot.getTranslateX() + GameDisplay.widthTank)
                        {
                            this.setTranslateX(this.getTranslateX() + 1);
                            return;
                        }
                    }
                }
            }
            /**End check of Bots*/
            /**Start check Element*/
            if(this.getBoundsInParent().intersects(element.getBoundsInParent()))
            {
                if (movingRight) {

                    if(this.getTranslateX()+GameDisplay.widthTank - pogreshnost == element.getTranslateX()){
                        this.setTranslateX(this.getTranslateX()- 1);
                        //  GameDisplay.gameRoot.getChildren().remove(this);
                        return;
                    }

                }else{
                    if(this.getTranslateX() + pogreshnost==element.getTranslateX()+GameDisplay.widthTank ){
                        this.setTranslateX(this.getTranslateX()+1);
                        return;
                    }
                }
            }
            /**End check of element*/
            this.setTranslateX(this.getTranslateX() + (movingRight ? 1 : -1));
        }







    }



    public void moveY(int y, MyElements element)
    {
        int greh = 10;
        boolean movingDown = y > 0;
        for (int i = 0; i < Math.abs(y); i++) {

            /**Start check platforms*/
            for (Node platform : GameDisplay.platforms) {
                if (this.getBoundsInParent().intersects(platform.getBoundsInParent())) {
                    if (movingDown) {
                        if (this.getTranslateY() + GameDisplay.widthTank == platform.getTranslateY()) {
                            this.setTranslateY(this.getTranslateY() - 1);
                            return;
                        }

                    } else {
                        if (this.getTranslateY() == platform.getTranslateY() + GameDisplay.blockSize) {
                            this.setTranslateY(this.getTranslateY() + 1);
                            return;
                        }
                    }

                }
            }
            /**End of check platforms*/
            /**Start check bots*/

            for(Node bot : GameDisplay.bots)
            {

                if (this.getBoundsInParent().intersects(bot.getBoundsInParent()))
                {

                    if (movingDown)
                    {
                        if (this.getTranslateY()+GameDisplay.widthTank - greh == bot.getTranslateY())
                        {
                            this.setTranslateY(this.getTranslateY() - 1);
                            return;
                        }
                    } else {
                        if (this.getTranslateY()+ greh == bot.getTranslateY() + GameDisplay.widthTank)
                        {
                            this.setTranslateY(this.getTranslateY() + 1);
                            return;
                        }
                    }
                }
            }
            /**End of check bots*/
            /**Start check Elements*/
            if (this.getBoundsInParent().intersects(element.getBoundsInParent()))
            {
                if (movingDown)
                {
                    if (this.getTranslateY() + GameDisplay.widthTank - greh == element.getTranslateY())
                    {
                        this.setTranslateY(this.getTranslateY() - 1);
                        return;
                    }
                }

                else
                {
                    if (this.getTranslateY() + greh == element.getTranslateY() + GameDisplay.widthTank)
                    {
                        this.setTranslateY(this.getTranslateY() + 1);
                        return;
                    }
                }
            }

            this.setTranslateY(this.getTranslateY() + (movingDown ? 1 : -1));
        }
    }
    public void  moveY(int y)
    {
        int greh = 10;
        boolean movingDown = y > 0;
        for (int i = 0; i < Math.abs(y); i++) {
            for (Node platform : GameDisplay.platforms) {
                if (this.getBoundsInParent().intersects(platform.getBoundsInParent())) {
                    if (movingDown) {
                        if (this.getTranslateY() + GameDisplay.widthTank == platform.getTranslateY()) {
                            this.setTranslateY(this.getTranslateY() - 1);
                            return;
                        }

                    } else {
                        if (this.getTranslateY() == platform.getTranslateY() + GameDisplay.blockSize) {
                            this.setTranslateY(this.getTranslateY() + 1);
                            return;
                        }
                    }

                }


            }

            for(Node bot : GameDisplay.bots)
            {

                if (this.getBoundsInParent().intersects(bot.getBoundsInParent()))
                {

                    if (movingDown)
                    {
                        if (this.getTranslateY()+GameDisplay.widthTank - greh == bot.getTranslateY())
                        {
                            this.setTranslateY(this.getTranslateY() - 1);
                            return;
                        }
                    } else {
                        if (this.getTranslateY()+ greh == bot.getTranslateY() + GameDisplay.widthTank)
                        {
                            this.setTranslateY(this.getTranslateY() + 1);
                            return;
                        }
                    }
                }
            }


            this.setTranslateY(this.getTranslateY() + (movingDown ? 1 : -1));
        }
    }



    public void PiyPiy(String sideOfTank)
    {

        switch (sideOfTank)
        {
            case "UP":
            {
              piy.setTranslateX(this.getTranslateX() + 43);
              piy.setTranslateY(this.getTranslateY());
            }



        }
        getChildren().addAll(piy);
    }
    public void movePiyPiy(int speed)
    {

    }

}
