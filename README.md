# Author
Matan Toledano

If you have any questions or encounter any issues with this project, feel free to contact me at matantoled@gmail.com. I'll be more than happy to help!

# Bulls and Cows Game

"Bulls and Cows" is a classic code-breaking game. Players must guess the computer's secret combination of numbers. For each guess, they receive feedback in terms of "bulls" (correct number in the correct position) and "cows" (correct number in the wrong position). The game is developed using the Spring Boot framework for Java and incorporates security and database functionalities.

## Security
![image](https://github.com/matantoled/Bulls-and-Cows/assets/75612523/9f56fca7-478a-4272-8182-960f297dff95)


Upon the first connection, players are prompted to log in:
- **Username**: Can be either "user" or "admin".
- **Password**: For both roles, the password is "password".

The game has two roles:
- **user**: Regular game access.
- **admin**: Has the added ability to view the game's answers and to reset the high scores.

## Gameplay and Features
![image](https://github.com/matantoled/Bulls-and-Cows/assets/75612523/a5a376ab-94d5-44ce-916c-19759a068e26)

- Players guess the computer's secret combination and receive feedback.
- The game's answers are printed in the terminal.
- The application utilizes a database to save the top 5 scores.
- If a player with the same name achieves a better score, the record is updated.
- At the end of the game, a scoreboard displays the highest scores, the associated player names, and the date/time of the score.

![image](https://github.com/matantoled/Bulls-and-Cows/assets/75612523/7e4cc5e0-015f-4b28-b52c-e898a729b91b)



## Getting Started

### Prerequisites
Ensure you have Maven and Java installed on your system.

# Initializing 

In order to initialize the project make sure to:
1.	When you open the project, if intelliJ propose to "Load Maven Project" do it. You can later reload maven with the "M" icon on the right of the screen, or by right clicking on the pom.xml file and selecting "Maven -> Reload project".
2.	You see red lines in the code? Go to File -> Project Structure -> Project Settings -> Project -> SDK -> and choose your Java SDK
3.	Still see red stuff? Open the same dialog and click on "Fix" if you see some
4.	Edit your configuration "ex4" at the top right. Make sure the "Main class" is set to "hac.DemoApplication" and that Java is set
Everything ok? Let's continue:
1.	Run the SQL server like this:
![image](https://github.com/matantoled/Vaccination-Project/assets/75612523/e905d6b5-8b3f-4807-836d-fb7c750b6dce)

 
and create a database named "ex4". Here's how you can do it:
Click the Admin button in the MySQL row and a website will open:
![image](https://github.com/matantoled/Vaccination-Project/assets/75612523/f272e883-947d-4faf-8fcd-562903249d73)

 
click on the new button -> write "ex5" on the Database name and press Create.
Now you have a DB named ex4. 

The DB credentials are stored in the application.properties file. You may change them if you want (and create another with the same name).
2.	Run the project, you should not see any errors in IntelliJ console.

## How to Play

1. Log in with either the "user" or "admin" role.
2. Enter your guess by selecting numbers for each field.
3. Submit your guess to receive feedback on "bulls" and "cows".
4. Adjust your guess based on feedback until you find the correct combination or run out of attempts.
5. View the scoreboard for top scores, player names, and date/time.

## Acknowledgments
- Special thanks to my partner, Yitzhak Halevi, for his invaluable contributions to this project.
- A heartfelt thank you to Hadassah College for providing the resources and support that made this project possible.
