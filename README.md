𝓟𝓾𝓶𝓹𝓒𝓸𝓲𝓷 𝓐𝓷𝓭𝓻𝓸𝓲𝓭 𝓐𝓹𝓹 🚀

This Android application, named PumpCoin, is designed to monitor cryptocurrency price changes in real-time using WebSocket connections to the Binance API. The app provides users with the ability to set custom price change thresholds and time intervals to receive notifications when significant price changes occur.

𝓕𝓮𝓪𝓽𝓾𝓻𝓮𝓼

Real-time WebSocket connection to Binance API for live cryptocurrency price updates.
Customizable time intervals for monitoring price changes (1 minute, 3 minutes, 5 minutes, etc.).
User-defined percentage change threshold to trigger notifications.
Notification system to alert users of significant price changes in their selected cryptocurrency pairs.

𝓣𝓮𝓬𝓱𝓷𝓸𝓵𝓸𝓰𝓲𝓮𝓼 𝓤𝓼𝓮𝓭

Android Studio for Android application development.
Binance API for live cryptocurrency price data.
WebSocket for real-time communication with the Binance server.
Java for backend logic and WebSocket handling.

𝓗𝓸𝔀 𝓽𝓸 𝓤𝓼𝓮

Launch the PumpCoin Android app on your device.
Select the desired time interval for monitoring (e.g., 1 minute, 5 minutes).
Set the percentage change threshold for price notifications.
Click on the "Start" button to initiate the WebSocket connection and start monitoring.
Receive notifications when significant price changes occur.

𝓒𝓸𝓭𝓮 𝓞𝓿𝓮𝓻𝓿𝓲𝓮𝔀

MainActivity.java: The main activity responsible for handling user interactions, starting the WebSocket connection, and managing the foreground service for continuous monitoring.
MyForegroundService.java: A foreground service responsible for maintaining the WebSocket connection in the background, processing real-time price data, and triggering notifications.

𝓗𝓸𝔀 𝓽𝓸 𝓑𝓾𝓲𝓵𝓭 𝓪𝓷𝓭 𝓡𝓾𝓷

Clone the repository to your local machine.
Open the project in Android Studio.
Build and run the project on your Android device.

𝓝𝓸𝓽𝓮𝓼

Ensure that you have the necessary permissions and dependencies installed to run Android applications on your device.
Feel free to contribute to the project, report issues, or suggest improvements!
