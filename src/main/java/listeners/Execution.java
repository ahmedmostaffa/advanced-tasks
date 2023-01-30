package listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IExecutionListener;

import utilities.dataHelpers.ConfigReader;
import utilities.videos.AutoScreenRecorder;

import java.awt.*;
import java.io.File;
import java.io.IOException;


public class Execution implements IExecutionListener {
	Logger log=LogManager.getLogger();
	
	@Override
	public void onExecutionStart() {
		switch(ConfigReader.getStringProperty("videoRecording").toUpperCase()) {
		case "ON":
			try {
				if(ConfigReader.getBooleanProperty("headless")) {
					log.info("video recording won't be invoked");			
				}else {
					log.info("start recording");
					AutoScreenRecorder.startRecording("recording");
				}
				
			} catch (Throwable e) {
				log.info("failed to start the recording");
			}
			break;

		
		case "OFF":
			log.info("videos recording is turned off");
			break;
			default:
				log.error("failed to start video recording no supported input");
				throw new IllegalStateException("No supported input for video recording");

		}

		
			}

	@Override
	public void onExecutionFinish() {
		switch(ConfigReader.getStringProperty("videoRecording").toUpperCase()) {
		case "ON":
		try {
			if(!ConfigReader.getBooleanProperty("headless")) {	
				log.info("stop recording");
				AutoScreenRecorder.stopRecording();
				log.info("recording video has been successfully saved");
			}
		} catch (Throwable e) {
			log.info("failed to stop the recording");
		}
		break;
		case "OFF":
			log.info("please remember the video recording is not initialized");
			break;
			default:
				log.error("failed to start video recording");
				throw new IllegalStateException("No supported input for video recording");

		}
		try {
			Desktop.getDesktop().browse(new File("test-output/SparkReport/Index.html").toURI());
		} catch (Throwable throwable) {
		}
	}

}
