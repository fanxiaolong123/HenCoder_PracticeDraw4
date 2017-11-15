package com.hencoder.hencoderpracticedraw4.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw4.R;

public class Practice12CameraRotateFixedView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    Point point1 = new Point(200, 200);
    Point point2 = new Point(600, 200);

    public Practice12CameraRotateFixedView(Context context) {
        super(context);
    }

    public Practice12CameraRotateFixedView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice12CameraRotateFixedView(Context context, @Nullable AttributeSet attrs, int
            defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 创建 Camera
        Camera camera = new Camera();


        /**
         * 先用 canvas 把 bitmap 移动到原点, 再用 camera 旋转x轴, 然后移动回原来的位置
         * 注意: canvas 的几何变换顺序和代码调用是相反的
         */
        canvas.save();
        camera.save();
        // 先把图片移动到原点
        canvas.translate(point1.x + bitmap.getWidth() / 2, point1.y + bitmap.getHeight() / 2);
        // 旋转 camera 三维坐标的 x 轴
        camera.rotateX(30);
        // 应用到 canvas
        camera.applyToCanvas(canvas);
        // 再把图片移到原来的位置
        canvas.translate(-(point1.x + bitmap.getWidth() / 2), -(point1.y + bitmap.getHeight() / 2));
        canvas.drawBitmap(bitmap, point1.x, point1.y, paint);
        canvas.restore();
        camera.restore();


        canvas.save();
        camera.save();
        // 先移到原点
        canvas.translate(point2.x + bitmap.getWidth() / 2, point2.y + bitmap.getHeight() / 2);
        // 旋转 camera 三维坐标的 y 轴
        camera.rotateY(30);
        // 应用到 canvas
        camera.applyToCanvas(canvas);
        // 移回原来的位置
        canvas.translate(-(point2.x + bitmap.getWidth() / 2), -(point2.y + bitmap.getHeight() / 2));
        canvas.drawBitmap(bitmap, point2.x, point2.y, paint);
        canvas.restore();
        camera.restore();
    }
}
