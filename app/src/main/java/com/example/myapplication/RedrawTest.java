package com.example.myapplication;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOverlay;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.io.InputStream;

public class RedrawTest extends View {
    private class ChessPiece extends BitmapDrawable {
        final int id;
        public ChessPiece(Context context, int _id) {
            super(getResources(), getBitmapFromAsset(context, "chess_piece.png"));
            id = _id;
        }
    }

    final Bitmap chessboard;
    final ChessPiece[] chessPieces;
    final Rect boardSize;
    final Paint paint = new Paint();
    final ViewOverlay overlay;

    final int borderSize = 32;
    final int nChestPieces = 6;

    private static Bitmap getBitmapFromAsset(Context context, String filePath) {
        AssetManager assetManager = context.getAssets();
        InputStream inputStream;
        Bitmap bitmap = null;
        try {
            inputStream = assetManager.open(filePath);
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            // handle exception
        }
        return bitmap;
    }

    public RedrawTest(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        chessboard = getBitmapFromAsset(context, "chessboard.jpg");
        boardSize = new Rect(
                borderSize, borderSize,
                chessboard.getWidth()-borderSize, chessboard.getHeight()-borderSize
        );
        overlay = getOverlay();
        chessPieces = new ChessPiece[nChestPieces];
        for (int i=0; i<nChestPieces; ++i) {
            chessPieces[i] = new ChessPiece(context, i);
            chessPieces[i].setBounds(getBoardRect(0, i));
            overlay.add(chessPieces[i]);
        }
    }

    public void redraw(int i, int j, int pieceId) {
        chessPieces[pieceId].setBounds(getBoardRect(i, j));
        invalidateDrawable(chessPieces[pieceId]);
    }

    private Rect getBoardRect(int i, int j) {
        return new Rect(
                boardSize.left + j*boardSize.width()/8 + 5,
                boardSize.top + i*boardSize.height()/8 + 5,
                boardSize.left + (j+1)*boardSize.width()/8 - 5,
                boardSize.top + (i+1)*boardSize.height()/8 - 5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(this.chessboard, 0, 0, paint);
    }
}
