import RPI.GPIO as GPIO
from time import sleep

GPIO.setwarnings(False)
GPIO.setmode(GPIO.BOARD)

GPIO.setup(3, GPIO.IN)

while True:
    i = GPIO.input(3)
    if i == 1:
        print("no obstacle")
        sleep(1)
    else:
        print("obstacle detected")
        sleep(1)
