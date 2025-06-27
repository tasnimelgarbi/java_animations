# Java Animations Projects

This repository contains multiple computer graphics mini-projects developed using Java Swing as part of a graphics coursework.

Each branch in this repository represents a separate animation project with its own logic, structure, and rendering.

## Projects names 

| Name             | Description                                        |
|------------------|----------------------------------------------------|
| sunshine         | Simulates a sunrise scene with flowers and birds.  |
| SpaceAdventure   | A space-themed animation with stars and planets.   |
| house            | A house scene with interactive elements.           |
| mug              | An animated rotating mug using 2D transformations. |


## Technologies Used

- Java SE
- Swing (javax.swing)
- AWT (java.awt)
- Java Utilities (java.util)
- AWT Events & Geometry (java.awt.event, java.awt.geom)

## Author

Tasnim Elgarbi – Zagazig University – Faculty of Computers and Informatices

## How to Use

```bash
git clone https://github.com/tasnimelgarbi/java_animations.git
git checkout <branch-name>
javac -d build $(find . -name "*.java")
java -cp build <MainClassName>
