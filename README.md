# Battleship


![img](https://github.com/GeekyMoose/battleship/blob/master/documents/screenshots/theme_default/1-defaultWelcome.png)


<!-- *********************************************************************** -->
## Description
Frebruary - May 2015

Battleship is a university project realized during the second year of Bachelor of Computer Science. 
The goal was to realize a battleship game using Java.

The teacher in charge was [Michele Pagani](http://www.pps.univ-paris-diderot.fr/~pagani/) (michele.pagani@pps.univ-paris-diderot.fr)
<br/>
We got the best grade for this project.


<!-- *********************************************************************** -->
## Installation
Clone this project on your computer using 
`git clone https://github.com/GeekyMoose/battleship.git`


<!-- *********************************************************************** -->
## Execute
#### On linux and MAC
To run the project, use the script `exec.sh`<br/>
Allow executable rights with `chmod 755 exec.sh`

* Execute project with `./exec.sh -x`
* Generate javadoc with `./exec.sh -o`
* Generate jar file with `./exec.sh -j`
* Display help with `./exec.sh -h`

#### On Windows
Simple execute the jar file `battleship.jar` from the project directory. If you want to move the file, don't forget to move the folder data as well
(As it's required for program execution, or you will get a nice error warning)

####WARNING
Don't forget to check the technical description (Java version for example)


<!-- *********************************************************************** -->
## Technical description
- Java version: java SE 8 (Warning, we didn't develope compatibility for older version)
- Language: Java / Swing
- IDE used: NetBeans 8
- Network: TCP Protocol (The server itself is written in Java)


<!-- *********************************************************************** -->
## Explosion animation
![img](https://github.com/GeekyMoose/battleship/blob/master/documents/screenshots/gif/explosion_demo.gif)

Explosions are managed by a 'handcraft java library'. (Can be find in `com.battleship.dynamic` package).

#### How does it work
The explosions manager can be compared to an automata with a specific number a states. 

* Each state is an image. (Even if `EvenApp` can theorically manage any type)
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
Name format: xxxxaa (xxxx is an unique number, aa represente the animation position)

* xxxx is an unique number (Used as a name)
* aa represente the animation position. (up to 98, 99 is forbidden).

The animation number is used in case of 'dynamic image' loading. This process will load the first image (00) and then, will try to load image while image aa+1 exists (Note, 99 is forbidden, since a bug could occure as the next image will be loaded as well in the current animation loading)


<!-- *********************************************************************** -->
## Create your own theme
You can create your own theme and import it in the game. 

![img](https://github.com/GeekyMoose/battleship/blob/master/documents/screenshots/gif/theme_default.gif)
![img](https://github.com/GeekyMoose/battleship/blob/master/documents/screenshots/gif/theme_paint.gif)

All themes are located in the folder `data/theme`. 
<br/>
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
JavaDoc for this project is available in the folder [documents/javadoc](https://github.com/GeekyMoose/battleship/tree/master/documents/JavaDoc)


<!-- *********************************************************************** -->
## Why the paint theme? 
While we were coding this project, we had a joke about the graphic design of the project, imagining what could happened if a child was drawing the project. And so, you know what hapened! The most difficult part was to stay serious in the university library during this awful task.


## Authors
* Constantin MASSON ([GeekyMoose](https://github.com/GeekyMoose))
* Anthony CHAFFOT ([NightWold](https://github.com/NightWolfRobot))
* Jessica FAVIN ([JessicaFavin](https://github.com/JessicaFavin))



