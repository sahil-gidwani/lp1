from picamera import PiCamera
from time import sleep

camera = PiCamera()
camera.resolution(1080, 30)
camera.start_preview()
sleep(2)
camera.capture("/home/pi/camera/image.jpg")
camera.stop_preview()
camera.close()
