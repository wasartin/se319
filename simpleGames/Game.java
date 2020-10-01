package ticTacToe;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage; 

public class Game extends Application{
	
	private int player = 0;
	private final int PLAYER_X = 0;
	private final int PLAYER_O = 1;
	private int turns = 0;
	
	private BorderPane border = new BorderPane();
	private HBox topBorder = new HBox();
	
	private Label xTurn = new Label("Player X's Turn");
	private Label yTurn = new Label("Player O's Turn");

	public static void main(String args[]) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		//Border pane for everything
		assembleBoard();
		GridPane gridPane = createGame();
		border.setCenter(gridPane);
		
		Scene scene = new Scene(border);
		stage.setTitle("Tic Tac Toe");
		stage.setScene(scene);
		stage.show();
	}
	
	private void assembleBoard() {
		border.setMinSize(500, 500);
		//Top Border, announce winner/loser
		topBorder.setPadding(new Insets(50, 12, 50, 12));
		topBorder.setStyle("-fx-background-color: #85C1E9");
    	
		border.setTop(topBorder);
		
		//Restart button
		HBox bottomBorder = new HBox();
		bottomBorder.setPadding(new Insets(50, 12, 50, 12));
		bottomBorder.setStyle("-fx-background-color: #A52A2A");
		Button restart = new Button("Restart");
		restart.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        border.setCenter(restartGame());
		    }
		});
		bottomBorder.getChildren().add(restart);
		
		border.setBottom(bottomBorder);
		
		//Left pane for player X
		VBox leftBorder = new VBox();
		leftBorder.setPadding(new Insets(15, 30, 15, 30));
		leftBorder.setStyle("-fx-background-color: #52BE80");
		leftBorder.getChildren().add(xTurn);
		border.setLeft(leftBorder);
		
		//Right pane for Player O
		VBox rightBorder = new VBox();
		rightBorder.setPadding(new Insets(15, 30, 15, 30));
		rightBorder.setStyle("-fx-background-color: #FFD700");
		rightBorder.getChildren().add(yTurn);
		border.setRight(rightBorder);
	}
	
	private GridPane createGame() {
		xTurn.setOpacity(1);
		yTurn.setOpacity(0);
		player = PLAYER_X;
		GridPane gridPane = new GridPane();
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				ToggleButton currButton = new ToggleButton();
				currButton.setStyle("-fx-min-height: 120px; -fx-min-width: 120px; -fx-border-style: solid;");
				currButton.setOnAction(handler);
				gridPane.add(currButton, i, j);
				currButton.setId("unitilized");
			}
		}
		gridPane.setMinSize(300, 300);
		gridPane.setAlignment(Pos.CENTER);
		return gridPane;
	}
	
	private GridPane restartGame() {
		turns = 0;
		topBorder.getChildren().clear();
		return createGame();
	}
	
	// Check if someone has won. Return PLAYER_X for x, PLAYER_O for o, and -1 if it is a draw.
	private int win(ToggleButton t) {
		GridPane currGrid = (GridPane) t.getParent();
		ObservableList<Node> someList = currGrid.getChildren();
		//fill some board
		String[][] board = new String[3][3];
		for(int i = 0; i < 9; i++) {
			int row = currGrid.getRowIndex(someList.get(i));
			int col = currGrid.getColumnIndex(someList.get(i));
			ToggleButton curr = (ToggleButton) someList.get(i);
			board[row][col] = curr.getId();
		}
		//check for vertical victory
		if(board[0][0].equals(board[1][0]) && board[0][0].equals(board[2][0]) && !board[0][0].equals("unitilized")) {
			if(board[0][0].equals("x")) return PLAYER_X;
			else return PLAYER_O;
		}
		if(board[0][1].equals(board[1][1]) && board[0][1].equals(board[2][1]) && !board[0][1].equals("unitilized")) {
			if(board[0][1].equals("x")) return PLAYER_X;
			else return PLAYER_O;
		}
		if(board[0][2].equals(board[1][2]) && board[0][2].equals(board[2][2]) && !board[0][2].equals("unitilized")) {
			if(board[0][2].equals("x")) return PLAYER_X;
			else return PLAYER_O;
		}
		//horizontal victory
		if(board[0][0].equals(board[0][1]) && board[0][0].equals(board[0][2]) && !board[0][0].equals("unitilized")) {
			if(board[0][0].equals("x")) return PLAYER_X;
			else return PLAYER_O;
		}
		if(board[1][0].equals(board[1][1]) && board[1][0].equals(board[1][2]) && !board[1][0].equals("unitilized")) {
			if(board[1][0].equals("x")) return PLAYER_X;
			else return PLAYER_O;
		}
		if(board[2][0].equals(board[2][1]) && board[2][0].equals(board[2][2]) && !board[2][0].equals("unitilized")) {
			if(board[2][0].equals("x")) return PLAYER_X;
			else return PLAYER_O;
		}
		//Check Negative slope win Top left to Bottom right
		if(board[0][0].equals(board[1][1]) && board[0][0].equals(board[2][2]) && !board[0][0].equals("unitilized")) {
			if(board[0][0].equals("x")) return PLAYER_X;
			else return PLAYER_O;
		}
		//Check positive slope win, Bottom Left to Top Right
		if(board[2][0].equals(board[1][1]) && board[2][0].equals(board[0][2]) && !board[2][0].equals("unitilized")) {
			if(board[2][0].equals("x")) return PLAYER_X;
			else return PLAYER_O;
		}
		return -1;
	}
	
	private final EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>(){
        @Override
        public void handle(final ActionEvent event) {
            ToggleButton button = (ToggleButton) event.getSource();
            turns++;
            if (player == PLAYER_X) {
                //displayX
            	Image x = new Image("images\\x.jpg", 100, 100, true, true);
            	ImageView xImg = new ImageView(x);
            	button.setGraphic(xImg);
            	button.setId("x");
            	player = PLAYER_O;
            	xTurn.setOpacity(0);
            	yTurn.setOpacity(1);
            }
            else if(player == PLAYER_O) {
            	//displayO
            	Image o = new Image("images\\o.jpg", 100, 100, true, true);
            	ImageView oImg = new ImageView(o);
            	button.setGraphic(oImg);
            	button.setId("o");
            	player = PLAYER_X;
            	xTurn.setOpacity(1);
            	yTurn.setOpacity(0);
            }
        	int winner = win(button);
        	if(winner == PLAYER_X || winner == PLAYER_O) {
        		turns = 9;
        		GridPane gridPane = (GridPane) button.getParent();
        		ObservableList<Node> someList = gridPane.getChildren();
        		for(int i = 0; i < someList.size(); i++) {
        			ToggleButton currButton = (ToggleButton) someList.get(i);
        			currButton.setMouseTransparent(true);
        		}
        	}
            button.setMouseTransparent(true);
            //Check for win
            if(turns == 9) {
            	//if No win, display a draw
            	yTurn.setOpacity(0);
            	xTurn.setOpacity(0);
            	String result = "";
            	switch(winner) {
            	case PLAYER_X:
            		result = "Congratulations, X wins the game.";
            		break;
            	case PLAYER_O:
            		result = "Congratulations, O wins the game.";
            		break;
            	case -1: 
            	default:
            		result = "Draw! Techincally you are both losers."; 
            		break;
            	}
            	Label display = new Label(result);
            	display.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
            	topBorder.getChildren().add(display);
            }
        }
    };
}
