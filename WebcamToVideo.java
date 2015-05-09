//  OpenCV Java Code to get Video stream
//  from Webcam and write to file
//  +++++++++++++++++++++++++++++
//           Sakib Sami
//      s4kibs4mi@gmail.com
//       www.sakibsami.com
//  +++++++++++++++++++++++++++++

import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_highgui;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameRecorder;
import org.bytedeco.javacv.OpenCVFrameConverter;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WebcamToVideo {
    private CanvasFrame webcamViewFrame;
    private OpenCVFrameConverter.ToIplImage frameConverter;
    private opencv_highgui.VideoCapture videoCapture;
    private FrameRecorder frameRecorder;
    private opencv_core.Mat mat;

    public WebcamToVideo() {
        webcamViewFrame = new CanvasFrame("Webcam View");
        frameConverter = new OpenCVFrameConverter.ToIplImage();
        videoCapture = new opencv_highgui.VideoCapture(0);
        mat = new opencv_core.Mat();

        webcamViewFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        webcamViewFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                doExit();
            }
        });

        if (videoCapture.isOpened()) {
            videoCapture.shiftRight(mat);

            try {
                frameRecorder = FrameRecorder.createDefault("/Users/s4kibs4mi/Documents/webcamToVideo.mp4",
                        mat.cvSize().width(), mat.cvSize().height());
                frameRecorder.start();

                while (true) {
                    videoCapture.shiftRight(mat);
                    Frame frame = frameConverter.convert(mat);
                    webcamViewFrame.showImage(frame);
                    frameRecorder.record(frame);
                    frameRecorder.setInterleaved(true);
                    Thread.sleep(0);
                }

            } catch (Exception ex) {

            }
        }
    }

    private void doExit() {
        if (videoCapture.isOpened()) {
            videoCapture.release();
        }

        try {
            frameRecorder.stop();
        } catch (FrameRecorder.Exception e1) {
            e1.printStackTrace();
        }
    }

    public static void main(String args[]) {
        new WebcamToVideo();
    }
}
