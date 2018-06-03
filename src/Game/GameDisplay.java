package Game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class GameDisplay extends Application
{

    public static int levelNumber = 0;
    public static boolean Repeat = false;
    public static boolean Multi = false;
    public static boolean NewGame = false;
    public static int countOfTanks = 2;
    private int countOfTaksrepit;

    public final static int widthTank  = 87;
    private final int heightTank = 87;
    public static final int blockSize = 55;
    Stage handlstage;

    Stage endGame;
    AnimationTimer timer;
    @FXML
    Button exit;
    private final int speed = 10;

    Image backGroundImg = new Image(getClass().getResourceAsStream("back.png"));
    Image tank = new Image(getClass().getResourceAsStream("2.3.6.png"/*"11.png"*/));
    Image tank2 = new Image(getClass().getResourceAsStream("2.3.6.png"/*"11.png"*/));

    private HashMap<KeyCode,Boolean> keys = new HashMap<>();
    public static ArrayList<Blocks> platforms = new ArrayList<>();
    public static ArrayList<BotTank> bots = new ArrayList<>();
    public static ArrayList<Piy> piys = new ArrayList<>();
    public static ArrayList<Piy> botPiys = new ArrayList<>();
    public static ArrayList<KeyCode>keyCodes = new ArrayList<>();
    public static ArrayList<Integer>controllers_Сount = new ArrayList<>();//номера итераций где мы нажимали клавишу
    public static ArrayList<Piy>multiPiys = new ArrayList<>();



    static Pane gameRoot = new Pane();
    public Pane appRoot =new Pane();

    FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/Game_Style.fxml"));

    String sideOfTank = "HZ";
/**Create tanks*/
    ImageView imageView = new ImageView(tank);
    MyElements element = new MyElements(imageView,900,980,"HANDLE");
    ImageView imageView2 = new ImageView(tank2);
    MyElements element2 =  new MyElements(imageView2,800, 80, "Second");


    boolean side;
    BotTank bot;
    boolean side2;
    BotTank bot2;


    int levelWidth;
    int delay = 20;

    boolean Click = false;


    boolean getSide(int rightEndXorUpEndY,int leftEndXorDownEndY,BotTank botTank,char sideStr,boolean prev)
    {
        boolean tankSide = prev;

        if(sideStr == 'X')
        {
            if (botTank.getTranslateX() >= rightEndXorUpEndY)
            {
                tankSide = false;
                return tankSide;

            } else if (botTank.getTranslateX() <= leftEndXorDownEndY)
            {
                tankSide = true;
                return tankSide;
            }

        }else
        {
            if (botTank.getTranslateY() >= rightEndXorUpEndY)
            {
                tankSide = false;
                return tankSide;
            }

            if (botTank.getTranslateY() <= leftEndXorDownEndY)
            {
                tankSide = true;
                return tankSide;
            }
        }
        return tankSide;
    }



    public void setBotTankGo(boolean side,char sideStr, BotTank botTank,int counts)
    {

        if(sideStr == 'X')
        {


            if (side) {
                botTank.animation.setOffsetY(87);
                if(count1 > delay && botTank.Life) {
                    botPiys.add(new Piy(botTank, "RIGHT"));
                    //new Thread(botPiys.get(piys.size() - 1));
                    count1 = 0;
                }
                botTank.moveX(speed, element);//если наш движок остановился из-за танка, он вернет true, в итоге наш метод тоже вернет true


            } else {
                botTank.animation.setOffsetY(174);
                if(count1 > delay && botTank.Life) {
                    botPiys.add(new Piy(botTank, "LEFT"));
                    //new Thread(piys.get(piys.size() - 1));
                    count1 = 0;
                }
                botTank.moveX(-speed, element);



            }
        }else if(sideStr == 'Y'){

            if(side){
                botTank.animation.setOffsetY(0);
                if(count2 > delay && botTank.Life) {
                    botPiys.add(new Piy(botTank, "DOWN"));
                    //new Thread(piys.get(piys.size() - 1));
                    count2 = 0;
                }
                botTank.moveY(speed, element);//если наш движок остановился из-за танка, он вернет true, в итоге наш метод тоже вернет true


            } else{
                botTank.animation.setOffsetY(261);
                if(count2 > delay  && botTank.Life) {
                    botPiys.add(new Piy(botTank, "UP"));
                    //new Thread(piys.get(piys.size() - 1));
                    count2 = 0;
                }
                botTank.moveY(-speed, element);

            }

        }

    }

    private void InitContent(String mlt)
    {
        ImageView backGround = new ImageView(backGroundImg);
        backGround.setFitHeight(19 * blockSize);
        backGround.setFitWidth(36 * blockSize);

        levelWidth = Level.levels[levelNumber][0].length()*blockSize;//Длинна уровня
        for(int i = 0;i<Level.levels[levelNumber].length;i++){
            String line = Level.levels[levelNumber][i];
            for(int j= 0;j<line.length();j++){

                switch (line.charAt(j)){
                    case '0':
                        break;

                    case '1':
                        Blocks platform = new Blocks(Blocks.BlockType.PLATFORM,j*blockSize,i*blockSize);
                        break;
                }
            }
        }

        //gameRoot.getChildren().add(botTank);
        if(mlt != "MULTI") {
            side = true;
            bot = new BotTank(275, 600);
            side2 = true;
            bot2 = new BotTank(800, 80);
        }
        if(mlt == "MULTI")
        {
            gameRoot.getChildren().add(element2);
        }
        countOfTaksrepit = countOfTanks;
        gameRoot.getChildren().add(element);
        appRoot.getChildren().addAll(backGround,gameRoot);

    }


    private void initBots(int count)
    {
        side = getSide(600,100,bot,'Y',side);
        setBotTankGo(side,'Y',bot,count1);
        side2 = getSide(1200,500,bot2,'X',side2);
        count2++;
        setBotTankGo(side2,'X',bot2,count2);


    }

    int count = 0;
    int count1 = 0;
    int count2 = 0;


    public void EndScreen() throws IOException {

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/Game_Style.fxml"));
        Parent root = (Parent) loader.load();
        Game_Style_Controller controller = loader.getController();
        Scene scene = new Scene(root,700, 700);
        scene.getStylesheets().add(getClass().getResource("../Menu/Css/Game_Style.css").toExternalForm());

        stage.setTitle("Results");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setHeight(400);
        stage.setWidth(615);


        controller.setStage(stage);
        stage.show();

    }
    public void EndOfGame() throws IOException {
        for(int i = 0;i<botPiys.size();i++)
            gameRoot.getChildren().remove(botPiys.get(i));
        for(int i = 0;i<piys.size();i++)
            gameRoot.getChildren().remove(piys.get(i));
        gameRoot.getChildren().remove(bot);
        gameRoot.getChildren().remove(bot2);
        gameRoot.getChildren().remove(element);
        appRoot.getChildren().remove(gameRoot);
        timer.stop();
        botPiys.clear();
        piys.clear();
        bots.clear();
        EndScreen();
        count = 0;
        count1 = 0;
        count2 = 0;
        side =true;
        side2 =true;
        countOfTanks = countOfTaksrepit;
        handlstage.close();
    }

    static int HANDLE_GAME_COUNT = 0;//Количество итераций двух циклов, они равны



    private boolean isPressed(KeyCode key)
    {
        return keys.getOrDefault(key,false);
    }
    int controller_count = 0;//индекс клавиши
    int Repeat_Game_Count = 0;//Количество итераций репита == HANDLE_GAME_COUNT
    int index = 0;
    int indexForCount = 0;
    public void update() throws IOException {


        count++;
        count1++;
        count2++;


        initBots(count);

      /*  for (int i = 0; i < piys.size(); i++) {
            piys.get(i).run();
        }*/
        for (int i = 0; i < botPiys.size(); i++) {
            switch (botPiys.get(i).getSide()) {
                case "UP": {
                    botPiys.get(i).moveYbt(-speed * 2, element);
                    break;
                }
                case "DOWN": {
                    botPiys.get(i).moveYbt(speed * 2, element);
                    break;
                }
                case "RIGHT": {
                    botPiys.get(i).moveXbt(speed * 2, element);
                    break;
                }
                case "LEFT": {
                    botPiys.get(i).moveXbt(-speed * 2, element);
                }
            }
        }
        /**Set Bot Tank*/

        bot.animation.play();
        bot2.animation.play();
        for (int i = 0; i < piys.size(); i++) {
            piys.get(i).run();
        }

        /**Instruction for handle element*/
        if (!Repeat) {
            if (isPressed(KeyCode.UP)) {
                /*
                 * 1. Добавляем в список нажатую клавишу
                 * 2. Заносим номер итерации в список чисел
                 *
                 * */
                keyCodes.add(KeyCode.UP);//Добавляем в список клавишу
                controllers_Сount.add(HANDLE_GAME_COUNT);//Добавляем номер итерации в котором юзали клавишу
                System.out.println("UP COUNT = "+ HANDLE_GAME_COUNT);
                element.animation.play();
                element.animation.setOffsetY(261);
                element.moveY(-speed);
                sideOfTank = "UP";
                if (isPressed(KeyCode.SPACE)) {
                    keyCodes.add(KeyCode.SPACE);
                    if (count > delay) {
                        piys.add(new Piy(element, "UP"));
                        new Thread(piys.get(piys.size() - 1));
                        count = 0;

                    }
                }

            } else if (isPressed(KeyCode.DOWN)) {
                keyCodes.add(KeyCode.DOWN);//Добавляем в список клавишу
                controllers_Сount.add(HANDLE_GAME_COUNT);//Добавляем номер итерации в котором юзали клавишу
                element.animation.play();
                element.animation.setOffsetY(0);
                if ((element.getTranslateY()) < 950) {
                    element.moveY(speed);
                    sideOfTank = "DOWN";

                }
                if (isPressed(KeyCode.SPACE)) {
                    keyCodes.add(KeyCode.SPACE);

                    if (count > delay) {
                        piys.add(new Piy(element, "DOWN"));
                        new Thread(piys.get(piys.size() - 1));
                        count = 0;
                    }
                }

            } else if (isPressed(KeyCode.RIGHT)) {
                keyCodes.add(KeyCode.RIGHT);//Добавляем в список клавишу
                controllers_Сount.add(HANDLE_GAME_COUNT);//Добавляем номер итерации в котором юзали клавишу
                element.animation.play();
                element.animation.setOffsetY(87);
                element.moveX(speed);
                sideOfTank = "RIGHT";

                if (isPressed(KeyCode.SPACE)) {
                    keyCodes.add(KeyCode.SPACE);
                    if (count > delay) {
                        piys.add(new Piy(element, "RIGHT"));
                        new Thread(piys.get(piys.size() - 1));
                        count = 0;
                    }
                }

            } else if (isPressed(KeyCode.LEFT)) {
                keyCodes.add(KeyCode.LEFT);//Добавляем в список клавишу
                controllers_Сount.add(HANDLE_GAME_COUNT);//Добавляем номер итерации в котором юзали клавишу
                element.animation.play();
                element.animation.setOffsetY(174);
                element.moveX(-speed);
                sideOfTank = "LEFT";
                if (isPressed(KeyCode.SPACE)) {
                    keyCodes.add(KeyCode.SPACE);
                    if (count > delay) {
                        piys.add(new Piy(element, "LEFT"));
                        new Thread(piys.get(piys.size() - 1));
                        count = 0;

                    }
                }
            } else if (isPressed(KeyCode.SPACE)) {
                keyCodes.add(KeyCode.SPACE);//Добавляем в список клавишу
                controllers_Сount.add(HANDLE_GAME_COUNT);//Добавляем номер итерации в котором юзали клавишу
                if (count > 10) {
                    piys.add(new Piy(element, sideOfTank));
                    new Thread(piys.get(piys.size() - 1));
                    count = 0;
                }

            } else {
                element.animation.stop();
            }

            if (!element.life || countOfTanks == 0) EndOfGame();

            /**End instruction of handle element*/

            HANDLE_GAME_COUNT++;
        }
    }
    public void Repeat() throws IOException {



        count++;
        count1++;
        count2++;
        bot.animation.play();
        bot2.animation.play();

        initBots(count);

        for (int i = 0; i < piys.size(); i++) {
            piys.get(i).run();
        }
        for (int i = 0; i < botPiys.size(); i++) {
            switch (botPiys.get(i).getSide()) {
                case "UP": {
                    botPiys.get(i).moveYbt(-speed * 2, element);
                    break;
                }
                case "DOWN": {
                    botPiys.get(i).moveYbt(speed * 2, element);
                    break;
                }
                case "RIGHT": {
                    botPiys.get(i).moveXbt(speed * 2, element);
                    break;
                }
                case "LEFT": {
                    botPiys.get(i).moveXbt(-speed * 2, element);
                }
            }
        }
        System.out.println("index of pos = "+ controllers_Сount.size());
        System.out.println("index of key = " + keyCodes.size());
        if(index < keyCodes.size()) {
            if (Repeat_Game_Count == controllers_Сount.get(indexForCount)) {

                if (keyCodes.get(index) == KeyCode.UP) {
                    index++;//Т.к. у нас номер
                    indexForCount++;
                    element.animation.play();
                    element.animation.setOffsetY(261);
                    element.moveY(-speed);
                    sideOfTank = "UP";
                    System.out.println("MAAAAAAAAAAAAAAAYYYYYYYYYYBEEEEEEEEEEEEEE PIIIIIIIIIIIIIIIIIIIYYYYYYYYYYYYYYYYY!!!!!!!!!");
                    if(index<keyCodes.size())
                    if (keyCodes.get(index) == KeyCode.SPACE) {
                        System.out.println("PIIIIIIIIIIIIIIIIIIIIIYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY");
                        index++;
                        if (count > delay) {
                            piys.add(new Piy(element, "UP"));
                            new Thread(piys.get(piys.size() - 1));
                            count = 0;
                        }
                    }

                } else if (keyCodes.get(index) == KeyCode.DOWN) {
                    index++;
                    indexForCount++;
                    element.animation.play();
                    element.animation.setOffsetY(0);
                    if ((element.getTranslateY()) < 950) {
                        element.moveY(speed);
                        sideOfTank = "DOWN";
                    }
                    if(index<keyCodes.size())
                    if (keyCodes.get(index) == KeyCode.SPACE) {
                        index++;
                        if (count > delay) {
                            piys.add(new Piy(element, "DOWN"));
                            new Thread(piys.get(piys.size() - 1));
                            count = 0;
                        }
                    }

                } else if (keyCodes.get(index) == KeyCode.RIGHT) {
                    index++;
                    indexForCount++;
                    element.animation.play();
                    element.animation.setOffsetY(87);
                    element.moveX(speed);
                    sideOfTank = "RIGHT";
                    if(index<keyCodes.size())
                    if (keyCodes.get(index) == KeyCode.SPACE) {
                        index++;
                        if (count > delay) {
                            piys.add(new Piy(element, "RIGHT"));
                            new Thread(piys.get(piys.size() - 1));
                            count = 0;
                        }
                    }

                } else if (keyCodes.get(index) == KeyCode.LEFT) {
                    index++;
                    indexForCount++;
                    element.animation.play();
                    element.animation.setOffsetY(174);
                    element.moveX(-speed);
                    sideOfTank = "LEFT";
                    controller_count++;
                    if(index<keyCodes.size())
                    if (keyCodes.get(index) == KeyCode.SPACE) {
                        index++;
                        if (count > delay) {
                            piys.add(new Piy(element, "LEFT"));
                            new Thread(piys.get(piys.size() - 1));
                            count = 0;

                        }
                    }
                } else if (keyCodes.get(index) == KeyCode.SPACE) {
                    index++;
                    indexForCount++;
                    if (count > 10) {
                        piys.add(new Piy(element, sideOfTank));
                        new Thread(piys.get(piys.size() - 1));
                        count = 0;
                    }

                } else {
                    element.animation.stop();
                }

            }
        }
        System.out.println("Repeat Game Count = "+ Repeat_Game_Count);
        System.out.println("Game Count = "+HANDLE_GAME_COUNT);
        System.out.println("Index = "+index);
        if(Repeat_Game_Count == HANDLE_GAME_COUNT)
        {
            /*for(int i = 0;i<botPiys.size();i++)
                gameRoot.getChildren().remove(botPiys.get(i));
            for(int i = 0;i<piys.size();i++)
                gameRoot.getChildren().remove(piys.get(i));
            gameRoot.getChildren().remove(bot);
            gameRoot.getChildren().remove(bot2);
            gameRoot.getChildren().remove(element);
            timer.stop();
            botPiys.clear();
            piys.clear();
            bots.clear();
            //  EndScreen();
            handlstage.close();*/

            EndOfGame();
            keyCodes.clear();
            controllers_Сount.clear();
            index = 0;
            indexForCount = 0;
            HANDLE_GAME_COUNT = 0;
        }
        Repeat_Game_Count++;
        if (!element.life ){
            keyCodes.clear();
            controllers_Сount.clear();
            EndOfGame();
        }
    }
    String sideOfTank1 = "NULL";
    String sideOfTank2 = "NULL";
    public void Multi()throws IOException{

        count++;
        count1++;
        for(int i = 0;i<multiPiys.size();i++)
        {
            if(multiPiys.get(i).GetMeaning() == "HANDLE") {

                switch (multiPiys.get(i).getSide()) {
                    case "UP": {
                        System.out.println("HHHHHHHHHHHHHHHHHAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAANDDDDDDDDDDDDDDDDDDLEEEEEEEEEEEEEEEEE");
                        multiPiys.get(i).moveYbt(-speed * 2, element2);

                        break;
                    }
                    case "DOWN": {
                        multiPiys.get(i).moveYbt(speed * 2, element2);

                        break;
                    }
                    case "RIGHT": {
                        multiPiys.get(i).moveXbt(speed * 2, element2);

                        break;
                    }
                    case "LEFT": {

                        multiPiys.get(i).moveXbt(-speed * 2, element2);

                        break;
                    }
                }
            }else{

                switch (multiPiys.get(i).getSide()) {
                    case "UP": {
                        System.out.println("SSSSSSSSSSSSSSSSSSEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEECCCCCCCCCCCCCCCCCCCCOOOOOOOOOOONNNNNNNNNNNDDDDDDDDDDD");
                        multiPiys.get(i).moveYbt(-speed * 2, element);


                        break;
                    }
                    case "DOWN": {
                        multiPiys.get(i).moveYbt(speed * 2, element);
                        break;
                    }
                    case "RIGHT": {
                        multiPiys.get(i).moveXbt(speed * 2, element);


                        break;
                    }
                    case "LEFT": {
                        multiPiys.get(i).moveXbt(-speed * 2, element);


                        break;
                    }
                }
            }
        }

        if (isPressed(KeyCode.UP)) {
            /*
             * 1. Добавляем в список нажатую клавишу
             * 2. Заносим номер итерации в список чисел
             *
             * */

            element.animation.play();
            element.animation.setOffsetY(261);
            element.moveY(-speed,element2);
            sideOfTank1 = "UP";
            if (isPressed(KeyCode.SPACE)) {

                if (count > delay) {

                    multiPiys.add(new Piy(element, "UP"));
                    //new Thread(piys.get(piys.size() - 1));
                    count = 0;

                }
            }

        } else if (isPressed(KeyCode.DOWN)) {
            element.animation.play();
           element.animation.setOffsetY(0);
            if ((element.getTranslateY()) < 950) {
                element.moveY(speed,element2);
                sideOfTank1 = "DOWN";

            }
            if (isPressed(KeyCode.SPACE)) {
                if (count > delay) {
                    multiPiys.add(new Piy(element, "DOWN"));
                    //new Thread(piys.get(piys.size() - 1));
                    count = 0;
                }
            }

        } else if (isPressed(KeyCode.RIGHT)) {
            element.animation.play();
           element.animation.setOffsetY(87);
            element.moveX(speed,element2);
            sideOfTank1 = "RIGHT";

            if (isPressed(KeyCode.SPACE)) {

                if (count > delay) {
                    multiPiys.add(new Piy(element, "RIGHT"));
                    //new Thread(piys.get(piys.size() - 1));
                    count = 0;
                }
            }

        } else if (isPressed(KeyCode.LEFT)) {
            element.animation.play();
            element.animation.setOffsetY(174);
            element.moveX(-speed,element2);
            sideOfTank1 = "LEFT";
            if (isPressed(KeyCode.SPACE)) {
                if (count > delay) {
                    multiPiys.add(new Piy(element, "LEFT"));
                    //new Thread(piys.get(piys.size() - 1));
                    count = 0;

                }
            }
        } else if (isPressed(KeyCode.SPACE)) {
            if (count > 10) {
                multiPiys.add(new Piy(element, sideOfTank1));
                //new Thread(piys.get(piys.size() - 1));
                count = 0;
            }

        } else {
            //element.animation.stop();
        }

       if (isPressed(KeyCode.W))//UP
       {
            /*
             * 1. Добавляем в список нажатую клавишу
             * 2. Заносим номер итерации в список чисел
             *
             * */

            element2.animation.play();
            element2.animation.setOffsetY(261);
            element2.moveY(-speed,element);
            sideOfTank2 = "UP";
            if (isPressed(KeyCode.C)) {

                if (count1 > delay) {

                    multiPiys.add(new Piy(element2, "UP"));
                    //new Thread(piys.get(piys.size() - 1));
                    count1 = 0;

                }
            }

        } else if (isPressed(KeyCode.S)) //DOWN
        {
            element2.animation.play();
            element2.animation.setOffsetY(0);
            if ((element2.getTranslateY()) < 950) {
                element2.moveY(speed,element);
                sideOfTank2 = "DOWN";

            }
            if (isPressed(KeyCode.C)) {
                if (count1 > delay) {
                    multiPiys.add(new Piy(element2, "DOWN"));
                    //new Thread(piys.get(piys.size() - 1));
                    count1 = 0;
                }
            }

        } else if (isPressed(KeyCode.D))//RIGHT
        {
            element2.animation.play();
            element2.animation.setOffsetY(87);
            element2.moveX(speed,element);
            sideOfTank2 = "RIGHT";

            if (isPressed(KeyCode.C)) {

                if (count1 > delay) {
                    multiPiys.add(new Piy(element2, "RIGHT"));
                    //new Thread(piys.get(piys.size() - 1));
                    count1 = 0;
                }
            }

        } else if (isPressed(KeyCode.A))//LEFT
        {
            element2.animation.play();
            element2.animation.setOffsetY(174);
            element2.moveX(-speed,element);
            sideOfTank2 = "LEFT";
            if (isPressed(KeyCode.C)) {
                if (count1 > delay) {
                    multiPiys.add(new Piy(element2, "LEFT"));
                    //new Thread(piys.get(piys.size() - 1));
                    count1 = 0;

                }
            }
        } else if (isPressed(KeyCode.C))//PIY
        {
            if (count1 > 10) {
                multiPiys.add(new Piy(element2, sideOfTank2));
                //new Thread(piys.get(piys.size() - 1));
                count1 = 0;
            }

        } else {
           // element.animation.stop();
        }

        if(!element2.life || !element.life)EndOfGame();
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        final int width = 1920;
        final int  height = 1045;                   // width = 1536, height = 839

        stage = new Stage();
        Scene scene = new Scene(appRoot,1920,1045);
        /**Setting elements in root*/
        String str = "NULL";
        if(Multi)
            str = "MULTI";

        InitContent(str);
        /**Elements are installing*/

        //Scene scene = new Scene(gameRoot);
        appRoot = (Pane) loader.load();
        /**When key click */
        scene.setOnKeyPressed(event -> keys.put(event.getCode(),true));

        /**When key released*/
        scene.setOnKeyReleased(event -> keys.put(event.getCode(),false));
        timer  = new AnimationTimer() {

            @Override
            public void handle(long now) {

                if(!Repeat && !Multi && NewGame) {
                    try {
                        update();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else if (Repeat&& !Multi && !NewGame)
                {
                    try {
                        Repeat();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else if(Multi && !Repeat && !NewGame)
                {
                    try {
                        Multi();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

        };


        timer.start();

        stage.setTitle("World Of Tanks");
        // handlstage.getStylesheets().add(getClass().getResource("../Game/Game_Style.fxml").toExternalForm());
        stage.setScene(scene);
        Game_Style_Controller controller = loader.getController();
        controller.setGameStage(stage);
        stage.show();
        handlstage = stage;

    }

}
