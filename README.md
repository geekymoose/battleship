# Battleship
Download the executable: [Battleship.zip](https://github.com/GeekyMoose/battleship/releases/download/v1.0/battleship.zip)

![img](https://github.com/GeekyMoose/battleship/blob/master/documents/screenshots/theme_default/1-defaultWelcome.png)


<!-- *********************************************************************** -->
## Description
February - May 2015

Battleship is a university project realized during the second year of Bachelor of Computer Science. The goal was to realize a battleship game using Java.

The teacher in charge was [Michele Pagani](http://www.pps.univ-paris-diderot.fr/~pagani/) (michele.pagani@pps.univ-paris-diderot.fr)<br/>
We got the best grade for this project.


<!-- *********************************************************************** -->
## Installation / Execution

### Download
**Executable zip file:** Download the zip file. (See above for link).<br/>
**Source code:** clone the project using `git clone https://github.com/GeekyMoose/battleship.git`

### Execute from release zip file
Unzip the file and execute the jar file. Note that all the elements inside this zip file are required in order to run.

### Execute from source code
#### On Linux and MAC
To run the project, use the script `exec.sh`<br/>
Allow executable rights with `chmod 755 exec.sh`

* Display help with `./exec.sh -h`
* Execute project with `./exec.sh -x`
* Generate JavaDoc with `./exec.sh -o`
* Generate jar file with `./exec.sh -j`

#### On Windows
There is no easy way to compile from windows. However, you can download the zip executable and run the .jar file.

####Warning
Don't forget to check the technical description (Java version for example)


<!-- *********************************************************************** -->
## Technical description
- Java version: Java SE 8 (Warning, we didn't develop compatibility for older version)
- Language: Java / Swing
- IDE used: NetBeans 8
- Network: TCP Protocol (The server itself is written in Java)


<!-- *********************************************************************** -->
## Explosion animation
![img](https://github.com/GeekyMoose/battleship/blob/master/documents/screenshots/gif/explosion_demo.gif)

Explosions are managed by a 'handcraft Java library'. (Can be find in `com.battleship.dynamic` package).

#### How does it work
The explosions manager can be compared to an automata with a specific number a states. 

* Each state is an image. (Even if `EvenApp` can in theory manage any type)
* When `EvenApp` start, a timer set a push delay which call next state.
* `DynamicEvent` extends `EvenApp` and add a position (x:y) behavior
* `ExplosionEvent` extends `DynamicEvent` and create the specific explosion behavior


<!-- *********************************************************************** -->
## Theme manager
All the images used are loaded from `data/theme` folder. An 'handcraft' library is in charge of this loading.

![img](https://github.com/GeekyMoose/battleship/blob/master/documents/screenshots/imagesLoading.png)
 
* `com.battleship.asset.ThemeManager` This class manage the themes loading (Find the files, create paths..)
* `com.battleship.views.tools.Theme` keep the current loaded theme and functions for image loading

Image name is formated using an unique number. (Dev note: a format accepting string name could be better, but an another name parsing would be required). 
<br/>
Name format: xxxxaa (xxxx is an unique number, aa represents the animation position)

* xxxx is an unique number (Used as a name)
* aa represents the animation position. (up to 98, 99 is forbidden).

The animation number is used in case of 'dynamic image' loading. This process will load the first image (00) and then, will try to load image while image aa+1 exists (Note, 99 is forbidden, since a bug could occure as the next image will be loaded as well in the current animation loading)


<!-- *********************************************************************** -->
## Create your own theme
You can create your own theme and import it in the game. 

![img](https://github.com/GeekyMoose/battleship/blob/master/documents/screenshots/gif/theme_default.gif)
![img](https://github.com/GeekyMoose/battleship/blob/master/documents/screenshots/gif/theme_paint.gif)

All themes are located in the folder `data/theme`. <br/>
In order to now all the images you must create, take a look at the `data/theme/default` folder.

#### Theme crafting steps
- Create a new folder in `data/theme` (The folder name is the theme name)
- Draw all the required images and place them inside your own theme folder.
- During game execution, use the menu to change the displayed theme
- If some images are missing, the loading will fail.

#### WARNING
- The way you organize you theme folder (inside) doesn't matter at all since path are dynamically created.
- In order to be valid, all images needed by the game must be created (Or the theme loading will fail).
- The dimension to use for each image can be recovered from default theme. **Bad dimension is not checked and could be critical for the game execution!**


<!-- *********************************************************************** -->
## Screenshots
A full list of screenshots for various themes can be found in the [screenshots
folder](https://github.com/GeekyMoose/battleship/tree/master/documents/screenshots).


<!-- *********************************************************************** -->
## JavaDoc
You can generate the JavaDoc using `exec.sh -o`


<!-- *********************************************************************** -->
## Why the paint theme? 
While we were coding this project, we had a joke about the graphic design of the project, imagining what could happened if a child was drawing the project. And so, you know what happened! The most difficult part was to stay serious in the university library during this awful task.


<!-- *********************************************************************** -->
## Authors
* Constantin MASSON ([GeekyMoose](https://github.com/GeekyMoose))
* Anthony CHAFFOT ([NightWold](https://github.com/NightWolfRobot))
* Jessica FAVIN ([JessicaFavin](https://github.com/JessicaFavin))

