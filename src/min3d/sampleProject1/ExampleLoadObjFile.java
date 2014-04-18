package min3d.sampleProject1;

import android.os.Bundle;
import android.view.MotionEvent;
import min3d.core.Object3dContainer;
import min3d.core.RendererActivity;
import min3d.parser.IParser;
import min3d.parser.Parser;
import min3d.vos.Light;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.MotionEvent;

/**
 * How to load a model from a .obj file
 * 
 * @author dennis.ippel
 * 
 */
public class ExampleLoadObjFile extends RendererActivity {
	private Object3dContainer objModel;
	private GLSurfaceView mGLSurfaceView;
	float touchTurn;
	float touchTurnUp;
	public float mAngleX;
    public float mAngleY;
    private final float TOUCH_SCALE_FACTOR = 180.0f / 320;
    private final float TRACKBALL_SCALE_FACTOR = 36.0f;
//    private CubeRenderer mRenderer;
    private float mPreviousX;
    private float mPreviousY;


	@Override
	public void initScene() {
		
		scene.lights().add(new Light());
		
		IParser parser = Parser.createParser(Parser.Type.OBJ,
//				getResources(), "min3d.sampleProject1:raw/camaro_obj", true);
				getResources(), "min3d.sampleProject1:raw/sony_obj", true);
		parser.parse();

		objModel = parser.getParsedObject();
		objModel.scale().x = objModel.scale().y = objModel.scale().z = .7f;
		scene.addChild(objModel);
		
		scene.camera().position.z= 10;
		scene.camera().target = objModel.position();
	}

	@Override
	public void updateScene() {
//		objModel.rotation().x++;
//		objModel.rotation().z++;
		
	    if (mAngleX != 0) {
//	        scene.camera().position.rotateY(touchTurn);
	        scene.camera().position.rotateY(mAngleX);
//	        touchTurn = 0;
	        mAngleX = 0;
	    }

	    if (mAngleY != 0) {
//	        scene.camera().position.rotateX(touchTurnUp);
	        scene.camera().position.rotateX(mAngleY);
//	        touchTurnUp = 0;
	        mAngleY = 0;
	    }

	}
	
	public boolean onTouchEvent(MotionEvent me) {
//	    float xpos = 0;
//		float ypos = 0;
//		if (me.getAction() == MotionEvent.ACTION_DOWN) {
//	        xpos = me.getX();
//	        ypos = me.getY();
//	        return true;
//	    }
//
//	    
//		if (me.getAction() == MotionEvent.ACTION_UP) {
//	        xpos = -1;
//	        ypos = -1;
//	        touchTurn = 0;
//	        touchTurnUp = 0;
//	        return true;
//	    }
//
		float x = me.getX();
        float y = me.getY();
	    if (me.getAction() == MotionEvent.ACTION_MOVE) {
//	        float xd = me.getX() - xpos;
//	        float yd = me.getY() - ypos;
	    	
	    	float dx = x - mPreviousX;
            float dy = y - mPreviousY;
//
//	        xpos = me.getX();
//	        ypos = me.getY();
//
//	        touchTurn = xd / -20f;
//	        touchTurnUp = yd / -20f;
//	        return true;
//	    }
//
//	    try {
//	        Thread.sleep(15);
//	    } catch (Exception e) {
//
//	    }
//
//	    return super.onTouchEvent(me);
        
            mAngleX += dx * TOUCH_SCALE_FACTOR;
            mAngleY += dy * TOUCH_SCALE_FACTOR;
          
        }
        mPreviousX = x;
        mPreviousY = y;
        return super.onTouchEvent(me);

	}
	
	@Override 
	public boolean onTrackballEvent(MotionEvent e) {
        mAngleX += e.getX() * TRACKBALL_SCALE_FACTOR;
        mAngleY += e.getY() * TRACKBALL_SCALE_FACTOR;
        mGLSurfaceView.requestRender();
        return true;
    }

}


