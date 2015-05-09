//  OpenCV C++ Code to get Video stream
//  from Webcam and write to file
//  +++++++++++++++++++++++++++++
//           Sakib Sami
//      s4kibs4mi@gmail.com
//       www.sakibsami.com
//  +++++++++++++++++++++++++++++

#include <iostream>
#include "opencv2/highgui/highgui.hpp"
#include "opencv2/core/core.hpp"
#include "opencv2/imgproc/imgproc.hpp"

using namespace cv;
using namespace std;

int main(){
    VideoCapture vc(0);
    Mat mat;
    
    vc >> mat;
    VideoWriter vw("/Users/s4kibs4mi/Documents/cppWebcamExample.avi",CV_FOURCC('D','I','V','X'), 30, cvSize(1280,720));
    
    if(vc.isOpened() && vw.isOpened()){
        try{
            while (true) {
                vc >> mat;
                imshow("WebCam", mat);
                vw.write(mat);
                
                if(waitKey(1) == 27){
                    break;
                }
            }
        }catch(exception& e){
            cout << e.what() << endl;
        }
    }
    return 0;
}
