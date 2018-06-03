package Game;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;



public class Blocks extends Pane {

    Image blockImg = new Image(getClass().getResourceAsStream("Wall.1.1.png"));
    ImageView block;

    public enum BlockType{
        PLATFORM,BRICK
    }
    public Blocks(BlockType blockType,int x, int y)
    {
        block = new ImageView(blockImg);
        block.setFitWidth(55);
        block.setFitHeight(55);
        setTranslateX(x);
        setTranslateY(y);

        switch (blockType)
        {
            case PLATFORM:
                block.setViewport(new Rectangle2D(0,0,55,55));
                break;

            case BRICK:
                block.setViewport(new Rectangle2D(0,0,0,0));
        }

        getChildren().add(block);
        GameDisplay.platforms.add(this);
        GameDisplay.gameRoot.getChildren().add(this);
    }


}
