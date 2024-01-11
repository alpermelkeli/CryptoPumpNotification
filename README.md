
PumpCoin Android App
This Android application, named PumpCoin, is designed to monitor cryptocurrency price changes in real-time using WebSocket connections to the Binance API. The app provides users with the ability to set custom price change thresholds and time intervals to receive notifications when significant price changes occur.

Features
Real-time WebSocket connection to Binance API for live cryptocurrency price updates.
Customizable time intervals for monitoring price changes (1 minute, 3 minutes, 5 minutes, etc.).
User-defined percentage change threshold to trigger notifications.
Notification system to alert users of significant price changes in their selected cryptocurrency pairs.
Technologies Used
Android Studio for Android application development.
Binance API for live cryptocurrency price data.
WebSocket for real-time communication with the Binance server.
Java for backend logic and WebSocket handling.
How to Use
Launch the PumpCoin Android app on your device.
Select the desired time interval for monitoring (e.g., 1 minute, 5 minutes).
Set the percentage change threshold for price notifications.
Click on the "Start" button to initiate the WebSocket connection and start monitoring.
Receive notifications when significant price changes occur.
Code Overview
MainActivity.java: The main activity responsible for handling user interactions, starting the WebSocket connection, and managing the foreground service for continuous monitoring.

MyForegroundService.java: A foreground service responsible for maintaining the WebSocket connection in the background, processing real-time price data, and triggering notifications.

How to Build and Run
Clone the repository to your local machine.
Open the project in Android Studio.
Build and run the project on your Android device.
Notes
Ensure that you have the necessary permissions and dependencies installed to run Android applications on your device.
Feel free to contribute to the project, report issues, or suggest improvements!
