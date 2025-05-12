# Experimenting with PulseWidthModulation on the PCB using C language

#include <wiringPi.h>
const int pwmPin = 12;
int main(void) {
wiringPiSetupPhys(); // use physical header pin numbers
pinMode(pwmPin, PWM_OUTPUT);
for(int pwmValue = 0; pwmValue <= 1024; pwmValue += 256) {
pwmWrite(pwmPin, pwmValue);
delay(500);
}
pwmWrite(pwmPin, 0); // turn LED off again
return 0;
}