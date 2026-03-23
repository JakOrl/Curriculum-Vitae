# DIY Pong 

A professional, modular recreation of the classic Pong arcade game built with **Java 21** and **JavaFX**. This project follows the **Model-View-Controller (MVC)** architectural pattern to ensure clean separation of concerns and maintainability.

---

## Features

* **Dynamic Gameplay:** Responsive paddle movement and ball physics with automatic speed scaling.
* **Customizable Settings:** Real-time menus to adjust game win targets, ball acceleration, and racket dimensions.
* **Robust Physics:** Custom **AABB (Axis-Aligned Bounding Box)** collision detection logic.
* **State Management:** Integrated Pause/Resume system (`P` key) and New Game functionality (`N` key).
* **Scalable UI:** The game canvas dynamically binds to window resizing for a consistent experience.

---

##  Technical Architecture

This project is built using modern Java standards:

* **MVC Pattern:** * **Models:** `Ball`, `Racket`, `Player`, and `Game` handle the data and physics.
    * **View:** `Pong_Canvas` and `Pong_Menu` manage the JavaFX rendering and UI components.
    * **Controller:** `Controller`, `MenuListener`, and `My_Main` manage the game loop and user input.
* **JUnit 5 Testing:** Core collision logic is verified via unit tests to ensure physics accuracy.
* **Java Module System (JPMS):** Fully modularized for Java 21 compatibility.

---

##  How to Play

### Controls
| Action | Player 1 (Left) | Player 2 (Right) |
| :--- | :--- | :--- |
| **Move Up** | `W` | `UP Arrow` |
| **Move Down** | `S` | `DOWN Arrow` |

### System Commands
* **SPACE**: Serve the ball / Start game.
* **P**: Pause or Resume the game.
* **N**: Reset and start a New Game.

---

## 📦 Installation & Setup

### Prerequisites
* **JDK 21** or higher.
* **Maven** for dependency management.

### Running the Application
This project is optimized for IntelliJ IDEA to take advantage of its automated JavaFX module handling.

Open Project: Open the root folder in IntelliJ IDEA.

SDK Check: Ensure JDK 21 is selected (File > Project Structure > Project).

Run Game: Navigate to src/main/java/Pong_Source/Controller/My_Main.java.

Launch: Right-click My_Main and select Run 'My_Main.main()'.

Note on Maven: While a pom.xml is provided for dependency management, environment-specific Java Module System (JPMS) configurations may cause mvn javafx:run to fail in certain terminal environments. Execution via the IDE is the verified method for this build.