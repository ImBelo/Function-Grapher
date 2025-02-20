package tests;



import org.junit.jupiter.api.Test;
import model.interfaces.Camera;
import model.main.CameraImpl;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.AfterEach;

class TestCameraImpl {
	
		@AfterEach
	    void resetCamera() {
	        Camera camera = CameraImpl.getInstance();
	        camera.setCameraX(0.0);
	        camera.setCameraY(0.0);
	        camera.setCameraWidth(20.0);
	        camera.setCameraHeight(10.0);
	    }

	    @Test
	    void testSingletonInstance() {
	        Camera instance1 = CameraImpl.getInstance();
	        Camera instance2 = CameraImpl.getInstance();
	        Assertions.assertSame(instance1, instance2, "Le due istanze devono essere la stessa (singleton).");
	    }

	    @Test
	    void testSetAndGetCameraX() {
	        Camera camera = CameraImpl.getInstance();
	        camera.setCameraX(10.5);
	        Assertions.assertEquals(10.5, camera.getCameraX(), 0.001, "La coordinata X della fotocamera non è corretta.");
	    }

	    @Test
	    void testToRealX() {
	        Camera camera = CameraImpl.getInstance();
	        camera.setCameraX(0.0);
	        camera.setCameraWidth(20.0);
	        double realX = camera.toRealX(512); 
	        Assertions.assertEquals(0.0, realX, 0.001, "La conversione a coordinate reali X non è corretta.");
	    }
	    
	    @Test
	    void testToRealY() {
	        Camera camera = CameraImpl.getInstance();
	        camera.setCameraY(0.0);
	        camera.setCameraHeight(10.0);
	        double realY = camera.toRealY(300); 
	        Assertions.assertEquals(0.0, realY, 0.001, "La conversione a coordinate reali Y non è corretta.");
	    }

	    @Test
	    void testToScreenX() {
	        Camera camera = CameraImpl.getInstance();
	        camera.setCameraX(0.0);
	        camera.setCameraWidth(20.0);
	        int screenX = camera.toScreenX(0.0);
	        Assertions.assertEquals(512, screenX, "La conversione a coordinate dello schermo X non è corretta.");
	    }

	    @Test
	    void testToScreenY() {
	        Camera camera = CameraImpl.getInstance();
	        camera.setCameraY(0.0);
	        camera.setCameraHeight(10.0);
	        int screenY = camera.toScreenY(0.0); 
	        Assertions.assertEquals(300, screenY, "La conversione a coordinate dello schermo Y non è corretta.");
	    }
	
	    @Test
	    void testGetWidthPixels() {
	        Camera camera = CameraImpl.getInstance();
	        Assertions.assertEquals(1024, camera.getWidthPixels(), "La larghezza in pixel non è corretta.");
	    }

	    @Test
	    void testGetHeightPixels() {
	        Camera camera = CameraImpl.getInstance();
	        Assertions.assertEquals(600, camera.getHeightPixels(), "L'altezza in pixel non è corretta.");
	    }
	    
		@Test
		void testConversionCoherence() {
		    Camera camera = CameraImpl.getInstance();
		    camera.setCameraX(0.0);
		    camera.setCameraY(0.0);
		    camera.setCameraWidth(4.0);
		    camera.setCameraHeight(2.0);
	
		    double realX = -2.0;
		    int screenX = camera.toScreenX(realX);
		    double backToRealX = camera.toRealX(screenX);
		    Assertions.assertEquals(realX, backToRealX, 0.001, "La conversione X avanti e indietro non è coerente.");
	
		    double realY = 1.0;
		    int screenY = camera.toScreenY(realY);
		    double backToRealY = camera.toRealY(screenY);
		    Assertions.assertEquals(realY, backToRealY, 0.001, "La conversione Y avanti e indietro non è coerente.");
		}		

}
