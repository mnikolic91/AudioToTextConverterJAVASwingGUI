# Java Swing Audio to Text Converter

This project is the final project for the "Fundamentals of Object-Oriented Programming" course, developed by Marija Nikolić. It is a simple yet powerful audio to text converter application developed in Java, utilizing the Swing library for the graphical user interface (GUI). It leverages the AssemblyAI API for accurate and efficient audio transcription, providing users with an intuitive platform to convert their audio files into text format. Designed with security in mind, the application features a login page where user credentials are securely encrypted and stored locally. The architecture follows a mockup Model-View-Controller (MVC) pattern, ensuring a clean separation of concerns and enhancing the maintainability of the codebase.

## Features

- **Audio to Text Conversion**: Utilizes AssemblyAI's cutting-edge API to transcribe audio files to text with high accuracy.

- **Java Swing GUI**: Offers a user-friendly interface, making it easy for users to navigate through the application and perform audio conversions.

- **Secure Login System**: Incorporates a secure login mechanism, with user data encrypted and stored locally, ensuring the confidentiality and integrity of user information.

- **MVC Architecture**: Implements a mockup MVC pattern, segregating the application into distinct layers for the model, view, and controller, which simplifies the development and maintenance process.

## Prerequisites

Before you begin, ensure you have installed the following requirements:

- Java Development Kit (JDK) 8 or above
- Gson library (essential for project functionality)

### Adding Gson Library in IntelliJ

To run this project successfully, you need to add the Gson library to your IntelliJ project setup:

1. The `gson.jar` file is included in the project files.
2. In IntelliJ, go to `File` -> `Project Structure` -> `Libraries`.
3. Click the `+` (add) button and navigate to the path where `gson.jar` is located to include it in your project.

## Setup and Installation

1. Clone the repository to your local machine:
```bash
git clone [repo path]
```

2. Navigate to the project directory and install the dependencies:
```bash
cd your-repo-name
```

3. Run the application:
```bash
java -jar target/your-artifact-name.jar
```

## Usage

- Launch the application and log in using your credentials. If it's your first time using the app, you'll need to sign up.
- Once logged in, navigate to the "Convert" section, and upload the audio file you wish to transcribe.
- Click "Convert" to start the transcription process. Once completed, the text will be displayed on the screen.
- You can save the transcribed text to your local machine.

  
## Security

This application employs robust encryption techniques to secure user data. All sensitive information, including passwords, is encrypted before being stored locally, ensuring that user credentials are protected against unauthorized access.

## Contributing

We welcome contributions from the community. If you'd like to contribute to the development of this audio to text converter, please fork the repository and submit a pull request with your changes.

- Author
Marija Nikolić

