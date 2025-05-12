#!/usr/bin/python

#This is a reaction game to be played on a PCB 
#The circuit diagram will be shown aswell. 
#This program runs on Python IDLE rather than Geany

import RPi.GPIO as GPIO
import time, random
GPIO.setmode(GPIO.BOARD)
# Set up the LED pin as an output
led = 37
GPIO.setup(led, GPIO.OUT)
GPIO.output(led, 0)
# Set up the pins that the buttons connect to as inputs
s1 = 11
s2 = 13
GPIO.setup(s1, GPIO.IN)
GPIO.setup(s2, GPIO.IN)
# Set up buzzer
buzzer = 12
GPIO.setup(buzzer, GPIO.OUT)
# Initialise PWM, 1kHz
p = GPIO.PWM(buzzer, 1000)
# Get player names
player1 = input("Enter Player 1’s name: ")
player2 = input("Enter Player 2’s name: ")
player_1_score = 0
player_2_score = 0
rounds = int(input("How many rounds?:"))
for i in range(0,rounds):
    # Wait random number of seconds before turning LED on
    time.sleep(random.uniform(5, 10))
    GPIO.output(led, 1)
    start_game = time.time()
    # Loop to wait until a button is pressed
    while GPIO.input(s1) == False and GPIO.input(s2) == False:
        pass
    # Determine which button was pressed
    if GPIO.input(s1) == True:
        print(player1 + " wins!")
        end_game = time.time()
        print("Reaction time:", end_game - start_game)
        player_1_score += 1
    else:
        print(player2 + " wins!")
        end_game = time.time()
        print("Reaction time:", end_game - start_game)
        player_2_score += 1
    # Button was pressed so sound buzzer for 0.5 seconds
    p.start(50) # 50% duty cycle
    time.sleep(0.5)
    p.stop()
    #Waiting from 5-10 seconds before the LED lights up again
    GPIO.output(led, 0)
#Prints the winner depending on who has better scores 
if player_1_score > player_2_score:
    print(player1, "Wins!")
elif player_1_score == player_2_score:
    print("Draw!")
else:
    print(player2, "Wins!")
    
# Clean up all GPIO
GPIO.cleanup()
