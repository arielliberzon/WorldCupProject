package des.h;


import java.util.ArrayList;
import java.util.Iterator;

 

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class groupstage extends GridPane {

	
	private int row;
	private int colmnun;
	String color;
	String group;
	private ArrayList<String> list=new ArrayList<String>();
	private ArrayList<StackPane> stack=new java.util.ArrayList<StackPane>();
	ArrayList<Label> label=new java.util.ArrayList<Label>();
	ArrayList<String> str=new java.util.ArrayList<String>();
	////////////////////////////////////////////////////////////////
	ArrayList<String> team1=new ArrayList<>();
	ArrayList<Button> playbutton=new ArrayList<>();
	ArrayList<String> team2=new ArrayList<>();
	////////////////////////////////////////////////////////////////
	
	//Construtor for the Group s
	public groupstage(int a, int b , ArrayList<String> list,String color,String group) {
		this.colmnun=a;
		this.row=b;
		this.list=list;
		this.color=color;
		this.group=group;
	}
	
	//second constructor
	public groupstage(ArrayList<String> team1,ArrayList<Button> playButton, ArrayList<String> team2) {
		this.team1=team1;
		this.playbutton=playButton;
		this.team2=team2;
	}
	
	//Adding default value to str list
	public void defaultLabel() {
		str.add(" Group " + group);
		str.add("P");
		str.add("W");
		str.add("D");
		str.add("L");
		str.add("GF");
		str.add("GA");
		str.add("GD");
		str.add("Pts");
	}
	
	//fake arraylist filler
	public void arr() {
		defaultLabel();
		for (int i = 0; i < row*colmnun; i++) {
			if (i<9) {
				label.add(new Label(str.get(i)));
			}else {
				label.add(new Label(list.get(i)));
			}
			label.get(i).setTextFill(Color.ALICEBLUE);
			}
	}
	
	
	
	public GridPane tablePainting() {
		arr();
		GridPane pane=new GridPane();
		int count=0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < colmnun; j++) {
				pane.setGridLinesVisible(true);
				pane.autosize();
				StackPane stack=new StackPane();
				stack.setBorder(new Border(new BorderStroke(Color.WHITE,BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
				stack.setMaxSize(100, 50);
				stack.setMinSize(100, 50);
				stack.setStyle(color);
				stack.getChildren().add(label.get(count));
				pane.setAlignment(Pos.CENTER);
				pane.add(stack, j, i);
				count =count +1;
			}
		}
		return pane;
	}
	
	
	
	//gridpane second
	//soon to be done!, hang tight
	public GridPane tablePaintingSecond() {
		arr();
		GridPane pane=new GridPane();
		int count=0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < colmnun; j++) {
				pane.setGridLinesVisible(true);
				pane.autosize();
				StackPane stack=new StackPane();
				stack.setBorder(new Border(new BorderStroke(Color.WHITE,BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
				stack.setMaxSize(100, 50);
				stack.setMinSize(100, 50);
				stack.setStyle(color);
				stack.getChildren().add(label.get(count));
				pane.setAlignment(Pos.CENTER);
				pane.add(stack, j, i);
				count =count +1;
			}
		}
		return pane;
	}

	
	
}



