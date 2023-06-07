# MinecraftServerCheck

MinecraftServerCheck is an Android Studio project that allows users to check the ping of a Minecraft server or server address. It provides information about the server's responsiveness, helping users determine the quality of their connection to the server.

## How It Works

The project utilizes the `InetAddress` class from Java's networking library to perform a ping test to the specified server address. The `PingTask` class, implemented as an `AsyncTask`, handles the background process of checking the server's reachability and calculating the ping time. The ping time is measured by sending a network request to the server and calculating the time it takes to receive a response.

The project includes the following key components:

- `MainActivity.java`: This is the main activity class that controls the user interface and handles user interactions. It includes methods for initiating a ping test, displaying the ping result, and providing improvement tips based on the ping value.

- `PingTask`: This inner class extends `AsyncTask` and performs the ping test in the background. It takes the server address as input, resolves the IP address of the server, and sends a ping request to measure the response time. The result is then passed to the `onPostExecute` method for further processing.

- `LivePingTask`: Another inner class that extends `AsyncTask`, it periodically performs live ping tests to continuously update the ping value in real-time. It runs on a separate timer and provides live feedback on the responsiveness of the server.

## Building Process

To build and run the MinecraftServerCheck project, follow these steps:

1. Download and install Android Studio from the official website: [https://developer.android.com/studio](https://developer.android.com/studio)

2. Clone or download the MinecraftServerCheck project from the GitHub repository: [https://github.com/your-username/MinecraftServerCheck](https://github.com/your-username/MinecraftServerCheck)

3. Open Android Studio and select "Open an existing Android Studio project."

4. Navigate to the location where you cloned or downloaded the project and select the project folder.

5. Once the project is open, connect an Android device or start an emulator.

6. Click on the "Run" button (green triangle) in the Android Studio toolbar or select "Run > Run 'app'" from the menu.

7. Choose the target device and click "OK" to build and install the app on the selected device or emulator.

8. The app should launch on the device or emulator, and you can proceed to test the Minecraft server ping functionality.

## Contribution

Contributions to the MinecraftServerCheck project are welcome. If you want to contribute, please follow these steps:

1. Fork the repository on GitHub: [https://github.com/your-username/MinecraftServerCheck](https://github.com/your-username/MinecraftServerCheck)

2. Clone your forked repository to your local machine.

3. Create a new branch for your feature or bug fix: `git checkout -b feature/your-feature-name` or `git checkout -b bugfix/your-bug-fix-name`.

4. Make the necessary changes and commit them with descriptive commit messages.

5. Push your changes to your forked repository.

6. Open a pull request on the original repository, describing your changes and the motivation behind them.

7. Wait for the maintainers to review your pull request and address any feedback if required.

## Support

If you encounter any issues or have suggestions for improvement, please open an issue on the GitHub repository: [https://github.com/your-username/MinecraftServerCheck/issues](https://github.com/muhtadshah/MinecraftServerCheck/issues)

Please provide detailed information about the problem or your suggestion to help us understand and address it effectively.

## License

The MinecraftServerCheck project is released under the [GPL-3.0 License](https://opensource.org/licenses/GPL-3.0). You can find the full license text in the `LICENSE` file of the project.
