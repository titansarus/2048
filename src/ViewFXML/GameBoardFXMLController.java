package ViewFXML;

import Model.Account;
import Model.Block;
import Model.Game;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class GameBoardFXMLController {
    public static final int beginX = 400;
    public static final int beginY = 400;
    public static final int endX = 800;
    public static final int endY = 800;

    public static final int emptyCellR = 205, emptyCellG = 193, emptyCellB = 180;

    public static final int padding = 10;

    public int rectangleDim = 0;

    public int stepOfMove = 0;

    public Game game;

    @FXML
    public Label lblLoginedUser;

    public ArrayList<Rectangle> blocks = new ArrayList<>();

    public void updateLoginedUser() {
        if (Account.getLoginedAccount() == null) {
            lblLoginedUser.setText("No User Logined");
        } else {
            lblLoginedUser.setText(Account.getLoginedAccount().getUsername());
        }
    }

    public void blockMaker() {
        int availabeSpace = (endX - beginX - (game.getN() - 1) * padding) / (game.getN() - 1);
        rectangleDim = availabeSpace / game.getN();
        stepOfMove = rectangleDim + padding;

        for (int i = 0; i < game.getN(); i++) {
            for (int j = 0; j < game.getN(); j++) {
                Rectangle rectangle = new Rectangle(rectangleDim, rectangleDim);
                rectangle.relocate(beginX + j * stepOfMove, beginY + i * stepOfMove);
                blocks.add(rectangle);
            }
        }

    }

    public void blockPainter() {
        Block[][] board = game.getBoard();
        int n = getGame().getN();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == null) {
                    blocks.get(i * n + j).setFill(Color.valueOf("#CDC1B4"));
                } else {
                    String color = giveHexStringOfColor(board[i][j]);
                    blocks.get(i * n + j).setFill(Color.valueOf(color));
                }
            }
        }
    }

    //Null: #CDC1B4
    //2: #EEE4DA
    //4: #EDE0C8
    //8: #F2B179
    //16: #F59563
    //32: #F67C5F
    //64: ##F65E3B
    //128 and others: #F9F6F2
    public String giveHexStringOfColor(Block block) {
        String ret = "0XFFFFFF";
        if (block.getNum() == 2) {
            ret = "#EEE4DA";
        } else if (block.getNum() == 4) {
            ret = "#EDE0C8";
        } else if (block.getNum() == 8) {
            ret = "#F2B179";
        } else if (block.getNum() == 16) {
            ret = "#F59563";
        } else if (block.getNum() == 32) {
            ret = "#F67C5F";
        } else if (block.getNum() == 64) {
            ret = "#F65E3B";
        } else {
            ret = "#F9F6F2";
        }

        return ret;
    }


    public void handleBack() {
        if (Container.scenes.size() > 0) {
            Container.scenes.removeLast();
            Container.stage.setScene(Container.scenes.getLast());
            Container.stage.show();
        }


    }


    public void handleExit() {
        System.exit(0);
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
