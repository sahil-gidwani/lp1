# VCC (+) - 23
# GND - 6

import RPI.GPIO as GPIO
from time import sleep

GPIO.setwarnings(False)
GPIO.setmode(GPIO.BOARD)

GPIO.setup(23, GPIO.OUT)

while True:
    GPIO.output(23, GPIO.HIGH)
    print("beep!!")
    sleep(1)
    GPIO.output(23, GPIO.LOW)
    print("...")
    sleep(1)
