package edu.gmail.yerra.keshav.Spectre;

import android.graphics.Typeface;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import edu.gmail.yerra.keshav.Spectre.model.Board;
import edu.gmail.yerra.keshav.Spectre.model.BoardListener;
import edu.gmail.yerra.keshav.Spectre.model.Tile;
import edu.gmail.yerra.keshav.Spectre.model.TileMovement;
import edu.gmail.yerra.keshav.Spectre.view.BoardView;



public class BoardControllerActivity extends ActionBarActivity implements BoardListener {

    private BoardView boardView;
    private Board board;
    private int userMoves;
    private TextView movesTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // Start Activity as FullScreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);

        // Fetch Intent Extra values for Board difficulty
        Bundle extra = getIntent().getExtras();
        BoardDifficulty difficulty = extra != null
                                     ? (BoardDifficulty) extra.getSerializable("difficulty")
                                     : BoardDifficulty.EASY;

        int boardSize = 0;

        switch (difficulty)
        {
            case HARD:
                boardSize = 6;
                break;

            case MEDIUM:
                boardSize = 4;
                break;

            case EASY:
            default:
                boardSize = 2;
                break;

        }


        board = new Board(boardSize, boardSize);
        board.addBoardListener(this);


        setContentView(R.layout.activity_board_controller);
        getSupportActionBar().hide();


        setActivityTypeface();


        boardView = (BoardView) findViewById(R.id.board_view);
        boardView.requestFocus();


        userMoves = 0;

    }


    private void setActivityTypeface()
    {

        Typeface tf = Typeface.createFromAsset(getResources().getAssets(), "OpenSans-Semibold.ttf");


        Button newButton = (Button) findViewById(R.id.new_board_button);
        //Button solveButton = (Button) findViewById(R.id.solve_board_button);

        TextView movesLabelTextView = (TextView) findViewById(R.id.moves_label_textview);
        movesTextView = (TextView) findViewById(R.id.moves_textview);



        newButton.setTypeface(tf);
        //solveButton.setTypeface(tf);
        movesLabelTextView.setTypeface(tf);


        ViewCompat.setElevation(newButton, 40);
       // ViewCompat.setElevation(solveButton, 40);



        tf = Typeface.createFromAsset(getResources().getAssets(), "OpenSans-Regular.ttf");
        movesTextView.setTypeface(tf);
    }


    @Override
    public void boardChanged()
    {
        userMoves++;
        movesTextView.setText(""+userMoves);
        boardView.invalidate();
    }


    @Override
    public void boardSolved()
    {
        Toast.makeText(this, "The Board was solved!", Toast.LENGTH_SHORT).show();
    }


    public void solveBoardConfiguration(View view)
    {
        board.solve();
    }


    public void newBoardConfiguration(View view)
    {
        userMoves = -1;
        board.scrambleBoard();
    }


    public int getBoardLength()
    {
       return board.getLength();
    }


    public int getBoardWidth()
    {
        return board.getWidth();
    }


    public Iterable<Tile> getBoardTiles()
    {
        return board.getTiles();
    }


    public void boardTileClicked(int x, int y, TileMovement direction)
    {
        board.tileClicked(x, y, direction);
    }
}
