# 🧩 Terminal Sudoku Game

A console-based Sudoku game implemented in **Java**.  
Fill a 9×9 grid with numbers so that each row, column, and 3×3 section contains all the digits between 1 and 9.  

---

## 🚀 Features

- Play Sudoku with **three difficulty levels**: Easy, Medium, Hard  
- **Lives system** – start with 3 lives; lose a life for incorrect moves  
- Reveals the **solution** at the end of the game  
- Keeps track of correctly solved boxes  
- Console-based, interactive gameplay with prompts for input  

---

## 🎮 Controls & Gameplay

1. Pick a difficulty level: **Easy**, **Medium**, or **Hard**  
2. The board will reveal some numbers based on difficulty  
3. Input a location on the board as `x y` (0-based index)  
4. Enter a value from `1` to `9` to fill that box  
5. Incorrect moves **subtract lives**  
6. Game ends when all boxes are filled correctly or lives reach zero  
7. The full solution is displayed at the end of the game  
8. Option to play again after finishing  

---

## ⚡ Requirements

- Java 8 or higher  
- Console/terminal to run the game  

---

## ▶️ Running the Game

1. Compile all Java files:

   `javac *.java`  

2. Run the game:

   `java Sudoku`  

---

## 🌟 Future Improvements

- Add **GUI interface** for interactive play  
- Implement **timer and scoring system**  
- Add **save/load functionality**  
- Include **custom Sudoku puzzles**  

---
