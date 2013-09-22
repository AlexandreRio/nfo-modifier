nfo-modifier
=======

nfo-modifier is a Java application that allows the modification of .nfo files (i.e. encoded in cp437 )
and provides several tools to ease the creation and manipulation of nfo.

You can have a look at the documentation : `doc/index.html`.

For the record I have nothing to do with the `exemple.nfo` file, I just picked it with google.

![example.nfo file](https://raw.github.com/AlexandreRio/nfo-modifier/master/interface.png)

Usage
-------
Simply launch nfo-modifier.java with a Java Runtime Environment > 1.7

If you prefer building from the sources, you will need to use ant.

    $ ant run

Compiles the sources and run the application in graphical mode

    $ ant jar

Creates a .jar of the application named `build/nfo-modifier.jar`, after that
the rest of the folder can be deleted. All the necessery files are included in
the jar archive.

    $ ant -p

Displays all the allowed tasks.

    $ ant help

Displays the help.

    $ java -jar nfo-modifier.jar

Launch the application with an empty text area.

    $ java -jar nfo-modifier.jar file.nfo

Launch the application and display the file : `file.nfo`.

Features list
-------
* Read and write nfo files,
* Create profiles to manage templates,

TODO list
-------

* Sort of status bar with : current longest line, number of lines,
* Tools for text manipulation, move a text of a column towards the right/left,

Special Thanks
-------

Thanks to [tips4java](http://tips4java.wordpress.com/) for the text line
number under a really permissive license.
