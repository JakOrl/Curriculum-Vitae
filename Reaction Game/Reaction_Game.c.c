#include <stdio.h> // required for printf()
#include <stdlib.h> // required for rand()
#include <time.h> // required for time()
#include <wiringPi.h> // include WiringPi library
#include <softTone.h> // include Software Tone library to sound piezo
// pin number declarations - use the physical header pin numbers.
const int ledPin = 37;
const int s1 = 11;
const int s2 = 13;
const int buzzer = 12;
int main(void) {
wiringPiSetupPhys(); // use physical header pin numbers
pinMode(ledPin, OUTPUT);
digitalWrite(ledPin, LOW); // turn LED OFF
pinMode(s1, INPUT);
pinMode(s2, INPUT);
softToneCreate(buzzer);
srand((unsigned)time(NULL)); // seed random number generator
delay(5000 + (rand() % 4096));
digitalWrite(ledPin, HIGH); // turn LED ON
while(digitalRead(s1) == 0 && digitalRead(s2) == 0) {
} // loop until button pressed
if(digitalRead(s1)) {
printf("S1 wins!\n");
}
else {
printf("S2 wins!\n");
}
// sound buzzer for 0.5 seconds
softToneWrite(buzzer, 1000);
delay(500);
digitalWrite(ledPin, LOW); // end of game - turn LED OFF
return 0;
}